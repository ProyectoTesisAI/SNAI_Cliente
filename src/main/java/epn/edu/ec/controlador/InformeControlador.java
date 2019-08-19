/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AsistenciaAdolescente;
import epn.edu.ec.modelo.Informe;
import epn.edu.ec.modelo.ItemTaller;
import epn.edu.ec.modelo.RegistroFotografico;
import epn.edu.ec.modelo.Taller;
import epn.edu.ec.servicios.AsistenciaAdolescentesServicio;
import epn.edu.ec.servicios.InformeServicio;
import epn.edu.ec.servicios.RegistroAsistenciaServicio;
import epn.edu.ec.servicios.RegistroFotograficoServicio;
import epn.edu.ec.servicios.TallerServicio;
import epn.edu.ec.utilidades.EnlacesPrograma;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.Part;
import org.omnifaces.util.Utils;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author User
 */
@Named(value = "informeControlador")
@ViewScoped
public class InformeControlador implements Serializable{

    private List<Part> imagenes;
    private List<RegistroFotografico> registroFotografico;
    
    List<AsistenciaAdolescente> listaParaChequeo;
    List<ItemTaller> listaItemsTaller;
    
    Informe informe;
    Taller taller;
    RegistroFotograficoServicio servicioRegistroFotografico;
    
    int cantidadAsistentes=0;
    private boolean skip;
    
    RegistroAsistenciaServicio servicioRegistro;
    AsistenciaAdolescentesServicio servicioAsistencia;
    TallerServicio servicioTaller;
    InformeServicio servicioInforme;
    
    Integer duracionActividades=0;
    private Date horaFin;
    
    private EnlacesPrograma enlaces;

    @PostConstruct
    public void init(){
        enlaces= new EnlacesPrograma();
        imagenes= new ArrayList<>();
        registroFotografico= new ArrayList<>();
        taller= new Taller();
        informe= new Informe();
        listaParaChequeo= new ArrayList<>();
        listaItemsTaller= new ArrayList<>();
        
        horaFin=null;
        
        servicioRegistro= new RegistroAsistenciaServicio();
        servicioTaller= new TallerServicio();
        servicioInforme= new InformeServicio();
        servicioRegistroFotografico= new RegistroFotograficoServicio();
        servicioAsistencia= new AsistenciaAdolescentesServicio();
        
        Taller tallerRescatado = (Taller) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("taller_psicologia");

        if (tallerRescatado != null) {
            
            taller = tallerRescatado;
            
            obtenerRegistroAsistencia();
            
            List<ItemTaller> itemsAux= servicioTaller.obtenerItemsPorTalleres(taller.getIdTaller());
            
            if(itemsAux != null){                
                listaItemsTaller=itemsAux;
            }
            obtenerDuracionTaller();
        }
    }
        
    
    
    public InformeControlador() {
    }

    public Informe getInforme() {
        return informe;
    }

    public void setInforme(Informe informe) {
        this.informe = informe;
    }

    public int getCantidadAsistentes() {
        return cantidadAsistentes;
    }

    public void setCantidadAsistentes(int cantidadAsistentes) {
        this.cantidadAsistentes = cantidadAsistentes;
    }

    public Taller getTaller() {
        return taller;
    }

    public void setTaller(Taller taller) {
        this.taller = taller;
    }

    public RegistroAsistenciaServicio getServicioRegistro() {
        return servicioRegistro;
    }

    public void setServicioRegistro(RegistroAsistenciaServicio servicioRegistro) {
        this.servicioRegistro = servicioRegistro;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public List<ItemTaller> getListaItemsTaller() {
        return listaItemsTaller;
    }

    public void setListaItemsTaller(List<ItemTaller> listaItemsTaller) {
        this.listaItemsTaller = listaItemsTaller;
    }

    public TallerServicio getServicioTaller() {
        return servicioTaller;
    }

    public void setServicioTaller(TallerServicio servicioTaller) {
        this.servicioTaller = servicioTaller;
    }

    public InformeServicio getServicioInforme() {
        return servicioInforme;
    }

    public void setServicioInforme(InformeServicio servicioInforme) {
        this.servicioInforme = servicioInforme;
    }

    public RegistroFotograficoServicio getServicioRegistroFotografico() {
        return servicioRegistroFotografico;
    }

    public void setServicioRegistroFotografico(RegistroFotograficoServicio servicioRegistroFotografico) {
        this.servicioRegistroFotografico = servicioRegistroFotografico;
    }

    public List<Part> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Part> imagenes) {
        this.imagenes = imagenes;
    }


    public List<RegistroFotografico> getRegistroFotografico() {
        return registroFotografico;
    }

    public void setRegistroFotografico(List<RegistroFotografico> registroFotografico) {
        this.registroFotografico = registroFotografico;
    }

    public List<AsistenciaAdolescente> getListaParaChequeo() {
        return listaParaChequeo;
    }

    public void setListaParaChequeo(List<AsistenciaAdolescente> listaParaChequeo) {
        this.listaParaChequeo = listaParaChequeo;
    }

    public Date getHoraFin() {
        return horaFin=obtenerHoraFin();
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }    
    
