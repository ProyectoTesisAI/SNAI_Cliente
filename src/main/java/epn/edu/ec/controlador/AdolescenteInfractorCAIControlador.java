package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.modelo.DatosProvinciaCanton;
import epn.edu.ec.servicios.AdolescenteInfractorCAIServicio;
import epn.edu.ec.servicios.DatosProvinciaCantonServicio;
import epn.edu.ec.utilidades.Validaciones;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    private AdolescenteInfractorCAI adolescenteInfractorCAIEditar;
    private AdolescenteInfractorCAI adolescenteInfractorCAICrear;
    private AdolescenteInfractorCAIServicio servicio;
    private boolean guardado;

    private List<DatosProvinciaCanton> provincias;
    private List<DatosProvinciaCanton> cantones;
    private DatosProvinciaCantonServicio servicioCAIPC;

    //Objeto que contiene el codigo de las validaciones
    private Validaciones validacion;
    
    //Objetos para saber si es cedula o documento
    String tipoDocumento;
    boolean esCedula;

    @PostConstruct
    public void init() {
        servicio = new AdolescenteInfractorCAIServicio();
        guardado = false;
        validacion = new Validaciones();

        provincias = new ArrayList<>();
        servicioCAIPC = new DatosProvinciaCantonServicio();
        
        if(isEsCedula()){
            tipoDocumento="ECUATORIANA";
        }else{
            tipoDocumento="EXTRANJERA";
        }

        adolescenteInfractorCAICrear = new AdolescenteInfractorCAI();

        adolescenteInfractorCAIEditar = new AdolescenteInfractorCAI();
        AdolescenteInfractorCAI adolescenteInfractorCAIAux = (AdolescenteInfractorCAI) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_cai");
        if (adolescenteInfractorCAIAux != null) {
            adolescenteInfractorCAIEditar = adolescenteInfractorCAIAux;
            guardado = true;
        }

        provincias = servicioCAIPC.listarDatosProvinciaCanton();
    }

    public AdolescenteInfractorCAI getAdolescenteInfractorCAICrear() {
        return adolescenteInfractorCAICrear;
    }

    public void setAdolescenteInfractorCAICrear(AdolescenteInfractorCAI adolescenteInfractorCAICrear) {
        this.adolescenteInfractorCAICrear = adolescenteInfractorCAICrear;
    }

    public List<DatosProvinciaCanton> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<DatosProvinciaCanton> provincias) {
        this.provincias = provincias;
    }

    public AdolescenteInfractorCAI getAdolescenteInfractorCAIEditar() {
        return adolescenteInfractorCAIEditar;
    }

    public void setAdolescenteInfractorCAIEditar(AdolescenteInfractorCAI adolescenteInfractorCAIEditar) {
        this.adolescenteInfractorCAIEditar = adolescenteInfractorCAIEditar;
    }

    public AdolescenteInfractorCAIServicio getServicio() {
        return servicio;
    }

    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }

    public List<DatosProvinciaCanton> listarCantonesPorProvincia(String provincia) {
        List<DatosProvinciaCanton> cantonesAux = new ArrayList<>();

        for (DatosProvinciaCanton c : provincias) {
            if (provincia.equals(c.getProvincia())) {
                cantonesAux.add(c);
            }
        }
        return cantonesAux;
    }

    public List<DatosProvinciaCanton> getCantones() {
        String provincia = null;
        cantones = new ArrayList<>();
        if (adolescenteInfractorCAICrear.getProvinciaAdolescente() != null) {
            provincia = adolescenteInfractorCAICrear.getProvinciaAdolescente();
            if (provincia != null) {
                cantones = listarCantonesPorProvincia(provincia);
            }
            return cantones;
        } else if (adolescenteInfractorCAIEditar.getProvinciaAdolescente() != null) {
            provincia = adolescenteInfractorCAIEditar.getProvinciaAdolescente();
            if (provincia != null) {
                cantones = listarCantonesPorProvincia(provincia);
            }
            return cantones;
        } else {
            return null;
        }
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

    //Métodos para invocar a los diferentes servicios web************
    public String guardarAdolescenteInfractor() {
        Boolean verificador1 = false;
        Boolean verificador2 = false;
        verificador1 = this.adolescenteInfractorCAICrear.getVerificadorCedula();
        verificador2 = this.adolescenteInfractorCAICrear.getVerificadorFechaNacimiento();
        if (verificador1 == false || verificador2 == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ha ocurrido un error, no se guardó el Adolescente Infractor", "Error"));
            return null;
        } else if (verificador1 == true || verificador2 == true) {
            AdolescenteInfractorCAI ai_cai = servicio.guardarAdolescenteInfractorCAI(this.adolescenteInfractorCAICrear);
            if (ai_cai != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adolescente_infractor_cai", ai_cai);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado correctamente el Adolescente ", "Aviso"));
                return "/paginas/cai/matriz/panel_crear_cai.com?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ha ocurrido un error, no se guardó el Adolescente Infractor", "Error"));
                return null;
            }
        } else {
            return null;
        }
    }

    public void limpiarMensajeCedula(AjaxBehaviorEvent evento) {
        String cedula = adolescenteInfractorCAICrear.getCedula();
        if (validacion.cedulaValida(cedula)) {
            mensaje = "";
        } else {
            mensaje = "cédula incorrecta";
        }
    }

    public void validarCedula(AjaxBehaviorEvent evento) {

        String cedula = adolescenteInfractorCAICrear.getCedula();
        if (validacion.cedulaValida(cedula)) {
            mensaje = "cédula correcta";
        } else {
            mensaje = "cédula incorrecta";
        }
    }

    public void limpiarMensajeFechaNacimiento(AjaxBehaviorEvent evento) {
        Date fecha = adolescenteInfractorCAICrear.getFechaNacimiento();
        if (validacion.verificarFechaNacimiento(fecha)) {
            mensaje1 = "";
        } else {
            mensaje1 = "Fecha incorrecta";
        }
    }

    public void validarFechaNacimiento(AjaxBehaviorEvent evento) {
        Date fecha = adolescenteInfractorCAICrear.getFechaNacimiento();
        if (validacion.verificarFechaNacimiento(fecha)) {
            mensaje1 = "Fecha correcta";
        } else {
            mensaje1 = "Fecha incorrecta";
        }
    }

    public void limpiarMensajeNumeroContacto(AjaxBehaviorEvent evento) {
        String numero = adolescenteInfractorCAICrear.getNumeroContacto();
        if (validacion.numeroContactoValida(numero)) {
            mensaje2 = "";
        } else {
            mensaje2 = "Número incorrecto";
        }
    }

    public void validarNumeroContacto(AjaxBehaviorEvent evento) {
        String numero = adolescenteInfractorCAICrear.getNumeroContacto();
        if (validacion.numeroContactoValida(numero)) {
            mensaje2 = "Número correcto";
        } else {
            mensaje2 = "Número incorrecto";
        }
    }
}
