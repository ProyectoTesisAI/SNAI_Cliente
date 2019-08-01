package epn.edu.ec.servicios;

import epn.edu.ec.modelo.Usuario;
import epn.edu.ec.utilidades.Constantes;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class UsuarioServicio {
    
    private final ConexionServicio<Usuario> conexion;
    private static final String URL_USUARIO=Constantes.URL_USUARIO;

    public UsuarioServicio() {
        this.conexion = new ConexionServicio<>();
    }
    
    public Usuario guardarUsuario(Usuario usuario) {

        Usuario usuarioAux = null;
        Response response = conexion.conexion(URL_USUARIO, "POST", true, usuario);
        if (response.getStatus() == 200) {
            usuarioAux = response.readEntity(Usuario.class);
        }
        return usuarioAux;
    }

    public Usuario obtenerUsuario(Integer id) {

        Usuario rcuAux = null;
        Response response = conexion.conexion(URL_USUARIO + "/" + id.toString(), "GET", true, null);
        if (response.getStatus() == 200) {
            rcuAux = response.readEntity(Usuario.class);
        }
        return rcuAux;
    }

    public List<Usuario> listaAdolescentesInfractoresCAI() {

        List<Usuario> listaUsuarioAux = null;
        Response response = conexion.conexion(URL_USUARIO, "GET", true, null);
        if (response.getStatus() == 200) {
            listaUsuarioAux = response.readEntity(new GenericType<List<Usuario>>() {
            });
        }
        return listaUsuarioAux;
    }
}
