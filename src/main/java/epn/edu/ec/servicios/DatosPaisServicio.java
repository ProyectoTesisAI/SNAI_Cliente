package epn.edu.ec.servicios;

import epn.edu.ec.modelo.DatosPais;
import epn.edu.ec.utilidades.Constantes;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class DatosPaisServicio {

    private final ConexionServicio<DatosPais> conexion;
    private static final String URL_DATOS_PAIS = Constantes.URL_DATOS_PAIS;
    
    public DatosPaisServicio() {
        conexion = new ConexionServicio<>();
    }

    public String guardarDatosPais(DatosPais datosPais) {

        try {
            DatosPais datosPaisAux = null;
            String url = null;
            Response response = conexion.conexion(URL_DATOS_PAIS, "POST", true, datosPais);
            if (response != null) {
                if (response.getStatus() == 200) {
                    datosPaisAux = response.readEntity(DatosPais.class);
                    if (datosPaisAux != null) {
                        url = "/menu.com?faces-redirect=true";
                    }
                }
            } 
            return url;
        } catch (Exception e) {
            return null;
        }
    }

    
    public String actualizarDatosPais(DatosPais datosPais) {

        try {
            String url = null;
            Integer id = datosPais.getIdPais();
            DatosPais datosProviciaCantonAux = null;
            Response response = conexion.conexion(URL_DATOS_PAIS, "PUT", true, datosPais);
            if (response != null) {
                if (response.getStatus() == 200) {
                    datosProviciaCantonAux = response.readEntity(DatosPais.class);

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

    public List<DatosPais> listarDatosPais() {

        try {
            List<DatosPais> listaDatosAux = null;
            Response response = conexion.conexion(URL_DATOS_PAIS, "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    listaDatosAux = response.readEntity(new GenericType<List<DatosPais>>() {
                    });
                }
            }           
            return listaDatosAux;
        } catch (Exception e) {
            return null;
        }  
    }

    public String eliminarDatosPais(Integer id) {

        try {
            String url = null;
            Response response = conexion.conexion(URL_DATOS_PAIS, "DELETE", true, null);
            if (response != null) {
                if (response.getStatus() == 200 || response.getStatus()==204) {
                    url = "/menu.com?faces-redirect=true";
                }     
            }
            return url;
        } catch (Exception e) {
            return null;
        }
      
    }
}
