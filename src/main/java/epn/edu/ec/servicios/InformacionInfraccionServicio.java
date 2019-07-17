package epn.edu.ec.servicios;

import epn.edu.ec.modelo.InformacionInfraccion;
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

public class InformacionInfraccionServicio {
        
    private final Client cliente;
    public static final String URL_INFORMACION_INFRACCION=Constantes.URL_INFORMACION_INFRACCION;  
    
    public InformacionInfraccionServicio(){
        cliente= ClientBuilder.newClient();
    }   

    public InformacionInfraccion guardarInformacionInfraccion(InformacionInfraccion informacionInfraccion){
        
        InformacionInfraccion informacionInfraccionAux=null;
                       
        WebTarget webTarget=cliente.target(URL_INFORMACION_INFRACCION);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response = invocationBuilder.put(Entity.entity(informacionInfraccion, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        
        if(response.getStatus()==200){        
            informacionInfraccionAux=response.readEntity(InformacionInfraccion.class);       
        } 
       
        return informacionInfraccionAux;
        
    }

    public InformacionInfraccion obtenerInformacionInfraccion(Integer id){
        
        InformacionInfraccion informacionInfraccionAux=null;
                       
        WebTarget webTarget=cliente.target(URL_INFORMACION_INFRACCION).path(id.toString());        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            informacionInfraccionAux= response.readEntity(InformacionInfraccion.class);
        }           
        return informacionInfraccionAux;
        
    }
}
