package epn.edu.ec.servicios;

import epn.edu.ec.modelo.AdolescenteInfractor;
import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.utilidades.Constantes;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class AdolescenteInfractorUDIServicio {

    private final ConexionServicio<AdolescenteInfractorUDI> conexion;
    private final ConexionServicio<AdolescenteInfractor> conexionAI;
    private static final String URL_ADOLESCENTE_INFRACTOR_UDI = Constantes.URL_ADOLESCENTE_UDI;
    private static final String URL_ADOLESCENTE_INFRACTOR = Constantes.URL_ADOLESCENTE;

    public AdolescenteInfractorUDIServicio() {
        conexion = new ConexionServicio<>();
        conexionAI = new ConexionServicio<>();
    }

    public AdolescenteInfractorUDI guardarAdolescenteInfractorUDI(AdolescenteInfractorUDI adolescenteInfractorUDI) {

        AdolescenteInfractorUDI adolescenteInfractorUDIAux = null;
        Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR_UDI, "POST", true, adolescenteInfractorUDI);
        if (response.getStatus() == 200) {
            adolescenteInfractorUDIAux = response.readEntity(AdolescenteInfractorUDI.class);
        }

        return adolescenteInfractorUDIAux;

    }

    public AdolescenteInfractorUDI guardarEdicionAdolescenteInfractorUDI(AdolescenteInfractorUDI adolescenteInfractorUDI) {

        AdolescenteInfractorUDI adolescenteInfractorUDIAux = null;
        Response responseAI = conexionAI.conexion(URL_ADOLESCENTE_INFRACTOR, "PUT", true, adolescenteInfractorUDI.getIdAdolescenteInfractor());
        if (responseAI.getStatus() == 200) {
            Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR_UDI, "PUT", true, adolescenteInfractorUDI);
            if (response.getStatus() == 200) {
                adolescenteInfractorUDIAux = response.readEntity(AdolescenteInfractorUDI.class);
            }
        }

        return adolescenteInfractorUDIAux;

    }

    public AdolescenteInfractorUDI obtenerAdolescenteInfractorUDI(Integer id) {

        AdolescenteInfractorUDI adolescenteInfractorUDIAux = null;
        Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR_UDI + "/" + id.toString(), "GET", true, null);
        if (response.getStatus() == 200) {
            adolescenteInfractorUDIAux = response.readEntity(AdolescenteInfractorUDI.class);
        }
        return adolescenteInfractorUDIAux;

    }

    public List<AdolescenteInfractorUDI> listaAdolescentesInfractoresUDI() {

        List<AdolescenteInfractorUDI> listaAdolescentesUDIAux = null;
        Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR_UDI, "GET", true, null);
        if (response.getStatus() == 200) {
            listaAdolescentesUDIAux = response.readEntity(new GenericType<List<AdolescenteInfractorUDI>>() {
            });
        }
        return listaAdolescentesUDIAux;
    }

//    public int eliminarAdolescenteInfractor(Integer id) {
//
//        int statusRespuesta = 0;
//        Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR_UDI, "DELETE", true, null);
//        if (response.getStatus() == 200) {
//            statusRespuesta = 200;
//        }
//        return statusRespuesta;
//    }
}
