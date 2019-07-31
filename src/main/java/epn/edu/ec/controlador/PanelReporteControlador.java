package epn.edu.ec.controlador;

import epn.edu.ec.modelo.DatosTipoPenalCAI;
import epn.edu.ec.modelo.EjecucionMedidaCAI;
import epn.edu.ec.modelo.Reporte;
import epn.edu.ec.modelo.Reporte1;
import epn.edu.ec.modelo.Reporte2;
import epn.edu.ec.modelo.Reporte3;
import epn.edu.ec.modelo.Reporte4;
import epn.edu.ec.modelo.Reporte5;
import epn.edu.ec.modelo.Reporte6N;
import epn.edu.ec.modelo.Reporte6S;
import epn.edu.ec.modelo.Reporte7;
import epn.edu.ec.servicios.AdolescenteInfractorServicio;
import epn.edu.ec.servicios.DatosTipoPenalCAIServicio;
import epn.edu.ec.utilidades.EnlacesPrograma;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "panelReportesControlador")
@ViewScoped
public class PanelReporteControlador implements Serializable {

    private EnlacesPrograma enlaces;
    private Reporte1 reporte1;
    private Reporte2 reporte2;
    private List<Reporte1> listaReportes1CAI;
    private List<Reporte1> listaReportes1UDI;
    private List<Reporte2> listaReportes2CAI;
    private List<Reporte2> listaReportes2UDI;
    private List<Reporte3> listaReportes3CAI;
    private List<Reporte3> listaReportes3UDI;
    private List<Reporte4> listaReportes4CAI;
    private List<Reporte4> listaReportes4UDI;
    private List<Reporte5> listaReportes5CAI;
    private List<Reporte6S> listaReportes6SCAI;
    private List<Reporte6S> listaReportes6SUDI;
    private List<Reporte6N> listaReportes6NCAI;
    private List<Reporte6N> listaReportes6NUDI;
    private List<Reporte7> listaReportes7CAI;
    private List<Reporte7> listaReportes7UDI;
    private AdolescenteInfractorServicio servicio;
    private Date fechaBuscar;
    private String nacionalidad;
    private String medida;
    private EjecucionMedidaCAI medidaCAI;
    private String nivelEducativo;
    private String edad;

    private List<DatosTipoPenalCAI> tiposPenal;
    private DatosTipoPenalCAIServicio servicioTP;
    
    private Reporte reporte;
    private List<Reporte> listaDescripcionReportes;
    
    String tipoMedida;
    boolean esSocioeducativa;
    private boolean estudia;

    @PostConstruct
    public void init() {
        servicio = new AdolescenteInfractorServicio();
        listaReportes1CAI = new ArrayList<>();
        listaReportes1UDI = new ArrayList<>();
        listaReportes2CAI = new ArrayList<>();
        listaReportes2UDI = new ArrayList<>();
        listaReportes3CAI = new ArrayList<>();
        listaReportes3UDI = new ArrayList<>();
        listaReportes4CAI = new ArrayList<>();
        listaReportes4UDI = new ArrayList<>();
        listaReportes5CAI = new ArrayList<>();
        listaReportes6SCAI = new ArrayList<>();
        listaReportes6SUDI = new ArrayList<>();
        listaReportes6NCAI = new ArrayList<>();
        listaReportes6NUDI = new ArrayList<>();
        listaReportes7CAI = new ArrayList<>();
        listaReportes7UDI = new ArrayList<>();
        reporte1 = new Reporte1();
        reporte2 = new Reporte2();
        nacionalidad = null;
        edad=null;
        enlaces = new EnlacesPrograma();

        tiposPenal = new ArrayList<>();
        servicioTP = new DatosTipoPenalCAIServicio();
        fechaBuscar = new Date();
        medida=null;
        medidaCAI=new EjecucionMedidaCAI();
        nivelEducativo=null;

        tiposPenal = servicioTP.listarDatosTipoPenalCAI();
        
        if(isEstudia()){
            estudia=true;
        }
        else{
            estudia=false;
        }
        
        listaDescripcionReportes = new ArrayList<>();
        listaDescripcionReportes.add(reporte=new Reporte("Reporte 1", "Reporte para obetener los adolescetes por Tipo de delito"));
        listaDescripcionReportes.add(reporte=new Reporte("Reporte 2", "Reporte para obetener los adolescetes por la edad del adolescente"));
        listaDescripcionReportes.add(reporte=new Reporte("Reporte 3", "Reporte para obetener la edad del adolescente en una determinada fecha"));
        listaDescripcionReportes.add(reporte=new Reporte("Reporte 4", "Reporte para obetener los adolescetes por la nacionalidad del adolescente"));
        listaDescripcionReportes.add(reporte=new Reporte("Reporte 5", "Reporte para obetener los adolescetes por el tipo de medida"));
        listaDescripcionReportes.add(reporte=new Reporte("Reporte 6", "Reporte para obetener los adolescetes por fecha de ingreso al CAI"));
        listaDescripcionReportes.add(reporte=new Reporte("Reporte 7", "Reporte para obetener los adolescetes por su nivel de educación"));
        listaDescripcionReportes.add(reporte=new Reporte("Reporte 8", "Reporte para obetener los adolescetes por su edad y nivel de educación"));
        listaDescripcionReportes.add(reporte=new Reporte("Reporte 9", "Reporte para obetener los adolescetes por su lugar de residencia"));
    }

