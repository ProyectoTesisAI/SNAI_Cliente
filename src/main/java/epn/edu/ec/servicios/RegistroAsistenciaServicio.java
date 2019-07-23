package epn.edu.ec.servicios;

import epn.edu.ec.modelo.AdolescenteInfractor;
import epn.edu.ec.modelo.RegistroAsistencia;
import epn.edu.ec.modelo.AsistenciaAdolescente;
import epn.edu.ec.modelo.CAI;
import epn.edu.ec.modelo.Taller;
import epn.edu.ec.modelo.UDI;
import epn.edu.ec.utilidades.Constantes;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class RegistroAsistenciaServicio {
    
    private final ConexionServicio<RegistroAsistencia> conexion;
    private final ConexionServicio<UDI> conexionUDI;
    private final ConexionServicio<CAI> conexionCAI;
    private final ConexionServicio<Taller> conexionTaller;
    private static final String URL_REGISTRO_ASISTENCIA=Constantes.URL_REGISTRO_ASISTENCIA; 
    
    public RegistroAsistenciaServicio(){
        conexion= new ConexionServicio<>();
        conexionUDI= new ConexionServicio<>();
        conexionCAI= new ConexionServicio<>();
        conexionTaller= new ConexionServicio<>();
    }   
    
    
    public List<AdolescenteInfractor> listaAdolescentesInfractoresPorUzdi(UDI udi){
        
        List<AdolescenteInfractor> registroAsistenciaUdi=null;
        Response response= conexionUDI.conexion(URL_REGISTRO_ASISTENCIA+"/ListaAdolescentesPorUzdi", "POST", true, udi);
        if(response.getStatus()==200){
            registroAsistenciaUdi=response.readEntity(new GenericType<List<AdolescenteInfractor>>(){});
        }
        
        return registroAsistenciaUdi;

    }
    
    public List<AdolescenteInfractor> listaAdolescentesInfractoresPorCai(CAI cai){
        
        List<AdolescenteInfractor> registroAsistenciaCai=null;
        Response response= conexionCAI.conexion(URL_REGISTRO_ASISTENCIA+"/ListaAdolescentesPorCai", "POST", true, cai);
        if(response.getStatus()==200){
            registroAsistenciaCai=response.readEntity(new GenericType<List<AdolescenteInfractor>>(){});
        }
        
        return registroAsistenciaCai;

    }
    
    public RegistroAsistencia guardarRegistroAsistencia(RegistroAsistencia registroAsistencia){
        
        RegistroAsistencia registroAsistenciaAux=null;
        Response response= conexion.conexion(URL_REGISTRO_ASISTENCIA, "PUT", true, registroAsistencia);
        if(response.getStatus()==200){
            registroAsistenciaAux =response.readEntity(RegistroAsistencia.class);
        }        
        return registroAsistenciaAux;

    }
    
    public List<AsistenciaAdolescente> listaAdolescentesInfractoresPorTaller(Taller taller){
        
        List<AsistenciaAdolescente> registroAsistenciaUdi=null;
        Response response= conexionTaller.conexion(URL_REGISTRO_ASISTENCIA+"/ListaAdolescentesPorTaller", "POST", true, taller);
        if(response.getStatus()==200){
            registroAsistenciaUdi=response.readEntity(new GenericType<List<AsistenciaAdolescente>>(){});
        }        
        return registroAsistenciaUdi;

    }
    
    public int obtenerNumeroAdolescentesPorTaller(Integer idTaller){
        
        Response response= conexion.conexion(URL_REGISTRO_ASISTENCIA+"/Taller/NumeroAsistentes/"+idTaller.toString(), "GET", true, null);
        if(response.getStatus()==200){
            int numeroAsistentes=Integer.parseInt(response.readEntity(String.class)); 
            return numeroAsistentes;
        }
        else{
            return 0;
        }
    }
    
    public Integer eliminarRegistroAsistencia(Integer registroAsistenciaTaller){
           
        Response response= conexion.conexion(URL_REGISTRO_ASISTENCIA+"/"+registroAsistenciaTaller.toString(), "DELETE", true, null);
        Integer resultado=response.getStatus();
        return resultado;
    }
}
