package epn.edu.ec.servicios;

import epn.edu.ec.modelo.ActividadesInstrumentos;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.core.Response;

public class ActividadesInstrumentosServicio {

    private final ConexionServicio<ActividadesInstrumentos> conexion;
    private static final String URL_ACTIVIDADES_INSTRUMENTOS = Constantes.URL_ACTIVIDADES_INSTRUMENTOS;
    
    public ActividadesInstrumentosServicio() {
        conexion = new ConexionServicio<>();
    }

    public ActividadesInstrumentos guardarActividadesInstrumentos(ActividadesInstrumentos actividadesInstrumentos) {

        try {
            
            ActividadesInstrumentos actividadesInstrumentosAux = null;
            Response response = conexion.conexion(URL_ACTIVIDADES_INSTRUMENTOS, "PUT", true, actividadesInstrumentos);
            if (response != null) {
                if (response.getStatus() == 200) {
                    actividadesInstrumentosAux = response.readEntity(ActividadesInstrumentos.class);
                }
            }
            return actividadesInstrumentosAux;
        }catch(Exception e){
            return null;
        }
    }

    public ActividadesInstrumentos obtenerActividadesInstrumentos(Integer id) {

        try {
            ActividadesInstrumentos actividadesInstrumentosAux = null;
            Response response = conexion.conexion(URL_ACTIVIDADES_INSTRUMENTOS + "/" + id.toString(), "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    actividadesInstrumentosAux = response.readEntity(ActividadesInstrumentos.class);
                }
            }
            return actividadesInstrumentosAux;

        } catch (Exception e) {
            return null;
        }
    }
}
