package epn.edu.ec.servicios;

import epn.edu.ec.modelo.MotivoEgresoCAI;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.core.Response;

public class MotivoEgresoCAIServicio {
        
    private final ConexionServicio<MotivoEgresoCAI> conexion;    
    private static final String URL_MOTIVO_EGRESO=Constantes.URL_MOTIVO_EGRESO_CAI;  
    
    public MotivoEgresoCAIServicio(){
        conexion= new ConexionServicio<>();
    }   

    public MotivoEgresoCAI guardarMotivoEgresoCAI(MotivoEgresoCAI motivoEgresoCAI){
        
        MotivoEgresoCAI motivoEgresoCAIAux=null;
        Response response= conexion.conexion(URL_MOTIVO_EGRESO, "PUT", true, motivoEgresoCAI);
        if(response.getStatus()==200){        
            motivoEgresoCAIAux=response.readEntity(MotivoEgresoCAI.class);       
        } 
       
        return motivoEgresoCAIAux;
        
    }

    public MotivoEgresoCAI obtenerMotivoEgresoCAI(Integer id){
        
        MotivoEgresoCAI motivoEgresoCAIAux=null;
        Response response= conexion.conexion(URL_MOTIVO_EGRESO+"/"+id.toString(), "GET", true, null);
        if(response.getStatus()==200){
            motivoEgresoCAIAux= response.readEntity(MotivoEgresoCAI.class);
        }           
        return motivoEgresoCAIAux;
        
    }
}
