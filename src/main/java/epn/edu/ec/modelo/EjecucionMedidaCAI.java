package epn.edu.ec.modelo;

import java.io.Serializable;
import java.util.Date;


public class EjecucionMedidaCAI implements Serializable {


    private DetalleInfraccionCAI idDetalleInfraccion;
    private Date fechaAprehension;
    private Date fechaResolucion;
    private Date fechaIngresoCai;
    private String tipoMedida;
    private String medidaCautelar;
    private Date alertaSalidaIntermediaPreventiva;
    private Integer anios;
    private Integer meses;
    private Integer dias;
    private Integer tiempoSentenDias;
    private Integer tiempoPrivacionLibertad;
    private String observacionesProcesoJudicial;
    private CAI idCai;

    public EjecucionMedidaCAI() {
    }

    public DetalleInfraccionCAI getIdDetalleInfraccion() {
        return idDetalleInfraccion;
    }

    public void setIdDetalleInfraccion(DetalleInfraccionCAI idDetalleInfraccion) {
        this.idDetalleInfraccion = idDetalleInfraccion;
    }
    
    public Date getFechaAprehension() {
        return fechaAprehension;
    }

    public void setFechaAprehension(Date fechaAprehension) {
        this.fechaAprehension = fechaAprehension;
    }

    public Date getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public Date getFechaIngresoCai() {
        return fechaIngresoCai;
    }

    public void setFechaIngresoCai(Date fechaIngresoCai) {
        this.fechaIngresoCai = fechaIngresoCai;
    }

    public String getTipoMedida() {
        return tipoMedida;
    }

    public void setTipoMedida(String tipoMedida) {
        this.tipoMedida = tipoMedida;
    }

    public String getMedidaCautelar() {
        return medidaCautelar;
    }

    public void setMedidaCautelar(String medidaCautelar) {
        this.medidaCautelar = medidaCautelar;
    }

    public Date getAlertaSalidaIntermediaPreventiva() {
        return alertaSalidaIntermediaPreventiva;
    }

    public void setAlertaSalidaIntermediaPreventiva(Date alertaSalidaIntermediaPreventiva) {
        this.alertaSalidaIntermediaPreventiva = alertaSalidaIntermediaPreventiva;
    }

    public Integer getAnios() {
        return anios;
    }

    public void setAnios(Integer anios) {
        this.anios = anios;
    }

    public Integer getMeses() {
        return meses;
    }

    public void setMeses(Integer meses) {
        this.meses = meses;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Integer getTiempoSentenDias() {
        return tiempoSentenDias;
    }

    public void setTiempoSentenDias(Integer tiempoSentenDias) {
        this.tiempoSentenDias = tiempoSentenDias;
    }

    public Integer getTiempoPrivacionLibertad() {
        return tiempoPrivacionLibertad;
    }

    public void setTiempoPrivacionLibertad(Integer tiempoPrivacionLibertad) {
        this.tiempoPrivacionLibertad = tiempoPrivacionLibertad;
    }

    public String getObservacionesProcesoJudicial() {
        return observacionesProcesoJudicial;
    }

    public void setObservacionesProcesoJudicial(String observacionesProcesoJudicial) {
        this.observacionesProcesoJudicial = observacionesProcesoJudicial;
    }

    public CAI getIdCai() {
        return idCai;
    }

    public void setIdCai(CAI idCai) {
        this.idCai = idCai;
    }

 }
