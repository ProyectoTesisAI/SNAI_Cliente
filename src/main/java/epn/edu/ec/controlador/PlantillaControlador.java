/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.controlador;

import epn.edu.ec.modelo.Usuario;
import epn.edu.ec.utilidades.EnlacesPrograma;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author User
 */
@Named(value = "plantillaControlador")
@ViewScoped
public class PlantillaControlador implements Serializable{
    
    private EnlacesPrograma enlaces;
    
    public PlantillaControlador(){
        enlaces= new EnlacesPrograma();
    }
    
    private String RolUsuario(){
    
        Usuario usuarioLoginAux = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");

        if(usuarioLoginAux!=null){
            String rolUsuario=usuarioLoginAux.getIdRolUsuarioCentro().getIdRol().getRol();
            
            if(rolUsuario!=null){
                return rolUsuario;
            }
            else{
                return null;
            }
        }   
        else{
            return null;
        }
    }

    public void verificarSesionAdministrador() throws IOException{
    
        try{
            String rolUsuario=RolUsuario();
        
            if(rolUsuario!=null){
      
                if(!rolUsuario.equals("ADMINISTRADOR")){
                    
                    FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
                }
            }else{
                FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
            }
            
        }catch(Exception e){
            FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.PATH_ERROR);
        }
    }


    public void verificarSesionUsuario() throws IOException{
    
        try{
            
            String rolUsuario=RolUsuario();
        
            if(rolUsuario!=null){
                
            }else{
                FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
            }
            
        }catch(Exception e){
            FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.PATH_ERROR);
        }
    }
    
    public void validarLiderUzdi() throws IOException{
        
        try{
            
            String rolUsuario=RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals("ADMINISTRADOR") || rolUsuario.equals("COORDINADOR/LIDER UZDI") ){
                }
                else{
                    FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
                }
            }else{
                FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
            }
            
        }catch(Exception e){
            FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
        }
    }
   
    public void validarPsicologo() throws IOException{
        
        try{
            
            String rolUsuario=RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals("ADMINISTRADOR") || rolUsuario.equals("EQUIPO TECNICO PSICOLOGO UZDI") ){
                    
                }
                else{
                    FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
                }
            }else{
                FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
            }
            
        }catch(Exception e){
            FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
        }
    }
   
    public void validarTrabajadorSocial() throws IOException{
        
        try{
            
            String rolUsuario=RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals("ADMINISTRADOR") || rolUsuario.equals("TRABAJADOR SOCIAL") ){
                    
                }
                else{
                    FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
                }
            }else{
                FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
            }
            
        }catch(Exception e){
            FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
        }
        
    }

}
