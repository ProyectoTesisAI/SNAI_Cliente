package epn.edu.ec.controlador;

import epn.edu.ec.modelo.Usuario;
import epn.edu.ec.utilidades.EnlacesPrograma;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "administracionMenuControlador")
@ViewScoped
public class AdministracionMenuControlador implements Serializable {

    private EnlacesPrograma enlaces;
    private boolean esCAI;
    private boolean esUZDI;
    private String rolUsuario;
    private boolean tipoRolJuridico;
    private boolean tipoRolPsicologo;
    private boolean tipoRolInspector;
    private Usuario usuarioLogin;

    @PostConstruct
    public void init() {
        enlaces = new EnlacesPrograma();
        usuarioLogin=new Usuario();
        usuarioLogin = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
        rolUsuario=usuarioLogin.getIdRolUsuarioCentro().getIdRol().getRol();
    }

    public boolean isEsCAI() {
        if ("COORDINADOR CAI".equals(rolUsuario) || "EQUIPO TECNICO PSICOLOGO CAI".equals(rolUsuario) || "EQUIPO TECNICO JURIDICO CAI".equals(rolUsuario) || "INSPECTOR EDUCADOR".equals(rolUsuario) || "ADMINISTRADOR".equals(rolUsuario) || "SUBDIRECTOR".equals(rolUsuario)) {
            esUZDI=false;
            return esCAI=true;
        }
        return esCAI;
    }

    public boolean isEsUZDI() {
        if ("COORDINADOR/LIDER UZDI".equals(rolUsuario) || "EQUIPO TECNICO PSICOLOGO UZDI".equals(rolUsuario) || "EQUIPO TECNICO JURIDICO UZDI".equals(rolUsuario)|| "ADMINISTRADOR".equals(rolUsuario) || "SUBDIRECTOR".equals(rolUsuario)) {
            esCAI=false;
            return esUZDI=true;
        }
        return esUZDI;
    }

    public boolean isTipoRolJuridico() {
        if(rolUsuario.contains("JURIDICO") || rolUsuario.contains("COORDINADOR") || "ADMINISTRADOR".equals(rolUsuario) || "SUBDIRECTOR".equals(rolUsuario)){
            tipoRolInspector=false;
            tipoRolPsicologo=false;
            return tipoRolJuridico=true;
        }
        return tipoRolJuridico;
    }

    public boolean isTipoRolPsicologo() {
        if(rolUsuario.contains("PSICOLOGO") || rolUsuario.contains("COORDINADOR") || "ADMINISTRADOR".equals(rolUsuario) || "SUBDIRECTOR".equals(rolUsuario)){
            tipoRolInspector=false;
            tipoRolJuridico=false;
            return tipoRolPsicologo=true;
        }
        return tipoRolPsicologo;
    }

    public boolean isTipoRolInspector() {
        if("INSPECTOR EDUCADOR".equals(rolUsuario) || "COORDINADOR CAI".equals(rolUsuario) || "ADMINISTRADOR".equals(rolUsuario) || "SUBDIRECTOR".equals(rolUsuario)){
            tipoRolPsicologo=false;
            tipoRolJuridico=false;
            return tipoRolInspector=true;
        }
        return tipoRolInspector;
    }

    public Usuario getUsuarioLogin() {
        return usuarioLogin;
    }
    
    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public String validarTallerPsicologia() {
        String tipoTaller = "PSICOLOGIA";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipoTaller", tipoTaller);
        //return "/paginas/psicologia/taller_psicologia.com?faces-redirect=true";
        return enlaces.PATH_TALLER_CREAR + "?faces-redirect=true";
    }

    public String validarTallerJuridico() {
        String tipoTaller = "JURÍDICO";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipoTaller", tipoTaller);
        //return "/paginas/psicologia/taller_psicologia.com?faces-redirect=true";
        return enlaces.PATH_TALLER_CREAR + "?faces-redirect=true";
    }

    public String validarTallerInspectorEducador() {
        String tipoTaller = "INSPECTOR EDUCADOR";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipoTaller", tipoTaller);
        //return "/paginas/psicologia/taller_psicologia.com?faces-redirect=true";
        return enlaces.PATH_TALLER_CREAR + "?faces-redirect=true";
    }

    public String gestionarTaller() {
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
        String rolUsuario = usuarioAux.getIdRolUsuarioCentro().getIdRol().getRol();

        if ("ADMINISTRADOR".equals(rolUsuario)) {
            return enlaces.PATH_PANEL_TALLER_ADMINISTRADOR + "?faces-redirect=true";
        } else {
            return enlaces.PATH_PANEL_TALLER + "?faces-redirect=true";
        }
    }

    public String gestionarInforme() {
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
        String rolUsuario = usuarioAux.getIdRolUsuarioCentro().getIdRol().getRol();

        if ("ADMINISTRADOR".equals(rolUsuario)) {
            return enlaces.PATH_PANEL_INFORME_ADMINISTRADOR + "?faces-redirect=true";
        } else {
            return enlaces.PATH_PANEL_INFORME + "?faces-redirect=true";
        }
    }

}
