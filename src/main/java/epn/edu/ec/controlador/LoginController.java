/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.controlador;

import epn.edu.ec.modelo.Usuario;
import epn.edu.ec.servicios.LoginServicio;
import epn.edu.ec.utilidades.EnlacesPrograma;
import java.io.Serializable;
import javax.annotation.PostConstruct;
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
public class LoginController implements Serializable{

    private Usuario usuario;
    private LoginServicio servicio;
    private EnlacesPrograma enlaces;
    
    @PostConstruct
    public void init(){
        usuario= new Usuario();
        servicio= new LoginServicio();
        enlaces= new EnlacesPrograma();
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    public void cifrarUsuario(){
        String user=usuario.getUsuario();
        user = DigestUtils.md5Hex(user);
        System.out.println("USUARIO:"+ user);
        
        usuario.setUsuario(user.toUpperCase());
    }
    
    public void cifrarContraseña(){
        String password=usuario.getContraseña();
        password=DigestUtils.md5Hex(password);
        System.out.println("PASSWORD:"+ password);

        usuario.setContraseña(password.toUpperCase());
    }
    
    public String loguerUsuario(){
        
        //cifrarUsuario();
        //cifrarContraseña();
        
        Usuario usuarioLogueado= servicio.loguearUsuario(usuario);
        
        if(usuarioLogueado!=null){
            
            String rolUsuario=usuarioLogueado.getIdRolUsuarioCentro().getIdRol().getRol();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogin", usuarioLogueado);
            
            if ("ADMINISTRADOR".equals(rolUsuario)) {
                return enlaces.PATH_PANEL_TALLER_ADMINISTRADOR + "?faces-redirect=true";
            } else {
                return enlaces.PATH_PANEL_TALLER + "?faces-redirect=true";
            }           
        }
        else{
            return null;
        }
        
    }

    
}
