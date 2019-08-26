package epn.edu.ec.servicios;

import epn.edu.ec.modelo.AsistenciaAdolescente;
import epn.edu.ec.utilidades.Constantes;
import javax.ws.rs.core.Response;

public class AsistenciaAdolescentesServicio {

    private final ConexionServicio<AsistenciaAdolescente> conexion;
    private static final String URL_REGISTRO_ASISTENCIA_ADOLESCENTE = Constantes.URL_REGISTRO_ASISTENCIA_ADOLESCENTE;
    
    public AsistenciaAdolescentesServicio() {
        conexion = new ConexionServicio<>();
    }

    public AsistenciaAdolescente guardarRegistroAsistenciaAdolescente(AsistenciaAdolescente asistenciaAdolescente) {

        try {
            AsistenciaAdolescente asistenciaAdolescenteAux = null;
            Response response = conexion.conexion(URL_REGISTRO_ASISTENCIA_ADOLESCENTE, "PUT", true, asistenciaAdolescente);
            if (response != null) {
                if (response.getStatus() == 200) {
                    asistenciaAdolescenteAux = response.readEntity(AsistenciaAdolescente.class);
                }
            }
            return asistenciaAdolescenteAux;
        } catch (Exception e) {
            return null;
        }
    }

}
