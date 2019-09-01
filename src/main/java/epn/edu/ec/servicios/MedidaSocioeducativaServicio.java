package epn.edu.ec.servicios;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.modelo.MedidaSocioeducativa;
import epn.edu.ec.utilidades.URLServicios;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class MedidaSocioeducativaServicio {
    
    private final ConexionServicio<MedidaSocioeducativa> conexion;    
    private final ConexionServicio<AdolescenteInfractorUDI> conexionAdolescenteUDI;    
    private static final String URL_MEDIDA_SOCIOEDUCATIVA=URLServicios.URL_MEDIDA_SOCIOEDUCATIVA;  
    
    public MedidaSocioeducativaServicio(){
        conexion= new ConexionServicio<>();
        conexionAdolescenteUDI= new ConexionServicio<>();
    }   

    public MedidaSocioeducativa guardarMedidaSocioeducativa(MedidaSocioeducativa medidaSocioeducativa){
        
        try {

            MedidaSocioeducativa medidaSocioeducativaAux = null;
            Response response = conexion.conexion(URL_MEDIDA_SOCIOEDUCATIVA, "PUT", true, medidaSocioeducativa);
            if (response != null) {
                if (response.getStatus() == 200) {
                    medidaSocioeducativaAux = response.readEntity(MedidaSocioeducativa.class);
                }
            }
            return medidaSocioeducativaAux;
        } catch (Exception e) {
            return null;
        }
    }

    public List<MedidaSocioeducativa> listaMedidaSocioeducativosPorAdolescentesUzdi(AdolescenteInfractorUDI adolescenteInfractorUDI){
        
        try {
            List<MedidaSocioeducativa> listaMedidasSocioeducativas = null;
            Response response = conexionAdolescenteUDI.conexion(URL_MEDIDA_SOCIOEDUCATIVA + "/MedidasSocioeducativasPorAdolescenteUDI", "POST", true, adolescenteInfractorUDI);
            if (response != null) {
                if (response.getStatus() == 200) {
                    listaMedidasSocioeducativas = response.readEntity(new GenericType<List<MedidaSocioeducativa>>() {
                    });
                }
            }
            return listaMedidasSocioeducativas;
        } catch (Exception e) {
            return null;
        }

    }
    
    public List<MedidaSocioeducativa> listaMedidasSocioeducativasPorAdolescente(Integer idAdolescenteUZDI){
        
        try {
            List<MedidaSocioeducativa> listaMedidasSocioeducativas = null;
            Response response = conexion.conexion(URL_MEDIDA_SOCIOEDUCATIVA + "/Adolescente/" + idAdolescenteUZDI.toString(), "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    listaMedidasSocioeducativas = response.readEntity(new GenericType<List<MedidaSocioeducativa>>() {
                    });
                }
            }

            return listaMedidasSocioeducativas;
        } catch (Exception e) {
            return null;
        }        
    }
    
    public MedidaSocioeducativa obtenerMedidaMasAlta(Integer idAdolescenteUZDI){
        
        try {
            MedidaSocioeducativa medidaMasAlta = null;
            Response response = conexion.conexion(URL_MEDIDA_SOCIOEDUCATIVA + "/MedidaMasAlta/AdolescenteUDI/" + idAdolescenteUZDI.toString(), "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    List<MedidaSocioeducativa> medidasSocioeducativas = response.readEntity(new GenericType<List<MedidaSocioeducativa>>() {
                    });
                    if (medidasSocioeducativas != null) {
                        medidaMasAlta = medidasSocioeducativas.get(0);
                    }
                }
            }
            return medidaMasAlta;
            
        } catch (Exception e) {
            return null;
        }
    }
    
    public void eliminarMedidaSocioeducativa(Integer id) {
        Response response = conexion.conexion(URL_MEDIDA_SOCIOEDUCATIVA+"/"+id.toString(), "DELETE", true, null);
    }
}
