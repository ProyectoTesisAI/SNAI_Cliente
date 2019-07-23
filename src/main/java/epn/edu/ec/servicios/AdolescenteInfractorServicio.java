package epn.edu.ec.servicios;

import epn.edu.ec.modelo.AdolescenteInfractor;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.core.Response;

public class AdolescenteInfractorServicio {
    
    private final ConexionServicio<AdolescenteInfractor> conexion;
    private static final String URL_ADOLESCENTE_INFRACTOR=Constantes.URL_ADOLESCENTE;
    
    public AdolescenteInfractorServicio() {
        conexion = new ConexionServicio<>();
    }
    
    public AdolescenteInfractor guardarAdolescenteInfractorUDI(AdolescenteInfractor adolescenteInfractor){
        
        AdolescenteInfractor adolescenteInfractorAux=null;
        Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR, "POST", true, adolescenteInfractor);
        if(response.getStatus()==200){       
            adolescenteInfractorAux=response.readEntity(AdolescenteInfractor.class);       
        } 
       
        return adolescenteInfractorAux;
        
    }
}
