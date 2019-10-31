package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.modelo.EjeSalud;
import epn.edu.ec.servicios.EjeSaludServicio;
import epn.edu.ec.utilidades.Validaciones;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "ejeSaludControladorCAI")
@ViewScoped
public class EjeSaludCAIControlador implements Serializable{

    //mensajes que controlan las validaciones
    private String mensaje = "";
    //Objeto que contiene el codigo de las validaciones
    private Validaciones validacion;
    
    private AdolescenteInfractorCAI adolescenteInfractorCAI;
    private EjeSalud ejeSalud;
    
    private EjeSaludServicio servicio;
    
    private boolean guardado;
    private boolean saludable;
    private boolean consumeSustancias;
    
    private String genero;
    private boolean esMujer;
    
    String tipoD;
    boolean esDiscapacidad;
    
     @PostConstruct
    public void init(){
        
        validacion = new Validaciones();
        servicio= new EjeSaludServicio();
        
        ejeSalud= new EjeSalud();
        guardado=false;
        consumeSustancias=true;
        
        if (isEsDiscapacidad()) {
            tipoD = "SI";
        } else {
            tipoD = "NO";
        }
        
        adolescenteInfractorCAI= new AdolescenteInfractorCAI();
        AdolescenteInfractorCAI adolescenteInfractorCAIAux= (AdolescenteInfractorCAI)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_cai");
        
        if(adolescenteInfractorCAIAux != null){
            
            adolescenteInfractorCAI=adolescenteInfractorCAIAux;
            
            EjeSalud ejeSaludCAIAux= servicio.obtenerEjeSalud(adolescenteInfractorCAI.getIdAdolescenteInfractor().getIdAdolescenteInfractor());
            
            if(ejeSaludCAIAux!=null){
            
                ejeSalud=ejeSaludCAIAux;
                guardado=true;
                
                String saludableAux = ejeSaludCAIAux.getSituacionSalud();
                
                if(saludableAux.equals("SALUDABLE")){
                    saludable=true;                    
                }
                else if(saludableAux.equals("NO SALUDABLE")){
                    saludable=false;
                }
                if (ejeSalud.getConsumeSustancias() == true) {
                    consumeSustancias = true;
                } else {
                    consumeSustancias = false;
                }
                if (ejeSalud.getDiscapacidad().equals("SI")) {
                    
                    tipoD = "SI";
                } 
                else if(ejeSalud.getDiscapacidad().equals("NO")) {
                    tipoD = "NO";
                }
                else{
                    tipoD="EN PROCESO DE CERTIFICACIÓN";
                }
            }
            genero=adolescenteInfractorCAIAux.getIdAdolescenteInfractor().getGenero();
            
            if(genero.equals("MASCULINO")){
                esMujer=false;
            }
            else if(genero.equals("FEMENINO")){
                esMujer=true;
            }
        }
        
    }

    public AdolescenteInfractorCAI getAdolescenteInfractorCAI() {
        return adolescenteInfractorCAI;
    }

    public void setAdolescenteInfractorCAI(AdolescenteInfractorCAI adolescenteInfractorUDI) {
        this.adolescenteInfractorCAI = adolescenteInfractorUDI;
    }

    public EjeSalud getEjeSalud() {
        return ejeSalud;
    }

    public void setEjeSaludUDI(EjeSalud ejeSalud) {
        this.ejeSalud = ejeSalud;
    }

