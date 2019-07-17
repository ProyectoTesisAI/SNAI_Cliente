package epn.edu.ec.modelo;

import java.io.Serializable;
import java.util.Date;

public class MotivoEgresoCAI implements Serializable {

    private AdolescenteInfractorCAI idAdolescenteInfractorCAI;
    private String motivoSalida;
    private Date fechaSalidaCentro;
    private CAI idCaiTraslado;

    public MotivoEgresoCAI() {
    }

    public AdolescenteInfractorCAI getIdAdolescenteInfractorCAI() {
        return idAdolescenteInfractorCAI;
    }

    public void setIdAdolescenteInfractorCAI(AdolescenteInfractorCAI idAdolescenteInfractorCAI) {
        this.idAdolescenteInfractorCAI = idAdolescenteInfractorCAI;
    }

    public String getMotivoSalida() {
        return motivoSalida;
    }

    public void setMotivoSalida(String motivoSalida) {
        this.motivoSalida = motivoSalida;
    }

    public Date getFechaSalidaCentro() {
        return fechaSalidaCentro;
    }

    public void setFechaSalidaCentro(Date fechaSalidaCentro) {
        this.fechaSalidaCentro = fechaSalidaCentro;
    }

    public CAI getIdCaiTraslado() {
        return idCaiTraslado;
    }

    public void setIdCaiTraslado(CAI idCaiTraslado) {
        this.idCaiTraslado = idCaiTraslado;
    }

    @Override
    public String toString() {
        return "MotivoEgresoCAI{" + "idMotivoEgreso=" + idAdolescenteInfractorCAI + ", motivoSalida=" + motivoSalida + ", fechaSalidaCentro=" + fechaSalidaCentro + ", caiTrasladoFk=" + idCaiTraslado + '}';
    }
    
}
