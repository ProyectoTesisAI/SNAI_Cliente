/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.controlador;

import epn.edu.ec.modelo.InformePsicologia;
import epn.edu.ec.servicios.InformePsicologiaServicio;
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
@Named(value = "panelInformePsicologiaControlador")
@ViewScoped
public class PanelInformePsicologiaControlador implements Serializable{

    private List<InformePsicologia> listaInformePsicologia;
    private InformePsicologiaServicio servicio;

    @PostConstruct
    public void init() {

        servicio = new InformePsicologiaServicio();

        listaInformePsicologia = new ArrayList<>();
        listaInformePsicologia = servicio.listarInformePsicologia();

    }

    public List<InformePsicologia> getListaInformePsicologia() {
        return listaInformePsicologia;
    }

    public void setListaInformePsicologia(List<InformePsicologia> listaInformePsicologia) {
        this.listaInformePsicologia = listaInformePsicologia;
    }

    public InformePsicologiaServicio getServicio() {
        return servicio;
    }

    public String verTallerPsicologia(InformePsicologia informe) {

        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("taller_psicologia", informe.getIdTallerPsicologia());
            return "/paginas/psicologia/taller_psicologia_ver.com?faces-redirect=true";

        } catch (Exception ex) {
            return null;
        }
    }

    public String verInformePsicologia(InformePsicologia informe){
        try {
            informe.setEstado("ver");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("informe_psicologia_editar", informe);
            return "/paginas/psicologia/informe_psicologia_editar.com?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }
    
    public String editarInformePsicologia(InformePsicologia informe){
        try {
            informe.setEstado("editar");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("informe_psicologia_editar", informe);
            return "/paginas/psicologia/informe_psicologia_editar.com?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }
}