    public EjeSaludServicio getServicio() {
        return servicio;
    }

    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }
    
    public boolean isSaludable() {
        return saludable;
    }

    public void setSaludable(boolean saludable) {
        this.saludable = saludable;
        if(saludable==true){
            ejeSalud.setSituacionSalud("SALUDABLE");
            this.ejeSalud.setDiagnosticoEnfermedad(null);
            this.ejeSalud.setNumeroHistoriaClinica(null);
            this.ejeSalud.setRecibeTratamiento(null);
            this.ejeSalud.setTomaMedicacion(null);
            
        }else if(saludable==false){
            ejeSalud.setSituacionSalud("NO SALUDABLE");
        }
    }

    public boolean isConsumeSustancias() {
        return consumeSustancias;
    }

    public void setConsumeSustancias(boolean consumeSustancias) {
        
        this.consumeSustancias = consumeSustancias;
        if(consumeSustancias==true){
            ejeSalud.setConsumeSustancias(true);
        }else{
            ejeSalud.setConsumeSustancias(false);
        }
        
    }
    
    public String getTipoD() {
        return tipoD;
    }

    public void setTipoD(String tipoD) {
        ejeSalud.setDiscapacidad(tipoD);
        this.tipoD = tipoD;
        if("SI".equals(tipoD)){
            esDiscapacidad=true;
            ejeSalud.setDiscapacidad("SI");
        }else if("NO".equals(tipoD)){
            esDiscapacidad=false;
            ejeSalud.setDiscapacidad("NO");
        }else if("EN PROCESO DE CERTIFICACIÓN".equals(tipoD)){
            esDiscapacidad=false;
            ejeSalud.setDiscapacidad("EN PROCESO DE CERTIFICACIÓN");
        }
    }

    public boolean isEsDiscapacidad() {
        if("SI".equals(tipoD)){
            esDiscapacidad=true;
        }else if("NO".equals(tipoD) || "EN PROCESO DE CERTIFICACIÓN".equals(tipoD)){
            esDiscapacidad=false;
        }
        return esDiscapacidad;
    }

    public void setEsDiscapacidad(boolean esDiscapacidad) {
        this.esDiscapacidad = esDiscapacidad;
    }
    
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isEsMujer() {
        return esMujer;
    }

    public void setEsMujer(boolean esMujer) {
        this.esMujer = esMujer;
    }
    
        /*********************Métodos para invocar a los diferentes servicios web******************/
    
    public void guardarEjeSaludCAI(){
        
        if("NO".equals(tipoD) || "EN PROCESO DE CERTIFICACIÓN".equals(tipoD)){
            this.ejeSalud.setTipoDiscapacidad("NINGUNA");
            this.ejeSalud.setPorcentajeDiscapacidad(0);
        }
        
        if(!consumeSustancias){
            this.ejeSalud.setTipoSustancia(null);
            this.ejeSalud.setRecibeTratamiento(false);
        }
        
        if(!esMujer){
            this.ejeSalud.setTiempoGestacionMes(null);
            this.ejeSalud.setEmbarazo(false);
        }
        else{
            if(ejeSalud.getEmbarazo() == false){
                ejeSalud.setTiempoGestacionMes(null);
            }
        }
        
        this.ejeSalud.setIdAdolescenteInfractor(adolescenteInfractorCAI.getIdAdolescenteInfractor());

        EjeSalud ejeSaludCAIAux = servicio.guardarEjeSalud(ejeSalud);
        if (ejeSaludCAIAux != null) {
            guardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO EJE SALUD", "Información"));
            
        } else {
            guardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO EJE SALUD", "Error"));
        }
    }
    
    public void editarEjeSaludCAI(){
        
        this.ejeSalud.setIdAdolescenteInfractor(adolescenteInfractorCAI.getIdAdolescenteInfractor());

        if("NO".equals(tipoD) || "EN PROCESO DE CERTIFICACIÓN".equals(tipoD)){
            this.ejeSalud.setTipoDiscapacidad("NINGUNA");
            this.ejeSalud.setPorcentajeDiscapacidad(0);
        }
        
        if(!consumeSustancias){
            this.ejeSalud.setTipoSustancia(null);
            this.ejeSalud.setRecibeTratamiento(false);
        }
        
        if(!esMujer){
            this.ejeSalud.setTiempoGestacionMes(null);
            this.ejeSalud.setEmbarazo(false);
        }
        else{
            if(this.ejeSalud.getEmbarazo() == false){
                this.ejeSalud.setTiempoGestacionMes(null);
            }
        }
        
        EjeSalud ejeSaludCAIAux = servicio.guardarEjeSalud(this.ejeSalud);
        if (ejeSaludCAIAux != null) {
            guardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO EJE SALUD", "Información"));
            
        } else {
            guardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO EJE SALUD", "Error"));
        }
    }

    public void limpiarMensaje(AjaxBehaviorEvent evento) {
        String numero = ejeSalud.getNumeroHistoriaClinica();
        if (validacion.verificadorSoloNumeros(numero)) {
            mensaje = "";
        } else {
            mensaje = "Número incorrecto";
        }
    }

    public void validarSoloNumero(AjaxBehaviorEvent evento) {
        String numero = ejeSalud.getNumeroHistoriaClinica();
        if (validacion.verificadorSoloNumeros(numero)) {
            mensaje = "Número correcto";
        } else {
            mensaje = "Número incorrecto";
        }
    }
    
}