package epn.edu.ec.servicios;

import epn.edu.ec.modelo.AdolescenteInfractor;
import epn.edu.ec.modelo.RegistroAsistencia;
import epn.edu.ec.modelo.AsistenciaAdolescente;
import epn.edu.ec.modelo.CAI;
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
    
    private final ConexionServicio<RegistroAsistencia> conexion;    
    
    private final Client cliente;
    private static final String URL_REGISTRO_ASISTENCIA=Constantes.URL_REGISTRO_ASISTENCIA; 
    
    public RegistroAsistenciaServicio(){
        conexion= new ConexionServicio<>();
        cliente= ClientBuilder.newClient();
    }   
    
    
    public List<AdolescenteInfractor> listaAdolescentesInfractoresPorUzdi(UDI udi){
        
        List<AdolescenteInfractor> registroAsistenciaUdi=null;
        
        WebTarget webTarget=cliente.target(URL_REGISTRO_ASISTENCIA+"/ListaAdolescentesPorUzdi");        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");     
        Response response =invocationBuilder.post(Entity.entity(udi, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        if(response.getStatus()==200){
            registroAsistenciaUdi=response.readEntity(new GenericType<List<AdolescenteInfractor>>(){});
        }
        
        return registroAsistenciaUdi;

    }
    
    public List<AdolescenteInfractor> listaAdolescentesInfractoresPorCai(CAI cai){
        
        List<AdolescenteInfractor> registroAsistenciaCai=null;
        
        WebTarget webTarget=cliente.target(URL_REGISTRO_ASISTENCIA+"/ListaAdolescentesPorCai");        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");     
        Response response =invocationBuilder.post(Entity.entity(cai, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        if(response.getStatus()==200){
            registroAsistenciaCai=response.readEntity(new GenericType<List<AdolescenteInfractor>>(){});
        }
        
        return registroAsistenciaCai;

    }
    
    public RegistroAsistencia guardarRegistroAsistencia(RegistroAsistencia registroAsistencia){
        
        RegistroAsistencia registroAsistenciaAux=null;
        Response response= conexion.conexion(URL_REGISTRO_ASISTENCIA, "PUT", true, registroAsistencia);
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
        
        Response response= conexion.conexion(URL_REGISTRO_ASISTENCIA+"/Taller/NumeroAsistentes/"+idTaller.toString(), "GET", true, null);
        if(response.getStatus()==200){
            int numeroAsistentes=Integer.parseInt(response.readEntity(String.class)); 
            return numeroAsistentes;
        }
        else{
            return 0;
        }
    }
    
    public Integer eliminarRegistroAsistencia(Integer registroAsistenciaTaller){
           
        Response response= conexion.conexion(URL_REGISTRO_ASISTENCIA+"/"+registroAsistenciaTaller.toString(), "DELETE", true, null);
        Integer resultado=response.getStatus();
        return resultado;
    }
}
