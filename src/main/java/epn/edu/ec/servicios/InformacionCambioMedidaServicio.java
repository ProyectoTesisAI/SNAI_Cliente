package epn.edu.ec.servicios;

import epn.edu.ec.modelo.InformacionCambioMedidaCAI;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.core.Response;

public class InformacionCambioMedidaServicio {
        
    private final ConexionServicio<InformacionCambioMedidaCAI> conexion;    
    private static final String URL_INFORMACION_CAMBIO_MEDIDA=Constantes.URL_INFORMACION_CAMBIO_MEDIDA_CAI;  
    
    public InformacionCambioMedidaServicio(){
        conexion= new ConexionServicio<>();
    }   

    public InformacionCambioMedidaCAI guardarInformacionCambioMedidaCAI(InformacionCambioMedidaCAI informacionCambioMedida){
        
        try {
            InformacionCambioMedidaCAI informacionCambioMedidaAux = null;
            Response response = conexion.conexion(URL_INFORMACION_CAMBIO_MEDIDA, "PUT", true, informacionCambioMedida);
            if (response != null) {
                if (response.getStatus() == 200) {
                    informacionCambioMedidaAux = response.readEntity(InformacionCambioMedidaCAI.class);
                }
            }

            return informacionCambioMedidaAux;
        } catch (Exception e) {
            return null;
        }      
    }

    public InformacionCambioMedidaCAI obtenerInformacionCambioMedidaCAI(Integer id){
        
        try {
            InformacionCambioMedidaCAI informacionCambioMedidaAux = null;
            Response response = conexion.conexion(URL_INFORMACION_CAMBIO_MEDIDA + "/" + id.toString(), "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    informacionCambioMedidaAux = response.readEntity(InformacionCambioMedidaCAI.class);
                }
            }

            return informacionCambioMedidaAux;
        } catch (Exception e) {
            return null;
        }
          
    }
}
