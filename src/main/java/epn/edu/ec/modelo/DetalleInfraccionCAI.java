package epn.edu.ec.modelo;

import java.io.Serializable;

public class DetalleInfraccionCAI implements Serializable {

    private Integer idDetalleInfraccion;
    private String tipoPenal;
    private String provinciaInfraccion;
    private String cantonInfraccion;
    private String unidadJudicial;
    private String nombreUnidadJudicial;
    private String numeroCausa;
    private String nombreJuez;
    private AdolescenteInfractorCAI idAdolescenteInfractorCAI;

    public DetalleInfraccionCAI() {
    }

    public Integer getIdDetalleInfraccion() {
        return idDetalleInfraccion;
    }

    public void setIdDetalleInfraccion(Integer idDetalleInfraccion) {
        this.idDetalleInfraccion = idDetalleInfraccion;
    }

    public String getTipoPenal() {
        return tipoPenal;
    }

    public void setTipoPenal(String tipoPenal) {
        this.tipoPenal = tipoPenal;
    }

    public String getProvinciaInfraccion() {
        return provinciaInfraccion;
    }

    public void setProvinciaInfraccion(String provinciaInfraccion) {
        this.provinciaInfraccion = provinciaInfraccion;
    }

    public String getCantonInfraccion() {
        return cantonInfraccion;
    }

    public void setCantonInfraccion(String cantonInfraccion) {
        this.cantonInfraccion = cantonInfraccion;
    }

    public String getUnidadJudicial() {
        return unidadJudicial;
    }

    public void setUnidadJudicial(String unidadJudicial) {
        this.unidadJudicial = unidadJudicial;
    }

    public String getNombreUnidadJudicial() {
        return nombreUnidadJudicial;
    }

    public void setNombreUnidadJudicial(String nombreUnidadJudicial) {
        this.nombreUnidadJudicial = nombreUnidadJudicial;
    }

    public String getNumeroCausa() {
        return numeroCausa;
    }

    public void setNumeroCausa(String numeroCausa) {
        this.numeroCausa = numeroCausa;
    }

    public String getNombreJuez() {
        return nombreJuez;
    }

    public void setNombreJuez(String nombreJuez) {
        this.nombreJuez = nombreJuez;
    }

    public AdolescenteInfractorCAI getIdAdolescenteInfractorCAI() {
        return idAdolescenteInfractorCAI;
    }

    public void setIdAdolescenteInfractorCAI(AdolescenteInfractorCAI idAdolescenteInfractorCAI) {
        this.idAdolescenteInfractorCAI = idAdolescenteInfractorCAI;
    }
}
