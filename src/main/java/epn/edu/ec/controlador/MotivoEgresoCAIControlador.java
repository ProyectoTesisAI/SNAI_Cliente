package epn.edu.ec.controlador;

import epn.edu.ec.modelo.CAI;
import epn.edu.ec.modelo.EjecucionMedidaCAI;
import epn.edu.ec.modelo.MotivoEgresoCAI;
import epn.edu.ec.servicios.CaiServicio;
import epn.edu.ec.servicios.MotivoEgresoCAIServicio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "motivoEgresoCAIControlador")
@ViewScoped
public class MotivoEgresoCAIControlador implements Serializable {

    private EjecucionMedidaCAI ejecucionMedidaCAI;
    private MotivoEgresoCAI motivoEgresoCAI;
    private MotivoEgresoCAIServicio servicio;
    private boolean guardado;

    private CAI cai;
    private List<CAI> listaCAI;
    private CaiServicio servicioCAI;

    //Variable para mostrar/ocultar panel TrasladoCAI
    private String tipoMotivoSalida;
    private boolean esTrasladoCAI;

    @PostConstruct
    public void init() {
        servicio = new MotivoEgresoCAIServicio();
        servicioCAI = new CaiServicio();

        motivoEgresoCAI = new MotivoEgresoCAI();
        guardado = false;
        esTrasladoCAI = false;

        cai = new CAI();
        listaCAI = new ArrayList<>();
        listaCAI = servicioCAI.listaCai();

        if (isEsTrasladoCAI()) {
            tipoMotivoSalida = "TRASLADO A OTRO CAI";
        }

        ejecucionMedidaCAI = new EjecucionMedidaCAI();

        EjecucionMedidaCAI ejecucionMedidaCAIAux = (EjecucionMedidaCAI) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ejecucion_medida_cai");

        if (ejecucionMedidaCAIAux != null) {

            ejecucionMedidaCAI = ejecucionMedidaCAIAux;

            MotivoEgresoCAI motivoEgresoCAIAux = servicio.obtenerMotivoEgresoCAI(ejecucionMedidaCAI.getIdEjecucionMedidaCai());

            if (motivoEgresoCAIAux != null) {

                motivoEgresoCAI = motivoEgresoCAIAux;
                tipoMotivoSalida = motivoEgresoCAI.getMotivoSalida();
                guardado = true;

                if (motivoEgresoCAI.getMotivoSalida().equals("TRASLADO A OTRO CAI")) {
                    esTrasladoCAI = true;
                    cai = motivoEgresoCAI.getIdCaiTraslado();
                } else {
                    esTrasladoCAI = false;
                }
            } else {
                motivoEgresoCAI = new MotivoEgresoCAI();
            }
        }
    }

    public EjecucionMedidaCAI getEjecucionMedidaCAI() {
        return ejecucionMedidaCAI;
    }

    public void setEjecucionMedidaCAI(EjecucionMedidaCAI ejecucionMedidaCAI) {
        this.ejecucionMedidaCAI = ejecucionMedidaCAI;
    }

    public String getTipoMotivoSalida() {
        return tipoMotivoSalida;
    }

    public void setTipoMotivoSalida(String tipoMotivoSalida) {
        this.tipoMotivoSalida = tipoMotivoSalida;
        this.motivoEgresoCAI.setMotivoSalida(tipoMotivoSalida);
        if ("TRASLADO A OTRO CAI".equals(tipoMotivoSalida)) {
            esTrasladoCAI = true;
        } else {
            esTrasladoCAI = false;
        }
    }

    public MotivoEgresoCAI getMotivoEgresoCAI() {
        if (motivoEgresoCAI.getIdEjecucionMedidaCAI() != null) {
            if (motivoEgresoCAI.getMotivoSalida().equals("TRASLADO A OTRO CAI")) {
                esTrasladoCAI = true;
                this.motivoEgresoCAI.setMotivoSalida(tipoMotivoSalida);
            } else {
                esTrasladoCAI = false;
                this.motivoEgresoCAI.setMotivoSalida(tipoMotivoSalida);
            }
        }
        return motivoEgresoCAI;
    }

    public void setMotivoEgresoCAI(MotivoEgresoCAI motivoEgresoCAI) {
        this.motivoEgresoCAI = motivoEgresoCAI;
    }

    public MotivoEgresoCAIServicio getServicio() {
        return servicio;
    }

    public CAI getCai() {
        return cai;
    }

    public void setCai(CAI cai) {
        this.cai = cai;
    }

    public List<CAI> getListaCAI() {
        return listaCAI;
    }

    public void setListaCAI(List<CAI> listaCAI) {
        this.listaCAI = listaCAI;
    }

    public CaiServicio getServicioCAI() {
        return servicioCAI;
    }

    public void setServicioCAI(CaiServicio servicioCAI) {
        this.servicioCAI = servicioCAI;
    }

    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }

    public boolean isEsTrasladoCAI() {
        return esTrasladoCAI;
    }

    public void setEsTrasladoCAI(boolean esTrasladoCAI) {
        this.esTrasladoCAI = esTrasladoCAI;
        if (esTrasladoCAI == true) {
        } else if (esTrasladoCAI == false) {
        }
    }

    /**
     * *******************Métodos para invocar a los diferentes servicios
     * web*****************
     */
    public void guardarMotivoEgresoCAI() {
        if (this.motivoEgresoCAI != null) {
            if ("TRASLADO A OTRO CAI".equals(this.motivoEgresoCAI.getMotivoSalida())) {
                for (CAI c : listaCAI) {
                    if (c.getCai().equals(cai.getCai())) {
                        cai = c;
                    }
                }
                this.motivoEgresoCAI.setIdCaiTraslado(cai);
            } else {
                CAI caiVacio = null;
                this.motivoEgresoCAI.setIdCaiTraslado(caiVacio);
            }
            this.motivoEgresoCAI.setIdEjecucionMedidaCAI(ejecucionMedidaCAI);
            MotivoEgresoCAI motivoEgresoCAIAux = servicio.guardarMotivoEgresoCAI(motivoEgresoCAI);
            if (motivoEgresoCAIAux != null) {
                guardado = true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO MOTIVO DE EGRESO", "Información"));

            } else {
                guardado = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO MOTIVO DE EGRESO", "Error"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "EL REGISTRO MOTIVO DE EGRESO ESTA VACIO", "Error"));
        }
    }

    public void editarMotivoEgresoCAI() {
        if (this.motivoEgresoCAI != null) {
            if ("TRASLADO A OTRO CAI".equals(this.motivoEgresoCAI.getMotivoSalida())) {
                for (CAI c : listaCAI) {
                    if (c.getCai().equals(cai.getCai())) {
                        cai = c;
                    }
                }
                this.motivoEgresoCAI.setIdCaiTraslado(cai);
            } else {
                CAI caiVacio = null;
                this.motivoEgresoCAI.setIdCaiTraslado(caiVacio);
            }
            this.motivoEgresoCAI.setIdEjecucionMedidaCAI(ejecucionMedidaCAI);

            MotivoEgresoCAI motivoEgresoCAIAux = servicio.guardarMotivoEgresoCAI(motivoEgresoCAI);
            if (motivoEgresoCAIAux != null) {
                guardado = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO MOTIVO DE EGRESO", "Información"));

            } else {
                guardado = true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO MOTIVO DE EGRESO", "Error"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "EL REGISTRO MOTIVO DE EGRESO ESTA VACIO", "Error"));
        }
    }
}
