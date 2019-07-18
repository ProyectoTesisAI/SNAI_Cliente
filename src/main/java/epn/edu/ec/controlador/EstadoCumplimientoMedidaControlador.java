package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.modelo.CAI;
import epn.edu.ec.modelo.EstadoCumplimientoMedida;
import epn.edu.ec.modelo.UDI;
import epn.edu.ec.servicios.CaiServicio;
import epn.edu.ec.servicios.EstadoCumplimientoMedidaServicio;
import epn.edu.ec.servicios.UdiServicio;
import epn.edu.ec.utilidades.EnlacesPrograma;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "estadoCumplimientoMedidaControlador")
@ViewScoped
public class EstadoCumplimientoMedidaControlador implements Serializable {

    private AdolescenteInfractorUDI adolescenteInfractorUDI;
    private EstadoCumplimientoMedida estadoCumplimientoMedida;
    private EstadoCumplimientoMedidaServicio servicio;
    private boolean guardado;
    private String estado = "";

    private boolean ActivarEjecucion;
    private boolean ActivarCulminada;
    private boolean ActivarDerivada;
    private boolean ActivarIncumplimiento;

    private boolean ejecucionGuardada;
    private boolean culminadaGuardada;
    private boolean derivadaGuardada;
    private boolean incumplimientoGuardada;
    private EnlacesPrograma enlaces;

    //variables usadas para la derivacion de centro
    String tipoCentro;
    boolean esUzdi;
    UDI udi;
    CAI cai;
    CaiServicio controladorCai;
    UdiServicio controladorUdi;
    List<UDI> listaUdi;
    List<CAI> listaCai;

    @PostConstruct
    public void init() {
        controladorCai = new CaiServicio();
        controladorUdi = new UdiServicio();
        udi = new UDI();
        cai = new CAI();
        listaUdi = new ArrayList<>();
        listaCai = new ArrayList<>();

        enlaces = new EnlacesPrograma();
        servicio = new EstadoCumplimientoMedidaServicio();

        estadoCumplimientoMedida = new EstadoCumplimientoMedida();
        guardado = false;

        estado = "EN_EJECUCIÓN";
        //System.out.println("estado init: " + estado);
        if (estado.equals("EN_EJECUCIÓN")) {
            System.out.println("ejecucion");
            ActivarEjecucion = true;
            ActivarCulminada = false;
            ActivarDerivada = false;
            ActivarIncumplimiento = false;

        } else if (estado.equals("CULMINADA")) {
            System.out.println("culminada");
            ActivarEjecucion = false;
            ActivarCulminada = true;
            ActivarDerivada = false;
            ActivarIncumplimiento = false;

        } else if (estado.equals("DERIVADA")) {
            System.out.println("derivada");
            ActivarEjecucion = false;
            ActivarCulminada = false;
            ActivarDerivada = true;
            ActivarIncumplimiento = false;

        } else if (estado.equals("INCUMPLIMIENTO")) {
            System.out.println("incumplimiento");
            ActivarEjecucion = false;
            ActivarCulminada = false;
            ActivarDerivada = false;
            ActivarIncumplimiento = true;

        }

        if (isEsUzdi()) {
            tipoCentro = "UZDI";
            listaUdi = controladorUdi.listaUdi(); //muestro la lista de UDIs rescatadas de la base de datos
            udi = new UDI();
            cai = new CAI();
        } else {
            tipoCentro = "CAI";
            listaCai = controladorCai.listaCai(); //muestro la lista de CAIs rescatadas de la base de datos
            cai = new CAI();
            udi = new UDI();
        }

        adolescenteInfractorUDI = new AdolescenteInfractorUDI();
        AdolescenteInfractorUDI adolescenteInfractorUDIAux = (AdolescenteInfractorUDI) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_udi");

        if (adolescenteInfractorUDIAux != null) {

            adolescenteInfractorUDI = adolescenteInfractorUDIAux;

            EstadoCumplimientoMedida estadoCumplimientoMedidaAux = servicio.obtenerEstadoCumplimientoMedida(adolescenteInfractorUDI.getIdAdolescenteInfractor().getIdAdolescenteInfractor());

            if (estadoCumplimientoMedidaAux != null) {

                estadoCumplimientoMedida = estadoCumplimientoMedidaAux;
                guardado = true;
                if (estadoCumplimientoMedidaAux.getSituacionActual() != null) {
                    if (estadoCumplimientoMedida.getSituacionActual().equals("EN_EJECUCIÓN")) {
                        ActivarEjecucion = true;
                        ActivarCulminada = false;
                        ActivarDerivada = false;
                        ActivarIncumplimiento = false;
                        estado = estadoCumplimientoMedida.getSituacionActual();
                        //System.out.println("ee: " + ActivarEjecucion);
                    } else if (estadoCumplimientoMedida.getSituacionActual().equals("CULMINADA")) {
                        ActivarEjecucion = false;
                        ActivarCulminada = true;
                        ActivarDerivada = false;
                        ActivarIncumplimiento = false;
                        estado = estadoCumplimientoMedida.getSituacionActual();
                        //System.out.println("ec: " + ActivarCulminada);
                    } else if (estadoCumplimientoMedida.getSituacionActual().equals("DERIVADA")) {
                        ActivarEjecucion = false;
                        ActivarCulminada = false;
                        ActivarDerivada = true;
                        ActivarIncumplimiento = false;
                        estado = estadoCumplimientoMedida.getSituacionActual();
                        //System.out.println("ed: " + ActivarDerivada);
                    } else if (estadoCumplimientoMedida.getSituacionActual().equals("INCUMPLIMIENTO")) {
                        ActivarEjecucion = false;
                        ActivarCulminada = false;
                        ActivarDerivada = false;
                        ActivarIncumplimiento = true;
                        estado = estadoCumplimientoMedida.getSituacionActual();
                        //System.out.println("ei: " + ActivarIncumplimiento);
                    }
                } else {
                    System.out.println("La situacion es " + estadoCumplimientoMedida.getSituacionActual());
                }
                if (estadoCumplimientoMedida.getCaiReceptoraDerivacion() != null) {
                    tipoCentro = "CAI";
                    listaCai = controladorCai.listaCai();
                    cai = estadoCumplimientoMedidaAux.getCaiReceptoraDerivacion();
                } else if (estadoCumplimientoMedida.getUzdiReceptoraDerivacion() != null) {
                    tipoCentro = "UZDI";
                    listaUdi = controladorUdi.listaUdi();
                    udi = estadoCumplimientoMedidaAux.getUzdiReceptoraDerivacion();
                }
            }
        }

    }

