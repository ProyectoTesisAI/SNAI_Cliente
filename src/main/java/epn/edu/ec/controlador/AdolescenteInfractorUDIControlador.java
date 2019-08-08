package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractor;
import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.servicios.AdolescenteInfractorServicio;
import epn.edu.ec.servicios.AdolescenteInfractorUDIServicio;
import epn.edu.ec.utilidades.EnlacesPrograma;
import epn.edu.ec.utilidades.PermisosUsuario;
import epn.edu.ec.utilidades.Validaciones;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "adolescenteInfractorUDIControlador")
@ViewScoped
public class AdolescenteInfractorUDIControlador implements Serializable {

    //mensajes que controlan las validaciones
    private String mensaje = "";
    private String mensaje1 = "";
    //Clase que contiene el codigo de ciertas validaciones
    private Validaciones validacion;

    private AdolescenteInfractor adolescenteInfractorCrear;
    private AdolescenteInfractor adolescenteInfractorEditar;
    private AdolescenteInfractorServicio servicio;

    private AdolescenteInfractorUDI adolescenteInfractorUDICrear;
    private AdolescenteInfractorUDI adolescenteInfractorUDIEditar;
    private AdolescenteInfractorUDIServicio servicioUDI;
    private EnlacesPrograma enlaces;
    private PermisosUsuario permisos;
    private boolean guardado;

    //Objetos para saber si es cedula o documento
    String tipoDocumento;
    boolean esCedula;

    @PostConstruct
    public void init() {
        
        permisos= new PermisosUsuario();
        enlaces=  new EnlacesPrograma();
        servicioUDI = new AdolescenteInfractorUDIServicio();
        guardado = false;
        validacion = new Validaciones();

        if (isEsCedula()) {
            tipoDocumento = "ECUATORIANA";
        } else {
            tipoDocumento = "EXTRANJERA";
        }

        adolescenteInfractorCrear = new AdolescenteInfractor();
        adolescenteInfractorEditar = new AdolescenteInfractor();
        servicio = new AdolescenteInfractorServicio();

        adolescenteInfractorUDICrear = new AdolescenteInfractorUDI();
        this.adolescenteInfractorCrear.setTipo("UZDI");
        
        adolescenteInfractorUDICrear.setIdAdolescenteInfractor(adolescenteInfractorCrear);
        adolescenteInfractorUDIEditar = new AdolescenteInfractorUDI();
        AdolescenteInfractorUDI adolescenteInfractorUDIAux = (AdolescenteInfractorUDI) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_udi");
        if (adolescenteInfractorUDIAux != null) {
            adolescenteInfractorUDIEditar = adolescenteInfractorUDIAux;
            guardado = true;
            if (adolescenteInfractorUDIAux.getIdAdolescenteInfractor().getCedula() != null && adolescenteInfractorUDIAux.getIdAdolescenteInfractor().getDocumento() == null) {
                tipoDocumento = "ECUATORIANA";
            } else if (adolescenteInfractorUDIAux.getIdAdolescenteInfractor().getCedula() == null && adolescenteInfractorUDIAux.getIdAdolescenteInfractor().getDocumento() != null) {
                tipoDocumento = "EXTRANJERA";
            }
            adolescenteInfractorEditar = adolescenteInfractorUDIEditar.getIdAdolescenteInfractor();
        }

    }

    public AdolescenteInfractorUDI getAdolescenteInfractorUDICrear() {
        return adolescenteInfractorUDICrear;
    }

    public void setAdolescenteInfractorUDICrear(AdolescenteInfractorUDI adolescenteInfractorCrear) {
        this.adolescenteInfractorUDICrear = adolescenteInfractorCrear;
    }

    public AdolescenteInfractor getAdolescenteInfractorCrear() {
        return adolescenteInfractorCrear;
    }

    public void setAdolescenteInfractorCrear(AdolescenteInfractor adolescenteInfractorCrear) {
        this.adolescenteInfractorCrear = adolescenteInfractorCrear;
    }

