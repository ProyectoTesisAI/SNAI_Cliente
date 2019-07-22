/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.controlador;

import epn.edu.ec.modelo.Usuario;
import epn.edu.ec.servicios.LoginServicio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author User
 */
@Named(value = "loginController")
@ViewScoped
public class LoginController implements Serializable{

    private Usuario usuario;
    private LoginServicio servicio;

    @PostConstruct
    public void init(){
        usuario= new Usuario();
        servicio= new LoginServicio();
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String loguerUsuario(){
        Usuario usuarioLogueado= servicio.loguearUsuario(usuario);
        
        if(usuarioLogueado!=null){
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogin", usuarioLogueado);
            return "/paginas/psicologia/panel_taller_psicologia.com?faces-redirect=true";
        }
        else{
            return null;
        }
        
    }

    
}