    public AdolescenteInfractorUDI getAdolescenteInfractorUDI() {
        return adolescenteInfractorUDI;
    }

    public void setAdolescenteInfractorUDI(AdolescenteInfractorUDI adolescenteInfractorUDI) {
        this.adolescenteInfractorUDI = adolescenteInfractorUDI;
    }

    public EstadoCumplimientoMedida getEstadoCumplimientoMedida() {
        return estadoCumplimientoMedida;
    }

    public void setEstadoCumplimientoMedida(EstadoCumplimientoMedida estadoCumplimientoMedida) {
        this.estadoCumplimientoMedida = estadoCumplimientoMedida;
    }

    public EstadoCumplimientoMedidaServicio getServicio() {
        return servicio;
    }

    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }

    public String getEstado() {
        System.out.println("estado: " + estado);
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
        if (estado.equals("EN_EJECUCIÓN")) {
            System.out.println("ejecucion");
            ActivarEjecucion = true;
            ActivarCulminada = false;
            ActivarDerivada = false;
            ActivarIncumplimiento = false;
            //System.out.println("ee: " + ActivarEjecucion);
            //System.out.println("ec: " + ActivarCulminada);
            //System.out.println("ed: " + ActivarDerivada);
            //System.out.println("ei: " + ActivarIncumplimiento);
        } else if (estado.equals("CULMINADA")) {
            System.out.println("culminada");
            ActivarEjecucion = false;
            ActivarCulminada = true;
            ActivarDerivada = false;
            ActivarIncumplimiento = false;
            //System.out.println("ce: " + ActivarEjecucion);
            //System.out.println("cc: " + ActivarCulminada);
            //System.out.println("cd: " + ActivarDerivada);
            //System.out.println("ci: " + ActivarIncumplimiento);
        } else if (estado.equals("DERIVADA")) {
            System.out.println("derivada");
            ActivarEjecucion = false;
            ActivarCulminada = false;
            ActivarDerivada = true;
            ActivarIncumplimiento = false;
            //System.out.println("de: " + ActivarEjecucion);
            //System.out.println("dc: " + ActivarCulminada);
            //System.out.println("dd: " + ActivarDerivada);
            //System.out.println("di: " + ActivarIncumplimiento);
        } else if (estado.equals("INCUMPLIMIENTO")) {
            System.out.println("incumplimiento");
            ActivarEjecucion = false;
            ActivarCulminada = false;
            ActivarDerivada = false;
            ActivarIncumplimiento = true;
            //System.out.println("ie: " + ActivarEjecucion);
            //System.out.println("ic: " + ActivarCulminada);
            //System.out.println("id: " + ActivarDerivada);
            //System.out.println("ii: " + ActivarIncumplimiento);
        }
    }

    public boolean isActivarEjecucion() {
        return ActivarEjecucion;
    }

    public void setActivarEjecucion(boolean ActivarEjecucion) {
        this.ActivarEjecucion = ActivarEjecucion;
    }

    public boolean isActivarCulminada() {
        return ActivarCulminada;
    }

    public void setActivarCulminada(boolean ActivarCulminada) {
        this.ActivarCulminada = ActivarCulminada;
    }

    public boolean isActivarDerivada() {
        return ActivarDerivada;
    }

    public void setActivarDerivada(boolean ActivarDerivada) {
        this.ActivarDerivada = ActivarDerivada;
    }

    public boolean isActivarIncumplimiento() {
        return ActivarIncumplimiento;
    }

    public void setActivarIncumplimiento(boolean ActivarIncumplimiento) {
        this.ActivarIncumplimiento = ActivarIncumplimiento;
    }

    public boolean isEjecucionGuardada() {
        return ejecucionGuardada;
    }

    public void setEjecucionGuardada(boolean ejecucionGuardada) {
        this.ejecucionGuardada = ejecucionGuardada;
    }

    public boolean isCulminadaGuardada() {
        return culminadaGuardada;
    }

    public void setCulminadaGuardada(boolean culminadaGuardada) {
        this.culminadaGuardada = culminadaGuardada;
    }

    public boolean isDerivadaGuardada() {
        return derivadaGuardada;
    }

    public void setDerivadaGuardada(boolean derivadaGuardada) {
        this.derivadaGuardada = derivadaGuardada;
    }

    public boolean isIncumplimientoGuardada() {
        return incumplimientoGuardada;
    }

    public void setIncumplimientoGuardada(boolean incumplimientoGuardada) {
        this.incumplimientoGuardada = incumplimientoGuardada;
    }

    public String getTipoCentro() {
        return tipoCentro;
    }

    public void setTipoCentro(String tipoCentro) {
        this.tipoCentro = tipoCentro;
        if ("UZDI".equals(tipoCentro)) {
            System.out.println("Ha seleccionado UZDI");
            esUzdi = true;
            udi = new UDI();
            cai = new CAI();
            listaUdi = controladorUdi.listaUdi(); //muestro la lista de UDIs rescatadas de la base de 
        } else if ("CAI".equals(tipoCentro)) {
            System.out.println("Ha seleccionado CAI");
            esUzdi = false;
            udi = new UDI();
            cai = new CAI();
            listaCai = controladorCai.listaCai(); //muestro la lista de CAIs rescatadas de la base de datos
        }
    }

    public boolean isEsUzdi() {
        if ("UZDI".equals(tipoCentro)) {
            esUzdi = true;

        } else if ("CAI".equals(tipoCentro)) {
            esUzdi = false;
        }
        return esUzdi;
    }

    public List<UDI> getListaUdi() {
        return listaUdi;
    }

    public void setListaUdi(List<UDI> listaUdi) {
        this.listaUdi = listaUdi;
    }

    public List<CAI> getListaCai() {
        return listaCai;
    }

    public void setListaCai(List<CAI> listaCai) {
        this.listaCai = listaCai;
    }

    public CaiServicio getControladorCai() {
        return controladorCai;
    }

    public UdiServicio getControladorUdi() {
        return controladorUdi;
    }

    public UDI getUdi() {
        return udi;
    }

    public void setUdi(UDI udi) {
        this.udi = udi;
    }

    public CAI getCai() {
        return cai;
    }

    public void setCai(CAI cai) {
        this.cai = cai;
    }

    /**
     * *******************Métodos para invocar a los diferentes servicios
     * web*****************
     */
    public String guardarEstadoCumplimientoMedida() {

        for (UDI u : listaUdi) {
            if (u.getUdi().equals(udi.getUdi())) {
                udi = u;
                break;
            }
        }
        for (CAI c : listaCai) {
            if (c.getCai().equals(cai.getCai())) {
                cai = c;
                break;
            }
        }
        if (udi.getIdUdi() != null) {
            this.estadoCumplimientoMedida.setUzdiReceptoraDerivacion(udi);
        } else {
            this.estadoCumplimientoMedida.setUzdiReceptoraDerivacion(null);
        }
        if (cai.getIdCai() != null) {
            this.estadoCumplimientoMedida.setCaiReceptoraDerivacion(cai);
        } else {
            this.estadoCumplimientoMedida.setCaiReceptoraDerivacion(null);
        }
        this.estadoCumplimientoMedida.setIdAdolescenteInfractorUDI(adolescenteInfractorUDI);
        this.estadoCumplimientoMedida.setSituacionActual(estado);

        EstadoCumplimientoMedida estadoCumplimientoMedidaAux = servicio.guardarEstadoCumplimientoMedida(estadoCumplimientoMedida);
        if (estadoCumplimientoMedidaAux != null) {
            return enlaces.PATH_PANEL_UDI + "?faces-redirect=true";
        } else {
            return null;
        }
    }

}
