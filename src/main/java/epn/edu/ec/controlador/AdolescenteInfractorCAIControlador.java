package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractor;
import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.servicios.AdolescenteInfractorCAIServicio;
import epn.edu.ec.servicios.AdolescenteInfractorServicio;
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
    private AdolescenteInfractor adolescenteInfractorEditar;
    private AdolescenteInfractorServicio servicio;

    private AdolescenteInfractorCAI adolescenteInfractorCAIEditar;
    private AdolescenteInfractorCAI adolescenteInfractorCAICrear;
    private AdolescenteInfractorCAIServicio servicioCAI;

    private boolean guardado;

    //Objetos para saber si es cedula o documento
    String tipoDocumento;
    boolean esCedula;

    @PostConstruct
    public void init() {
        servicioCAI = new AdolescenteInfractorCAIServicio();
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

        adolescenteInfractorCAICrear = new AdolescenteInfractorCAI();
        this.adolescenteInfractorCrear.setTipo("CAI");
        adolescenteInfractorCAICrear.setIdAdolescenteInfractor(adolescenteInfractorCrear);
        adolescenteInfractorCAIEditar = new AdolescenteInfractorCAI();
        AdolescenteInfractorCAI adolescenteInfractorCAIAux = (AdolescenteInfractorCAI) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_cai");
        if (adolescenteInfractorCAIAux != null) {
            adolescenteInfractorCAIEditar = adolescenteInfractorCAIAux;
            guardado = true;
            if (adolescenteInfractorCAIAux.getIdAdolescenteInfractor().getCedula() != null && adolescenteInfractorCAIAux.getIdAdolescenteInfractor().getDocumento() == null) {
                tipoDocumento = "ECUATORIANA";
            } else if (adolescenteInfractorCAIAux.getIdAdolescenteInfractor().getCedula() == null && adolescenteInfractorCAIAux.getIdAdolescenteInfractor().getDocumento() != null) {
                tipoDocumento = "EXTRANJERA";
            }
            adolescenteInfractorEditar = adolescenteInfractorCAIEditar.getIdAdolescenteInfractor();
        }
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

    public AdolescenteInfractor getAdolescenteInfractorEditar() {
        return adolescenteInfractorEditar;
    }

    public void setAdolescenteInfractorEditar(AdolescenteInfractor adolescenteInfractorEditar) {
        this.adolescenteInfractorEditar = adolescenteInfractorEditar;
    }

    public AdolescenteInfractorCAI getAdolescenteInfractorCAIEditar() {
        return adolescenteInfractorCAIEditar;
    }

    public void setAdolescenteInfractorCAIEditar(AdolescenteInfractorCAI adolescenteInfractorCAIEditar) {
        this.adolescenteInfractorCAIEditar = adolescenteInfractorCAIEditar;
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
    public String guardarAdolescenteInfractor() {
        if (this.adolescenteInfractorCAICrear.getIdAdolescenteInfractor() != null) {
            if ("ECUATORIANA".equals(tipoDocumento)) {
                this.adolescenteInfractorCAICrear.getIdAdolescenteInfractor().setDocumento(null);
                this.adolescenteInfractorCAICrear.getIdAdolescenteInfractor().setNacionalidad(tipoDocumento);
            } else if ("EXTRANJERA".equals(tipoDocumento)) {
                this.adolescenteInfractorCAICrear.getIdAdolescenteInfractor().setCedula(null);
                this.adolescenteInfractorCAICrear.getIdAdolescenteInfractor().setNacionalidad(tipoDocumento);
            }
            AdolescenteInfractorCAI ai_cai = servicioCAI.guardarAdolescenteInfractorCAI(this.adolescenteInfractorCAICrear);
            if (ai_cai != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adolescente_infractor_cai", ai_cai);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado correctamente el Adolescente ", "Aviso"));
                return "/paginas/cai/matriz/panel_crear_cai.com?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ha ocurrido un error, no se guardó el Adolescente Infractor", "Error"));
                return null;
            }
        } else {
            System.out.println("Se tiene un adolescente en null");
            return null;
        }
    }
    
    public void guardarEdicionAdolescenteInfractor() {

                
        if (this.adolescenteInfractorCAIEditar.getIdAdolescenteInfractor() != null) {

            if ("ECUATORIANA".equals(tipoDocumento)) {
                this.adolescenteInfractorCAIEditar.getIdAdolescenteInfractor().setDocumento(null);
                this.adolescenteInfractorCAIEditar.getIdAdolescenteInfractor().setNacionalidad(tipoDocumento);
            } else if ("EXTRANJERA".equals(tipoDocumento)) {
                this.adolescenteInfractorCAIEditar.getIdAdolescenteInfractor().setCedula(null);
                this.adolescenteInfractorCAIEditar.getIdAdolescenteInfractor().setNacionalidad(tipoDocumento);
            }
            
            AdolescenteInfractorCAI ai_udi = servicioCAI.guardarEdicionAdolescenteInfractorCAI(this.adolescenteInfractorCAIEditar);
            if (ai_udi != null) {
                guardado = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL ADOLESCETE INFRACTOR CAI", "Información"));
            } else {
                guardado = true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO DEL ADOLESCENTE INFRACTOR CAI", "Error"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO DEL ADOLESCENTE INFRACTOR CAI", "Error"));
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

    public void limpiarMensajeCedulaEditar(AjaxBehaviorEvent evento) {
        String cedula = adolescenteInfractorCAIEditar.getIdAdolescenteInfractor().getCedula();
        if (validacion.cedulaValida(cedula)) {
            mensaje = "";
        } else {
            mensaje = "cédula incorrecta";
        }
    }

    public void validarCedulaEditar(AjaxBehaviorEvent evento) {
        String cedula = adolescenteInfractorCAIEditar.getIdAdolescenteInfractor().getCedula();
        if (validacion.cedulaValida(cedula)) {
            mensaje = "cédula correcta";
        } else {
            mensaje = "cédula incorrecta";
        }
    }

    public void limpiarMensajeFechaNacimientoEditar(AjaxBehaviorEvent evento) {
        Date fecha = adolescenteInfractorCAIEditar.getIdAdolescenteInfractor().getFechaNacimiento();
        if (validacion.verificarFechaNacimiento(fecha)) {
            mensaje1 = "";
        } else {
            mensaje1 = "Fecha incorrecta";
        }
    }

    public void validarFechaNacimientoEditar(AjaxBehaviorEvent evento) {
        Date fecha = adolescenteInfractorCAIEditar.getIdAdolescenteInfractor().getFechaNacimiento();
        if (validacion.verificarFechaNacimiento(fecha)) {
            mensaje1 = "Fecha correcta";
        } else {
            mensaje1 = "Fecha corresponde a una persona mayor de 17 años";
        }
    }
}
