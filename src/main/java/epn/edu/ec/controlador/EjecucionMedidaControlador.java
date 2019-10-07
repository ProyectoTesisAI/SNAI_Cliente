package epn.edu.ec.controlador;

import epn.edu.ec.modelo.CAI;
import epn.edu.ec.modelo.DatosProvinciaCanton;
import epn.edu.ec.modelo.DetalleInfraccionCAI;
import epn.edu.ec.modelo.EjecucionMedidaCAI;
import epn.edu.ec.modelo.InformacionCambioMedidaCAI;
import epn.edu.ec.servicios.CaiServicio;
import epn.edu.ec.servicios.DatosProvinciaCantonServicio;
import epn.edu.ec.servicios.DetalleInfraccionCAIServicio;
import epn.edu.ec.servicios.EjecucionMedidaServicio;
import epn.edu.ec.servicios.InformacionCambioMedidaServicio;
import epn.edu.ec.utilidades.PermisosUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "ejecucionMedidaControlador")
@ViewScoped
public class EjecucionMedidaControlador implements Serializable {

    private DetalleInfraccionCAI detalleInfraccionCAI;
    private EjecucionMedidaCAI ejecucionMedidaCAI;
    private List<EjecucionMedidaCAI> listaEjecucionMedida;
    private EjecucionMedidaServicio servicio;
    private DetalleInfraccionCAIServicio servicioDI;
    private boolean guardado;
    
    private List<DatosProvinciaCanton> provincias;
    private List<DatosProvinciaCanton> cantones;
    private DatosProvinciaCantonServicio servicioCAIPC;

    private CAI cai;
    private List<CAI> listaCAI;
    private CaiServicio servicioCAI;
    private PermisosUsuario permisosUsuario;
    String tipoMedida;
    boolean esSocioeducativa=true;
    private Date fechaIngresoProcesoCAI=null;
    
    @PostConstruct
    public void init() {
        
        permisosUsuario= new PermisosUsuario();
        servicio = new EjecucionMedidaServicio();
        servicioCAI = new CaiServicio();
        servicioDI = new DetalleInfraccionCAIServicio();
        cai= new CAI();
        
        listaCAI = new ArrayList<>();
        listaCAI = servicioCAI.listaCai();

        ejecucionMedidaCAI = new EjecucionMedidaCAI();
        listaEjecucionMedida = new ArrayList<>();
        guardado = false;
        
        provincias = new ArrayList<>();
        servicioCAIPC = new DatosProvinciaCantonServicio();

        detalleInfraccionCAI = new DetalleInfraccionCAI();

        DetalleInfraccionCAI detalleInfraccionCAIAux = (DetalleInfraccionCAI) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("detalle_infraccion_cai");
        
        if(detalleInfraccionCAIAux != null){
            
            detalleInfraccionCAI=detalleInfraccionCAIAux;
            
            fechaIngresoProcesoCAI=detalleInfraccionCAI.getIdAdolescenteInfractorCAI().getFechaIngresoProceso();
            ejecucionMedidaCAI.setFechaIngresoCai(fechaIngresoProcesoCAI);
            
            List<EjecucionMedidaCAI> listaAux = servicio.obtenerMedidasPorInfraccionCAI(detalleInfraccionCAI);
            
            if(listaAux.isEmpty()!=true){            
                listaEjecucionMedida=listaAux;
            }
        }
        List<DatosProvinciaCanton> provinciasAux = servicioCAIPC.listarDatosProvinciaCanton();
        if(provinciasAux!=null){
            provincias=provinciasAux;
        }
    }
    
    public String getTipoMedida() {
        return tipoMedida;
    }

