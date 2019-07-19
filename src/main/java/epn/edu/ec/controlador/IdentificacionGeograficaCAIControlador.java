package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.modelo.DatosProvinciaCanton;
import epn.edu.ec.modelo.IdentificacionGeografica;
import epn.edu.ec.servicios.DatosProvinciaCantonServicio;
import epn.edu.ec.servicios.IdentificacionGeograficaServicio;
import epn.edu.ec.utilidades.EnlacesPrograma;
import epn.edu.ec.utilidades.Validaciones;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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

    private AdolescenteInfractorUDI adolescenteInfractorUDI;
    private IdentificacionGeografica identificacionGeografica;
    private IdentificacionGeograficaServicio servicio;
    private boolean guardado;

    private List<DatosProvinciaCanton> provincias;
    private List<DatosProvinciaCanton> cantones;
    private List<DatosProvinciaCanton> cantonesNacimiento;
    private DatosProvinciaCantonServicio servicioCAIPC;
    private EnlacesPrograma enlaces;

    private String nacionalidad;
    private boolean esEcuatoriana;

    @PostConstruct
    public void init() {

        validacion = new Validaciones();
        enlaces = new EnlacesPrograma();
        servicio = new IdentificacionGeograficaServicio();

        identificacionGeografica = new IdentificacionGeografica();
        guardado = false;

        provincias = new ArrayList<>();
        servicioCAIPC = new DatosProvinciaCantonServicio();

        adolescenteInfractorUDI = new AdolescenteInfractorUDI();
        AdolescenteInfractorUDI adolescenteInfractorUDIAux = (AdolescenteInfractorUDI) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_udi");

        if (adolescenteInfractorUDIAux != null) {
            adolescenteInfractorUDI = adolescenteInfractorUDIAux;
            IdentificacionGeografica identificacionGeograficaAux = servicio.obtenerIdentificacionGeografica(adolescenteInfractorUDI.getIdAdolescenteInfractor().getIdAdolescenteInfractor());
            if (identificacionGeograficaAux != null) {
                identificacionGeografica = identificacionGeograficaAux;
                guardado = true;
            }
            nacionalidad = adolescenteInfractorUDIAux.getIdAdolescenteInfractor().getNacionalidad();
            System.out.println("Nacionalidad init: " + nacionalidad);
            if (nacionalidad.equals("ECUATORIANA")) {
                esEcuatoriana = true;
            } else if (nacionalidad.equals("EXTRANJERO")) {
                esEcuatoriana = false;
            }
        }

        provincias = servicioCAIPC.listarDatosProvinciaCanton();

    }

    public AdolescenteInfractorUDI getAdolescenteInfractorUDI() {
        return adolescenteInfractorUDI;
    }

    public void setAdolescenteInfractorUDI(AdolescenteInfractorUDI adolescenteInfractorUDI) {
        this.adolescenteInfractorUDI = adolescenteInfractorUDI;
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
        System.out.println("Nacionalidad :" + nacionalidad);
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
        //System.out.println("Provincia del adoles: "+provincia);
        if (provincia != null) {
            cantones = listarCantonesPorProvincia(provincia);
        } else {
            System.out.println("No hay provincia seleccionada");
        }
        return cantones;
    }

    public List<DatosProvinciaCanton> getCantonesNacimiento() {
        cantonesNacimiento=new ArrayList<>();
        String provinciaNacimiento = identificacionGeografica.getEstadoOProvinciaNacimiento();
        if(provinciaNacimiento!=null){
            cantonesNacimiento = listarCantonesPorProvincia(provinciaNacimiento);
        }else{
            System.out.println("No hay provincia de nacimiento ecuatoriana escogida");
        }
        return cantonesNacimiento;
    }

    /**
     * *******************Métodos para invocar a los diferentes servicios
     * web*****************
     */
    public String guardarIdentificacionGeografica() {
        if(nacionalidad.equals("ECUATORIANA")){
            this.identificacionGeografica.setPaisNacimiento("ECUADOR");
        }
        this.identificacionGeografica.setIdAdolescenteInfractorUDI(adolescenteInfractorUDI.getIdAdolescenteInfractor());

        IdentificacionGeografica identificacionGeograficaAux = servicio.guardarIdentificacionGeografica(identificacionGeografica);
        if (identificacionGeograficaAux != null) {
            return enlaces.PATH_PANEL_UDI + "?faces-redirect=true";
        } else {
            return null;
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
