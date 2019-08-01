package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.modelo.CAI;
import epn.edu.ec.modelo.DatosProvinciaCanton;
import epn.edu.ec.modelo.DetalleInfraccionCAI;
import epn.edu.ec.modelo.EjecucionMedidaCAI;
import epn.edu.ec.servicios.CaiServicio;
import epn.edu.ec.servicios.DatosProvinciaCantonServicio;
import epn.edu.ec.servicios.DetalleInfraccionCAIServicio;
import epn.edu.ec.servicios.EjecucionMedidaServicio;
import epn.edu.ec.utilidades.PermisosUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "ejecucionMedidaControlador")
@ViewScoped
public class EjecucionMedidaControlador implements Serializable {

    private AdolescenteInfractorCAI adolescenteInfractorCai;
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

    @PostConstruct
    public void init() {
        
        permisosUsuario= new PermisosUsuario();
        servicio = new EjecucionMedidaServicio();
        servicioCAI = new CaiServicio();
        servicioDI = new DetalleInfraccionCAIServicio();

        listaCAI = new ArrayList<>();
        listaCAI = servicioCAI.listaCai();

        adolescenteInfractorCai = new AdolescenteInfractorCAI();
        ejecucionMedidaCAI = new EjecucionMedidaCAI();
        listaEjecucionMedida = new ArrayList<>();
        guardado = false;
        
        provincias = new ArrayList<>();
        servicioCAIPC = new DatosProvinciaCantonServicio();

        detalleInfraccionCAI = new DetalleInfraccionCAI();

        DetalleInfraccionCAI detalleInfraccionCAIAux = (DetalleInfraccionCAI) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("detalle_infraccion_cai");
        
        if(detalleInfraccionCAIAux != null){
            
            detalleInfraccionCAI=detalleInfraccionCAIAux;            
            ejecucionMedidaCAI.setFechaReporteCAI(detalleInfraccionCAI.getIdAdolescenteInfractorCAI().getFechaIngresoProceso());
            
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
            System.out.println("No hay provincia seleccionada");
        }
        return cantones;
    }

    /**
     * *******************Métodos para invocar a los diferentes servicios
     * web*****************
     */
    public void guardarEjecucionMedida() {

        this.ejecucionMedidaCAI.setIdDetalleInfraccionCAI(detalleInfraccionCAI);

        EjecucionMedidaCAI ejecucionMedidaAux = servicio.guardarEjecucionMedidaCAI(ejecucionMedidaCAI);
        if (ejecucionMedidaAux != null) {
            guardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO EJECUCIÓN MEDIDA", "Información"));
            
        } else {
            guardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO EJECUCIÓN MEDIDA", "Error"));
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
}
