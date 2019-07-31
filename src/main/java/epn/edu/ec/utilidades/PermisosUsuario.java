/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.utilidades;

import epn.edu.ec.modelo.Usuario;
import javax.faces.context.FacesContext;

/**
 *
 * @author User
 */
public class PermisosUsuario {
    
    private EnlacesPrograma enlaces;
    
    public PermisosUsuario(){
        enlaces= new EnlacesPrograma();
    }
    
    public String RolUsuario(){
    
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
    
    public String redireccionGestionInformacionUzdi(){
        
        String rolUsuario=RolUsuario();
        
        if (rolUsuario != null) {
        
            if ("ADMINISTRADOR".equals(rolUsuario)){
                return enlaces.PATH_PANEL_CREAR_UDI_ADMINISTRADOR+"?faces-redirect=true";
            }
            else{
            
                if(rolUsuario.equals("COORDINADOR/LIDER UZDI")){
                    return enlaces.PATH_PANEL_CREAR_UDI_LIDER_UZDI+"?faces-redirect=true";
                }
                else if(rolUsuario.equals("EQUIPO TECNICO PSICOLOGO UZDI")){
                    return enlaces.PATH_PANEL_CREAR_UDI_PSICOLOGO+"?faces-redirect=true";
                }
                else if(rolUsuario.equals("TRABAJADOR SOCIAL UZDI")){
                    return enlaces.PATH_PANEL_CREAR_UDI_TRABAJADOR_SOCIAL+"?faces-redirect=true";
                }              
                else{
                    return null;
                }  
            }
        }
        else{
            return enlaces.PATH_ERROR+"?faces-redirect=true";
        }
    }
    
    public String redireccionGestionInformacionCai(){
        
        String rolUsuario=RolUsuario();
        
        if (rolUsuario != null) {
        
            if ("ADMINISTRADOR".equals(rolUsuario)){
                return enlaces.PATH_PANEL_CREAR_CAI_ADMINISTRADOR+"?faces-redirect=true";
            }
            else{
            
                if(rolUsuario.equals("COORDINADOR CAI")){
                    return enlaces.PATH_PANEL_CREAR_CAI_COORDINADOR+"?faces-redirect=true";
                }
                else if(rolUsuario.equals("INSPECTOR EDUCADOR")){
                    return enlaces.PATH_PANEL_CREAR_CAI_INSPECTOR_EDUCADOR+"?faces-redirect=true";
                }
                else if(rolUsuario.equals("EQUIPO TECNICO PSICOLOGO CAI")){
                    return enlaces.PATH_PANEL_CREAR_CAI_PSICOLOGO+"?faces-redirect=true";
                }
                else if(rolUsuario.equals("EQUIPO TECNICO JURIDICO CAI")){
                    return enlaces.PATH_PANEL_CREAR_CAI_JURIDICO+"?faces-redirect=true";
                }
                else if(rolUsuario.equals("TRABAJADOR SOCIAL CAI")){
                    return enlaces.PATH_PANEL_CREAR_CAI_TRABAJADOR_SOCIAL+"?faces-redirect=true";
                }
                else{
                    return null;
                }  
            }
        }
        else{
            return enlaces.PATH_ERROR+"?faces-redirect=true";
        }
    }
    
    public String redireccionEjecucionMedida(){
        
        String rolUsuario=RolUsuario();
        
        if (rolUsuario != null) {
        
            if ("ADMINISTRADOR".equals(rolUsuario)){
                return enlaces.PATH_PANEL_EJECUCION_MEDIDA_CAI_CREAR+"?faces-redirect=true";
            }
            else{
            
                if(rolUsuario.equals("COORDINADOR CAI")){
                    return enlaces.PATH_PANEL_EJECUCION_MEDIDA_CAI_CREAR+"?faces-redirect=true";
                }
                else if(rolUsuario.equals("INSPECTOR EDUCADOR")){
                    return enlaces.PATH_PANEL_EJECUCION_MEDIDA_CAI_VER+"?faces-redirect=true";
                }
                else if(rolUsuario.equals("EQUIPO TECNICO PSICOLOGO CAI")){
                    return enlaces.PATH_PANEL_EJECUCION_MEDIDA_CAI_VER+"?faces-redirect=true";
                }
                else if(rolUsuario.equals("EQUIPO TECNICO JURIDICO CAI")){
                    return enlaces.PATH_PANEL_EJECUCION_MEDIDA_CAI_CREAR+"?faces-redirect=true";
                }
                else if(rolUsuario.equals("TRABAJADOR SOCIAL CAI")){
                    return enlaces.PATH_PANEL_EJECUCION_MEDIDA_CAI_VER+"?faces-redirect=true";
                }
                else{
                    return null;
                }  
            }
        }
        else{
            return enlaces.PATH_ERROR+"?faces-redirect=true";
        }
    }

    public String redireccionInformacionAdicionalMedida(){
        
        String rolUsuario=RolUsuario();
        
        if (rolUsuario != null) {
        
            if ("ADMINISTRADOR".equals(rolUsuario)){
                return enlaces.PATH_PANEL_INFORMACION_MEDIDA_CAI+"?faces-redirect=true";
            }
            else{
            
                if(rolUsuario.equals("COORDINADOR CAI")){
                    return enlaces.PATH_PANEL_INFORMACION_MEDIDA_CAI+"?faces-redirect=true";
                }
                else if(rolUsuario.equals("INSPECTOR EDUCADOR")){
                    return enlaces.PATH_PANEL_INFORMACION_MEDIDA_CAI_INSPECTOR_EDUCADOR+"?faces-redirect=true";
                }
                else if(rolUsuario.equals("EQUIPO TECNICO PSICOLOGO CAI")){
                    return enlaces.PATH_PANEL_INFORMACION_MEDIDA_CAI+"?faces-redirect=true";
                }
                else if(rolUsuario.equals("EQUIPO TECNICO JURIDICO CAI")){
                    return enlaces.PATH_PANEL_INFORMACION_MEDIDA_CAI+"?faces-redirect=true";
                }
                else if(rolUsuario.equals("TRABAJADOR SOCIAL CAI")){
                    return enlaces.PATH_PANEL_INFORMACION_MEDIDA_CAI+"?faces-redirect=true";
                }
                else{
                    return null;
                }  
            }
        }
        else{
            return enlaces.PATH_ERROR+"?faces-redirect=true";
        }
    }
}