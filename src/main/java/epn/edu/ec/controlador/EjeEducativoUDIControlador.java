package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.modelo.EjeEducativo;
import epn.edu.ec.servicios.EjeEducativoServicio;
import epn.edu.ec.utilidades.EnlacesPrograma;
import epn.edu.ec.utilidades.PermisosUsuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "ejeEducativoControladorUDI")
@ViewScoped
public class EjeEducativoUDIControlador implements Serializable{

    private AdolescenteInfractorUDI adolescenteInfractorUDI;
    private EjeEducativo ejeEducativo;
    
    private EjeEducativoServicio servicio;
    
    private boolean guardado;
    private boolean estudia;
    private PermisosUsuario permisosUsuario;
    
     @PostConstruct
    public void init(){
        
        permisosUsuario= new PermisosUsuario();
        servicio= new EjeEducativoServicio();
        
        ejeEducativo=new EjeEducativo();
        guardado=false;
        
        adolescenteInfractorUDI= new AdolescenteInfractorUDI();
        AdolescenteInfractorUDI adolescenteInfractorUDIAux= (AdolescenteInfractorUDI)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_udi");
        
        if(adolescenteInfractorUDIAux != null){
            
            adolescenteInfractorUDI=adolescenteInfractorUDIAux;
            
            EjeEducativo ejeEducativoUDIAux= servicio.obtenerEjeEducativo(adolescenteInfractorUDI.getIdAdolescenteInfractor().getIdAdolescenteInfractor());
            if(ejeEducativoUDIAux!=null){
                ejeEducativo=ejeEducativoUDIAux;
                guardado=true;
                estudia=ejeEducativo.getEstudia();
            }            
        }else{
            adolescenteInfractorUDI= new AdolescenteInfractorUDI();
        }
        
    }

    public AdolescenteInfractorUDI getAdolescenteInfractorUDI() {
        return adolescenteInfractorUDI;
    }

    public void setAdolescenteInfractorUDI(AdolescenteInfractorUDI adolescenteInfractorUDI) {
        this.adolescenteInfractorUDI = adolescenteInfractorUDI;
    }

    public EjeEducativo getEjeEducativo() {
        return ejeEducativo;
    }

    public void setEjeEducativo(EjeEducativo ejeEducativo) {
        this.ejeEducativo = ejeEducativo;
    }

    public EjeEducativoServicio getServicio() {
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
            ejeEducativo.setEstudia(true);
        }
        else if(estudia==false){
            ejeEducativo.setEstudia(false);
        }
        
    }

    
        /*********************Métodos para invocar a los diferentes servicios web******************/
    
    public void guardarEjeEducativoUDI(){
        
        this.ejeEducativo.setIdAdolescenteInfractor(adolescenteInfractorUDI.getIdAdolescenteInfractor());
        
        EjeEducativo ejeEducativoUDIAux = servicio.guardarEjeEducativo(ejeEducativo);
        if (ejeEducativoUDIAux != null) {
            guardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO EJE EDUCATIVO", "Información"));
            
        } else {
            guardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO EJE EDUCATIVO", "Error"));
        }
    }

    public void guardarEdicionEjeEducativoUDI(){
        
        this.ejeEducativo.setIdAdolescenteInfractor(adolescenteInfractorUDI.getIdAdolescenteInfractor());
        
        EjeEducativo ejeEducativoUDIAux = servicio.guardarEjeEducativo(ejeEducativo);
        if (ejeEducativoUDIAux != null) {
            guardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO EJE EDUCATIVO", "Información"));
            
        } else {
            guardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO EJE EDUCATIVO", "Error"));
        }
    }
}
