package epn.edu.ec.servicios;

import epn.edu.ec.modelo.InformacionJudicial;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.core.Response;

public class InformacionJudicialServicio {
     
    private final ConexionServicio<InformacionJudicial> conexion;    
    private static final String URL_INFORMACION_JUDICIAL=Constantes.URL_INFORMACION_JUDICIAL;  
    
    public InformacionJudicialServicio(){
        conexion= new ConexionServicio<>();
    }   

    public InformacionJudicial guardarInformacionJudicial(InformacionJudicial informacionJudicial){
        
        InformacionJudicial informacionJudicialAux=null;
        Response response= conexion.conexion(URL_INFORMACION_JUDICIAL, "PUT", true, informacionJudicial);
        if(response.getStatus()==200){        
            informacionJudicialAux=response.readEntity(InformacionJudicial.class);       
        } 
       
        return informacionJudicialAux;
        
    }

    public InformacionJudicial obtenerInformacionJudicial(Integer id){
        
        InformacionJudicial informacionJudicialAux=null;
        Response response= conexion.conexion(URL_INFORMACION_JUDICIAL+"/"+id.toString(), "GET", true, null);
        if(response.getStatus()==200){
            informacionJudicialAux= response.readEntity(InformacionJudicial.class);
        }           
        return informacionJudicialAux;
        
    }
}
