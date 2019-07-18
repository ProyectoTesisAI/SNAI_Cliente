package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.modelo.EjeLaboral;
import epn.edu.ec.servicios.EjeLaboralServicio;
import epn.edu.ec.utilidades.EnlacesPrograma;
import java.io.Serializable;
import javax.annotation.PostConstruct;
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
    private EnlacesPrograma enlaces;
    
    @PostConstruct
    public void init(){
        
        enlaces= new EnlacesPrograma();
        servicio= new EjeLaboralServicio();
        
        ejeLaboral= new EjeLaboral();
        guardado=false;
        
        if (isTrabaja()) {
            trabaja = true;
        } else {
            trabaja = false;            
        }
        
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
        }
    }
    
    
        /*********************Métodos para invocar a los diferentes servicios web******************/
    
    public String guardarEjeLaboral(){
        
        this.ejeLaboral.setIdAdolescenteInfractorUDI(adolescenteInfractorUDI);

        EjeLaboral ejeLaboralAux = servicio.guardarEjeLaboral(ejeLaboral);
        if(ejeLaboralAux!=null){
            return enlaces.PATH_PANEL_UDI+"?faces-redirect=true";        
        }
        else{
            return null;
        }
    }

    
}
