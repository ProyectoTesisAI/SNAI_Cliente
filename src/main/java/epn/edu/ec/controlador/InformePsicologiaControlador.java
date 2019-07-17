package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.modelo.Fotografia;
import epn.edu.ec.modelo.InformePsicologia;
import epn.edu.ec.modelo.ItemInformePsicologia;
import epn.edu.ec.modelo.ItemTallerPsicologia;
import epn.edu.ec.modelo.RegistroAsistenciaAdolescenteUDI;
import epn.edu.ec.modelo.RegistroFotografico;
import epn.edu.ec.modelo.TallerPsicologia;
import epn.edu.ec.servicios.InformePsicologiaServicio;
import epn.edu.ec.servicios.ItemInformePsicologiaServicio;
import epn.edu.ec.servicios.RegistroAsistenciaAdolescenteUDIServicio;
import epn.edu.ec.servicios.RegistroAsistenciaServicio;
import epn.edu.ec.servicios.RegistroFotograficoServicio;
import epn.edu.ec.servicios.TallerPsicologiaServicio;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named(value = "informePsicologiaControlador")
@ViewScoped
public class InformePsicologiaControlador implements Serializable{

    InformePsicologia informePsicologia;
    TallerPsicologia tallerPsicologia;
    
    List<ItemTallerPsicologia> listaItemsTallerPsicologia;
    
    InformePsicologiaServicio controlador;
    ItemInformePsicologiaServicio controladorItemInforme;
    TallerPsicologiaServicio controladorTaller;
    
    //Registro Fotografico
    RegistroFotografico registroFotografico;
    RegistroFotograficoServicio controladorRegistroFotografico;
    
    //Lista de asistentes de UDI que son provenientes de el taller
    RegistroAsistenciaServicio controladorAsistencia;
    RegistroAsistenciaAdolescenteUDIServicio controladorAsistenciaUDI;

    List<AdolescenteInfractorUDI> listaAdolescentesUzdi;
    List<RegistroAsistenciaAdolescenteUDI> listaParaChequeo;
    
    boolean informeGuardado=false;
    boolean registroAsistenciaGuardado=true;
    
    int indiceInforme=0;
    
    @PostConstruct
    public void init(){
        controlador= new InformePsicologiaServicio();
        controladorItemInforme= new ItemInformePsicologiaServicio();
        controladorTaller= new TallerPsicologiaServicio();
        controladorRegistroFotografico= new RegistroFotograficoServicio();
        controladorAsistencia = new RegistroAsistenciaServicio();
        controladorAsistenciaUDI = new RegistroAsistenciaAdolescenteUDIServicio();
        
        informePsicologia= new InformePsicologia();
        tallerPsicologia= new TallerPsicologia();
        registroFotografico= new RegistroFotografico();
        
        listaItemsTallerPsicologia= new ArrayList<>();
        listaAdolescentesUzdi = new ArrayList<>();
        listaParaChequeo = new ArrayList<>();
        
        
        TallerPsicologia tallerPsicologiaRescatado = (TallerPsicologia) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("taller_psicologia");

        if (tallerPsicologiaRescatado != null) {
            
            tallerPsicologia = tallerPsicologiaRescatado;
            
            int numeroAsistentes=controladorAsistencia.obtenerNumeroAdolescentesPorTaller(tallerPsicologia.getIdTallerPsicologia());
            informePsicologia.setNumAdolescentes(numeroAsistentes);
            
            obtenerRegistroAsistencia();
            
            
            List<ItemTallerPsicologia> itemsAux= controladorTaller.obtenerItemsPorTalleresPsicologia(tallerPsicologia.getIdTallerPsicologia());
            
            if(itemsAux != null){
                
                listaItemsTallerPsicologia=itemsAux;
            }
            
            InformePsicologia informePsicologiaAux = (InformePsicologia) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("informe_psicologia");

            if (informePsicologiaAux != null) {

                informePsicologia = informePsicologiaAux;
                informeGuardado = true;
                registroAsistenciaGuardado = false;

            } else {
                informeGuardado = false;
            }
        }
        
        

    }

    public InformePsicologia getInformePsicologia() {
        return informePsicologia;
    }

    public void setInformePsicologia(InformePsicologia informePsicologia) {
        this.informePsicologia = informePsicologia;
    }

    public TallerPsicologia getTallerPsicologia() {
        return tallerPsicologia;
    }

    public void setTallerPsicologia(TallerPsicologia tallerPsicologia) {
        this.tallerPsicologia = tallerPsicologia;
    }
    
    public InformePsicologiaServicio getControlador() {
        return controlador;
    }


    public ItemInformePsicologiaServicio getControladorItemInforme() {
        return controladorItemInforme;
    }

    public TallerPsicologiaServicio getControladorTaller() {
        return controladorTaller;
    }

    public RegistroFotografico getRegistroFotografico() {
        return registroFotografico;
    }

    public void setRegistroFotografico(RegistroFotografico registroFotografico) {
        this.registroFotografico = registroFotografico;
    }

    public List<AdolescenteInfractorUDI> getListaAdolescentesUzdi() {
        return listaAdolescentesUzdi;
    }

    public void setListaAdolescentesUzdi(List<AdolescenteInfractorUDI> listaAdolescentesUzdi) {
        this.listaAdolescentesUzdi = listaAdolescentesUzdi;
    }
    
    public List<RegistroAsistenciaAdolescenteUDI> getListaParaChequeo() {
        return listaParaChequeo;
    }

