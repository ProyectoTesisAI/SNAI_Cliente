package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractor;
import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.servicios.AdolescenteInfractorCAIServicio;
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

@Named(value = "adolescenteInfractorCAIControlador")
@ViewScoped
public class AdolescenteInfractorCAIControlador implements Serializable {

    //mensajes que controlan las validaciones
    private String mensaje = "";
    private String mensaje1 = "";
    private String mensaje2 = "";
    //Objeto que contiene el codigo de las validaciones
    private Validaciones validacion;

    private AdolescenteInfractor adolescenteInfractorCrear;
    private AdolescenteInfractorCAI adolescenteInfractorCAICrear;
    private AdolescenteInfractorCAIServicio servicioCAI;
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
        servicioCAI = new AdolescenteInfractorCAIServicio();
        guardado = false;
        validacion = new Validaciones();

        if (isEsCedula()) {
            tipoDocumento = "ECUATORIANA";
        } else {
            tipoDocumento = "EXTRANJERA";
        }

        adolescenteInfractorCrear = new AdolescenteInfractor();

        adolescenteInfractorCAICrear = new AdolescenteInfractorCAI();
        this.adolescenteInfractorCrear.setTipo("CAI");
        adolescenteInfractorCAICrear.setIdAdolescenteInfractor(adolescenteInfractorCrear);
        
    }

    public AdolescenteInfractorCAI getAdolescenteInfractorCAICrear() {
        return adolescenteInfractorCAICrear;
    }

    public void setAdolescenteInfractorCAICrear(AdolescenteInfractorCAI adolescenteInfractorCAICrear) {
        this.adolescenteInfractorCAICrear = adolescenteInfractorCAICrear;
    }

    public AdolescenteInfractor getAdolescenteInfractorCrear() {
        return adolescenteInfractorCrear;
    }

    public void setAdolescenteInfractorCrear(AdolescenteInfractor adolescenteInfractorCrear) {
        this.adolescenteInfractorCrear = adolescenteInfractorCrear;
    }

    public AdolescenteInfractorCAIServicio getServicioCAI() {
        return servicioCAI;
    }

    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
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

    public String getMensaje2() {
        return mensaje2;
    }

    public void setMensaje2(String mensaje2) {
        this.mensaje2 = mensaje2;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
        this.adolescenteInfractorCAICrear.getIdAdolescenteInfractor().setNacionalidad(tipoDocumento);
        if ("ECUATORIANA".equals(tipoDocumento)) {
            esCedula = true;
            this.adolescenteInfractorCAICrear.getIdAdolescenteInfractor().setDocumento(null);
        } else if ("EXTRANJERA".equals(tipoDocumento)) {
            esCedula = false;
            this.adolescenteInfractorCAICrear.getIdAdolescenteInfractor().setCedula(null);
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

    //Métodos para invocar a los diferentes servicios web************
    public void guardarAdolescenteInfractor() {
        
        if (this.adolescenteInfractorCAICrear.getIdAdolescenteInfractor() != null) {
            
            Date fechaNacimiento=this.adolescenteInfractorCAICrear.getIdAdolescenteInfractor().getFechaNacimiento();
            
            if (validacion.verificarFechaNacimiento(fechaNacimiento)) {
            
                if ("ECUATORIANA".equals(tipoDocumento)) {

                    String cedula = this.adolescenteInfractorCAICrear.getIdAdolescenteInfractor().getCedula();
                    if (validacion.cedulaValida(cedula)) {
                        guardarRegistroAdolescenteInfractor();
                        
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR AL GUARDAR EL ADOLESCENTE INFRACTOR, HA INGRESADO UNA CÉDULA INCORRECTA", "Error"));
                    }

                } else if ("EXTRANJERA".equals(tipoDocumento)) {
                    guardarRegistroAdolescenteInfractor();
                }
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR AL GUARDAR EL ADOLESCENTE INFRACTOR, NO DEBE SER MAYOR DE 26 AÑOS", "Error"));
            }
            
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO DEL ADOLESCENTE INFRACTOR CAI", "Error"));
        }
        
    }

    private void guardarRegistroAdolescenteInfractor() {
        
        AdolescenteInfractorCAI ai_cai = servicioCAI.guardarAdolescenteInfractorCAI(this.adolescenteInfractorCAICrear);
               
        if (ai_cai != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL ADOLESCETE INFRACTOR CAI", "Información"));
            guardado = true;

        } else {
            guardado = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO DEL ADOLESCETE INFRACTOR CAI", "Error"));
        }
    }
    
    public String redireccionGuardarAdolescenteInfractor() throws InterruptedException {
        if(guardado==true){
            Thread.sleep(1250);
            String rol = permisos.RolUsuario();
            if (rol != null) {
                if (rol.equals(Constantes.ROL_ADMINISTRADOR)) {
                    return enlaces.PATH_PANEL_CAI_ADMINISTRADOR + "?faces-redirect=true";
                } else {
                    return enlaces.PATH_PANEL_CAI_USER + "?faces-redirect=true";
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
        String cedula = adolescenteInfractorCAICrear.getIdAdolescenteInfractor().getCedula();
        if (validacion.cedulaValida(cedula)) {
            mensaje = "";
        } else {
            mensaje = "cédula incorrecta";
        }
    }

    public void validarCedula(AjaxBehaviorEvent evento) {

        String cedula = adolescenteInfractorCAICrear.getIdAdolescenteInfractor().getCedula();
        if (validacion.cedulaValida(cedula)) {
            mensaje = "cédula correcta";
        } else {
            mensaje = "cédula incorrecta";
        }
    }

    public void limpiarMensajeFechaNacimiento(AjaxBehaviorEvent evento) {
        Date fecha = adolescenteInfractorCAICrear.getIdAdolescenteInfractor().getFechaNacimiento();
        if (validacion.verificarFechaNacimiento(fecha)) {
            mensaje1 = "";
        } else {
            mensaje1 = "Fecha incorrecta";
        }
    }

    public void validarFechaNacimiento(AjaxBehaviorEvent evento) {
        Date fecha = adolescenteInfractorCAICrear.getIdAdolescenteInfractor().getFechaNacimiento();
        if (validacion.verificarFechaNacimiento(fecha)) {
            mensaje1 = "Fecha correcta";
        } else {
            mensaje1 = "Fecha incorrecta";
        }
    }

}
