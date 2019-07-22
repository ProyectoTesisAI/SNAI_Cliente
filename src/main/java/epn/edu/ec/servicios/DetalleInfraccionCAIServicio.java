package epn.edu.ec.servicios;

import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.modelo.DetalleInfraccionCAI;
import epn.edu.ec.utilidades.Constantes;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class DetalleInfraccionCAIServicio {
        
    private final Client cliente;
    public static final String URL_DETALLE_INFRACCION=Constantes.URL_DETALLE_INFRACCION_CAI;  
    
    public DetalleInfraccionCAIServicio(){
        cliente= ClientBuilder.newClient();
    }   

    public DetalleInfraccionCAI guardarDetalleInfraccionCAI(DetalleInfraccionCAI detalleInfraccion){
        
        DetalleInfraccionCAI informacionInfraccionAux=null;
                       
        WebTarget webTarget=cliente.target(URL_DETALLE_INFRACCION);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response = invocationBuilder.put(Entity.entity(detalleInfraccion, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        
        if(response.getStatus()==200){        
            informacionInfraccionAux=response.readEntity(DetalleInfraccionCAI.class);       
        } 
       
        return informacionInfraccionAux;
        
    }

    public DetalleInfraccionCAI obtenerDetalleInfraccionCAI(Integer id){
        
        DetalleInfraccionCAI informacionInfraccionAux=null;
                       
        WebTarget webTarget=cliente.target(URL_DETALLE_INFRACCION).path(id.toString());        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            informacionInfraccionAux= response.readEntity(DetalleInfraccionCAI.class);
        }           
        return informacionInfraccionAux;        
    }
    
    public List<DetalleInfraccionCAI> obtenerDetallesInfraccionCAI(AdolescenteInfractorCAI adolescente){
        
        List<DetalleInfraccionCAI> listaDetallesInfraccionAux=null;
                       
        WebTarget webTarget=cliente.target(URL_DETALLE_INFRACCION+"/ListaInfraccionesPorAdolescente");        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.post(Entity.entity(adolescente, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        if(response.getStatus()==200){
            listaDetallesInfraccionAux= response.readEntity(new GenericType<List<DetalleInfraccionCAI>>(){});
        }else if(response.getStatus()==204){
            listaDetallesInfraccionAux=new ArrayList<>();
        }
        return listaDetallesInfraccionAux;
        
    }
}
