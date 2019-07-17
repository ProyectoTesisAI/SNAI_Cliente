package epn.edu.ec.modelo;

import java.io.Serializable;

public class EjeLaboral implements Serializable {

    private AdolescenteInfractorUDI idAdolescenteInfractorUDI;
    private Boolean trabaja;
    private String ocupacionAdolescente;
    private String ramaActividadEconomica;
    private String descripcionActividad;
    private Integer numeroHorasTrabajo;
    private String ingresoMensual;
    private Boolean afiliacionIess;
    private String inclinacionVocacion;

    public EjeLaboral() {
    }

    public AdolescenteInfractorUDI getIdAdolescenteInfractorUDI() {
        return idAdolescenteInfractorUDI;
    }

    public void setIdAdolescenteInfractorUDI(AdolescenteInfractorUDI idAdolescenteInfractorUDI) {
        this.idAdolescenteInfractorUDI = idAdolescenteInfractorUDI;
    }

    public Boolean getTrabaja() {
        return trabaja;
    }

    public void setTrabaja(Boolean trabaja) {
        this.trabaja = trabaja;
    }

    public String getOcupacionAdolescente() {
        return ocupacionAdolescente;
    }

    public void setOcupacionAdolescente(String ocupacionAdolescente) {
        this.ocupacionAdolescente = ocupacionAdolescente;
    }

    public String getRamaActividadEconomica() {
        return ramaActividadEconomica;
    }

    public void setRamaActividadEconomica(String ramaActividadEconomica) {
        this.ramaActividadEconomica = ramaActividadEconomica;
    }

    public String getDescripcionActividad() {
        return descripcionActividad;
    }

    public void setDescripcionActividad(String descripcionActividad) {
        this.descripcionActividad = descripcionActividad;
    }

    public Integer getNumeroHorasTrabajo() {
        return numeroHorasTrabajo;
    }

    public void setNumeroHorasTrabajo(Integer numeroHorasTrabajo) {
        this.numeroHorasTrabajo = numeroHorasTrabajo;
    }

    public String getIngresoMensual() {
        return ingresoMensual;
    }

    public void setIngresoMensual(String ingresoMensual) {
        this.ingresoMensual = ingresoMensual;
    }

    public Boolean getAfiliacionIess() {
        return afiliacionIess;
    }

    public void setAfiliacionIess(Boolean afiliacionIess) {
        this.afiliacionIess = afiliacionIess;
    }

    public String getInclinacionVocacion() {
        return inclinacionVocacion;
    }

    public void setInclinacionVocacion(String inclinacionVocacion) {
        this.inclinacionVocacion = inclinacionVocacion;
    }

    @Override
    public String toString() {
        return "EjeLaboral{" + "idEjeLaboral=" + idAdolescenteInfractorUDI + ", trabaja=" + trabaja + ", ocupacionAdolescente=" + ocupacionAdolescente + ", ramaActividadEconomica=" + ramaActividadEconomica + ", descripcionActividad=" + descripcionActividad + ", numeroHorasTrabajo=" + numeroHorasTrabajo + ", ingresoMensual=" + ingresoMensual + ", afiliacionIess=" + afiliacionIess + ", inclinacionVocacion=" + inclinacionVocacion + '}';
    }
    
}
