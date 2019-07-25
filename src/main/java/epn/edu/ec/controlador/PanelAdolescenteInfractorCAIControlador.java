package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.servicios.AdolescenteInfractorCAIServicio;
import epn.edu.ec.utilidades.EnlacesPrograma;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "panelAdolescenteInfractorCAIControlador")
@ViewScoped
public class PanelAdolescenteInfractorCAIControlador implements Serializable{

    private List<AdolescenteInfractorCAI> listadoAdolescentesInfractoresCAI;
    private AdolescenteInfractorCAIServicio servicio;
    private EnlacesPrograma enlaces;
    
    @PostConstruct
    public void init() {
        
        servicio= new AdolescenteInfractorCAIServicio();
        enlaces=new EnlacesPrograma();
        listadoAdolescentesInfractoresCAI= new ArrayList<>();
        listadoAdolescentesInfractoresCAI=servicio.listaAdolescentesInfractoresCAI();
    }

    public List<AdolescenteInfractorCAI> getListadoAdolescentesInfractoresCAI() {
        return listadoAdolescentesInfractoresCAI;
    }

    public void setListadoAdolescentesInfractoresCAI(List<AdolescenteInfractorCAI> listadoAdolescentesInfractoresCAI) {
        this.listadoAdolescentesInfractoresCAI = listadoAdolescentesInfractoresCAI;
    }
    
    
    public String agregarInformacion(AdolescenteInfractorCAI ai_cai) {

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adolescente_infractor_cai", ai_cai);
        //return "/paginas/cai/matriz/panel_crear_cai.com?faces-redirect=true";
        return enlaces.PATH_ADOLESCENTE_CAI_ANIADIR+"?faces-redirect=true";
    }

}
