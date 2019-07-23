/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.servicios;

import epn.edu.ec.modelo.RegistroFotografico;
import epn.edu.ec.utilidades.Constantes;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author User
 */
public class RegistroFotograficoServicio {
 
    private final ConexionServicio<RegistroFotografico> conexion;    
    private static final String URL_REGISTRO_FOTOGRAFICO=Constantes.URL_REGISTRO_FOTOGRAFICO;
    
    public RegistroFotograficoServicio(){
        conexion= new ConexionServicio<>();
    }
    
    public void guardarRegistroFotografico(RegistroFotografico registroFotografico){
        
        try{
            Response response= conexion.conexion(URL_REGISTRO_FOTOGRAFICO, "PUT", true, registroFotografico);
        }catch(Exception e){
            System.out.println("ERROR:---->"+e);
        }

    }
    
    public List<RegistroFotografico> listaRegistroFotografico(Integer id){
        
        List<RegistroFotografico> listaRegistroFotograficoAux=null;
        Response response= conexion.conexion(URL_REGISTRO_FOTOGRAFICO+"/Informe/"+id.toString(), "GET", true, null);
        if(response.getStatus()==200){
            listaRegistroFotograficoAux= response.readEntity(new GenericType<List<RegistroFotografico>>(){});
        }           
        return listaRegistroFotograficoAux;
    }
    
    public Integer eliminarRegistroFotografico(Integer registroFotografico){
           
        Response response= conexion.conexion(URL_REGISTRO_FOTOGRAFICO+"/"+registroFotografico.toString(), "DELETE", true, null);
        Integer resultado=response.getStatus();
        return resultado;
    }
}
