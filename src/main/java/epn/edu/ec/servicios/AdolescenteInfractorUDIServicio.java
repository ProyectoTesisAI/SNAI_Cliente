package epn.edu.ec.servicios;

import epn.edu.ec.modelo.AdolescenteInfractor;
import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.utilidades.URLServicios;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class AdolescenteInfractorUDIServicio {

    private final ConexionServicio<AdolescenteInfractorUDI> conexion;
    private final ConexionServicio<AdolescenteInfractor> conexionAI;
    private static final String URL_ADOLESCENTE_INFRACTOR_UDI = URLServicios.URL_ADOLESCENTE_UDI;
    private static final String URL_ADOLESCENTE_INFRACTOR = URLServicios.URL_ADOLESCENTE;

    public AdolescenteInfractorUDIServicio() {
        conexion = new ConexionServicio<>();
        conexionAI = new ConexionServicio<>();
    }

    public AdolescenteInfractorUDI guardarAdolescenteInfractorUDI(AdolescenteInfractorUDI adolescenteInfractorUDI) {

        try {
            AdolescenteInfractorUDI adolescenteInfractorUDIAux = null;
            Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR_UDI, "POST", true, adolescenteInfractorUDI);
            if (response != null) {
                if (response.getStatus() == 200) {
                    adolescenteInfractorUDIAux = response.readEntity(AdolescenteInfractorUDI.class);
                }
            }
            return adolescenteInfractorUDIAux;
        } catch (Exception e) {
            return null;
        }

    }

    public AdolescenteInfractorUDI guardarEdicionAdolescenteInfractorUDI(AdolescenteInfractorUDI adolescenteInfractorUDI) {

        try {
            AdolescenteInfractorUDI adolescenteInfractorUDIAux = null;
            Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR_UDI, "PUT", true, adolescenteInfractorUDI);
            if (response != null) {
                if (response.getStatus() == 200) {
                    adolescenteInfractorUDIAux = response.readEntity(AdolescenteInfractorUDI.class);
                }
            }
            return adolescenteInfractorUDIAux;
        } catch (Exception e) {
            return null;
        }
    }

    public AdolescenteInfractorUDI obtenerAdolescenteInfractorUDI(Integer id) {

        try {
            AdolescenteInfractorUDI adolescenteInfractorUDIAux = null;
            Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR_UDI + "/" + id.toString(), "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    adolescenteInfractorUDIAux = response.readEntity(AdolescenteInfractorUDI.class);
                }
            }
            return adolescenteInfractorUDIAux;
        } catch (Exception e) {
            return null;
        }
    }

    public List<AdolescenteInfractorUDI> listaAdolescentesInfractoresUDI() {

        try {

            List<AdolescenteInfractorUDI> listaAdolescentesUDIAux = null;
            Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR_UDI, "GET", true, null);
            if (response.getStatus() == 200) {
                listaAdolescentesUDIAux = response.readEntity(new GenericType<List<AdolescenteInfractorUDI>>() {
                });
            }
            return listaAdolescentesUDIAux;
        } catch (Exception e) {
            return null;
        }   
    }

}
