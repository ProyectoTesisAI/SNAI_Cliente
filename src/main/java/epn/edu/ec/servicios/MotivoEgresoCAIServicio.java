package epn.edu.ec.servicios;

import epn.edu.ec.modelo.MotivoEgresoCAI;
import epn.edu.ec.utilidades.URLServicios;
import javax.ws.rs.core.Response;

public class MotivoEgresoCAIServicio {
        
    private final ConexionServicio<MotivoEgresoCAI> conexion;    
    private static final String URL_MOTIVO_EGRESO=URLServicios.URL_MOTIVO_EGRESO_CAI;  
    
    public MotivoEgresoCAIServicio(){
        conexion= new ConexionServicio<>();
    }   

    public MotivoEgresoCAI guardarMotivoEgresoCAI(MotivoEgresoCAI motivoEgresoCAI){
        
        try {
            MotivoEgresoCAI motivoEgresoCAIAux = null;
            Response response = conexion.conexion(URL_MOTIVO_EGRESO, "PUT", true, motivoEgresoCAI);
            if (response != null) {
                if (response.getStatus() == 200) {
                    motivoEgresoCAIAux = response.readEntity(MotivoEgresoCAI.class);
                }
            }
            return motivoEgresoCAIAux;
        } catch (Exception e) {
            return null;
        }
    }

    public MotivoEgresoCAI obtenerMotivoEgresoCAI(Integer id){
        
        try {
            MotivoEgresoCAI motivoEgresoCAIAux = null;
            Response response = conexion.conexion(URL_MOTIVO_EGRESO + "/" + id.toString(), "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    motivoEgresoCAIAux = response.readEntity(MotivoEgresoCAI.class);
                }
            }
            return motivoEgresoCAIAux;
        } catch (Exception e) {
            return null;
        }  
    }
}
