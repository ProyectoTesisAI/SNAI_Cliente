package epn.edu.ec.servicios;

import epn.edu.ec.modelo.Usuario;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ConexionServicio<T> {
    
    private Client cliente=null;
    private  String token=null;
    
    public ConexionServicio(){
        try{
            cliente= ClientBuilder.newClient();
            Usuario usuarioAux= (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
            if(usuarioAux!=null){
                token=usuarioAux.getToken();
            }
        }catch(Exception e){
        
        }
    }
    
    public Response conexion(String URL, String tipoPeticion, boolean necesitaToken,  T informacionAEnviar){
        
        try {
            WebTarget webTarget = cliente.target(URL);
            Invocation.Builder invocationBuilder=null;
            
            if(necesitaToken){
                invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8").header(HttpHeaders.AUTHORIZATION,"Bearer "+token );

            }
            else{
                invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8");

            }
            
            Response response = null;

            if (tipoPeticion.equals("GET")) {
                
                response = invocationBuilder.get();
                String headers= response.getHeaderString(HttpHeaders.AUTHORIZATION);
            } 
            else if (tipoPeticion.equals("POST")) {
                
                response = invocationBuilder.post(Entity.entity(informacionAEnviar, MediaType.APPLICATION_JSON + ";charset=UTF-8"));
            } 
            else if (tipoPeticion.equals("PUT")) {
                
                response = invocationBuilder.put(Entity.entity(informacionAEnviar, MediaType.APPLICATION_JSON + ";charset=UTF-8"));
            }
            else if (tipoPeticion.equals("DELETE")) {
                
                response = invocationBuilder.delete();
            }
            return response;
        }
        catch(Exception e){
            return null;
        }
        
    } 
}
