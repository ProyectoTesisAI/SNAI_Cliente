package epn.edu.ec.controlador;

import epn.edu.ec.utilidades.EnlacesPrograma;
import epn.edu.ec.utilidades.PermisosUsuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "administracionMenuControlador")
@ViewScoped
public class AdministracionMenuControlador implements Serializable {

    private EnlacesPrograma enlaces;
    private PermisosUsuario permisosUsuario;
    private boolean esCAI;
    private boolean esUZDI;
    private boolean tipoRolJuridico;
    private boolean tipoRolPsicologo;
    private boolean tipoRolInspector;
    private boolean tipoRolAdministrador;
    private boolean tipoRolAdministradorOSubdirector;
    private boolean permitidoCrearAdolescenteUDI;
    private boolean permitidoCrearAdolescenteCAI;
    private boolean permitidoCrearUsuario;

    @PostConstruct
    public void init() {

        enlaces = new EnlacesPrograma();
        permisosUsuario = new PermisosUsuario();
    }

    public boolean isTipoRolAdministrador() {
        String rolUsuario = permisosUsuario.RolUsuario();
        if (rolUsuario != null) {
            if ("ADMINISTRADOR".equals(rolUsuario)) {
                tipoRolInspector = false;
                tipoRolPsicologo = false;
                tipoRolJuridico = false;
                tipoRolAdministrador = true;
            }
        }
        return tipoRolAdministrador;
    }

    public boolean isTipoRolAdministradorOSubdirector() {
        String rolUsuario = permisosUsuario.RolUsuario();
        if (rolUsuario != null) {
            if ("ADMINISTRADOR".equals(rolUsuario) || "SUBDIRECTOR".equals(rolUsuario)) {
                tipoRolInspector = false;
                tipoRolPsicologo = false;
                tipoRolJuridico = false;
                tipoRolAdministrador = false;
                tipoRolAdministradorOSubdirector = true;
            }
        }
        return tipoRolAdministradorOSubdirector;
    }

    public boolean isEsCAI() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {
            if ("DIRECTOR TECNICO DE MEDIDAS PRIVATIVAS Y ATENCIÓN".equals(rolUsuario) || "COORDINADOR CAI".equals(rolUsuario) || "EQUIPO TECNICO PSICOLOGO CAI".equals(rolUsuario) || "EQUIPO TECNICO JURIDICO CAI".equals(rolUsuario) || "TRABAJADOR SOCIAL CAI".equals(rolUsuario) || "INSPECTOR EDUCADOR".equals(rolUsuario) || "ADMINISTRADOR".equals(rolUsuario) || "SUBDIRECTOR".equals(rolUsuario)) {
                esUZDI = false;
                esCAI = true;
            }
        }

