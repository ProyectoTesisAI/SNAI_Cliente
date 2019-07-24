/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.controlador;

import epn.edu.ec.modelo.Usuario;
import epn.edu.ec.utilidades.EnlacesPrograma;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author User
 */
@Named(value = "tipoTallerControlador")
@ViewScoped
public class TipoTallerControlador implements Serializable{

    private EnlacesPrograma enlaces;
    
    @PostConstruct
    public void init(){
        enlaces= new EnlacesPrograma();
    }
    
    public String validarTallerPsicologia(){
        String tipoTaller="PSICOLOGIA";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipoTaller", tipoTaller);
        return "/paginas/psicologia/taller_psicologia.com?faces-redirect=true";
    }
    
    public String validarTallerJuridico(){
        String tipoTaller="JURÍDICO";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipoTaller", tipoTaller);
        return "/paginas/psicologia/taller_psicologia.com?faces-redirect=true";
    }
    
    public String validarTallerInspectorEducador(){
        String tipoTaller="INSPECTOR EDUCADOR";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipoTaller", tipoTaller);
        return "/paginas/psicologia/taller_psicologia.com?faces-redirect=true";
    }
    
    public String gestionarTaller(){
        Usuario usuarioAux = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
        String rolUsuario=usuarioAux.getIdRolUsuarioCentro().getIdRol().getRol();
        if(rolUsuario=="ADMINISTRADOR"){
            //return enlaces.PATH_PANEL_TALLER_ADMINISTRADOR+"?faces-redirect=true";
            return "/paginas/admin/taller/panel_taller.com?faces-redirect=true";
        }else{
//            return enlaces.PATH_PANEL_TALLER+"?faces-redirect=true";
            return "/paginas/user/taller/panel_taller.com?faces-redirect=true";
        }
    }
}
