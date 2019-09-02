package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractor;
import epn.edu.ec.modelo.AsistenciaAdolescente;
import epn.edu.ec.modelo.CAI;
import epn.edu.ec.modelo.ItemTaller;
import epn.edu.ec.modelo.RegistroAsistencia;
import epn.edu.ec.modelo.Taller;
import epn.edu.ec.modelo.UDI;
import epn.edu.ec.modelo.Usuario;
import epn.edu.ec.servicios.AsistenciaAdolescentesServicio;
import epn.edu.ec.servicios.CaiServicio;
import epn.edu.ec.servicios.ItemTallerServicio;
import epn.edu.ec.servicios.RegistroAsistenciaServicio;
import epn.edu.ec.servicios.TallerServicio;
import epn.edu.ec.servicios.UdiServicio;
import epn.edu.ec.utilidades.PermisosUsuario;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Named(value = "tallerControlador")
@ViewScoped
public class TallerControlador implements Serializable {

    //Datos de sesion
    private Usuario usuarioLogin;
    private String tipoTaller;

    private Integer duracion;
    private String actividad;
    private String objetivoEspecifico;
    private String materiales;
    private String responsable;
    
    //Datos de taller del tema
    private String tematicaTaller;
    
    Taller tallerCrear;
    RegistroAsistencia registroAsistencia;
    UDI udi;
    CAI cai;
//    UDI udiAux;
//    CAI caiAux;

    List<UDI> listaUdi;
    List<CAI> listaCai;
    List<ItemTaller> listaItemsTaller;
    List<AdolescenteInfractor> listadoAsistencia;

    TallerServicio servicioTaller;
    CaiServicio servicioCai;
    UdiServicio servicioUdi;
    ItemTallerServicio servicioItemTaller;
    RegistroAsistenciaServicio servicioRegistro;
    AsistenciaAdolescentesServicio servicioAsistencia;

    String tipoCentro;
    boolean esUzdi;
    Integer numeroParticipantes;
    boolean esTecnico;
    boolean esTecnicoCAI;
    boolean esTecnicoUDI;

    boolean tallerGuardado = false;
    int indiceTaller = 0;
    
    private ItemTaller item;
    private PermisosUsuario permisos;
    
    @PostConstruct
    public void init() {

        //inicializar datos de sesion
        usuarioLogin = new Usuario();
        tipoTaller = "";
        usuarioLogin = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
        tipoTaller = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tipoTaller");

        servicioTaller = new TallerServicio();
        servicioCai = new CaiServicio();
        servicioUdi = new UdiServicio();
        servicioRegistro = new RegistroAsistenciaServicio();
        servicioItemTaller = new ItemTallerServicio();
        servicioAsistencia = new AsistenciaAdolescentesServicio();

        tallerCrear = new Taller();
        registroAsistencia = new RegistroAsistencia();
        udi = new UDI();
        cai = new CAI();
  //      udiAux = new UDI();
  //      caiAux = new CAI();
        listaUdi = new ArrayList<>();
        listaCai = new ArrayList<>();

        listaItemsTaller = new ArrayList<>();
        listadoAsistencia = new ArrayList<>();
        
        permisos= new PermisosUsuario();
        item = new ItemTaller();

        if (isEsUzdi()) {
            tipoCentro = "UZDI";
            listaUdi = servicioUdi.listaUdi(); //muestro la lista de UDIs rescatadas de la base de datos

        } else {
            tipoCentro = "CAI";
            listaCai = servicioCai.listaCai(); //muestro la lista de CAIs rescatadas de la base de datos
        }

        String rol = permisos.RolUsuario();
        if (rol != null) {
            if ("ADMINISTRADOR".equals(rol) || "SUBDIRECTOR".equals(rol) || "LIDER UZDI".equals(rol) || "COORDINADOR CAI".equals(rol) || "DIRECTOR TECNICO DE MEDIDAS NO PRIVATIVAS Y PREVENCIÓN".equals(rol) || "DIRECTOR TECNICO DE MEDIDAS PRIVATIVAS Y ATENCIÓN".equals(rol)) {
                esTecnico = false; //content-disable=true
                esTecnicoCAI=false;
                esTecnicoUDI=false;
                
                if(tipoTaller.equals("INSPECTOR EDUCADOR")){
                    esTecnico=true; //content-disable=true
                    esTecnicoCAI=false; 
                    esTecnicoUDI=true;
                    tipoCentro = "CAI";
                    listaCai = servicioCai.listaCai();
                }
                
            } else {
                esTecnico = true; //content-disable=true
                

                if ("EQUIPO TECNICO PSICOLOGO UZDI".equals(rol) || "EQUIPO TECNICO JURIDICO UZDI".equals(rol) || "TRABAJADOR SOCIAL UZDI".equals(rol)) {
                    
                    esTecnicoCAI=true; 
                    esTecnicoUDI=true;
                    tipoCentro = "UZDI";
                    listaUdi = servicioUdi.listaUdi();
                    udi = usuarioLogin.getIdRolUsuarioCentro().getIdUdi();
    //                udiAux = udi;

                } else if ("EQUIPO TECNICO PSICOLOGO CAI".equals(rol) || "EQUIPO TECNICO JURIDICO CAI".equals(rol) || "INSPECTOR EDUCADOR".equals(rol) || "TRABAJADOR SOCIAL CAI".equals(rol)) {
                    esTecnicoCAI=true; 
                    esTecnicoUDI=true;
                    tipoCentro = "CAI";
                    listaCai = servicioCai.listaCai();
                    cai = usuarioLogin.getIdRolUsuarioCentro().getIdCai();
    //                caiAux = cai;
                }
            }
        }
    }

