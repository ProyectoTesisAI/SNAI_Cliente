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
@Named(value = "validarAccesoControlador")
@ViewScoped
public class ValidarAccesoControlador implements Serializable{
    
    private final EnlacesPrograma enlaces;
    
    public ValidarAccesoControlador(){
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
            
        }catch(IOException e){
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
            
        }catch(IOException e){
            FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.PATH_ERROR);
        }
    }
    
    
//    MÉTODOS PARA VALIDAR EL ACCESO AL PANEL CREAR UZDI DE ACUERDO AL TIPO DE ROL
    
    public void validarAccesoPanelLiderUzdi() throws IOException{
        
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
            
        }catch(IOException e){
            FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
        }
    }
   
    public void validarAccesoPanelPsicologoUZDI() throws IOException{
        
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
            
        }catch(IOException e){
            FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
        }
    }
   
    public void validarAccesoPanelTrabajadorSocialUZDI() throws IOException{
        
        try{
            
            String rolUsuario=RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals("ADMINISTRADOR") || rolUsuario.equals("EQUIPO TECNICO TRABAJADOR SOCIAL UZDI") ){
                    
                }
                else{
                    FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
                }
            }else{
                FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
            }
            
        }catch(IOException e){
            FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
        }
        
    }
    

    
//    MÉTODOS PARA VALIDAR EL ACCESO AL PANEL CREAR CAI DE ACUERDO AL TIPO DE ROL
    
    public void validarAccesoPanelCoordinadorCAI() throws IOException{
        
        try{
            
            String rolUsuario=RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals("ADMINISTRADOR") || rolUsuario.equals("COORDINADOR CAI") ){
                    
                }
                else{
                    FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
                }
            }else{
                FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
            }
            
        }catch(IOException e){
            FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
        }
        
    }
    
    public void validarAccesoPanelInspectorEducador() throws IOException{
        
        try{
            
            String rolUsuario=RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals("ADMINISTRADOR") || rolUsuario.equals("INSPECTOR EDUCADOR") ){
                    
                }
                else{
                    FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
                }
            }else{
                FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
            }
            
        }catch(IOException e){
            FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
        }
        
    }
    
    public void validarAccesoPanelPsicologoCAI() throws IOException{
        
        try{
            
            String rolUsuario=RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals("ADMINISTRADOR") || rolUsuario.equals("EQUIPO TECNICO PSICOLOGO CAI") ){
                    
                }
                else{
                    FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
                }
            }else{
                FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
            }
            
        }catch(IOException e){
            FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
        }
        
    }

    public void validarAccesoPanelJuridicoCAI() throws IOException{
        
        try{
            
            String rolUsuario=RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals("ADMINISTRADOR") || rolUsuario.equals("EQUIPO TECNICO JURIDICO CAI") ){
                    
                }
                else{
                    FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
                }
            }else{
                FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
            }
            
        }catch(IOException e){
            FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
        }
        
    }
    
    public void validarAccesoPanelTrabjadorSocialCAI() throws IOException{
        
        try{
            
            String rolUsuario=RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals("ADMINISTRADOR") || rolUsuario.equals("EQUIPO TECNICO TRABAJADOR SOCIAL CAI") ){
                    
                }
                else{
                    FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
                }
            }else{
                FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
            }
            
        }catch(IOException e){
            FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
        }
        
    }

    public void validarAccesoPanelCrearEjecucionMedidaCAI() throws IOException{
        
        try{
            
            String rolUsuario=RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals("ADMINISTRADOR") || rolUsuario.equals("COORDINADOR CAI") || rolUsuario.equals("EQUIPO TECNICO JURIDICO CAI")){
                    
                }
                else{
                    FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
                }
            }else{
                FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
            }
            
        }catch(IOException e){
            FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
        }
        
    }
    
    public void validarAccesoPanelVerEjecucionMedidaCAI() throws IOException{
        
        try{
            
            String rolUsuario=RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals("ADMINISTRADOR") || rolUsuario.equals("EQUIPO TECNICO TRABAJADOR SOCIAL CAI") 
                        || rolUsuario.equals("INSPECTOR EDUCADOR") || rolUsuario.equals("EQUIPO TECNICO PSICOLOGO CAI")){
                    
                }
                else{
                    FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
                }
            }else{
                FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
            }
            
        }catch(IOException e){
            FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
        }
        
    }

    public void validarAccesoPanelInformacionMedidaCAI() throws IOException{
        
        try{
            
            String rolUsuario=RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals("ADMINISTRADOR") || rolUsuario.equals("COORDINADOR CAI") ||rolUsuario.equals("EQUIPO TECNICO TRABAJADOR SOCIAL CAI") 
                        || rolUsuario.equals("EQUIPO TECNICO PSICOLOGO CAI") || rolUsuario.equals("EQUIPO TECNICO JURIDICO CAI")){
                    
                }
                else{
                    FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
                }
            }else{
                FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
            }
            
        }catch(IOException e){
            FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
        }
        
    }

}
