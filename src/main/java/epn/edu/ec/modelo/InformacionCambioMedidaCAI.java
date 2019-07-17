package epn.edu.ec.modelo;

import java.io.Serializable;
import java.util.Date;


public class InformacionCambioMedidaCAI implements Serializable {


    private DetalleInfraccionCAI idDetalleInfraccionCAI;
    private Boolean aceptacionJuezCambioMedida;
    private String observaciones;
    private String cambioMedidaSocioeducativa;
    private Integer cumplimieno6080TiempoPrivacionLibertad;
    private Date fechaCumplimiento6080;
    private Date alertaCambioMedida;
    private String especificacionNuevaMedida;

    public InformacionCambioMedidaCAI() {
    }

    public DetalleInfraccionCAI getIdDetalleInfraccionCAI() {
        return idDetalleInfraccionCAI;
    }

    public void setIdDetalleInfraccionCAI(DetalleInfraccionCAI idDetalleInfraccionCAI) {
        this.idDetalleInfraccionCAI = idDetalleInfraccionCAI;
    }
    
    public Boolean getAceptacionJuezCambioMedida() {
        return aceptacionJuezCambioMedida;
    }

    public void setAceptacionJuezCambioMedida(Boolean aceptacionJuezCambioMedida) {
        this.aceptacionJuezCambioMedida = aceptacionJuezCambioMedida;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getCambioMedidaSocioeducativa() {
        return cambioMedidaSocioeducativa;
    }

    public void setCambioMedidaSocioeducativa(String cambioMedidaSocioeducativa) {
        this.cambioMedidaSocioeducativa = cambioMedidaSocioeducativa;
    }

    public Integer getCumplimieno6080TiempoPrivacionLibertad() {
        return cumplimieno6080TiempoPrivacionLibertad;
    }

    public void setCumplimieno6080TiempoPrivacionLibertad(Integer cumplimieno6080TiempoPrivacionLibertad) {
        this.cumplimieno6080TiempoPrivacionLibertad = cumplimieno6080TiempoPrivacionLibertad;
    }

    public Date getFechaCumplimiento6080() {
        return fechaCumplimiento6080;
    }

    public void setFechaCumplimiento6080(Date fechaCumplimiento6080) {
        this.fechaCumplimiento6080 = fechaCumplimiento6080;
    }

    public Date getAlertaCambioMedida() {
        return alertaCambioMedida;
    }

    public void setAlertaCambioMedida(Date alertaCambioMedida) {
        this.alertaCambioMedida = alertaCambioMedida;
    }

    public String getEspecificacionNuevaMedida() {
        return especificacionNuevaMedida;
    }

    public void setEspecificacionNuevaMedida(String especificacionNuevaMedida) {
        this.especificacionNuevaMedida = especificacionNuevaMedida;
    }

}
