package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.modelo.CumplimientoMedidaCAI;
import epn.edu.ec.modelo.DetalleInfraccionCAI;
import epn.edu.ec.modelo.EjecucionMedidaCAI;
import epn.edu.ec.servicios.CumplimientoMedidaCAIServicio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "cumplimientoMedidaCAIControlador")
@ViewScoped
public class CumplimientoMedidaCAIControlador implements Serializable{

    private EjecucionMedidaCAI ejecucionMedidaCAI;
    private CumplimientoMedidaCAI cumplimientoCAIMedida;
    private CumplimientoMedidaCAIServicio servicio;
    private boolean guardado;
    
     @PostConstruct
    public void init(){
        servicio= new CumplimientoMedidaCAIServicio();
        
        cumplimientoCAIMedida =new CumplimientoMedidaCAI();
        guardado=false;
        
        ejecucionMedidaCAI= new EjecucionMedidaCAI();
        EjecucionMedidaCAI ejecucionMedidaCAIAux = (EjecucionMedidaCAI) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ejecucion_medida_cai");

        if(ejecucionMedidaCAIAux != null){
            
            ejecucionMedidaCAI=ejecucionMedidaCAIAux;
            
            CumplimientoMedidaCAI cumplimientoCAIMedidaAux= servicio.obtenerCumplimientoMedidaCAI(ejecucionMedidaCAI.getIdEjecucionMedidaCai());
            if(cumplimientoCAIMedidaAux!=null){
                cumplimientoCAIMedida=cumplimientoCAIMedidaAux;
                guardado=true;
            }            
        }
        
    }

    public EjecucionMedidaCAI getEjecucionMedidaCAI() {
        return ejecucionMedidaCAI;
    }

    public void setEjecucionMedidaCAI(EjecucionMedidaCAI ejecucionMedidaCAI) {
        this.ejecucionMedidaCAI = ejecucionMedidaCAI;
    }

    public CumplimientoMedidaCAI getCumplimientoCAIMedida() {
        return cumplimientoCAIMedida;
    }

    public void setCumplimientoCAIMedida(CumplimientoMedidaCAI cumplimientoCAIMedida) {
        this.cumplimientoCAIMedida = cumplimientoCAIMedida;
    }

    public CumplimientoMedidaCAIServicio getServicio() {
        return servicio;
    }

    public void setServicio(CumplimientoMedidaCAIServicio servicio) {
        this.servicio = servicio;
    }

    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }

    
   
        /*********************Métodos para invocar a los diferentes servicios web******************/
    
    public String guardarCumplimientoMedidaCAI(){
        
        this.cumplimientoCAIMedida.setIdEjecucionMedidaCAI(ejecucionMedidaCAI); 

        CumplimientoMedidaCAI cumplimientoCAIMedidaAux = servicio.guardarCumplimientoMedidaCAI(cumplimientoCAIMedida);
        if(cumplimientoCAIMedidaAux!=null){
            return "/paginas/cai/cai.com?faces-redirect=true";     
        }
        else{
            return null;
        }
    }

    
}
