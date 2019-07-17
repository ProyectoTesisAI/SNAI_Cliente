package epn.edu.ec.modelo;

import java.io.Serializable;

public class AdolescenteInfractorUDI implements Serializable {

    private AdolescenteInfractor idAdolescenteInfractor;
    private String conQuienVive;

    public AdolescenteInfractorUDI() {
    }

    public AdolescenteInfractor getIdAdolescenteInfractor() {
        return idAdolescenteInfractor;
    }

    public void setIdAdolescenteInfractor(AdolescenteInfractor idAdolescenteInfractor) {
        this.idAdolescenteInfractor = idAdolescenteInfractor;
    }

    public String getConQuienVive() {
        return conQuienVive;
    }

    public void setConQuienVive(String conQuienVive) {
        this.conQuienVive = conQuienVive;
    }

    

}
