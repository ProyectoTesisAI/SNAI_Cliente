package epn.edu.ec.servicios;

import epn.edu.ec.modelo.EjeEducativo;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class EjeEducativoServicio {

    private final Client cliente;
    public static final String URL_EJE_EDUCATIVO = Constantes.URL_EJE_EDUCATIVO;
    ConexionServicio<EjeEducativo> conexion;

    public EjeEducativoServicio() {
        cliente = ClientBuilder.newClient();
        conexion = new ConexionServicio<>();
    }

    public EjeEducativo guardarEjeEducativo(EjeEducativo ejeEducativo) {

        EjeEducativo ejeEducativoAux = null;
        /*                       
        WebTarget webTarget=cliente.target(URL_EJE_EDUCATIVO);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response = invocationBuilder.put(Entity.entity(ejeEducativoCAI, MediaType.APPLICATION_JSON+";charset=UTF-8"));
         */
        Response response = conexion.conexion(URL_EJE_EDUCATIVO, "PUT", true, ejeEducativo);
        if (response.getStatus() == 200) {
            ejeEducativoAux = response.readEntity(EjeEducativo.class);
        }

        return ejeEducativoAux;

    }

    public EjeEducativo obtenerEjeEducativo(Integer id) {

        EjeEducativo ejeEducativoCAIAux = null;
        /*
        WebTarget webTarget = cliente.target(URL_EJE_EDUCATIVO).path(id.toString());
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8");
        Response response = invocationBuilder.get();
         */
        Response response = conexion.conexion(URL_EJE_EDUCATIVO + "/" + id.toString(), "GET", true, null);
        if (response.getStatus() == 200) {
            ejeEducativoCAIAux = response.readEntity(EjeEducativo.class);
        }
        return ejeEducativoCAIAux;

    }
}
