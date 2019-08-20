package epn.edu.ec.controlador;

import epn.edu.ec.modelo.Taller;
import epn.edu.ec.modelo.Usuario;
import epn.edu.ec.servicios.TallerServicio;
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

@Named(value = "panelTallerControlador")
@ViewScoped
public class PanelTallerControlador implements Serializable {

    private List<Taller> listaTalleres;
    private TallerServicio servicio;
    private EnlacesPrograma enlaces;
    private Usuario usuario;
    private PermisosUsuario permisosUsuario;

    @PostConstruct
    public void init() {

        permisosUsuario= new PermisosUsuario();
        servicio = new TallerServicio();
        enlaces = new EnlacesPrograma();
        usuario = new Usuario();
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
        
        listaTalleres = new ArrayList<>();
        if(usuario!=null){
            if(usuario.getIdRolUsuarioCentro().getIdRol().getRol().equals("ADMINISTRADOR")||usuario.getIdRolUsuarioCentro().getIdRol().getRol().equals("SUBDIRECTOR")){
                listaTalleres = servicio.listaTalleresSinInforme();
            }else if(usuario.getIdRolUsuarioCentro().getIdRol().getRol().equals("COORDINADOR/LIDER UZDI")){
                listaTalleres=servicio.listaTalleresSinInformeSoloUZDI();
            }else if(usuario.getIdRolUsuarioCentro().getIdRol().getRol().equals("COORDINADOR CAI")){
                listaTalleres=servicio.listaTalleresSinInformeSoloCAI();
            }else {
                listaTalleres = servicio.listaTalleresSinInformePorUsuario(usuario);
            }
        }else{
            System.out.println("Hubo un problema");
        }

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
            String rolActual = permisosUsuario.RolUsuario();

            if(rolActual!=null){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("taller_psicologia", taller);

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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "NO TIENE ACCESO DE ADMINISTRADOR PARA REALIZAR ESTA ACCION", "ERROR"));
            return enlaces.PATH_ERROR+"?faces-redirect=true";
        }
    }
    
    public String editarTaller(Taller taller) {

        try {
            
            String rolActual = permisosUsuario.RolUsuario();
            
            if(rolActual!=null){
                
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("taller_psicologia", taller);
            
                if ("ADMINISTRADOR".equals(rolActual)){
                    return enlaces.PATH_TALLER_EDITAR_ADMINISTRADOR+"?faces-redirect=true";
                }
                else{
                    return enlaces.PATH_ERROR+"?faces-redirect=true";
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
    
    public String eliminarTaller(Taller tallerSeleccionado) {
        
        String rolActual = permisosUsuario.RolUsuario();
        
        if(rolActual!=null){
            
            if ("ADMINISTRADOR".equals(rolActual)) {
                int statusRespuesta = servicio.eliminarTaller(tallerSeleccionado.getIdTaller());

                if (statusRespuesta == 200 || statusRespuesta==204) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA ELIMINADO CORRECTAMENTE EL REGISTRO", "INFORMACION"));
                    return enlaces.PATH_PANEL_TALLER_ADMINISTRADOR+"?faces-redirect=true";
                } else {
                    return enlaces.PATH_ERROR+"?faces-redirect=true";
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "NO TIENE ACCESO DE ADMINISTRADOR PARA REALIZAR ESTA ACCION", "ERROR"));
                return enlaces.PATH_ERROR+"?faces-redirect=true";
            }
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR", "ERROR"));
            return enlaces.PATH_ERROR+"?faces-redirect=true";
        }
        
    }

    public String agregarInforme(Taller taller) {
        try {
            
            String rolActual = permisosUsuario.RolUsuario();
            if(rolActual!=null){
                
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("taller_psicologia", taller);

                if(rolActual.equals("ADMINISTRADOR")){
                
                    return enlaces.PATH_INFORME_CREAR_ADMINISTRADOR+"?faces-redirect=true";
                }else{
                    return enlaces.PATH_INFORME_CREAR_USER+"?faces-redirect=true";
                }
            }else{
                return enlaces.PATH_ERROR+"?faces-redirect=true";
            }
            
        } catch (Exception e) {
            return null;
        }
    }
}
