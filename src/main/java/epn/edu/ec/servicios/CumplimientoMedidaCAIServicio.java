package epn.edu.ec.servicios;

import epn.edu.ec.modelo.CumplimientoMedidaCAI;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.core.Response;

public class CumplimientoMedidaCAIServicio {

    private final ConexionServicio<CumplimientoMedidaCAI> conexion;
    private static final String URL_CUMPLIMIENTO_MEDIDA_CAI = Constantes.URL_CUMPLIMIENTO_MEDIDA_CAI;
    
    public CumplimientoMedidaCAIServicio() {
        conexion = new ConexionServicio<>();
    }

    public CumplimientoMedidaCAI guardarCumplimientoMedidaCAI(CumplimientoMedidaCAI cumplimientoMedidaCAI) {

        try {
            CumplimientoMedidaCAI cumplimientoMedidaCAIAux = null;
            Response response = conexion.conexion(URL_CUMPLIMIENTO_MEDIDA_CAI, "POST", true, cumplimientoMedidaCAI);
            if (response != null) {
                if (response.getStatus() == 200) {
                    cumplimientoMedidaCAIAux = response.readEntity(CumplimientoMedidaCAI.class);
                }
            }
            return cumplimientoMedidaCAIAux;

        } catch (Exception e) {
            return null;
        }
    }

    public CumplimientoMedidaCAI obtenerCumplimientoMedidaCAI(Integer id) {

        try {
            CumplimientoMedidaCAI cumplimientoMedidaCAIAux = null;
            Response response = conexion.conexion(URL_CUMPLIMIENTO_MEDIDA_CAI + "/" + id.toString(), "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    cumplimientoMedidaCAIAux = response.readEntity(CumplimientoMedidaCAI.class);
                }
            }
            return cumplimientoMedidaCAIAux;
        } catch (Exception e) {
            return null;
        }
    }
}
