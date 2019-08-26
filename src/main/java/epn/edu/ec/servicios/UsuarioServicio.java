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

        try {

            Usuario usuarioAux = null;
            Response response = conexion.conexion(URL_USUARIO, "POST", true, usuario);
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
    
    public Usuario desactivarUsuario(Usuario usuario) {

        try {
            Usuario usuarioAux = null;
            Response response = conexion.conexion(URL_USUARIO, "PUT", true, usuario);
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
    
    public Usuario activarUsuario(Usuario usuario) {

        try {
            Usuario usuarioAux = null;
            Response response = conexion.conexion(URL_USUARIO, "PUT", true, usuario);
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

    public Usuario obtenerUsuario(Integer id) {

        try {
            Usuario rcuAux = null;
            Response response = conexion.conexion(URL_USUARIO + "/" + id.toString(), "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    rcuAux = response.readEntity(Usuario.class);
                }
            }

            return rcuAux;
        } catch (Exception e) {
            return null;
        }
        
    }

    public List<Usuario> listaUsuarios() {

        try {
            List<Usuario> listaUsuarioAux = null;
            Response response = conexion.conexion(URL_USUARIO, "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    listaUsuarioAux = response.readEntity(new GenericType<List<Usuario>>() {
                    });
                }
            }

            return listaUsuarioAux;
        } catch (Exception e) {
            return null;
        }
        
    }
    
    public List<Usuario> listaUsuariosActivos() {

        try {
            List<Usuario> listaUsuarioAux = null;
            Response response = conexion.conexion(URL_USUARIO + "/UsuariosActivos", "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    listaUsuarioAux = response.readEntity(new GenericType<List<Usuario>>() {
                    });
                } else if (response.getStatus() == 204) {
                    listaUsuarioAux = null;
                }
            }

            return listaUsuarioAux;
        } catch (Exception e) {
            return null;
        }
        
    }
    
    public List<Usuario> listaUsuariosDesactivados() {

        try {
            List<Usuario> listaUsuarioAux = null;
            Response response = conexion.conexion(URL_USUARIO + "/UsuariosDesactivados", "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    listaUsuarioAux = response.readEntity(new GenericType<List<Usuario>>() {
                    });
                } else if (response.getStatus() == 204) {
                    listaUsuarioAux = null;
                }
            }

            return listaUsuarioAux;
        } catch (Exception e) {
            return null;
        }
        
    }
}
