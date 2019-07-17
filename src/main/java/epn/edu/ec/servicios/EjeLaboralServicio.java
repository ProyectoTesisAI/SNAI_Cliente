package epn.edu.ec.servicios;

import epn.edu.ec.modelo.EjeLaboral;
import epn.edu.ec.utilidades.Constantes;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class EjeLaboralServicio {
        
    private final Client cliente;
    public static final String URL_EJE_LABORAL=Constantes.URL_EJE_LABORAL;  
    
    public EjeLaboralServicio(){
        cliente= ClientBuilder.newClient();
    }   

    public EjeLaboral guardarEjeLaboral(EjeLaboral ejeLaboral){
        
        EjeLaboral ejeLaboralAux=null;
                       
        WebTarget webTarget=cliente.target(URL_EJE_LABORAL);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response = invocationBuilder.put(Entity.entity(ejeLaboral, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        
        if(response.getStatus()==200){        
            ejeLaboralAux=response.readEntity(EjeLaboral.class);       
        } 
       
        return ejeLaboralAux;
        
    }

    public EjeLaboral obtenerEjeLaboral(Integer id){
        
        EjeLaboral ejeLaboralAux=null;
                       
        WebTarget webTarget=cliente.target(URL_EJE_LABORAL).path(id.toString());        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            ejeLaboralAux= response.readEntity(EjeLaboral.class);
        }           
        return ejeLaboralAux;
        
    }
}
