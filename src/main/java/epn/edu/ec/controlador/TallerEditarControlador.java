/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.modelo.CAI;
import epn.edu.ec.modelo.ItemTaller;
import epn.edu.ec.modelo.RegistroAsistencia;
import epn.edu.ec.modelo.Taller;
import epn.edu.ec.modelo.UDI;
import epn.edu.ec.servicios.CaiServicio;
import epn.edu.ec.servicios.ItemTallerServicio;
import epn.edu.ec.servicios.RegistroAsistenciaServicio;
import epn.edu.ec.servicios.TallerServicio;
import epn.edu.ec.servicios.UdiServicio;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author User
 */
@Named(value = "tallerEditarControlador")
@ViewScoped
public class TallerEditarControlador implements Serializable {

    //////////variables usadas para la edicion de los item de las actividades
    private int duracion;
    private String actividad;
    private String objetivoEspecifico;
    private String materiales;
    private String responsable;

    //////////variables usadas para el Taller /////////
    Taller tallerEditar;
    UDI udi;
    CAI cai;

    List<UDI> listaUdi;
    List<CAI> listaCai;

    List<ItemTaller> listaItemsTaller;

    String tipoCentro;
    boolean esUzdi;
    Integer numeroParticipantes;
    boolean tabla;

    TallerServicio controlador;
    CaiServicio controladorCai;
    UdiServicio controladorUdi;
    ItemTallerServicio controladroItemTaller;

    //////////////////////////////////////////variables usadas para el Registro Asistencia////////
    List<AdolescenteInfractorUDI> listaAdolescentesUzdi;
    List<AdolescenteInfractorCAI> listaAdolescentesCai;
    List<String> listaRegistroAsistencia;
    RegistroAsistencia registroAsistencia;

    RegistroAsistenciaServicio controladorAsistencia;
    ///////////////////////////////////////////////////

    boolean tallerGuardado = false;

    int indiceTaller = 0;

    @PostConstruct
    public void init() {

        controlador = new TallerServicio();
        controladorCai = new CaiServicio();
        controladorUdi = new UdiServicio();
        controladorAsistencia = new RegistroAsistenciaServicio();
        controladroItemTaller = new ItemTallerServicio();

        tallerEditar = new Taller();
        registroAsistencia = new RegistroAsistencia();
        udi = new UDI();
        cai = new CAI();
        listaCai = new ArrayList<>();
        listaUdi = new ArrayList<>();
        listaAdolescentesUzdi = new ArrayList<>();
        listaAdolescentesCai = new ArrayList<>();
        listaRegistroAsistencia = new ArrayList<>();

        listaItemsTaller = new ArrayList<>();

        if (isEsUzdi()) {
            tipoCentro = "UZDI";
            listaUdi = controladorUdi.listaUdi(); //muestro la lista de UDIs rescatadas de la base de datos
            udi = new UDI();
            cai = new CAI();
        } else if (isEsUzdi() == false) {
            tipoCentro = "CAI";
            listaCai = controladorCai.listaCai(); //muestro la lista de CAIs rescatadas de la base de datos
            udi = new UDI();
            cai = new CAI();
        }

        //////////EN EL CASO DE QUE EL TALLER SE HAYA GUARDADO////////////////////
        Taller tallerAux = (Taller) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("taller_psicologia");

        if (tallerAux != null) {
            tallerEditar = tallerAux;
            tallerGuardado = true;

            if (tallerAux.getIdCai() != null) {
                tipoCentro = "CAI";
                listaCai = controladorCai.listaCai(); //muestro la lista de UDIs rescatadas de la base de datos
                cai = tallerEditar.getIdCai();
            }
            if (tallerAux.getIdUdi() != null) {
                tipoCentro = "UZDI";
                listaUdi = controladorUdi.listaUdi(); //muestro la lista de UDIs rescatadas de la base de datos
                udi = tallerEditar.getIdUdi();
            }

            List<ItemTaller> itemsAux = controlador.obtenerItemsPorTalleres(tallerEditar.getIdTaller());

            if (itemsAux != null) {

                listaItemsTaller = itemsAux;
                indiceTaller = 1;
            }
            
        }
    }

    public Taller getTallerEditar() {
        return tallerEditar;
    }

    public void setTallerEditar(Taller tallerEditar) {
        this.tallerEditar = tallerEditar;
    }

    public TallerServicio getControlador() {
        return controlador;
    }

    public String getTipoCentro() {
        return tipoCentro;
    }

    public void setTipoCentro(String tipoCentro) {
        this.tipoCentro = tipoCentro;
        if ("UZDI".equals(tipoCentro)) {
            System.out.println("Ha seleccionado UZDI");
            esUzdi = true;
            udi = new UDI();
            cai = new CAI();
            listaUdi = controladorUdi.listaUdi(); //muestro la lista de UDIs rescatadas de la base de datos
        } else if ("CAI".equals(tipoCentro)) {
            System.out.println("Ha seleccionado CAI");
            esUzdi = false;
            udi = new UDI();
            cai = new CAI();
            listaCai = controladorCai.listaCai(); //muestro la lista de CAIs rescatadas de la base de datos
        }
    }

