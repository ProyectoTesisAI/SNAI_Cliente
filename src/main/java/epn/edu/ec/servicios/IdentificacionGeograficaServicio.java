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
        
        try {
            IdentificacionGeografica identificacionGeograficaAux = null;
            Response response = conexion.conexion(URL_IDENTIFICACION_GEOGRAFICA, "PUT", true, identificacionGeografica);
            if (response != null) {
                if (response.getStatus() == 200) {
                    identificacionGeograficaAux = response.readEntity(IdentificacionGeografica.class);
                }
            }

            return identificacionGeograficaAux;
        } catch (Exception e) {
            return null;
        }
      
    }

    public IdentificacionGeografica obtenerIdentificacionGeografica(Integer id){
        
        try {

            IdentificacionGeografica identificacionGeograficaAux = null;
            Response response = conexion.conexion(URL_IDENTIFICACION_GEOGRAFICA + "/" + id.toString(), "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    identificacionGeograficaAux = response.readEntity(IdentificacionGeografica.class);
                }
            }

            return identificacionGeograficaAux;
        } catch (Exception e) {
            return null;
        }      
    }
}