    public AdolescenteInfractor getAdolescenteInfractorEditar() {
        return adolescenteInfractorEditar;
    }

    public void setAdolescenteInfractorEditar(AdolescenteInfractor adolescenteInfractorEditar) {
        this.adolescenteInfractorEditar = adolescenteInfractorEditar;
    }

    public AdolescenteInfractorServicio getServicio() {
        return servicio;
    }

    public void setServicio(AdolescenteInfractorServicio servicio) {
        this.servicio = servicio;
    }

    public AdolescenteInfractorUDI getAdolescenteInfractorUDIEditar() {
        return adolescenteInfractorUDIEditar;
    }

    public void setAdolescenteInfractorUDIEditar(AdolescenteInfractorUDI adolescenteInfractorUDIEditar) {
        this.adolescenteInfractorUDIEditar = adolescenteInfractorUDIEditar;
    }

    public AdolescenteInfractorUDIServicio getServicioUDI() {
        return servicioUDI;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje1() {
        return mensaje1;
    }

    public void setMensaje1(String mensaje1) {
        this.mensaje1 = mensaje1;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
        this.adolescenteInfractorUDICrear.getIdAdolescenteInfractor().setNacionalidad(tipoDocumento);
        if ("ECUATORIANA".equals(tipoDocumento)) {
            esCedula = true;
            this.adolescenteInfractorUDICrear.getIdAdolescenteInfractor().setDocumento(null);
        } else if ("EXTRANJERA".equals(tipoDocumento)) {
            esCedula = false;
            this.adolescenteInfractorUDICrear.getIdAdolescenteInfractor().setCedula(null);
        }
    }

    public boolean isEsCedula() {
        if ("ECUATORIANA".equals(tipoDocumento)) {
            esCedula = true;
        } else if ("EXTRANJERA".equals(tipoDocumento)) {
            esCedula = false;
        }
        return esCedula;
    }

    public void setEsCedula(boolean esCedula) {
        this.esCedula = esCedula;
    }

    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }

    /**
     * ****Métodos para invocar a los diferentes servicios web************
     */
    public String guardarAdolescenteInfractor() {

        
        if (this.adolescenteInfractorUDICrear.getIdAdolescenteInfractor() != null) {
            
            if ("ECUATORIANA".equals(tipoDocumento)) {
                this.adolescenteInfractorUDICrear.getIdAdolescenteInfractor().setDocumento(null);
                this.adolescenteInfractorUDICrear.getIdAdolescenteInfractor().setNacionalidad(tipoDocumento);
            } else if ("EXTRANJERA".equals(tipoDocumento)) {
                this.adolescenteInfractorUDICrear.getIdAdolescenteInfractor().setCedula(null);
                this.adolescenteInfractorUDICrear.getIdAdolescenteInfractor().setNacionalidad(tipoDocumento);
            }

            AdolescenteInfractorUDI ai_udi = servicioUDI.guardarAdolescenteInfractorUDI(this.adolescenteInfractorUDICrear);
            if (ai_udi != null) {
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL ADOLESCETE INFRACTOR UZDI", "Información"));
                guardado=true;
                
                String rol= permisos.RolUsuario();
                if(rol!=null){
                    if(rol.equals("ADMINISTRADOR")){
                        return enlaces.PATH_PANEL_UDI_ADMINISTRADOR+"?faces-redirect=true";
                    }
                    else{
                        return enlaces.PATH_PANEL_UDI_USER+"?faces-redirect=true";
                    }
                }
                else{
                    return null;
                }

            } else {
                guardado=false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO DEL ADOLESCETE INFRACTOR UZDI", "Error"));
                return null;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO DEL ADOLESCENTE INFRACTOR UZDI", "Error"));
            return null;
        }
    }

    public void guardarEdicionAdolescenteInfractor() {

                
        if (this.adolescenteInfractorUDIEditar.getIdAdolescenteInfractor() != null) {

            if ("ECUATORIANA".equals(tipoDocumento)) {
                this.adolescenteInfractorUDIEditar.getIdAdolescenteInfractor().setDocumento(null);
                this.adolescenteInfractorUDIEditar.getIdAdolescenteInfractor().setNacionalidad(tipoDocumento);
            } else if ("EXTRANJERA".equals(tipoDocumento)) {
                this.adolescenteInfractorUDIEditar.getIdAdolescenteInfractor().setCedula(null);
                this.adolescenteInfractorUDIEditar.getIdAdolescenteInfractor().setNacionalidad(tipoDocumento);
            }
            
            AdolescenteInfractorUDI ai_udi = servicioUDI.guardarEdicionAdolescenteInfractorUDI(this.adolescenteInfractorUDIEditar);
            if (ai_udi != null) {             
                guardado = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL ADOLESCETE INFRACTOR UZDI", "Información"));
                
            } else {
                guardado = true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO DEL ADOLESCENTE INFRACTOR UZDI", "Error"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO DEL ADOLESCENTE INFRACTOR UZDI", "Error"));
        }
    }

