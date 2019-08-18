package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.modelo.InformacionJudicial;
import epn.edu.ec.modelo.MedidaSocioeducativa;
import epn.edu.ec.servicios.InformacionJudicialServicio;
import epn.edu.ec.servicios.MedidaSocioeducativaServicio;
import epn.edu.ec.utilidades.EnlacesPrograma;
import epn.edu.ec.utilidades.PermisosUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

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
    private PermisosUsuario permisosUsuario;
    
    @PostConstruct
    public void init(){
        
        permisosUsuario= new PermisosUsuario();
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
            
            InformacionJudicial  informacionJudicialAux= servicioJudicial.obtenerInformacionJudicial(adolescenteInfractorUDI.getIdAdolescenteInfractor().getIdAdolescenteInfractor());
        
            if(informacionJudicialAux != null){
                
               //activo o desactivo la visibilidad de los paneles de acuerdo a la selección hecha en el módulo
               // de Información Judicial
               ActivarAmonestacionVerbal=informacionJudicialAux.getAmonestacionVerbal();
               ActivarImposicionReglasConducta=informacionJudicialAux.getImposicionReglasConducta();
               ActivarApoyoSocioFamiliar=informacionJudicialAux.getOrientacionApoyoSocioFamiliar();
               ActivarServicioComunidad=informacionJudicialAux.getServicioComunidad();
               ActivarLibertadAsistida=informacionJudicialAux.getLibertadAsistida();
               medidaSocioeducativa.setFechaInicioMedida(informacionJudicialAux.getFechaInicioCumplimientoMedida());
               medidaSocioeducativa2.setFechaInicioMedida(informacionJudicialAux.getFechaInicioCumplimientoMedida());
               medidaSocioeducativa3.setFechaInicioMedida(informacionJudicialAux.getFechaInicioCumplimientoMedida());
               medidaSocioeducativa4.setFechaInicioMedida(informacionJudicialAux.getFechaInicioCumplimientoMedida());
               medidaSocioeducativa5.setFechaInicioMedida(informacionJudicialAux.getFechaInicioCumplimientoMedida());
               if(ActivarAmonestacionVerbal==true || ActivarImposicionReglasConducta==true || ActivarApoyoSocioFamiliar==true
                       || ActivarServicioComunidad==true || ActivarLibertadAsistida==true){
                   guardado=true;
               }
               
               List<MedidaSocioeducativa> listaMedidasSocioeducativasAux= servicio.listaMedidasSocioeducativasPorAdolescente(adolescenteInfractorUDI.getIdAdolescenteInfractor().getIdAdolescenteInfractor());
               
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
    
    public void guardarMedidaSocioeducativa1(){
        /*AMONESTACION VERBAL
        DONDE NO HAY MESES, DIAS, HORAS
        */
        this.medidaSocioeducativa.setTiempoDia(0);
        this.medidaSocioeducativa.setTiempoHoras(0);
        this.medidaSocioeducativa.setTiempoMeses(0);
        this.medidaSocioeducativa.setIdAdolescenteInfractorUDI(adolescenteInfractorUDI);

        MedidaSocioeducativa medidaSocioeducativaAux = servicio.guardarMedidaSocioeducativa(medidaSocioeducativa);
        if (medidaSocioeducativaAux!= null) {
            amonestacionVerbalGuardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO AMONESTACIÓN VERBAL", "Información"));
            
        } else {
            amonestacionVerbalGuardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO AMONESTACIÓN VERBAL", "Error"));
        }
    }
    
    public void guardarEdicionMedidaSocioeducativa1(){
        /*AMONESTACION VERBAL
        DONDE NO HAY MESES, DIAS, HORAS
        */
        this.medidaSocioeducativa.setTiempoDia(0);
        this.medidaSocioeducativa.setTiempoHoras(0);
        this.medidaSocioeducativa.setTiempoMeses(0);
        this.medidaSocioeducativa.setIdAdolescenteInfractorUDI(adolescenteInfractorUDI);

        MedidaSocioeducativa medidaSocioeducativaAux = servicio.guardarMedidaSocioeducativa(medidaSocioeducativa);
        if (medidaSocioeducativaAux!= null) {
            amonestacionVerbalGuardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO AMONESTACIÓN VERBAL", "Información"));
            
        } else {
            amonestacionVerbalGuardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO AMONESTACIÓN VERBAL", "Error"));
        }
    }
    
    public void guardarMedidaSocioeducativa2(){
        /*IMPOSICION REGLAS CONDUCTA
        DONDE NO HAY HORAS
        */
        this.medidaSocioeducativa2.setTiempoHoras(0);
        this.medidaSocioeducativa2.setIdAdolescenteInfractorUDI(adolescenteInfractorUDI);

        MedidaSocioeducativa medidaSocioeducativaAux = servicio.guardarMedidaSocioeducativa(medidaSocioeducativa2);
        if (medidaSocioeducativaAux!= null) {
            imposicionReglasConductaGuardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO IMPOSICIÓN DE REGLAS DE CONDUCTA", "Información"));
            
        } else {
            imposicionReglasConductaGuardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO IMPOSICIÓN DE REGLAS DE CONDUCTA", "Error"));
        }
    }
    
    public void guardarEdicionMedidaSocioeducativa2(){
        /*IMPOSICION REGLAS CONDUCTA
        DONDE NO HAY HORAS
        */
        this.medidaSocioeducativa2.setTiempoHoras(0);
        this.medidaSocioeducativa2.setIdAdolescenteInfractorUDI(adolescenteInfractorUDI);

        MedidaSocioeducativa medidaSocioeducativaAux = servicio.guardarMedidaSocioeducativa(medidaSocioeducativa2);
        if (medidaSocioeducativaAux!= null) {
            imposicionReglasConductaGuardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO IMPOSICIÓN DE REGLAS DE CONDUCTA", "Información"));
            
        } else {
            imposicionReglasConductaGuardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO IMPOSICIÓN DE REGLAS DE CONDUCTA", "Error"));
        }
    }
    
    public void guardarMedidaSocioeducativa3(){
        /*APOYO PSICO SOCIO FAMILAR
        DONDE NO HAY HORAS
        */
        this.medidaSocioeducativa3.setTiempoHoras(0);
        this.medidaSocioeducativa3.setIdAdolescenteInfractorUDI(adolescenteInfractorUDI);

        MedidaSocioeducativa medidaSocioeducativaAux = servicio.guardarMedidaSocioeducativa(medidaSocioeducativa3);
        if (medidaSocioeducativaAux!= null) {
            apoyoSocioFamiliarGuardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO APOYO PSICO-SOCIO FAMILIAR", "Información"));
            
        } else {
            apoyoSocioFamiliarGuardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO APOYO PSICO-SOCIO FAMILIAR", "Error"));
        }
    }
    
    public void guardarEdicionMedidaSocioeducativa3(){
        /*APOYO PSICO SOCIO FAMILAR
        DONDE NO HAY HORAS
        */
        this.medidaSocioeducativa3.setTiempoHoras(0);
        this.medidaSocioeducativa3.setIdAdolescenteInfractorUDI(adolescenteInfractorUDI);

        MedidaSocioeducativa medidaSocioeducativaAux = servicio.guardarMedidaSocioeducativa(medidaSocioeducativa3);
        if (medidaSocioeducativaAux!= null) {
            apoyoSocioFamiliarGuardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO APOYO PSICO-SOCIO FAMILIAR", "Información"));
            
        } else {
            apoyoSocioFamiliarGuardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO APOYO PSICO-SOCIO FAMILIAR", "Error"));
        }
    }
    
    public void guardarMedidaSocioeducativa4(){
        /*SERVICIO COMUNIDAD
        DONDE NO HAY MESES, DIAS
        */
        this.medidaSocioeducativa4.setTiempoMeses(0);
        this.medidaSocioeducativa4.setIdAdolescenteInfractorUDI(adolescenteInfractorUDI);

        MedidaSocioeducativa medidaSocioeducativaAux = servicio.guardarMedidaSocioeducativa(medidaSocioeducativa4);
        if (medidaSocioeducativaAux!= null) {
            servicioComunidadGuardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO SERVICIO A LA COMUNIDAD", "Información"));
            
        } else {
            servicioComunidadGuardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO SERVICIO A LA COMUNIDAD", "Error"));
        }
    }
    
    public void guardarEdicionMedidaSocioeducativa4(){
        /*SERVICIO COMUNIDAD
        DONDE NO HAY MESES, DIAS
        */
        this.medidaSocioeducativa4.setTiempoMeses(0);
        this.medidaSocioeducativa4.setIdAdolescenteInfractorUDI(adolescenteInfractorUDI);

        MedidaSocioeducativa medidaSocioeducativaAux = servicio.guardarMedidaSocioeducativa(medidaSocioeducativa4);
        if (medidaSocioeducativaAux!= null) {
            servicioComunidadGuardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO SERVICIO A LA COMUNIDAD", "Información"));
            
        } else {
            servicioComunidadGuardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO SERVICIO A LA COMUNIDAD", "Error"));
        }
    }
    
    public void guardarMedidaSocioeducativa5(){
        /*LIBERTAD ASISTIDA
        DONDE NO HAY HORAS
        */
        this.medidaSocioeducativa5.setTiempoHoras(0);
        this.medidaSocioeducativa5.setIdAdolescenteInfractorUDI(adolescenteInfractorUDI);

        MedidaSocioeducativa medidaSocioeducativaAux = servicio.guardarMedidaSocioeducativa(medidaSocioeducativa5);
        if (medidaSocioeducativaAux!= null) {
            libertadAsistidaGuardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO LIBERTAD ASISTIDA", "Información"));
            
        } else {
            libertadAsistidaGuardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO LIBERTAD ASISTIDA", "Error"));
        }
    }
    
    public void guardarEdicionMedidaSocioeducativa5(){
        /*LIBERTAD ASISTIDA
        DONDE NO HAY HORAS
        */
        this.medidaSocioeducativa5.setTiempoHoras(0);
        this.medidaSocioeducativa5.setIdAdolescenteInfractorUDI(adolescenteInfractorUDI);

        MedidaSocioeducativa medidaSocioeducativaAux = servicio.guardarMedidaSocioeducativa(medidaSocioeducativa5);
        if (medidaSocioeducativaAux!= null) {
            libertadAsistidaGuardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO LIBERTAD ASISTIDA", "Información"));
            
        } else {
            libertadAsistidaGuardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO LIBERTAD ASISTIDA", "Error"));
        }
    }

}
