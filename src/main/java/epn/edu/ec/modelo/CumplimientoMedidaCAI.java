package epn.edu.ec.modelo;

import java.io.Serializable;
import java.util.Date;

public class CumplimientoMedidaCAI implements Serializable {

    private EjecucionMedidaCAI idEjecucionMedidaCAI;
    private Date fechaCumplimiento100;
    private Date alertaCambioMedida;

    public CumplimientoMedidaCAI() {
    }

    public EjecucionMedidaCAI getIdEjecucionMedidaCAI() {
        return idEjecucionMedidaCAI;
    }

    public void setIdEjecucionMedidaCAI(EjecucionMedidaCAI idEjecucionMedidaCAI) {
        this.idEjecucionMedidaCAI = idEjecucionMedidaCAI;
    }

    public Date getFechaCumplimiento100() {
        return fechaCumplimiento100;
    }

    public void setFechaCumplimiento100(Date fechaCumplimiento100) {
        this.fechaCumplimiento100 = fechaCumplimiento100;
    }

    public Date getAlertaCambioMedida() {
        return alertaCambioMedida;
    }

    public void setAlertaCambioMedida(Date alertaCambioMedida) {
        this.alertaCambioMedida = alertaCambioMedida;
    }

}
