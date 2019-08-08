package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.servicios.AdolescenteInfractorCAIServicio;
import epn.edu.ec.servicios.AdolescenteInfractorServicio;
import epn.edu.ec.utilidades.EnlacesPrograma;
import epn.edu.ec.utilidades.PermisosUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "panelAdolescenteInfractorCAIControlador")
@ViewScoped
public class PanelAdolescenteInfractorCAIControlador implements Serializable{

    private List<AdolescenteInfractorCAI> listadoAdolescentesInfractoresCAI;
    private AdolescenteInfractorCAIServicio servicio;
    private AdolescenteInfractorServicio servicioAI;
    private EnlacesPrograma enlaces;
    private PermisosUsuario permisosUsuario;
    
    @PostConstruct
    public void init() {
        permisosUsuario= new PermisosUsuario();
        servicio= new AdolescenteInfractorCAIServicio();
        servicioAI = new AdolescenteInfractorServicio();
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

        String gestionInformacionAdolescenteCai=permisosUsuario.redireccionGestionInformacionCai();
        
        if(gestionInformacionAdolescenteCai!=null){

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adolescente_infractor_cai", ai_cai);
            return gestionInformacionAdolescenteCai;
        }else{
            return null;
        }     
    }
    
    public String editarInformacion(AdolescenteInfractorCAI ai_cai) {

        String gestionInformacionAdolescenteCai=permisosUsuario.redireccionEditarInformacionCai();
        
        if(gestionInformacionAdolescenteCai!=null){

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adolescente_infractor_cai", ai_cai);
            return gestionInformacionAdolescenteCai;
        }else{
            return null;
        }     
    }
    
    public void eliminarAdolescenteInfractor(AdolescenteInfractorCAI adolescenteSeleccionado) {
        String rolActual = permisosUsuario.RolUsuario();
        if ("ADMINISTRADOR".equals(rolActual)) {
            int statusRespuesta = servicioAI.eliminarAdolescenteInfractor(adolescenteSeleccionado.getIdAdolescenteInfractor().getIdAdolescenteInfractor());

            if (statusRespuesta == 200) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA ELIMINADO CORRECTAMENTE EL REGISTRO", "INFORMACION"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR EN EL SERVICIO", "ERROR"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "NO TIENE ACCESO DE ADMINISTRADOR PARA REALIZAR ESTA ACCION", "ERROR"));
        }
    }

}
