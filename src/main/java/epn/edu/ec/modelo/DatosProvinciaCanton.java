package epn.edu.ec.modelo;

import java.io.Serializable;

public class DatosProvinciaCanton implements Serializable {

    private Integer idDatosProvinciaCanton;
    private String provincia;
    private String canton;

    public DatosProvinciaCanton() {
    }

    public DatosProvinciaCanton(Integer idDatosProvinciaCanton) {
        this.idDatosProvinciaCanton = idDatosProvinciaCanton;
    }

    public DatosProvinciaCanton(Integer idDatosProvinciaCanton, String provincia, String canton) {
        this.idDatosProvinciaCanton = idDatosProvinciaCanton;
        this.provincia = provincia;
        this.canton = canton;
    }

    public Integer getIdDatosProvinciaCanton() {
        return idDatosProvinciaCanton;
    }

    public void setIdDatosProvinciaCanton(Integer idDatosProvinciaCanton) {
        this.idDatosProvinciaCanton = idDatosProvinciaCanton;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    @Override
    public String toString() {
        return "DatosProvinciaCanton{" + "idDatosProvinciaCanton=" + idDatosProvinciaCanton + ", provincia=" + provincia + ", canton=" + canton + '}';
    }
    
}
