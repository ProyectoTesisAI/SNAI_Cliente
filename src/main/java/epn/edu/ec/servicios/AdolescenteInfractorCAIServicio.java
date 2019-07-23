package epn.edu.ec.servicios;

import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.utilidades.Constantes;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class AdolescenteInfractorCAIServicio {

    private final ConexionServicio<AdolescenteInfractorCAI> conexion;
    private static final String URL_ADOLESCENTE_INFRACTOR_CAI = Constantes.URL_ADOLESCENTE_CAI;
    
    public AdolescenteInfractorCAIServicio() {
        conexion = new ConexionServicio<>();
    }

    public AdolescenteInfractorCAI guardarAdolescenteInfractorCAI(AdolescenteInfractorCAI adolescenteInfractorCAI) {

        AdolescenteInfractorCAI adolescenteInfractorCAIAux = null;
        Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR_CAI, "POST", true, adolescenteInfractorCAI);
        if (response.getStatus() == 200) {
            adolescenteInfractorCAIAux = response.readEntity(AdolescenteInfractorCAI.class);
        }

        return adolescenteInfractorCAIAux;

    }

    public AdolescenteInfractorCAI obtenerAdolescenteInfractorCAI(Integer id) {

        AdolescenteInfractorCAI adolescenteInfractorCAIAux = null;
        Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR_CAI + "/" + id.toString(), "GET", true, null);
        if (response.getStatus() == 200) {
            adolescenteInfractorCAIAux = response.readEntity(AdolescenteInfractorCAI.class);
        }
        return adolescenteInfractorCAIAux;

    }

    public List<AdolescenteInfractorCAI> listaAdolescentesInfractoresCAI() {

        List<AdolescenteInfractorCAI> listaAdolescentesCAIAux = null;
        Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR_CAI, "GET", true, null);
        if (response.getStatus() == 200) {
            listaAdolescentesCAIAux = response.readEntity(new GenericType<List<AdolescenteInfractorCAI>>() {
            });
        }
        return listaAdolescentesCAIAux;
    }
}
