package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.modelo.EjeLaboral;
import epn.edu.ec.servicios.EjeLaboralServicio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "ejeLaboralControlador")
@ViewScoped
public class EjeLaboralControlador implements Serializable{

    private AdolescenteInfractorUDI adolescenteInfractorUDI;
    private EjeLaboral ejeLaboral;
    private EjeLaboralServicio servicio;
    
    private boolean guardado;
    private boolean trabaja;
    
    @PostConstruct
    public void init(){
        
        servicio= new EjeLaboralServicio();
        
        ejeLaboral= new EjeLaboral();
        guardado=false;
       
        adolescenteInfractorUDI= new AdolescenteInfractorUDI();
        AdolescenteInfractorUDI adolescenteInfractorUDIAux= (AdolescenteInfractorUDI)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_udi");
        
        if(adolescenteInfractorUDIAux != null){
            
            adolescenteInfractorUDI=adolescenteInfractorUDIAux;
            
            EjeLaboral ejeLaboralAux= servicio.obtenerEjeLaboral(adolescenteInfractorUDI.getIdAdolescenteInfractor().getIdAdolescenteInfractor());
            if(ejeLaboralAux!=null){
                ejeLaboral=ejeLaboralAux;
                guardado=true;
                trabaja=ejeLaboral.getTrabaja();
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

    public EjeLaboral getEjeLaboral() {
        return ejeLaboral;
    }

    public void setEjeLaboral(EjeLaboral ejeLaboral) {
        this.ejeLaboral = ejeLaboral;
    }

    public EjeLaboralServicio getServicio() {
        return servicio;
    }

    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }

    public boolean isTrabaja() {
        return trabaja;
    }

    public void setTrabaja(boolean trabaja) {
        this.trabaja = trabaja;
        if (trabaja==true) {
            ejeLaboral.setTrabaja(true);
        }else if(trabaja==false){
            ejeLaboral.setTrabaja(false);
            ejeLaboral.setOcupacionAdolescente(null);
            ejeLaboral.setRamaActividadEconomica(null);
            ejeLaboral.setAfiliacionIess(false);
            ejeLaboral.setIngresoMensual(null);
            ejeLaboral.setNumeroHorasTrabajo(0);
            ejeLaboral.setDescripcionActividad(null);
        }
    }
    
    
        /*********************Métodos para invocar a los diferentes servicios web******************/
    
    public void guardarEjeLaboral(){
        
        this.ejeLaboral.setIdAdolescenteInfractorUDI(adolescenteInfractorUDI);

        EjeLaboral ejeLaboralAux = servicio.guardarEjeLaboral(ejeLaboral);
        if (ejeLaboralAux != null) {
            guardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO EJE LABORAL", "Información"));
            
        } else {
            guardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO EJE LABORAL", "Error"));
        }
    }
    
    public void guardarEdicionEjeLaboral(){
        
        this.ejeLaboral.setIdAdolescenteInfractorUDI(adolescenteInfractorUDI);

        EjeLaboral ejeLaboralAux = servicio.guardarEjeLaboral(ejeLaboral);
        if (ejeLaboralAux != null) {
            guardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO EJE LABORAL", "Información"));
            
        } else {
            guardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO EJE LABORAL", "Error"));
        }
    }

    
}
