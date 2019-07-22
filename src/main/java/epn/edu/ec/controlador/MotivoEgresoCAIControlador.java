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
    String tipoMotivoSalida;
    boolean mostrarOcultarTrasladoCAI;

    @PostConstruct
    public void init() {
        servicio = new MotivoEgresoCAIServicio();
        servicioCAI = new CaiServicio();

        motivoEgresoCAI = new MotivoEgresoCAI();
        guardado = false;
        mostrarOcultarTrasladoCAI = false;

        cai = new CAI();
        listaCAI = new ArrayList<>();
        listaCAI = servicioCAI.listaCai();
        
        if(isMostrarOcultarTrasladoCAI()){
            tipoMotivoSalida="TRASLADO A OTRO CAI";
        }

        ejecucionMedidaCAI= new EjecucionMedidaCAI();
        
        EjecucionMedidaCAI ejecucionMedidaCAIAux = (EjecucionMedidaCAI) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ejecucion_medida_cai");

        if (ejecucionMedidaCAIAux != null) {
            
            ejecucionMedidaCAI=ejecucionMedidaCAIAux;
            
            MotivoEgresoCAI motivoEgresoCAIAux = servicio.obtenerMotivoEgresoCAI(ejecucionMedidaCAI.getIdEjecucionMedidaCai());
            
            if (motivoEgresoCAIAux != null) {
                motivoEgresoCAI = motivoEgresoCAIAux;
                guardado = true;
                if (motivoEgresoCAI.getMotivoSalida().equals("TRASLADO A OTRO CAI")) {
                    mostrarOcultarTrasladoCAI=true;
                }else{
                    mostrarOcultarTrasladoCAI=false;
                }
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
    }
    
    public MotivoEgresoCAI getMotivoEgresoCAI() {
        if(motivoEgresoCAI.equals("TRASLADO A OTRO CAI")){
            mostrarOcultarTrasladoCAI=true;
            System.out.println("traslado: "+mostrarOcultarTrasladoCAI);
        }else{
            mostrarOcultarTrasladoCAI=false;
            System.out.println("traslado: "+mostrarOcultarTrasladoCAI);
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

    public boolean isMostrarOcultarTrasladoCAI() {
        return mostrarOcultarTrasladoCAI;
    }

    public void setMostrarOcultarTrasladoCAI(boolean mostrarOcultarTrasladoCAI) {
        this.mostrarOcultarTrasladoCAI = mostrarOcultarTrasladoCAI;
        if(mostrarOcultarTrasladoCAI==true){
            System.out.println("mostrar es: "+mostrarOcultarTrasladoCAI);
        }else if(mostrarOcultarTrasladoCAI==false){
            System.out.println("mostrar es: "+mostrarOcultarTrasladoCAI);
        }
    }

    /**
     * *******************Métodos para invocar a los diferentes servicios web*****************
     */
    public String guardarMotivoEgresoCAI() {

        this.motivoEgresoCAI.setIdEjecucionMedidaCAI(ejecucionMedidaCAI);

        MotivoEgresoCAI motivoEgresoCAIAux = servicio.guardarMotivoEgresoCAI(motivoEgresoCAI);
        if (motivoEgresoCAIAux != null) {
            return "/paginas/cai/cai.com?faces-redirect=true";
        } else {
            return null;
        }
    }
}
