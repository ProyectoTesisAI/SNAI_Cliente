package epn.edu.ec.controlador;

import epn.edu.ec.modelo.Usuario;
import epn.edu.ec.utilidades.EnlacesPrograma;
import epn.edu.ec.utilidades.PermisosUsuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private boolean permitidoCrearAdolescenteUDI;
    private boolean permitidoCrearAdolescenteCAI;
    
    @PostConstruct
    public void init() {
       
        enlaces = new EnlacesPrograma();
        permisosUsuario= new PermisosUsuario();
    }
    
    public boolean isEsCAI() {
        
        String rolUsuario=permisosUsuario.RolUsuario();
        
        if(rolUsuario!=null){
            if ("COORDINADOR CAI".equals(rolUsuario) || "EQUIPO TECNICO PSICOLOGO CAI".equals(rolUsuario) || "EQUIPO TECNICO JURIDICO CAI".equals(rolUsuario) || "INSPECTOR EDUCADOR".equals(rolUsuario) || "ADMINISTRADOR".equals(rolUsuario) || "SUBDIRECTOR".equals(rolUsuario)) {
                esUZDI=false;
                esCAI=true;
            }
        }
        
        return esCAI;
    }

    public boolean isEsUZDI() {
        
        String rolUsuario=permisosUsuario.RolUsuario();
        
        if(rolUsuario!=null){
            if ("COORDINADOR/LIDER UZDI".equals(rolUsuario) || "EQUIPO TECNICO PSICOLOGO UZDI".equals(rolUsuario) || "EQUIPO TECNICO JURIDICO UZDI".equals(rolUsuario)|| "ADMINISTRADOR".equals(rolUsuario) || "SUBDIRECTOR".equals(rolUsuario)) {
                esCAI=false;
                esUZDI=true;
            }           
        }
        return esUZDI;
    }

    public boolean isTipoRolJuridico() {
        
        String rolUsuario=permisosUsuario.RolUsuario();
        
        if (rolUsuario != null) {
            if (rolUsuario.contains("JURIDICO") || rolUsuario.contains("COORDINADOR") || "ADMINISTRADOR".equals(rolUsuario) || "SUBDIRECTOR".equals(rolUsuario)) {
                tipoRolInspector = false;
                tipoRolPsicologo = false;
                tipoRolJuridico = true;
            }
        }
        return tipoRolJuridico;
    }

    public boolean isTipoRolPsicologo() {
        
        String rolUsuario=permisosUsuario.RolUsuario();
        
        if (rolUsuario != null) {

            if(rolUsuario.contains("PSICOLOGO") || rolUsuario.contains("COORDINADOR") || "ADMINISTRADOR".equals(rolUsuario) || "SUBDIRECTOR".equals(rolUsuario)){
                tipoRolInspector=false;
                tipoRolJuridico=false;
                tipoRolPsicologo=true;
            }
        }
        return tipoRolPsicologo;
    }

    public boolean isTipoRolInspector() {
        
        String rolUsuario=permisosUsuario.RolUsuario();
        
        if (rolUsuario != null) {
        
            if("INSPECTOR EDUCADOR".equals(rolUsuario) || "COORDINADOR CAI".equals(rolUsuario) || "ADMINISTRADOR".equals(rolUsuario) || "SUBDIRECTOR".equals(rolUsuario)){
                tipoRolPsicologo=false;
                tipoRolJuridico=false;
                tipoRolInspector=true;
            }
        }
        return tipoRolInspector;
    }

    public boolean isPermitidoCrearAdolescenteUDI() {
        
        String rolUsuario=permisosUsuario.RolUsuario();
        
        if (rolUsuario != null) {
        
            if("ADMINISTRADOR".equals(rolUsuario) || "COORDINADOR/LIDER UZDI".equals(rolUsuario)){
                permitidoCrearAdolescenteUDI=true;
            }
        }
        return permitidoCrearAdolescenteUDI;
    }

    public boolean isPermitidoCrearAdolescenteCAI() {
        
        String rolUsuario=permisosUsuario.RolUsuario();
        
        if (rolUsuario != null) {
        
            if("ADMINISTRADOR".equals(rolUsuario) || "COORDINADOR CAI".equals(rolUsuario) || rolUsuario.equals("EQUIPO TECNICO JURIDICO CAI") || rolUsuario.equals("INSPECTOR EDUCADOR")){
                permitidoCrearAdolescenteCAI=true;
            }
        }
        return permitidoCrearAdolescenteCAI;
    }
    
    
    
    public String validarTallerPsicologia() {
        
        String tipoTaller = "PSICOLOGIA";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipoTaller", tipoTaller);
        return enlaces.PATH_TALLER_CREAR + "?faces-redirect=true";
    }

    public String validarTallerJuridico() {
        
        String tipoTaller = "JURÍDICO";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipoTaller", tipoTaller);
        return enlaces.PATH_TALLER_CREAR + "?faces-redirect=true";
    }

    public String validarTallerInspectorEducador() {
        
        String tipoTaller = "INSPECTOR EDUCADOR";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipoTaller", tipoTaller);
        return enlaces.PATH_TALLER_CREAR + "?faces-redirect=true";
    }

    public String gestionarTaller() {
        
        String rolUsuario=permisosUsuario.RolUsuario();
        
        if (rolUsuario != null) {

            if ("ADMINISTRADOR".equals(rolUsuario)) {
                return enlaces.PATH_PANEL_TALLER_ADMINISTRADOR + "?faces-redirect=true";
            } else {
                return enlaces.PATH_PANEL_TALLER + "?faces-redirect=true";
            }
        }
        else{
            return enlaces.PATH_ERROR+"?faces-redirect=true";
        }
    }

    public String gestionarInforme() {
        
        String rolUsuario=permisosUsuario.RolUsuario();
        
        if (rolUsuario != null) {
        
            if ("ADMINISTRADOR".equals(rolUsuario)) {
                return enlaces.PATH_PANEL_INFORME_ADMINISTRADOR + "?faces-redirect=true";
            } else {
                return enlaces.PATH_PANEL_INFORME + "?faces-redirect=true";
            }
        }
        else{
            return enlaces.PATH_ERROR+"?faces-redirect=true";
        }
    }
    
    public String validarGestionInformacionAdolescenteUZDI(){
    
        String rolUsuario=permisosUsuario.RolUsuario();
        
        if (rolUsuario != null) {
        
            if ("ADMINISTRADOR".equals(rolUsuario)){
                return enlaces.PATH_PANEL_UDI_ADMINISTRADOR+"?faces-redirect=true";
            }
            else{
            
                return enlaces.PATH_PANEL_UDI_USER+"?faces-redirect=true";
            }
        }
        else{
            return enlaces.PATH_ERROR+"?faces-redirect=true";
        }
    }
    
    public String validarGestionInformacionAdolescenteCAI(){
    
        String rolUsuario=permisosUsuario.RolUsuario();
        
        if (rolUsuario != null) {
        
            if ("ADMINISTRADOR".equals(rolUsuario)){
                return enlaces.PATH_PANEL_CAI_ADMIN+"?faces-redirect=true";
            }
            else{
            
                return enlaces.PATH_PANEL_CAI_USER+"?faces-redirect=true";
            }
        }
        else{
            return enlaces.PATH_ERROR+"?faces-redirect=true";
        }
    }
}