    public void limpiarMensajeCedula(AjaxBehaviorEvent evento) {
        String cedula = adolescenteInfractorUDICrear.getIdAdolescenteInfractor().getCedula();
        if (validacion.cedulaValida(cedula)) {
            mensaje = "";
        } else {
            mensaje = "cédula incorrecta";
        }
    }

    public void validarCedula(AjaxBehaviorEvent evento) {
        String cedula = adolescenteInfractorUDICrear.getIdAdolescenteInfractor().getCedula();
        if (validacion.cedulaValida(cedula)) {
            mensaje = "cédula correcta";
        } else {
            mensaje = "cédula incorrecta";
        }
    }

    public void limpiarMensajeFechaNacimiento(AjaxBehaviorEvent evento) {
        Date fecha = adolescenteInfractorUDICrear.getIdAdolescenteInfractor().getFechaNacimiento();
        if (validacion.verificarFechaNacimiento(fecha)) {
            mensaje1 = "";
        } else {
            mensaje1 = "Fecha incorrecta";
        }
    }

    public void validarFechaNacimiento(AjaxBehaviorEvent evento) {
        Date fecha = adolescenteInfractorUDICrear.getIdAdolescenteInfractor().getFechaNacimiento();
        if (validacion.verificarFechaNacimiento(fecha)) {
            mensaje1 = "Fecha correcta";
        } else {
            mensaje1 = "Fecha corresponde a una persona mayor de 17 años";
        }
    }

    public void limpiarMensajeCedulaEditar(AjaxBehaviorEvent evento) {
        String cedula = adolescenteInfractorUDIEditar.getIdAdolescenteInfractor().getCedula();
        if (validacion.cedulaValida(cedula)) {
            mensaje = "";
        } else {
            mensaje = "cédula incorrecta";
        }
    }

    public void validarCedulaEditar(AjaxBehaviorEvent evento) {
        String cedula = adolescenteInfractorUDIEditar.getIdAdolescenteInfractor().getCedula();
        if (validacion.cedulaValida(cedula)) {
            mensaje = "cédula correcta";
        } else {
            mensaje = "cédula incorrecta";
        }
    }

    public void limpiarMensajeFechaNacimientoEditar(AjaxBehaviorEvent evento) {
        Date fecha = adolescenteInfractorUDIEditar.getIdAdolescenteInfractor().getFechaNacimiento();
        if (validacion.verificarFechaNacimiento(fecha)) {
            mensaje1 = "";
        } else {
            mensaje1 = "Fecha incorrecta";
        }
    }

    public void validarFechaNacimientoEditar(AjaxBehaviorEvent evento) {
        Date fecha = adolescenteInfractorUDIEditar.getIdAdolescenteInfractor().getFechaNacimiento();
        if (validacion.verificarFechaNacimiento(fecha)) {
            mensaje1 = "Fecha correcta";
        } else {
            mensaje1 = "Fecha corresponde a una persona mayor de 17 años";
        }
    }
}
