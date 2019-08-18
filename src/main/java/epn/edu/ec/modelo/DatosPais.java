package epn.edu.ec.modelo;

import java.io.Serializable;

public class DatosPais implements Serializable {

    private Integer idPais;
    private String pais;

    public DatosPais() {
    }

    public DatosPais(Integer idPais) {
        this.idPais = idPais;
    }

    public DatosPais(Integer idPais, String pais) {
        this.idPais = idPais;
        this.pais = pais;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    
}
