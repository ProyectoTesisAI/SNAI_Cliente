package epn.edu.ec.servicios;

import epn.edu.ec.modelo.EjeEducativo;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.core.Response;

public class EjeEducativoServicio {

    private final ConexionServicio<EjeEducativo> conexion;
    private static final String URL_EJE_EDUCATIVO = Constantes.URL_EJE_EDUCATIVO;
    
    public EjeEducativoServicio() {
        conexion = new ConexionServicio<>();
    }

    public EjeEducativo guardarEjeEducativo(EjeEducativo ejeEducativo) {

        EjeEducativo ejeEducativoAux = null;
        Response response = conexion.conexion(URL_EJE_EDUCATIVO, "PUT", true, ejeEducativo);
        if (response.getStatus() == 200) {
            ejeEducativoAux = response.readEntity(EjeEducativo.class);
        }

        return ejeEducativoAux;

    }

    public EjeEducativo obtenerEjeEducativo(Integer id) {

        EjeEducativo ejeEducativoCAIAux = null;
        Response response = conexion.conexion(URL_EJE_EDUCATIVO + "/" + id.toString(), "GET", true, null);
        if (response.getStatus() == 200) {
            ejeEducativoCAIAux = response.readEntity(EjeEducativo.class);
        }
        return ejeEducativoCAIAux;

    }
}
