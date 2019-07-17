package epn.edu.ec.servicios;

import epn.edu.ec.modelo.EjeSalud;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class EjeSaludServicio {
        
    private final Client cliente;
    public static final String URL_EJE_SALUD_CAI=Constantes.URL_EJE_SALUD;  
    
    public EjeSaludServicio(){
        cliente= ClientBuilder.newClient();
    }   

    public EjeSalud guardarEjeSalud(EjeSalud ejeSaludCAI){
        
        EjeSalud ejeSaludCAIAux=null;
                       
        WebTarget webTarget=cliente.target(URL_EJE_SALUD_CAI);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response = invocationBuilder.put(Entity.entity(ejeSaludCAI, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        
        if(response.getStatus()==200){        
            ejeSaludCAIAux=response.readEntity(EjeSalud.class);       
        } 
       
        return ejeSaludCAIAux;
        
    }

    public EjeSalud obtenerEjeSalud(Integer id){
        
        EjeSalud ejeSaludCAIAux=null;
                       
        WebTarget webTarget=cliente.target(URL_EJE_SALUD_CAI).path(id.toString());        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            ejeSaludCAIAux= response.readEntity(EjeSalud.class);
        }           
        return ejeSaludCAIAux;
        
    }
}