    public Taller getTallerCrear() {
        return tallerCrear;
    }

    public void setTallerCrear(Taller tallerCrear) {
        this.tallerCrear = tallerCrear;
    }

    public TallerServicio getServicioTaller() {
        return servicioTaller;
    }

    public String getTipoCentro() {
        return tipoCentro;
    }

    public void setTipoCentro(String tipoCentro) {
        this.tipoCentro = tipoCentro;

        if ("UZDI".equals(tipoCentro)) {
            esUzdi = true;
            listaUdi = servicioUdi.listaUdi(); //muestro la lista de UDIs rescatadas de la base de 

        } else if ("CAI".equals(tipoCentro)) {
            esUzdi = false;
            listaCai = servicioCai.listaCai(); //muestro la lista de CAIs rescatadas de la base de datos
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

    public CaiServicio getServicioCai() {
        return servicioCai;
    }

    public UdiServicio getServicioUdi() {
        return servicioUdi;
    }

    public Integer getNumeroParticipantes() {

        if (tipoCentro.equals("UZDI")) {
            cai = new CAI();
        } else if (tipoCentro.equals("CAI")) {
            udi = new UDI();
        }

        if (udi.getUdi() != null) {

            for (UDI u : listaUdi) {
                if (u.getUdi().equals(udi.getUdi())) {
                    udi = u;
      //              udiAux = u;
                    break;
                }
            }
            numeroParticipantes = servicioTaller.obtenerNumeroAdolescentePorUdi(udi);
//            udi = new UDI();

        } else if (cai.getCai() != null) {
            for (CAI c : listaCai) {
                if (c.getCai().equals(cai.getCai())) {
                    cai = c;
      //              caiAux = c;
                    break;
                }
            }
            numeroParticipantes = servicioTaller.obtenerNumeroAdolescentePorCai(cai);
//            cai = new CAI();
        } else {
            numeroParticipantes = 0;
        }
        return numeroParticipantes;
    }

    public void setNumeroParticipantes(Integer numeroParticipantes) {
        this.numeroParticipantes = numeroParticipantes;
    }

    public RegistroAsistenciaServicio getServicioRegistro() {
        return servicioRegistro;
    }

    public List<ItemTaller> getListaItemsTaller() {
        return listaItemsTaller;
    }

    public void setListaItemsTaller(List<ItemTaller> listaItemsTaller) {
        this.listaItemsTaller = listaItemsTaller;
    }

    public ItemTallerServicio getServicioItemTaller() {
        return servicioItemTaller;
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

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
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

    public List<AdolescenteInfractor> getListadoAsistencia() {
        return listadoAsistencia;
    }

    public void setListadoAsistencia(List<AdolescenteInfractor> listadoAsistencia) {
        this.listadoAsistencia = listadoAsistencia;
    }

    public Usuario getUsuarioLogin() {
        return usuarioLogin;
    }

    public boolean isEsTecnico() {
        return esTecnico;
    }

    public void setEsTecnico(boolean esTecnico) {
        this.esTecnico = esTecnico;
    }

    public String getTipoTaller() {
        return tipoTaller;
    }

    public String getTematicaTaller() {
        return tematicaTaller;
    }

    public void setTematicaTaller(String tematicaTaller) {
        this.tematicaTaller = tematicaTaller;
    }

    public ItemTaller getItem() {
        return item;
    }

    public void setItem(ItemTaller item) {
        this.item = item;
    }

    public boolean isEsTecnicoCAI() {
        return esTecnicoCAI;
    }

    public boolean isEsTecnicoUDI() {
        return esTecnicoUDI;
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

        limpiarActividad();
    }

    private void limpiarActividad() {
        duracion = null;
        actividad = null;
        materiales = null;
        objetivoEspecifico = null;
        responsable = null;
    }

    private void asignarUdiCai() {

        if (udi.getIdUdi() != null) {

            tallerCrear.setIdUdi(udi);
            tallerCrear.setIdCai(null);

        } else if (cai.getIdCai() != null) {
            tallerCrear.setIdCai(cai);
            tallerCrear.setIdUdi(null);
        }
    }

    private Taller guardarTaller() {

        try {
            if (usuarioLogin != null) {
                asignarUdiCai();
                tallerCrear.setNumeroTotalParticipantes(numeroParticipantes);
                tallerCrear.setIdUsuario(usuarioLogin);
                tallerCrear.setTipo(tipoTaller);
                Taller taller = servicioTaller.guardarTaller(tallerCrear);

                if (taller != null) {
                    return taller;
                } else {
                    return null;
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }

    }

    private void guardarItemsTaller(Taller tallerGuardado) {

        int itemsGuardados = 0;

        for (ItemTaller i : listaItemsTaller) {

            i.setIdTaller(tallerGuardado);
            servicioItemTaller.guardarItemTaller(i);
            itemsGuardados++;
        }

    }

    private void generarRegistroAsistencia(Taller taller) {

        if (taller.getIdUdi() == null && taller.getIdCai() == null) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR, AL GENERAR EL REGISTRO DE ASISTENCIA ", "Aviso"));
        } else if (taller.getIdUdi() != null) {

            List<AdolescenteInfractor> registroAux = servicioRegistro.listaAdolescentesInfractoresPorUzdi(taller.getIdUdi());

            if (registroAux == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "NO HAY ADOLESCENTES INFRACTORES EN LA " + taller.getIdUdi().getUdi(), "Aviso"));
            } else {
                listadoAsistencia = registroAux;

            }
        } else if (taller.getIdCai() != null) {

            List<AdolescenteInfractor> registroAux = servicioRegistro.listaAdolescentesInfractoresPorCai(taller.getIdCai());

            if (registroAux == null) {
                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "NO HAY ADOLESCENTES INFRACTORES EN LA " + taller.getIdCai().getCai(), "Aviso"));
            } else {
                listadoAsistencia = registroAux;

            }
        }
    }

