package epn.edu.ec.servicios;

import epn.edu.ec.modelo.DatosProvinciaCanton;
import epn.edu.ec.utilidades.Constantes;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class DatosProvinciaCantonServicio {
    
    private Client cliente;
    public static final String URL_DATOS_PROVINCIA_CANTON =Constantes.URL_DATOS_PROVINCIA_CANTON;

    public DatosProvinciaCantonServicio() {
        this.cliente=ClientBuilder.newClient();
    }
    
    
    public String guardarDatosProvinciaCanton(DatosProvinciaCanton datosProviciaCanton) {

        DatosProvinciaCanton datosProviciaCantonAux = null;
        String url = null;
        
        WebTarget webTarget = cliente.target(URL_DATOS_PROVINCIA_CANTON);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8");
        Response response = invocationBuilder.post(Entity.entity(datosProviciaCanton, MediaType.APPLICATION_JSON));
        System.out.println("ITEM GUARDADO: " + response.getStatus() + "++++++++++++++");
        datosProviciaCantonAux = response.readEntity(DatosProvinciaCanton.class);

        if (datosProviciaCantonAux != null) {
            url = "/menu.com?faces-redirect=true";
        }
        return url;

    }

    public String editarDatosProvinciaCanton(Integer id) {

        String url = null;
        DatosProvinciaCanton datosProviciaCanton = null;

        WebTarget webTarget = cliente.target(URL_DATOS_PROVINCIA_CANTON).path(id.toString());
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8");
        datosProviciaCanton = invocationBuilder.get(DatosProvinciaCanton.class);

        if (datosProviciaCanton != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("datosProviciaCantonObj", datosProviciaCanton);
            url = "paginas/adolescente_infractor_editar.com?faces-redirect=true";
        }
        return url;
    }

    public String actualizarDatosProvinciaCanton(DatosProvinciaCanton datosProviciaCanton) {

        String url = null;
        Integer id = datosProviciaCanton.getIdDatosProvinciaCanton();
        DatosProvinciaCanton datosProviciaCantonAux = null;

        WebTarget webTarget = cliente.target(URL_DATOS_PROVINCIA_CANTON).path(id.toString());
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8");
        Response response = invocationBuilder.put(Entity.entity(datosProviciaCanton, MediaType.APPLICATION_JSON));
        System.out.println("ITEM ACTUALIZADO: " + response.getStatus() + "++++++++++++++");
        datosProviciaCantonAux = response.readEntity(DatosProvinciaCanton.class);

        if (datosProviciaCantonAux != null) {
            url = "/menu.com?faces-redirect=true";
        }
        return url;

    }

    public List<DatosProvinciaCanton> listarDatosProvinciaCanton() {
        
        List<DatosProvinciaCanton> listaActividadesAux = null;

        WebTarget webTarget = cliente.target(URL_DATOS_PROVINCIA_CANTON);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8");
        Response response=invocationBuilder.get();
        if(response.getStatus()==200){
            listaActividadesAux = response.readEntity(new GenericType<List<DatosProvinciaCanton>>(){});
        }        
        return listaActividadesAux;
    }
    
    public String eliminarDatosProvinciaCanton(Integer id) {

        String url = null;
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(URL_DATOS_PROVINCIA_CANTON).path(id.toString());
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8");
        Response response = invocationBuilder.delete();
        if (response != null) {
            url = "/menu.com?faces-redirect=true";
        }
        return url;
    }
}
