package epn.edu.ec.servicios;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.modelo.RegistroAsistencia;
import epn.edu.ec.modelo.AsistenciaAdolescente;
import epn.edu.ec.modelo.Taller;
import epn.edu.ec.modelo.UDI;
import epn.edu.ec.utilidades.Constantes;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RegistroAsistenciaServicio {
    
    private final Client cliente;
    public String URL_REGISTRO_ASISTENCIA=Constantes.URL_REGISTRO_ASISTENCIA; 
    
    public RegistroAsistenciaServicio(){
        cliente= ClientBuilder.newClient();
    }   
    
    public List<AdolescenteInfractorUDI> listaAdolescentesInfractoresPorUzdi(UDI udi){
        
        List<AdolescenteInfractorUDI> registroAsistenciaUdi=null;
        
        WebTarget webTarget=cliente.target(URL_REGISTRO_ASISTENCIA+"/ListaAdolescentesPorUzdi");        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");     
        Response response =invocationBuilder.post(Entity.entity(udi, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        if(response.getStatus()==200){
            registroAsistenciaUdi=response.readEntity(new GenericType<List<AdolescenteInfractorUDI>>(){});
        }
        
        return registroAsistenciaUdi;

    }
    
    public RegistroAsistencia guardarRegistroAsistencia(RegistroAsistencia registroAsistencia){
        
        RegistroAsistencia registroAsistenciaAux=null;
        
        WebTarget webTarget=cliente.target(URL_REGISTRO_ASISTENCIA);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");     
        Response response =invocationBuilder.put(Entity.entity(registroAsistencia, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        if(response.getStatus()==200){
            registroAsistenciaAux =response.readEntity(RegistroAsistencia.class);
        }        
        return registroAsistenciaAux;

    }
    
    public List<AsistenciaAdolescente> listaAdolescentesInfractoresPorTaller(Taller taller){
        
        List<AsistenciaAdolescente> registroAsistenciaUdi=null;
        
        WebTarget webTarget=cliente.target(URL_REGISTRO_ASISTENCIA+"/ListaAdolescentesPorTaller");        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");     
        Response response =invocationBuilder.post(Entity.entity(taller, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        if(response.getStatus()==200){
            registroAsistenciaUdi=response.readEntity(new GenericType<List<AsistenciaAdolescente>>(){});
        }        
        return registroAsistenciaUdi;

    }
    
    public int obtenerNumeroAdolescentesPorTaller(Integer idTaller){
          
        WebTarget webTarget=cliente.target(URL_REGISTRO_ASISTENCIA+"/Taller/NumeroAsistentes/"+idTaller.toString());        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");     
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            int numeroAsistentes=Integer.parseInt(response.readEntity(String.class)); 
            return numeroAsistentes;
        }
        else{
            return 0;
        }
        

    }

    public Integer eliminarListadoAsistencia(Integer registroAsistenciaTaller){
        Integer resultado=0;
        WebTarget webTarget=cliente.target(URL_REGISTRO_ASISTENCIA).path(registroAsistenciaTaller.toString());        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+ ";charset=UTF-8");        
        Response response=invocationBuilder.delete();
        resultado=response.getStatus();
        return resultado;
    }
}
