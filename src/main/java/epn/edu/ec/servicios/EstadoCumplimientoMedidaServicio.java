package epn.edu.ec.servicios;

import epn.edu.ec.modelo.EstadoCumplimientoMedida;
import epn.edu.ec.utilidades.URLServicios;
import javax.ws.rs.core.Response;

public class EstadoCumplimientoMedidaServicio {

    private final ConexionServicio<EstadoCumplimientoMedida> conexion;
    private static final String URL_ESTADO_CUMPLIMIENTO_MEDIDA = URLServicios.URL_ESTADO_CUMPLIMIENTO_MEDIDA;
    
    public EstadoCumplimientoMedidaServicio() {
        conexion = new ConexionServicio<>();
    }

    public EstadoCumplimientoMedida guardarEstadoCumplimientoMedida(EstadoCumplimientoMedida estadoCumplimientoMedida) {

        try {
            EstadoCumplimientoMedida estadoCumplimientoMedidaAux = null;
            Response response = conexion.conexion(URL_ESTADO_CUMPLIMIENTO_MEDIDA, "PUT", true, estadoCumplimientoMedida);
            if (response != null) {
                if (response.getStatus() == 200) {
                    estadoCumplimientoMedidaAux = response.readEntity(EstadoCumplimientoMedida.class);
                }

            }
            return estadoCumplimientoMedidaAux;
        } catch (Exception e) {
            return null;
        }
    }

    public EstadoCumplimientoMedida obtenerEstadoCumplimientoMedida(Integer id) {

        try {
            EstadoCumplimientoMedida estadoCumplimientoMedidaAux = null;
            Response response = conexion.conexion(URL_ESTADO_CUMPLIMIENTO_MEDIDA + "/" + id.toString(), "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    estadoCumplimientoMedidaAux = response.readEntity(EstadoCumplimientoMedida.class);
                }
            }

            return estadoCumplimientoMedidaAux;
        } catch (Exception e) {
            return null;
        }
       
    }
    
}
