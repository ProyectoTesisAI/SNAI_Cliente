package epn.edu.ec.controlador;

import epn.edu.ec.modelo.Informe;
import epn.edu.ec.modelo.Usuario;
import epn.edu.ec.servicios.InformeServicio;
import epn.edu.ec.utilidades.Constantes;
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
    private List<Informe> listaInformePorTipo;
    private InformeServicio servicio;
    private EnlacesPrograma enlaces;
    private Usuario usuario;
    private PermisosUsuario permisosUsuario;
    private String tipoTaller;

    @PostConstruct
    public void init() {

        permisosUsuario= new PermisosUsuario();
        servicio = new InformeServicio();
        enlaces=new EnlacesPrograma();
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
        tipoTaller = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tipoTallerSeleccionadoMenu");
        
        listaInforme = new ArrayList<>();
        if(usuario!=null){
            
            if(usuario.getIdRolUsuarioCentro().getIdRol().getRol().equals(Constantes.ROL_ADMINISTRADOR)||usuario.getIdRolUsuarioCentro().getIdRol().getRol().equals(Constantes.ROL_SUBDIRECTOR)){
                listaInforme = servicio.listarInforme();
            }else if(usuario.getIdRolUsuarioCentro().getIdRol().getRol().equals(Constantes.ROL_LIDER_UZDI)||usuario.getIdRolUsuarioCentro().getIdRol().getRol().equals(Constantes.ROL_DIRECTOR_UZDI)){
                listaInforme=servicio.listarInformesSoloUZDI();
            }else if(usuario.getIdRolUsuarioCentro().getIdRol().getRol().equals(Constantes.ROL_COORDINADOR_CAI)||usuario.getIdRolUsuarioCentro().getIdRol().getRol().equals(Constantes.ROL_DIRECTOR_CAI)){
                listaInforme=servicio.listarInformesSoloCAI();
            }else {
                listaInforme = servicio.listarInformesPorUsuario(usuario);
            }
        }
        
        if(listaInforme.size() > 0){
        
            listaInformePorTipo= new ArrayList<>();
            
            for(Informe i: listaInforme){
                
                if(i.getIdTaller().getTipo().equals(tipoTaller)){
                    listaInformePorTipo.add(i);
                }
            }
        }
    }

    public List<Informe> getListaInforme() {
        return listaInformePorTipo;
    }

    public InformeServicio getServicio() {
        return servicio;
    }

    public String getTipoTaller() {
        return tipoTaller;
    }

    public void setTipoTaller(String tipoTaller) {
        this.tipoTaller = tipoTaller;
    }

    
    public String verTaller(Informe informe) {

        try {
            String rolActual = permisosUsuario.RolUsuario();

            if(rolActual!=null){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("taller_seleccionado", informe.getIdTaller());

                if (Constantes.ROL_ADMINISTRADOR.equals(rolActual)){
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

                if (Constantes.ROL_ADMINISTRADOR.equals(rolActual)){
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

                if (Constantes.ROL_ADMINISTRADOR.equals(rolActual)){
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
    
            if (Constantes.ROL_ADMINISTRADOR.equals(rolActual)) {
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
