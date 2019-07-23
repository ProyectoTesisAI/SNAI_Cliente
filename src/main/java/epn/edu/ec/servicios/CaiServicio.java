package epn.edu.ec.servicios;

import epn.edu.ec.modelo.CAI;
import epn.edu.ec.utilidades.Constantes;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class CaiServicio {

    private final ConexionServicio<CAI> conexion;
    private static final String URL_CAI = Constantes.URL_CAI;
    
    public CaiServicio() {
        conexion = new ConexionServicio<>();
    }

    public List<CAI> listaCai() {

        List<CAI> listaCAIAux = null;
        Response response = conexion.conexion(URL_CAI, "GET", true, null);
        if (response.getStatus() == 200) {
            listaCAIAux = response.readEntity(new GenericType<List<CAI>>() {
            });
        }

        return listaCAIAux;

    }

}
