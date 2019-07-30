package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.servicios.AdolescenteInfractorUDIServicio;
import epn.edu.ec.utilidades.EnlacesPrograma;
import epn.edu.ec.utilidades.PermisosUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "panelAdolescenteInfractorUDIControlador")
@ViewScoped
public class PanelAdolescenteInfractorUDIControlador implements Serializable{

    private List<AdolescenteInfractorUDI> listadoAdolescentesInfractoresUDI;
    private AdolescenteInfractorUDIServicio servicio;
    private EnlacesPrograma enlaces;
    private PermisosUsuario permisosUsuario;
    
    @PostConstruct
    public void init(){
        
        permisosUsuario= new PermisosUsuario();
        servicio= new AdolescenteInfractorUDIServicio();
        enlaces=new EnlacesPrograma();
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
        
        String gestionInformacionAdolescenteUzdi=permisosUsuario.redireccionGestionInformacionUzdi();
        
        if(gestionInformacionAdolescenteUzdi!=null){

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adolescente_infractor_udi", ai_udi);
            return gestionInformacionAdolescenteUzdi;
        }else{
            return null;
        }     
    }
    
    public String editarInformacion(AdolescenteInfractorUDI ai_udi){
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adolescente_infractor_udi", ai_udi);
        return "/paginas/udi/matriz/panel_editar_udi.com?faces-redirect=true";
        //return enlaces.PATH_ADOLESCENTE_UDI_CREAR+"?faces-redirect=true";
    }
    
    public String eliminarAdolescenteInfractor(AdolescenteInfractorUDI adolescenteSeleccionado){
        
        int statusRespuesta=servicio.eliminarAdolescenteInfractor(adolescenteSeleccionado.getIdAdolescenteInfractor().getIdAdolescenteInfractor());
        
        if(statusRespuesta==200){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado correctamente el Adolescente Infractor ","Aviso" ));
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ha ocurrido un error guardadando el Adolescente Infrctor","Error" ));
        }
        return "/paginas/udi/udi.com?faces-redirect=true";
    }
}
