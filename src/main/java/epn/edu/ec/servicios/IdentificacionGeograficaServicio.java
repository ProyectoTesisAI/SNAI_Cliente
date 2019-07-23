package epn.edu.ec.servicios;
        
import epn.edu.ec.modelo.IdentificacionGeografica;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.core.Response;

public class IdentificacionGeograficaServicio {
    
    private final ConexionServicio<IdentificacionGeografica> conexion;    
    private static final String URL_IDENTIFICACION_GEOGRAFICA=Constantes.URL_IDENTIFICACION_GEOGRAFICA;  
    
    public IdentificacionGeograficaServicio(){
        conexion= new ConexionServicio<>();
    }   

    public IdentificacionGeografica guardarIdentificacionGeografica(IdentificacionGeografica identificacionGeografica){
        
        IdentificacionGeografica identificacionGeograficaAux=null;
        Response response= conexion.conexion(URL_IDENTIFICACION_GEOGRAFICA, "PUT", true, identificacionGeografica);
        if(response.getStatus()==200){        
            identificacionGeograficaAux=response.readEntity(IdentificacionGeografica.class);       
        } 
       
        return identificacionGeograficaAux;
        
    }

    public IdentificacionGeografica obtenerIdentificacionGeografica(Integer id){
        
        IdentificacionGeografica identificacionGeograficaAux=null;
        Response response= conexion.conexion(URL_IDENTIFICACION_GEOGRAFICA+"/"+id.toString(), "GET", true, null);
        if(response.getStatus()==200){
            identificacionGeograficaAux= response.readEntity(IdentificacionGeografica.class);
        }           
        return identificacionGeograficaAux;
        
    }
}
