/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.servicios;

import epn.edu.ec.modelo.Informe;
import epn.edu.ec.utilidades.Constantes;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author User
 */
public class InformeServicio {
    
    private final ConexionServicio<Informe> conexion;    
    private static final String URL_INFORME=Constantes.URL_INFORME;
    
    public InformeServicio(){
        conexion= new ConexionServicio<>();
    }
    
    public Informe guardarInforme(Informe informe){
        
        Informe informeAux=null;
        Response response= conexion.conexion(URL_INFORME, "PUT", true, informe);
        if(response.getStatus()==200){
            informeAux =response.readEntity(Informe.class);
        }
        return informeAux;

    }
    
    
    public List<Informe> listarInforme(){
        
        List<Informe> listaItemsInforme=null;
        Response response= conexion.conexion(URL_INFORME, "GET", true, null);
        if(response.getStatus()==200){
            listaItemsInforme= response.readEntity(new GenericType<List<Informe>>(){});
        }           
        return listaItemsInforme;
    }
    
}
