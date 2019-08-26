package epn.edu.ec.servicios;

import epn.edu.ec.modelo.InformacionInfraccion;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.core.Response;

public class InformacionInfraccionServicio {
    
    private final ConexionServicio<InformacionInfraccion> conexion;    
    private static final String URL_INFORMACION_INFRACCION=Constantes.URL_INFORMACION_INFRACCION;  
    
    public InformacionInfraccionServicio(){
        conexion= new ConexionServicio<>();
    }   

    public InformacionInfraccion guardarInformacionInfraccion(InformacionInfraccion informacionInfraccion){
        
        try {
            InformacionInfraccion informacionInfraccionAux = null;
            Response response = conexion.conexion(URL_INFORMACION_INFRACCION, "PUT", true, informacionInfraccion);
            if (response != null) {
                if (response.getStatus() == 200) {
                    informacionInfraccionAux = response.readEntity(InformacionInfraccion.class);
                }
            }

            return informacionInfraccionAux;
        } catch (Exception e) {
            return null;
        }
  
    }

    public InformacionInfraccion obtenerInformacionInfraccion(Integer id){
        
        try {
            InformacionInfraccion informacionInfraccionAux = null;
            Response response = conexion.conexion(URL_INFORMACION_INFRACCION + "/" + id.toString(), "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    informacionInfraccionAux = response.readEntity(InformacionInfraccion.class);
                }
            }

            return informacionInfraccionAux;
        } catch (Exception e) {
            return null;
        }   
    }
}