    public boolean isEsUzdi() {
        if ("UZDI".equals(tipoCentro)) {
            esUzdi = true;

        } else if ("CAI".equals(tipoCentro)) {
            esUzdi = false;
        }
        return esUzdi;
    }

    public List<UDI> getListaUdi() {
        return listaUdi;
    }

    public void setListaUdi(List<UDI> listaUdi) {
        this.listaUdi = listaUdi;
    }

    public List<CAI> getListaCai() {
        return listaCai;
    }

    public void setListaCai(List<CAI> listaCai) {
        this.listaCai = listaCai;
    }

    public CaiServicio getControladorCai() {
        return controladorCai;
    }

    public UdiServicio getControladorUdi() {
        return controladorUdi;
    }

    public Integer getNumeroParticipantes() {
        if (udi.getUdi() != null) {
            for (UDI u : listaUdi) {
                if (u.getUdi().equals(udi.getUdi())) {
                    udi = u;
                    break;
                }
            }
            numeroParticipantes = controlador.obtenerNumeroAdolescentePorUdi(udi);
        } else if (cai.getCai() != null) {
            for (CAI c : listaCai) {
                if (c.getCai().equals(cai.getCai())) {
                    cai = c;
                    break;
                }
            }
            numeroParticipantes = controlador.obtenerNumeroAdolescentePorCai(cai);
        }
        return numeroParticipantes;
    }

    public void setNumeroParticipantes(Integer numeroParticipantes) {
        this.numeroParticipantes = numeroParticipantes;
    }

    public List<AdolescenteInfractorUDI> getListaAdolescentesUzdi() {
        return listaAdolescentesUzdi;
    }

    public void setListaAdolescentesUzdi(List<AdolescenteInfractorUDI> listaAdolescentesUzdi) {
        this.listaAdolescentesUzdi = listaAdolescentesUzdi;
    }

    public List<AdolescenteInfractorCAI> getListaAdolescentesCai() {
        return listaAdolescentesCai;
    }

    public void setListaAdolescentesCai(List<AdolescenteInfractorCAI> listaAdolescentesCai) {
        this.listaAdolescentesCai = listaAdolescentesCai;
    }

    public RegistroAsistenciaServicio getControladorAsistencia() {
        return controladorAsistencia;
    }

    public List<ItemTaller> getListaItemsTaller() {
        return listaItemsTaller;
    }

    public void setListaItemsTaller(List<ItemTaller> listaItemsTaller) {
        this.listaItemsTaller = listaItemsTaller;
    }

    public ItemTallerServicio getControladroItemTaller() {
        return controladroItemTaller;
    }

    public boolean isTallerGuardado() {
        return tallerGuardado;
    }

    public void setTallerGuardado(boolean tallerGuardado) {
        this.tallerGuardado = tallerGuardado;
    }


    public RegistroAsistencia getRegistroAsistencia() {
        return registroAsistencia;
    }

    public void setRegistroAsistencia(RegistroAsistencia registroAsistencia) {
        this.registroAsistencia = registroAsistencia;
    }

    public int getIndiceTaller() {
        return indiceTaller;
    }

    public void setIndiceTaller(int indiceTaller) {
        this.indiceTaller = indiceTaller;
    }

    public UDI getUdi() {
        return udi;
    }

    public void setUdi(UDI udi) {
        this.udi = udi;
    }

    public CAI getCai() {
        return cai;
    }

