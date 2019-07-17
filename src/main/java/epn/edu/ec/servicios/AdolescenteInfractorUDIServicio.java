package epn.edu.ec.servicios;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.utilidades.Constantes;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AdolescenteInfractorUDIServicio {
    
    private final Client cliente;
    public String URL_ADOLESCENTE_INFRACTOR_UDI=Constantes.URL_ADOLESCENTE_UDI;
    
    public AdolescenteInfractorUDIServicio(){
        cliente= ClientBuilder.newClient();
    }
    
    public AdolescenteInfractorUDI guardarAdolescenteInfractorUDI(AdolescenteInfractorUDI adolescenteInfractorUDI){
        
        AdolescenteInfractorUDI adolescenteInfractorUDIAux=null;
                       
        WebTarget webTarget=cliente.target(URL_ADOLESCENTE_INFRACTOR_UDI);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response = invocationBuilder.post(Entity.entity(adolescenteInfractorUDI, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        
        if(response.getStatus()==200){       
            adolescenteInfractorUDIAux=response.readEntity(AdolescenteInfractorUDI.class);       
        } 
       
        return adolescenteInfractorUDIAux;
        
    }

    public AdolescenteInfractorUDI obtenerAdolescenteInfractorUDI(Integer id){
        
        AdolescenteInfractorUDI adolescenteInfractorUDIAux=null;
                       
        WebTarget webTarget=cliente.target(URL_ADOLESCENTE_INFRACTOR_UDI).path(id.toString());        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            adolescenteInfractorUDIAux= response.readEntity(AdolescenteInfractorUDI.class);
        }           
        return adolescenteInfractorUDIAux;
        
    }
        
    public List<AdolescenteInfractorUDI> listaAdolescentesInfractoresUDI(){
        
        List<AdolescenteInfractorUDI> listaAdolescentesUDIAux=null;
        
        WebTarget webTarget=cliente.target(URL_ADOLESCENTE_INFRACTOR_UDI);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            listaAdolescentesUDIAux =response.readEntity(new GenericType<List<AdolescenteInfractorUDI>>(){});
        }           
        return listaAdolescentesUDIAux;
    }
    
    public int eliminarAdolescenteInfractor(Integer id){
        
        int statusRespuesta=0;
        
        WebTarget webTarget=cliente.target(URL_ADOLESCENTE_INFRACTOR_UDI).path(id.toString());        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+ ";charset=UTF-8");        
        Response response=invocationBuilder.delete();
        if(response.getStatus() == 200){
            statusRespuesta=200;
        }
        return statusRespuesta;
    }
}
