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
        
        try {
            List<AdolescenteInfractor> registroAsistenciaUdi = null;
            Response response = conexionUDI.conexion(URL_REGISTRO_ASISTENCIA + "/ListaAdolescentesPorUzdi", "POST", true, udi);
            if (response != null) {
                if (response.getStatus() == 200) {
                    registroAsistenciaUdi = response.readEntity(new GenericType<List<AdolescenteInfractor>>() {
                    });
                }
            }
            return registroAsistenciaUdi;
        }catch (Exception e) {
            return null;
        }
    }
    
    public List<AdolescenteInfractor> listaAdolescentesInfractoresPorCai(CAI cai){
        
        try {

            List<AdolescenteInfractor> registroAsistenciaCai = null;
            Response response = conexionCAI.conexion(URL_REGISTRO_ASISTENCIA + "/ListaAdolescentesPorCai", "POST", true, cai);
            if (response != null) {
                if (response.getStatus() == 200) {
                    registroAsistenciaCai = response.readEntity(new GenericType<List<AdolescenteInfractor>>() {
                    });
                }
            }

            return registroAsistenciaCai;
        } catch (Exception e) {
            return null;
        }

    }
    
    public RegistroAsistencia guardarRegistroAsistencia(RegistroAsistencia registroAsistencia){
        
        try {
            RegistroAsistencia registroAsistenciaAux = null;
            Response response = conexion.conexion(URL_REGISTRO_ASISTENCIA, "PUT", true, registroAsistencia);
            if (response != null) {
                if (response.getStatus() == 200) {
                    registroAsistenciaAux = response.readEntity(RegistroAsistencia.class);
                }
            }

            return registroAsistenciaAux;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<AsistenciaAdolescente> listaAdolescentesInfractoresPorTaller(Taller taller){
        
        try {
            List<AsistenciaAdolescente> registroAsistenciaUdi = null;
            Response response = conexionTaller.conexion(URL_REGISTRO_ASISTENCIA + "/ListaAdolescentesPorTaller", "POST", true, taller);
            if (response != null) {
                if (response.getStatus() == 200) {
                    registroAsistenciaUdi = response.readEntity(new GenericType<List<AsistenciaAdolescente>>() {
                    });
                }
            }

            return registroAsistenciaUdi;
        } catch (Exception e) {
            return null;
        }
    }
    
    public int obtenerNumeroAdolescentesPorTaller(Integer idTaller){
        
        try {
            Response response = conexion.conexion(URL_REGISTRO_ASISTENCIA + "/Taller/NumeroAsistentes/" + idTaller.toString(), "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    int numeroAsistentes = Integer.parseInt(response.readEntity(String.class));
                    return numeroAsistentes;
                } else {
                    return 0;
                }
            }
            else{
                return 0;
            }
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    public Integer eliminarRegistroAsistencia(Integer registroAsistenciaTaller){
           
        try {
            Response response = conexion.conexion(URL_REGISTRO_ASISTENCIA + "/" + registroAsistenciaTaller.toString(), "DELETE", true, null);
            if (response != null) {
                Integer resultado = response.getStatus();
                return resultado;
            } else {
                return 0;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
