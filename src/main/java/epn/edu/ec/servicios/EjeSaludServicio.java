package epn.edu.ec.servicios;

import epn.edu.ec.modelo.EjeSalud;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.core.Response;

public class EjeSaludServicio {

    private final ConexionServicio<EjeSalud> conexion;
    private static final String URL_EJE_SALUD = Constantes.URL_EJE_SALUD;
    
    public EjeSaludServicio() {
        conexion = new ConexionServicio<>();
    }

    public EjeSalud guardarEjeSalud(EjeSalud ejeSalud) {

        try {
            EjeSalud ejeSaludCAIAux = null;
            Response response = conexion.conexion(URL_EJE_SALUD, "PUT", true, ejeSalud);
            if (response != null) {
                if (response.getStatus() == 200) {
                    ejeSaludCAIAux = response.readEntity(EjeSalud.class);
                }
            }
            return ejeSaludCAIAux;

        } catch (Exception e) {
            return null;
        }

    }

    public EjeSalud obtenerEjeSalud(Integer id) {

        try {
            EjeSalud ejeSaludCAIAux = null;
            Response response = conexion.conexion(URL_EJE_SALUD + "/" + id.toString(), "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    ejeSaludCAIAux = response.readEntity(EjeSalud.class);
                }
            }
            return ejeSaludCAIAux;
        } catch (Exception e) {
            return null;
        }       

    }
}
