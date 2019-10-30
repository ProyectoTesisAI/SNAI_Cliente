/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.controlador;

import epn.edu.ec.modelo.Usuario;
import epn.edu.ec.servicios.LoginServicio;
import epn.edu.ec.utilidades.Constantes;
import epn.edu.ec.utilidades.EnlacesPrograma;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author User
 */
@Named(value = "loginController")
@ViewScoped
public class LoginController implements Serializable {

    private Usuario usuario;
    private LoginServicio servicio;
    private EnlacesPrograma enlaces;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        servicio = new LoginServicio();
        enlaces = new EnlacesPrograma();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String cifrarContraseña() {
        String password = usuario.getContraseña();
        password = DigestUtils.sha256Hex(password);
        return password;
    }

    public String loguerUsuario() {

        Usuario user = new Usuario();

        user.setUsuario(usuario.getUsuario());
        
        //cifro la contraseña mediante SHA256
        String contraseña = cifrarContraseña();
        user.setContraseña(contraseña);

        Usuario usuarioLogueado = servicio.loguearUsuario(user);

        if (usuarioLogueado != null) {

            String rolUsuario = usuarioLogueado.getIdRolUsuarioCentro().getIdRol().getRol();
            
            //creo una sesión auxiliar denominada usuarioLogin, donde almaceno de forma temporal la información del usuario
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogin", usuarioLogueado);

            if (Constantes.ROL_ADMINISTRADOR.equals(rolUsuario)) {
                return enlaces.PATH_PANEL_TALLER_ADMINISTRADOR + "?faces-redirect=true";
            } else {
                if (rolUsuario.equals(Constantes.ROL_TRABAJADOR_SOCIAL_CAI)) {
                
                    return enlaces.PATH_PANEL_CAI_USER + "?faces-redirect=true";
                
                } else if (rolUsuario.equals(Constantes.ROL_TRABAJADOR_SOCIAL_UZDI)) {
                
                    return enlaces.PATH_PANEL_UDI_USER + "?faces-redirect=true";
                
                } else {
                
                    return enlaces.PATH_PANEL_TALLER_USER + "?faces-redirect=true";
                }
            }
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "USUARIO O CONTRASEÑA INCORRECTA", "Error"));
            return null;
        }

    }

}
