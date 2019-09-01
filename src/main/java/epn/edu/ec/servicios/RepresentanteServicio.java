package epn.edu.ec.servicios;

import epn.edu.ec.modelo.Representante;
import epn.edu.ec.utilidades.URLServicios;
import javax.ws.rs.core.Response;

public class RepresentanteServicio {
    
    private final ConexionServicio<Representante> conexion;    
    private static final String URL_REPRESENTANTE=URLServicios.URL_REPRESENTANTE;  
    
    public RepresentanteServicio(){
        conexion= new ConexionServicio<>();
    }   

    public Representante guardarRepresentante(Representante representante){
        
        try {
            Representante representanteAux = null;
            Response response = conexion.conexion(URL_REPRESENTANTE, "PUT", true, representante);
            if (response != null) {
                if (response.getStatus() == 200) {
                    representanteAux = response.readEntity(Representante.class);
                }
            }

            return representanteAux;
        } catch (Exception e) {
            return null;
        }
    }

    public Representante obtenerRepresentante(Integer id){
        
        try {
            Representante representanteAux = null;
            Response response = conexion.conexion(URL_REPRESENTANTE + "/" + id.toString(), "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    representanteAux = response.readEntity(Representante.class);
                }
            }
            return representanteAux;
        
        } catch (Exception e) {
            return null;
        }
    }
}
