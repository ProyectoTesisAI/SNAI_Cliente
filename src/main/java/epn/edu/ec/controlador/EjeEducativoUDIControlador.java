/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.modelo.EjeEducativoUDI;
import epn.edu.ec.servicios.EjeEducativoUDIServicio;
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
@Named(value = "ejeEducativoUDIControlador")
@ViewScoped
public class EjeEducativoUDIControlador implements Serializable{

    private AdolescenteInfractorUDI adolescenteInfractorUDI;
    private EjeEducativoUDI ejeEducativoUDI;
    private EjeEducativoUDIServicio servicio;
    private boolean guardado;
    private boolean estudia;
    private EnlacesPrograma enlaces;
    
    
     @PostConstruct
    public void init(){
        
        enlaces= new EnlacesPrograma();
        servicio= new EjeEducativoUDIServicio();
        
        ejeEducativoUDI=new EjeEducativoUDI();
        guardado=false;
        
        if(isEstudia()){
            estudia=true;
        }
        else{
            estudia=false;
        }
        
        adolescenteInfractorUDI= new AdolescenteInfractorUDI();
        AdolescenteInfractorUDI adolescenteInfractorUDIAux= (AdolescenteInfractorUDI)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_udi");
        
        if(adolescenteInfractorUDIAux != null){
            
            adolescenteInfractorUDI=adolescenteInfractorUDIAux;
            
            EjeEducativoUDI ejeEducativoUDIAux= servicio.obtenerEjeEducativoUDI(adolescenteInfractorUDI.getIdAdolescenteUdi());
            if(ejeEducativoUDIAux!=null){
                ejeEducativoUDI=ejeEducativoUDIAux;
                guardado=true;
                estudia=ejeEducativoUDI.getEstudia();
            }            
        }
        
    }

    public AdolescenteInfractorUDI getAdolescenteInfractorUDI() {
        return adolescenteInfractorUDI;
    }

    public void setAdolescenteInfractorUDI(AdolescenteInfractorUDI adolescenteInfractorUDI) {
        this.adolescenteInfractorUDI = adolescenteInfractorUDI;
    }

    public EjeEducativoUDI getEjeEducativoUDI() {
        return ejeEducativoUDI;
    }

    public void setEjeEducativoUDI(EjeEducativoUDI ejeEducativoUDI) {
        this.ejeEducativoUDI = ejeEducativoUDI;
    }

    public EjeEducativoUDIServicio getServicio() {
        return servicio;
    }
    
    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }

    public boolean isEstudia() {
        return estudia;
    }

    public void setEstudia(boolean estudia) {
        this.estudia = estudia;
        if(estudia==true){
            ejeEducativoUDI.setEstudia(true);
        }
        else if(estudia==false){
            ejeEducativoUDI.setEstudia(false);
        }
        
    }

    
        /*********************Métodos para invocar a los diferentes servicios web******************/
    
    public String guardarEjeEducativoUDI(){
        
        this.ejeEducativoUDI.setIdEjeEducativo(adolescenteInfractorUDI);
        
        EjeEducativoUDI ejeEducativoUDIAux = servicio.guardarEjeEducativoUDI(ejeEducativoUDI);
        if(ejeEducativoUDIAux!=null){
            return enlaces.PATH_PANEL_UDI+"?faces-redirect=true";        
        }
        else{
            return null;
        }
    }

    
}
