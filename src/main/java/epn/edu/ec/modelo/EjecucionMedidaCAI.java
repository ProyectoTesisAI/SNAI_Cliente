package epn.edu.ec.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EjecucionMedidaCAI implements Serializable {

    private Integer idEjecucionMedidaCai;    
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
    private DetalleInfraccionCAI idDetalleInfraccionCAI;


    public EjecucionMedidaCAI() {
    }

    public Integer getIdEjecucionMedidaCai() {
        return idEjecucionMedidaCai;
    }

    public void setIdEjecucionMedidaCai(Integer idEjecucionMedidaCai) {
        this.idEjecucionMedidaCai = idEjecucionMedidaCai;
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
        if (fechaAprehension != null) {
            Calendar fechaAux = Calendar.getInstance();
            fechaAux.setTime(fechaAprehension);
            fechaAux.add(Calendar.DATE, 90);
            alertaSalidaIntermediaPreventiva = fechaAux.getTime();
            return alertaSalidaIntermediaPreventiva;
        }
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
        int acumulador = 0;
        int a = 0;
        int m = 0;
        int d=0;
        if (anios != null) {
            a = anios * 365;
        }
        if (meses != null) {
            m = meses * 30;
        }
        
        if (dias != null) {
            d=dias;
        }
          
        acumulador=a+m+d;
        tiempoSentenDias = acumulador;
        return tiempoSentenDias;
    }

    public void setTiempoSentenDias(Integer tiempoSentenDias) {
        this.tiempoSentenDias = tiempoSentenDias;
    }

    public Integer getTiempoPrivacionLibertad() {
        int a = 0;
        int d = 0;
        int m = 0;
        int ac = 0;
        if (fechaAprehension != null && fechaIngresoCai!=null) {
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss z uuuu").withLocale(Locale.US);
            ZonedDateTime zdt1 = ZonedDateTime.parse(fechaAprehension.toString(), dtf);
            LocalDate ld1 = zdt1.toLocalDate();

            ZonedDateTime zdt2 = ZonedDateTime.parse(fechaIngresoCai.toString(), dtf);
            LocalDate ld2 = zdt2.toLocalDate();

            DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fecha1 = ld1.format(fmt1);
            LocalDate fechaA = LocalDate.parse(fecha1, fmt1);

            DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fecha2 = ld2.format(fmt2);
            LocalDate fechaR = LocalDate.parse(fecha2, fmt2);

            Period periodo = Period.between(fechaR, fechaA);
            d = periodo.getDays() * -1;
            m = periodo.getMonths() * -1;
            a = periodo.getYears() * -1;
            tiempoPrivacionLibertad = d + (m * 30) + (a * 365);           
            return tiempoPrivacionLibertad;
        }
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

    public DetalleInfraccionCAI getIdDetalleInfraccionCAI() {
        return idDetalleInfraccionCAI;
    }

    public void setIdDetalleInfraccionCAI(DetalleInfraccionCAI idDetalleInfraccionCAI) {
        this.idDetalleInfraccionCAI = idDetalleInfraccionCAI;
    }

 }
