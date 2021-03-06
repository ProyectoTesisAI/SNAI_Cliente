package epn.edu.ec.servicios;

import epn.edu.ec.modelo.DatosTipoPenalCAI;
import epn.edu.ec.utilidades.URLServicios;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class DatosTipoPenalCAIServicio {
    
    private final ConexionServicio<DatosTipoPenalCAI> conexion;
    private static final String URL_DATOS_TIPO_PENAL_CAI=URLServicios.URL_DATOS_TIPO_PENAL_CAI;
    
    public DatosTipoPenalCAIServicio() {
        conexion = new ConexionServicio<>();
    }
    
    public List<DatosTipoPenalCAI> listarDatosTipoPenalCAI(){
        
        try {
            List<DatosTipoPenalCAI> listaActividadesAux = null;
            Response response = conexion.conexion(URL_DATOS_TIPO_PENAL_CAI, "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    listaActividadesAux = response.readEntity(new GenericType<List<DatosTipoPenalCAI>>() {
                    });
                }
            }

            return listaActividadesAux;
        } catch (Exception e) {
            return null;
        }
        
    }
}
