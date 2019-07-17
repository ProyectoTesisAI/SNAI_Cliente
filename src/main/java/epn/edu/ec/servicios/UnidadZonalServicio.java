package epn.edu.ec.servicios;

import epn.edu.ec.modelo.UnidadZonal;
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

public class UnidadZonalServicio {
       
    private final Client cliente;
    public static final String URL_UNIDAD_ZONAL=Constantes.URL_UNIDAD_ZONAL;  
    
    public UnidadZonalServicio(){
        cliente= ClientBuilder.newClient();
    }   

    public UnidadZonal guardarUnidadZonal(UnidadZonal unidadZonal){
        
        UnidadZonal unidadZonalAux=null;
                       
        WebTarget webTarget=cliente.target(URL_UNIDAD_ZONAL);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response = invocationBuilder.put(Entity.entity(unidadZonal, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        
        if(response.getStatus()==200){        
            unidadZonalAux=response.readEntity(UnidadZonal.class);       
        } 
       
        return unidadZonalAux;
        
    }

    public UnidadZonal obtenerUnidadZonal(Integer id){
        
        UnidadZonal unidadZonalAux=null;
                       
        WebTarget webTarget=cliente.target(URL_UNIDAD_ZONAL).path(id.toString());        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            unidadZonalAux= response.readEntity(UnidadZonal.class);
        }           
        return unidadZonalAux;
        
    }
        
    
    
    public List<UnidadZonal> listarUnidadZonal(){
        
        List<UnidadZonal> listaUnidadZonalAux=null;
        
        Client client= ClientBuilder.newClient();
        WebTarget webTarget=client.target(URL_UNIDAD_ZONAL);
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON);        
        listaUnidadZonalAux=invocationBuilder.get(new GenericType<List<UnidadZonal>>(){});
        
        return listaUnidadZonalAux;

    }
    
    public String eliminarUnidadZonal(Integer id){
        
        String url=null;
        Client client= ClientBuilder.newClient();
        WebTarget webTarget=client.target(URL_UNIDAD_ZONAL).path(id.toString());
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON);        
        Response response=invocationBuilder.delete();
        if(response !=null){
            url="/menu.com?faces-redirect=true";
        }
        return url;
    }
}
