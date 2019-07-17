package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.modelo.DetalleInfraccionCAI;
import epn.edu.ec.modelo.EjecucionMedidaCAI;
import epn.edu.ec.modelo.InformacionCambioMedidaCAI;
import epn.edu.ec.servicios.EjecucionMedidaServicio;
import epn.edu.ec.servicios.InformacionCambioMedidaServicio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "informacionCambioMedidaControlador")
@ViewScoped
public class InformacionCambioMedidaControlador implements Serializable{

    private DetalleInfraccionCAI detalleInfraccion;
    private AdolescenteInfractorCAI adolescenteInfractorCAI;
    private InformacionCambioMedidaCAI estadoCumplimientoMedida;
    private InformacionCambioMedidaServicio servicio;
    private boolean guardado;
    
    private EjecucionMedidaCAI ejecucionMedida;
    private EjecucionMedidaServicio servicioEM;
    
     @PostConstruct
    public void init(){
        servicio= new InformacionCambioMedidaServicio();
        servicioEM=new EjecucionMedidaServicio();
        
        ejecucionMedida = new EjecucionMedidaCAI();
        
        estadoCumplimientoMedida =new InformacionCambioMedidaCAI();
        guardado=false;
        
        detalleInfraccion=new DetalleInfraccionCAI();
        
        adolescenteInfractorCAI= new AdolescenteInfractorCAI();
        adolescenteInfractorCAI= (AdolescenteInfractorCAI)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_cai");
        
        if(adolescenteInfractorCAI != null){
            InformacionCambioMedidaCAI informacionCambioMedidaAux= servicio.obtenerInformacionCambioMedidaCAI(adolescenteInfractorCAI.getIdAdolescenteCai());
            ejecucionMedida=servicioEM.obtenerEjecucionMedidaCAI(adolescenteInfractorCAI.getIdAdolescenteCai());
            if(informacionCambioMedidaAux!=null){
                estadoCumplimientoMedida=informacionCambioMedidaAux;
                estadoCumplimientoMedida.setEjecucionAux(ejecucionMedida);
                guardado=true;
            }else{
                estadoCumplimientoMedida.setEjecucionAux(ejecucionMedida);
            }
        }
        
    }

    public AdolescenteInfractorCAI getAdolescenteInfractorCAI() {
        return adolescenteInfractorCAI;
    }

    public void setAdolescenteInfractorCAI(AdolescenteInfractorCAI adolescenteInfractorCAI) {
        this.adolescenteInfractorCAI = adolescenteInfractorCAI;
    }

    public InformacionCambioMedidaCAI getInformacionCambioMedida() {
        return estadoCumplimientoMedida;
    }

    public void setInformacionCambioMedida(InformacionCambioMedidaCAI estadoCumplimientoMedida) {
        this.estadoCumplimientoMedida = estadoCumplimientoMedida;
    }

    public InformacionCambioMedidaServicio getServicio() {
        return servicio;
    }

    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }   
    
    /*********************Métodos para invocar a los diferentes servicios web******************/
    
    public String guardarInformacionCambioMedida(){
        
        this.estadoCumplimientoMedida.setIdInformacionCambioMedida(detalleInfraccion);

        InformacionCambioMedidaCAI informacionCambioMedidaAux = servicio.guardarInformacionCambioMedidaCAI(estadoCumplimientoMedida);
        if(informacionCambioMedidaAux!=null){
            return "/paginas/cai/cai.com?faces-redirect=true";     
        }
        else{
            return null;
        }
    }

    
}
