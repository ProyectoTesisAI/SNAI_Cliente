package epn.edu.ec.modelo;

import java.io.Serializable;
import java.util.Date;

public class CumplimientoMedidaCAI implements Serializable {

    private DetalleInfraccionCAI idDetallerInfraccionCAI;
    private Date fechaCumplimiento100;
    private Date alertaCambioMedida;

    public CumplimientoMedidaCAI() {
    }

    public DetalleInfraccionCAI getIdDetallerInfraccionCAI() {
        return idDetallerInfraccionCAI;
    }

    public void setIdDetallerInfraccionCAI(DetalleInfraccionCAI idDetallerInfraccionCAI) {
        this.idDetallerInfraccionCAI = idDetallerInfraccionCAI;
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

    @Override
    public String toString() {
        return "CumplimientoMedidaCAI{" + "idCumplimientoMedida=" + idDetallerInfraccionCAI + ", fechaCumplimiento100=" + fechaCumplimiento100 + ", alertaCambioMedida=" + alertaCambioMedida + '}';
    }
}
