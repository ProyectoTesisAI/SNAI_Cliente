package epn.edu.ec.servicios;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.modelo.MedidaSocioeducativa;
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

public class MedidaSocioeducativaServicio {
        
    private final Client cliente;
    public static final String URL_MEDIDA_SOCIOEDUCATIVA=Constantes.URL_MEDIDA_SOCIOEDUCATIVA;  
    
    public MedidaSocioeducativaServicio(){
        cliente= ClientBuilder.newClient();
    }   

    public MedidaSocioeducativa guardarMedidaSocioeducativa(MedidaSocioeducativa medidaSocioeducativa){
        
        MedidaSocioeducativa medidaSocioeducativaAux=null;
                       
        WebTarget webTarget=cliente.target(URL_MEDIDA_SOCIOEDUCATIVA);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response = invocationBuilder.put(Entity.entity(medidaSocioeducativa, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        
        if(response.getStatus()==200){        
            medidaSocioeducativaAux=response.readEntity(MedidaSocioeducativa.class);       
        } 
       
        return medidaSocioeducativaAux;
        
    }

    public List<MedidaSocioeducativa> listaMedidaSocioeducativosPorAdolescentesUzdi(AdolescenteInfractorUDI adolescenteInfractorUDI){
        
        List<MedidaSocioeducativa> listaMedidasSocioeducativas=null;
        
        WebTarget webTarget=cliente.target(URL_MEDIDA_SOCIOEDUCATIVA+"/MedidasSocioeducativasPorAdolescenteUDI");        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");     
        Response response =invocationBuilder.post(Entity.entity(adolescenteInfractorUDI, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        if(response.getStatus()==200){
            listaMedidasSocioeducativas=response.readEntity(new GenericType<List<MedidaSocioeducativa>>(){});
        }
        
        return listaMedidasSocioeducativas;

    }
    
    public MedidaSocioeducativa obtenerMedidaMasAlta(AdolescenteInfractorUDI adolescenteInfractorUDI){
        
        MedidaSocioeducativa medidaMasAlta=null;
        
        WebTarget webTarget=cliente.target(URL_MEDIDA_SOCIOEDUCATIVA+"/ObtenerMedidaMasAlta");        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");     
        Response response =invocationBuilder.post(Entity.entity(adolescenteInfractorUDI, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        if(response.getStatus()==200){
            List<MedidaSocioeducativa> medidasSocioeducativas=response.readEntity(new GenericType<List<MedidaSocioeducativa>>(){});
            if(medidasSocioeducativas!=null){
                medidaMasAlta=medidasSocioeducativas.get(0);
            }
        }
        
        return medidaMasAlta;

    }
}
