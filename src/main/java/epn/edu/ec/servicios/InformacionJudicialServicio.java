package epn.edu.ec.servicios;

import epn.edu.ec.modelo.InformacionJudicial;
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

public class InformacionJudicialServicio {
        
    private final Client cliente;
    public static final String URL_INFORMACION_JUDICIAL=Constantes.URL_INFORMACION_JUDICIAL;  
    
    public InformacionJudicialServicio(){
        cliente= ClientBuilder.newClient();
    }   

    public InformacionJudicial guardarInformacionJudicial(InformacionJudicial informacionJudicial){
        
        InformacionJudicial informacionJudicialAux=null;
                       
        WebTarget webTarget=cliente.target(URL_INFORMACION_JUDICIAL);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response = invocationBuilder.put(Entity.entity(informacionJudicial, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        
        if(response.getStatus()==200){        
            informacionJudicialAux=response.readEntity(InformacionJudicial.class);       
        } 
       
        return informacionJudicialAux;
        
    }

    public InformacionJudicial obtenerInformacionJudicial(Integer id){
        
        InformacionJudicial informacionJudicialAux=null;
                       
        WebTarget webTarget=cliente.target(URL_INFORMACION_JUDICIAL).path(id.toString());        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            informacionJudicialAux= response.readEntity(InformacionJudicial.class);
        }           
        return informacionJudicialAux;
        
    }
}
