package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractor;
import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.servicios.AdolescenteInfractorServicio;
import epn.edu.ec.servicios.AdolescenteInfractorUDIServicio;
import epn.edu.ec.utilidades.Constantes;
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
    private AdolescenteInfractorServicio servicio;

    private AdolescenteInfractorUDI adolescenteInfractorUDICrear;
    private AdolescenteInfractorUDIServicio servicioUDI;
    private EnlacesPrograma enlaces;
    private PermisosUsuario permisos;
    private boolean guardado;

    //Objetos para saber si es cedula o documento
    String tipoDocumento;
    boolean esCedula;

    @PostConstruct
    public void init() {

        permisos = new PermisosUsuario();
        enlaces = new EnlacesPrograma();
        servicioUDI = new AdolescenteInfractorUDIServicio();
        guardado = false;
        validacion = new Validaciones();

        if (isEsCedula()) {
            tipoDocumento = "ECUATORIANA";
        } else {
            tipoDocumento = "EXTRANJERA";
        }

        adolescenteInfractorCrear = new AdolescenteInfractor();
        servicio = new AdolescenteInfractorServicio();

        adolescenteInfractorUDICrear = new AdolescenteInfractorUDI();
        this.adolescenteInfractorCrear.setTipo("UZDI");

        adolescenteInfractorUDICrear.setIdAdolescenteInfractor(adolescenteInfractorCrear);
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

    public AdolescenteInfractorServicio getServicio() {
        return servicio;
    }

    public void setServicio(AdolescenteInfractorServicio servicio) {
        this.servicio = servicio;
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
    public void guardarAdolescenteInfractor() {
        if (this.adolescenteInfractorUDICrear.getIdAdolescenteInfractor() != null) {
            
            Date fechaNacimiento=this.adolescenteInfractorUDICrear.getIdAdolescenteInfractor().getFechaNacimiento();
            
            if (validacion.verificarFechaNacimiento(fechaNacimiento)) {
            
                if ("ECUATORIANA".equals(tipoDocumento)) {

                    String cedula = this.adolescenteInfractorUDICrear.getIdAdolescenteInfractor().getCedula();
                    if (validacion.cedulaValida(cedula)) {
                        guardarRegistroAdolescenteInfractor();
                        
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, 
                                "ERROR AL GUARDAR EL ADOLESCENTE INFRACTOR, HA INGRESADO UNA CÉDULA INCORRECTA", 
                                "Error")
                        );
                    }

                } else if ("EXTRANJERA".equals(tipoDocumento)) {
                    guardarRegistroAdolescenteInfractor();
                }
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, 
                        "ERROR AL GUARDAR EL ADOLESCENTE INFRACTOR, NO DEBE SER MAYOR DE 26 AÑOS", 
                        "Error")
                );
            }
            
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, 
                    "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO DEL ADOLESCENTE INFRACTOR UZDI", 
                    "Error")
            );
        }
    }

    private void guardarRegistroAdolescenteInfractor() {
        
        AdolescenteInfractorUDI ai_udi = servicioUDI.guardarAdolescenteInfractorUDI(this.adolescenteInfractorUDICrear);

        if (ai_udi != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL ADOLESCETE INFRACTOR UZDI", "Información"));
            guardado = true;

        } else {
            guardado = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO DEL ADOLESCETE INFRACTOR UZDI", "Error"));
        }
    }
    
    public String reedireccionGuardarAdolescenteInfractor() throws InterruptedException {
        if(guardado==true){
            Thread.sleep(1250);
            String rol = permisos.RolUsuario();
            if (rol != null) {
                if (rol.equals(Constantes.ROL_ADMINISTRADOR)) {
                    return enlaces.PATH_PANEL_UDI_ADMINISTRADOR + "?faces-redirect=true";
                } else {
                    return enlaces.PATH_PANEL_UDI_USER + "?faces-redirect=true";
                }
            } else {
                return null;
            }
        }
        else{
            return null;
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
}
