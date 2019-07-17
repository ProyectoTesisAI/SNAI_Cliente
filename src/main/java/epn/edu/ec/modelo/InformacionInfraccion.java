package epn.edu.ec.modelo;

import java.io.Serializable;

public class InformacionInfraccion implements Serializable {

    private AdolescenteInfractorUDI idAdolescenteInfractorUDI;
    private String provinciaDetencion;
    private String cantonDetencion;
    private String tipoInfraccion;
    
    public InformacionInfraccion() {
    }

    public AdolescenteInfractorUDI getIdAdolescenteInfractorUDI() {
        return idAdolescenteInfractorUDI;
    }

    public void setIdAdolescenteInfractorUDI(AdolescenteInfractorUDI idAdolescenteInfractorUDI) {
        this.idAdolescenteInfractorUDI = idAdolescenteInfractorUDI;
    }

    public String getProvinciaDetencion() {
        return provinciaDetencion;
    }

    public void setProvinciaDetencion(String provinciaDetencion) {
        this.provinciaDetencion = provinciaDetencion;
    }

    public String getCantonDetencion() {
        return cantonDetencion;
    }

    public void setCantonDetencion(String cantonDetencion) {
        this.cantonDetencion = cantonDetencion;
    }

    public String getTipoInfraccion() {
        return tipoInfraccion;
    }

    public void setTipoInfraccion(String tipoInfraccion) {
        this.tipoInfraccion = tipoInfraccion;
    }

    @Override
    public String toString() {
        return "InformacionInfraccion{" + "idInformacionInfraccion=" + idAdolescenteInfractorUDI + ", provinciaDetencion=" + provinciaDetencion + ", cantonDetencion=" + cantonDetencion + ", tipoInfraccion=" + tipoInfraccion + '}';
    }
   
}
