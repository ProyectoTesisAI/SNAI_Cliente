/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.servicios;

import epn.edu.ec.modelo.UDI;
import epn.edu.ec.utilidades.Constantes;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author User
 */
public class UdiServicio {
    
    private final ConexionServicio<UDI> conexion;
    private static final String URL_UDI=Constantes.URL_UDI; 
    
    public UdiServicio(){
        conexion= new ConexionServicio<>();
    }   
    
    public List<UDI> listaUdi(){
        
        try {
            List<UDI> listaUDIAux = null;
            Response response = conexion.conexion(URL_UDI, "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    listaUDIAux = response.readEntity(new GenericType<List<UDI>>() {
                    });
                }
            }
            return listaUDIAux;
        } catch (Exception e) {
            return null;
        }
    }
}
