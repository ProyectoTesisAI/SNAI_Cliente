package epn.edu.ec.servicios;

import epn.edu.ec.modelo.EstadoCumplimientoMedida;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class EstadoCumplimientoMedidaServicio {

    private final Client cliente;
    public static final String URL_ESTADO_CUMPLIMIENTO_MEDIDA = Constantes.URL_ESTADO_CUMPLIMIENTO_MEDIDA;
    ConexionServicio<EstadoCumplimientoMedida> conexion;

    public EstadoCumplimientoMedidaServicio() {
        cliente = ClientBuilder.newClient();
        conexion = new ConexionServicio<>();
    }

    public EstadoCumplimientoMedida guardarEstadoCumplimientoMedida(EstadoCumplimientoMedida estadoCumplimientoMedida) {

        EstadoCumplimientoMedida estadoCumplimientoMedidaAux = null;
        /*                       
        WebTarget webTarget=cliente.target(URL_ESTADO_CUMPLIMIENTO_MEDIDA);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response = invocationBuilder.put(Entity.entity(estadoCumplimientoMedida, MediaType.APPLICATION_JSON+";charset=UTF-8"));
         */
        Response response = conexion.conexion(URL_ESTADO_CUMPLIMIENTO_MEDIDA, "PUT", true, estadoCumplimientoMedida);
        if (response.getStatus() == 200) {
            estadoCumplimientoMedidaAux = response.readEntity(EstadoCumplimientoMedida.class);
        }

        return estadoCumplimientoMedidaAux;

    }

    public EstadoCumplimientoMedida obtenerEstadoCumplimientoMedida(Integer id) {

        EstadoCumplimientoMedida estadoCumplimientoMedidaAux = null;
        /*
        WebTarget webTarget = cliente.target(URL_ESTADO_CUMPLIMIENTO_MEDIDA).path(id.toString());
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8");
        Response response = invocationBuilder.get();
         */
        Response response = conexion.conexion(URL_ESTADO_CUMPLIMIENTO_MEDIDA + "/" + id.toString(), "GET", true, null);
        if (response.getStatus() == 200) {
            estadoCumplimientoMedidaAux = response.readEntity(EstadoCumplimientoMedida.class);
        }
        return estadoCumplimientoMedidaAux;

    }
}
