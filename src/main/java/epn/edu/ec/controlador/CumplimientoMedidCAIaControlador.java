package epn.edu.ec.controlador;

import epn.edu.ec.modelo.CumplimientoMedidaCAI;
import epn.edu.ec.modelo.EjecucionMedidaCAI;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "cumplimientoMedidCAIControlador")
@ViewScoped
public class CumplimientoMedidCAIaControlador implements Serializable {

    private EjecucionMedidaCAI ejecucionMedidaCAI;
    private CumplimientoMedidaCAI cumplimientoMedidaCAI;
    
    @PostConstruct
    public void init() {
        ejecucionMedidaCAI = new EjecucionMedidaCAI();
        cumplimientoMedidaCAI= new CumplimientoMedidaCAI();
        
        EjecucionMedidaCAI ejecucionMedidaCAIAux = (EjecucionMedidaCAI) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ejecucion_medida_cai");

        if (ejecucionMedidaCAIAux != null) {

            ejecucionMedidaCAI = ejecucionMedidaCAIAux;
            
            Date fechaCumplimiento100=getFechaCumplimiento100(ejecucionMedidaCAI.getFechaAprehension(), ejecucionMedidaCAI.getTiempoSentenDias());
            Date fechaAlertaCumplimiento100=getAlertaCumplimiento100(fechaCumplimiento100);
            
            cumplimientoMedidaCAI.setFechaCumplimiento100(fechaCumplimiento100);
            cumplimientoMedidaCAI.setAlertaCambioMedida(fechaAlertaCumplimiento100);
        }

    }

     private Date getFechaCumplimiento100(Date fechaAprehension, Integer tiempoSentenDias ) {
        
        Date fechaCumplimiento100=null;
        
        if (fechaAprehension != null) {
            Calendar fechaAux1 = Calendar.getInstance();
            fechaAux1.setTime(fechaAprehension);
            fechaAux1.add(Calendar.DATE, tiempoSentenDias);
            fechaCumplimiento100 = fechaAux1.getTime();
        }
        return fechaCumplimiento100;
    }

    

    private Date getAlertaCumplimiento100(Date fechaCumplimiento100) {
        
        Date fechaAlertaCumplimiento100=null;
        
        if (fechaCumplimiento100 != null) {
            Calendar fechaAux1 = Calendar.getInstance();
            fechaAux1.setTime(fechaCumplimiento100);
            fechaAux1.add(Calendar.DATE, -14);
            fechaAlertaCumplimiento100 = fechaAux1.getTime();
        }
        return fechaAlertaCumplimiento100;
    }

    public CumplimientoMedidaCAI getCumplimientoMedidaCAI() {
        return cumplimientoMedidaCAI;
    }

    public void setCumplimientoMedidaCAI(CumplimientoMedidaCAI cumplimientoMedidaCAI) {
        this.cumplimientoMedidaCAI = cumplimientoMedidaCAI;
    }
    
    

}