        return esCAI;
    }

    public boolean isEsUZDI() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {
            if ("DIRECTOR TECNICO DE MEDIDAS NO PRIVATIVAS Y PREVENCIÓN".equals(rolUsuario) || "LIDER UZDI".equals(rolUsuario) || "EQUIPO TECNICO PSICOLOGO UZDI".equals(rolUsuario) || "EQUIPO TECNICO JURIDICO UZDI".equals(rolUsuario) || "TRABAJADOR SOCIAL UZDI".equals(rolUsuario) || "ADMINISTRADOR".equals(rolUsuario) || "SUBDIRECTOR".equals(rolUsuario)) {
                esCAI = false;
                esUZDI = true;
            }
        }
        return esUZDI;
    }

    public boolean isTipoRolJuridico() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {
            if (rolUsuario.contains("JURIDICO") || rolUsuario.contains("DIRECTOR") || rolUsuario.contains("LIDER") || rolUsuario.contains("COORDINADOR") || "ADMINISTRADOR".equals(rolUsuario) || "SUBDIRECTOR".equals(rolUsuario)) {
                tipoRolInspector = false;
                tipoRolPsicologo = false;
                tipoRolJuridico = true;
            }
        }
        return tipoRolJuridico;
    }

    public boolean isTipoRolPsicologo() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {

            if (rolUsuario.contains("PSICOLOGO") || rolUsuario.contains("DIRECTOR") || rolUsuario.contains("LIDER") || rolUsuario.contains("COORDINADOR") || "ADMINISTRADOR".equals(rolUsuario) || "SUBDIRECTOR".equals(rolUsuario)) {
                tipoRolInspector = false;
                tipoRolJuridico = false;
                tipoRolPsicologo = true;
            }
        }
        return tipoRolPsicologo;
    }

    public boolean isTipoRolInspector() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {

            if ("INSPECTOR EDUCADOR".equals(rolUsuario) || "COORDINADOR CAI".equals(rolUsuario) || "DIRECTOR TECNICO DE MEDIDAS PRIVATIVAS Y ATENCIÓN".equals(rolUsuario) || "ADMINISTRADOR".equals(rolUsuario) || "SUBDIRECTOR".equals(rolUsuario)) {
                tipoRolPsicologo = false;
                tipoRolJuridico = false;
                tipoRolInspector = true;
            }
        }
        return tipoRolInspector;
    }

    public boolean isPermitidoCrearUsuario() {
        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {

            if ("ADMINISTRADOR".equals(rolUsuario)) {
                permitidoCrearUsuario = true;
            }
        }
        return permitidoCrearUsuario;
    }

    public boolean isPermitidoCrearAdolescenteUDI() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {

            if ("ADMINISTRADOR".equals(rolUsuario) || "LIDER UZDI".equals(rolUsuario) || "DIRECTOR TECNICO DE MEDIDAS NO PRIVATIVAS Y PREVENCIÓN".equals(rolUsuario)) {
                permitidoCrearAdolescenteUDI = true;
            }
        }
        return permitidoCrearAdolescenteUDI;
    }

    public boolean isPermitidoCrearAdolescenteCAI() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {

            if ("ADMINISTRADOR".equals(rolUsuario) || "COORDINADOR CAI".equals(rolUsuario) || "DIRECTOR TECNICO DE MEDIDAS PRIVATIVAS Y ATENCIÓN".equals(rolUsuario) || rolUsuario.equals("EQUIPO TECNICO JURIDICO CAI") || rolUsuario.equals("INSPECTOR EDUCADOR")) {
                permitidoCrearAdolescenteCAI = true;
            }
        }
        return permitidoCrearAdolescenteCAI;
    }

    public String validarTallerPsicologia() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {

            String tipoTaller = "PSICOLOGIA";
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipoTaller", tipoTaller);

            if (rolUsuario.equals("ADMINISTRADOR")) {
                return enlaces.PATH_TALLER_CREAR_ADMINISTRADOR + "?faces-redirect=true";
            } else {
                return enlaces.PATH_TALLER_CREAR_USER + "?faces-redirect=true";
            }

        } else {
            return enlaces.PATH_ERROR;
        }

    }

    public String validarTallerJuridico() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {

            String tipoTaller = "JURÍDICO";
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipoTaller", tipoTaller);

            if (rolUsuario.equals("ADMINISTRADOR")) {
                return enlaces.PATH_TALLER_CREAR_ADMINISTRADOR + "?faces-redirect=true";
            } else {
                return enlaces.PATH_TALLER_CREAR_USER + "?faces-redirect=true";
            }

        } else {
            return enlaces.PATH_ERROR;
        }

    }

    public String validarTallerInspectorEducador() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {
            String tipoTaller = "INSPECTOR EDUCADOR";
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipoTaller", tipoTaller);

            if (rolUsuario.equals("ADMINISTRADOR")) {
                return enlaces.PATH_TALLER_CREAR_ADMINISTRADOR + "?faces-redirect=true";
            } else {
                return enlaces.PATH_TALLER_CREAR_USER + "?faces-redirect=true";
            }

        } else {
            return enlaces.PATH_ERROR;
        }
    }

    public String gestionarTaller() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {

            if ("ADMINISTRADOR".equals(rolUsuario)) {
                return enlaces.PATH_PANEL_TALLER_ADMINISTRADOR + "?faces-redirect=true";
            } else {
                return enlaces.PATH_PANEL_TALLER_USER + "?faces-redirect=true";
            }
        } else {
            return enlaces.PATH_ERROR + "?faces-redirect=true";
        }
    }

    public String gestionarInforme() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {

            if ("ADMINISTRADOR".equals(rolUsuario)) {
                return enlaces.PATH_PANEL_INFORME_ADMINISTRADOR + "?faces-redirect=true";
            } else {
                return enlaces.PATH_PANEL_INFORME_USER + "?faces-redirect=true";
            }
        } else {
            return enlaces.PATH_ERROR + "?faces-redirect=true";
        }
    }

    public String validarGestionInformacionAdolescenteUZDI() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {

            if ("ADMINISTRADOR".equals(rolUsuario)) {
                return enlaces.PATH_PANEL_UDI_ADMINISTRADOR + "?faces-redirect=true";
            } else {

                return enlaces.PATH_PANEL_UDI_USER + "?faces-redirect=true";
            }
        } else {
            return enlaces.PATH_ERROR + "?faces-redirect=true";
        }
    }

    public String validarGestionInformacionAdolescenteCAI() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {

            if ("ADMINISTRADOR".equals(rolUsuario)) {
                return enlaces.PATH_PANEL_CAI_ADMINISTRADOR + "?faces-redirect=true";
            } else {

                return enlaces.PATH_PANEL_CAI_USER + "?faces-redirect=true";
            }
        } else {
            return enlaces.PATH_ERROR + "?faces-redirect=true";
        }
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return enlaces.PATH_LOGIN + "?faces-redirect=true";
    }
}
