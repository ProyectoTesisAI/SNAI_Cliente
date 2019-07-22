/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.servicios;

import epn.edu.ec.modelo.Usuario;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author User
 */
public class LoginServicio {
    
    private final Client cliente;
    public String URL_USUARIO_LOGIN=Constantes.URL_USUARIO;
    
    public LoginServicio(){
        cliente= ClientBuilder.newClient();
    }
    
    public Usuario loguearUsuario(Usuario usuario){
        
        Usuario usuarioAux=null;
        
        WebTarget webTarget=cliente.target(URL_USUARIO_LOGIN+"/login");        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");     
        Response response =invocationBuilder.post(Entity.entity(usuario, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        if(response.getStatus()==200){
            usuarioAux =response.readEntity(Usuario.class);
            
        }
        
        return usuarioAux;

    }
}