    public List<Reporte1> getListaReportes1CAI() {
        return listaReportes1CAI;
    }

    public void setListaReportes1CAI(List<Reporte1> listaReportes1CAI) {
        this.listaReportes1CAI = listaReportes1CAI;
    }

    public List<Reporte1> getListaReportes1UDI() {
        return listaReportes1UDI;
    }

    public void setListaReportes1UDI(List<Reporte1> listaReportes1UDI) {
        this.listaReportes1UDI = listaReportes1UDI;
    }

    public List<Reporte2> getListaReportes2CAI() {
        return listaReportes2CAI;
    }

    public void setListaReportes2CAI(List<Reporte2> listaReportes2CAI) {
        this.listaReportes2CAI = listaReportes2CAI;
    }

    public List<Reporte2> getListaReportes2UDI() {
        return listaReportes2UDI;
    }

    public void setListaReportes2UDI(List<Reporte2> listaReportes2UDI) {
        this.listaReportes2UDI = listaReportes2UDI;
    }

    public List<Reporte3> getListaReportes3CAI() {
        return listaReportes3CAI;
    }

    public void setListaReportes3CAI(List<Reporte3> listaReportes3CAI) {
        this.listaReportes3CAI = listaReportes3CAI;
    }

    public List<Reporte3> getListaReportes3UDI() {
        return listaReportes3UDI;
    }

    public void setListaReportes3UDI(List<Reporte3> listaReportes3UDI) {
        this.listaReportes3UDI = listaReportes3UDI;
    }

    public List<Reporte4> getListaReportes4CAI() {
        return listaReportes4CAI;
    }

    public void setListaReportes4CAI(List<Reporte4> listaReportes4CAI) {
        this.listaReportes4CAI = listaReportes4CAI;
    }

    public List<Reporte4> getListaReportes4UDI() {
        return listaReportes4UDI;
    }

    public void setListaReportes4UDI(List<Reporte4> listaReportes4UDI) {
        this.listaReportes4UDI = listaReportes4UDI;
    }

    public List<Reporte5> getListaReportes5CAI() {
        return listaReportes5CAI;
    }

    public void setListaReportes5CAI(List<Reporte5> listaReportes5CAI) {
        this.listaReportes5CAI = listaReportes5CAI;
    }

    public List<Reporte6S> getListaReportes6SCAI() {
        return listaReportes6SCAI;
    }

    public void setListaReportes6SCAI(List<Reporte6S> listaReportes6SCAI) {
        this.listaReportes6SCAI = listaReportes6SCAI;
    }

    public List<Reporte6S> getListaReportes6SUDI() {
        return listaReportes6SUDI;
    }

    public void setListaReportes6SUDI(List<Reporte6S> listaReportes6SUDI) {
        this.listaReportes6SUDI = listaReportes6SUDI;
    }

    public List<Reporte6N> getListaReportes6NCAI() {
        return listaReportes6NCAI;
    }

    public void setListaReportes6NCAI(List<Reporte6N> listaReportes6NCAI) {
        this.listaReportes6NCAI = listaReportes6NCAI;
    }

    public List<Reporte6N> getListaReportes6NUDI() {
        return listaReportes6NUDI;
    }

