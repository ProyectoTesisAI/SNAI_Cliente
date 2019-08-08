package epn.edu.ec.servicios;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.modelo.RegistroAsistencia;
import epn.edu.ec.modelo.CAI;
import epn.edu.ec.modelo.ItemTaller;
import epn.edu.ec.modelo.Taller;
import epn.edu.ec.modelo.UDI;
import epn.edu.ec.modelo.Usuario;
import epn.edu.ec.utilidades.Constantes;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class TallerServicio {
    
    private final ConexionServicio<Taller> conexion;
    private final ConexionServicio<Usuario> conexionUsuario;
    private final ConexionServicio<UDI> conexionUDI;
    private final ConexionServicio<CAI> conexionCAI;
    private static final String URL_TALLER=Constantes.URL_TALLER;
    
    public TallerServicio(){
        conexion= new ConexionServicio<>();
        conexionUsuario= new ConexionServicio<>();
        conexionUDI= new ConexionServicio<>();
        conexionCAI= new ConexionServicio<>();
    }
    
    public Taller guardarTaller(Taller taller){
        
        Taller tallerAux=null;
        Response response= conexion.conexion(URL_TALLER, "POST", true, taller);
        if(response.getStatus()==200){
            tallerAux =response.readEntity(Taller.class);
        }
        
        return tallerAux;

    }
    
    public Taller editarTaller(Taller taller){
        
        Taller tallerAux=null;
        Response response= conexion.conexion(URL_TALLER, "PUT", true, taller);
        if(response.getStatus()==200){
            tallerAux =response.readEntity(Taller.class);
        }
        
        return tallerAux;

    }
    
    public int eliminarTaller(Integer id) {

        int statusRespuesta = 0;
        Response response = conexion.conexion(URL_TALLER+"/"+id.toString(), "DELETE", true, null);
        if (response.getStatus() == 200) {
            statusRespuesta = 200;
        }
        return statusRespuesta;
    }
    
    public List<Taller> listaTalleresSinInforme(){
        
        List<Taller> listaActividadesAux=null;
        Response response= conexion.conexion(URL_TALLER+"/TalleresSinInforme", "GET", true, null);
        if(response.getStatus()==200){
            listaActividadesAux= response.readEntity(new GenericType<List<Taller>>(){});
        }           
        return listaActividadesAux;
    }
    
    public List<Taller> listaTalleresSinInformePorUsuario(Usuario usuario){
        
        List<Taller> listaActividadesAux=null;
        Response response= conexionUsuario.conexion(URL_TALLER+"/TalleresSinInformePorUsuario", "POST", true, usuario);
        if(response.getStatus()==200){
            listaActividadesAux= response.readEntity(new GenericType<List<Taller>>(){});
        }else if(response.getStatus()==204){
            listaActividadesAux=new ArrayList<>();
        }
        return listaActividadesAux;
    }
    
    public List<Taller> listaTalleresSinInformeSoloUZDI(){
        
        List<Taller> listaActividadesAux=null;
        Response response= conexionUsuario.conexion(URL_TALLER+"/TalleresSinInformeSoloUZDI", "GET", true, null);
        if(response.getStatus()==200){
            listaActividadesAux= response.readEntity(new GenericType<List<Taller>>(){});
        }else if(response.getStatus()==204){
            listaActividadesAux=new ArrayList<>();
        }
        return listaActividadesAux;
    }
    
    public List<Taller> listaTalleresSinInformeSoloCAI(){
        
        List<Taller> listaActividadesAux=null;
        Response response= conexionUsuario.conexion(URL_TALLER+"/TalleresSinInformeSoloCAI", "GET", true, null);
        if(response.getStatus()==200){
            listaActividadesAux= response.readEntity(new GenericType<List<Taller>>(){});
        }else if(response.getStatus()==204){
            listaActividadesAux=new ArrayList<>();
        }
        return listaActividadesAux;
    }
    
    public List<Taller> listaTalleresConInforme(){
        
        List<Taller> listaActividadesAux=null;
        Response response= conexion.conexion(URL_TALLER+"/TalleresConInforme", "GET", true, null);
        if(response.getStatus()==200){
            listaActividadesAux= response.readEntity(new GenericType<List<Taller>>(){});
        }           
        return listaActividadesAux;
    }
    
    public Integer obtenerNumeroAdolescentePorUdi(UDI udi ){
        
        Integer cantidadAdolescentesAux=0;
        Response response= conexionUDI.conexion(URL_TALLER+"/NumeroAdolescentesPorUzdi", "POST", true, udi);
        if(response.getStatus()==200){
           
            String cantidadAdolescente= response.readEntity(String.class);
                    
            if(cantidadAdolescente!=null){
                
                cantidadAdolescentesAux= Integer.parseInt(cantidadAdolescente);
            }
        }
        return cantidadAdolescentesAux;
    }
    
    public Integer obtenerNumeroAdolescentePorCai(CAI cai ){
        
        Integer cantidadAdolescentesAux=0;
        Response response= conexionCAI.conexion(URL_TALLER+"/NumeroAdolescentesPorCai", "POST", true, cai);
        if(response.getStatus()==200){
           
            String cantidadAdolescente= response.readEntity(String.class);
                    
            if(cantidadAdolescente!=null){
                
                cantidadAdolescentesAux= Integer.parseInt(cantidadAdolescente);
            }
        }
        
        return cantidadAdolescentesAux;
    }
    
    public List<AdolescenteInfractorUDI> listaAdolescentesPorUzdi(UDI udi){
        
        List<AdolescenteInfractorUDI> asistenciaAux=null;
        Response response= conexionUDI.conexion(URL_TALLER+"/ListarAdolescentesPorUzdi", "POST", true, udi);
        if(response.getStatus()==200){
            
            List<AdolescenteInfractorUDI> listaAsistenciaUdi= response.readEntity(new GenericType<List<AdolescenteInfractorUDI>>(){});
            if(listaAsistenciaUdi != null && listaAsistenciaUdi.size() >0){               
                
                asistenciaAux=listaAsistenciaUdi;
            }
        }
        return asistenciaAux;
    }
    
    public List<ItemTaller> obtenerItemsPorTalleres(Integer idTaller){
        
        List<ItemTaller> listaItemsTaller=null;
        Response response= conexion.conexion(URL_TALLER+"/ItemsTaller/"+idTaller.toString(), "GET", true, null);
        if(response.getStatus()==200){
            listaItemsTaller= response.readEntity(new GenericType<List<ItemTaller>>(){});
        }           
        return listaItemsTaller;
    }
    
    public RegistroAsistencia obtenerRegistroAsistenciaPorTaller(Integer idTaller){
        
        RegistroAsistencia registroAsistenciaAux =null;
        Response response= conexion.conexion(URL_TALLER+"/RegistroAsistencia/"+idTaller.toString(), "GET", true, null);
        if(response.getStatus()==200){
            registroAsistenciaAux= response.readEntity(RegistroAsistencia.class);
        }           
        return registroAsistenciaAux;
    }
}
