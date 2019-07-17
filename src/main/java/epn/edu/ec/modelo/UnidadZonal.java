package epn.edu.ec.modelo;

import java.io.Serializable;

public class UnidadZonal implements Serializable {

    private AdolescenteInfractorUDI idUnidadZonal;
    private String numeroExpediente;
    private UDI idUdi;

    public UnidadZonal() {
    }

    public AdolescenteInfractorUDI getIdUnidadZonal() {
        return idUnidadZonal;
    }

    public void setIdUnidadZonal(AdolescenteInfractorUDI idUnidadZonal) {
        this.idUnidadZonal = idUnidadZonal;
    }


    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public UDI getIdUdi() {
        return idUdi;
    }

    public void setIdUdi(UDI idUdi) {
        this.idUdi = idUdi;
    }

    @Override
    public String toString() {
        return "UnidadZonal{" + "idUnidadZonal=" + idUnidadZonal + ", numeroExpediente=" + numeroExpediente + ", idUdi=" + idUdi + '}';
    }

}
