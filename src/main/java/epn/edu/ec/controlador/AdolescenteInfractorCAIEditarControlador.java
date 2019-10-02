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

@Named(value = "adolescenteInfractorCAIEditarControlador")
@ViewScoped
public class AdolescenteInfractorCAIEditarControlador implements Serializable {

    //mensajes que controlan las validaciones
    private String mensaje = "";
    private String mensaje1 = "";
    private String mensaje2 = "";
    //Objeto que contiene el codigo de las validaciones
    private Validaciones validacion;

    private AdolescenteInfractor adolescenteInfractorEditar;
   
    private AdolescenteInfractorCAI adolescenteInfractorCAIEditar;
    private AdolescenteInfractorCAIServicio servicioCAI;
    private boolean guardado;

    //Objetos para saber si es cedula o documento
    String tipoDocumento;
    boolean esCedula;
    
    private PermisosUsuario permisos;
    private EnlacesPrograma enlaces;

    @PostConstruct
    public void init() {
        
        servicioCAI = new AdolescenteInfractorCAIServicio();
        guardado = false;
        validacion = new Validaciones();
        permisos=new PermisosUsuario();
        enlaces = new EnlacesPrograma();

        if (isEsCedula()) {
            tipoDocumento = "ECUATORIANA";
        } else {
            tipoDocumento = "EXTRANJERA";
        }

        adolescenteInfractorEditar = new AdolescenteInfractor();
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
        this.adolescenteInfractorCAIEditar.getIdAdolescenteInfractor().setNacionalidad(tipoDocumento);
        if ("ECUATORIANA".equals(tipoDocumento)) {
            esCedula = true;
            this.adolescenteInfractorCAIEditar.getIdAdolescenteInfractor().setDocumento(null);
        } else if ("EXTRANJERA".equals(tipoDocumento)) {
            esCedula = false;
            this.adolescenteInfractorCAIEditar.getIdAdolescenteInfractor().setCedula(null);
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

    public void guardarEdicionAdolescenteInfractor() {

        if (this.adolescenteInfractorCAIEditar.getIdAdolescenteInfractor() != null) {
            
            Date fechaNacimiento=this.adolescenteInfractorCAIEditar.getIdAdolescenteInfractor().getFechaNacimiento();
            
            if (validacion.verificarFechaNacimiento(fechaNacimiento)) {
            
                if ("ECUATORIANA".equals(tipoDocumento)) {

                    String cedula = this.adolescenteInfractorCAIEditar.getIdAdolescenteInfractor().getCedula();
                    if (validacion.cedulaValida(cedula)) {
                        guardarRegistroAdolescenteInfractorEditar();
                        
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR AL GUARDAR EL ADOLESCENTE INFRACTOR, HA INGRESADO UNA CÉDULA INCORRECTA", "Error"));
                    }

                } else if ("EXTRANJERA".equals(tipoDocumento)) {
                    guardarRegistroAdolescenteInfractorEditar();
                }
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR AL GUARDAR EL ADOLESCENTE INFRACTOR, NO DEBE SER MAYOR DE 26 AÑOS", "Error"));
            }
            
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO DEL ADOLESCENTE INFRACTOR CAI", "Error"));
        }
        
    }

    private void guardarRegistroAdolescenteInfractorEditar() {
        
        AdolescenteInfractorCAI ai_cai = servicioCAI.guardarEdicionAdolescenteInfractorCAI(this.adolescenteInfractorCAIEditar);
               
        if (ai_cai != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adolescente_infractor_cai", ai_cai);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL ADOLESCETE INFRACTOR CAI", "Información"));
            guardado = false;

        } else {
            guardado = true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO DEL ADOLESCETE INFRACTOR CAI", "Error"));
        }
    }
    
    public String redireccionEdicionAdolescenteInfractor() throws InterruptedException {
        
        if (guardado == false) {
            Thread.sleep(1250);
            String rol = permisos.RolUsuario();
            if (rol != null) {
                if (rol.equals(Constantes.ROL_ADMINISTRADOR)) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("indiceActual", "0");
                    return enlaces.PATH_PANEL_EDITAR_CAI_ADMINISTRADOR + "?faces-redirect=true";
                } else {
                    return null;
                }

            } else {
                return null;
            }
        } else {
            return null;
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
