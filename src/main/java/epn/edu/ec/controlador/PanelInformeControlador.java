package epn.edu.ec.controlador;

import epn.edu.ec.modelo.Informe;
import epn.edu.ec.modelo.Usuario;
import epn.edu.ec.servicios.InformeServicio;
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

@Named(value = "panelInformeControlador")
@ViewScoped
public class PanelInformeControlador implements Serializable{

    private List<Informe> listaInforme;
    private InformeServicio servicio;
    private EnlacesPrograma enlaces;
    private Usuario usuario;
    private PermisosUsuario permisosUsuario;

    @PostConstruct
    public void init() {

        permisosUsuario= new PermisosUsuario();
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
            String rolActual = permisosUsuario.RolUsuario();

            if(rolActual!=null){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("taller_psicologia", informe.getIdTaller());

                if ("ADMINISTRADOR".equals(rolActual)){
                    return enlaces.PATH_TALLER_VER_ADMINISTRADOR+"?faces-redirect=true";
                }
                else{
                    return enlaces.PATH_TALLER_VER_USER+"?faces-redirect=true";
                }
            }
            else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR", "ERROR"));
                return enlaces.PATH_ERROR+"?faces-redirect=true";
            }
        } catch (Exception ex) {
            return enlaces.PATH_ERROR+"?faces-redirect=true";
        }
    }

    public String verInforme(Informe informe){
        try {
            
            String rolActual = permisosUsuario.RolUsuario();

            if(rolActual!=null){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("informe_psicologia_editar", informe);

                if ("ADMINISTRADOR".equals(rolActual)){
                    return enlaces.PATH_INFORME_VER_ADMINISTRADOR+"?faces-redirect=true";
                }
                else{
                    return enlaces.PATH_INFORME_VER_USER+"?faces-redirect=true";
                }
            }
            else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR", "ERROR"));
                return enlaces.PATH_ERROR+"?faces-redirect=true";
            }

        } catch (Exception e) {
            return enlaces.PATH_ERROR+"?faces-redirect=true";
        }
    }
    
    public String editarInforme(Informe informe){
        try {
            String rolActual = permisosUsuario.RolUsuario();

            if(rolActual!=null){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("informe_psicologia_editar", informe);

                if ("ADMINISTRADOR".equals(rolActual)){
                    return enlaces.PATH_INFORME_EDITAR_ADMINISTRADOR+"?faces-redirect=true";
                }
                else{
                    return enlaces.PATH_ERROR+"?faces-redirect=true";
                }
            }
            else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR", "ERROR"));
                return enlaces.PATH_ERROR+"?faces-redirect=true";
            }

        } catch (Exception e) {
            return enlaces.PATH_ERROR+"?faces-redirect=true";
        }
    }
    
    public String eliminarTaller(Informe informeSeleccionado) {
        String rolActual = permisosUsuario.RolUsuario();
        
        if(rolActual!=null){
    
            if ("ADMINISTRADOR".equals(rolActual)) {
                int statusRespuesta = servicio.eliminarInforme(informeSeleccionado.getIdInforme());

                if (statusRespuesta == 200 || statusRespuesta==204) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA ELIMINADO CORRECTAMENTE EL REGISTRO", "INFORMACION"));
                    return enlaces.PATH_PANEL_INFORME_ADMINISTRADOR+"?faces-redirect=true";
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL ELIMINAR EL REGISTRO", "ERROR"));
                    return null;
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "NO TIENE ACCESO DE ADMINISTRADOR PARA REALIZAR ESTA ACCION", "ERROR"));
                return enlaces.PATH_ERROR+"?faces-redirect=true";
            }
        }else {
                
            return enlaces.PATH_ERROR+"?faces-redirect=true";
        }
        
    }
}
