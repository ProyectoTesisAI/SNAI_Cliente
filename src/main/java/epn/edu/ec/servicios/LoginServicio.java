/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.servicios;

import epn.edu.ec.modelo.Usuario;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.core.Response;

/**
 *
 * @author User
 */
public class LoginServicio {
    
    private final ConexionServicio<Usuario> conexion;    
    private static final String URL_USUARIO_LOGIN=Constantes.URL_USUARIO;
    
    public LoginServicio(){
        conexion= new ConexionServicio<>();
    }
    
    public Usuario loguearUsuario(Usuario usuario){
        
        Usuario usuarioAux=null;
        Response response= conexion.conexion(URL_USUARIO_LOGIN+"/login", "POST", false, usuario);
        if(response.getStatus()==200){
            usuarioAux =response.readEntity(Usuario.class);
            
        }
        
        return usuarioAux;

    }
}
