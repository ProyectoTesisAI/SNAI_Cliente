package epn.edu.ec.servicios;

import epn.edu.ec.modelo.DatosProvinciaCanton;
import epn.edu.ec.utilidades.URLServicios;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class DatosProvinciaCantonServicio {

    private final ConexionServicio<DatosProvinciaCanton> conexion;
    private static final String URL_DATOS_PROVINCIA_CANTON = URLServicios.URL_DATOS_PROVINCIA_CANTON;
    
    public DatosProvinciaCantonServicio() {
        conexion = new ConexionServicio<>();
    }

    public String guardarDatosProvinciaCanton(DatosProvinciaCanton datosProviciaCanton) {

        try {
            DatosProvinciaCanton datosProviciaCantonAux = null;
            String url = null;
            Response response = conexion.conexion(URL_DATOS_PROVINCIA_CANTON, "POST", true, datosProviciaCanton);
            if (response != null) {
                if (response.getStatus() == 200) {
                    datosProviciaCantonAux = response.readEntity(DatosProvinciaCanton.class);

                    if (datosProviciaCantonAux != null) {
                        url = "/menu.com?faces-redirect=true";
                    }
                }             
            }
            return url;
        } catch (Exception e) {
            return null;
        }
       
    }

    
    public String actualizarDatosProvinciaCanton(DatosProvinciaCanton datosProviciaCanton) {

        try {

            String url = null;
            Integer id = datosProviciaCanton.getIdDatosProvinciaCanton();
            DatosProvinciaCanton datosProviciaCantonAux = null;
            Response response = conexion.conexion(URL_DATOS_PROVINCIA_CANTON, "PUT", true, datosProviciaCanton);
            if (response != null) {

                if (response.getStatus() == 200) {
                    datosProviciaCantonAux = response.readEntity(DatosProvinciaCanton.class);
                    if (datosProviciaCantonAux != null) {
                        url = "/menu.com?faces-redirect=true";
                    }
                }
            }
            return url;
        } catch (Exception e) {
            return null;
        }
        

    }

    public List<DatosProvinciaCanton> listarDatosProvinciaCanton() {

        try {
            List<DatosProvinciaCanton> listaActividadesAux = null;
            Response response = conexion.conexion(URL_DATOS_PROVINCIA_CANTON, "GET", true, null);
            if (response != null) {

                if (response.getStatus() == 200) {
                    listaActividadesAux = response.readEntity(new GenericType<List<DatosProvinciaCanton>>() {
                    });
                }
            }
            return listaActividadesAux;
        } catch (Exception e) {
            return null;
        }
        
    }

    public String eliminarDatosProvinciaCanton(Integer id) {

        try {
            String url = null;
            Response response = conexion.conexion(URL_DATOS_PROVINCIA_CANTON, "DELETE", true, null);
            if (response != null) {
                if (response.getStatus() == 200 || response.getStatus() == 204) {
                    url = "/menu.com?faces-redirect=true";
                }
            }
            return url;
        } catch (Exception e) {
            return null;
        }       
    }
}
