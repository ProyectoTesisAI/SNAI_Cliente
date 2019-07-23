package epn.edu.ec.servicios;

import epn.edu.ec.modelo.DatosProvinciaCanton;
import epn.edu.ec.utilidades.Constantes;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class DatosProvinciaCantonServicio {

    private final ConexionServicio<DatosProvinciaCanton> conexion;
    private static final String URL_DATOS_PROVINCIA_CANTON = Constantes.URL_DATOS_PROVINCIA_CANTON;
    
    public DatosProvinciaCantonServicio() {
        conexion = new ConexionServicio<>();
    }

    public String guardarDatosProvinciaCanton(DatosProvinciaCanton datosProviciaCanton) {

        DatosProvinciaCanton datosProviciaCantonAux = null;
        String url = null;
        Response response = conexion.conexion(URL_DATOS_PROVINCIA_CANTON, "POST", true, datosProviciaCanton);
        datosProviciaCantonAux = response.readEntity(DatosProvinciaCanton.class);

        if (datosProviciaCantonAux != null) {
            url = "/menu.com?faces-redirect=true";
        }
        return url;

    }

    
    public String actualizarDatosProvinciaCanton(DatosProvinciaCanton datosProviciaCanton) {

        String url = null;
        Integer id = datosProviciaCanton.getIdDatosProvinciaCanton();
        DatosProvinciaCanton datosProviciaCantonAux = null;
        Response response = conexion.conexion(URL_DATOS_PROVINCIA_CANTON, "PUT", true, datosProviciaCanton);
        datosProviciaCantonAux = response.readEntity(DatosProvinciaCanton.class);

        if (datosProviciaCantonAux != null) {
            url = "/menu.com?faces-redirect=true";
        }
        return url;

    }

    public List<DatosProvinciaCanton> listarDatosProvinciaCanton() {

        List<DatosProvinciaCanton> listaActividadesAux = null;
        Response response = conexion.conexion(URL_DATOS_PROVINCIA_CANTON, "GET", true, null);
        if (response.getStatus() == 200) {
            listaActividadesAux = response.readEntity(new GenericType<List<DatosProvinciaCanton>>() {
            });
        }
        return listaActividadesAux;
    }

    public String eliminarDatosProvinciaCanton(Integer id) {

        String url = null;
        Response response = conexion.conexion(URL_DATOS_PROVINCIA_CANTON, "DELETE", true, null);
        if (response != null) {
            url = "/menu.com?faces-redirect=true";
        }
        return url;
    }
}
