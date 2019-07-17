/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.modelo.InformacionJudicial;
import epn.edu.ec.modelo.MedidaSocioeducativa;
import epn.edu.ec.servicios.InformacionJudicialServicio;
import epn.edu.ec.servicios.MedidaSocioeducativaServicio;
import epn.edu.ec.utilidades.EnlacesPrograma;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author User
 */
@Named(value = "medidaSocioEducativaControlador")
@ViewScoped
public class MedidaSocioEducativaControlador implements Serializable{

    private AdolescenteInfractorUDI adolescenteInfractorUDI;
    
    private MedidaSocioeducativa medidaSocioeducativa;
    private MedidaSocioeducativa medidaSocioeducativa2;
    private MedidaSocioeducativa medidaSocioeducativa3;
    private MedidaSocioeducativa medidaSocioeducativa4;
    private MedidaSocioeducativa medidaSocioeducativa5;
    private List<MedidaSocioeducativa> listaMedidasSocioeducativas; 

    private MedidaSocioeducativaServicio servicio;
    private InformacionJudicialServicio servicioJudicial;
    private boolean guardado;
    
    private boolean ActivarAmonestacionVerbal=false;
    private boolean ActivarImposicionReglasConducta=false;
    private boolean ActivarApoyoSocioFamiliar=false;
    private boolean ActivarServicioComunidad=false;
    private boolean ActivarLibertadAsistida=false;
    
    private boolean amonestacionVerbalGuardado;
    private boolean imposicionReglasConductaGuardado;
    private boolean apoyoSocioFamiliarGuardado;
    private boolean servicioComunidadGuardado;
    private boolean libertadAsistidaGuardado;
    
    private EnlacesPrograma enlaces;
    
