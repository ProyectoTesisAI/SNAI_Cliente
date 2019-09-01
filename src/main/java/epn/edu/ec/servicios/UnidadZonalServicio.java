package epn.edu.ec.servicios;

import epn.edu.ec.modelo.UnidadZonal;
import epn.edu.ec.utilidades.URLServicios;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class UnidadZonalServicio {
    
    private final ConexionServicio<UnidadZonal> conexion;
    private static final String URL_UNIDAD_ZONAL=URLServicios.URL_UNIDAD_ZONAL;  
    
    public UnidadZonalServicio(){
        conexion= new ConexionServicio<>();
    }   

    public UnidadZonal guardarUnidadZonal(UnidadZonal unidadZonal){
        
        try {
            UnidadZonal unidadZonalAux = null;
            Response response = conexion.conexion(URL_UNIDAD_ZONAL, "PUT", true, unidadZonal);
            if (response != null) {
                if (response.getStatus() == 200) {
                    unidadZonalAux = response.readEntity(UnidadZonal.class);
                }
            }
            return unidadZonalAux;
        } catch (Exception e) {
            return null;
        } 
    }

    public UnidadZonal obtenerUnidadZonal(Integer id){
        
        try {
            UnidadZonal unidadZonalAux = null;
            Response response = conexion.conexion(URL_UNIDAD_ZONAL + "/" + id.toString(), "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    unidadZonalAux = response.readEntity(UnidadZonal.class);
                }
            }

            return unidadZonalAux;
        } catch (Exception e) {
            return null;
        }
    }
        
    
    
    public List<UnidadZonal> listarUnidadZonal(){
        
        try {
            List<UnidadZonal> listaUnidadZonalAux = null;
            Response response = conexion.conexion(URL_UNIDAD_ZONAL, "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    listaUnidadZonalAux = response.readEntity(new GenericType<List<UnidadZonal>>() {
                    });
                }
            }

            return listaUnidadZonalAux;
        } catch (Exception e) {
            return null;
        }
    }    
}
