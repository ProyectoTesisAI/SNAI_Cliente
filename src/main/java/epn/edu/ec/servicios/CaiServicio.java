package epn.edu.ec.servicios;

import epn.edu.ec.modelo.CAI;
import epn.edu.ec.utilidades.Constantes;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class CaiServicio {

    private final Client cliente;
    public String URL_CAI = Constantes.URL_CAI;
    ConexionServicio<CAI> conexion;

    public CaiServicio() {
        cliente = ClientBuilder.newClient();
        conexion = new ConexionServicio<>();
    }

    public List<CAI> listaCai() {

        List<CAI> listaCAIAux = null;
        /*        
        WebTarget webTarget=cliente.target(URL_CAI);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response=invocationBuilder.get();
         */
        Response response = conexion.conexion(URL_CAI, "GET", true, null);
        if (response.getStatus() == 200) {
            listaCAIAux = response.readEntity(new GenericType<List<CAI>>() {
            });
        }

        return listaCAIAux;

    }

}