    private void guardarRegistroAsistencia(Taller taller) {

        try {
            if (taller != null && listadoAsistencia != null) {

                registroAsistencia.setIdTaller(taller);
                RegistroAsistencia registroAsistenciaAux = servicioRegistro.guardarRegistroAsistencia(registroAsistencia);

                if (registroAsistenciaAux != null) {

                    int asistenciaAdolescentes = 0;

                    if (listadoAsistencia.size() > 0) {

                        for (AdolescenteInfractor adolescente : listadoAsistencia) {

                            AsistenciaAdolescente asistencia = new AsistenciaAdolescente();
                            asistencia.setIdAdolescenteInfractor(adolescente);
                            asistencia.setIdRegistroAsistencia(registroAsistenciaAux);
                            asistencia.setAsistio(false);
                            servicioAsistencia.guardarRegistroAsistenciaAdolescente(asistencia);
                            asistenciaAdolescentes++;
                        }
                    }
                }
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO DE ASISTENCIA", "ERROR"));
        }

    }

    private void asignarListadoRegistroAsistencia() {

        for (AdolescenteInfractor a : listadoAsistencia) {

            if (a.getDocumento() != null) {
                a.setCedula(a.getDocumento());
            }
        }
    }

    public void guardarRegistroTaller() {

        try {

            if (udi.getUdi() != null || cai.getCai() != null) {

                if (numeroParticipantes > 0) {
                    Taller tallerAux = guardarTaller();

                    if (tallerAux != null) {

                        if (tallerAux.getIdTaller() > 0) {

                            guardarItemsTaller(tallerAux);
                            generarRegistroAsistencia(tallerAux);
                            guardarRegistroAsistencia(tallerAux);
                            asignarListadoRegistroAsistencia();
                            tallerGuardado = true;
                            tematicaTaller=tallerAux.getTema();
                            //tallerCrear=tallerAux;
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL TALLER", "Información"));

                        } else {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL TALLER DE PSICOLOGÍA", "Aviso"));
                        }

                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL TALLER DE PSICOLOGÍA", "Aviso"));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "LA UDI O CAI SELECCIONADA NO CUENTA CON ADOLESCENTES INFRACTORES", "Aviso"));
                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "NO HA SELECCIONADO UNA CAI O UDI PARA EL TALLER", "Aviso"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL TALLER DE PSICOLOGÍA", "Aviso"));
        }

    }

    public void guardarPDFAsistencia() {

        String ruta = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/reportes/RegistroAsistencia.jasper");
        
        Map<String, Object> parametros = new HashMap<>();

        String udiRescatado = tallerCrear.getIdUdi().getUdi();
        String caiRescatado = tallerCrear.getIdCai().getCai();
        if (udiRescatado != null && caiRescatado == null) {
            parametros.put("txtCentro", "REGISTRO DE ASISTENCIA " + udiRescatado);
        } else if (udiRescatado == null && caiRescatado != null) {
            parametros.put("txtCentro", "REGISTRO DE ASISTENCIA " + caiRescatado);
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss z uuuu").withLocale(Locale.US);
        ZonedDateTime zdt = ZonedDateTime.parse(tallerCrear.getFecha().toString(), dtf);
        LocalDate ld = zdt.toLocalDate();

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fecha = ld.format(fmt);

        parametros.put("txtTema", "TALLER:  " + tallerCrear.getTema());
        parametros.put("txtFecha", "FECHA DE REALIZACIÓN:  " + fecha);
        
        String rutaImagen = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/reportes/logo_ministerio.png");

        parametros.put("imgBackground", rutaImagen);

        try {

            List<AdolescenteInfractor> asistencia = new ArrayList<>();
            for (AdolescenteInfractor a : listadoAsistencia) {
                if (a.getDocumento() != null) {
                    a.setCedula(a.getDocumento());
                }
                asistencia.add(a);
            }

            File jasper = new File(ruta);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(asistencia));
            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();
            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;
                hsr.setContentType("application/pdf");
                hsr.addHeader("Content-disposition", "attachment; filename=Registro_Asistencia.pdf");
                try {
                    ServletOutputStream stream = hsr.getOutputStream();
                    JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                    stream.flush();
                } catch (IOException ex) {
                    System.out.println("Error:  " + ex.getMessage());
                }
                context.responseComplete();
            }

        } catch (JRException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "NO SE HA GENERADO EL REGISTRO DE ASISTENCIA", "ERROR"));
        }
    }

    public void obtenerItem(ItemTaller item){
        this.item=item;
    }
    
    public void agregarActividadEditada() {

        ItemTaller itemAux = new ItemTaller();
        itemAux.setDuracion(duracion);
        itemAux.setActividad(actividad);
        itemAux.setMateriales(materiales);
        itemAux.setObjetivoEspecifico(objetivoEspecifico);
        itemAux.setResponsable(responsable);

        limpiarActividad();
    }
    
    public void quitarItem(ItemTaller itemSeleccionado){
        this.item=itemSeleccionado;
        listaItemsTaller.remove(itemSeleccionado);
    }
}
