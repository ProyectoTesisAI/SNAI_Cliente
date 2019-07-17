package epn.edu.ec.modelo;

import java.io.Serializable;


public class Representante implements Serializable {

    private AdolescenteInfractor idAdolescenteInfracto;
    private String nacionalidad;
    private String cedula;
    private String documento;
    private String nombres;
    private String apellidos;
    private String parentesco;
    private String numeroContacto;

    public Representante() {
    }

    public AdolescenteInfractor getIdAdolescenteInfracto() {
        return idAdolescenteInfracto;
    }

    public void setIdAdolescenteInfracto(AdolescenteInfractor idAdolescenteInfracto) {
        this.idAdolescenteInfracto = idAdolescenteInfracto;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    

    
}
