package epn.edu.ec.modelo;

import java.io.Serializable;
import java.util.Date;

public class MotivoEgresoCAI implements Serializable {

    private EjecucionMedidaCAI idEjecucionMedidaCAI;
    private String motivoSalida;
    private Date fechaSalidaCentro;
    private CAI idCaiTraslado;

    public MotivoEgresoCAI() {
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

    public EjecucionMedidaCAI getIdEjecucionMedidaCAI() {
        return idEjecucionMedidaCAI;
    }

    public void setIdEjecucionMedidaCAI(EjecucionMedidaCAI idEjecucionMedidaCAI) {
        this.idEjecucionMedidaCAI = idEjecucionMedidaCAI;
    }    
}
