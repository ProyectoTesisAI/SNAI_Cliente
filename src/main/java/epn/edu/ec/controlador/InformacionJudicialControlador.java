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
    private int numeroMedidasGuardar=0;
    private EnlacesPrograma enlaces;
    private PermisosUsuario permisosUsuario;

    @PostConstruct
    public void init() {

        permisosUsuario= new PermisosUsuario();
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
            } else {
                informacionJudicial=new InformacionJudicial();
            }
        } else {
            adolescenteInfractorUDI = new AdolescenteInfractorUDI();
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
            numeroMedidas++;
            this.informacionJudicial.setAmonestacionVerbal(amonestacionVerbal);

        } else if(!amonestacionVerbal) {
            numeroMedidas--;
            this.informacionJudicial.setAmonestacionVerbal(amonestacionVerbal);
        }
    }

    public boolean isImposicionReglasConducta() {
        return imposicionReglasConducta;
    }

    public void setImposicionReglasConducta(boolean imposicionReglasConducta) {
        this.imposicionReglasConducta = imposicionReglasConducta;
        if (imposicionReglasConducta) {
            numeroMedidas++;
            this.informacionJudicial.setImposicionReglasConducta(apoyoSocioFamiliar);
        } else if(!imposicionReglasConducta){
            numeroMedidas--;
            this.informacionJudicial.setImposicionReglasConducta(apoyoSocioFamiliar);
        }
    }

    public boolean isApoyoSocioFamiliar() {
        return apoyoSocioFamiliar;
    }

    public void setApoyoSocioFamiliar(boolean apoyoSocioFamiliar) {
        this.apoyoSocioFamiliar = apoyoSocioFamiliar;
        if (apoyoSocioFamiliar) {
            numeroMedidas++;
            this.informacionJudicial.setOrientacionApoyoSocioFamiliar(apoyoSocioFamiliar);
        } else if(!apoyoSocioFamiliar){
            numeroMedidas--;
            this.informacionJudicial.setOrientacionApoyoSocioFamiliar(apoyoSocioFamiliar);
        }
    }

    public boolean isServicioComunidad() {
        return servicioComunidad;
    }

    public void setServicioComunidad(boolean servicioComunidad) {
        this.servicioComunidad = servicioComunidad;
        if (servicioComunidad) {
            numeroMedidas++;
            this.informacionJudicial.setServicioComunidad(servicioComunidad);
        } else if(!servicioComunidad) {
            numeroMedidas--;
            this.informacionJudicial.setServicioComunidad(servicioComunidad);
        }
    }

    public boolean isLibertadAsistida() {
        return libertadAsistida;
    }

    public void setLibertadAsistida(boolean libertadAsistida) {
        this.libertadAsistida = libertadAsistida;
        if (libertadAsistida) {
            numeroMedidas++;
            this.informacionJudicial.setLibertadAsistida(libertadAsistida);
        } else if(!libertadAsistida){
            numeroMedidas--;
            this.informacionJudicial.setLibertadAsistida(libertadAsistida);
        }
    }

    public int getNumeroMedidas() {
        return numeroMedidas;
    }

    public void setNumeroMedidas(int numeroMedidas) {
        this.numeroMedidas = numeroMedidas;
    }

    /**
     * *******************Métodos para invocar a los diferentes servicios web*****************
     */
    public void guardarInformacionJudicial() {
        
        if(amonestacionVerbal==true){
            numeroMedidasGuardar++;
            this.informacionJudicial.setAmonestacionVerbal(amonestacionVerbal);
        }
        if(apoyoSocioFamiliar==true){
            numeroMedidasGuardar++;
            this.informacionJudicial.setOrientacionApoyoSocioFamiliar(apoyoSocioFamiliar);
        }
        if(imposicionReglasConducta==true){
            numeroMedidasGuardar++;
            this.informacionJudicial.setImposicionReglasConducta(imposicionReglasConducta);
        }
        if(libertadAsistida==true){
            numeroMedidasGuardar++;
            this.informacionJudicial.setLibertadAsistida(libertadAsistida);
        }
        if(servicioComunidad==true){
            numeroMedidasGuardar++;
            this.informacionJudicial.setServicioComunidad(servicioComunidad);
        }
        this.informacionJudicial.setNumeroMedidas(numeroMedidasGuardar);
        this.informacionJudicial.setIdAdolescenteInfractorUDI(adolescenteInfractorUDI);

        InformacionJudicial informacionJudicialAux = servicio.guardarInformacionJudicial(informacionJudicial);
        if (informacionJudicialAux!= null) {
            guardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO INFORMACIÓN JUDICIAL", "Información"));
            
        } else {
            guardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO INFORMACIÓN JUDICIAL", "Error"));
        }
    }
}
