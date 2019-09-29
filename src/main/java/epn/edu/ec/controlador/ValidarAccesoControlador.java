/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.controlador;

import epn.edu.ec.utilidades.Constantes;
import epn.edu.ec.utilidades.EnlacesPrograma;
import epn.edu.ec.utilidades.PermisosUsuario;
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
    private final PermisosUsuario permisos;
    
    public ValidarAccesoControlador(){
        enlaces= new EnlacesPrograma();
        permisos=new PermisosUsuario();
    }
   

    public void verificarSesionAdministrador() throws IOException{
    
        try{
            
            String rolUsuario=permisos.RolUsuario();
        
            if(rolUsuario!=null){
      
                if(!rolUsuario.equals(Constantes.ROL_ADMINISTRADOR)){
                    
                    FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
                }
            }else{
                FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
            }
            
        }catch(IOException e){
            FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
        }
    }

    public void verificarSesionUsuario() throws IOException{
    
        try{
            
            String rolUsuario=permisos.RolUsuario();
        
            if(rolUsuario!=null){
                
            }else{
                FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
            }
            
        }catch(IOException e){
            FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
        }
    }
    
    
//    MÉTODOS PARA VALIDAR EL ACCESO AL PANEL CREAR UZDI DE ACUERDO AL TIPO DE ROL
    
    public void validarAccesoPanelLiderUzdi() throws IOException{
        
        try{
            
            String rolUsuario=permisos.RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals(Constantes.ROL_ADMINISTRADOR) || rolUsuario.equals(Constantes.ROL_SUBDIRECTOR) || rolUsuario.equals(Constantes.ROL_DIRECTOR_UZDI) || rolUsuario.equals(Constantes.ROL_LIDER_UZDI) ){
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
            
            String rolUsuario=permisos.RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals(Constantes.ROL_ADMINISTRADOR) || rolUsuario.equals(Constantes.ROL_SUBDIRECTOR) || rolUsuario.equals(Constantes.ROL_LIDER_UZDI) || rolUsuario.equals(Constantes.ROL_DIRECTOR_UZDI) || rolUsuario.equals(Constantes.ROL_PSICOLOGO_UZDI) ){
                    
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
    
    public void validarAccesoPanelJuridicoUzdi() throws IOException{
        
        try{
            
            String rolUsuario=permisos.RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals(Constantes.ROL_ADMINISTRADOR) || rolUsuario.equals(Constantes.ROL_SUBDIRECTOR) || rolUsuario.equals(Constantes.ROL_LIDER_UZDI) || rolUsuario.equals(Constantes.ROL_DIRECTOR_UZDI) || rolUsuario.equals(Constantes.ROL_JURIDICO_UZDI) ){
                    
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
            
            String rolUsuario=permisos.RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals(Constantes.ROL_ADMINISTRADOR) || rolUsuario.equals(Constantes.ROL_SUBDIRECTOR) || rolUsuario.equals(Constantes.ROL_LIDER_UZDI) || rolUsuario.equals(Constantes.ROL_DIRECTOR_UZDI) || rolUsuario.equals(Constantes.ROL_TRABAJADOR_SOCIAL_UZDI) ){
                    
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
            
            String rolUsuario=permisos.RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals(Constantes.ROL_ADMINISTRADOR) || rolUsuario.equals(Constantes.ROL_SUBDIRECTOR) || rolUsuario.equals(Constantes.ROL_COORDINADOR_CAI) || rolUsuario.equals(Constantes.ROL_DIRECTOR_CAI) ){
                    
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
            
            String rolUsuario=permisos.RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals(Constantes.ROL_ADMINISTRADOR) || rolUsuario.equals(Constantes.ROL_SUBDIRECTOR) || rolUsuario.equals(Constantes.ROL_INSPECTOR_EDUCADOR) ){
                    
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
            
            String rolUsuario=permisos.RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals(Constantes.ROL_ADMINISTRADOR) || rolUsuario.equals(Constantes.ROL_SUBDIRECTOR) || rolUsuario.equals(Constantes.ROL_PSICOLOGO_CAI) ){
                    
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
            
            String rolUsuario=permisos.RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals(Constantes.ROL_ADMINISTRADOR) || rolUsuario.equals(Constantes.ROL_SUBDIRECTOR) || rolUsuario.equals(Constantes.ROL_JURIDICO_CAI) ){
                    
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
            
            String rolUsuario=permisos.RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals(Constantes.ROL_ADMINISTRADOR) || rolUsuario.equals(Constantes.ROL_SUBDIRECTOR) || rolUsuario.equals(Constantes.ROL_TRABAJADOR_SOCIAL_CAI) ){
                    
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
            
            String rolUsuario=permisos.RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals(Constantes.ROL_ADMINISTRADOR) || rolUsuario.equals(Constantes.ROL_SUBDIRECTOR) || rolUsuario.equals(Constantes.ROL_COORDINADOR_CAI) || rolUsuario.equals(Constantes.ROL_DIRECTOR_CAI) || rolUsuario.equals(Constantes.ROL_JURIDICO_CAI)){
                    
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
            
            String rolUsuario=permisos.RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals(Constantes.ROL_ADMINISTRADOR) || rolUsuario.equals(Constantes.ROL_SUBDIRECTOR) || rolUsuario.equals(Constantes.ROL_TRABAJADOR_SOCIAL_CAI) 
                        || rolUsuario.equals(Constantes.ROL_INSPECTOR_EDUCADOR) || rolUsuario.equals(Constantes.ROL_PSICOLOGO_CAI)){
                    
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
            
            String rolUsuario=permisos.RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals(Constantes.ROL_ADMINISTRADOR) || rolUsuario.equals(Constantes.ROL_SUBDIRECTOR) || rolUsuario.equals(Constantes.ROL_COORDINADOR_CAI) || rolUsuario.equals(Constantes.ROL_DIRECTOR_CAI) ||rolUsuario.equals(Constantes.ROL_TRABAJADOR_SOCIAL_CAI) 
                        || rolUsuario.equals(Constantes.ROL_PSICOLOGO_CAI) || rolUsuario.equals(Constantes.ROL_JURIDICO_CAI)){
                    
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
    
    public void validarAccesoReportes() throws IOException {
        
        try {

            String rolUsuario = permisos.RolUsuario();

            if (rolUsuario != null) {
                if (Constantes.ROL_ADMINISTRADOR.equals(rolUsuario) || Constantes.ROL_SUBDIRECTOR.equals(rolUsuario) || Constantes.ROL_DIRECTOR_CAI.equals(rolUsuario) || Constantes.ROL_COORDINADOR_CAI.equals(rolUsuario) || Constantes.ROL_DIRECTOR_UZDI.equals(rolUsuario) || Constantes.ROL_LIDER_UZDI.equals(rolUsuario)) {
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
                }
            }
            else{
                FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
            }

        } catch (IOException e) {
            FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE + enlaces.PATH_ERROR);
        }
        
        
        
    }

    public String obtenerIndiceActual(){
        String indice="";
        indice = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("indiceActual");
        if(indice==null){
            indice="0";//se redirige a la pestaña inicial
        }else{
            return indice;
        }
        return indice;
    }
}