    public void setTipoMedida(String tipoMedida) {
        this.tipoMedida = tipoMedida;
        if("MEDIDA SOCIOEDUCATIVA PRIVATIVA DE LIBERTAD".equals(tipoMedida)){
            esSocioeducativa=true;
            this.ejecucionMedidaCAI.setTipoMedida("MEDIDA SOCIOEDUCATIVA PRIVATIVA DE LIBERTAD");
        }else if("MEDIDA CAUTELAR".equals(tipoMedida)){
            esSocioeducativa=false;
            this.ejecucionMedidaCAI.setTipoMedida("MEDIDA CAUTELAR");
        }
    }

    public boolean isEsSocioeducativa() {
        return esSocioeducativa;
    }

    public void setEsSocioeducativa(boolean esSocioeducativa) {
        this.esSocioeducativa = esSocioeducativa;
    }

    public DetalleInfraccionCAI getDetalleInfraccionCAI() {
        return detalleInfraccionCAI;
    }

    public void setDetalleInfraccionCAI(DetalleInfraccionCAI detalleInfraccionCAI) {
        this.detalleInfraccionCAI = detalleInfraccionCAI;
    }

    public EjecucionMedidaCAI getEjecucionMedidaCAI() {
        return ejecucionMedidaCAI;
    }

    public void setEjecucionMedidaCAI(EjecucionMedidaCAI ejecucionMedidaCAI) {
        this.ejecucionMedidaCAI = ejecucionMedidaCAI;
    }

    public List<EjecucionMedidaCAI> getListaEjecucionMedida() {
        return listaEjecucionMedida;
    }

    public void setListaEjecucionMedida(List<EjecucionMedidaCAI> listaEjecucionMedida) {
        this.listaEjecucionMedida = listaEjecucionMedida;
    }

    public EjecucionMedidaServicio getServicio() {
        return servicio;
    }

    public DetalleInfraccionCAIServicio getServicioDI() {
        return servicioDI;
    }

    public CAI getCai() {
        return cai;
    }

    public void setCai(CAI cai) {
        this.cai = cai;
    }

    public List<CAI> getListaCAI() {
        return listaCAI;
    }

    public void setListaCAI(List<CAI> listaCAI) {
        this.listaCAI = listaCAI;
    }

    public CaiServicio getServicioCAI() {
        return servicioCAI;
    }

    public void setServicioCAI(CaiServicio servicioCAI) {
        this.servicioCAI = servicioCAI;
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
        cantones = new ArrayList<>();
        String provincia = detalleInfraccionCAI.getProvinciaInfraccion();
        if (provincia != null) {
            cantones = listarCantonesPorProvincia(provincia);
        } else {
        }
        return cantones;
    }

    public Date getFechaIngresoProcesoCAI() {
        return fechaIngresoProcesoCAI;
    }
    
    /**
     * *******************Métodos para invocar a los diferentes servicios
     * web*****************
     */
    
    public void obtenerEjecucionMedidaSeleccionada(EjecucionMedidaCAI medidaSeleccionada){
         cai=medidaSeleccionada.getIdCai();
         
        if("MEDIDA SOCIOEDUCATIVA PRIVATIVA DE LIBERTAD".equals(medidaSeleccionada.getTipoMedida())){
            esSocioeducativa=true;
            tipoMedida= "MEDIDA SOCIOEDUCATIVA PRIVATIVA DE LIBERTAD";
            
            
        }else if("MEDIDA CAUTELAR".equals(medidaSeleccionada.getTipoMedida())){
            esSocioeducativa=false;
            tipoMedida="MEDIDA CAUTELAR";
        }
        
        ejecucionMedidaCAI=medidaSeleccionada;
        //ejecucionMedidaCAI.setFechaReporteCAI(fechaIngresoProcesoCAI);
    }
    
