package epn.edu.ec.controlador;

import epn.edu.ec.modelo.Informe;
import epn.edu.ec.modelo.Usuario;
import epn.edu.ec.servicios.InformeServicio;
import epn.edu.ec.utilidades.EnlacesPrograma;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "panelInformeControlador")
@ViewScoped
public class PanelInformeControlador implements Serializable{

    private List<Informe> listaInforme;
    private InformeServicio servicio;
    private EnlacesPrograma enlaces;
    private Usuario usuario;

    @PostConstruct
    public void init() {

        servicio = new InformeServicio();
        enlaces=new EnlacesPrograma();
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");

        listaInforme = new ArrayList<>();
        if(usuario!=null){
            if(usuario.getIdRolUsuarioCentro().getIdRol().getRol().equals("ADMINISTRADOR")||usuario.getIdRolUsuarioCentro().getIdRol().getRol().equals("SUBDIRECTOR")){
                listaInforme = servicio.listarInforme();
            }else if(usuario.getIdRolUsuarioCentro().getIdRol().getRol().equals("COORDINADOR/LIDER UZDI")){
                listaInforme=servicio.listarInformesSoloUZDI();
            }else if(usuario.getIdRolUsuarioCentro().getIdRol().getRol().equals("COORDINADOR CAI")){
                listaInforme=servicio.listarInformesSoloCAI();
            }else {
                listaInforme = servicio.listarInformesPorUsuario(usuario);
            }
        }else{
            System.out.println("Hubo un problema");
        }
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
            //return "/paginas/psicologia/taller_psicologia_ver.com?faces-redirect=true";
            return enlaces.PATH_TALLER_VER+"?faces-redirect=true";
        } catch (Exception ex) {
            return null;
        }
    }

    public String verInforme(Informe informe){
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("informe_psicologia_editar", informe);
            //return "/paginas/user/informe/informe_ver.com?faces-redirect=true";
            return enlaces.PATH_INFORME_VER+"?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }
    
    public String editarInforme(Informe informe){
        try {
            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("informe_psicologia_editar", informe);
            //return "/paginas/psicologia/informe_psicologia_editar.com?faces-redirect=true";
            return enlaces.PATH_INFORME_EDITAR+"?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }
    
    public void eliminarTaller(Informe informeSeleccionado) {
        String rolActual = usuario.getIdRolUsuarioCentro().getIdRol().getRol();
        if ("ADMINISTRADOR".equals(rolActual)) {
            int statusRespuesta = servicio.eliminarInforme(informeSeleccionado.getIdInforme());

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
