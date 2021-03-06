package epn.edu.ec.controlador;

import epn.edu.ec.modelo.Usuario;
import epn.edu.ec.utilidades.Constantes;
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
    private boolean permitidoVerReportes;
    private String usuario;

    @PostConstruct
    public void init() {

        enlaces = new EnlacesPrograma();
        permisosUsuario = new PermisosUsuario();
        
        Usuario usuarioLoginAux = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");

        if (usuarioLoginAux != null) {
            usuario=usuarioLoginAux.getNombres()+" "+usuarioLoginAux.getApellidos()+"  ";
        }
    
    }

    public String getUsuario() {
        return usuario;
    }

    public boolean isTipoRolAdministrador() {
        String rolUsuario = permisosUsuario.RolUsuario();
        if (rolUsuario != null) {
            if (Constantes.ROL_ADMINISTRADOR.equals(rolUsuario)) {
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
            if (Constantes.ROL_ADMINISTRADOR.equals(rolUsuario) || Constantes.ROL_SUBDIRECTOR.equals(rolUsuario) || Constantes.ROL_DIRECTOR_CAI.equals(rolUsuario) || Constantes.ROL_COORDINADOR_CAI.equals(rolUsuario) || Constantes.ROL_DIRECTOR_UZDI.equals(rolUsuario) || Constantes.ROL_LIDER_UZDI.equals(rolUsuario)) {
                tipoRolInspector = false;
                tipoRolPsicologo = false;
                tipoRolJuridico = false;
                tipoRolAdministrador = true;
                tipoRolAdministradorOSubdirector = true;
            }
        }
        return tipoRolAdministradorOSubdirector;
    }

    public boolean isEsCAI() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {
            if (Constantes.ROL_DIRECTOR_CAI.equals(rolUsuario) || Constantes.ROL_COORDINADOR_CAI.equals(rolUsuario) || Constantes.ROL_PSICOLOGO_CAI.equals(rolUsuario) || Constantes.ROL_JURIDICO_CAI.equals(rolUsuario) || Constantes.ROL_TRABAJADOR_SOCIAL_CAI.equals(rolUsuario) || Constantes.ROL_INSPECTOR_EDUCADOR.equals(rolUsuario) || Constantes.ROL_ADMINISTRADOR.equals(rolUsuario) || Constantes.ROL_SUBDIRECTOR.equals(rolUsuario)) {
                esUZDI = false;
                esCAI = true;
            }
        }

        return esCAI;
    }

    public boolean isEsUZDI() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {
            if (Constantes.ROL_DIRECTOR_UZDI.equals(rolUsuario) || Constantes.ROL_LIDER_UZDI.equals(rolUsuario) || Constantes.ROL_PSICOLOGO_UZDI.equals(rolUsuario) || Constantes.ROL_JURIDICO_UZDI.equals(rolUsuario) || Constantes.ROL_TRABAJADOR_SOCIAL_UZDI.equals(rolUsuario) || Constantes.ROL_ADMINISTRADOR.equals(rolUsuario) || Constantes.ROL_SUBDIRECTOR.equals(rolUsuario)) {
                esCAI = false;
                esUZDI = true;
            }
        }
        return esUZDI;
    }

    public boolean isTipoRolJuridico() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {
            if (rolUsuario.contains("JURIDICO") || rolUsuario.contains("DIRECTOR") || rolUsuario.contains("LIDER") || rolUsuario.contains("COORDINADOR") || Constantes.ROL_ADMINISTRADOR.equals(rolUsuario) || Constantes.ROL_SUBDIRECTOR.equals(rolUsuario)) {
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

            if (rolUsuario.contains("PSICOLOGO") || rolUsuario.contains("DIRECTOR") || rolUsuario.contains("LIDER") || rolUsuario.contains("COORDINADOR") || Constantes.ROL_ADMINISTRADOR.equals(rolUsuario) || Constantes.ROL_SUBDIRECTOR.equals(rolUsuario)) {
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

            if (Constantes.ROL_INSPECTOR_EDUCADOR.equals(rolUsuario) || Constantes.ROL_COORDINADOR_CAI.equals(rolUsuario) || Constantes.ROL_DIRECTOR_CAI.equals(rolUsuario) || Constantes.ROL_ADMINISTRADOR.equals(rolUsuario) || Constantes.ROL_SUBDIRECTOR.equals(rolUsuario)) {
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

            if (Constantes.ROL_ADMINISTRADOR.equals(rolUsuario)) {
                permitidoCrearUsuario = true;
            }
        }
        return permitidoCrearUsuario;
    }

    public boolean isPermitidoCrearAdolescenteUDI() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {

            if (Constantes.ROL_ADMINISTRADOR.equals(rolUsuario) || Constantes.ROL_SUBDIRECTOR.equals(rolUsuario) || Constantes.ROL_LIDER_UZDI.equals(rolUsuario) || Constantes.ROL_DIRECTOR_UZDI.equals(rolUsuario) || Constantes.ROL_JURIDICO_UZDI.equals(rolUsuario)) {
                permitidoCrearAdolescenteUDI = true;
            }
        }
        return permitidoCrearAdolescenteUDI;
    }

    public boolean isPermitidoCrearAdolescenteCAI() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {

            if (Constantes.ROL_ADMINISTRADOR.equals(rolUsuario)|| Constantes.ROL_SUBDIRECTOR.equals(rolUsuario) || Constantes.ROL_COORDINADOR_CAI.equals(rolUsuario) || Constantes.ROL_DIRECTOR_CAI.equals(rolUsuario) || rolUsuario.equals(Constantes.ROL_JURIDICO_CAI) || rolUsuario.equals(Constantes.ROL_INSPECTOR_EDUCADOR)) {
                permitidoCrearAdolescenteCAI = true;
            }
        }
        return permitidoCrearAdolescenteCAI;
    }

    public boolean isPermitidoVerReportes() {
        String rolUsuario = permisosUsuario.RolUsuario();
        if (rolUsuario != null) {
            if (Constantes.ROL_ADMINISTRADOR.equals(rolUsuario) || Constantes.ROL_SUBDIRECTOR.equals(rolUsuario) || Constantes.ROL_DIRECTOR_CAI.equals(rolUsuario) || Constantes.ROL_COORDINADOR_CAI.equals(rolUsuario) || Constantes.ROL_DIRECTOR_UZDI.equals(rolUsuario) || Constantes.ROL_LIDER_UZDI.equals(rolUsuario)) {
                permitidoVerReportes = true;
            }
        }
        return permitidoVerReportes;
    }

    public String validarTallerPsicologia() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {

            String tipoTaller = Constantes.TIPO_TALLER_PSICOLOGIA;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipoTaller", tipoTaller);

            if (rolUsuario.equals(Constantes.ROL_ADMINISTRADOR)) {
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

            String tipoTaller = Constantes.TIPO_TALLER_JURIDICO;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipoTaller", tipoTaller);

            if (rolUsuario.equals(Constantes.ROL_ADMINISTRADOR)) {
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
            String tipoTaller = Constantes.TIPO_TALLER_INSPECTOR_EDUCADOR;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipoTaller", tipoTaller);

            if (rolUsuario.equals(Constantes.ROL_ADMINISTRADOR)) {
                return enlaces.PATH_TALLER_CREAR_ADMINISTRADOR + "?faces-redirect=true";
            } else {
                return enlaces.PATH_TALLER_CREAR_USER + "?faces-redirect=true";
            }

        } else {
            return enlaces.PATH_ERROR;
        }
    }
    

    public String gestionarTallerJuridico() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {

            String tipoTallerSeleccionadoMenu=Constantes.TIPO_TALLER_JURIDICO;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipoTallerSeleccionadoMenu", tipoTallerSeleccionadoMenu);
            
            if (Constantes.ROL_ADMINISTRADOR.equals(rolUsuario)) {
                return enlaces.PATH_PANEL_TALLER_ADMINISTRADOR + "?faces-redirect=true";
            } else {
                return enlaces.PATH_PANEL_TALLER_USER + "?faces-redirect=true";
            }
        } else {
            return enlaces.PATH_ERROR + "?faces-redirect=true";
        }
    }
    
    public String gestionarTallerPsicologo() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {

            String tipoTallerSeleccionadoMenu=Constantes.TIPO_TALLER_PSICOLOGIA;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipoTallerSeleccionadoMenu", tipoTallerSeleccionadoMenu);
            
            if (Constantes.ROL_ADMINISTRADOR.equals(rolUsuario)) {
                return enlaces.PATH_PANEL_TALLER_ADMINISTRADOR + "?faces-redirect=true";
            
            }else {
                return enlaces.PATH_PANEL_TALLER_USER + "?faces-redirect=true";
            }
        } else {
            return enlaces.PATH_ERROR + "?faces-redirect=true";
        }
    }
    
    public String gestionarTallerInspectorEducador() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {

            String tipoTallerSeleccionadoMenu=Constantes.TIPO_TALLER_INSPECTOR_EDUCADOR;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipoTallerSeleccionadoMenu", tipoTallerSeleccionadoMenu);
            
            if (Constantes.ROL_ADMINISTRADOR.equals(rolUsuario)) {
                return enlaces.PATH_PANEL_TALLER_ADMINISTRADOR + "?faces-redirect=true";
            } else {
                return enlaces.PATH_PANEL_TALLER_USER + "?faces-redirect=true";
            }
        } else {
            return enlaces.PATH_ERROR + "?faces-redirect=true";
        }
    }

    
    public String gestionarInformePsicologo() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {

            String tipoTallerSeleccionadoMenu=Constantes.TIPO_TALLER_PSICOLOGIA;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipoTallerSeleccionadoMenu", tipoTallerSeleccionadoMenu);
            
            if (Constantes.ROL_ADMINISTRADOR.equals(rolUsuario)) {
                return enlaces.PATH_PANEL_INFORME_ADMINISTRADOR + "?faces-redirect=true";
            } else {
                return enlaces.PATH_PANEL_INFORME_USER + "?faces-redirect=true";
            }
        } else {
            return enlaces.PATH_ERROR + "?faces-redirect=true";
        }
    }
    
    public String gestionarInformeJuridico() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {

            String tipoTallerSeleccionadoMenu=Constantes.TIPO_TALLER_JURIDICO;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipoTallerSeleccionadoMenu", tipoTallerSeleccionadoMenu);
            
            if (Constantes.ROL_ADMINISTRADOR.equals(rolUsuario)) {
                return enlaces.PATH_PANEL_INFORME_ADMINISTRADOR + "?faces-redirect=true";
            } else {
                return enlaces.PATH_PANEL_INFORME_USER + "?faces-redirect=true";
            }
        } else {
            return enlaces.PATH_ERROR + "?faces-redirect=true";
        }
    }
    
    public String gestionarInformeInspectorEducador() {

        String rolUsuario = permisosUsuario.RolUsuario();

        if (rolUsuario != null) {

            String tipoTallerSeleccionadoMenu=Constantes.TIPO_TALLER_INSPECTOR_EDUCADOR;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipoTallerSeleccionadoMenu", tipoTallerSeleccionadoMenu);
            
            if (Constantes.ROL_ADMINISTRADOR.equals(rolUsuario)) {
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

            if (Constantes.ROL_ADMINISTRADOR.equals(rolUsuario)) {
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

            if (Constantes.ROL_ADMINISTRADOR.equals(rolUsuario)) {
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
