/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.servicios;


import epn.edu.ec.modelo.ItemTaller;
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
public class ItemTallerServicio {
    
    private final Client cliente;
    public String URL_ITEM_TALLER_PSICOLOGIA=Constantes.URL_ITEM_TALLER; 
    
    public ItemTallerServicio(){
        cliente= ClientBuilder.newClient();
    }   

    public List<ItemTaller> listaItemsTallerPsicologia(){
        
        List<ItemTaller> listaItemsTallerPsicologiaAux=null;
        
        WebTarget webTarget=cliente.target(URL_ITEM_TALLER_PSICOLOGIA);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            listaItemsTallerPsicologiaAux= response.readEntity(new GenericType<List<ItemTaller>>(){});
        }           
        return listaItemsTallerPsicologiaAux;
    }

    public ItemTaller guardarItemTaller(ItemTaller itemTaller){
        
        ItemTaller itemTallerPsicologiaAux=null;
        
        WebTarget webTarget=cliente.target(URL_ITEM_TALLER_PSICOLOGIA);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");     
        Response response =invocationBuilder.put(Entity.entity(itemTaller, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        if(response.getStatus()==200){
            itemTallerPsicologiaAux =response.readEntity(ItemTaller.class);
        }
        
        return itemTallerPsicologiaAux;

    }
}
