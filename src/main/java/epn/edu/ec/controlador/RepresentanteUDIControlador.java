package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractor;
import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.modelo.Representante;
import epn.edu.ec.servicios.RepresentanteServicio;
import epn.edu.ec.utilidades.EnlacesPrograma;
import epn.edu.ec.utilidades.Validaciones;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "representanteControladorUDI")
@ViewScoped
public class RepresentanteUDIControlador implements Serializable{

    //mensajes que controlan las validaciones
    private String mensaje = "";
    //Objeto que contiene el codigo de las validaciones
    private Validaciones validacion;
    
    private AdolescenteInfractor adolescenteInfractorCrear;
    private AdolescenteInfractorUDI adolescenteInfractorUDI;
    private Representante representante;
    private RepresentanteServicio servicio;
    private boolean guardado;
    private EnlacesPrograma enlaces;
    
    //Objetos para saber si es cedula o documento
    String tipoDocumento;
    boolean esCedula;
    
    @PostConstruct
    public void init(){
        
        validacion = new Validaciones();
        enlaces= new EnlacesPrograma();
        servicio= new RepresentanteServicio();
        
        representante= new Representante();
        guardado=false;
        
        if(isEsCedula()){
            tipoDocumento="ECUATORIANA";
        }else{
            tipoDocumento="EXTRANJERA";
        }
        
        adolescenteInfractorCrear=new AdolescenteInfractor();
        adolescenteInfractorUDI= new AdolescenteInfractorUDI();
        AdolescenteInfractorUDI adolescenteInfractorUDIAux= (AdolescenteInfractorUDI)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_udi");
        
        if(adolescenteInfractorUDIAux != null){
            
            adolescenteInfractorUDI=adolescenteInfractorUDIAux;
            Representante representanteAux= servicio.obtenerRepresentante(adolescenteInfractorUDI.getIdAdolescenteInfractor().getIdAdolescenteInfractor());
            if(representanteAux!=null){
                representante=representanteAux;
                guardado=true;
                if(representanteAux.getCedula()!=null && representanteAux.getDocumento()==null){
                    tipoDocumento="ECUATORIANA";
                }else if(representanteAux.getCedula()==null && representanteAux.getDocumento()!=null){
                    tipoDocumento="EXTRANJERA";
                }
            }else{
                representante=new Representante();
            }
        }
        
    }

    public AdolescenteInfractorUDI getAdolescenteInfractorUDI() {
        return adolescenteInfractorUDI;
    }

    public void setAdolescenteInfractorUDI(AdolescenteInfractorUDI adolescenteInfractorUDI) {
        this.adolescenteInfractorUDI = adolescenteInfractorUDI;
    }

    public Representante getRepresentante() {
        return representante;
    }

    public void setRepresentante(Representante representante) {
        this.representante = representante;
    }

    public RepresentanteServicio getServicio() {
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
    
    
    /*********************Métodos para invocar a los diferentes servicios web******************/
    
    public String guardarRepresentante(){
        
        this.representante.setNacionalidad(tipoDocumento);
        this.representante.setIdAdolescenteInfracto(adolescenteInfractorUDI.getIdAdolescenteInfractor());

        Representante representanteAux = servicio.guardarRepresentante(representante);
        if(representanteAux!=null){
            return enlaces.PATH_PANEL_UDI+"?faces-redirect=true";    
        }
        else{
            return null;
        }
    }

    public void limpiarMensajeCedula(AjaxBehaviorEvent evento) {
        String cedula = representante.getCedula();
        if (validacion.cedulaValida(cedula)) {
            mensaje = "";
        } else {
            mensaje = "cédula incorrecta";
        }
    }

    public void validarCedula(AjaxBehaviorEvent evento) {

        String cedula = representante.getCedula();
        if (validacion.cedulaValida(cedula)) {
            mensaje = "cédula correcta";
        } else {
            mensaje = "cédula incorrecta";
        }
    }
}