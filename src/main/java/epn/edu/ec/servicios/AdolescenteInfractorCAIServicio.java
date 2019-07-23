package epn.edu.ec.servicios;

import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.utilidades.Constantes;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class AdolescenteInfractorCAIServicio {

    private final Client cliente;
//    private final String token;
    public String URL_ADOLESCENTE_INFRACTOR_CAI = Constantes.URL_ADOLESCENTE_CAI;
    ConexionServicio<AdolescenteInfractorCAI> conexion;

    public AdolescenteInfractorCAIServicio() {
        cliente = ClientBuilder.newClient();
//        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
//        token = usuarioAux.getToken();
        conexion = new ConexionServicio<>();
    }

    public AdolescenteInfractorCAI guardarAdolescenteInfractorCAI(AdolescenteInfractorCAI adolescenteInfractorCAI) {

        AdolescenteInfractorCAI adolescenteInfractorCAIAux = null;
        /*        
        WebTarget webTarget=cliente.target(URL_ADOLESCENTE_INFRACTOR_CAI);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8").header(HttpHeaders.AUTHORIZATION, "Bearer "+token);        
        Response response = invocationBuilder.post(Entity.entity(adolescenteInfractorCAI, MediaType.APPLICATION_JSON+";charset=UTF-8"));
         */
        Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR_CAI, "POST", true, adolescenteInfractorCAI);
        if (response.getStatus() == 200) {
            adolescenteInfractorCAIAux = response.readEntity(AdolescenteInfractorCAI.class);
        }

        return adolescenteInfractorCAIAux;

    }

    public AdolescenteInfractorCAI obtenerAdolescenteInfractorCAI(Integer id) {

        AdolescenteInfractorCAI adolescenteInfractorCAIAux = null;
        /*
        WebTarget webTarget = cliente.target(URL_ADOLESCENTE_INFRACTOR_CAI).path(id.toString());
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8");
        Response response = invocationBuilder.get();
         */
        Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR_CAI + "/" + id.toString(), "GET", true, null);
        if (response.getStatus() == 200) {
            adolescenteInfractorCAIAux = response.readEntity(AdolescenteInfractorCAI.class);
        }
        return adolescenteInfractorCAIAux;

    }

    public List<AdolescenteInfractorCAI> listaAdolescentesInfractoresCAI() {

        List<AdolescenteInfractorCAI> listaAdolescentesCAIAux = null;
        /*
        WebTarget webTarget = cliente.target(URL_ADOLESCENTE_INFRACTOR_CAI);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8");
        Response response = invocationBuilder.get();
         */
        Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR_CAI, "GET", true, null);
        if (response.getStatus() == 200) {
            listaAdolescentesCAIAux = response.readEntity(new GenericType<List<AdolescenteInfractorCAI>>() {
            });
        }
        return listaAdolescentesCAIAux;
    }
}