    public void setListaParaChequeo(List<RegistroAsistenciaAdolescenteUDI> listaParaChequeo) {
        this.listaParaChequeo = listaParaChequeo;
    }

    public RegistroFotograficoServicio getControladorRegistroFotografico() {
        return controladorRegistroFotografico;
    }

    public void setControladorRegistroFotografico(RegistroFotograficoServicio controladorRegistroFotografico) {
        this.controladorRegistroFotografico = controladorRegistroFotografico;
    }

    public boolean isInformeGuardado() {
        return informeGuardado;
    }

    public void setInformeGuardado(boolean informeGuardado) {
        this.informeGuardado = informeGuardado;
    }

    public boolean isRegistroAsistenciaGuardado() {
        return registroAsistenciaGuardado;
    }

    public void setRegistroAsistenciaGuardado(boolean registroAsistenciaGuardado) {
        this.registroAsistenciaGuardado = registroAsistenciaGuardado;
    }

    public int getIndiceInforme() {
        return indiceInforme;
    }

    public void setIndiceInforme(int indiceInforme) {
        this.indiceInforme = indiceInforme;
    }

    public List<ItemTallerPsicologia> getListaItemsTallerPsicologia() {
        return listaItemsTallerPsicologia;
    }

    public void setListaItemsTallerPsicologia(List<ItemTallerPsicologia> listaItemsTallerPsicologia) {
        this.listaItemsTallerPsicologia = listaItemsTallerPsicologia;
    }

    
    

    public String guardarInformePsicologia(){
        
        try{
            int itemsGuardados=0;
            informePsicologia.setIdTallerPsicologia(tallerPsicologia);
            //aqui se setea la fecha para la base de datos
            informePsicologia.setFecha(tallerPsicologia.getFecha());
            InformePsicologia informePsicologiaAux= controlador.guardarInformePsicologia(this.informePsicologia);

            if(informePsicologiaAux != null){
                
                informeGuardado=true;
                registroAsistenciaGuardado=false; 
                indiceInforme=1;
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("informe_psicologia", informePsicologiaAux);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL INFORME DE PSICOLOGÍA","Aviso" ));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AHORA PUEDE GUARDAR EL REGISTRO DE ASISTENCIA","Aviso" ));
                return "/paginas/psicologia/informe_psicologia.com?faces-redirect=true";
                
            }
            else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL TALLER DE PSICOLOGÍA","Aviso" ));
            }
            
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL TALLER DE PSICOLOGÍA","Aviso" ));
            System.out.println("Alerta del boton guardar");
        }
        return null;
    }
    
    public void obtenerRegistroAsistencia() {

        if (tallerPsicologia == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "NO SE HA PODIDO CARGAR EL TALLER", "Aviso"));
        } else {
            List<RegistroAsistenciaAdolescenteUDI> registroAux = controladorAsistencia.listaAdolescentesInfractoresPorTaller(tallerPsicologia);
            if (registroAux == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "NO HAY ADOLESCENTES INFRACTORES EN EL " + tallerPsicologia.getIdTallerPsicologia(), "Aviso"));
            } else {
                if (registroAux.size() > 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ADOLESCENTES INFRACTORES PERTENECIENTES A " + tallerPsicologia.getIdUdi().getUdi(), "Aviso"));
                    listaParaChequeo = registroAux;
                    for (AdolescenteInfractorUDI a : listaAdolescentesUzdi) {
                        System.out.println("adolescente: " + a);

                    }
                }
            }
        }
    }
    
    public String guardarRegistroAsistencia(){
        
        int cantidadAsistentes=0;
        for(RegistroAsistenciaAdolescenteUDI asistencia : listaParaChequeo){
            if(asistencia.getAsistio()==true){
                RegistroAsistenciaAdolescenteUDI asistenciAux= controladorAsistenciaUDI.guardarRegistroAsistenciaAdolescenteUDI(asistencia);
                
                if(asistenciAux!=null){
                    cantidadAsistentes++;
                }
                
            }
        }
        
        if(cantidadAsistentes>0 ){
            informePsicologia.setEstadoegistroAsistencia(true);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado correctamente el Registro de Asistencia", "Aviso"));
            return "/paginas/psicologia/informe_psicologia.com?faces-redirect=true";
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ha ocurrido un error, no se guardó correctamente el Registro de Asistencia", "Aviso"));
            return null;
        }
        
    }
    
    public void subirFoto(FileUploadEvent evento) {
        
        UploadedFile foto = evento.getFile();
        
        try{
            String nombre=foto.getFileName();
            InputStream in=(InputStream)foto.getInputstream();
            byte[] buffer = new byte[(int) foto.getSize()];
            
            ByteArrayOutputStream buffer2 = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = in.read(data, 0, data.length)) != -1) {
                buffer2.write(data, 0, nRead);
            }

            buffer2.flush();
            byte[] byteArray = buffer2.toByteArray();
            
            Fotografia imagen= new Fotografia();
            imagen.setNombre(nombre);
            imagen.setArray(byteArray);
            imagen.setBuffer(buffer);
        
            registroFotografico.setDescripcion("Foto subida desde la app web");
            registroFotografico.setImagen(imagen);
            registroFotografico.setIdInformePsicologia(informePsicologia);
            RegistroFotografico registroFotograficoAux = controladorRegistroFotografico.guardarRegistroFotografico(registroFotografico);
            if(registroFotograficoAux.getFoto()!=null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado correctamente la imagen", "Aviso"));
            }
            registroFotografico=registroFotograficoAux;
        }
        catch (IOException e) {
            
        }
    }
    
}
