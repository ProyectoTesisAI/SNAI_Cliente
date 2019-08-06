package epn.edu.ec.controlador;

import epn.edu.ec.modelo.CAI;
import epn.edu.ec.modelo.EjecucionMedidaCAI;
import epn.edu.ec.modelo.InformacionCambioMedidaCAI;
import epn.edu.ec.servicios.CaiServicio;
import epn.edu.ec.servicios.EjecucionMedidaServicio;
import epn.edu.ec.servicios.InformacionCambioMedidaServicio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "informacionCambioMedidaControlador")
@ViewScoped
public class InformacionCambioMedidaControlador implements Serializable {

    private InformacionCambioMedidaCAI informacionCambioMedida;
    private InformacionCambioMedidaServicio servicio;
    private boolean guardado;

    private EjecucionMedidaCAI ejecucionMedidaCAI;
    private boolean aceptaCambioMedida;

    private CAI cai;
    private List<CAI> listaCAI;
    private CaiServicio servicioCAI;

    @PostConstruct
    public void init() {
        servicio = new InformacionCambioMedidaServicio();
        servicioCAI = new CaiServicio();

        ejecucionMedidaCAI = new EjecucionMedidaCAI();

        listaCAI = new ArrayList<>();
        listaCAI = servicioCAI.listaCai();

        informacionCambioMedida = new InformacionCambioMedidaCAI();
        guardado = false;
        aceptaCambioMedida=false;

        EjecucionMedidaCAI ejecucionMedidaCAIAux = (EjecucionMedidaCAI) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ejecucion_medida_cai");

        if (ejecucionMedidaCAIAux != null) {
            
            ejecucionMedidaCAI=ejecucionMedidaCAIAux;
            
            InformacionCambioMedidaCAI informacionCambioMedidaAux = servicio.obtenerInformacionCambioMedidaCAI(ejecucionMedidaCAI.getIdEjecucionMedidaCai());
            
            
            if (informacionCambioMedidaAux != null) {
                informacionCambioMedida = informacionCambioMedidaAux;
                guardado = true;
            } 
            else if (informacionCambioMedidaAux == null) {
                informacionCambioMedida = new InformacionCambioMedidaCAI();
                informacionCambioMedida.setIdEjecucionMedidaCAI(ejecucionMedidaCAI);         
                informacionCambioMedida.setCambioMedidaSocioeducativa("60% DE CUMPLIMIENTO");
            }
        }

    }

    public boolean isAceptaCambioMedida() {
        return aceptaCambioMedida;
    }

    public void setAceptaCambioMedida(boolean aceptaCambioMedida) {
        this.aceptaCambioMedida = aceptaCambioMedida;
        if(this.aceptaCambioMedida==true){
            informacionCambioMedida.setAceptacionJuezCambioMedida(true);
        }
        else{
            informacionCambioMedida.setAceptacionJuezCambioMedida(false);
        }
    }
    
    public InformacionCambioMedidaCAI getInformacionCambioMedida() {
        return informacionCambioMedida;
    }

    public EjecucionMedidaCAI getEjecucionMedidaCAI() {
        return ejecucionMedidaCAI;
    }

    public void setEjecucionMedidaCAI(EjecucionMedidaCAI ejecucionMedidaCAI) {
        this.ejecucionMedidaCAI = ejecucionMedidaCAI;
    }

    public void setInformacionCambioMedida(InformacionCambioMedidaCAI estadoCumplimientoMedida) {
        this.informacionCambioMedida = estadoCumplimientoMedida;
    }

    public InformacionCambioMedidaServicio getServicio() {
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

    /**
     * *******************Métodos para invocar a los diferentes servicios web*****************
     */
    public void guardarInformacionCambioMedida() {


        InformacionCambioMedidaCAI informacionCambioMedidaAux = servicio.guardarInformacionCambioMedidaCAI(informacionCambioMedida);
        if (informacionCambioMedidaAux != null) {
            guardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO INFORMACIÓN CAMBIO MEDIDA", "Información"));
            
        } else {
            guardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO INFORMACIÓN CAMBIO MEDIDA", "Error"));
        }
    }

}
