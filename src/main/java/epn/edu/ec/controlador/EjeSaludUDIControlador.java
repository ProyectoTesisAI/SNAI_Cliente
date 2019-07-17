/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.modelo.EjeSaludUDI;
import epn.edu.ec.servicios.EjeSaludUDIServicio;
import epn.edu.ec.utilidades.EnlacesPrograma;
import epn.edu.ec.utilidades.Validaciones;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "ejeSaludUDIControlador")
@ViewScoped
public class EjeSaludUDIControlador implements Serializable{

    //mensajes que controlan las validaciones
    private String mensaje = "";
    //Objeto que contiene el codigo de las validaciones
    private Validaciones validacion;
    
    private AdolescenteInfractorUDI adolescenteInfractorUDI;
    private EjeSaludUDI ejeSaludUDI;
    private EjeSaludUDIServicio servicio;
    private boolean guardado;
    private boolean saludable;
    private boolean consumeSustancias;
    private EnlacesPrograma enlaces;
    
    private String genero;
    private boolean esMujer;
    
     @PostConstruct
    public void init(){
        
        validacion = new Validaciones();
        enlaces= new EnlacesPrograma();
        servicio= new EjeSaludUDIServicio();
        
        ejeSaludUDI= new EjeSaludUDI();
        guardado=false;
        consumeSustancias=true;
        
        if (isSaludable()) {
            saludable = true;
        } else {
            saludable = false;            
        }
        
        adolescenteInfractorUDI= new AdolescenteInfractorUDI();
        AdolescenteInfractorUDI adolescenteInfractorUDIAux= (AdolescenteInfractorUDI)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_udi");
        
        if(adolescenteInfractorUDIAux != null){
            
            adolescenteInfractorUDI=adolescenteInfractorUDIAux;
            EjeSaludUDI ejeSaludUDIAux= servicio.obtenerEjeSaludUDI(adolescenteInfractorUDI.getIdAdolescenteUdi());
            if(ejeSaludUDIAux!=null){
                ejeSaludUDI=ejeSaludUDIAux;
                guardado=true;
                String saludableAux = ejeSaludUDIAux.getSituacionSalud();
                System.out.println("salud: "+saludableAux);
                if(saludableAux.equals("SALUDABLE")){
                    System.out.println("entro a saludable");
                    saludable=true;                    
                }else if(saludableAux.equals("NO SALUDABLE")){
                    System.out.println("no entro a saludable");
                    saludable=false;
                    
                    if(ejeSaludUDI.getConsumeSustancias()==true){
                        consumeSustancias=true;
                    }
                    else{
                        consumeSustancias=false;
                    }
                }
            }
            genero=adolescenteInfractorUDIAux.getGenero();
            if(genero.equals("MASCULINO")){
                esMujer=false;
            }else if(genero.equals("FEMENINO")){
                esMujer=true;
            }
        }
        
    }

    public AdolescenteInfractorUDI getAdolescenteInfractorUDI() {
        return adolescenteInfractorUDI;
    }

    public void setAdolescenteInfractorUDI(AdolescenteInfractorUDI adolescenteInfractorUDI) {
        this.adolescenteInfractorUDI = adolescenteInfractorUDI;
    }

    public EjeSaludUDI getEjeSaludUDI() {
        return ejeSaludUDI;
    }

    public void setEjeSaludUDI(EjeSaludUDI ejeSaludUDI) {
        this.ejeSaludUDI = ejeSaludUDI;
    }

    public EjeSaludUDIServicio getServicio() {
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
            ejeSaludUDI.setSituacionSalud("SALUDABLE");
        }else if(saludable==false){
            ejeSaludUDI.setSituacionSalud("NO SALUDABLE");
        }
    }

    public boolean isConsumeSustancias() {
        return consumeSustancias;
    }

    public void setConsumeSustancias(boolean consumeSustancias) {
        
        this.consumeSustancias = consumeSustancias;
        if(consumeSustancias==true){
            ejeSaludUDI.setConsumeSustancias(true);
        }else{
            ejeSaludUDI.setConsumeSustancias(false);
        }
        
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
    
    public String guardarEjeSaludUDI(){
        
        this.ejeSaludUDI.setIdEjeSalud(adolescenteInfractorUDI);

        EjeSaludUDI ejeSaludUDIAux = servicio.guardarEjeSaludUDI(ejeSaludUDI);
        if(ejeSaludUDIAux!=null){
            return enlaces.PATH_PANEL_UDI+"?faces-redirect=true";       
        }
        else{
            return null;
        }
    }

    public void limpiarMensaje(AjaxBehaviorEvent evento) {
        String numero = ejeSaludUDI.getNumeroHistoriaClinica();
        if (validacion.verificadorSoloNumeros(numero)) {
            mensaje = "";
        } else {
            mensaje = "Número incorrecto";
        }
    }

    public void validarSoloNumero(AjaxBehaviorEvent evento) {
        String numero = ejeSaludUDI.getNumeroHistoriaClinica();
        if (validacion.verificadorSoloNumeros(numero)) {
            mensaje = "Número correcto";
        } else {
            mensaje = "Número incorrecto";
        }
    }
    
}