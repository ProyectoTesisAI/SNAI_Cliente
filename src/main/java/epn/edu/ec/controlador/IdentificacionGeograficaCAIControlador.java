package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.modelo.DatosPais;
import epn.edu.ec.modelo.DatosProvinciaCanton;
import epn.edu.ec.modelo.IdentificacionGeografica;
import epn.edu.ec.servicios.DatosPaisServicio;
import epn.edu.ec.servicios.DatosProvinciaCantonServicio;
import epn.edu.ec.servicios.IdentificacionGeograficaServicio;
import epn.edu.ec.utilidades.Validaciones;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "identificacionGeograficaControladorCAI")
@ViewScoped
public class IdentificacionGeograficaCAIControlador implements Serializable {

    //mensajes que controlan las validaciones
    private String mensaje = "";
    //Objeto que contiene el codigo de las validaciones
    private Validaciones validacion;

    private AdolescenteInfractorCAI adolescenteInfractorCAI;
    private IdentificacionGeografica identificacionGeografica;
    private IdentificacionGeograficaServicio servicio;
    private boolean guardado;

    private List<DatosProvinciaCanton> provincias;
    private List<DatosProvinciaCanton> cantones;
    private List<DatosProvinciaCanton> cantonesNacimiento;
    private DatosProvinciaCantonServicio servicioCAIPC;
    
    private List<DatosPais> paises;
    private DatosPaisServicio servicioP;

    private String nacionalidad;
    private boolean esEcuatoriana;

    @PostConstruct
    public void init() {

        validacion = new Validaciones();
        servicio = new IdentificacionGeograficaServicio();

        identificacionGeografica = new IdentificacionGeografica();
        guardado = false;

        provincias = new ArrayList<>();
        servicioCAIPC = new DatosProvinciaCantonServicio();
        
        paises = new ArrayList<>();
        servicioP = new DatosPaisServicio();

        adolescenteInfractorCAI = new AdolescenteInfractorCAI();
        AdolescenteInfractorCAI adolescenteInfractorCAIAux = (AdolescenteInfractorCAI) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_cai");

        if (adolescenteInfractorCAIAux != null) {

            adolescenteInfractorCAI = adolescenteInfractorCAIAux;
            IdentificacionGeografica identificacionGeograficaAux = servicio.obtenerIdentificacionGeografica(adolescenteInfractorCAI.getIdAdolescenteInfractor().getIdAdolescenteInfractor());

            if (identificacionGeograficaAux != null) {
                identificacionGeografica = identificacionGeograficaAux;
                guardado = true;
            }
            nacionalidad = adolescenteInfractorCAIAux.getIdAdolescenteInfractor().getNacionalidad();
            
            if (nacionalidad.equals("ECUATORIANA")) {
                esEcuatoriana = true;
            } 
            else if (nacionalidad.equals("EXTRANJERO")) {
                esEcuatoriana = false;
            }
        }
        provincias = servicioCAIPC.listarDatosProvinciaCanton();
        paises = servicioP.listarDatosPais();
    }

    public AdolescenteInfractorCAI getAdolescenteInfractorCAI() {
        return adolescenteInfractorCAI;
    }

    public void setAdolescenteInfractorCAI(AdolescenteInfractorCAI adolescenteInfractorUDI) {
        this.adolescenteInfractorCAI = adolescenteInfractorUDI;
    }

    public IdentificacionGeografica getIdentificacionGeografica() {
        return identificacionGeografica;
    }

    public void setIdentificacionGeografica(IdentificacionGeografica identificacionGeografica) {
        this.identificacionGeografica = identificacionGeografica;
    }

    public IdentificacionGeograficaServicio getServicio() {
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

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public boolean isEsEcuatoriana() {
        return esEcuatoriana;
    }

    public void setEsEcuatoriana(boolean esEcuatoriana) {
        this.esEcuatoriana = esEcuatoriana;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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
        cantones = new ArrayList<>();
        String provincia = identificacionGeografica.getProvinciaResidencia();
        if (provincia != null) {
            cantones = listarCantonesPorProvincia(provincia);
        } else {
        }
        return cantones;
    }

    public List<DatosProvinciaCanton> getCantonesNacimiento() {
        cantonesNacimiento=new ArrayList<>();
        String provinciaNacimiento = identificacionGeografica.getEstadoOProvinciaNacimiento();
        if(provinciaNacimiento!=null){
            cantonesNacimiento = listarCantonesPorProvincia(provinciaNacimiento);
        }else{
        }
        return cantonesNacimiento;
    }

    public List<DatosPais> getPaises() {
        return paises;
    }

    /**
     * *******************Métodos para invocar a los diferentes servicios
     * web*****************
     */
    public void guardarIdentificacionGeografica() {
        if(nacionalidad.equals("ECUATORIANA")){
            this.identificacionGeografica.setPaisNacimiento("ECUADOR");
        }
        this.identificacionGeografica.setIdAdolescenteInfractor(adolescenteInfractorCAI.getIdAdolescenteInfractor());

        IdentificacionGeografica identificacionGeograficaAux = servicio.guardarIdentificacionGeografica(identificacionGeografica);
        if (identificacionGeograficaAux != null) {
            guardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO INFORMACIÓN GEOGRÁFICA", "Información"));
            
        } else {
            guardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO INFORMACIÓN GEOGRÁFICA", "Error"));
        }
    }
    
    public void editarIdentificacionGeografica() {
        if(nacionalidad.equals("ECUATORIANA")){
            this.identificacionGeografica.setPaisNacimiento("ECUADOR");
        }
        this.identificacionGeografica.setIdAdolescenteInfractor(adolescenteInfractorCAI.getIdAdolescenteInfractor());

        IdentificacionGeografica identificacionGeograficaAux = servicio.guardarIdentificacionGeografica(identificacionGeografica);
        if (identificacionGeograficaAux != null) {
            guardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO INFORMACIÓN GEOGRÁFICA", "Información"));
            
        } else {
            guardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO INFORMACIÓN GEOGRÁFICA", "Error"));
        }
    }

    public void limpiarMensajeNumeroContacto(AjaxBehaviorEvent evento) {
        String numero = identificacionGeografica.getTelefono();
        if (validacion.numeroContactoValida(numero)) {
            mensaje = "";
        } else {
            mensaje = "Número incorrecto";
        }
    }

    public void validarNumeroContacto(AjaxBehaviorEvent evento) {
        String numero = identificacionGeografica.getTelefono();
        if (validacion.numeroContactoValida(numero)) {
            mensaje = "Número correcto";
        } else {
            mensaje = "Número incorrecto";
        }
    }
}
