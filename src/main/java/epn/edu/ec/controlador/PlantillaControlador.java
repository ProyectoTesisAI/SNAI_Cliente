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

    public void verificarSesion() throws IOException{
    
        try{
            Usuario usuarioAux= (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
            
            if(usuarioAux==null){
                FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.URL_BASE+enlaces.PATH_ERROR);
            }
            
        }catch(Exception e){
            FacesContext.getCurrentInstance().getExternalContext().redirect(enlaces.PATH_ERROR);
        }
    }
    
}
