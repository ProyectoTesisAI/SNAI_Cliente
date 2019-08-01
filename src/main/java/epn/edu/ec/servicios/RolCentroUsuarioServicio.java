package epn.edu.ec.servicios;

import epn.edu.ec.modelo.RolCentroUsuario;
import epn.edu.ec.utilidades.Constantes;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class RolCentroUsuarioServicio {
    
    private final ConexionServicio<RolCentroUsuario> conexion;
    private static final String URL_ROL_CENTRO_USUARIO=Constantes.URL_ROL_CENTRO_USUARIO;

    public RolCentroUsuarioServicio() {
        this.conexion = new ConexionServicio<>();
    }
    /*No se usa por el hecho que se tiene quemado ya todos los valores a ocupar en la t_rol_centro_usuario*/
    public RolCentroUsuario guardarRolCentroUsuario(RolCentroUsuario rolcentrousuario) {

        RolCentroUsuario rcuAux = null;
        Response response = conexion.conexion(URL_ROL_CENTRO_USUARIO, "POST", true, rolcentrousuario);
        if (response.getStatus() == 200) {
            rcuAux = response.readEntity(RolCentroUsuario.class);
        }
        return rcuAux;
    }

    public RolCentroUsuario obtenerRolCentroUsuario(Integer id) {

        RolCentroUsuario rcuAux = null;
        Response response = conexion.conexion(URL_ROL_CENTRO_USUARIO + "/" + id.toString(), "GET", true, null);
        if (response.getStatus() == 200) {
            rcuAux = response.readEntity(RolCentroUsuario.class);
        }
        return rcuAux;
    }

    public List<RolCentroUsuario> listaRolCentroUsuario() {

        List<RolCentroUsuario> listaRCUAux = null;
        Response response = conexion.conexion(URL_ROL_CENTRO_USUARIO, "GET", true, null);
        if (response.getStatus() == 200) {
            listaRCUAux = response.readEntity(new GenericType<List<RolCentroUsuario>>() {
            });
        }
        return listaRCUAux;
    }
    
    /*Solo para ADMINISTRADOR, COORDINADOR/LIDER UZDI, COORDINADOR CAI*/
    public RolCentroUsuario obtenerRolAdministrativo(RolCentroUsuario rcu) {

        RolCentroUsuario rcuAux = null;
        Response response = conexion.conexion(URL_ROL_CENTRO_USUARIO + "/ObtenerRolAdministrativo", "POST", true, rcu);
        if (response.getStatus() == 200) {
            rcuAux = response.readEntity(RolCentroUsuario.class);
        }else if(response.getStatus()==204){
            rcuAux = null;
        }
        return rcuAux;
    }
    
    /*Solo para JURIDICO, PSICOLOGO DE UZDI*/
    public RolCentroUsuario obtenerRolSoloUDI(RolCentroUsuario rcu) {

        RolCentroUsuario rcuAux = null;
        Response response = conexion.conexion(URL_ROL_CENTRO_USUARIO + "/ObtenerRolSoloUDI", "POST", true, rcu);
        if (response.getStatus() == 200) {
            rcuAux = response.readEntity(RolCentroUsuario.class);
        }else if(response.getStatus()==204){
            rcuAux = null;
        }
        return rcuAux;
    }
    
    /*Solo para JURIDICO, PSICOLOGO DE CAI*/
    public RolCentroUsuario obtenerRolSoloCAI(RolCentroUsuario rcu) {

        RolCentroUsuario rcuAux = null;
        Response response = conexion.conexion(URL_ROL_CENTRO_USUARIO + "/ObtenerRolSoloCAI", "POST", true, rcu);
        if (response.getStatus() == 200) {
            rcuAux = response.readEntity(RolCentroUsuario.class);
        }else if(response.getStatus()==204){
            rcuAux = null;
        }
        return rcuAux;
    }
}
