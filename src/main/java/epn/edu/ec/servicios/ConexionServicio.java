/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.servicios;

import epn.edu.ec.modelo.Usuario;
import epn.edu.ec.utilidades.Constantes;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author User
 */
public class ConexionServicio<T> {
    
    private final Client cliente;
    private final String token;
    
    public ConexionServicio(){
        
        cliente= ClientBuilder.newClient();
        Usuario usuarioAux= (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
        token=usuarioAux.getToken();
        
    }
    
    public Response conexion(String URL, String tipoPeticion, boolean necesitaToken,  T informacionAEnviar){
        
        try {
            WebTarget webTarget = cliente.target(URL);
            Invocation.Builder invocationBuilder=null;
            
            if(necesitaToken){
                invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8").header(HttpHeaders.AUTHORIZATION,"Bearer "+token );

            }
            else{
                invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8");

            }
            
            Response response = null;

            if (tipoPeticion.equals("GET")) {
                
                response = invocationBuilder.get();
            } 
            else if (tipoPeticion.equals("POST")) {
                
                response = invocationBuilder.post(Entity.entity(informacionAEnviar, MediaType.APPLICATION_JSON + ";charset=UTF-8"));
            } 
            else if (tipoPeticion.equals("PUT")) {
                
                response = invocationBuilder.put(Entity.entity(informacionAEnviar, MediaType.APPLICATION_JSON + ";charset=UTF-8"));
            }
            else if (tipoPeticion.equals("DELETE")) {
                
                response = invocationBuilder.delete();
            }
            return response;
        }
        catch(Exception e){
            return null;
        }
        
    } 
}