    public void guardarEjecucionMedida() {

        if(ejecucionMedidaCAI.getFechaResolucion()== null || ejecucionMedidaCAI.getFechaAprehension() == null || ejecucionMedidaCAI.getAnios() == null || ejecucionMedidaCAI.getMeses() == null || ejecucionMedidaCAI.getDias() == null ){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fecha Resolución, Fecha de Aprehensión, Años, Meses, Días deben de tener un valor", "Error"));
            
        } else {
            
            for (CAI c : listaCAI) {
                if (c.getCai().equals(cai.getCai())) {
                    cai = c;
                }
            }
            this.ejecucionMedidaCAI.setIdCai(cai);
            this.ejecucionMedidaCAI.setFechaIngresoCai(fechaIngresoProcesoCAI);
            this.ejecucionMedidaCAI.setIdDetalleInfraccionCAI(detalleInfraccionCAI);

            EjecucionMedidaCAI ejecucionMedidaAux = servicio.guardarEjecucionMedidaCAI(ejecucionMedidaCAI);
            if (ejecucionMedidaAux != null) {

                List<EjecucionMedidaCAI> listaAux = servicio.obtenerMedidasPorInfraccionCAI(detalleInfraccionCAI);

                if (listaAux.isEmpty() != true) {
                    listaEjecucionMedida = listaAux;
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO EJECUCIÓN MEDIDA", "Información"));

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO EJECUCIÓN MEDIDA", "Error"));
            }
        }
        
    }
    
    public void guardarEdicionEjecucionMedida() {

        if(ejecucionMedidaCAI.getFechaResolucion()== null || ejecucionMedidaCAI.getFechaAprehension() == null || ejecucionMedidaCAI.getAnios() == null || ejecucionMedidaCAI.getMeses() == null || ejecucionMedidaCAI.getDias() == null ){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fecha Resolución, Fecha de Aprehensión, Años, Meses, Días deben de tener un valor", "Error"));
            
        } else {
            
            for (CAI c : listaCAI) {
                if (c.getCai().equals(cai.getCai())) {
                    cai = c;
                }
            }
            this.ejecucionMedidaCAI.setIdCai(cai);
            this.ejecucionMedidaCAI.setFechaIngresoCai(fechaIngresoProcesoCAI);
            this.ejecucionMedidaCAI.setIdDetalleInfraccionCAI(detalleInfraccionCAI);

            EjecucionMedidaCAI ejecucionMedidaAux = servicio.guardarEjecucionMedidaCAI(ejecucionMedidaCAI);
            if (ejecucionMedidaAux != null) {

                editarInformacionCambioMedida(ejecucionMedidaAux);
                
                //obtengo la lista auxiliar de Medidas, para mostrarlas en la tabla
                List<EjecucionMedidaCAI> listaAux = servicio.obtenerMedidasPorInfraccionCAI(detalleInfraccionCAI);

                if (listaAux.isEmpty() != true) {
                    listaEjecucionMedida = listaAux;
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO EJECUCIÓN MEDIDA", "Información"));

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO EJECUCIÓN MEDIDA", "Error"));
            }
        }
        
    }

    public String agregarInformacion(EjecucionMedidaCAI ejecucion){
        
        String redireccionInformacionAdicionalMedida=permisosUsuario.redireccionInformacionAdicionalMedida();
        
        if(redireccionInformacionAdicionalMedida!=null){

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ejecucion_medida_cai", ejecucion);
            return redireccionInformacionAdicionalMedida;
        }else{
            return null;
        } 
    }
    
    public String editarInformacion(EjecucionMedidaCAI ejecucion){
        
        String redireccionInformacionAdicionalMedida=permisosUsuario.redireccionEditarInformacionAdicionalMedida();
        
        if(redireccionInformacionAdicionalMedida!=null){

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ejecucion_medida_cai", ejecucion);
            return redireccionInformacionAdicionalMedida;
        }else{
            return null;
        } 
    }
    
    private void editarInformacionCambioMedida(EjecucionMedidaCAI  ejecucionMedidaCAI){
        
        InformacionCambioMedidaServicio servicioCambioMedida= new InformacionCambioMedidaServicio();
        
        InformacionCambioMedidaCAI infoCambioMedidaCAI= servicioCambioMedida.obtenerInformacionCambioMedidaCAI(ejecucionMedidaCAI.getIdEjecucionMedidaCai());
        if(infoCambioMedidaCAI != null){
        
            String cambioMedidaSocioeducativa=infoCambioMedidaCAI.getCambioMedidaSocioeducativa();
            Integer cumplimieno6080Tiempo=getCumplimieno6080TiempoPrivacionLibertad(cambioMedidaSocioeducativa, ejecucionMedidaCAI);
            
            if(cumplimieno6080Tiempo != null){
                
                Date fechaCumplimiento= getFechaCumplimiento6080(cambioMedidaSocioeducativa, ejecucionMedidaCAI, cumplimieno6080Tiempo);
            
                if(fechaCumplimiento != null){
                    
                    Date fechaAlertaCambioMedida=getAlertaCambioMedida(fechaCumplimiento);
                    
                    
                    infoCambioMedidaCAI.setCumplimieno6080TiempoPrivacionLibertad(cumplimieno6080Tiempo);;
                    infoCambioMedidaCAI.setFechaCumplimiento6080(fechaCumplimiento);
                    infoCambioMedidaCAI.setAlertaCambioMedida(fechaAlertaCambioMedida);
                    servicioCambioMedida.guardarInformacionCambioMedidaCAI(infoCambioMedidaCAI);
                }
            }
        
        }
        
        
    }
    
    private Integer getCumplimieno6080TiempoPrivacionLibertad(String cambioMedidaSocioeducativa, EjecucionMedidaCAI idEjecucionMedidaCAI) {
        
        Integer cumplimieno6080TiempoPrivacionLibertad=null;
        
        if(cambioMedidaSocioeducativa!=null && idEjecucionMedidaCAI!=null){
           
            if (idEjecucionMedidaCAI.getTiempoSentenDias()!=null) {
            
                if (cambioMedidaSocioeducativa.equals("60% DE CUMPLIMIENTO")) {
                    int tiempo60=(idEjecucionMedidaCAI.getTiempoSentenDias()*60)/100;
                    cumplimieno6080TiempoPrivacionLibertad=tiempo60;
                    return cumplimieno6080TiempoPrivacionLibertad;
                } else if (cambioMedidaSocioeducativa.equals("80% DE CUMPLIMIENTO")) {
                    int tiempo80=(idEjecucionMedidaCAI.getTiempoSentenDias()*80)/100;
                    cumplimieno6080TiempoPrivacionLibertad=tiempo80;
                    return cumplimieno6080TiempoPrivacionLibertad;
                }
            }
        }
        
        return cumplimieno6080TiempoPrivacionLibertad;
    }

    private Date getFechaCumplimiento6080(String cambioMedidaSocioeducativa, EjecucionMedidaCAI idEjecucionMedidaCAI, Integer cumplimieno6080TiempoPrivacionLibertad) {
        
        Date fechaCumplimiento6080=null;
        
        if(cambioMedidaSocioeducativa!=null && idEjecucionMedidaCAI!=null){
            
            if(idEjecucionMedidaCAI.getFechaAprehension()!=null){
                
                Calendar fechaAux = Calendar.getInstance();
                fechaAux.setTime(idEjecucionMedidaCAI.getFechaAprehension());
                fechaAux.add(Calendar.DATE, cumplimieno6080TiempoPrivacionLibertad);
                fechaCumplimiento6080=fechaAux.getTime();
            }
        }
        
        return fechaCumplimiento6080;
    }

    private Date getAlertaCambioMedida(Date fechaCumplimiento6080) {
        
        Date alertaCambioMedida=null;
        
        if(fechaCumplimiento6080!=null){
            Calendar fechaAlerta= Calendar.getInstance();
            fechaAlerta.setTime(fechaCumplimiento6080);
            fechaAlerta.add(Calendar.DATE, -14);
            alertaCambioMedida=fechaAlerta.getTime();
        }
        return alertaCambioMedida;
    }

 
    
}
