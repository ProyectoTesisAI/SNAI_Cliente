package epn.edu.ec.modelo;

import java.io.Serializable;


public class IdentificacionGeografica implements Serializable {

    private AdolescenteInfractor idAdolescenteInfractor;
    private String paisNacimiento;
    private String estadoOProvinciaNacimiento;
    private String ciudadOCantonNacimiento;
    private String provinciaResidencia;
    private String cantonResidencia;
    private String parroquiaResidencia;
    private String direccionDomicilio;
    private String referenciaDomicilio;
    private String telefono;
    
    public IdentificacionGeografica() {
    }

    public AdolescenteInfractor getIdAdolescenteInfractor() {
        return idAdolescenteInfractor;
    }

    public void setIdAdolescenteInfractor(AdolescenteInfractor idAdolescenteInfractor) {
        this.idAdolescenteInfractor = idAdolescenteInfractor;
    }

    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    public String getEstadoOProvinciaNacimiento() {
        return estadoOProvinciaNacimiento;
    }

    public void setEstadoOProvinciaNacimiento(String estadoOProvinciaNacimiento) {
        this.estadoOProvinciaNacimiento = estadoOProvinciaNacimiento;
    }

    public String getCiudadOCantonNacimiento() {
        return ciudadOCantonNacimiento;
    }

    public void setCiudadOCantonNacimiento(String ciudadOCantonNacimiento) {
        this.ciudadOCantonNacimiento = ciudadOCantonNacimiento;
    }

    public String getProvinciaResidencia() {
        return provinciaResidencia;
    }

    public void setProvinciaResidencia(String provinciaResidencia) {
        this.provinciaResidencia = provinciaResidencia;
    }

    public String getCantonResidencia() {
        return cantonResidencia;
    }

    public void setCantonResidencia(String cantonResidencia) {
        this.cantonResidencia = cantonResidencia;
    }

    public String getParroquiaResidencia() {
        return parroquiaResidencia;
    }

    public void setParroquiaResidencia(String parroquiaResidencia) {
        this.parroquiaResidencia = parroquiaResidencia;
    }

    public String getDireccionDomicilio() {
        return direccionDomicilio;
    }

    public void setDireccionDomicilio(String direccionDomicilio) {
        this.direccionDomicilio = direccionDomicilio;
    }

    public String getReferenciaDomicilio() {
        return referenciaDomicilio;
    }

    public void setReferenciaDomicilio(String referenciaDomicilio) {
        this.referenciaDomicilio = referenciaDomicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
