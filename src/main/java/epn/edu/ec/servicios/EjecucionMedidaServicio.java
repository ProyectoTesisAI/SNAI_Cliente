package epn.edu.ec.servicios;

import epn.edu.ec.modelo.DetalleInfraccionCAI;
import epn.edu.ec.modelo.EjecucionMedidaCAI;
import epn.edu.ec.utilidades.URLServicios;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class EjecucionMedidaServicio {

    private final ConexionServicio<DetalleInfraccionCAI> conexionDI;
    private final ConexionServicio<EjecucionMedidaCAI> conexionEM;
    private static final String URL_EJECUCION_MEDIDA = URLServicios.URL_EJECUCION_MEDIDA_CAI;
    
    public EjecucionMedidaServicio() {
        conexionDI = new ConexionServicio<>();
        conexionEM = new ConexionServicio<>();
    }

    public EjecucionMedidaCAI guardarEjecucionMedidaCAI(EjecucionMedidaCAI ejecucionMedida) {

        try {
            EjecucionMedidaCAI ejecucionMedidaAux = null;
            Response response = conexionEM.conexion(URL_EJECUCION_MEDIDA, "PUT", true, ejecucionMedida);
            if (response != null) {
                if (response.getStatus() == 200) {
                    ejecucionMedidaAux = response.readEntity(EjecucionMedidaCAI.class);
                }
            }

            return ejecucionMedidaAux;
        } catch (Exception e) {
            return null;
        }      

    }

    public EjecucionMedidaCAI obtenerEjecucionMedidaCAI(Integer id) {

        try {

            EjecucionMedidaCAI ejecucionMedidaAux = null;
            Response response = conexionEM.conexion(URL_EJECUCION_MEDIDA + "/" + id.toString(), "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    ejecucionMedidaAux = response.readEntity(EjecucionMedidaCAI.class);
                }
            }
            return ejecucionMedidaAux;
        } catch (Exception e) {
            return null;
        }    

    }

    public List<EjecucionMedidaCAI> obtenerMedidasPorInfraccionCAI(DetalleInfraccionCAI infraccion) {

        try {

            List<EjecucionMedidaCAI> listaMedidasPorInfraccionAux = null;
            Response response = conexionDI.conexion(URL_EJECUCION_MEDIDA + "/ListaMedidasPorInfraccionCAI", "POST", true, infraccion);
            if (response != null) {
                if (response.getStatus() == 200) {
                    listaMedidasPorInfraccionAux = response.readEntity(new GenericType<List<EjecucionMedidaCAI>>() {
                    });
                } else if (response.getStatus() == 204) {
                    listaMedidasPorInfraccionAux = new ArrayList<>();
                }
            }
            return listaMedidasPorInfraccionAux;
        } catch (Exception e) {
            return null;
        }       

    }
    
    public List<EjecucionMedidaCAI> obtenerMedidasPorIdAdolescenteCAI(Integer idAdolesceneteCAI) {

        try {
            List<EjecucionMedidaCAI> listaEjecucionMedidasAux = null;
            Response response = conexionEM.conexion(URL_EJECUCION_MEDIDA + "/ListaMedidasPorIdAdolescente/" + idAdolesceneteCAI.toString(), "GET", true, null);
            if (response != null) {
                if (response.getStatus() == 200) {
                    listaEjecucionMedidasAux = response.readEntity(new GenericType<List<EjecucionMedidaCAI>>() {});
                }
            }

            return listaEjecucionMedidasAux;
        } catch (Exception e) {
            return null;
        }
       
    }
}
