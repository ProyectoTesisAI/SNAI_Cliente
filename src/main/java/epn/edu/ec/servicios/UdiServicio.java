/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.servicios;

import epn.edu.ec.modelo.UDI;
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
public class UdiServicio {
    
    private final Client cliente;
    public String URL_UDI=Constantes.URL_UDI; 
    
    public UdiServicio(){
        cliente= ClientBuilder.newClient();
    }   
    
    public List<UDI> listaUdi(){
        
        List<UDI> listaUDIAux=null;
        
        WebTarget webTarget=cliente.target(URL_UDI);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response=invocationBuilder.get();
        if(response.getStatus()==200){
            listaUDIAux=response.readEntity(new GenericType<List<UDI>>(){});
        }
        
        return listaUDIAux;

    }
}
