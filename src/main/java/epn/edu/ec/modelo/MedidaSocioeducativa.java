package epn.edu.ec.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


public class MedidaSocioeducativa implements Serializable {
    
    private Integer idMedidaSocioeducativa;
    private String medidaSocioeducativa;
    private Integer tiempoMeses;
    private Integer tiempoDia;
    private Integer tiempoHoras;
    private Boolean cumplioMedida;
    private Date fechaFinMedida;
    private String institucionEjecutoraMedida;
    private AdolescenteInfractorUDI idAdolescenteInfractorUDI;
    
    private Date fechaInicioMedida;

    public MedidaSocioeducativa() {
        tiempoMeses=0;
        tiempoDia=0;
        tiempoHoras=0;
    }

    public Integer getIdMedidaSocioeducativa() {
        return idMedidaSocioeducativa;
    }

    public void setIdMedidaSocioeducativa(Integer idMedidaSocioeducativa) {
        this.idMedidaSocioeducativa = idMedidaSocioeducativa;
    }

    public String getMedidaSocioeducativa() {
        return medidaSocioeducativa;
    }

    public void setMedidaSocioeducativa(String medidaSocioeducativa) {
        this.medidaSocioeducativa = medidaSocioeducativa;
    }

    public Integer getTiempoMeses() {
        return tiempoMeses;
    }

    public void setTiempoMeses(Integer tiempoMeses) {
        this.tiempoMeses = tiempoMeses;
    }

    public Integer getTiempoDia() {
        return tiempoDia;
    }

    public void setTiempoDia(Integer tiempoDia) {
        this.tiempoDia = tiempoDia;
    }

    public Integer getTiempoHoras() {
        return tiempoHoras;
    }

    public void setTiempoHoras(Integer tiempoHoras) {
        this.tiempoHoras = tiempoHoras;
    }

    public Boolean getCumplioMedida() {
        return cumplioMedida;
    }

    public void setCumplioMedida(Boolean cumplioMedida) {
        this.cumplioMedida = cumplioMedida;
    }

    public Date getFechaFinMedida() {
        if(fechaInicioMedida!=null){
            int meses = tiempoMeses*30;
            int dias = tiempoDia;
            int horas = tiempoHoras/24;
            int total = meses+dias+horas;
            Calendar fechaAux = Calendar.getInstance();
            fechaAux.setTime(fechaInicioMedida);
            fechaAux.add(Calendar.DATE, total);
            fechaFinMedida = fechaAux.getTime();
            return fechaFinMedida;
        }
        return fechaFinMedida;
    }

    public void setFechaFinMedida(Date fechaFinMedida) {
        this.fechaFinMedida = fechaFinMedida;
    }

    public String getInstitucionEjecutoraMedida() {
        return institucionEjecutoraMedida;
    }

    public void setInstitucionEjecutoraMedida(String institucionEjecutoraMedida) {
        this.institucionEjecutoraMedida = institucionEjecutoraMedida;
    }

    public AdolescenteInfractorUDI getIdAdolescenteInfractorUDI() {
        return idAdolescenteInfractorUDI;
    }

    public void setIdAdolescenteInfractorUDI(AdolescenteInfractorUDI idAdolescenteInfractorUDI) {
        this.idAdolescenteInfractorUDI = idAdolescenteInfractorUDI;
    }

    public Date getFechaInicioMedida() {
        return fechaInicioMedida;
    }

    public void setFechaInicioMedida(Date fechaInicioMedida) {
        this.fechaInicioMedida = fechaInicioMedida;
    }

    @Override
    public String toString() {
        return "MedidaSocioeducativa{" + "idMedidaSocioeducativa=" + idMedidaSocioeducativa + ", medidaSocioeducativa=" + medidaSocioeducativa + ", tiempoMeses=" + tiempoMeses + ", tiempoDia=" + tiempoDia + ", tiempoHoras=" + tiempoHoras + ", cumplioMedida=" + cumplioMedida + ", fechaFinMedida=" + fechaFinMedida + ", institucionEjecutoraMedida=" + institucionEjecutoraMedida + ", idAdolescenteUdi=" + idAdolescenteInfractorUDI + '}';
    } 
    
}
