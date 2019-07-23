package epn.edu.ec.servicios;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.utilidades.Constantes;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class AdolescenteInfractorUDIServicio {

    private final ConexionServicio<AdolescenteInfractorUDI> conexion;
    private static final String URL_ADOLESCENTE_INFRACTOR = Constantes.URL_ADOLESCENTE_UDI;
    
    public AdolescenteInfractorUDIServicio() {
        conexion = new ConexionServicio<>();
    }

    public AdolescenteInfractorUDI guardarAdolescenteInfractorUDI(AdolescenteInfractorUDI adolescenteInfractorUDI) {

        AdolescenteInfractorUDI adolescenteInfractorUDIAux = null;
        Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR, "POST", true, adolescenteInfractorUDI);
        if (response.getStatus() == 200) {
            adolescenteInfractorUDIAux = response.readEntity(AdolescenteInfractorUDI.class);
        }

        return adolescenteInfractorUDIAux;

    }

    public AdolescenteInfractorUDI obtenerAdolescenteInfractorUDI(Integer id) {

        AdolescenteInfractorUDI adolescenteInfractorUDIAux = null;
        Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR + "/" + id.toString(), "GET", true, null);
        if (response.getStatus() == 200) {
            adolescenteInfractorUDIAux = response.readEntity(AdolescenteInfractorUDI.class);
        }
        return adolescenteInfractorUDIAux;

    }

    public List<AdolescenteInfractorUDI> listaAdolescentesInfractoresUDI() {

        List<AdolescenteInfractorUDI> listaAdolescentesUDIAux = null;
        Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR, "GET", true, null);
        if (response.getStatus() == 200) {
            listaAdolescentesUDIAux = response.readEntity(new GenericType<List<AdolescenteInfractorUDI>>() {
            });
        }
        return listaAdolescentesUDIAux;
    }

    public int eliminarAdolescenteInfractor(Integer id) {

        int statusRespuesta = 0;
        Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR, "DELETE", true, null);
        if (response.getStatus() == 200) {
            statusRespuesta = 200;
        }
        return statusRespuesta;
    }
}
