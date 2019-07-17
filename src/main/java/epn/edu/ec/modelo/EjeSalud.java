/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.modelo;

import java.io.Serializable;

public class EjeSalud implements Serializable {

    private AdolescenteInfractor idAdolescenteInfractor;
    private String situacionSalud;
    private String diagnosticoEnfermedad;
    private Boolean recibeTratamiento;
    private Boolean tomaMedicacion;
    private String numeroHistoriaClinica;
    private String embarazo;
    private String tiempoGestacionMes;
    private Boolean consumeSustancias;
    private String tipoSustancia;
    private Boolean recibeTratamientoDrogas;
    private String discapacidad;
    private String tipoDiscapacidad;
    private Integer porcentajeDiscapacidad;
    private Boolean enfermadadesCatastroficas;

    public EjeSalud() {
    }

    public AdolescenteInfractor getIdAdolescenteInfractor() {
        return idAdolescenteInfractor;
    }

    public void setIdAdolescenteInfractor(AdolescenteInfractor idAdolescenteInfractor) {
        this.idAdolescenteInfractor = idAdolescenteInfractor;
    }

    public String getSituacionSalud() {
        return situacionSalud;
    }

    public void setSituacionSalud(String situacionSalud) {
        this.situacionSalud = situacionSalud;
    }

    public String getDiagnosticoEnfermedad() {
        return diagnosticoEnfermedad;
    }

    public void setDiagnosticoEnfermedad(String diagnosticoEnfermedad) {
        this.diagnosticoEnfermedad = diagnosticoEnfermedad;
    }

    public Boolean getRecibeTratamiento() {
        return recibeTratamiento;
    }

    public void setRecibeTratamiento(Boolean recibeTratamiento) {
        this.recibeTratamiento = recibeTratamiento;
    }

    public Boolean getTomaMedicacion() {
        return tomaMedicacion;
    }

    public void setTomaMedicacion(Boolean tomaMedicacion) {
        this.tomaMedicacion = tomaMedicacion;
    }

    public String getNumeroHistoriaClinica() {
        return numeroHistoriaClinica;
    }

    public void setNumeroHistoriaClinica(String numeroHistoriaClinica) {
        this.numeroHistoriaClinica = numeroHistoriaClinica;
    }

    public String getEmbarazo() {
        return embarazo;
    }

    public void setEmbarazo(String embarazo) {
        this.embarazo = embarazo;
    }

    public String getTiempoGestacionMes() {
        return tiempoGestacionMes;
    }

    public void setTiempoGestacionMes(String tiempoGestacionMes) {
        this.tiempoGestacionMes = tiempoGestacionMes;
    }

    public Boolean getConsumeSustancias() {
        return consumeSustancias;
    }

    public void setConsumeSustancias(Boolean consumeSustancias) {
        this.consumeSustancias = consumeSustancias;
    }

    public String getTipoSustancia() {
        return tipoSustancia;
    }

    public void setTipoSustancia(String tipoSustancia) {
        this.tipoSustancia = tipoSustancia;
    }

    public Boolean getRecibeTratamientoDrogas() {
        return recibeTratamientoDrogas;
    }

    public void setRecibeTratamientoDrogas(Boolean recibeTratamientoDrogas) {
        this.recibeTratamientoDrogas = recibeTratamientoDrogas;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    public String getTipoDiscapacidad() {
        return tipoDiscapacidad;
    }

    public void setTipoDiscapacidad(String tipoDiscapacidad) {
        this.tipoDiscapacidad = tipoDiscapacidad;
    }

    public Integer getPorcentajeDiscapacidad() {
        return porcentajeDiscapacidad;
    }

    public void setPorcentajeDiscapacidad(Integer porcentajeDiscapacidad) {
        this.porcentajeDiscapacidad = porcentajeDiscapacidad;
    }

    public Boolean getEnfermadadesCatastroficas() {
        return enfermadadesCatastroficas;
    }

    public void setEnfermadadesCatastroficas(Boolean enfermadadesCatastroficas) {
        this.enfermadadesCatastroficas = enfermadadesCatastroficas;
    }

    
    
}