    @PostConstruct
    public void init(){
        
        enlaces= new EnlacesPrograma();
        servicio= new MedidaSocioeducativaServicio();
        servicioJudicial= new InformacionJudicialServicio();
        
        medidaSocioeducativa= new MedidaSocioeducativa();
        medidaSocioeducativa.setMedidaSocioeducativa("AMONESTACIÓN VERBAL");
        
        medidaSocioeducativa2= new MedidaSocioeducativa();
        medidaSocioeducativa2.setMedidaSocioeducativa("IMPOSICIÓN DE REGLAS DE CONDUCTA");
        
        medidaSocioeducativa3= new MedidaSocioeducativa();
        medidaSocioeducativa3.setMedidaSocioeducativa("ORIENTACIÓN Y APOYO PSICO SOCIO FAMILIAR");
        
        medidaSocioeducativa4= new MedidaSocioeducativa();
        medidaSocioeducativa4.setMedidaSocioeducativa("SERVICIO A LA COMUNIDAD");
        
        medidaSocioeducativa5= new MedidaSocioeducativa();
        medidaSocioeducativa5.setMedidaSocioeducativa("LIBERTAD ASISTIDA");
        
        listaMedidasSocioeducativas= new ArrayList<>();
        
        guardado=false;
        amonestacionVerbalGuardado=false;
        imposicionReglasConductaGuardado=false;
        apoyoSocioFamiliarGuardado=false;
        servicioComunidadGuardado=false;
        libertadAsistidaGuardado=false;
        
        adolescenteInfractorUDI= new AdolescenteInfractorUDI();
        AdolescenteInfractorUDI adolescenteInfractorUDIAux= (AdolescenteInfractorUDI)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_udi");
        
        if(adolescenteInfractorUDIAux!=null){
            
            adolescenteInfractorUDI=adolescenteInfractorUDIAux;
            
            InformacionJudicial  informacionJudicialAux= servicioJudicial.obtenerInformacionJudicial(adolescenteInfractorUDI.getIdAdolescenteUdi());
        
            if(informacionJudicialAux != null){
                
               //activo o desactivo la visibilidad de los paneles de acuerdo a la selección hecha en el módulo
               // de Información Judicial
               ActivarAmonestacionVerbal=informacionJudicialAux.getAmonestacionVerbal();
               ActivarImposicionReglasConducta=informacionJudicialAux.getImposicionReglasConducta();
               ActivarApoyoSocioFamiliar=informacionJudicialAux.getOrientacionApoyoSocioFamiliar();
               ActivarServicioComunidad=informacionJudicialAux.getServicioComunidad();
               ActivarLibertadAsistida=informacionJudicialAux.getLibertadAsistida();
               
               if(ActivarAmonestacionVerbal==true || ActivarImposicionReglasConducta==true || ActivarApoyoSocioFamiliar==true
                       || ActivarServicioComunidad==true || ActivarLibertadAsistida==true){
                   guardado=true;
               }
               
               List<MedidaSocioeducativa> listaMedidasSocioeducativasAux= servicio.listaMedidaSocioeducativosPorAdolescentesUzdi(adolescenteInfractorUDI);
               
               if(listaMedidasSocioeducativasAux!=null){
                   
                   listaMedidasSocioeducativas=listaMedidasSocioeducativasAux;
                   
                   for(MedidaSocioeducativa m : listaMedidasSocioeducativas){
                       if("AMONESTACIÓN VERBAL".equals(m.getMedidaSocioeducativa())){
                           medidaSocioeducativa=m;
                           amonestacionVerbalGuardado=true;
                       }
                       else if("IMPOSICIÓN DE REGLAS DE CONDUCTA".equals(m.getMedidaSocioeducativa())){
                           medidaSocioeducativa2=m;
                           imposicionReglasConductaGuardado=true;
                       }
                       else if("ORIENTACIÓN Y APOYO PSICO SOCIO FAMILIAR".equals(m.getMedidaSocioeducativa())){
                           medidaSocioeducativa3=m;
                           apoyoSocioFamiliarGuardado=true;
                       }
                       else if("SERVICIO A LA COMUNIDAD".equals(m.getMedidaSocioeducativa())){
                           medidaSocioeducativa4=m;
                           servicioComunidadGuardado=true;
                       }
                       else if("LIBERTAD ASISTIDA".equals(m.getMedidaSocioeducativa())){
                           medidaSocioeducativa5=m;
                           libertadAsistidaGuardado=true;
                       }
                   } 
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

    public MedidaSocioeducativa getMedidaSocioeducativa() {
        return medidaSocioeducativa;
    }

    public void setMedidaSocioeducativa(MedidaSocioeducativa medidaSocioeducativa) {
        this.medidaSocioeducativa = medidaSocioeducativa;
    }

    public MedidaSocioeducativaServicio getServicio() {
        return servicio;
    }

    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }

    public InformacionJudicialServicio getServicioJudicial() {
        return servicioJudicial;
    }

    public void setServicioJudicial(InformacionJudicialServicio servicioJudicial) {
        this.servicioJudicial = servicioJudicial;
    }

    public boolean isActivarAmonestacionVerbal() {
        return ActivarAmonestacionVerbal;
    }

    public void setActivarAmonestacionVerbal(boolean ActivarAmonestacionVerbal) {
        this.ActivarAmonestacionVerbal = ActivarAmonestacionVerbal;
    }

    public boolean isActivarImposicionReglasConducta() {
        return ActivarImposicionReglasConducta;
    }

    public void setActivarImposicionReglasConducta(boolean ActivarImposicionReglasConducta) {
        this.ActivarImposicionReglasConducta = ActivarImposicionReglasConducta;
    }

    public boolean isActivarApoyoSocioFamiliar() {
        return ActivarApoyoSocioFamiliar;
    }

    public void setActivarApoyoSocioFamiliar(boolean ActivarApoyoSocioFamiliar) {
        this.ActivarApoyoSocioFamiliar = ActivarApoyoSocioFamiliar;
    }

    public boolean isActivarServicioComunidad() {
        return ActivarServicioComunidad;
    }

    public void setActivarServicioComunidad(boolean ActivarServicioComunidad) {
        this.ActivarServicioComunidad = ActivarServicioComunidad;
    }

    public boolean isActivarLibertadAsistida() {
        return ActivarLibertadAsistida;
    }

    public void setActivarLibertadAsistida(boolean ActivarLibertadAsistida) {
        this.ActivarLibertadAsistida = ActivarLibertadAsistida;
    }

    public MedidaSocioeducativa getMedidaSocioeducativa2() {
        return medidaSocioeducativa2;
    }

    public void setMedidaSocioeducativa2(MedidaSocioeducativa medidaSocioeducativa2) {
        this.medidaSocioeducativa2 = medidaSocioeducativa2;
    }

    public MedidaSocioeducativa getMedidaSocioeducativa3() {
        return medidaSocioeducativa3;
    }

    public void setMedidaSocioeducativa3(MedidaSocioeducativa medidaSocioeducativa3) {
        this.medidaSocioeducativa3 = medidaSocioeducativa3;
    }

    public MedidaSocioeducativa getMedidaSocioeducativa4() {
        return medidaSocioeducativa4;
    }

    public void setMedidaSocioeducativa4(MedidaSocioeducativa medidaSocioeducativa4) {
        this.medidaSocioeducativa4 = medidaSocioeducativa4;
    }

    public MedidaSocioeducativa getMedidaSocioeducativa5() {
        return medidaSocioeducativa5;
    }

    public void setMedidaSocioeducativa5(MedidaSocioeducativa medidaSocioeducativa5) {
        this.medidaSocioeducativa5 = medidaSocioeducativa5;
    }

    public List<MedidaSocioeducativa> getListaMedidasSocioeducativas() {
        return listaMedidasSocioeducativas;
    }

    public void setListaMedidasSocioeducativas(List<MedidaSocioeducativa> listaMedidasSocioeducativas) {
        this.listaMedidasSocioeducativas = listaMedidasSocioeducativas;
    }

    public boolean isAmonestacionVerbalGuardado() {
        return amonestacionVerbalGuardado;
    }

    public void setAmonestacionVerbalGuardado(boolean amonestacionVerbalGuardado) {
        this.amonestacionVerbalGuardado = amonestacionVerbalGuardado;
    }

    public boolean isImposicionReglasConductaGuardado() {
        return imposicionReglasConductaGuardado;
    }

    public void setImposicionReglasConductaGuardado(boolean imposicionReglasConductaGuardado) {
        this.imposicionReglasConductaGuardado = imposicionReglasConductaGuardado;
    }

    public boolean isApoyoSocioFamiliarGuardado() {
        return apoyoSocioFamiliarGuardado;
    }

    public void setApoyoSocioFamiliarGuardado(boolean apoyoSocioFamiliarGuardado) {
        this.apoyoSocioFamiliarGuardado = apoyoSocioFamiliarGuardado;
    }

    public boolean isServicioComunidadGuardado() {
        return servicioComunidadGuardado;
    }

    public void setServicioComunidadGuardado(boolean servicioComunidadGuardado) {
        this.servicioComunidadGuardado = servicioComunidadGuardado;
    }

    public boolean isLibertadAsistidaGuardado() {
        return libertadAsistidaGuardado;
    }

    public void setLibertadAsistidaGuardado(boolean libertadAsistidaGuardado) {
        this.libertadAsistidaGuardado = libertadAsistidaGuardado;
    }
    
    
    /*********************Métodos para invocar a los diferentes servicios web******************/
    
    public String guardarMedidaSocioeducativa(){
        
        this.medidaSocioeducativa.setIdAdolescenteUdi(adolescenteInfractorUDI);

        MedidaSocioeducativa medidaSocioeducativaAux = servicio.guardarMedidaSocioeducativa(medidaSocioeducativa);
        if(medidaSocioeducativaAux!=null){
            return enlaces.PATH_PANEL_UDI+"?faces-redirect=true";    
        }
        else{
            return null;
        }
    }
    
    public String guardarMedidaSocioeducativa2(){
        
        this.medidaSocioeducativa2.setIdAdolescenteUdi(adolescenteInfractorUDI);

        MedidaSocioeducativa medidaSocioeducativaAux = servicio.guardarMedidaSocioeducativa(medidaSocioeducativa2);
        if(medidaSocioeducativaAux!=null){
            return enlaces.PATH_PANEL_UDI+"?faces-redirect=true"; 
        }
        else{
            return null;
        }
    }
    
    public String guardarMedidaSocioeducativa3(){
        
        this.medidaSocioeducativa3.setIdAdolescenteUdi(adolescenteInfractorUDI);

        MedidaSocioeducativa medidaSocioeducativaAux = servicio.guardarMedidaSocioeducativa(medidaSocioeducativa3);
        if(medidaSocioeducativaAux!=null){
            return enlaces.PATH_PANEL_UDI+"?faces-redirect=true";   
        }
        else{
            return null;
        }
    }
    
    public String guardarMedidaSocioeducativa4(){
        
        this.medidaSocioeducativa4.setIdAdolescenteUdi(adolescenteInfractorUDI);

        MedidaSocioeducativa medidaSocioeducativaAux = servicio.guardarMedidaSocioeducativa(medidaSocioeducativa4);
        if(medidaSocioeducativaAux!=null){
            return enlaces.PATH_PANEL_UDI+"?faces-redirect=true";     
        }
        else{
            return null;
        }
    }
    
    public String guardarMedidaSocioeducativa5(){
        
        this.medidaSocioeducativa5.setIdAdolescenteUdi(adolescenteInfractorUDI);

        MedidaSocioeducativa medidaSocioeducativaAux = servicio.guardarMedidaSocioeducativa(medidaSocioeducativa5);
        if(medidaSocioeducativaAux!=null){
            return enlaces.PATH_PANEL_UDI+"?faces-redirect=true";     
        }
        else{
            return null;
        }
    }

}
