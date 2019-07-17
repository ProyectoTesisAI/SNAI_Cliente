/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.modelo.CAI;
import epn.edu.ec.modelo.ItemTallerPsicologia;
import epn.edu.ec.modelo.RegistroAsistencia;
import epn.edu.ec.modelo.RegistroAsistenciaAdolescenteCAI;
import epn.edu.ec.modelo.RegistroAsistenciaAdolescenteUDI;
import epn.edu.ec.modelo.TallerPsicologia;
import epn.edu.ec.modelo.UDI;
import epn.edu.ec.servicios.CaiServicio;
import epn.edu.ec.servicios.ItemTallerPsicologiaServicio;
import epn.edu.ec.servicios.RegistroAsistenciaAdolescenteCAIServicio;
import epn.edu.ec.servicios.RegistroAsistenciaAdolescenteUDIServicio;
import epn.edu.ec.servicios.RegistroAsistenciaServicio;
import epn.edu.ec.servicios.TallerPsicologiaServicio;
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
@Named(value = "tallerPsicologiaEditarControlador")
@ViewScoped
public class TallerPsicologiaEditarControlador implements Serializable {

    //////////variables usadas para la edicion de los item de las actividades
    private Date hora;
    private String actividad;
    private String objetivoEspecifico;
    private String materiales;
    private String responsable;

    //////////variables usadas para el Taller Psicologia/////////
    TallerPsicologia tallerPsicologiaEditar;
    UDI udi;
    CAI cai;

    List<UDI> listaUdi;
    List<CAI> listaCai;

    List<ItemTallerPsicologia> listaItemsTallerPsicologia;

    String tipoCentro;
    boolean esUzdi;
    Integer numeroParticipantes;
    boolean tabla;

    TallerPsicologiaServicio controlador;
    CaiServicio controladorCai;
    UdiServicio controladorUdi;
    ItemTallerPsicologiaServicio controladroItemTaller;

    //////////////////////////////////////////variables usadas para el Registro Asistencia////////
    List<AdolescenteInfractorUDI> listaAdolescentesUzdi;
    List<AdolescenteInfractorCAI> listaAdolescentesCai;
    List<String> listaRegistroAsistencia;
    RegistroAsistencia registroAsistencia;

    RegistroAsistenciaServicio controladorAsistencia;
    RegistroAsistenciaAdolescenteUDIServicio controladorAsistenciaUDI;
    RegistroAsistenciaAdolescenteCAIServicio controladorAsistenciaCAI;
    ///////////////////////////////////////////////////

    boolean tallerGuardado = false;

    int indiceTaller = 0;

    @PostConstruct
    public void init() {

        controlador = new TallerPsicologiaServicio();
        controladorCai = new CaiServicio();
        controladorUdi = new UdiServicio();
        controladorAsistencia = new RegistroAsistenciaServicio();
        controladroItemTaller = new ItemTallerPsicologiaServicio();
        controladorAsistenciaUDI = new RegistroAsistenciaAdolescenteUDIServicio();
        controladorAsistenciaCAI = new RegistroAsistenciaAdolescenteCAIServicio();

        tallerPsicologiaEditar = new TallerPsicologia();
        registroAsistencia = new RegistroAsistencia();
        udi = new UDI();
        cai = new CAI();
        listaCai = new ArrayList<>();
        listaUdi = new ArrayList<>();
        listaAdolescentesUzdi = new ArrayList<>();
        listaAdolescentesCai = new ArrayList<>();
        listaRegistroAsistencia = new ArrayList<>();

        listaItemsTallerPsicologia = new ArrayList<>();

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
        TallerPsicologia tallerAux = (TallerPsicologia) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("taller_psicologia");

        if (tallerAux != null) {
            tallerPsicologiaEditar = tallerAux;
            tallerGuardado = true;

            if (tallerAux.getIdCai() != null) {
                tipoCentro = "CAI";
                listaCai = controladorCai.listaCai(); //muestro la lista de UDIs rescatadas de la base de datos
                cai = tallerPsicologiaEditar.getIdCai();
            }
            if (tallerAux.getIdUdi() != null) {
                tipoCentro = "UZDI";
                listaUdi = controladorUdi.listaUdi(); //muestro la lista de UDIs rescatadas de la base de datos
                udi = tallerPsicologiaEditar.getIdUdi();
            }

            List<ItemTallerPsicologia> itemsAux = controlador.obtenerItemsPorTalleresPsicologia(tallerPsicologiaEditar.getIdTallerPsicologia());

            if (itemsAux != null) {

                listaItemsTallerPsicologia = itemsAux;
                indiceTaller = 1;
            }
            List<RegistroAsistenciaAdolescenteUDI> registroAsistenciaUDIAux = new ArrayList<>();
            List<RegistroAsistenciaAdolescenteCAI> registroAsistenciaCAIAux = new ArrayList<>();
            if (udi.getIdUdi() != null) {
                registroAsistenciaUDIAux = controladorAsistencia.listaAdolescentesInfractoresPorTaller(tallerAux);
                if (registroAsistenciaUDIAux != null) {
                    List<AdolescenteInfractorUDI> listaAsistencia = new ArrayList<>();
                    for (RegistroAsistenciaAdolescenteUDI asis : registroAsistenciaUDIAux) {
                        AdolescenteInfractorUDI a = asis.getIdAdolescenteUdi();
                        listaAsistencia.add(a);
                    }
                    listaAdolescentesUzdi = listaAsistencia;
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AÚN NO HA GUARDADO EL REGISTRO DE ASISTENCIA UDI", "Aviso"));
                }
            } else if (cai.getIdCai() != null) {
                registroAsistenciaCAIAux = controladorAsistencia.listaAdolescentesInfractoresPorTallerCAI(tallerAux);
                if (registroAsistenciaCAIAux != null) {
                    List<AdolescenteInfractorCAI> listaAsistencia = new ArrayList<>();
                    for (RegistroAsistenciaAdolescenteCAI asis : registroAsistenciaCAIAux) {
                        AdolescenteInfractorCAI a = asis.getIdAdolescenteCai();
                        listaAsistencia.add(a);
                    }
                    listaAdolescentesCai = listaAsistencia;
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AÚN NO HA GUARDADO EL REGISTRO DE ASISTENCIA CAI", "Aviso"));
                }
            }
        }
    }

