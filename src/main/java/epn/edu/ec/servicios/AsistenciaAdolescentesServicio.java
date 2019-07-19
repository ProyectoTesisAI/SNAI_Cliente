/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.servicios;

import epn.edu.ec.modelo.AsistenciaAdolescente;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author User
 */
public class AsistenciaAdolescentesServicio {
    
    private final Client cliente;
    public String URL_REGISTRO_ASISTENCIA_ADOLESCENTE=Constantes.URL_REGISTRO_ASISTENCIA_ADOLESCENTE; 
    
    public AsistenciaAdolescentesServicio(){
        cliente= ClientBuilder.newClient();
    }   
    
    public AsistenciaAdolescente guardarRegistroAsistenciaAdolescente(AsistenciaAdolescente asistenciaAdolescente){
        
        AsistenciaAdolescente asistenciaAdolescenteAux=null;
        
        WebTarget webTarget=cliente.target(URL_REGISTRO_ASISTENCIA_ADOLESCENTE);        
        Invocation.Builder invocationBuilder=webTarget.request(MediaType.APPLICATION_JSON+";charset=UTF-8");     
        Response response =invocationBuilder.put(Entity.entity(asistenciaAdolescente, MediaType.APPLICATION_JSON+";charset=UTF-8"));
        if(response.getStatus()==200){
            asistenciaAdolescenteAux =response.readEntity(AsistenciaAdolescente.class);
        }
        
        return asistenciaAdolescenteAux;

    }
    
   
}
