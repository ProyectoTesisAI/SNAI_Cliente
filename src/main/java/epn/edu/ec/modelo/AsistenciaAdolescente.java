/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.modelo;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class AsistenciaAdolescente implements Serializable {

    private Integer idAsistenciaAdolescente;
    private AdolescenteInfractor idAdolescenteInfractor;
    private RegistroAsistencia idRegistroAsistencia;
    private Boolean asistio;
    
    public AsistenciaAdolescente() {
    }

    public AsistenciaAdolescente(Integer idAsistenciaAdolescentePk) {
        this.idAsistenciaAdolescente = idAsistenciaAdolescentePk;
    }

    public Integer getIdAsistenciaAdolescente() {
        return idAsistenciaAdolescente;
    }

    public void setIdAsistenciaAdolescente(Integer idAsistenciaAdolescente) {
        this.idAsistenciaAdolescente = idAsistenciaAdolescente;
    }

    public Boolean getAsistio() {
        return asistio;
    }

    public void setAsistio(Boolean asistio) {
        this.asistio = asistio;
    }

    public AdolescenteInfractor getIdAdolescenteInfractor() {
        return idAdolescenteInfractor;
    }

    public void setIdAdolescenteInfractor(AdolescenteInfractor idAdolescenteInfractor) {
        this.idAdolescenteInfractor = idAdolescenteInfractor;
    }

    public RegistroAsistencia getIdRegistroAsistencia() {
        return idRegistroAsistencia;
    }

    public void setIdRegistroAsistencia(RegistroAsistencia idRegistroAsistencia) {
        this.idRegistroAsistencia = idRegistroAsistencia;
    }

    @Override
    public String toString() {
        return "epn.edu.ec.entidades.AsistenciaAdolescente[ idAsistenciaAdolescentePk=" + idAsistenciaAdolescente + " ]";
    }
    
}
