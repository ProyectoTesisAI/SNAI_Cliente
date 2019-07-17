/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.servicios;

import epn.edu.ec.modelo.RegistroFotografico;
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
public class RegistroFotograficoServicio {
 
    private final Client cliente;
    public String URL_REGISTRO_FOTOGRAFICO=Constantes.URL_REGISTRO_FOTOGRAFICO;
    
    public RegistroFotograficoServicio(){
        cliente= ClientBuilder.newClient();
    }
    
    public void guardarRegistroFotografico(RegistroFotografico registroFotografico){
        
        try{
            
            WebTarget webTarget=cliente.target(URL_REGISTRO_FOTOGRAFICO);        
            Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");     
            Response response =invocationBuilder.put(Entity.entity(registroFotografico, MediaType.APPLICATION_JSON+";charset=UTF-8"));
            
        }catch(Exception e){
            System.out.println("ERROR:---->"+e);
        }

    }
    
    public List<RegistroFotografico> listaRegistroFotografico(Integer id){
        
        List<RegistroFotografico> listaRegistroFotograficoAux=null;
        
        WebTarget webTarget=cliente.target(URL_REGISTRO_FOTOGRAFICO+"/Informe/"+id.toString());        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            listaRegistroFotograficoAux= response.readEntity(new GenericType<List<RegistroFotografico>>(){});
        }           
        return listaRegistroFotograficoAux;
    }
}
