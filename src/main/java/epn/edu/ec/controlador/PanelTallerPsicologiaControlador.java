package epn.edu.ec.controlador;

import epn.edu.ec.modelo.Taller;
import epn.edu.ec.servicios.TallerServicio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "panelTallerPsicologiaControlador")
@ViewScoped
public class PanelTallerPsicologiaControlador implements Serializable {

    private List<Taller> listaTalleresPsicologia;
    private TallerServicio servicio;

    @PostConstruct
    public void init() {

        servicio = new TallerServicio();

        listaTalleresPsicologia = new ArrayList<>();
        listaTalleresPsicologia = servicio.listaTalleresPsicologia();

    }

    public List<Taller> getListaTalleresPsicologia() {
        return listaTalleresPsicologia;
    }

    public void setListaTalleresPsicologia(List<Taller> listaTalleresPsicologia) {
        this.listaTalleresPsicologia = listaTalleresPsicologia;
    }

    public TallerPsicologiaServicio getServicio() {
        return servicio;
    }

    public String verTallerPsicologia(TallerPsicologia taller) {

        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("taller_psicologia", taller);
            return "/paginas/psicologia/taller_psicologia_ver.com?faces-redirect=true";

        } catch (Exception ex) {
            return null;
        }
    }
    
    public String editarTallerPsicologia(TallerPsicologia taller) {

        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("taller_psicologia", taller);
            return "/paginas/psicologia/taller_psicologia_editar.com?faces-redirect=true";

        } catch (Exception ex) {
            return null;
        }
    }

    public String agregarInformePsicologia(TallerPsicologia taller) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("taller_psicologia", taller);
            return "/paginas/psicologia/informe_psicologia.com?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }
}
