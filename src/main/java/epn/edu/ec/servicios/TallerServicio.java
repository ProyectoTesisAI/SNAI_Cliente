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
    
    private final ConexionServicio<Taller> conexion;
    private final Client cliente;
    private static final String URL_TALLER=Constantes.URL_TALLER;
    
    public TallerServicio(){
        conexion= new ConexionServicio<>();
        cliente= ClientBuilder.newClient();
    }
    
    public Taller guardarTaller(Taller taller){
        
        Taller tallerAux=null;
        Response response= conexion.conexion(URL_TALLER, "POST", true, taller);
        if(response.getStatus()==200){
            tallerAux =response.readEntity(Taller.class);
        }
        
        return tallerAux;

    }
    
    public Taller editarTaller(Taller taller){
        
        Taller tallerAux=null;
        Response response= conexion.conexion(URL_TALLER, "PUT", true, taller);
        if(response.getStatus()==200){
            tallerAux =response.readEntity(Taller.class);
        }
        
        return tallerAux;

    }
        
    public List<Taller> listaTalleresSinInforme(){
        
        List<Taller> listaActividadesAux=null;
        Response response= conexion.conexion(URL_TALLER+"/TalleresSinInforme", "GET", true, null);
        if(response.getStatus()==200){
            listaActividadesAux= response.readEntity(new GenericType<List<Taller>>(){});
        }           
        return listaActividadesAux;
    
    
    }
    
    public List<Taller> listaTalleresConInforme(){
        
        List<Taller> listaActividadesAux=null;
        Response response= conexion.conexion(URL_TALLER+"/TalleresConInforme", "GET", true, null);
        if(response.getStatus()==200){
            listaActividadesAux= response.readEntity(new GenericType<List<Taller>>(){});
        }           
        return listaActividadesAux;
    }
    
    public Integer obtenerNumeroAdolescentePorUdi(UDI udi ){
        
        Integer cantidadAdolescentesAux=0;
        WebTarget webTarget=cliente.target(Constantes.URL_TALLER+"/NumeroAdolescentesPorUzdi");        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.post(Entity.entity(udi, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        if(response.getStatus()==200){
           
            String cantidadAdolescente= response.readEntity(String.class);
                    
            if(cantidadAdolescente!=null){
                
                cantidadAdolescentesAux= Integer.parseInt(cantidadAdolescente);
            }
        }
        return cantidadAdolescentesAux;
    }
    
    public Integer obtenerNumeroAdolescentePorCai(CAI cai ){
        
        Integer cantidadAdolescentesAux=0;
        
        WebTarget webTarget=cliente.target(Constantes.URL_TALLER+"/NumeroAdolescentesPorCai");        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.post(Entity.entity(cai, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        if(response.getStatus()==200){
           
            String cantidadAdolescente= response.readEntity(String.class);
                    
            if(cantidadAdolescente!=null){
                
                cantidadAdolescentesAux= Integer.parseInt(cantidadAdolescente);
            }
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
        
        WebTarget webTarget=cliente.target(URL_TALLER+"/ItemsTaller").path(idTaller.toString());        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            listaItemsTaller= response.readEntity(new GenericType<List<ItemTaller>>(){});
        }           
        return listaItemsTaller;
    }
    
    public RegistroAsistencia obtenerRegistroAsistenciaPorTaller(Integer idTaller){
        
        RegistroAsistencia registroAsistenciaAux =null;
        
        WebTarget webTarget=cliente.target(URL_TALLER+"/RegistroAsistencia").path(idTaller.toString());        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            registroAsistenciaAux= response.readEntity(RegistroAsistencia.class);
        }           
        return registroAsistenciaAux;
    }
}
