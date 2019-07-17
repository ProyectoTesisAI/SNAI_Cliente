package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.modelo.EjeSaludCAI;
import epn.edu.ec.servicios.EjeSaludCAIServicio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "ejeSaludCAIControlador")
@ViewScoped
public class EjeSaludCAIControlador implements Serializable {

    private AdolescenteInfractorCAI adolescenteInfractorCAI;
    private EjeSaludCAI ejeSaludCAI;
    private EjeSaludCAIServicio servicio;
    private boolean guardado;
    private boolean saludable;

    @PostConstruct
    public void init() {
        servicio = new EjeSaludCAIServicio();

        ejeSaludCAI = new EjeSaludCAI();
        guardado = false;

        adolescenteInfractorCAI = new AdolescenteInfractorCAI();
        adolescenteInfractorCAI = (AdolescenteInfractorCAI) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_cai");

        if (isSaludable()) {
            saludable = true;
        } else {
            saludable = false;            
        }
        
        if (adolescenteInfractorCAI != null) {
            EjeSaludCAI ejeSaludCAIAux = servicio.obtenerEjeSaludCAI(adolescenteInfractorCAI.getIdAdolescenteCai());
            if (ejeSaludCAIAux != null) {
                ejeSaludCAI = ejeSaludCAIAux;
                guardado = true;
                String saludableAux = ejeSaludCAIAux.getSituacionSalud();
                System.out.println("salud: "+saludableAux);
                if(saludableAux.equals("SALUDABLE")){
                    System.out.println("entro a saludable");
                    saludable=true;
                }else if(saludableAux.equals("NO SALUDABLE")){
                    System.out.println("no entro a saludable");
                    saludable=false;
                }
            }
        }

    }

    public AdolescenteInfractorCAI getAdolescenteInfractorCAI() {
        return adolescenteInfractorCAI;
    }

    public void setAdolescenteInfractorCAI(AdolescenteInfractorCAI adolescenteInfractorCAI) {
        this.adolescenteInfractorCAI = adolescenteInfractorCAI;
    }

    public EjeSaludCAI getEjeSaludCAI() {
        return ejeSaludCAI;
    }

    public void setEjeSaludCAI(EjeSaludCAI ejeSaludCAI) {
        this.ejeSaludCAI = ejeSaludCAI;
    }

    public EjeSaludCAIServicio getServicio() {
        return servicio;
    }

    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }

    public boolean isSaludable() {
        return saludable;
    }

    public void setSaludable(boolean saludable) {
        this.saludable = saludable;
        if(saludable==true){
            ejeSaludCAI.setSituacionSalud("SALUDABLE");
        }else if(saludable==false){
            ejeSaludCAI.setSituacionSalud("NO SALUDABLE");
        }
    }

    /**
     * *******************Métodos para invocar a los diferentes servicios web*****************
     */
    public String guardarEjeSaludCAI() {

        this.ejeSaludCAI.setIdEjeSalud(adolescenteInfractorCAI);
        System.out.println("saluda a guardar: "+ejeSaludCAI);

        EjeSaludCAI ejeSaludCAIAux = servicio.guardarEjeSaludCAI(ejeSaludCAI);
        if (ejeSaludCAIAux != null) {
            return "/paginas/cai/cai.com?faces-redirect=true";
        } else {
            return null;
        }
    }

}
