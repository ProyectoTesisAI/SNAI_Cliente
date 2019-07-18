/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.modelo.InformacionJudicial;
import epn.edu.ec.servicios.InformacionJudicialServicio;
import epn.edu.ec.utilidades.EnlacesPrograma;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author User
 */
@Named(value = "informacionJudicialControlador")
@ViewScoped
public class InformacionJudicialControlador implements Serializable{

   private AdolescenteInfractorUDI adolescenteInfractorUDI;
   
   private InformacionJudicial informacionJudicial;
   private InformacionJudicialServicio servicio;
   private boolean guardado;
   
   private boolean amonestacionVerbal;
   private boolean imposicionReglasConducta;
   private boolean apoyoSocioFamiliar;
   private boolean servicioComunidad;
   private boolean libertadAsistida;
   private int numeroMedidas=0;
   private EnlacesPrograma enlaces;
   
    @PostConstruct
    public void init(){
        
        enlaces= new EnlacesPrograma();
        servicio= new InformacionJudicialServicio();
        guardado=false;
        
        informacionJudicial= new InformacionJudicial();
               
        adolescenteInfractorUDI= new AdolescenteInfractorUDI();
        AdolescenteInfractorUDI adolescenteInfractorUDIAux = (AdolescenteInfractorUDI)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_udi");
        
        if(adolescenteInfractorUDIAux != null){
            
            adolescenteInfractorUDI=adolescenteInfractorUDIAux;
            
            InformacionJudicial  informacionJudicialAux= servicio.obtenerInformacionJudicial(adolescenteInfractorUDI.getIdAdolescenteInfractor().getIdAdolescenteInfractor());
            if(informacionJudicialAux != null){
                informacionJudicial=informacionJudicialAux;
                guardado=true;
                amonestacionVerbal=informacionJudicial.getAmonestacionVerbal();
                imposicionReglasConducta=informacionJudicial.getImposicionReglasConducta();
                apoyoSocioFamiliar=informacionJudicial.getOrientacionApoyoSocioFamiliar();
                servicioComunidad=informacionJudicial.getServicioComunidad();
                libertadAsistida=informacionJudicial.getLibertadAsistida();
                numeroMedidas=informacionJudicial.getNumeroMedidas();
            }
            else{

                if(amonestacionVerbal){
                    numeroMedidas++;
                }
                if(imposicionReglasConducta){
                    numeroMedidas++;
                }
                if(apoyoSocioFamiliar){
                    numeroMedidas++;
                }
                if(servicioComunidad){
                    numeroMedidas++;
                }
                if(libertadAsistida){
                    numeroMedidas++;
                }

            }
        }
        
        
    }

    public AdolescenteInfractorUDI getAdolescenteInfractorUDI() {
        return adolescenteInfractorUDI;
    }

    public void setAdolescenteInfractorUDI(AdolescenteInfractorUDI adolescenteInfractorUDI) {
        this.adolescenteInfractorUDI = adolescenteInfractorUDI;
    }

    public InformacionJudicial getInformacionJudicial() {
        return informacionJudicial;
    }

    public void setInformacionJudicial(InformacionJudicial informacionJudicial) {
        this.informacionJudicial = informacionJudicial;
    }

    public InformacionJudicialServicio getServicio() {
        return servicio;
    }

    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }

    public boolean isAmonestacionVerbal() {
        
        return amonestacionVerbal;
    }

    public void setAmonestacionVerbal(boolean amonestacionVerbal) {
        this.amonestacionVerbal = amonestacionVerbal;
        if(amonestacionVerbal){
            numeroMedidas++;
            this.informacionJudicial.setAmonestacionVerbal(true);
            
        }
        else{
            numeroMedidas--;
            this.informacionJudicial.setAmonestacionVerbal(false);
        }
    }

    public boolean isImposicionReglasConducta() {
        return imposicionReglasConducta;
    }

    public void setImposicionReglasConducta(boolean imposicionReglasConducta) {
        this.imposicionReglasConducta = imposicionReglasConducta;
        if(imposicionReglasConducta){
            numeroMedidas++;
            this.informacionJudicial.setImposicionReglasConducta(true);
        }
        else{
            numeroMedidas--;
            this.informacionJudicial.setImposicionReglasConducta(false);
        }
    }

    public boolean isApoyoSocioFamiliar() {
        return apoyoSocioFamiliar;
    }

    public void setApoyoSocioFamiliar(boolean apoyoSocioFamiliar) {
        this.apoyoSocioFamiliar = apoyoSocioFamiliar;
        if(apoyoSocioFamiliar){
            numeroMedidas++;
            this.informacionJudicial.setOrientacionApoyoSocioFamiliar(true);
        }
        else{
            numeroMedidas--;
            this.informacionJudicial.setOrientacionApoyoSocioFamiliar(false);
        }
    }

    public boolean isServicioComunidad() {
        return servicioComunidad;
    }

    public void setServicioComunidad(boolean servicioComunidad) {
        this.servicioComunidad = servicioComunidad;
        if(servicioComunidad){
            numeroMedidas++;
            this.informacionJudicial.setServicioComunidad(true);
        }
        else{
            numeroMedidas--;
            this.informacionJudicial.setServicioComunidad(false);
        }
    }

    public boolean isLibertadAsistida() {
        return libertadAsistida;
    }

    public void setLibertadAsistida(boolean libertadAsistida) {
        this.libertadAsistida = libertadAsistida;
        if(libertadAsistida){
            numeroMedidas++;
            this.informacionJudicial.setLibertadAsistida(true);
        }
        else{
            numeroMedidas--;
            this.informacionJudicial.setLibertadAsistida(false);
        }
    }

    public int getNumeroMedidas() {
        return numeroMedidas;
    }

    public void setNumeroMedidas(int numeroMedidas) {
        this.numeroMedidas = numeroMedidas;
    }

    /*********************Métodos para invocar a los diferentes servicios web******************/
    
    public String guardarInformacionJudicial(){
        
        this.informacionJudicial.setNumeroMedidas(numeroMedidas);
        this.informacionJudicial.setIdAdolescenteInfractorUDI(adolescenteInfractorUDI); 

        InformacionJudicial informacionJudicialAux = servicio.guardarInformacionJudicial(informacionJudicial);
        if(informacionJudicialAux!=null){
            return enlaces.PATH_PANEL_UDI+"?faces-redirect=true";        
        }
        else{
            return null;
        }
    }
}
