/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractor;
import epn.edu.ec.modelo.AsistenciaAdolescente;
import epn.edu.ec.modelo.CAI;
import epn.edu.ec.modelo.ItemTaller;
import epn.edu.ec.modelo.RegistroAsistencia;
import epn.edu.ec.modelo.Taller;
import epn.edu.ec.modelo.UDI;
import epn.edu.ec.servicios.AsistenciaAdolescentesServicio;
import epn.edu.ec.servicios.CaiServicio;
import epn.edu.ec.servicios.ItemTallerServicio;
import epn.edu.ec.servicios.RegistroAsistenciaServicio;
import epn.edu.ec.servicios.TallerServicio;
import epn.edu.ec.servicios.UdiServicio;
import epn.edu.ec.utilidades.EnlacesPrograma;
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

    private Integer duracion;
    private String actividad;
    private String objetivoEspecifico;
    private String materiales;
    private String responsable;private 

    EnlacesPrograma enlaces;
    Taller tallerEditar;
    RegistroAsistencia registroAsistencia;
    UDI udi;
    CAI cai;
    

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

    int indiceTaller = 0;

    @PostConstruct
    public void init() {

        servicioTaller = new TallerServicio();
        servicioCai = new CaiServicio();
        servicioUdi = new UdiServicio();
        servicioRegistro = new RegistroAsistenciaServicio();
        servicioItemTaller = new ItemTallerServicio();
        servicioAsistencia= new AsistenciaAdolescentesServicio();
        
        tallerEditar = new Taller();
        enlaces= new EnlacesPrograma();
        registroAsistencia = new RegistroAsistencia();
        udi = new UDI();
        cai = new CAI();
        listaUdi = new ArrayList<>();
        listaCai = new ArrayList<>();

        listaItemsTaller = new ArrayList<>();
        listadoAsistencia= new ArrayList<>();

        if (isEsUzdi()) {
            tipoCentro = "UZDI";
            listaUdi = servicioUdi.listaUdi(); //muestro la lista de UDIs rescatadas de la base de datos
            
        } else {
            tipoCentro = "CAI";
            listaCai = servicioCai.listaCai(); //muestro la lista de CAIs rescatadas de la base de datos
        }
        
        //////////EN EL CASO DE QUE EL TALLER SE HAYA GUARDADO////////////////////
        Taller tallerAux = (Taller) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("taller_psicologia");

        if (tallerAux != null) {
            tallerEditar = tallerAux;
            
            if (tallerAux.getIdCai() != null) {
                tipoCentro = "CAI";
                listaCai = servicioCai.listaCai(); //muestro la lista de CAIs rescatadas de la base de datos
                cai = tallerEditar.getIdCai();
            }
            if (tallerAux.getIdUdi() != null) {
                tipoCentro = "UZDI";
                listaUdi = servicioUdi.listaUdi(); //muestro la lista de UDIs rescatadas de la base de 
                udi = tallerEditar.getIdUdi();
            }

            List<ItemTaller> itemsAux = servicioTaller.obtenerItemsPorTalleres(tallerEditar.getIdTaller());

            if (itemsAux != null) {

                listaItemsTaller = itemsAux;
                indiceTaller = 1;
            }
            
            obtenerRegistroAsistencia();
        }
    }

    public Taller getTallerEditar() {
        return tallerEditar;
    }

    public void setTallerEditar(Taller tallerEditar) {
        this.tallerEditar = tallerEditar;
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
            udi= new UDI();
            cai= new CAI();
            listaUdi = servicioUdi.listaUdi(); //muestro la lista de UDIs rescatadas de la base de 
        
        } else if ("CAI".equals(tipoCentro)) {
            esUzdi = false;
            udi= new UDI();
            cai= new CAI();
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

        if (udi.getUdi() != null) {

            for (UDI u : listaUdi) {
                if (u.getUdi().equals(udi.getUdi())) {
                    udi = u;
                    break;
                }
            }
            numeroParticipantes = servicioTaller.obtenerNumeroAdolescentePorUdi(udi);
        } else if (cai.getCai() != null) {
            for (CAI c : listaCai) {
                if (c.getCai().equals(cai.getCai())) {
                    cai = c;
                    break;
                }
            }
            numeroParticipantes = servicioTaller.obtenerNumeroAdolescentePorCai(cai);
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
    
    /**
     * ***************************Eventos********************************************
     */

    public void obtenerRegistroAsistencia() {

        if (tallerEditar == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "NO SE HA PODIDO CARGAR EL TALLER", "Aviso"));
        } 
        else {
        
            List<AsistenciaAdolescente> registroAux = servicioRegistro.listaAdolescentesInfractoresPorTaller(tallerEditar);
            
            if (registroAux == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "NO HAY ADOLESCENTES INFRACTORES EN EL " + tallerEditar.getIdTaller(), "Aviso"));
            } 
            else {
                if (registroAux.size() > 0) {
                    
                    
                    for(AsistenciaAdolescente a : registroAux){
                        AdolescenteInfractor adolescente= new AdolescenteInfractor();
                        adolescente=a.getIdAdolescenteInfractor();
                        listadoAsistencia.add(adolescente);
                        
                    }
                }
            }
        }
    }
    
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

    private void limpiarActividad(){
        duracion=null;
        actividad=null;
        materiales=null;
        objetivoEspecifico=null;
        responsable=null;
    }
    
    private void asignarUdiCai() {
        
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
            tallerEditar.setIdCai(null);
        
        } else if (cai.getIdCai() != null) {
            tallerEditar.setIdCai(cai);
            tallerEditar.setIdUdi(null);
        }
    }

    private Taller guardarTaller(){
    
        try{
            
            asignarUdiCai();
            tallerEditar.setNumeroTotalParticipantes(numeroParticipantes);
            tallerEditar.setIdTaller(tallerEditar.getIdTaller());
            Taller taller = servicioTaller.editarTaller(tallerEditar);

            if(taller!= null){
                return taller;
            }
            else{
                return null;
            }
            
        }catch(Exception e){
            return null;
        }
    }
    
    private void guardarItemsTaller(Taller tallerGuardado) {

        int itemsGuardados = 0;

        for (ItemTaller i : listaItemsTaller) {
            i.setIdItemTaller(i.getIdItemTaller());
            i.setIdTaller(tallerGuardado);
            servicioItemTaller.guardarItemTaller(i);
            itemsGuardados++;
        }

    }
    
    private void generarRegistroAsistencia(Taller taller) {

        if (taller.getIdUdi() == null && taller.getIdCai() == null) {
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR, AL GENERAR EL REGISTRO DE ASISTENCIA ", "Aviso"));
        } 
        else if (taller.getIdUdi() != null) {
        
            List<AdolescenteInfractor> registroAux = servicioRegistro.listaAdolescentesInfractoresPorUzdi(taller.getIdUdi());
            
            if (registroAux == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "NO HAY ADOLESCENTES INFRACTORES EN LA " + taller.getIdUdi().getUdi(), "Aviso"));
            } 
            else {         
                listadoAsistencia=registroAux;
                
            }
        } 
        else if (taller.getIdCai() != null) {
        
            List<AdolescenteInfractor> registroAux = servicioRegistro.listaAdolescentesInfractoresPorCai(taller.getIdCai());
            
            if (registroAux == null) {
                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "NO HAY ADOLESCENTES INFRACTORES EN LA " + taller.getIdCai().getCai(), "Aviso"));
            } 
            else {
                listadoAsistencia=registroAux;
                
            }
        }
    }

    private void guardarRegistroAsistencia(Taller taller) {

        try {
            if (taller != null && listadoAsistencia != null) {
                
                servicioRegistro.eliminarRegistroAsistencia(taller.getIdTaller());
                
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
                        if (asistenciaAdolescentes > 0 && asistenciaAdolescentes == listadoAsistencia.size()) {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO DE ASISTENCIA", "Aviso"));
                        }
                    }
                }
            }
            

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO DE ASISTENCIA", "ERROR"));
        }

    }
    
    public String editarRegistroTaller() {

        try {
            if (udi.getUdi() != null || cai.getCai() != null) {
                
                if (numeroParticipantes > 0) {

                    Taller tallerAux = guardarTaller();

                    if (tallerAux != null) {

                        if (tallerAux.getIdTaller() > 0) {

                            guardarItemsTaller(tallerAux);
                            generarRegistroAsistencia(tallerAux);
                            guardarRegistroAsistencia(tallerAux);
                            return enlaces.PATH_PANEL_PSICOLOGIA + "?faces-redirect=true";

                        } else {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL TALLER DE PSICOLOGÍA", "Aviso"));
                            return null;
                        }

                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL TALLER DE PSICOLOGÍA", "Aviso"));
                        return null;
                    }
                }
                else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "LA UDI O CAI SELECCIONADA NO CUENTA CON ADOLESCENTES INFRACTORES", "Aviso"));
                    return null;
                }
                
            }
            else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "NO HA SELECCIONADO UNA CAI O UDI PARA EL TALLER", "Aviso"));
                return null;
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL TALLER DE PSICOLOGÍA", "Aviso"));
            return null;
        }
        
    }

    public void guardarPDFAsistencia() {
        //retorna el path del archivo-->Retorna: "file:D:/User/Documents/NetBeansProjects/SistemaReeducacionAI/SistemaReeducacionAI/src/main/java/epn/edu/ec/reportes/RegistroAsistencia.jasper"
        String ruta = getClass().getClassLoader().getResource("/epn/edu/ec/reportes/RegistroAsistencia.jasper").toString();
        //elimino los 6 primeros caracteres, es decir elimino: "file:/", para obtener solo la ruta del archivo
        ruta = ruta.substring(6);

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("txtUDI", "REGISTRO DE ASISTENCIA " + tallerEditar.getIdUdi().getUdi());
        parametros.put("txtTema","TALLER:  "+ tallerEditar.getTema());

        try {

            File jasper = new File(ruta);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(listadoAsistencia));

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
