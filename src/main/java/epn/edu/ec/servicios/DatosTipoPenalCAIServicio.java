package epn.edu.ec.servicios;

import epn.edu.ec.modelo.DatosTipoPenalCAI;
import epn.edu.ec.utilidades.Constantes;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class DatosTipoPenalCAIServicio {
    
    private final Client cliente;
    public static final String URL_DATOS_TIPO_PENAL_CAI=Constantes.URL_DATOS_TIPO_PENAL_CAI;
    ConexionServicio<DatosTipoPenalCAI> conexion;

    public DatosTipoPenalCAIServicio() {
        this.cliente = ClientBuilder.newClient();
        conexion = new ConexionServicio<>();
    }
    
    public List<DatosTipoPenalCAI> listarDatosTipoPenalCAI(){
        
        List<DatosTipoPenalCAI> listaActividadesAux=null;
/*        
        Client client= ClientBuilder.newClient();
        WebTarget webTarget=client.target(URL_DATOS_TIPO_PENAL_CAI);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+ ";charset=UTF-8");        
        listaActividadesAux=invocationBuilder.get(new GenericType<List<DatosTipoPenalCAI>>(){});
*/
        Response response = conexion.conexion(URL_DATOS_TIPO_PENAL_CAI, "GET", true, null);
        if (response.getStatus() == 200) {
            listaActividadesAux = response.readEntity(new GenericType<List<DatosTipoPenalCAI>>() {
            });
        }
        return listaActividadesAux;

    }
}
