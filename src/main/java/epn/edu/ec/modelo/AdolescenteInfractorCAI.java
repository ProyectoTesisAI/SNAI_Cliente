package epn.edu.ec.modelo;

import java.io.Serializable;
import java.util.Date;

public class AdolescenteInfractorCAI implements Serializable {

    private AdolescenteInfractor idAdolescenteInfractor;
    private Date fechaIngresoProceso;
    private String observacionIngreso;
    
    public AdolescenteInfractorCAI() {
    }

    public AdolescenteInfractor getIdAdolescenteInfractor() {
        return idAdolescenteInfractor;
    }

    public void setIdAdolescenteInfractor(AdolescenteInfractor idAdolescenteInfractor) {
        this.idAdolescenteInfractor = idAdolescenteInfractor;
    }

    public Date getFechaIngresoProceso() {
        return fechaIngresoProceso;
    }

    public void setFechaIngresoProceso(Date fechaIngresoProceso) {
        this.fechaIngresoProceso = fechaIngresoProceso;
    }

    public String getObservacionIngreso() {
        return observacionIngreso;
    }

    public void setObservacionIngreso(String observacionIngreso) {
        this.observacionIngreso = observacionIngreso;
    }
    
}
