package epn.edu.ec.modelo;

import java.io.Serializable;
import java.util.Date;


public class InformacionJudicial implements Serializable {

    private AdolescenteInfractorUDI idAdolescenteInfractorUDI;
    private Boolean reincidencia;
    private String nombreFiscal;
    private String unidadJudicial;
    private String nombreUnidadJudicial;
    private String nombreJuez;
    private String defensor;
    private String nombreDefensor;
    private String numeroCausa;
    private Date fechaImposicionMedida;
    private Date fechaRecepcionZonalResolucion;
    private Date fechaInicioCumplimientoMedida;
    private String formaImposicionMedida;
    private String tipoTerminacionAnticipadaMedida;
    private Boolean amonestacionVerbal;
    private Boolean imposicionReglasConducta;
    private Boolean orientacionApoyoSocioFamiliar;
    private Boolean servicioComunidad;
    private Boolean libertadAsistida;
    private Integer numeroMedidas;
    
    public InformacionJudicial() {
    }

    public AdolescenteInfractorUDI getIdAdolescenteInfractorUDI() {
        return idAdolescenteInfractorUDI;
    }

    public void setIdAdolescenteInfractorUDI(AdolescenteInfractorUDI idAdolescenteInfractorUDI) {
        this.idAdolescenteInfractorUDI = idAdolescenteInfractorUDI;
    }

    public Boolean getReincidencia() {
        return reincidencia;
    }

    public void setReincidencia(Boolean reincidencia) {
        this.reincidencia = reincidencia;
    }

    public String getNombreFiscal() {
        return nombreFiscal;
    }

    public void setNombreFiscal(String nombreFiscal) {
        this.nombreFiscal = nombreFiscal;
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

    public String getNombreJuez() {
        return nombreJuez;
    }

    public void setNombreJuez(String nombreJuez) {
        this.nombreJuez = nombreJuez;
    }

    public String getDefensor() {
        return defensor;
    }

    public void setDefensor(String defensor) {
        this.defensor = defensor;
    }

    public String getNombreDefensor() {
        return nombreDefensor;
    }

    public void setNombreDefensor(String nombreDefensor) {
        this.nombreDefensor = nombreDefensor;
    }

    public String getNumeroCausa() {
        return numeroCausa;
    }

    public void setNumeroCausa(String numeroCausa) {
        this.numeroCausa = numeroCausa;
    }

    public Date getFechaImposicionMedida() {
        return fechaImposicionMedida;
    }

    public void setFechaImposicionMedida(Date fechaImposicionMedida) {
        this.fechaImposicionMedida = fechaImposicionMedida;
    }

    public Date getFechaRecepcionZonalResolucion() {
        return fechaRecepcionZonalResolucion;
    }

    public void setFechaRecepcionZonalResolucion(Date fechaRecepcionZonalResolucion) {
        this.fechaRecepcionZonalResolucion = fechaRecepcionZonalResolucion;
    }

    public Date getFechaInicioCumplimientoMedida() {
        return fechaInicioCumplimientoMedida;
    }

    public void setFechaInicioCumplimientoMedida(Date fechaInicioCumplimientoMedida) {
        this.fechaInicioCumplimientoMedida = fechaInicioCumplimientoMedida;
    }

    public String getFormaImposicionMedida() {
        return formaImposicionMedida;
    }

    public void setFormaImposicionMedida(String formaImposicionMedida) {
        this.formaImposicionMedida = formaImposicionMedida;
    }

    public String getTipoTerminacionAnticipadaMedida() {
        return tipoTerminacionAnticipadaMedida;
    }

    public void setTipoTerminacionAnticipadaMedida(String tipoTerminacionAnticipadaMedida) {
        this.tipoTerminacionAnticipadaMedida = tipoTerminacionAnticipadaMedida;
    }

    public Boolean getAmonestacionVerbal() {
        return amonestacionVerbal;
    }

    public void setAmonestacionVerbal(Boolean amonestacionVerbal) {
        this.amonestacionVerbal = amonestacionVerbal;
    }

    public Boolean getImposicionReglasConducta() {
        return imposicionReglasConducta;
    }

    public void setImposicionReglasConducta(Boolean imposicionReglasConducta) {
        this.imposicionReglasConducta = imposicionReglasConducta;
    }

    public Boolean getOrientacionApoyoSocioFamiliar() {
        return orientacionApoyoSocioFamiliar;
    }

    public void setOrientacionApoyoSocioFamiliar(Boolean orientacionApoyoSocioFamiliar) {
        this.orientacionApoyoSocioFamiliar = orientacionApoyoSocioFamiliar;
    }
        
    public Boolean getServicioComunidad() {
        return servicioComunidad;
    }

    public void setServicioComunidad(Boolean servicioComunidad) {
        this.servicioComunidad = servicioComunidad;
    }

    public Boolean getLibertadAsistida() {
        return libertadAsistida;
    }

    public void setLibertadAsistida(Boolean libertadAsistida) {
        this.libertadAsistida = libertadAsistida;
    }

    public Integer getNumeroMedidas() {
        return numeroMedidas;
    }

    public void setNumeroMedidas(Integer numeroMedidas) {
        this.numeroMedidas = numeroMedidas;
    }

    @Override
    public String toString() {
        return "InformacionJudicial{" + "idInformacionJudicial=" + idAdolescenteInfractorUDI + ", reincidencia=" + reincidencia + ", nombreFiscal=" + nombreFiscal + ", unidadJudicial=" + unidadJudicial + ", nombreJuez=" + nombreJuez + ", defensor=" + defensor + ", nombreDefensor=" + nombreDefensor + ", numeroCausa=" + numeroCausa + ", fechaImposicionMedida=" + fechaImposicionMedida + ", fechaRecepcionZonalResolucion=" + fechaRecepcionZonalResolucion + ", fechaInicioCumplimientoMedida=" + fechaInicioCumplimientoMedida + ", formaImposicionMedida=" + formaImposicionMedida + ", tipoTerminacionAnticipadaMedida=" + tipoTerminacionAnticipadaMedida + ", amonestacionVerbal=" + amonestacionVerbal + ", imposicionReglasConducta=" + imposicionReglasConducta + ", orientacionApoyoSocioFamiliar=" + orientacionApoyoSocioFamiliar + ", servicioComunidad=" + servicioComunidad + ", libertadAsistida=" + libertadAsistida + ", numeroMedidas=" + numeroMedidas + '}';
    }
    
}
