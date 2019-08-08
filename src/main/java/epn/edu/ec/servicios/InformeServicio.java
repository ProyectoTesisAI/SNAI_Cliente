package epn.edu.ec.servicios;

import epn.edu.ec.modelo.Informe;
import epn.edu.ec.modelo.Usuario;
import epn.edu.ec.utilidades.Constantes;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class InformeServicio {
    
    private final ConexionServicio<Informe> conexion;
    private final ConexionServicio<Usuario> conexionU;
    private static final String URL_INFORME=Constantes.URL_INFORME;
    
    public InformeServicio(){
        conexion= new ConexionServicio<>();
        conexionU= new ConexionServicio<>();
    }
    
    public Informe guardarInforme(Informe informe){
        
        Informe informeAux=null;
        Response response= conexion.conexion(URL_INFORME, "PUT", true, informe);
        if(response.getStatus()==200){
            informeAux =response.readEntity(Informe.class);
        }
        return informeAux;

    }    
    
    public int eliminarInforme(Integer id) {

        int statusRespuesta = 0;
        Response response = conexion.conexion(URL_INFORME+"/"+id.toString(), "DELETE", true, null);
        if (response.getStatus() == 200) {
            statusRespuesta = 200;
        }
        return statusRespuesta;
    }
    
    public List<Informe> listarInforme(){
        
        List<Informe> listaItemsInforme=null;
        Response response= conexion.conexion(URL_INFORME, "GET", true, null);
        if(response.getStatus()==200){
            listaItemsInforme= response.readEntity(new GenericType<List<Informe>>(){});
        }           
        return listaItemsInforme;
    }
    
    public List<Informe> listarInformesPorUsuario(Usuario usuario){
        List<Informe> listaInformesAux = null;
        Response response = conexionU.conexion(URL_INFORME+"/InformesPorUsuario", "POST", true, usuario);
        if(response.getStatus()==200){
            listaInformesAux=response.readEntity(new GenericType<List<Informe>>(){});
        }else if(response.getStatus()==204){
            listaInformesAux=new ArrayList<>();
        }
        return listaInformesAux;
    }
    
    public List<Informe> listarInformesSoloUZDI(){
        List<Informe> listaInformesAux = null;
        Response response = conexionU.conexion(URL_INFORME+"/InformeSoloUZDI", "GET", true, null);
        if(response.getStatus()==200){
            listaInformesAux=response.readEntity(new GenericType<List<Informe>>(){});
        }else if(response.getStatus()==204){
            listaInformesAux=new ArrayList<>();
        }
        return listaInformesAux;
    }
    
    public List<Informe> listarInformesSoloCAI(){
        List<Informe> listaInformesAux = null;
        Response response = conexionU.conexion(URL_INFORME+"/InformeSoloCAI", "GET", true, null);
        if(response.getStatus()==200){
            listaInformesAux=response.readEntity(new GenericType<List<Informe>>(){});
        }else if(response.getStatus()==204){
            listaInformesAux=new ArrayList<>();
        }
        return listaInformesAux;
    }
    
}
