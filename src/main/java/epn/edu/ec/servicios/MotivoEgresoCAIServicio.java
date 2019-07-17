package epn.edu.ec.servicios;

import epn.edu.ec.modelo.MotivoEgresoCAI;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class MotivoEgresoCAIServicio {
        
    private final Client cliente;
    public static final String URL_MOTIVO_EGRESO=Constantes.URL_MOTIVO_EGRESO_CAI;  
    
    public MotivoEgresoCAIServicio(){
        cliente= ClientBuilder.newClient();
    }   

    public MotivoEgresoCAI guardarMotivoEgresoCAI(MotivoEgresoCAI motivoEgresoCAI){
        
        MotivoEgresoCAI motivoEgresoCAIAux=null;
                       
        WebTarget webTarget=cliente.target(URL_MOTIVO_EGRESO);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response = invocationBuilder.put(Entity.entity(motivoEgresoCAI, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        
        if(response.getStatus()==200){        
            motivoEgresoCAIAux=response.readEntity(MotivoEgresoCAI.class);       
        } 
       
        return motivoEgresoCAIAux;
        
    }

    public MotivoEgresoCAI obtenerMotivoEgresoCAI(Integer id){
        
        MotivoEgresoCAI motivoEgresoCAIAux=null;
                       
        WebTarget webTarget=cliente.target(URL_MOTIVO_EGRESO).path(id.toString());        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            motivoEgresoCAIAux= response.readEntity(MotivoEgresoCAI.class);
        }           
        return motivoEgresoCAIAux;
        
    }
}
