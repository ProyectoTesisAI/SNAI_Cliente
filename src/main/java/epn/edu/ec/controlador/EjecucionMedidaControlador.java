package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.modelo.CAI;
import epn.edu.ec.modelo.DetalleInfraccionCAI;
import epn.edu.ec.modelo.EjecucionMedidaCAI;
import epn.edu.ec.servicios.CaiServicio;
import epn.edu.ec.servicios.EjecucionMedidaServicio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "ejecucionMedidaControlador")
@ViewScoped
public class EjecucionMedidaControlador implements Serializable{

    private DetalleInfraccionCAI detalleInfraccionCAI;
    private EjecucionMedidaCAI ejecucionMedidaCAI;
    private EjecucionMedidaServicio servicio;
    private boolean guardado;
    
    private CAI cai; 
    private List<CAI> listaCAI;
    private CaiServicio servicioUDI;
    
     @PostConstruct
    public void init(){
        servicio= new EjecucionMedidaServicio();
        servicioUDI=new CaiServicio();
        
        listaCAI=new ArrayList<>();
        listaCAI=servicioUDI.listaCai();
        
        ejecucionMedidaCAI =new EjecucionMedidaCAI();
        guardado=false;
        
        detalleInfraccionCAI=new DetalleInfraccionCAI();
        
        DetalleInfraccionCAI detalleInfraccionCAIAux = (DetalleInfraccionCAI) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_cai");

        if(detalleInfraccionCAIAux != null){
            
            detalleInfraccionCAI=detalleInfraccionCAIAux;
            
            EjecucionMedidaCAI ejecucionMedidaAux= servicio.obtenerEjecucionMedidaCAI(detalleInfraccionCAI.getIdDetalleInfraccion());
            
            if(ejecucionMedidaAux!=null){
            
                ejecucionMedidaCAI=ejecucionMedidaAux;
                guardado=true;
            }else{
                
                //ejecucionMedidaCAI. setFechaReporteCAI(adolescenteInfractorCAI.getFechaIngresoProceso());
            }
        }
        
    } 

    public DetalleInfraccionCAI getDetalleInfraccionCAI() {
        return detalleInfraccionCAI;
    }

    public void setDetalleInfraccionCAI(DetalleInfraccionCAI detalleInfraccionCAI) {
        this.detalleInfraccionCAI = detalleInfraccionCAI;
    }

    public EjecucionMedidaCAI getEjecucionMedidaCAI() {
        return ejecucionMedidaCAI;
    }

    public void setEjecucionMedidaCAI(EjecucionMedidaCAI ejecucionMedidaCAI) {
        this.ejecucionMedidaCAI = ejecucionMedidaCAI;
    }

    public EjecucionMedidaServicio getServicio() {
        return servicio;
    }

    public CAI getCai() {
        return cai;
    }

    public void setCai(CAI cai) {
        this.cai = cai;
    }

    public List<CAI> getListaCAI() {
        return listaCAI;
    }

    public void setListaCAI(List<CAI> listaCAI) {
        this.listaCAI = listaCAI;
    }

    public CaiServicio getServicioUDI() {
        return servicioUDI;
    }

    public void setServicioUDI(CaiServicio servicioUDI) {
        this.servicioUDI = servicioUDI;
    }

    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }
    
        /*********************Métodos para invocar a los diferentes servicios web******************/
    
    public String guardarEstadoCumplimientoMedida(){
        
        this.ejecucionMedidaCAI.setIdDetalleInfraccionCAI(detalleInfraccionCAI);

        EjecucionMedidaCAI ejecucionMedidaAux = servicio.guardarEjecucionMedidaCAI(ejecucionMedidaCAI);
        if(ejecucionMedidaAux!=null){
            return "/paginas/cai/cai.com?faces-redirect=true";     
        }
        else{
            return null;
        }
    }

    
}
