package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.modelo.DatosProvinciaCanton;
import epn.edu.ec.modelo.DatosTipoPenalCAI;
import epn.edu.ec.modelo.DetalleInfraccionCAI;
import epn.edu.ec.servicios.DatosProvinciaCantonServicio;
import epn.edu.ec.servicios.DatosTipoPenalCAIServicio;
import epn.edu.ec.servicios.DetalleInfraccionCAIServicio;
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

@Named(value = "detalleInfraccionControlador")
@ViewScoped
public class DetalleInfraccionControlador implements Serializable {

    private AdolescenteInfractorCAI adolescenteInfractorCAI;

    private DetalleInfraccionCAI detalleInfraccion;
    private List<DetalleInfraccionCAI> listaDetalleInfraccion;
    private DetalleInfraccionCAIServicio servicio;
    private boolean guardado;

    private List<DatosProvinciaCanton> provincias;
    private List<DatosProvinciaCanton> cantones;
    private DatosProvinciaCantonServicio servicioCAIPC;

    private List<DatosTipoPenalCAI> tiposPenal;
    private DatosTipoPenalCAIServicio servicioTP;
    private PermisosUsuario permisosUsuario;

    private String provincia;
    @PostConstruct
    public void init() {

        permisosUsuario= new PermisosUsuario();
        servicio = new DetalleInfraccionCAIServicio();
        detalleInfraccion = new DetalleInfraccionCAI();
        guardado = false;

        provincias = new ArrayList<>();
        servicioCAIPC = new DatosProvinciaCantonServicio();
        
        tiposPenal = new ArrayList<>();
        servicioTP = new DatosTipoPenalCAIServicio();

        adolescenteInfractorCAI = new AdolescenteInfractorCAI();
        listaDetalleInfraccion= new ArrayList<>();
        
        AdolescenteInfractorCAI adolescenteInfractorCAIAux = (AdolescenteInfractorCAI) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_cai");

        if (adolescenteInfractorCAIAux != null) {
            
            adolescenteInfractorCAI=adolescenteInfractorCAIAux;
            
            List<DetalleInfraccionCAI> listaAux = servicio.obtenerDetallesInfraccionCAI(adolescenteInfractorCAI);
            
            if (listaAux.isEmpty()!=true) {
                listaDetalleInfraccion = listaAux;
            } 
        } 
        List<DatosProvinciaCanton> provinciasAux = servicioCAIPC.listarDatosProvinciaCanton();
        if(provinciasAux!=null){
            provincias=provinciasAux;
        }
        
        List<DatosTipoPenalCAI> tiposPenalAux=servicioTP.listarDatosTipoPenalCAI();
        if(tiposPenalAux!=null){
            tiposPenal=tiposPenalAux;
        }
    }

    public AdolescenteInfractorCAI getAdolescenteInfractorCAI() {
        return adolescenteInfractorCAI;
    }

    public void setAdolescenteInfractorCAI(AdolescenteInfractorCAI adolescenteInfractorUDI) {
        this.adolescenteInfractorCAI = adolescenteInfractorUDI;
    }

    public DetalleInfraccionCAI getDetalleInfraccion() {
        return detalleInfraccion;
    }

    public void setDetalleInfraccion(DetalleInfraccionCAI informacionInfraccion) {
        this.detalleInfraccion = informacionInfraccion;
    }

    public List<DetalleInfraccionCAI> getListaDetalleInfraccion() {
        return listaDetalleInfraccion;
    }

    public void setListaDetalleInfraccion(List<DetalleInfraccionCAI> listaDetalleInfraccion) {
        this.listaDetalleInfraccion = listaDetalleInfraccion;
    }

    public DetalleInfraccionCAIServicio getServicio() {
        return servicio;
    }

    public List<DatosProvinciaCanton> getProvincias() {
        return provincias;
    }

    public DatosProvinciaCantonServicio getServicioCAIPC() {
        return servicioCAIPC;
    }

    public List<DatosTipoPenalCAI> getTiposPenal() {
        return tiposPenal;
    }

    public DatosTipoPenalCAIServicio getServicioTP() {
        return servicioTP;
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
        String provincia = detalleInfraccion.getProvinciaInfraccion();
        if (provincia != null) {
            cantones = listarCantonesPorProvincia(provincia);
        } else {
            System.out.println("No hay provincia seleccionada");
        }
        return cantones;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    
    

    /**
     * *******************Métodos para invocar a los diferentes servicios
     * web*****************
     */
    public void guardarDetalleInfraccion() {
        
        System.out.println("provincia:" +provincia);
        this.detalleInfraccion.setIdAdolescenteInfractorCAI(adolescenteInfractorCAI);
        
        DetalleInfraccionCAI detalleInfraccionAux = servicio.guardarDetalleInfraccionCAI(detalleInfraccion);
        if (detalleInfraccionAux != null) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO DETALLE INFRACCIÓN", "Información"));
            
            List<DetalleInfraccionCAI> listaAux = servicio.obtenerDetallesInfraccionCAI(adolescenteInfractorCAI);
            
            if (listaAux.isEmpty()!=true) {
                listaDetalleInfraccion = listaAux;
            } 
            
            
        } else {
            guardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO DETALLE INFRACCIÓN", "Error"));
        }
    }
    
    public void obtenerDetalleInfraccionSeleccionada(DetalleInfraccionCAI infraccionSeleccionada){
        detalleInfraccion=infraccionSeleccionada;
    }
    
    public String agregarMedida(DetalleInfraccionCAI detalle){
        
        String redireccionEjecucionMedida=permisosUsuario.redireccionEjecucionMedida();
        
        if(redireccionEjecucionMedida!=null){

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("detalle_infraccion_cai", detalle);
            return redireccionEjecucionMedida;
        }else{
            return null;
        }    
    }
}
