/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.servicios;

import epn.edu.ec.modelo.CAI;
import epn.edu.ec.utilidades.Constantes;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author User
 */
public class CaiServicio {
    
    private final Client cliente;
    public String URL_CAI=Constantes.URL_CAI; 
    
    public CaiServicio(){
        cliente= ClientBuilder.newClient();
    }   
    
    public List<CAI> listaCai(){
        
        List<CAI> listaCAIAux=null;
        
        WebTarget webTarget=cliente.target(URL_CAI);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response=invocationBuilder.get();
        if(response.getStatus()==200){
            listaCAIAux=response.readEntity(new GenericType<List<CAI>>(){});
        }
        
        return listaCAIAux;

    }
    
}
