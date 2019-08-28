package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.modelo.InformacionJudicial;
import epn.edu.ec.servicios.InformacionJudicialServicio;
import epn.edu.ec.utilidades.EnlacesPrograma;
import epn.edu.ec.utilidades.PermisosUsuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "informacionJudicialControlador")
@ViewScoped
public class InformacionJudicialControlador implements Serializable {

    private AdolescenteInfractorUDI adolescenteInfractorUDI;

    private InformacionJudicial informacionJudicial;
    private InformacionJudicialServicio servicio;
    private boolean guardado;

    private boolean amonestacionVerbal;
    private boolean imposicionReglasConducta;
    private boolean apoyoSocioFamiliar;
    private boolean servicioComunidad;
    private boolean libertadAsistida;
    private int numeroMedidas = 0;
    private EnlacesPrograma enlaces;
    private PermisosUsuario permisos;

    @PostConstruct
    public void init() {

        permisos= new PermisosUsuario();
        enlaces = new EnlacesPrograma();
        servicio = new InformacionJudicialServicio();
        guardado = false;

        informacionJudicial = new InformacionJudicial();

        adolescenteInfractorUDI = new AdolescenteInfractorUDI();
        AdolescenteInfractorUDI adolescenteInfractorUDIAux = (AdolescenteInfractorUDI) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_udi");

        if (adolescenteInfractorUDIAux != null) {

            adolescenteInfractorUDI = adolescenteInfractorUDIAux;

            InformacionJudicial informacionJudicialAux = servicio.obtenerInformacionJudicial(adolescenteInfractorUDI.getIdAdolescenteInfractor().getIdAdolescenteInfractor());
            if (informacionJudicialAux != null) {
                informacionJudicial = informacionJudicialAux;
                guardado = true;
                amonestacionVerbal = informacionJudicial.getAmonestacionVerbal();
                imposicionReglasConducta = informacionJudicial.getImposicionReglasConducta();
                apoyoSocioFamiliar = informacionJudicial.getOrientacionApoyoSocioFamiliar();
                servicioComunidad = informacionJudicial.getServicioComunidad();
                libertadAsistida = informacionJudicial.getLibertadAsistida();
                numeroMedidas = informacionJudicial.getNumeroMedidas();
            }
        }

    }

    public AdolescenteInfractorUDI getAdolescenteInfractorUDI() {
        return adolescenteInfractorUDI;
    }

    public void setAdolescenteInfractorUDI(AdolescenteInfractorUDI adolescenteInfractorUDI) {
        this.adolescenteInfractorUDI = adolescenteInfractorUDI;
    }

    public InformacionJudicial getInformacionJudicial() {
        return informacionJudicial;
    }

    public void setInformacionJudicial(InformacionJudicial informacionJudicial) {
        this.informacionJudicial = informacionJudicial;
    }