    public void setCai(CAI cai) {
        this.cai = cai;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setEsUzdi(boolean esUzdi) {
        this.esUzdi = esUzdi;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getObjetivoEspecifico() {
        return objetivoEspecifico;
    }

    public void setObjetivoEspecifico(String objetivoEspecifico) {
        this.objetivoEspecifico = objetivoEspecifico;
    }

    public String getMateriales() {
        return materiales;
    }

    public void setMateriales(String materiales) {
        this.materiales = materiales;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public boolean isTabla() {
        if (listaAdolescentesCai.size()==0) {
            tabla = true;

        } else if (listaAdolescentesUzdi.size()==0) {
            tabla = false;
        }
        return tabla;
    }

    public void setTabla(boolean tabla) {
        this.tabla = tabla;
    }
    
    /**
     * ***************************Eventos********************************************
     */
    public void agregarActividad() {

        ItemTaller itemAux = new ItemTaller();
        itemAux.setDuracion(duracion);
        itemAux.setActividad(actividad);
        itemAux.setMateriales(materiales);
        itemAux.setObjetivoEspecifico(objetivoEspecifico);
        itemAux.setResponsable(responsable);

        listaItemsTaller.add(itemAux);
        
        duracion=0;
        actividad=null;
        materiales=null;
        objetivoEspecifico=null;
        responsable=null;
    }

    public String editarTaller() {

        try {
            int itemsGuardados = 0;
            for (UDI u : listaUdi) {
                if (u.getUdi().equals(udi.getUdi())) {
                    udi = u;
                    break;
                }
            }
            for (CAI c : listaCai) {
                if (c.getCai().equals(cai.getCai())) {
                    cai = c;
                    break;
                }
            }
            if (udi.getIdUdi() != null) {
                tallerEditar.setIdUdi(udi);
            } else {
                tallerEditar.setIdUdi(null);
            }
            if (cai.getIdCai() != null) {
                tallerEditar.setIdCai(cai);
            } else {
                tallerEditar.setIdCai(null);
            }
            tallerEditar.setNumeroTotalParticipantes(numeroParticipantes);
            tallerEditar.setIdTaller(tallerEditar.getIdTaller());
            Taller taller = controlador.editarTaller(tallerEditar);

            if (taller != null) {
                for (ItemTaller i : listaItemsTaller) {
                    i.setIdItemTaller(i.getIdItemTaller());
                    i.setIdTaller(taller);
                    controladroItemTaller.guardarItemTaller(i);
                    itemsGuardados++;

                }

                if (itemsGuardados > 0 && itemsGuardados == listaItemsTaller.size()) {

                    indiceTaller = 1;
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("taller_psicologia", taller);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL TALLER DE PSICOLOGÍA", "Aviso"));

                    guardarRegistroAsistencia(taller);
                    return "/paginas/psicologia/taller_psicologia_ver.com?faces-redirect=true";
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL TALLER DE PSICOLOGÍA", "Aviso"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL TALLER DE PSICOLOGÍA", "Aviso"));
        }
        return null;
    }

    public void generarRegistroAsistencia(Taller taller) {

        if (taller.getIdUdi() == null && taller.getIdCai() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "NO SE HA SELECCIONADO UNIDAD ZONAL ", "Aviso"));
        } else if (taller.getIdUdi() != null) {
            List<AdolescenteInfractorUDI> registroAux = controladorAsistencia.listaAdolescentesInfractoresPorUzdi(taller.getIdUdi());
            if (registroAux == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "NO HAY ADOLESCENTES INFRACTORES EN LA " + taller.getIdUdi().getUdi(), "Aviso"));
            } else {
                if (registroAux.size() > 0) {
                    listaAdolescentesUzdi = registroAux;
                }
            }
        } else if (taller.getIdCai() != null) {
            List<AdolescenteInfractorCAI> registroAux = controladorAsistencia.listaAdolescentesInfractoresPorCai(taller.getIdCai());
            if (registroAux == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "NO HAY ADOLESCENTES INFRACTORES EN LA " + taller.getIdCai().getCai(), "Aviso"));
            } else {
                if (registroAux.size() > 0) {
                    listaAdolescentesCai = registroAux;
                }
            }
        }
    }

    public void guardarRegistroAsistencia(Taller taller) {

        try {
            generarRegistroAsistencia(taller);
            Integer idRegistroTaller = taller.getIdTaller();
            Integer respuestaEliminado = controladorAsistencia.eliminarListadoAsistencia(idRegistroTaller);

            registroAsistencia.setIdTaller(taller);
            RegistroAsistencia registroAsistenciaAux = controladorAsistencia.guardarRegistroAsistencia(registroAsistencia);

            if (respuestaEliminado == 200 || respuestaEliminado == 204) {
                if (registroAsistenciaAux != null) {
                    int asistenciaAdolescentes = 0;
                                   }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO DE ASISTENCIA", "ERROR"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO DE ASISTENCIA", "ERROR"));
        }
    }

    public void guardarPDFAsistencia() {
        //retorna el path del archivo-->Retorna: "file:D:/User/Documents/NetBeansProjects/SistemaReeducacionAI/SistemaReeducacionAI/src/main/java/epn/edu/ec/reportes/RegistroAsistencia.jasper"
        String ruta = getClass().getClassLoader().getResource("/epn/edu/ec/reportes/RegistroAsistencia.jasper").toString();
        //elimino los 6 primeros caracteres, es decir elimino: "file:/", para obtener solo la ruta del archivo
        ruta = ruta.substring(6);

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("txtUDI", "REGISTRO DE ASISTENCIA " + tallerEditar.getIdUdi().getUdi());

        try {

            File jasper = new File(ruta);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(this.getListaAdolescentesUzdi()));

            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();
            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;
                hsr.setContentType("application/pdf");
                hsr.addHeader("Content-disposition", "attachment; filename=jsfReporte.pdf");
                try {
                    ServletOutputStream stream = hsr.getOutputStream();
                    JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                    stream.flush();
                } catch (IOException ex) {
                    System.out.println("Error:  " + ex.getMessage());
                }
                context.responseComplete();
            }

            /*JasperExportManager.exportReportToPdfFile(jasperPrint, "D:\\User\\Desktop\\Registro Asistencia "+taller.getIdUdi().getUdi()+".pdf"); // 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GENERADO CORRECTAMENTE EL REGISTRO DE ASISTENCIA ","Aviso" ));*/
        } catch (Exception e) {
            System.out.println("Error:  " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "NO SE HA GENERADO EL REGISTRO DE ASISTENCIA", "ERROR"));
        }
    }
}
