package epn.edu.ec.servicios;

import epn.edu.ec.modelo.InformacionInfraccion;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.core.Response;

public class InformacionInfraccionServicio {
    
    private final ConexionServicio<InformacionInfraccion> conexion;    
    private static final String URL_INFORMACION_INFRACCION=Constantes.URL_INFORMACION_INFRACCION;  
    
    public InformacionInfraccionServicio(){
        conexion= new ConexionServicio<>();
    }   

    public InformacionInfraccion guardarInformacionInfraccion(InformacionInfraccion informacionInfraccion){
        
        InformacionInfraccion informacionInfraccionAux=null;
        Response response= conexion.conexion(URL_INFORMACION_INFRACCION, "PUT", true, informacionInfraccion);
        if(response.getStatus()==200){        
            informacionInfraccionAux=response.readEntity(InformacionInfraccion.class);       
        } 
       
        return informacionInfraccionAux;
        
    }

    public InformacionInfraccion obtenerInformacionInfraccion(Integer id){
        
        InformacionInfraccion informacionInfraccionAux=null;
        Response response= conexion.conexion(URL_INFORMACION_INFRACCION+"/"+id.toString(), "GET", true, null);
        if(response.getStatus()==200){
            informacionInfraccionAux= response.readEntity(InformacionInfraccion.class);
        }           
        return informacionInfraccionAux;
        
    }
}
