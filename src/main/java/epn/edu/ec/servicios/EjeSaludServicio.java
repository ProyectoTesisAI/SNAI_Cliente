package epn.edu.ec.servicios;

import epn.edu.ec.modelo.EjeSalud;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class EjeSaludServicio {

    private final Client cliente;
    public static final String URL_EJE_SALUD = Constantes.URL_EJE_SALUD;
    ConexionServicio<EjeSalud> conexion;

    public EjeSaludServicio() {
        cliente = ClientBuilder.newClient();
        conexion = new ConexionServicio<>();
    }

    public EjeSalud guardarEjeSalud(EjeSalud ejeSalud) {

        EjeSalud ejeSaludCAIAux = null;
        /*                       
        WebTarget webTarget=cliente.target(URL_EJE_SALUD);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response = invocationBuilder.put(Entity.entity(ejeSalud, MediaType.APPLICATION_JSON+";charset=UTF-8"));
         */
        Response response = conexion.conexion(URL_EJE_SALUD, "PUT", true, ejeSalud);
        if (response.getStatus() == 200) {
            ejeSaludCAIAux = response.readEntity(EjeSalud.class);
        }

        return ejeSaludCAIAux;

    }

    public EjeSalud obtenerEjeSalud(Integer id) {

        EjeSalud ejeSaludCAIAux = null;
        /*
        WebTarget webTarget = cliente.target(URL_EJE_SALUD).path(id.toString());
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8");
        Response response = invocationBuilder.get();
         */
        Response response = conexion.conexion(URL_EJE_SALUD + "/" + id.toString(), "GET", true, null);
        if (response.getStatus() == 200) {
            ejeSaludCAIAux = response.readEntity(EjeSalud.class);
        }
        return ejeSaludCAIAux;

    }
}
