package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.modelo.EjeEducativo;
import epn.edu.ec.servicios.EjeEducativoServicio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "ejeEducativoControladorCAI")
@ViewScoped
public class EjeEducativoCAIControlador implements Serializable{

    private AdolescenteInfractorCAI adolescenteInfractorCAI;
    private EjeEducativo ejeEducativo;
    
    private EjeEducativoServicio servicio;
    
    private boolean guardado;
    private boolean estudia;
    
     @PostConstruct
    public void init(){
        
        servicio= new EjeEducativoServicio();
        
        ejeEducativo=new EjeEducativo();
        guardado=false;
        
        adolescenteInfractorCAI= new AdolescenteInfractorCAI();
        AdolescenteInfractorCAI adolescenteInfractorCAIAux= (AdolescenteInfractorCAI)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_cai");
        
        if(adolescenteInfractorCAIAux != null){
            
            adolescenteInfractorCAI=adolescenteInfractorCAIAux;
            
            EjeEducativo ejeEducativoCAIAux= servicio.obtenerEjeEducativo(adolescenteInfractorCAI.getIdAdolescenteInfractor().getIdAdolescenteInfractor());
            
            if(ejeEducativoCAIAux!=null){
                ejeEducativo=ejeEducativoCAIAux;
                guardado=true;
                estudia=ejeEducativo.getEstudia();
            }            
        }
        
    }

    public AdolescenteInfractorCAI getAdolescenteInfractorCAI() {
        return adolescenteInfractorCAI;
    }

    public void setAdolescenteInfractorCAI(AdolescenteInfractorCAI adolescenteInfractorUDI) {
        this.adolescenteInfractorCAI = adolescenteInfractorUDI;
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
            this.ejeEducativo.setRazonNoEstudia(null);
            this.ejeEducativo.setUltimoNivelEducativoAprobado(null);
            this.ejeEducativo.setNombreUnidadEducativaUltimaAprobado(null);
            this.ejeEducativo.setSostenimientoUltimoNivelAprobado(null);
            this.ejeEducativo.setAniosRezagoEscolar(null);
        }
        else if(estudia==false){
            ejeEducativo.setEstudia(false);
            this.ejeEducativo.setSostenimiento(null);
            this.ejeEducativo.setRegimenEducativo(null);
            this.ejeEducativo.setNivelEducativoActual(null);
            this.ejeEducativo.setTipoOferta(null);
            this.ejeEducativo.setNombreUnidadEducativa(null);
        }
        
    }

    
        /*********************Métodos para invocar a los diferentes servicios web******************/
    
    public void guardarEjeEducativoCAI(){
        
        this.ejeEducativo.setIdAdolescenteInfractor(adolescenteInfractorCAI.getIdAdolescenteInfractor());
        
        EjeEducativo ejeEducativoCAIAux = servicio.guardarEjeEducativo(this.ejeEducativo);
        if (ejeEducativoCAIAux != null) {
            guardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO EJE EDUCATIVO", "Información"));
            
        } else {
            guardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO EJE EDUCATIVO", "Error"));
        }
    }
    
    public void editarEjeEducativoCAI(){
        
        this.ejeEducativo.setIdAdolescenteInfractor(adolescenteInfractorCAI.getIdAdolescenteInfractor());
        
        EjeEducativo ejeEducativoCAIAux = servicio.guardarEjeEducativo(this.ejeEducativo);
        if (ejeEducativoCAIAux != null) {
            guardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO EJE EDUCATIVO", "Información"));
            
        } else {
            guardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO EJE EDUCATIVO", "Error"));
        }
    }

    
}
