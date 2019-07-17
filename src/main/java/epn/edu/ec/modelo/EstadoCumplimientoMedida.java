package epn.edu.ec.modelo;

import java.io.Serializable;
import java.util.Date;

public class EstadoCumplimientoMedida implements Serializable {

    private AdolescenteInfractorUDI idAdolescenteInfractorUDI;
    private String situacionActual;
    private Date fechaReporteCulminacion;
    private Date fechaReporteDerivacion;   
    private Date fechaReporteIncumplimiento;
    private String estadoIncumplimiento;
    private Boolean reanudacionMedida;
    private Date fechaReanudacion;
    private CAI caiReceptoraDerivacion;
    private UDI uzdiReceptoraDerivacion;
    
    public EstadoCumplimientoMedida() {
    }

    public AdolescenteInfractorUDI getIdAdolescenteInfractorUDI() {
        return idAdolescenteInfractorUDI;
    }

    public void setIdAdolescenteInfractorUDI(AdolescenteInfractorUDI idAdolescenteInfractorUDI) {
        this.idAdolescenteInfractorUDI = idAdolescenteInfractorUDI;
    }

    public String getSituacionActual() {
        return situacionActual;
    }

    public void setSituacionActual(String situacionActual) {
        this.situacionActual = situacionActual;
    }

    public Date getFechaReporteCulminacion() {
        return fechaReporteCulminacion;
    }

    public void setFechaReporteCulminacion(Date fechaReporteCulminacion) {
        this.fechaReporteCulminacion = fechaReporteCulminacion;
    }

    public Date getFechaReporteDerivacion() {
        return fechaReporteDerivacion;
    }

    public void setFechaReporteDerivacion(Date fechaReporteDerivacion) {
        this.fechaReporteDerivacion = fechaReporteDerivacion;
    }

    public Date getFechaReporteIncumplimiento() {
        return fechaReporteIncumplimiento;
    }

    public void setFechaReporteIncumplimiento(Date fechaReporteIncumplimiento) {
        this.fechaReporteIncumplimiento = fechaReporteIncumplimiento;
    }

    public String getEstadoIncumplimiento() {
        return estadoIncumplimiento;
    }

    public void setEstadoIncumplimiento(String estadoIncumplimiento) {
        this.estadoIncumplimiento = estadoIncumplimiento;
    }

    public Boolean getReanudacionMedida() {
        return reanudacionMedida;
    }

    public void setReanudacionMedida(Boolean reanudacionMedida) {
        this.reanudacionMedida = reanudacionMedida;
    }

    public Date getFechaReanudacion() {
        return fechaReanudacion;
    }

    public void setFechaReanudacion(Date fechaReanudacion) {
        this.fechaReanudacion = fechaReanudacion;
    }

    public CAI getCaiReceptoraDerivacion() {
        return caiReceptoraDerivacion;
    }

    public void setCaiReceptoraDerivacion(CAI caiReceptoraDerivacion) {
        this.caiReceptoraDerivacion = caiReceptoraDerivacion;
    }

    public UDI getUzdiReceptoraDerivacion() {
        return uzdiReceptoraDerivacion;
    }

    public void setUzdiReceptoraDerivacion(UDI uzdiReceptoraDerivacion) {
        this.uzdiReceptoraDerivacion = uzdiReceptoraDerivacion;
    }

    
}
