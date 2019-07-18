/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.controlador;

import epn.edu.ec.modelo.Informe;
import epn.edu.ec.servicios.InformeServicio;
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
@Named(value = "panelInformeControlador")
@ViewScoped
public class PanelInformeControlador implements Serializable{

    private List<Informe> listaInforme;
    private InformeServicio servicio;

    @PostConstruct
    public void init() {

        servicio = new InformeServicio();

        listaInforme = new ArrayList<>();
        listaInforme = servicio.listarInforme();

    }

    public List<Informe> getListaInforme() {
        return listaInforme;
    }

    public void setListaInforme(List<Informe> listaInforme) {
        this.listaInforme = listaInforme;
    }

    public InformeServicio getServicio() {
        return servicio;
    }

    public String verTaller(Informe informe) {

        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("taller_psicologia", informe.getIdTaller());
            return "/paginas/psicologia/taller_psicologia_ver.com?faces-redirect=true";

        } catch (Exception ex) {
            return null;
        }
    }

    public String verInforme(Informe informe){
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("informe_psicologia_editar", informe);
            return "/paginas/psicologia/informe_psicologia_editar.com?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }
    
    public String editarInforme(Informe informe){
        try {
            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("informe_psicologia_editar", informe);
            return "/paginas/psicologia/informe_psicologia_editar.com?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }
}
