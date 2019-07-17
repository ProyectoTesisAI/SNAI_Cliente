package epn.edu.ec.servicios;

import epn.edu.ec.modelo.AdolescenteInfractorCAI;
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

public class AdolescenteInfractorCAIServicio {
    
    private final Client cliente;
    public String URL_ADOLESCENTE_INFRACTOR_CAI=Constantes.URL_ADOLESCENTE_CAI;
    
    public AdolescenteInfractorCAIServicio(){
        cliente= ClientBuilder.newClient();
    }
    
    public AdolescenteInfractorCAI guardarAdolescenteInfractorCAI(AdolescenteInfractorCAI adolescenteInfractorCAI){
        
        AdolescenteInfractorCAI adolescenteInfractorCAIAux=null;
                       
        WebTarget webTarget=cliente.target(URL_ADOLESCENTE_INFRACTOR_CAI);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response = invocationBuilder.post(Entity.entity(adolescenteInfractorCAI, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        
        if(response.getStatus()==200){       
            adolescenteInfractorCAIAux=response.readEntity(AdolescenteInfractorCAI.class);       
        } 
       
        return adolescenteInfractorCAIAux;
        
    }

    public AdolescenteInfractorCAI obtenerAdolescenteInfractorCAI(Integer id){
        
        AdolescenteInfractorCAI adolescenteInfractorCAIAux=null;
                       
        WebTarget webTarget=cliente.target(URL_ADOLESCENTE_INFRACTOR_CAI).path(id.toString());        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            adolescenteInfractorCAIAux= response.readEntity(AdolescenteInfractorCAI.class);
        }           
        return adolescenteInfractorCAIAux;
        
    }
        
    public List<AdolescenteInfractorCAI> listaAdolescentesInfractoresCAI(){
        
        List<AdolescenteInfractorCAI> listaAdolescentesCAIAux=null;
        
        WebTarget webTarget=cliente.target(URL_ADOLESCENTE_INFRACTOR_CAI);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            listaAdolescentesCAIAux =response.readEntity(new GenericType<List<AdolescenteInfractorCAI>>(){});
        }           
        return listaAdolescentesCAIAux;
    }
}
