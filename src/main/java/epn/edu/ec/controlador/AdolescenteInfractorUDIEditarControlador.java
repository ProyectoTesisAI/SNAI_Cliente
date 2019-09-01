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

@Named(value = "adolescenteInfractorUDIEditarControlador")
@ViewScoped
public class AdolescenteInfractorUDIEditarControlador implements Serializable {

    //mensajes que controlan las validaciones
    private String mensaje = "";
    private String mensaje1 = "";
    //Clase que contiene el codigo de ciertas validaciones
    private Validaciones validacion;

    private AdolescenteInfractor adolescenteInfractorEditar;
    private AdolescenteInfractorServicio servicio;

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

        adolescenteInfractorEditar = new AdolescenteInfractor();
        servicio = new AdolescenteInfractorServicio();

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
        if ("ECUATORIANA".equals(tipoDocumento)) {
            esCedula = true;
        } else if ("EXTRANJERA".equals(tipoDocumento)) {
            esCedula = false;
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

    
    public void guardarEdicionAdolescenteInfractor() {
        if (this.adolescenteInfractorUDIEditar.getIdAdolescenteInfractor() != null) {
            
            Date fechaNacimiento=this.adolescenteInfractorUDIEditar.getIdAdolescenteInfractor().getFechaNacimiento();
            
            if (validacion.verificarFechaNacimiento(fechaNacimiento)) {
            
                if ("ECUATORIANA".equals(tipoDocumento)) {

                    String cedula = this.adolescenteInfractorUDIEditar.getIdAdolescenteInfractor().getCedula();
                    if (validacion.cedulaValida(cedula)) {
                        guardarRegistroAdolescenteInfractor();
                        
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR AL GUARDAR EL ADOLESCENTE INFRACTOR, HA INGRESADO UNA CÉDULA INCORRECTA", "Error"));
                    }

                } else if ("EXTRANJERA".equals(tipoDocumento)) {
                    guardarRegistroAdolescenteInfractor();
                }
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR AL GUARDAR EL ADOLESCENTE INFRACTOR, NO DEBE SER MAYOR DE 18 AÑOS", "Error"));
            }
            
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO DEL ADOLESCENTE INFRACTOR UZDI", "Error"));
        }
    }

    private void guardarRegistroAdolescenteInfractor() {
        
        AdolescenteInfractorUDI ai_udi = servicioUDI.guardarEdicionAdolescenteInfractorUDI(this.adolescenteInfractorUDIEditar);

        if (ai_udi != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL ADOLESCETE INFRACTOR UZDI", "Información"));
            guardado = false;

        } else {
            guardado = true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO DEL ADOLESCETE INFRACTOR UZDI", "Error"));
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
