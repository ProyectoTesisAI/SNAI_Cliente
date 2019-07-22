package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.modelo.DatosProvinciaCanton;
import epn.edu.ec.modelo.DatosTipoPenalCAI;
import epn.edu.ec.modelo.DetalleInfraccionCAI;
import epn.edu.ec.servicios.DatosProvinciaCantonServicio;
import epn.edu.ec.servicios.DatosTipoPenalCAIServicio;
import epn.edu.ec.servicios.DetalleInfraccionCAIServicio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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

    @PostConstruct
    public void init() {
        servicio = new DetalleInfraccionCAIServicio();

        detalleInfraccion = new DetalleInfraccionCAI();
        listaDetalleInfraccion = new ArrayList<>();
        guardado = false;

        provincias = new ArrayList<>();
        servicioCAIPC = new DatosProvinciaCantonServicio();
        
        tiposPenal = new ArrayList<>();
        servicioTP = new DatosTipoPenalCAIServicio();

        adolescenteInfractorCAI = new AdolescenteInfractorCAI();
        adolescenteInfractorCAI = (AdolescenteInfractorCAI) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_cai");

        if (adolescenteInfractorCAI != null) {
            List<DetalleInfraccionCAI> listaAux = servicio.obtenerDetallesInfraccionCAI(adolescenteInfractorCAI);
            if (listaAux.isEmpty()!=true) {
                listaDetalleInfraccion = listaAux;
            } else if(listaAux.isEmpty()==true){
                listaDetalleInfraccion=new ArrayList<>();
            }
        } else {
            adolescenteInfractorCAI = new AdolescenteInfractorCAI();
        }

        provincias = servicioCAIPC.listarDatosProvinciaCanton();
        
        tiposPenal=servicioTP.listarDatosTipoPenalCAI();
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

    /**
     * *******************Métodos para invocar a los diferentes servicios
     * web*****************
     */
    public String guardarInformacionInfraccion() {
        this.detalleInfraccion.setIdAdolescenteInfractorCAI(adolescenteInfractorCAI);
        //this.detalleInfraccion.setIdDetalleInfraccion(adolescenteInfractorCAI.getIdAdolescenteInfractor().getIdAdolescenteInfractor());

        DetalleInfraccionCAI detalleInfraccionAux = servicio.guardarDetalleInfraccionCAI(detalleInfraccion);
        if (detalleInfraccionAux != null) {
            return "/paginas/cai/cai.com?faces-redirect=true";
        } else {
            return null;
        }
    }
    
    public String agregarMedida(DetalleInfraccionCAI detalle){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("detalle_infraccion_cai", detalle);
        return "/paginas/cai/matriz/panel_crear_medida_cai.com?faces-redirect=true";
    }
}