    public void setListaReportes6NUDI(List<Reporte6N> listaReportes6NUDI) {
        this.listaReportes6NUDI = listaReportes6NUDI;
    }

    public List<Reporte7> getListaReportes7CAI() {
        return listaReportes7CAI;
    }

    public void setListaReportes7CAI(List<Reporte7> listaReportes7CAI) {
        this.listaReportes7CAI = listaReportes7CAI;
    }

    public List<Reporte7> getListaReportes7UDI() {
        return listaReportes7UDI;
    }

    public void setListaReportes7UDI(List<Reporte7> listaReportes7UDI) {
        this.listaReportes7UDI = listaReportes7UDI;
    }

    public List<DatosTipoPenalCAI> getTiposPenal() {
        return tiposPenal;
    }

    public DatosTipoPenalCAIServicio getServicioTP() {
        return servicioTP;
    }

    public Reporte1 getReporte1() {
        return reporte1;
    }

    public void setReporte1(Reporte1 reporte1) {
        this.reporte1 = reporte1;
    }

    public Reporte2 getReporte2() {
        return reporte2;
    }

    public void setReporte2(Reporte2 reporte2) {
        this.reporte2 = reporte2;
    }

    public Date getFechaBuscar() {
        return fechaBuscar;
    }

    public void setFechaBuscar(Date fechaBuscar) {
        this.fechaBuscar = fechaBuscar;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public EjecucionMedidaCAI getMedidaCAI() {
        return medidaCAI;
    }

    public void setMedidaCAI(EjecucionMedidaCAI medidaCAI) {
        this.medidaCAI = medidaCAI;
    }

    public String getTipoMedida() {
        return tipoMedida;
    }

    public void setTipoMedida(String tipoMedida) {
        this.tipoMedida = tipoMedida;
        if("MEDIDA SOCIOEDUCATIVA PRIVATIVA DE LIBERTAD".equals(tipoMedida)){
            esSocioeducativa=true;
            this.medidaCAI.setTipoMedida(tipoMedida);
        }else if("MEDIDA CAUTELAR".equals(tipoMedida)){
            esSocioeducativa=false;
            this.medidaCAI.setTipoMedida(tipoMedida);
        }
    }

    public boolean isEsSocioeducativa() {
        if("MEDIDA SOCIOEDUCATIVA PRIVATIVA DE LIBERTAD".equals(tipoMedida)){
            esSocioeducativa=true;
            this.medidaCAI.setTipoMedida(tipoMedida);
        }else if("MEDIDA CAUTELAR".equals(tipoMedida)){
            esSocioeducativa=false;
            this.medidaCAI.setTipoMedida(tipoMedida);
        }
        return esSocioeducativa;
    }

    public void setEsSocioeducativa(boolean esSocioeducativa) {
        this.esSocioeducativa = esSocioeducativa;
    }

    public String getNivelEducativo() {
        return nivelEducativo;
    }

    public void setNivelEducativo(String nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }
    
    public boolean isEstudia() {
        return estudia;
    }

    public void setEstudia(boolean estudia) {
        this.estudia = estudia;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public Reporte getReporte() {
        return reporte;
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

    public List<Reporte> getListaDescripcionReportes() {
        return listaDescripcionReportes;
    }

    public void setListaDescripcionReportes(List<Reporte> listaDescripcionReportes) {
        this.listaDescripcionReportes = listaDescripcionReportes;
    }

    public List<Reporte1> buscarTipoDelitoUDI() {
        List<Reporte1> reporteRespuestaUDI = servicio.reporteTipoDelitoUDI(reporte1);
        if (reporteRespuestaUDI != null) {
            return listaReportes1UDI = reporteRespuestaUDI;
        } else {
            return listaReportes1UDI = null;
        }
    }

    public List<Reporte1> buscarTipoDelitoCAI() {
        List<Reporte1> reporteRespuestaCAI = servicio.reporteTipoDelitoCAI(reporte1);
        if (reporteRespuestaCAI != null) {
            return listaReportes1CAI = reporteRespuestaCAI;
        } else {
            return listaReportes1CAI = null;
        }
    }

    public List<Reporte2> buscarEdadCAI() {
        List<Reporte2> reporteRespuestaCAI = servicio.reporteEdadCAI(reporte2);
        if (reporteRespuestaCAI != null) {
            return listaReportes2CAI = reporteRespuestaCAI;
        } else {
            return listaReportes2CAI = null;
        }
    }

    public List<Reporte2> buscarEdadUDI() {
        List<Reporte2> reporteRespuestaUDI = servicio.reporteEdadUDI(reporte2);
        if (reporteRespuestaUDI != null) {
            return listaReportes2UDI = reporteRespuestaUDI;
        } else {
            return listaReportes2UDI = null;
        }
    }

    public List<Reporte2> buscarEdadFechaFuturaUDI() {
        List<Reporte2> reporteRespuestaUDI = servicio.reporteEdadFechaUDI(fechaBuscar);
        if (reporteRespuestaUDI != null) {
            return listaReportes2UDI = reporteRespuestaUDI;
        } else {
            return listaReportes2UDI = null;
        }
    }

    public List<Reporte2> buscarEdadFechaFuturaCAI() {
        List<Reporte2> reporteRespuestaCAI = servicio.reporteEdadFechaCAI(fechaBuscar);
        if (reporteRespuestaCAI != null) {
            return listaReportes2CAI = reporteRespuestaCAI;
        } else {
            return listaReportes2CAI = null;
        }
    }

    public List<Reporte3> buscarNacionalidadCAI() {
        List<Reporte3> reporteRespuestaCAI = servicio.reporteNacionalidadCAI(nacionalidad);
        if (reporteRespuestaCAI != null) {
            return listaReportes3CAI = reporteRespuestaCAI;
        } else {
            return listaReportes3CAI = null;
        }
    }

    public List<Reporte3> buscarNacionalidadUDI() {
        List<Reporte3> reporteRespuestaUDI = servicio.reporteNacionalidadCAI(nacionalidad);
        if (reporteRespuestaUDI != null) {
            return listaReportes3UDI = reporteRespuestaUDI;
        } else {
            return listaReportes3UDI = null;
        }
    }
    
    public List<Reporte4> buscarMedidasSocioeducativasUDI() {
        List<Reporte4> reporteRespuestaUDI = servicio.reporteMedidasSocioeducativasUDI(medida);
        if (reporteRespuestaUDI != null) {
            return listaReportes4UDI = reporteRespuestaUDI;
        } else {
            return listaReportes4UDI = null;
        }
    }
    
    public List<Reporte4> buscarMedidasSocioeducativasCAI() {
        List<Reporte4> reporteRespuestaCAI = servicio.reporteMedidasSocioeducativasCAI(medidaCAI);
        if (reporteRespuestaCAI != null) {
            return listaReportes4CAI = reporteRespuestaCAI;
        } else {
            return listaReportes4CAI = null;
        }
    }
    
    public List<Reporte5> buscarFechaIngresoCAI() {
        List<Reporte5> reporteRespuestaCAI = servicio.reporteFechaIngresoCAI(fechaBuscar);
        if (reporteRespuestaCAI != null) {
            return listaReportes5CAI = reporteRespuestaCAI;
        } else {
            return listaReportes5CAI = null;
        }
    }
    
    public List<Reporte6S> buscarNivelEducativoSiUDI() {
        List<Reporte6S> reporteRespuestaUDI = servicio.reporteNivelEducacionSiUDI(nivelEducativo);
        if (reporteRespuestaUDI != null) {
            return listaReportes6SUDI = reporteRespuestaUDI;
        } else {
            return listaReportes6SUDI = null;
        }
    }
    
    public List<Reporte6N> buscarNivelEducativoNoUDI() {
        List<Reporte6N> reporteRespuestaUDI = servicio.reporteNivelEducacionNoUDI(nivelEducativo);
        if (reporteRespuestaUDI != null) {
            return listaReportes6NUDI = reporteRespuestaUDI;
        } else {
            return listaReportes6NUDI = null;
        }
    }
    
    public List<Reporte6S> buscarNivelEducativoSiCAI() {
        List<Reporte6S> reporteRespuestaCAI = servicio.reporteNivelEducacionSiCAI(nivelEducativo);
        if (reporteRespuestaCAI != null) {
            return listaReportes6SCAI = reporteRespuestaCAI;
        } else {
            return listaReportes6SCAI = null;
        }
    }
    
    public List<Reporte6N> buscarNivelEducativoNoCAI() {
        List<Reporte6N> reporteRespuestaCAI = servicio.reporteNivelEducacionNoCAI(nivelEducativo);
        if (reporteRespuestaCAI != null) {
            return listaReportes6NCAI = reporteRespuestaCAI;
        } else {
            return listaReportes6NCAI = null;
        }
    }
    
    public List<Reporte6S> buscarEdadNivelEducativoSiUDI() {
        List<Reporte6S> reporteRespuestaUDI = servicio.reporteEdadNivelEducacionSiUDI(edad);
        if (reporteRespuestaUDI != null) {
            return listaReportes6SUDI = reporteRespuestaUDI;
        } else {
            return listaReportes6SUDI = null;
        }
    }
    
    public List<Reporte6N> buscarEdadNivelEducativoNoUDI() {
        List<Reporte6N> reporteRespuestaUDI = servicio.reporteEdadNivelEducacionNoUDI(edad);
        if (reporteRespuestaUDI != null) {
            return listaReportes6NUDI = reporteRespuestaUDI;
        } else {
            return listaReportes6NUDI = null;
        }
    }
    
    public List<Reporte6S> buscarEdadNivelEducativoSiCAI() {
        List<Reporte6S> reporteRespuestaCAI = servicio.reporteEdadNivelEducacionSiCAI(edad);
        if (reporteRespuestaCAI != null) {
            return listaReportes6SCAI = reporteRespuestaCAI;
        } else {
            return listaReportes6SCAI = null;
        }
    }
    
    public List<Reporte6N> buscarEdadNivelEducativoNoCAI() {
        List<Reporte6N> reporteRespuestaCAI = servicio.reporteEdadNivelEducacionNoCAI(edad);
        if (reporteRespuestaCAI != null) {
            return listaReportes6NCAI = reporteRespuestaCAI;
        } else {
            return listaReportes6NCAI = null;
        }
    }
    
    public List<Reporte7> buscarLugarResidenciaUDI() {
        List<Reporte7> reporteRespuestaUDI = servicio.reporteLugarResidenciaUDI();
        if (reporteRespuestaUDI != null) {
            return listaReportes7UDI = reporteRespuestaUDI;
        } else {
            return listaReportes7UDI = null;
        }
    }
    
    public List<Reporte7> buscarLugarResidenciaCAI() {
        List<Reporte7> reporteRespuestaCAI = servicio.reporteLugarResidenciaCAI();
        if (reporteRespuestaCAI != null) {
            return listaReportes7CAI = reporteRespuestaCAI;
        } else {
            return listaReportes7CAI = null;
        }
    }
    
    public String generarReportes(Reporte reporte) {

        try {
            if("Reporte 1".equals(reporte.getNombre())){
                return enlaces.PATH_PANEL_REPORTE_1+"?faces-redirect=true";
            }else if("Reporte 2".equals(reporte.getNombre())){
                return enlaces.PATH_PANEL_REPORTE_2+"?faces-redirect=true";
            }else if("Reporte 3".equals(reporte.getNombre())){
                return enlaces.PATH_PANEL_REPORTE_3+"?faces-redirect=true";
            }else if("Reporte 4".equals(reporte.getNombre())){
                return enlaces.PATH_PANEL_REPORTE_4+"?faces-redirect=true";
            }else if("Reporte 5".equals(reporte.getNombre())){
                return enlaces.PATH_PANEL_REPORTE_5+"?faces-redirect=true";
            }else if("Reporte 6".equals(reporte.getNombre())){
                return enlaces.PATH_PANEL_REPORTE_6+"?faces-redirect=true";
            }else if("Reporte 7".equals(reporte.getNombre())){
                return enlaces.PATH_PANEL_REPORTE_7+"?faces-redirect=true";
            }else if("Reporte 8".equals(reporte.getNombre())){
                return enlaces.PATH_PANEL_REPORTE_8+"?faces-redirect=true";
            }else if("Reporte 9".equals(reporte.getNombre())){
                return enlaces.PATH_PANEL_REPORTE_9+"?faces-redirect=true";
            }else{
                return enlaces.PATH_ERROR+"?faces-redirect=true";
            }            

        } catch (Exception ex) {
            return null;
        }
    }
}
