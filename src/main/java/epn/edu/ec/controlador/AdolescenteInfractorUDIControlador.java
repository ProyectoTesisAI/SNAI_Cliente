package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.servicios.AdolescenteInfractorUDIServicio;
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

    private AdolescenteInfractorUDI adolescenteInfractorUDIEditar;
    private AdolescenteInfractorUDI adolescenteInfractorUDICrear;
    private AdolescenteInfractorUDIServicio servicio;
    private boolean guardado;

    //Objetos para saber si es cedula o documento
    String tipoDocumento;
    boolean esCedula;
    String tipoDiscapacidad;
    boolean esDiscapacidad;

    @PostConstruct
    public void init() {
        servicio = new AdolescenteInfractorUDIServicio();
        guardado = false;
        validacion = new Validaciones();
        
        if(isEsCedula()){
            tipoDocumento="ECUATORIANA";
        }else{
            tipoDocumento="EXTRANJERA";
        }
        
        if(isEsDiscapacidad()){
            tipoDiscapacidad="SI";
        }else{
            tipoDiscapacidad="NO";
        }

        adolescenteInfractorUDICrear = new AdolescenteInfractorUDI();

        adolescenteInfractorUDIEditar = new AdolescenteInfractorUDI();
        AdolescenteInfractorUDI adolescenteInfractorUDIAux = (AdolescenteInfractorUDI) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_udi");

        if (adolescenteInfractorUDIAux != null) {
            adolescenteInfractorUDIEditar = adolescenteInfractorUDIAux;
            guardado = true;
            if(adolescenteInfractorUDIAux.getCedula()!=null && adolescenteInfractorUDIAux.getDocumento()==null){
                tipoDocumento="ECUATORIANA";
                System.out.println("1: "+adolescenteInfractorUDIEditar.getNacionalidad());
            }else if(adolescenteInfractorUDIAux.getCedula()==null && adolescenteInfractorUDIAux.getDocumento()!=null){
                tipoDocumento="EXTRANJERA";
                System.out.println("2: "+adolescenteInfractorUDIEditar.getNacionalidad());
            }
            if(adolescenteInfractorUDIAux.getDiscapacidad()!=null){
                tipoDiscapacidad="SI";
                System.out.println("3: "+adolescenteInfractorUDIEditar.getDiscapacidad());
            }else{
                tipoDiscapacidad="NO";
                System.out.println("4: "+adolescenteInfractorUDIEditar.getDiscapacidad());
            }
        }
    }

    public AdolescenteInfractorUDI getAdolescenteInfractorUDICrear() {
        return adolescenteInfractorUDICrear;
    }

    public void setAdolescenteInfractorUDICrear(AdolescenteInfractorUDI adolescenteInfractorCrear) {
        this.adolescenteInfractorUDICrear = adolescenteInfractorCrear;
    }

    public AdolescenteInfractorUDI getAdolescenteInfractorUDIEditar() {
        return adolescenteInfractorUDIEditar;
    }

    public void setAdolescenteInfractorUDIEditar(AdolescenteInfractorUDI adolescenteInfractorUDIEditar) {
        this.adolescenteInfractorUDIEditar = adolescenteInfractorUDIEditar;
    }

    public AdolescenteInfractorUDIServicio getServicio() {
        return servicio;
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
    
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
        if("ECUATORIANA".equals(tipoDocumento)){
            esCedula=true;
        }else if("EXTRANJERA".equals(tipoDocumento)){
            esCedula=false;
        }
    }

    public boolean isEsCedula() {
        if("ECUATORIANA".equals(tipoDocumento)){
            esCedula=true;
        }else if("EXTRANJERA".equals(tipoDocumento)){
            esCedula=false;
        }
        return esCedula;
    }

    public void setEsCedula(boolean esCedula) {
        this.esCedula = esCedula;
    }

    public String getTipoDiscapacidad() {
        return tipoDiscapacidad;
    }

    public void setTipoDiscapacidad(String tipoDiscapacidad) {
        this.tipoDiscapacidad = tipoDiscapacidad;
        if("SI".equals(tipoDiscapacidad)){
            esDiscapacidad=true;
        }else if("NO".equals(tipoDiscapacidad) || "EN PROCESO DE CERTIFICACIÓN".equals(tipoDiscapacidad)){
            esDiscapacidad=false;
        }
    }

    public boolean isEsDiscapacidad() {
        if("SI".equals(tipoDiscapacidad)){
            esDiscapacidad=true;
        }else if("NO".equals(tipoDiscapacidad) || "EN PROCESO DE CERTIFICACIÓN".equals(tipoDiscapacidad)){
            esDiscapacidad=false;
        }
        return esDiscapacidad;
    }

    public void setEsDiscapacidad(boolean esDiscapacidad) {
        this.esDiscapacidad = esDiscapacidad;
    }
    
    

    /**
     * ****Métodos para invocar a los diferentes servicios web************
     */
    public String guardarAdolescenteInfractor() {
        
        if(tipoDocumento!=null){
            this.adolescenteInfractorUDICrear.setNacionalidad(tipoDocumento);
        }

        AdolescenteInfractorUDI ai_udi = servicio.guardarAdolescenteInfractorUDI(this.adolescenteInfractorUDICrear);
        if (ai_udi != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adolescente_infractor_udi", ai_udi);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado correctamente el Adolescente ", "Aviso"));
            return "/paginas/udi/matriz/panel_crear_udi.com?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ha ocurrido un error, no se guardó el Adolescente Infractor", "Error"));
            return null;
        }
    }

    public void limpiarMensajeCedula(AjaxBehaviorEvent evento) {
        String cedula = adolescenteInfractorUDICrear.getCedula();
        if (validacion.cedulaValida(cedula)) {
            mensaje = "";
        } else {
            mensaje = "cédula incorrecta";
        }
    }

    public void validarCedula(AjaxBehaviorEvent evento) {
        String cedula = adolescenteInfractorUDICrear.getCedula();
        if (validacion.cedulaValida(cedula)) {
            mensaje = "cédula correcta";
        } else {
            mensaje = "cédula incorrecta";
        }
    }

    public void limpiarMensajeFechaNacimiento(AjaxBehaviorEvent evento) {
        Date fecha = adolescenteInfractorUDICrear.getFechaNacimiento();
        if (validacion.verificarFechaNacimiento(fecha)) {
            mensaje1 = "";
        } else {
            mensaje1 = "Fecha incorrecta";
        }
    }

    public void validarFechaNacimiento(AjaxBehaviorEvent evento) {
        Date fecha = adolescenteInfractorUDIEditar.getFechaNacimiento();
        if (validacion.verificarFechaNacimiento(fecha)) {
            mensaje1 = "Fecha correcta";
        } else {
            mensaje1 = "Fecha corresponde a una persona mayor de 17 años";
        }
    }

    public void limpiarMensajeCedulaEditar(AjaxBehaviorEvent evento) {
        String cedula = adolescenteInfractorUDIEditar.getCedula();
        if (validacion.cedulaValida(cedula)) {
            mensaje = "";
        } else {
            mensaje = "cédula incorrecta";
        }
    }

    public void validarCedulaEditar(AjaxBehaviorEvent evento) {
        String cedula = adolescenteInfractorUDIEditar.getCedula();
        if (validacion.cedulaValida(cedula)) {
            mensaje = "cédula correcta";
        } else {
            mensaje = "cédula incorrecta";
        }
    }

    public void limpiarMensajeFechaNacimientoEditar(AjaxBehaviorEvent evento) {
        Date fecha = adolescenteInfractorUDIEditar.getFechaNacimiento();
        if (validacion.verificarFechaNacimiento(fecha)) {
            mensaje1 = "";
        } else {
            mensaje1 = "Fecha incorrecta";
        }
    }

    public void validarFechaNacimientoEditar(AjaxBehaviorEvent evento) {
        Date fecha = adolescenteInfractorUDIEditar.getFechaNacimiento();
        if (validacion.verificarFechaNacimiento(fecha)) {
            mensaje1 = "Fecha correcta";
        } else {
            mensaje1 = "Fecha corresponde a una persona mayor de 17 años";
        }
    }
}
