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

@Named(value = "panelTallerControlador")
@ViewScoped
public class PanelTallerControlador implements Serializable {

    private List<Taller> listaTalleres;
    private TallerServicio servicio;

    @PostConstruct
    public void init() {

        servicio = new TallerServicio();

        listaTalleres = new ArrayList<>();
        listaTalleres = servicio.listaTalleres();

    }

    public List<Taller> getListaTalleres() {
        return listaTalleres;
    }

    public void setListaTalleres(List<Taller> listaTalleres) {
        this.listaTalleres = listaTalleres;
    }

    public TallerServicio getServicio() {
        return servicio;
    }

    public String verTaller(Taller taller) {

        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("taller_psicologia", taller);
            return "/paginas/psicologia/taller_psicologia_ver.com?faces-redirect=true";

        } catch (Exception ex) {
            return null;
        }
    }
    
    public String editarTaller(Taller taller) {

        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("taller_psicologia", taller);
            return "/paginas/psicologia/taller_psicologia_editar.com?faces-redirect=true";

        } catch (Exception ex) {
            return null;
        }
    }

    public String agregarInforme(Taller taller) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("taller_psicologia", taller);
            return "/paginas/psicologia/informe_psicologia.com?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }
}
