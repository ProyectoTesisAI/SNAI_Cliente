package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.modelo.UDI;
import epn.edu.ec.modelo.UnidadZonal;
import epn.edu.ec.servicios.UdiServicio;
import epn.edu.ec.servicios.UnidadZonalServicio;
import epn.edu.ec.utilidades.EnlacesPrograma;
import epn.edu.ec.utilidades.PermisosUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "unidadZonalControlador")
@ViewScoped
public class UnidadZonalControlador implements Serializable{
    
    private AdolescenteInfractorUDI adolescenteInfractorUDI;
    
    private UnidadZonal unidadZonal;    
    private List<UnidadZonal> listaUnidadZonal;
    private UnidadZonalServicio servicio;
    private boolean guardado;
    
    private UDI udi; 
    private List<UDI> listaUDI;
    private UdiServicio servicioUDI;
    private EnlacesPrograma enlaces;
    private PermisosUsuario permisosUsuario;

    @PostConstruct
    public void init(){
        
        permisosUsuario= new PermisosUsuario();
        enlaces= new EnlacesPrograma();
        servicio = new UnidadZonalServicio();
        servicioUDI = new UdiServicio();
        guardado=false;
        
        unidadZonal = new UnidadZonal();    
        udi= new UDI();
        listaUnidadZonal = new ArrayList<>();
        listaUDI=new ArrayList<>();
        listaUDI=servicioUDI.listaUdi();
        
        adolescenteInfractorUDI= new AdolescenteInfractorUDI();
        
        AdolescenteInfractorUDI adolescenteInfractorUDIAux= (AdolescenteInfractorUDI)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_udi");
        
        if(adolescenteInfractorUDIAux != null){
            
            adolescenteInfractorUDI=adolescenteInfractorUDIAux;
            UnidadZonal unidadZonalAux= obtenerUnidadZonal(adolescenteInfractorUDI.getIdAdolescenteInfractor().getIdAdolescenteInfractor());
            
            if(unidadZonalAux!=null){
                
                udi=unidadZonalAux.getIdUdi();
                unidadZonal=unidadZonalAux;
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

    public UdiServicio getServicioUDI() {
        return servicioUDI;
    }
    
    public UnidadZonal getUnidadZonal() {
        return unidadZonal;
    }

    public void setUnidadZonal(UnidadZonal unidadZonal) {
        this.unidadZonal = unidadZonal;
    }

    public List<UnidadZonal> getListaUnidadZonal() {
        return listaUnidadZonal;
    }

    public void setListaUnidadZonal(List<UnidadZonal> listaUnidadZonal) {
        this.listaUnidadZonal = listaUnidadZonal;
    }

    public UnidadZonalServicio getServicio() {
        return servicio;
    }

    public List<UDI> getListaUDI() {
        return listaUDI;
    }

    public void setListaUDI(List<UDI> listaUDI) {
        this.listaUDI = listaUDI;
    }

    public UDI getUdi() {
        return udi;
    }

    public void setUdi(UDI udi) {
        this.udi = udi;
    }

    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }
    
    /*********************Métodos para invocar a los diferentes servicios web******************/
    
    public void guardarUnidadZonal(){
        
        for(UDI u: listaUDI){
            if(u.getUdi().equals(udi.getUdi())){
                udi=u;
            }
        }
        this.unidadZonal.setIdUdi(udi);
        this.unidadZonal.setIdUnidadZonal(adolescenteInfractorUDI);
        UnidadZonal uz= servicio.guardarUnidadZonal(unidadZonal);
        if (uz != null) {
            guardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO UNIDAD ZONAL", "Información"));
            
        } else {
            guardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO UNIDAD ZONAL", "Error"));
        }
    }
    
    public void guardarEdicionUnidadZonal(){
        
        for(UDI u: listaUDI){
            if(u.getUdi().equals(udi.getUdi())){
                udi=u;
            }
        }
        this.unidadZonal.setIdUdi(udi);
        this.unidadZonal.setIdUnidadZonal(adolescenteInfractorUDI);
        UnidadZonal uz= servicio.guardarUnidadZonal(unidadZonal);
        if (uz != null) {
            guardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO UNIDAD ZONAL", "Información"));
            
        } else {
            guardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO UNIDAD ZONAL", "Error"));
        }
    }
    
    public UnidadZonal obtenerUnidadZonal(Integer id){
        UnidadZonal unidadZonalAux= servicio.obtenerUnidadZonal(id);
        return unidadZonalAux;
    }

}