    public TallerPsicologia getTallerPsicologiaEditar() {
        return tallerPsicologiaEditar;
    }

    public void setTallerPsicologiaEditar(TallerPsicologia tallerPsicologiaEditar) {
        this.tallerPsicologiaEditar = tallerPsicologiaEditar;
    }

    public TallerPsicologiaServicio getControlador() {
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

    public List<ItemTallerPsicologia> getListaItemsTallerPsicologia() {
        return listaItemsTallerPsicologia;
    }

    public void setListaItemsTallerPsicologia(List<ItemTallerPsicologia> listaItemsTallerPsicologia) {
        this.listaItemsTallerPsicologia = listaItemsTallerPsicologia;
    }

    public ItemTallerPsicologiaServicio getControladroItemTaller() {
        return controladroItemTaller;
    }

    public boolean isTallerGuardado() {
        return tallerGuardado;
    }

    public void setTallerGuardado(boolean tallerGuardado) {
        this.tallerGuardado = tallerGuardado;
    }

    public RegistroAsistenciaAdolescenteUDIServicio getControladorAsistenciaUDI() {
        return controladorAsistenciaUDI;
    }

    public void setControladorAsistenciaUDI(RegistroAsistenciaAdolescenteUDIServicio controladorAsistenciaUDI) {
        this.controladorAsistenciaUDI = controladorAsistenciaUDI;
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

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
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

        ItemTallerPsicologia itemAux = new ItemTallerPsicologia();
        itemAux.setHora(hora);
        itemAux.setActividad(actividad);
        itemAux.setMateriales(materiales);
        itemAux.setObjetivoEspecifico(objetivoEspecifico);
        itemAux.setResponsable(responsable);

        listaItemsTallerPsicologia.add(itemAux);
        
        hora=null;
        actividad=null;
        materiales=null;
        objetivoEspecifico=null;
        responsable=null;
    }

    public String editarTallerPsicologia() {

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
                tallerPsicologiaEditar.setIdUdi(udi);
            } else {
                tallerPsicologiaEditar.setIdUdi(null);
            }
            if (cai.getIdCai() != null) {
                tallerPsicologiaEditar.setIdCai(cai);
            } else {
                tallerPsicologiaEditar.setIdCai(null);
            }
            tallerPsicologiaEditar.setNumeroTotalParticipantes(numeroParticipantes);
            tallerPsicologiaEditar.setIdTallerPsicologia(tallerPsicologiaEditar.getIdTallerPsicologia());
            TallerPsicologia taller = controlador.editarTallerPsicologia(tallerPsicologiaEditar);

            if (taller != null) {
                for (ItemTallerPsicologia i : listaItemsTallerPsicologia) {
                    i.setIdItemTallerPsicologia(i.getIdItemTallerPsicologia());
                    i.setIdTallerPsicologia(taller);
                    controladroItemTaller.guardarItemTallerPsicologia(i);
                    itemsGuardados++;

                }

                if (itemsGuardados > 0 && itemsGuardados == listaItemsTallerPsicologia.size()) {

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

    public void generarRegistroAsistencia(TallerPsicologia tallerPsicologia) {

        if (tallerPsicologia.getIdUdi() == null && tallerPsicologia.getIdCai() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "NO SE HA SELECCIONADO UNIDAD ZONAL ", "Aviso"));
        } else if (tallerPsicologia.getIdUdi() != null) {
            List<AdolescenteInfractorUDI> registroAux = controladorAsistencia.listaAdolescentesInfractoresPorUzdi(tallerPsicologia.getIdUdi());
            if (registroAux == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "NO HAY ADOLESCENTES INFRACTORES EN LA " + tallerPsicologia.getIdUdi().getUdi(), "Aviso"));
            } else {
                if (registroAux.size() > 0) {
                    listaAdolescentesUzdi = registroAux;
                }
            }
        } else if (tallerPsicologia.getIdCai() != null) {
            List<AdolescenteInfractorCAI> registroAux = controladorAsistencia.listaAdolescentesInfractoresPorCai(tallerPsicologia.getIdCai());
            if (registroAux == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "NO HAY ADOLESCENTES INFRACTORES EN LA " + tallerPsicologia.getIdCai().getCai(), "Aviso"));
            } else {
                if (registroAux.size() > 0) {
                    listaAdolescentesCai = registroAux;
                }
            }
        }
    }

    public void guardarRegistroAsistencia(TallerPsicologia tallerPsicologia) {

        try {
            generarRegistroAsistencia(tallerPsicologia);
            Integer idRegistroTaller = tallerPsicologia.getIdTallerPsicologia();
            Integer respuestaEliminado = controladorAsistencia.eliminarListadoAsistencia(idRegistroTaller);

            registroAsistencia.setTematica(tallerPsicologia.getTema());
            registroAsistencia.setFecha(tallerPsicologia.getFecha());
            registroAsistencia.setIdRegistroAsistencia(tallerPsicologia);
            RegistroAsistencia registroAsistenciaAux = controladorAsistencia.guardarRegistroAsistencia(registroAsistencia);

            if (respuestaEliminado == 200 || respuestaEliminado == 204) {
                if (registroAsistenciaAux != null) {
                    int asistenciaAdolescentes = 0;
                    if (listaAdolescentesUzdi != null && listaAdolescentesUzdi.size()>0 && listaAdolescentesCai.size()==0) {
                        for (AdolescenteInfractorUDI au : listaAdolescentesUzdi) {
                            RegistroAsistenciaAdolescenteUDI asistencia = new RegistroAsistenciaAdolescenteUDI();
                            asistencia.setIdAdolescenteUdi(au);
                            asistencia.setIdRegistroAsistencia(registroAsistenciaAux);
                            controladorAsistenciaUDI.guardarRegistroAsistenciaAdolescenteUDI(asistencia);
                            asistenciaAdolescentes++;
                        }
                        if (asistenciaAdolescentes > 0 && asistenciaAdolescentes == listaAdolescentesUzdi.size()) {
                            tallerGuardado = true;
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO DE ASISTENCIA", "Aviso"));
                        }
                    } else if (listaAdolescentesUzdi.size() == 0 && listaAdolescentesCai != null & listaAdolescentesCai.size()>0) {
                        for (AdolescenteInfractorCAI ac : listaAdolescentesCai) {
                            RegistroAsistenciaAdolescenteCAI asistencia = new RegistroAsistenciaAdolescenteCAI();
                            asistencia.setIdAdolescenteCai(ac);
                            asistencia.setIdRegistroAsistencia(registroAsistenciaAux);
                            controladorAsistenciaCAI.guardarRegistroAsistenciaAdolescenteCAI(asistencia);
                            asistenciaAdolescentes++;
                        }
                        if (asistenciaAdolescentes > 0 && asistenciaAdolescentes == listaAdolescentesCai.size()) {
                            tallerGuardado = true;
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO DE ASISTENCIA", "Aviso"));
                        }
                    }
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
        parametros.put("txtUDI", "REGISTRO DE ASISTENCIA " + tallerPsicologiaEditar.getIdUdi().getUdi());

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

            /*JasperExportManager.exportReportToPdfFile(jasperPrint, "D:\\User\\Desktop\\Registro Asistencia "+tallerPsicologia.getIdUdi().getUdi()+".pdf"); // 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GENERADO CORRECTAMENTE EL REGISTRO DE ASISTENCIA ","Aviso" ));*/
        } catch (Exception e) {
            System.out.println("Error:  " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "NO SE HA GENERADO EL REGISTRO DE ASISTENCIA", "ERROR"));
        }
    }
}
