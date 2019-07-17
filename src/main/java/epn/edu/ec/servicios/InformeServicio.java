/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.servicios;

import epn.edu.ec.modelo.Informe;
import epn.edu.ec.modelo.ItemTaller;
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
public class InformeServicio {
    
    private final Client cliente;
    public String URL_INFORME=Constantes.URL_INFORME;
    
    public InformeServicio(){
        cliente= ClientBuilder.newClient();
    }
    
    public Informe guardarInforme(Informe informe){
        
        Informe informePsicologiaAux=null;
        
        WebTarget webTarget=cliente.target(URL_INFORME);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");     
        Response response =invocationBuilder.put(Entity.entity(informe, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        if(response.getStatus()==200){
            informePsicologiaAux =response.readEntity(Informe.class);
        }
        
        return informePsicologiaAux;

    }
    
    
    public List<Informe> listarInforme(){
        
        List<Informe> listaItemsInforme=null;
        
        WebTarget webTarget=cliente.target(URL_INFORME);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");        
        Response response =invocationBuilder.get();
        if(response.getStatus()==200){
            listaItemsInforme= response.readEntity(new GenericType<List<Informe>>(){});
        }           
        return listaItemsInforme;
    }
    
}
