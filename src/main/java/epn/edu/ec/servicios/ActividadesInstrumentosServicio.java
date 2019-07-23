package epn.edu.ec.servicios;

import epn.edu.ec.modelo.ActividadesInstrumentos;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class ActividadesInstrumentosServicio {

    private final Client cliente;
    public static final String URL_ACTIVIDADES_INSTRUMENTOS = Constantes.URL_ACTIVIDADES_INSTRUMENTOS;
    ConexionServicio<ActividadesInstrumentos> conexion;

    public ActividadesInstrumentosServicio() {
        cliente = ClientBuilder.newClient();
        conexion = new ConexionServicio<>();
    }

    public ActividadesInstrumentos guardarActividadesInstrumentos(ActividadesInstrumentos actividadesInstrumentos) {

        ActividadesInstrumentos actividadesInstrumentosAux = null;
        /*                       
        WebTarget webTarget=cliente.target(URL_ACTIVIDADES_INSTRUMENTOS);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response = invocationBuilder.put(Entity.entity(actividadesInstrumentos, MediaType.APPLICATION_JSON+";charset=UTF-8"));
         */
        Response response = conexion.conexion(URL_ACTIVIDADES_INSTRUMENTOS, "PUT", true, actividadesInstrumentos);
        if (response.getStatus() == 200) {
            actividadesInstrumentosAux = response.readEntity(ActividadesInstrumentos.class);
        }

        return actividadesInstrumentosAux;

    }

    public ActividadesInstrumentos obtenerActividadesInstrumentos(Integer id) {

        ActividadesInstrumentos actividadesInstrumentosAux = null;
        /*
        WebTarget webTarget = cliente.target(URL_ACTIVIDADES_INSTRUMENTOS).path(id.toString());
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8");
        Response response = invocationBuilder.get();
         */
        Response response = conexion.conexion(URL_ACTIVIDADES_INSTRUMENTOS+"/"+id.toString(), "GET", true, null);
        if (response.getStatus() == 200) {
            actividadesInstrumentosAux = response.readEntity(ActividadesInstrumentos.class);
        }
        return actividadesInstrumentosAux;

    }
}
