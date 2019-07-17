package epn.edu.ec.servicios;

import epn.edu.ec.modelo.IdentificacionGeografica;
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

public class IdentificacionGeograficaServicio {
        
    private final Client cliente;
    public static final String URL_IDENTIFICACION_GEOGRAFICA=Constantes.URL_IDENTIFICACION_GEOGRAFICA;  
    
    public IdentificacionGeograficaServicio(){
        cliente= ClientBuilder.newClient();
    }   

    public IdentificacionGeografica guardarIdentificacionGeografica(IdentificacionGeografica identificacionGeografica){
        
        IdentificacionGeografica identificacionGeograficaAux=null;
                       
        WebTarget webTarget=cliente.target(URL_IDENTIFICACION_GEOGRAFICA);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response = invocationBuilder.put(Entity.entity(identificacionGeografica, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        
        if(response.getStatus()==200){        
            identificacionGeograficaAux=response.readEntity(IdentificacionGeografica.class);       
        } 
       
        return identificacionGeograficaAux;
        
    }

    public IdentificacionGeografica obtenerIdentificacionGeografica(Integer id){
        
        IdentificacionGeografica identificacionGeograficaAux=null;
                       
        WebTarget webTarget=cliente.target(URL_IDENTIFICACION_GEOGRAFICA).path(id.toString());        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            identificacionGeograficaAux= response.readEntity(IdentificacionGeografica.class);
        }           
        return identificacionGeograficaAux;
        
    }
}
