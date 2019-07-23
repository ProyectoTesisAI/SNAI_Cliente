package epn.edu.ec.servicios;

import epn.edu.ec.modelo.CumplimientoMedidaCAI;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class CumplimientoMedidaCAIServicio {

    private final Client cliente;
    public static final String URL_CUMPLIMIENTO_MEDIDA_CAI = Constantes.URL_CUMPLIMIENTO_MEDIDA_CAI;
    ConexionServicio<CumplimientoMedidaCAI> conexion;

    public CumplimientoMedidaCAIServicio() {
        cliente = ClientBuilder.newClient();
        conexion = new ConexionServicio<>();
    }

    public CumplimientoMedidaCAI guardarCumplimientoMedidaCAI(CumplimientoMedidaCAI cumplimientoMedidaCAI) {

        CumplimientoMedidaCAI cumplimientoMedidaCAIAux = null;
        /*
        WebTarget webTarget=cliente.target(URL_CUMPLIMIENTO_MEDIDA_CAI);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response = invocationBuilder.put(Entity.entity(cumplimientoMedidaCAI, MediaType.APPLICATION_JSON+";charset=UTF-8"));
         */
        Response response = conexion.conexion(URL_CUMPLIMIENTO_MEDIDA_CAI, "POST", true, cumplimientoMedidaCAI);
        if (response.getStatus() == 200) {
            cumplimientoMedidaCAIAux = response.readEntity(CumplimientoMedidaCAI.class);
        }

        return cumplimientoMedidaCAIAux;

    }

    public CumplimientoMedidaCAI obtenerCumplimientoMedidaCAI(Integer id) {

        CumplimientoMedidaCAI cumplimientoMedidaCAIAux = null;
        /*
        WebTarget webTarget = cliente.target(URL_CUMPLIMIENTO_MEDIDA_CAI).path(id.toString());
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8");
        Response response = invocationBuilder.get();
         */
        Response response = conexion.conexion(URL_CUMPLIMIENTO_MEDIDA_CAI + "/" + id.toString(), "GET", true, null);
        if (response.getStatus() == 200) {
            cumplimientoMedidaCAIAux = response.readEntity(CumplimientoMedidaCAI.class);
        }
        return cumplimientoMedidaCAIAux;

    }
}
