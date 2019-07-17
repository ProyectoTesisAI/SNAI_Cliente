package epn.edu.ec.servicios;

import epn.edu.ec.modelo.Representante;
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

public class RepresentanteServicio {
        
    private final Client cliente;
    public static final String URL_REPRESENTANTE=Constantes.URL_REPRESENTANTE;  
    
    public RepresentanteServicio(){
        cliente= ClientBuilder.newClient();
    }   

    public Representante guardarRepresentante(Representante representante){
        
        Representante representanteAux=null;
                       
        WebTarget webTarget=cliente.target(URL_REPRESENTANTE);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response = invocationBuilder.put(Entity.entity(representante, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        
        if(response.getStatus()==200){        
            representanteAux=response.readEntity(Representante.class);       
        } 
       
        return representanteAux;
        
    }

    public Representante obtenerRepresentante(Integer id){
        
        Representante representanteAux=null;
                       
        WebTarget webTarget=cliente.target(URL_REPRESENTANTE).path(id.toString());        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            representanteAux= response.readEntity(Representante.class);
        }           
        return representanteAux;
        
    }
}
