package epn.edu.ec.servicios;

import epn.edu.ec.modelo.AdolescenteInfractor;
import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.utilidades.Constantes;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class AdolescenteInfractorCAIServicio {

    private final ConexionServicio<AdolescenteInfractorCAI> conexion;
    private final ConexionServicio<AdolescenteInfractor> conexionAI;
    private static final String URL_ADOLESCENTE_INFRACTOR_CAI = Constantes.URL_ADOLESCENTE_CAI;
    private static final String URL_ADOLESCENTE_INFRACTOR = Constantes.URL_ADOLESCENTE;
    
    public AdolescenteInfractorCAIServicio() {
        conexion = new ConexionServicio<>();
        conexionAI = new ConexionServicio<>();
    }

    public AdolescenteInfractorCAI guardarAdolescenteInfractorCAI(AdolescenteInfractorCAI adolescenteInfractorCAI) {

        try {
            AdolescenteInfractorCAI adolescenteInfractorCAIAux = null;
            Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR_CAI, "POST", true, adolescenteInfractorCAI);
            if (response != null) {
                if (response.getStatus() == 200) {
                    adolescenteInfractorCAIAux = response.readEntity(AdolescenteInfractorCAI.class);
                }

            }
            return adolescenteInfractorCAIAux;
        } catch (Exception e) {
            return null;
        }
    }
    
    public AdolescenteInfractorCAI guardarEdicionAdolescenteInfractorCAI(AdolescenteInfractorCAI adolescenteInfractorCAI) {

        try {
            AdolescenteInfractorCAI adolescenteInfractorUDIAux = null;
            Response responseAI = conexionAI.conexion(URL_ADOLESCENTE_INFRACTOR, "PUT", true, adolescenteInfractorCAI.getIdAdolescenteInfractor());
            if (responseAI != null) {
                if (responseAI.getStatus() == 200) {
                    Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR_CAI, "PUT", true, adolescenteInfractorCAI);
                    if (response.getStatus() == 200) {
                        adolescenteInfractorUDIAux = response.readEntity(AdolescenteInfractorCAI.class);
                    }
                }

            }
            return adolescenteInfractorUDIAux;
        } catch (Exception e) {
            return null;
        }
        
    }

    public AdolescenteInfractorCAI obtenerAdolescenteInfractorCAI(Integer id) {

        try {
            AdolescenteInfractorCAI adolescenteInfractorCAIAux = null;
            Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR_CAI + "/" + id.toString(), "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    adolescenteInfractorCAIAux = response.readEntity(AdolescenteInfractorCAI.class);
                }
            }

            return adolescenteInfractorCAIAux;

        } catch (Exception e) {
            return null;
        }

    }

    public List<AdolescenteInfractorCAI> listaAdolescentesInfractoresCAI() {

        try {

            List<AdolescenteInfractorCAI> listaAdolescentesCAIAux = null;
            Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR_CAI, "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    listaAdolescentesCAIAux = response.readEntity(new GenericType<List<AdolescenteInfractorCAI>>() {
                    });
                }
            }
            return listaAdolescentesCAIAux;
        } catch (Exception e) {
            return null;
        }
        
    }
}
