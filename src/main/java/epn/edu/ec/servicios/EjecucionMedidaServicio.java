package epn.edu.ec.servicios;

import epn.edu.ec.modelo.EjecucionMedidaCAI;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class EjecucionMedidaServicio {
        
    private final Client cliente;
    public static final String URL_EJECUCION_MEDIDA=Constantes.URL_EJECUCION_MEDIDA_CAI;  
    
    public EjecucionMedidaServicio(){
        cliente= ClientBuilder.newClient();
    }   

    public EjecucionMedidaCAI guardarEjecucionMedidaCAI(EjecucionMedidaCAI ejecucionMedida){
        
        EjecucionMedidaCAI ejecucionMedidaAux=null;
                       
        WebTarget webTarget=cliente.target(URL_EJECUCION_MEDIDA);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response = invocationBuilder.put(Entity.entity(ejecucionMedida, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        
        if(response.getStatus()==200){        
            ejecucionMedidaAux=response.readEntity(EjecucionMedidaCAI.class);       
        } 
       
        return ejecucionMedidaAux;
        
    }

    public EjecucionMedidaCAI obtenerEjecucionMedidaCAI(Integer id){
        
        EjecucionMedidaCAI ejecucionMedidaAux=null;
                       
        WebTarget webTarget=cliente.target(URL_EJECUCION_MEDIDA).path(id.toString());        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            ejecucionMedidaAux= response.readEntity(EjecucionMedidaCAI.class);
        }           
        return ejecucionMedidaAux;
        
    }
}
