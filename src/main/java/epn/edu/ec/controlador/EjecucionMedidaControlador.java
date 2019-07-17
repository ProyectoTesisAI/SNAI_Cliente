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

    private DetalleInfraccionCAI detalleInfraccion;
    private AdolescenteInfractorCAI adolescenteInfractorCAI;
    private EjecucionMedidaCAI ejecucionMedida;
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
        
        ejecucionMedida =new EjecucionMedidaCAI();
        guardado=false;
        
        detalleInfraccion=new DetalleInfraccionCAI();
        
        adolescenteInfractorCAI= new AdolescenteInfractorCAI();
        adolescenteInfractorCAI= (AdolescenteInfractorCAI)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_cai");
        
        if(adolescenteInfractorCAI != null){
            EjecucionMedidaCAI ejecucionMedidaAux= servicio.obtenerEjecucionMedidaCAI(adolescenteInfractorCAI.getIdAdolescenteCai());
            if(ejecucionMedidaAux!=null){
                ejecucionMedida=ejecucionMedidaAux;
                guardado=true;
            }else{
                ejecucionMedida.setFechaReporteCAI(adolescenteInfractorCAI.getFechaReporte());
            }
        }
        
    }

    public AdolescenteInfractorCAI getAdolescenteInfractorCAI() {
        return adolescenteInfractorCAI;
    }

    public void setAdolescenteInfractorCAI(AdolescenteInfractorCAI adolescenteInfractorCAI) {
        this.adolescenteInfractorCAI = adolescenteInfractorCAI;
    }

    public EjecucionMedidaCAI getEjecucionMedida() {
        return ejecucionMedida;
    }

    public void setEjecucionMedida(EjecucionMedidaCAI ejecucionMedida) {
        this.ejecucionMedida = ejecucionMedida;
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
        
        this.ejecucionMedida.setIdEjecucionMedida(detalleInfraccion);

        EjecucionMedidaCAI ejecucionMedidaAux = servicio.guardarEjecucionMedidaCAI(ejecucionMedida);
        if(ejecucionMedidaAux!=null){
            return "/paginas/cai/cai.com?faces-redirect=true";     
        }
        else{
            return null;
        }
    }

    
}
