package epn.edu.ec.servicios;

import epn.edu.ec.modelo.Representante;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.core.Response;

public class RepresentanteServicio {
    
    private final ConexionServicio<Representante> conexion;    
    private static final String URL_REPRESENTANTE=Constantes.URL_REPRESENTANTE;  
    
    public RepresentanteServicio(){
        conexion= new ConexionServicio<>();
    }   

    public Representante guardarRepresentante(Representante representante){
        
        Representante representanteAux=null;
        Response response= conexion.conexion(URL_REPRESENTANTE, "PUT", true, representante);
        if(response.getStatus()==200){        
            representanteAux=response.readEntity(Representante.class);       
        } 
       
        return representanteAux;
        
    }

    public Representante obtenerRepresentante(Integer id){
        
        Representante representanteAux=null;
        Response response= conexion.conexion(URL_REPRESENTANTE+"/"+id.toString(), "GET", true, null);
        if(response.getStatus()==200){
            representanteAux= response.readEntity(Representante.class);
        }           
        return representanteAux;
        
    }
}
