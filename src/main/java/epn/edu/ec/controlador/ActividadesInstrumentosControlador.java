package epn.edu.ec.controlador;

import epn.edu.ec.modelo.ActividadesInstrumentos;
import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.servicios.ActividadesInstrumentosServicio;
import epn.edu.ec.utilidades.PermisosUsuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "actividadesInstrumentosControlador")
@ViewScoped
public class ActividadesInstrumentosControlador implements Serializable{

    private AdolescenteInfractorUDI adolescenteInfractorUDI;
    private ActividadesInstrumentos actividadesInstrumentos;
    private ActividadesInstrumentosServicio servicio;
    private boolean guardado;
    
    @PostConstruct
    public void init(){
        
        servicio= new ActividadesInstrumentosServicio();
        
        actividadesInstrumentos= new ActividadesInstrumentos();
        guardado=false;
        
        adolescenteInfractorUDI= new AdolescenteInfractorUDI();
        AdolescenteInfractorUDI adolescenteInfractorUDIAux= (AdolescenteInfractorUDI)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_udi");
        
        if(adolescenteInfractorUDIAux != null){
            
            adolescenteInfractorUDI=adolescenteInfractorUDIAux;
            
            ActividadesInstrumentos actividadesInstrumentosAux= servicio.obtenerActividadesInstrumentos(adolescenteInfractorUDI.getIdAdolescenteInfractor().getIdAdolescenteInfractor());
            if(actividadesInstrumentosAux!=null){
                actividadesInstrumentos=actividadesInstrumentosAux;
                guardado=true;
            }            
        }
        
    }

    public AdolescenteInfractorUDI getAdolescenteInfractorUDI() {
        return adolescenteInfractorUDI;
    }

    public void setAdolescenteInfractorUDI(AdolescenteInfractorUDI adolescenteInfractorUDI) {
        this.adolescenteInfractorUDI = adolescenteInfractorUDI;
    }

    public ActividadesInstrumentos getActividadesInstrumentos() {
        return actividadesInstrumentos;
    }

    public void setActividadesInstrumentos(ActividadesInstrumentos actividadesInstrumentos) {
        this.actividadesInstrumentos = actividadesInstrumentos;
    }

    
    public ActividadesInstrumentosServicio getServicio() {
        return servicio;
    }

    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }
    
    /*********************Métodos para invocar a los diferentes servicios web******************/
    
    public void guardarActividadesInstrumentos(){
        
        this.actividadesInstrumentos.setIdAdolescenteInfractorUDI(adolescenteInfractorUDI);

        ActividadesInstrumentos actividadesInstrumentosAux = servicio.guardarActividadesInstrumentos(actividadesInstrumentos);
        if (actividadesInstrumentosAux != null) {
            guardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO,
                    "SE HA GUARDADO CORRECTAMENTE EL REGISTRO ACTIVIDADES E INSTRUMENTOS", 
                    "Información"));
            
        } else {
            guardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, 
                    "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO ACTIVIDADES E INSTRUMENTOS", 
                    "Error"));
        }
    }
    
    public void guardarEdicionActividadesInstrumentos(){
        
        this.actividadesInstrumentos.setIdAdolescenteInfractorUDI(adolescenteInfractorUDI);

        ActividadesInstrumentos actividadesInstrumentosAux = servicio.guardarActividadesInstrumentos(actividadesInstrumentos);
        if (actividadesInstrumentosAux != null) {
            guardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO ACTIVIDADES E INSTRUMENTOS", "Información"));
            
        } else {
            guardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO ACTIVIDADES E INSTRUMENTOS", "Error"));
        }
    }

}
