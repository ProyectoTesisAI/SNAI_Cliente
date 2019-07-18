/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.servicios;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.modelo.RegistroAsistencia;
import epn.edu.ec.modelo.CAI;
import epn.edu.ec.modelo.ItemTaller;
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

/**
 *
 * @author User
 */
public class TallerServicio {
    
    private final Client cliente;
    public String URL_TALLER_PSICOLOGIA=Constantes.URL_TALLER;
    
    public TallerServicio(){
        cliente= ClientBuilder.newClient();
    }
    
    public Taller guardarTaller(Taller taller){
        
        Taller tallerAux=null;
        
        WebTarget webTarget=cliente.target(URL_TALLER_PSICOLOGIA);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");     
        Response response =invocationBuilder.post(Entity.entity(taller, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        if(response.getStatus()==200){
            tallerAux =response.readEntity(Taller.class);
        }
        
        return tallerAux;

    }
    
    public Taller editarTaller(Taller taller){
        
        Taller tallerAux=null;
        
        WebTarget webTarget=cliente.target(URL_TALLER_PSICOLOGIA);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");     
        Response response =invocationBuilder.put(Entity.entity(taller, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        if(response.getStatus()==200){
            tallerAux =response.readEntity(Taller.class);
        }
        
        return tallerAux;

    }
        
    public List<Taller> listaTalleres(){
        
        List<Taller> listaActividadesAux=null;
        
        WebTarget webTarget=cliente.target(URL_TALLER_PSICOLOGIA+"/TalleresSinInforme");        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            listaActividadesAux= response.readEntity(new GenericType<List<Taller>>(){});
        }           
        return listaActividadesAux;
    }
    
    public List<Taller> listaTalleresConInforme(){
        
        List<Taller> listaActividadesAux=null;
        
        WebTarget webTarget=cliente.target(URL_TALLER_PSICOLOGIA+"/TalleresConInforme");        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            listaActividadesAux= response.readEntity(new GenericType<List<Taller>>(){});
        }           
        return listaActividadesAux;
    }
    
    public Integer obtenerNumeroAdolescentePorUdi(UDI udi ){
        
        Integer cantidadAdolescentesAux=null;
        
        WebTarget webTarget=cliente.target(Constantes.URL_TALLER+"/NumeroAdolescentesPorUzdi");        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.post(Entity.entity(udi, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        if(response.getStatus()==200){
           
             String cantidadAdolescente= response.readEntity(String.class);
                    
            if(cantidadAdolescente!=null){
                
                cantidadAdolescentesAux= Integer.parseInt(cantidadAdolescente);
            }
        }
        else if(response.getStatus()==204){
            cantidadAdolescentesAux=0;
        }
        
        return cantidadAdolescentesAux;
    }
    
    public Integer obtenerNumeroAdolescentePorCai(CAI cai ){
        
        Integer cantidadAdolescentesAux=null;
        
        WebTarget webTarget=cliente.target(Constantes.URL_TALLER+"/NumeroAdolescentesPorCai");        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.post(Entity.entity(cai, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        if(response.getStatus()==200){
           
            String cantidadAdolescente= response.readEntity(String.class);
                    
            if(cantidadAdolescente!=null){
                
                cantidadAdolescentesAux= Integer.parseInt(cantidadAdolescente);
            }
        }
        else if(response.getStatus()==204){
            cantidadAdolescentesAux=0;
        }
        
        return cantidadAdolescentesAux;
    }
    
    public List<AdolescenteInfractorUDI> listaAdolescentesPorUzdi(UDI udi){
        
        List<AdolescenteInfractorUDI> asistenciaAux=null;
                
        WebTarget webTarget=cliente.target(Constantes.URL_TALLER+"/ListarAdolescentesPorUzdi");        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.post(Entity.entity(udi, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        if(response.getStatus()==200){
            
            List<AdolescenteInfractorUDI> listaAsistenciaUdi= response.readEntity(new GenericType<List<AdolescenteInfractorUDI>>(){});
            if(listaAsistenciaUdi != null && listaAsistenciaUdi.size() >0){               
                
                asistenciaAux=listaAsistenciaUdi;
            }
        }
        return asistenciaAux;
    }
    
    public List<ItemTaller> obtenerItemsPorTalleres(Integer idTaller){
        
        List<ItemTaller> listaItemsTaller=null;
        
        WebTarget webTarget=cliente.target(URL_TALLER_PSICOLOGIA+"/ItemsTaller").path(idTaller.toString());        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            listaItemsTaller= response.readEntity(new GenericType<List<ItemTaller>>(){});
        }           
        return listaItemsTaller;
    }
    
    public RegistroAsistencia obtenerRegistroAsistenciaPorTaller(Integer idTaller){
        
        RegistroAsistencia registroAsistenciaAux =null;
        
        WebTarget webTarget=cliente.target(URL_TALLER_PSICOLOGIA+"/RegistroAsistencia").path(idTaller.toString());        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            registroAsistenciaAux= response.readEntity(RegistroAsistencia.class);
        }           
        return registroAsistenciaAux;
    }
}
