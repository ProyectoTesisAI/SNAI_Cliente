/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.servicios;


import epn.edu.ec.modelo.ItemTaller;
import epn.edu.ec.utilidades.Constantes;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author User
 */
public class ItemTallerServicio {
    
    private final ConexionServicio<ItemTaller> conexion;    
    private static final String URL_ITEM_TALLER_PSICOLOGIA=Constantes.URL_ITEM_TALLER; 
    
    public ItemTallerServicio(){
        conexion= new ConexionServicio<>();
    }   

    public ItemTaller guardarItemTaller(ItemTaller itemTaller){
        
        ItemTaller itemTallerPsicologiaAux=null;
        Response response= conexion.conexion(URL_ITEM_TALLER_PSICOLOGIA, "PUT", true, itemTaller);
        if(response.getStatus()==200){
            itemTallerPsicologiaAux =response.readEntity(ItemTaller.class);
        }
        
        return itemTallerPsicologiaAux;

    }
}
