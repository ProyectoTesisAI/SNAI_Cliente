package epn.edu.ec.servicios;

import epn.edu.ec.modelo.AdolescenteInfractor;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AdolescenteInfractorServicio {
    private final Client cliente;
    public String URL_ADOLESCENTE_INFRACTOR=Constantes.URL_ADOLESCENTE;

    public AdolescenteInfractorServicio() {
        cliente= ClientBuilder.newClient();
    }
    
    public AdolescenteInfractor guardarAdolescenteInfractorUDI(AdolescenteInfractor adolescenteInfractor){
        
        AdolescenteInfractor adolescenteInfractorAux=null;
                       
        WebTarget webTarget=cliente.target(URL_ADOLESCENTE_INFRACTOR);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response = invocationBuilder.post(Entity.entity(adolescenteInfractor, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        
        if(response.getStatus()==200){       
            adolescenteInfractorAux=response.readEntity(AdolescenteInfractor.class);       
        } 
       
        return adolescenteInfractorAux;
        
    }
}