    /*************************************************************/
    
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            
            skip = false;
            return "confirm";
        }
        else {
           
           obtenerNumeroAsistentes();
            
            return event.getNewStep();
        }
    }
    
    public void obtenerNumeroAsistentes() {
        
        cantidadAsistentes=0;
        
        if(listaParaChequeo != null){
            
            for(AsistenciaAdolescente r: listaParaChequeo){
                if(r.getAsistio()==true){
                    cantidadAsistentes++;
                }
            }
        }
        
    }
    
    public void obtenerDuracionTaller() {
        
        duracionActividades=0;
        
        if(listaItemsTaller != null){
            
            for(ItemTaller i: listaItemsTaller){
                if(i.getDuracion()!=null){
                    duracionActividades=duracionActividades+i.getDuracion();
                }
            }
        }
        
    }
    
    public Date obtenerHoraFin() {
        
        horaFin=null;
        if(taller.getHoraInicio()!=null){
            Calendar horaAux = Calendar.getInstance();
            horaAux.setTime(taller.getHoraInicio());
            horaAux.add(Calendar.MINUTE, duracionActividades);
            horaFin=horaAux.getTime();
        }
        return horaFin;
    }

    public void obtenerRegistroAsistencia() {

        if (taller == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "NO SE HA PODIDO CARGAR EL TALLER", "Aviso"));
        
        } 
        else {
        
            List<AsistenciaAdolescente> registroAux = servicioRegistro.listaAdolescentesInfractoresPorTaller(taller);
            
            if (registroAux == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "NO HAY ADOLESCENTES INFRACTORES EN EL " + taller.getIdTaller(), "Aviso"));
            } 
            else {
                if (registroAux.size() > 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ADOLESCENTES INFRACTORES PERTENECIENTES A " , "Aviso"));
                    listaParaChequeo = registroAux;
                }
            }
        }
    }
    
    public void asignarImagen() throws IOException {
        
        if(registroFotografico.size()<5){
            for (int i = 0; i < imagenes.size(); i++) {
                if(registroFotografico.size()<=4){
                    if(imagenes.get(i) != null ){
                        RegistroFotografico registro= new RegistroFotografico();
                        byte[] array= Utils.toByteArray(imagenes.get(i).getInputStream());
                        registro.setImagen(array);
                        registroFotografico.add(registro);
                    }
                }else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "HA ALCANZADO EL LÍMITE DE FOTOS","Aviso" ));
                    break;
                }
                
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "HA ALCANZADO EL LÍMITE DE FOTOS","Aviso" ));
        }
        
    }
    
    public void eliminarImagen(RegistroFotografico registroSeleccionado){
        
        for (RegistroFotografico r : registroFotografico) {
            if(r==registroSeleccionado){
                registroFotografico.remove(r);
                break;
            }
        }
    }
    
    private int guardarRegistroAsistencia(){
        
        if(listaParaChequeo != null){
            
            int cantidadRegistro=0;
            for(AsistenciaAdolescente asistencia : listaParaChequeo){
            
                if(asistencia.getAsistio()==true){
                    
                    AsistenciaAdolescente asistenciAux= servicioAsistencia.guardarRegistroAsistenciaAdolescente(asistencia);
                
                    if(asistenciAux!=null){
                        cantidadRegistro++;
                    }
                }
            }

            if(cantidadRegistro>0 ){
                
                return cantidadRegistro;
            }
            else{
                return 0;
            }
        }else{
            return 0;
        }
        
    }
    
    private Informe guardarInforme(){
        
        try{
            informe.setHoraInicio(taller.getHoraInicio());
            informe.setNumeroAdolescentes(cantidadAsistentes);
            informe.setIdTaller(taller);
            informe.setObjetivoGeneral(taller.getObjetivo());
            informe.setFecha(taller.getFecha());
            informe.setHoraFin(horaFin);
            Informe informeAux= servicioInforme.guardarInforme(this.informe);

            if(informeAux != null){
                
                return informeAux;
                
            }
            else{
                return null;
                
            }
            
        }catch(Exception e){
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL TALLER DE PSICOLOGÍA","Aviso" ));
            return null;        
        }
        
    }
    
    private void guardarRegistroFotografico(Informe informe){
    
        if(registroFotografico.size()>0){
            
            for(RegistroFotografico registro : registroFotografico){
                
                if(registro.getImagen()!=null){
                    registro.setIdInforme(informe);
                    servicioRegistroFotografico.guardarRegistroFotografico(registro);
                }
            }
        }
    }
    
    public String guardarInformeResultados(){
        
        try{

            int asistencia=guardarRegistroAsistencia();
            if(asistencia >0){

                Informe informeAux= guardarInforme();

                if(informeAux != null){
                    
                    guardarRegistroFotografico(informeAux);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL INFORME DE PSICOLOGÍA","Aviso" ));
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("informe_psicologia_editar",informeAux);
                    return enlaces.PATH_INFORME_VER+"?faces-redirect=true";
                }
                else{
                    return null;
                }
            }
            else{
                return null;
            }
        }catch(Exception e){
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL TALLER DE PSICOLOGÍA","Aviso" ));
            return null;
        } 
    }
}

