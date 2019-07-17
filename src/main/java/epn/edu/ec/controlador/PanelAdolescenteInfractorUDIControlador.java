/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.servicios.AdolescenteInfractorUDIServicio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author User
 */
@Named(value = "panelAdolescenteInfractorUDIControlador")
@ViewScoped
public class PanelAdolescenteInfractorUDIControlador implements Serializable{

    private List<AdolescenteInfractorUDI> listadoAdolescentesInfractoresUDI;
    private AdolescenteInfractorUDIServicio servicio;
    
    @PostConstruct
    public void init(){
        servicio= new AdolescenteInfractorUDIServicio();
        listadoAdolescentesInfractoresUDI= new ArrayList<>();
        listadoAdolescentesInfractoresUDI=servicio.listaAdolescentesInfractoresUDI();
    }

    public List<AdolescenteInfractorUDI> getListadoAdolescentesInfractoresUDI() {
        return listadoAdolescentesInfractoresUDI;
    }

    public void setListadoAdolescentesInfractoresUDI(List<AdolescenteInfractorUDI> listadoAdolescentesInfractoresUDI) {
        this.listadoAdolescentesInfractoresUDI = listadoAdolescentesInfractoresUDI;
    }
    
    public String agregarInformacion(AdolescenteInfractorUDI ai_udi){
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adolescente_infractor_udi", ai_udi);
        return "/paginas/udi/matriz/panel_crear_udi.com?faces-redirect=true";
    }
    
    public String editarInformacion(AdolescenteInfractorUDI ai_udi){
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adolescente_infractor_udi", ai_udi);
        return "/paginas/udi/matriz/panel_editar_udi.com?faces-redirect=true";
    }
    
    public String eliminarAdolescenteInfractor(AdolescenteInfractorUDI adolescenteSeleccionado){
        
        int statusRespuesta=servicio.eliminarAdolescenteInfractor(adolescenteSeleccionado.getIdAdolescenteUdi());
        
        if(statusRespuesta==200){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado correctamente el Adolescente Infractor ","Aviso" ));
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ha ocurrido un error guardadando el Adolescente Infrctor","Error" ));
        }
        return "/paginas/udi/udi.com?faces-redirect=true";
    }
}
