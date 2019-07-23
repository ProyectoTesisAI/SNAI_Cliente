package epn.edu.ec.servicios;

import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.modelo.DetalleInfraccionCAI;
import epn.edu.ec.utilidades.Constantes;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class DetalleInfraccionCAIServicio {

    private final ConexionServicio<DetalleInfraccionCAI> conexionDetalle;
    private final ConexionServicio<AdolescenteInfractorCAI> conexionAdolescente;
    private static final String URL_DETALLE_INFRACCION = Constantes.URL_DETALLE_INFRACCION_CAI;
    
    public DetalleInfraccionCAIServicio() {
        conexionDetalle = new ConexionServicio<>();
        conexionAdolescente = new ConexionServicio<>();
    }

    public DetalleInfraccionCAI guardarDetalleInfraccionCAI(DetalleInfraccionCAI detalleInfraccion) {

        DetalleInfraccionCAI informacionInfraccionAux = null;
        Response response = conexionDetalle.conexion(URL_DETALLE_INFRACCION, "PUT", true, detalleInfraccion);
        if (response.getStatus() == 200) {
            informacionInfraccionAux = response.readEntity(DetalleInfraccionCAI.class);
        }

        return informacionInfraccionAux;

    }

    public DetalleInfraccionCAI obtenerDetalleInfraccionCAI(Integer id) {

        DetalleInfraccionCAI informacionInfraccionAux = null;
        Response response = conexionDetalle.conexion(URL_DETALLE_INFRACCION + "/" + id.toString(), "GET", true, null);
        if (response.getStatus() == 200) {
            informacionInfraccionAux = response.readEntity(DetalleInfraccionCAI.class);
        }
        return informacionInfraccionAux;
    }

    public List<DetalleInfraccionCAI> obtenerDetallesInfraccionCAI(AdolescenteInfractorCAI adolescente) {

        List<DetalleInfraccionCAI> listaDetallesInfraccionAux = null;
        Response response = conexionAdolescente.conexion(URL_DETALLE_INFRACCION+ "/ListaInfraccionesPorAdolescente", "POST", true, adolescente);
        if (response.getStatus() == 200) {
            listaDetallesInfraccionAux = response.readEntity(new GenericType<List<DetalleInfraccionCAI>>() {
            });
        } else if (response.getStatus() == 204) {
            listaDetallesInfraccionAux = new ArrayList<>();
        }
        return listaDetallesInfraccionAux;

    }
}
