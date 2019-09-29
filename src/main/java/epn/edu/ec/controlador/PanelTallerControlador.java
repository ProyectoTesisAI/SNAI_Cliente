package epn.edu.ec.controlador;

import epn.edu.ec.modelo.Taller;
import epn.edu.ec.modelo.Usuario;
import epn.edu.ec.servicios.TallerServicio;
import epn.edu.ec.utilidades.Constantes;
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
    private List<Taller> listaTalleresPorTipo;
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
        String tipoTaller = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tipoTallerSeleccionadoMenu");
        
        listaTalleres = new ArrayList<>();
        if(usuario!=null){
            
            String rol=usuario.getIdRolUsuarioCentro().getIdRol().getRol();
            
            if(rol.equals(Constantes.ROL_ADMINISTRADOR)|| rol.equals(Constantes.ROL_SUBDIRECTOR)){
                listaTalleres = servicio.listaTalleresSinInforme();
            }else if(rol.equals(Constantes.ROL_LIDER_UZDI) || rol.equals(Constantes.ROL_DIRECTOR_UZDI)){
                listaTalleres=servicio.listaTalleresSinInformeSoloUZDI();
            }else if(rol.equals(Constantes.ROL_COORDINADOR_CAI) || rol.equals( Constantes.ROL_DIRECTOR_CAI)){
                listaTalleres=servicio.listaTalleresSinInformeSoloCAI();
            }else {
                listaTalleres = servicio.listaTalleresSinInformePorUsuario(usuario);
            }
            
            if (tipoTaller == null) {

                if (rol.equals(Constantes.ROL_ADMINISTRADOR) || rol.equals(Constantes.ROL_SUBDIRECTOR) || rol.equals(Constantes.ROL_LIDER_UZDI) || rol.equals(Constantes.ROL_COORDINADOR_CAI) || rol.contains("DIRECTOR") || rol.contains("PSICOLOGO")) {
                    tipoTaller = Constantes.TIPO_TALLER_PSICOLOGIA;
                } else if (rol.contains("JURIDICO")) {
                    tipoTaller = Constantes.TIPO_TALLER_JURIDICO;
                } else if (rol.contains(Constantes.ROL_INSPECTOR_EDUCADOR)) {
                    tipoTaller = Constantes.TIPO_TALLER_INSPECTOR_EDUCADOR;
                }

            }
        }
        
        
        
        if(listaTalleres.size() > 0){
        
            listaTalleresPorTipo= new ArrayList<>();
            
            for(Taller e: listaTalleres){
                
                if(e.getTipo().equals(tipoTaller)){
                    listaTalleresPorTipo.add(e);
                }
            }
        }

    }

    public List<Taller> getListaTalleres() {
        return listaTalleresPorTipo;
    }

    public TallerServicio getServicio() {
        return servicio;
    }

    public String verTaller(Taller taller) {

        try {
            String rolActual = permisosUsuario.RolUsuario();

            if(rolActual!=null){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("taller_seleccionado", taller);

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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "NO TIENE ACCESO DE ADMINISTRADOR PARA REALIZAR ESTA ACCION", "ERROR"));
            return enlaces.PATH_ERROR+"?faces-redirect=true";
        }
    }
    
    public String editarTaller(Taller taller) {

        try {
            
            String rolActual = permisosUsuario.RolUsuario();
            
            if(rolActual!=null){
                
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("taller_seleccionado", taller);
            
                if (Constantes.ROL_ADMINISTRADOR.equals(rolActual)){
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
            
            if (Constantes.ROL_ADMINISTRADOR.equals(rolActual)) {
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
                
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("taller_seleccionado", taller);

                if(rolActual.equals(Constantes.ROL_ADMINISTRADOR)){
                
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
