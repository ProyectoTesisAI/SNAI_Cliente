package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.servicios.AdolescenteInfractorServicio;
import epn.edu.ec.servicios.AdolescenteInfractorUDIServicio;
import epn.edu.ec.utilidades.Constantes;
import epn.edu.ec.utilidades.EnlacesPrograma;
import epn.edu.ec.utilidades.PermisosUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "panelAdolescenteInfractorSinZonaUDIControlador")
@ViewScoped
public class PanelAdolescenteInfractorSinZonaUDIControlador implements Serializable {

    private List<AdolescenteInfractorUDI> listadoAdolescentesInfractoresSinZonaUDI;
    private AdolescenteInfractorUDIServicio servicio;
    private AdolescenteInfractorServicio servicioAI;
    private EnlacesPrograma enlaces;
    private PermisosUsuario permisosUsuario;

    @PostConstruct
    public void init() {

        permisosUsuario = new PermisosUsuario();
        servicio = new AdolescenteInfractorUDIServicio();
        servicioAI = new AdolescenteInfractorServicio();
        enlaces = new EnlacesPrograma();
        listadoAdolescentesInfractoresSinZonaUDI = new ArrayList<>();
        listadoAdolescentesInfractoresSinZonaUDI = servicio.listaAdolescentesInfractoresSinZonaUDI();
        
        if (listadoAdolescentesInfractoresSinZonaUDI != null) {
            if (listadoAdolescentesInfractoresSinZonaUDI.size() > 0) {
                for (AdolescenteInfractorUDI a : listadoAdolescentesInfractoresSinZonaUDI) {
                    if (a.getIdAdolescenteInfractor().getDocumento() != null) {
                        a.getIdAdolescenteInfractor().setCedula(a.getIdAdolescenteInfractor().getDocumento());
                    }
                }
            }
        }

    }

    public List<AdolescenteInfractorUDI> getListadoAdolescentesInfractoresSinZonaUDI() {
        return listadoAdolescentesInfractoresSinZonaUDI;
    }

    public void setListadoAdolescentesInfractoresSinZonaUDI(List<AdolescenteInfractorUDI> listadoAdolescentesInfractoresSinZonaUDI) {
        this.listadoAdolescentesInfractoresSinZonaUDI = listadoAdolescentesInfractoresSinZonaUDI;
    }

    public String agregarInformacion(AdolescenteInfractorUDI ai_udi) {
        String gestionInformacionAdolescenteUzdi = permisosUsuario.redireccionGestionInformacionUzdi();
        AdolescenteInfractorUDI aux = new AdolescenteInfractorUDI();
        aux=servicio.obtenerAdolescenteInfractorUDI(ai_udi.getIdAdolescenteInfractor().getIdAdolescenteInfractor());
        if (gestionInformacionAdolescenteUzdi != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("indiceActual", "0");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adolescente_infractor_udi", aux);
            return gestionInformacionAdolescenteUzdi;
        } else {
            return null;
        }
    }

    public String editarInformacion(AdolescenteInfractorUDI ai_udi) {
        String gestionEdicionInformacionAdolescenteUzdi = permisosUsuario.redireccionGestionEdicionInformacionUzdi();
        AdolescenteInfractorUDI aux = new AdolescenteInfractorUDI();
        aux=servicio.obtenerAdolescenteInfractorUDI(ai_udi.getIdAdolescenteInfractor().getIdAdolescenteInfractor());
        if (gestionEdicionInformacionAdolescenteUzdi != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("indiceActual", "0");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adolescente_infractor_udi", aux);
            return enlaces.PATH_PANEL_EDITAR_UDI_ADMINISTRADOR + "?faces-redirect=true";
        } else {
            return enlaces.PATH_ERROR + "?faces-redirect=true";
        }
        //return enlaces.PATH_ADOLESCENTE_UDI_CREAR+"?faces-redirect=true";
    }

    public String eliminarAdolescenteInfractor(AdolescenteInfractorUDI adolescenteSeleccionado) {
        String rolActual = permisosUsuario.RolUsuario();
        if (Constantes.ROL_ADMINISTRADOR.equals(rolActual)) {
            int statusRespuesta = servicioAI.eliminarAdolescenteInfractor(adolescenteSeleccionado.getIdAdolescenteInfractor().getIdAdolescenteInfractor());

            if (statusRespuesta == 200) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA ELIMINADO CORRECTAMENTE EL REGISTRO", "INFORMACION"));
                return enlaces.PATH_PANEL_UDI_ADMINISTRADOR+"?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR EN EL SERVICIO", "ERROR"));
                return null;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "NO TIENE ACCESO DE ADMINISTRADOR PARA REALIZAR ESTA ACCION", "ERROR"));
            return enlaces.PATH_ERROR+"?faces-redirect=true";
        }
    }
}
