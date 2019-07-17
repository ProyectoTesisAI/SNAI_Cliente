package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.modelo.CumplimientoMedidaCAI;
import epn.edu.ec.modelo.DetalleInfraccionCAI;
import epn.edu.ec.servicios.CumplimientoMedidaCAIServicio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "cumplimientoMedidaCAIControlador")
@ViewScoped
public class CumplimientoMedidaCAIControlador implements Serializable{

    private DetalleInfraccionCAI detalleInfraccion;
    
    private AdolescenteInfractorCAI adolescenteInfractorCAI;
    private CumplimientoMedidaCAI cumplimientoCAIMedida;
    private CumplimientoMedidaCAIServicio servicio;
    private boolean guardado;
    
     @PostConstruct
    public void init(){
        servicio= new CumplimientoMedidaCAIServicio();
        
        cumplimientoCAIMedida =new CumplimientoMedidaCAI();
        guardado=false;
        
        detalleInfraccion = new DetalleInfraccionCAI();
        adolescenteInfractorCAI= new AdolescenteInfractorCAI();
        adolescenteInfractorCAI= (AdolescenteInfractorCAI)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_cai");
        
        if(adolescenteInfractorCAI != null){
            CumplimientoMedidaCAI cumplimientoCAIMedidaAux= servicio.obtenerCumplimientoMedidaCAI(adolescenteInfractorCAI.getIdAdolescenteCai());
            if(cumplimientoCAIMedidaAux!=null){
                cumplimientoCAIMedida=cumplimientoCAIMedidaAux;
                guardado=true;
            }            
        }
        
    }

    public AdolescenteInfractorCAI getAdolescenteInfractorCAI() {
        return adolescenteInfractorCAI;
    }

    public void setAdolescenteInfractorCAI(AdolescenteInfractorCAI adolescenteInfractorCAI) {
        this.adolescenteInfractorCAI = adolescenteInfractorCAI;
    }

    public CumplimientoMedidaCAI getCumplimientoMedidaCAI() {
        return cumplimientoCAIMedida;
    }

    public void setCumplimientoMedidaCAI(CumplimientoMedidaCAI cumplimientoCAIMedida) {
        this.cumplimientoCAIMedida = cumplimientoCAIMedida;
    }

    public CumplimientoMedidaCAIServicio getServicio() {
        return servicio;
    }

    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }
   
        /*********************Métodos para invocar a los diferentes servicios web******************/
    
    public String guardarCumplimientoMedidaCAI(){
        
        this.cumplimientoCAIMedida.setIdDetalleInfraccionCAI(detalleInfraccion);

        CumplimientoMedidaCAI cumplimientoCAIMedidaAux = servicio.guardarCumplimientoMedidaCAI(cumplimientoCAIMedida);
        if(cumplimientoCAIMedidaAux!=null){
            return "/paginas/cai/cai.com?faces-redirect=true";     
        }
        else{
            return null;
        }
    }

    
}
