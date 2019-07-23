package epn.edu.ec.servicios;

import epn.edu.ec.modelo.DetalleInfraccionCAI;
import epn.edu.ec.modelo.EjecucionMedidaCAI;
import epn.edu.ec.utilidades.Constantes;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class EjecucionMedidaServicio {

    private final Client cliente;
    public static final String URL_EJECUCION_MEDIDA = Constantes.URL_EJECUCION_MEDIDA_CAI;
    ConexionServicio<DetalleInfraccionCAI> conexionDI;
    ConexionServicio<EjecucionMedidaCAI> conexionEM;

    public EjecucionMedidaServicio() {
        cliente = ClientBuilder.newClient();
        conexionDI = new ConexionServicio<>();
        conexionEM = new ConexionServicio<>();
    }

    public EjecucionMedidaCAI guardarEjecucionMedidaCAI(EjecucionMedidaCAI ejecucionMedida) {

        EjecucionMedidaCAI ejecucionMedidaAux = null;
        /*                       
        WebTarget webTarget=cliente.target(URL_EJECUCION_MEDIDA);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response = invocationBuilder.put(Entity.entity(ejecucionMedida, MediaType.APPLICATION_JSON+";charset=UTF-8"));
         */
        Response response = conexionEM.conexion(URL_EJECUCION_MEDIDA, "PUT", true, ejecucionMedida);
        if (response.getStatus() == 200) {
            ejecucionMedidaAux = response.readEntity(EjecucionMedidaCAI.class);
        }

        return ejecucionMedidaAux;

    }

    public EjecucionMedidaCAI obtenerEjecucionMedidaCAI(Integer id) {

        EjecucionMedidaCAI ejecucionMedidaAux = null;
        /*
        WebTarget webTarget = cliente.target(URL_EJECUCION_MEDIDA).path(id.toString());
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8");
        Response response = invocationBuilder.get();
         */
        Response response = conexionEM.conexion(URL_EJECUCION_MEDIDA + "/" + id.toString(), "GET", true, null);
        if (response.getStatus() == 200) {
            ejecucionMedidaAux = response.readEntity(EjecucionMedidaCAI.class);
        }
        return ejecucionMedidaAux;

    }

    public List<EjecucionMedidaCAI> obtenerMedidasPorInfraccionCAI(DetalleInfraccionCAI infraccion) {

        List<EjecucionMedidaCAI> listaMedidasPorInfraccionAux = null;
        /*
        WebTarget webTarget = cliente.target(URL_EJECUCION_MEDIDA + "/ListaMedidasPorInfraccionCAI");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8");
        Response response = invocationBuilder.post(Entity.entity(infraccion, MediaType.APPLICATION_JSON + ";charset=UTF-8"));
         */
        Response response = conexionDI.conexion(URL_EJECUCION_MEDIDA + "/ListaMedidasPorInfraccionCAI", "POST", true, infraccion);
        if (response.getStatus() == 200) {
            listaMedidasPorInfraccionAux = response.readEntity(new GenericType<List<EjecucionMedidaCAI>>() {
            });
        } else if (response.getStatus() == 204) {
            listaMedidasPorInfraccionAux = new ArrayList<>();
        }
        return listaMedidasPorInfraccionAux;

    }
}
