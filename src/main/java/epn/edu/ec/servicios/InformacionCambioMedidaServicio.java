package epn.edu.ec.servicios;

import epn.edu.ec.modelo.InformacionCambioMedidaCAI;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class InformacionCambioMedidaServicio {
        
    private final Client cliente;
    public static final String URL_INFORMACION_CAMBIO_MEDIDA=Constantes.URL_INFORMACION_CAMBIO_MEDIDA_CAI;  
    
    public InformacionCambioMedidaServicio(){
        cliente= ClientBuilder.newClient();
    }   

    public InformacionCambioMedidaCAI guardarInformacionCambioMedidaCAI(InformacionCambioMedidaCAI informacionCambioMedida){
        
        InformacionCambioMedidaCAI informacionCambioMedidaAux=null;
                       
        WebTarget webTarget=cliente.target(URL_INFORMACION_CAMBIO_MEDIDA);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response = invocationBuilder.put(Entity.entity(informacionCambioMedida, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        
        if(response.getStatus()==200){        
            informacionCambioMedidaAux=response.readEntity(InformacionCambioMedidaCAI.class);       
        } 
       
        return informacionCambioMedidaAux;
        
    }

    public InformacionCambioMedidaCAI obtenerInformacionCambioMedidaCAI(Integer id){
        
        InformacionCambioMedidaCAI informacionCambioMedidaAux=null;
                       
        WebTarget webTarget=cliente.target(URL_INFORMACION_CAMBIO_MEDIDA).path(id.toString());        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            informacionCambioMedidaAux= response.readEntity(InformacionCambioMedidaCAI.class);
        }           
        return informacionCambioMedidaAux;
        
    }
}
