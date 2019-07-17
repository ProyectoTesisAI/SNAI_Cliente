/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.servicios.AdolescenteInfractorCAIServicio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author User
 */
@Named(value = "panelAdolescenteInfractorCAIControlador")
@ViewScoped
public class PanelAdolescenteInfractorCAIControlador implements Serializable{

    private List<AdolescenteInfractorCAI> listadoAdolescentesInfractoresCAI;
    private AdolescenteInfractorCAIServicio servicio;
    
    @PostConstruct
    public void init() {
        
        servicio= new AdolescenteInfractorCAIServicio();
        listadoAdolescentesInfractoresCAI= new ArrayList<>();
        listadoAdolescentesInfractoresCAI=servicio.listaAdolescentesInfractoresCAI();
    }

    public List<AdolescenteInfractorCAI> getListadoAdolescentesInfractoresCAI() {
        return listadoAdolescentesInfractoresCAI;
    }

    public void setListadoAdolescentesInfractoresCAI(List<AdolescenteInfractorCAI> listadoAdolescentesInfractoresCAI) {
        this.listadoAdolescentesInfractoresCAI = listadoAdolescentesInfractoresCAI;
    }
    
    
    public String agregarInformacion(AdolescenteInfractorCAI ai_udi) {

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adolescente_infractor_cai", ai_udi);
        return "/paginas/cai/matriz/panel_crear_cai.com?faces-redirect=true";
    }

}
