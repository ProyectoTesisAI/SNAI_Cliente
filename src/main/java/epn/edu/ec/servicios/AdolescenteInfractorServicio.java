package epn.edu.ec.servicios;

import epn.edu.ec.modelo.AdolescenteInfractor;
import epn.edu.ec.modelo.EjecucionMedidaCAI;
import epn.edu.ec.modelo.Reporte1;
import epn.edu.ec.modelo.Reporte2;
import epn.edu.ec.modelo.Reporte3;
import epn.edu.ec.modelo.Reporte4;
import epn.edu.ec.modelo.Reporte5;
import epn.edu.ec.modelo.Reporte6S;
import epn.edu.ec.modelo.Usuario;
import epn.edu.ec.utilidades.Constantes;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AdolescenteInfractorServicio {

    private final ConexionServicio<AdolescenteInfractor> conexion;
    private final ConexionServicio<Reporte1> conexionR1;
    private static final String URL_ADOLESCENTE_INFRACTOR = Constantes.URL_ADOLESCENTE;

    public AdolescenteInfractorServicio() {
        conexion = new ConexionServicio<>();
        conexionR1 = new ConexionServicio<>();
    }

    public AdolescenteInfractor guardarAdolescenteInfractorUDI(AdolescenteInfractor adolescenteInfractor) {
        AdolescenteInfractor adolescenteInfractorAux = null;
        Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR, "POST", true, adolescenteInfractor);
        if (response.getStatus() == 200) {
            adolescenteInfractorAux = response.readEntity(AdolescenteInfractor.class);
        }
        return adolescenteInfractorAux;
    }

    public List<Reporte1> reporteTipoDelitoUDI(Reporte1 tipoDelito) {
        List<Reporte1> reporteAux = null;
        String token = null;
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
        if (usuarioAux != null) {
            token = usuarioAux.getToken();
        }
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(URL_ADOLESCENTE_INFRACTOR).path("reporteTipoDelitoUDI");
        Invocation.Builder invocationBuilder= webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8").header(HttpHeaders.AUTHORIZATION,"Bearer "+token );
        Response response = invocationBuilder.post(Entity.entity(tipoDelito, MediaType.APPLICATION_JSON + ";charset=UTF-8"));
        if (response.getStatus() == 200) {
            reporteAux = response.readEntity(new GenericType<List<Reporte1>>() {
            });
        } else if (response.getStatus() == 204) {
            reporteAux = null;
        }
        return reporteAux;
    }
    
    public List<Reporte1> reporteTipoDelitoCAI(Reporte1 tipoDelito) {
        List<Reporte1> reporteAux = null;
        String token = null;
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
        if (usuarioAux != null) {
            token = usuarioAux.getToken();
        }
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(URL_ADOLESCENTE_INFRACTOR).path("reporteTipoDelitoCAI");
        Invocation.Builder invocationBuilder= webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8").header(HttpHeaders.AUTHORIZATION,"Bearer "+token );
        Response response = invocationBuilder.post(Entity.entity(tipoDelito, MediaType.APPLICATION_JSON + ";charset=UTF-8"));
        if (response.getStatus() == 200) {
            reporteAux = response.readEntity(new GenericType<List<Reporte1>>() {
            });
        } else if (response.getStatus() == 204) {
            reporteAux = null;
        }
        return reporteAux;
    }
    
    public List<Reporte2> reporteEdadCAI(Reporte2 edad) {
        List<Reporte2> reporteAux = null;
        String token = null;
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
        if (usuarioAux != null) {
            token = usuarioAux.getToken();
        }
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(URL_ADOLESCENTE_INFRACTOR).path("reporteEdadCAI");
        Invocation.Builder invocationBuilder= webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8").header(HttpHeaders.AUTHORIZATION,"Bearer "+token );
        Response response = invocationBuilder.post(Entity.entity(edad, MediaType.APPLICATION_JSON + ";charset=UTF-8"));
        if (response.getStatus() == 200) {
            reporteAux = response.readEntity(new GenericType<List<Reporte2>>() {
            });
        } else if (response.getStatus() == 204) {
            reporteAux = null;
        }
        return reporteAux;
    }
    
    public List<Reporte2> reporteEdadUDI(Reporte2 edad) {
        List<Reporte2> reporteAux = null;
        String token = null;
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
        if (usuarioAux != null) {
            token = usuarioAux.getToken();
        }
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(URL_ADOLESCENTE_INFRACTOR).path("reporteEdadUDI");
        Invocation.Builder invocationBuilder= webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8").header(HttpHeaders.AUTHORIZATION,"Bearer "+token );
        Response response = invocationBuilder.post(Entity.entity(edad, MediaType.APPLICATION_JSON + ";charset=UTF-8"));
        if (response.getStatus() == 200) {
            reporteAux = response.readEntity(new GenericType<List<Reporte2>>() {
            });
        } else if (response.getStatus() == 204) {
            reporteAux = null;
        }
        return reporteAux;
    }
    
    public List<Reporte2> reporteEdadFechaUDI(Date edad) {
        List<Reporte2> reporteAux = null;
        String token = null;
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
        if (usuarioAux != null) {
            token = usuarioAux.getToken();
        }
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(URL_ADOLESCENTE_INFRACTOR).path("reporteEdadFechaUDI");
        Invocation.Builder invocationBuilder= webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8").header(HttpHeaders.AUTHORIZATION,"Bearer "+token );
        Response response = invocationBuilder.post(Entity.entity(edad, MediaType.APPLICATION_JSON + ";charset=UTF-8"));
        if (response.getStatus() == 200) {
            reporteAux = response.readEntity(new GenericType<List<Reporte2>>() {
            });
        } else if (response.getStatus() == 204) {
            reporteAux = null;
        }
        return reporteAux;
    }
    
    public List<Reporte2> reporteEdadFechaCAI(Date edad) {
        List<Reporte2> reporteAux = null;
        String token = null;
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
        if (usuarioAux != null) {
            token = usuarioAux.getToken();
        }
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(URL_ADOLESCENTE_INFRACTOR).path("reporteEdadFechaCAI");
        Invocation.Builder invocationBuilder= webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8").header(HttpHeaders.AUTHORIZATION,"Bearer "+token );
        Response response = invocationBuilder.post(Entity.entity(edad, MediaType.APPLICATION_JSON + ";charset=UTF-8"));
        if (response.getStatus() == 200) {
            reporteAux = response.readEntity(new GenericType<List<Reporte2>>() {
            });
        } else if (response.getStatus() == 204) {
            reporteAux = null;
        }
        return reporteAux;
    }
    
    public List<Reporte3> reporteNacionalidadCAI(String nacionalidad) {
        List<Reporte3> reporteAux = null;
        String token = null;
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
        if (usuarioAux != null) {
            token = usuarioAux.getToken();
        }
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(URL_ADOLESCENTE_INFRACTOR).path("reporteNacionalidadCAI");
        Invocation.Builder invocationBuilder= webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8").header(HttpHeaders.AUTHORIZATION,"Bearer "+token );
        Response response = invocationBuilder.post(Entity.entity(nacionalidad, MediaType.APPLICATION_JSON + ";charset=UTF-8"));
        if (response.getStatus() == 200) {
            reporteAux = response.readEntity(new GenericType<List<Reporte3>>() {
            });
        } else if (response.getStatus() == 204) {
            reporteAux = null;
        }
        return reporteAux;
    }
    
    public List<Reporte3> reporteNacionalidadUDI(String nacionalidad) {
        List<Reporte3> reporteAux = null;
        String token = null;
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
        if (usuarioAux != null) {
            token = usuarioAux.getToken();
        }
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(URL_ADOLESCENTE_INFRACTOR).path("reporteNacionalidadUDI");
        Invocation.Builder invocationBuilder= webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8").header(HttpHeaders.AUTHORIZATION,"Bearer "+token );
        Response response = invocationBuilder.post(Entity.entity(nacionalidad, MediaType.APPLICATION_JSON + ";charset=UTF-8"));
        if (response.getStatus() == 200) {
            reporteAux = response.readEntity(new GenericType<List<Reporte3>>() {
            });
        } else if (response.getStatus() == 204) {
            reporteAux = null;
        }
        return reporteAux;
    }
    
    public List<Reporte4> reporteMedidasSocioeducativasUDI(String medidaSocioeducativa) {
        List<Reporte4> reporteAux = null;
        String token = null;
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
        if (usuarioAux != null) {
            token = usuarioAux.getToken();
        }
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(URL_ADOLESCENTE_INFRACTOR).path("reporteMedidaSocioeducativaUDI");
        Invocation.Builder invocationBuilder= webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8").header(HttpHeaders.AUTHORIZATION,"Bearer "+token );
        Response response = invocationBuilder.post(Entity.entity(medidaSocioeducativa, MediaType.APPLICATION_JSON + ";charset=UTF-8"));
        if (response.getStatus() == 200) {
            reporteAux = response.readEntity(new GenericType<List<Reporte4>>() {
            });
        } else if (response.getStatus() == 204) {
            reporteAux = null;
        }
        return reporteAux;
    }
    
    public List<Reporte4> reporteMedidasSocioeducativasCAI(EjecucionMedidaCAI medidaSocioeducativa) {
        List<Reporte4> reporteAux = null;
        String token = null;
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
        if (usuarioAux != null) {
            token = usuarioAux.getToken();
        }
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(URL_ADOLESCENTE_INFRACTOR).path("reporteMedidaSocioeducativaCAI");
        Invocation.Builder invocationBuilder= webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8").header(HttpHeaders.AUTHORIZATION,"Bearer "+token );
        Response response = invocationBuilder.post(Entity.entity(medidaSocioeducativa, MediaType.APPLICATION_JSON + ";charset=UTF-8"));
        if (response.getStatus() == 200) {
            reporteAux = response.readEntity(new GenericType<List<Reporte4>>() {
            });
        } else if (response.getStatus() == 204) {
            reporteAux = null;
        }
        return reporteAux;
    }
    
    public List<Reporte5> reporteFechaIngresoCAI(Date fechaIngreso) {
        List<Reporte5> reporteAux = null;
        String token = null;
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
        if (usuarioAux != null) {
            token = usuarioAux.getToken();
        }
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(URL_ADOLESCENTE_INFRACTOR).path("reporteFechaIngesoCAI");
        Invocation.Builder invocationBuilder= webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8").header(HttpHeaders.AUTHORIZATION,"Bearer "+token );
        Response response = invocationBuilder.post(Entity.entity(fechaIngreso, MediaType.APPLICATION_JSON + ";charset=UTF-8"));
        if (response.getStatus() == 200) {
            reporteAux = response.readEntity(new GenericType<List<Reporte5>>() {
            });
        } else if (response.getStatus() == 204) {
            reporteAux = null;
        }
        return reporteAux;
    }
    
    public List<Reporte6S> reporteNivelEducacionSiUDI(String nivelEducacion) {
        List<Reporte6S> reporteAux = null;
        String token = null;
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
        if (usuarioAux != null) {
            token = usuarioAux.getToken();
        }
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(URL_ADOLESCENTE_INFRACTOR).path("reporteNivelEducacionSUDI");
        Invocation.Builder invocationBuilder= webTarget.request(MediaType.APPLICATION_JSON + ";charset=UTF-8").header(HttpHeaders.AUTHORIZATION,"Bearer "+token );
        Response response = invocationBuilder.post(Entity.entity(nivelEducacion, MediaType.APPLICATION_JSON + ";charset=UTF-8"));
        if (response.getStatus() == 200) {
            reporteAux = response.readEntity(new GenericType<List<Reporte6S>>() {
            });
        } else if (response.getStatus() == 204) {
            reporteAux = null;
        }
        return reporteAux;
    }
}
