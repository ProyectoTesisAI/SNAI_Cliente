package epn.edu.ec.servicios;

import epn.edu.ec.modelo.EjeEducativo;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class EjeEducativoServicio {
        
    private final Client cliente;
    public static final String URL_EJE_EDUCATIVO_CAI=Constantes.URL_EJE_EDUCATIVO;  
    
    public EjeEducativoServicio(){
        cliente= ClientBuilder.newClient();
    }   

    public EjeEducativo guardarEjeEducativo(EjeEducativo ejeEducativoCAI){
        
        EjeEducativo ejeEducativoCAIAux=null;
                       
        WebTarget webTarget=cliente.target(URL_EJE_EDUCATIVO_CAI);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response = invocationBuilder.put(Entity.entity(ejeEducativoCAI, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        
        if(response.getStatus()==200){        
            ejeEducativoCAIAux=response.readEntity(EjeEducativo.class);       
        } 
       
        return ejeEducativoCAIAux;
        
    }

    public EjeEducativo obtenerEjeEducativo(Integer id){
        
        EjeEducativo ejeEducativoCAIAux=null;
                       
        WebTarget webTarget=cliente.target(URL_EJE_EDUCATIVO_CAI).path(id.toString());        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            ejeEducativoCAIAux= response.readEntity(EjeEducativo.class);
        }           
        return ejeEducativoCAIAux;
        
    }
}
