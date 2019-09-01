package epn.edu.ec.servicios;

import epn.edu.ec.modelo.Usuario;
import epn.edu.ec.utilidades.URLServicios;
import javax.ws.rs.core.Response;

public class LoginServicio {
    
    private final ConexionServicio<Usuario> conexion;    
    private static final String URL_USUARIO_LOGIN=URLServicios.URL_USUARIO;
    
    public LoginServicio(){
        conexion= new ConexionServicio<>();
    }
    
    public Usuario loguearUsuario(Usuario usuario){
        
        try {
            Usuario usuarioAux = null;
            Response response = conexion.conexion(URL_USUARIO_LOGIN + "/login", "POST", false, usuario);
            if (response != null) {
                if (response.getStatus() == 200) {
                    usuarioAux = response.readEntity(Usuario.class);
                }
            }

            return usuarioAux;
        } catch (Exception e) {
            return null;
        }

    }
}