    public InformacionJudicialServicio getServicio() {
        return servicio;
    }

    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }

    public boolean isAmonestacionVerbal() {

        return amonestacionVerbal;
    }

    public void setAmonestacionVerbal(boolean amonestacionVerbal) {
        this.amonestacionVerbal = amonestacionVerbal;
        if (amonestacionVerbal) {
            this.informacionJudicial.setAmonestacionVerbal(true);

        } else{
            this.informacionJudicial.setAmonestacionVerbal(false);
        }
    }

    public boolean isImposicionReglasConducta() {
        return imposicionReglasConducta;
    }

    public void setImposicionReglasConducta(boolean imposicionReglasConducta) {
        this.imposicionReglasConducta = imposicionReglasConducta;
        if (imposicionReglasConducta) {
            this.informacionJudicial.setImposicionReglasConducta(true);
        } else{
            this.informacionJudicial.setImposicionReglasConducta(false);
        }
    }

    public boolean isApoyoSocioFamiliar() {
        return apoyoSocioFamiliar;
    }

    public void setApoyoSocioFamiliar(boolean apoyoSocioFamiliar) {
        this.apoyoSocioFamiliar = apoyoSocioFamiliar;
        if (apoyoSocioFamiliar) {
            this.informacionJudicial.setOrientacionApoyoSocioFamiliar(true);
        } else{
            this.informacionJudicial.setOrientacionApoyoSocioFamiliar(false);
        }
    }

    public boolean isServicioComunidad() {
        return servicioComunidad;
    }

    public void setServicioComunidad(boolean servicioComunidad) {
        this.servicioComunidad = servicioComunidad;
        if (servicioComunidad) {
            this.informacionJudicial.setServicioComunidad(true);
        } else{
            this.informacionJudicial.setServicioComunidad(false);
        }
    }

    public boolean isLibertadAsistida() {
        return libertadAsistida;
    }

    public void setLibertadAsistida(boolean libertadAsistida) {
        this.libertadAsistida = libertadAsistida;
        if (libertadAsistida) {
            this.informacionJudicial.setLibertadAsistida(true);
        } else{
            this.informacionJudicial.setLibertadAsistida(false);
        }
    }

    public int getNumeroMedidas() {
        numeroMedidas=0;
        if (amonestacionVerbal) {
            numeroMedidas++;
        }
        
        if (imposicionReglasConducta) {
            numeroMedidas++;
        }
        
        if (apoyoSocioFamiliar) {
            numeroMedidas++;
        }
        
        if (servicioComunidad) {
            numeroMedidas++;
        }
        
        if (libertadAsistida) {
            numeroMedidas++;
        }
        
        if(numeroMedidas<0){
            numeroMedidas=0;
        }
        return numeroMedidas;
    }

    public void setNumeroMedidas(int numeroMedidas) {
        this.numeroMedidas = numeroMedidas;
    }

    /**
     * *******************Métodos para invocar a los diferentes servicios web*****************
     */
    public String guardarInformacionJudicial() {
        
        if (numeroMedidas > 0 && numeroMedidas<6) {
            this.informacionJudicial.setNumeroMedidas(numeroMedidas);
            this.informacionJudicial.setIdAdolescenteInfractorUDI(adolescenteInfractorUDI);

            InformacionJudicial informacionJudicialAux = servicio.guardarInformacionJudicial(informacionJudicial);
            if (informacionJudicialAux != null) {
                
                guardado = true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO INFORMACIÓN JUDICIAL", "Información"));
                
                String rol = permisos.RolUsuario();
                if (rol != null) {
                    if (rol.equals("ADMINISTRADOR")) {
                        return enlaces.PATH_PANEL_CREAR_UDI_ADMINISTRADOR + "?faces-redirect=true";
                    }
                    else{
                        if(rol.equals("LIDER UZDI")){
                            return enlaces.PATH_PANEL_CREAR_UDI_LIDER_UZDI+"?faces-redirect=true";
                        }
                        else{
                            return enlaces.PATH_PANEL_UDI_USER+"?faces-redirect=true";
                        }
                    }
                } else {
                    return null;
                }
                

            } else {
                guardado = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO INFORMACIÓN JUDICIAL", "Error"));
                return null;
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "DEBE DE SELECCIONAR AL MENOS UNA MEDIDA SOCIOEDUCATIVA", "Error"));
            return null;
        }
        
        
    }
    
    public void guardarEdicionInformacionJudicial() {
        
        if (numeroMedidas > 0 && numeroMedidas<6) {
            this.informacionJudicial.setNumeroMedidas(numeroMedidas);
            this.informacionJudicial.setIdAdolescenteInfractorUDI(adolescenteInfractorUDI);

            InformacionJudicial informacionJudicialAux = servicio.guardarInformacionJudicial(informacionJudicial);
            if (informacionJudicialAux != null) {
                guardado = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO INFORMACIÓN JUDICIAL", "Información"));

            } else {
                guardado = true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO INFORMACIÓN JUDICIAL", "Error"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "DEBE DE SELECCIONAR AL MENOS UNA MEDIDA SOCIOEDUCATIVA", "Error"));
        }
        
    }
}
