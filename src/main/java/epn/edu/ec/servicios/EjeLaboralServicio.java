package epn.edu.ec.servicios;

import epn.edu.ec.modelo.EjeLaboral;
import epn.edu.ec.utilidades.URLServicios;
import javax.ws.rs.core.Response;

public class EjeLaboralServicio {

    private final ConexionServicio<EjeLaboral> conexion;
    private static final String URL_EJE_LABORAL = URLServicios.URL_EJE_LABORAL;
    
    public EjeLaboralServicio() {
        conexion = new ConexionServicio<>();
    }

    public EjeLaboral guardarEjeLaboral(EjeLaboral ejeLaboral) {

        try {
            EjeLaboral ejeLaboralAux = null;
            Response response = conexion.conexion(URL_EJE_LABORAL, "PUT", true, ejeLaboral);
            if (response != null) {
                if (response.getStatus() == 200) {
                    ejeLaboralAux = response.readEntity(EjeLaboral.class);
                }
            }

            return ejeLaboralAux;
        } catch (Exception e) {
            return null;
        }     

    }

    public EjeLaboral obtenerEjeLaboral(Integer id) {

        try {
            EjeLaboral ejeLaboralAux = null;
            Response response = conexion.conexion(URL_EJE_LABORAL + "/" + id.toString(), "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    ejeLaboralAux = response.readEntity(EjeLaboral.class);
                }
            }

            return ejeLaboralAux;
        } catch (Exception e) {
            return null;
        }       

    }
}
