package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.modelo.Fotografia;
import epn.edu.ec.modelo.InformePsicologia;
import epn.edu.ec.modelo.ItemInformePsicologia;
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

@Named(value = "informePsicologiaEditarControlador")
@ViewScoped
public class InformePsicologiaEditarControlador implements Serializable{

    InformePsicologia informePsicologiaEditar;
    TallerPsicologia tallerPsicologia;
    
    ItemInformePsicologia item1;
    ItemInformePsicologia item2;
    ItemInformePsicologia item3;
    ItemInformePsicologia item4;
    ItemInformePsicologia item5;
    List<ItemInformePsicologia> itemsInformePsicologia;
    
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
    //variable local para editar/ver
    private String estadoActual;
    
    @PostConstruct
    public void init(){
        controlador= new InformePsicologiaServicio();
        controladorItemInforme= new ItemInformePsicologiaServicio();
        controladorTaller= new TallerPsicologiaServicio();
        controladorRegistroFotografico= new RegistroFotograficoServicio();
        controladorAsistencia = new RegistroAsistenciaServicio();
        controladorAsistenciaUDI = new RegistroAsistenciaAdolescenteUDIServicio();
        
        informePsicologiaEditar= new InformePsicologia();
        tallerPsicologia= new TallerPsicologia();
        registroFotografico= new RegistroFotografico();
        
        listaAdolescentesUzdi = new ArrayList<>();
        listaParaChequeo = new ArrayList<>();
        
        item1= new ItemInformePsicologia();
        item2= new ItemInformePsicologia();
        item3= new ItemInformePsicologia();
        item4= new ItemInformePsicologia();
        item5= new ItemInformePsicologia();
        
        itemsInformePsicologia= new ArrayList<>();
        itemsInformePsicologia.add(item1);
        itemsInformePsicologia.add(item2);
        itemsInformePsicologia.add(item3);
        itemsInformePsicologia.add(item4);
        itemsInformePsicologia.add(item5);
        
        InformePsicologia informePsicologiaAux = (InformePsicologia) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("informe_psicologia_editar");
        estadoActual=informePsicologiaAux.getEstado();
        if(estadoActual=="ver"){
            System.out.println("Estado: "+estadoActual);
            informeGuardado = true;
        }else if(estadoActual=="editar"){
            System.out.println("Estado: "+estadoActual);
            informeGuardado = false;
        }
        if (informePsicologiaAux != null) {
            
            informePsicologiaEditar = informePsicologiaAux;
            //informeGuardado = true;
            registroAsistenciaGuardado = false;
            
            tallerPsicologia=informePsicologiaAux.getIdTallerPsicologia();
            obtenerRegistroAsistencia();

            List<ItemInformePsicologia> itemsInforme=controlador.obtenerItemsPorInformePsicologia(informePsicologiaEditar.getIdInformePsicologia());
            
            if(itemsInforme!=null && itemsInforme.size()>0){
                itemsInformePsicologia=itemsInforme;
            }
            
        } else {
            //InformePsicologia informePsicologiaGenerico = controlador.
            informeGuardado = false;
        }

    }

    public InformePsicologia getInformePsicologiaEditar() {
        return informePsicologiaEditar;
    }

    public void setInformePsicologiaEditar(InformePsicologia informePsicologiaEditar) {
        this.informePsicologiaEditar = informePsicologiaEditar;
    }

    public TallerPsicologia getTallerPsicologia() {
        return tallerPsicologia;
    }

    public void setTallerPsicologia(TallerPsicologia tallerPsicologia) {
        this.tallerPsicologia = tallerPsicologia;
    }
    
    public ItemInformePsicologia getItem1() {
        return item1;
    }

    public void setItem1(ItemInformePsicologia item1) {
        this.item1 = item1;
    }

    public ItemInformePsicologia getItem2() {
        return item2;
    }

    public void setItem2(ItemInformePsicologia item2) {
        this.item2 = item2;
    }

    public ItemInformePsicologia getItem3() {
        return item3;
    }

    public void setItem3(ItemInformePsicologia item3) {
        this.item3 = item3;
    }

    public ItemInformePsicologia getItem4() {
        return item4;
    }

    public void setItem4(ItemInformePsicologia item4) {
        this.item4 = item4;
    }

    public ItemInformePsicologia getItem5() {
        return item5;
    }

    public void setItem5(ItemInformePsicologia item5) {
        this.item5 = item5;
    }

    public List<ItemInformePsicologia> getItemsInformePsicologia() {
        return itemsInformePsicologia;
    }

    public void setItemsInformePsicologia(List<ItemInformePsicologia> itemsInformePsicologia) {
        this.itemsInformePsicologia = itemsInformePsicologia;
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

    

    public String guardarInformePsicologia(){
        
        try{
            int itemsGuardados=0;
            informePsicologiaEditar.setIdTallerPsicologia(tallerPsicologia);
            InformePsicologia informePsicologiaAux= controlador.guardarInformePsicologia(this.informePsicologiaEditar);

            if(informePsicologiaAux != null){
                for(ItemInformePsicologia i : itemsInformePsicologia){
                    i.setIdInformePsicologia(informePsicologiaAux);
                    controladorItemInforme.guardarItemInformePsicologia(i);
                    itemsGuardados++;
                }
                
                if(itemsGuardados >0 && itemsGuardados==itemsInformePsicologia.size()){
                    
                    informeGuardado=true;
                    registroAsistenciaGuardado=false; 
                    indiceInforme=1;
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("informe_psicologia_editar", informePsicologiaAux);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL INFORME DE PSICOLOGÍA","Aviso" ));
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AHORA PUEDE GUARDAR EL REGISTRO DE ASISTENCIA","Aviso" ));
                    return "/paginas/psicologia/informe_psicologia_editar.com?faces-redirect=true";
                }
            }
            else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL TALLER DE PSICOLOGÍA","Aviso" ));
            }
            
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL TALLER DE PSICOLOGÍA","Aviso" ));
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
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ADOLESCENTES INFRACTORES PERTENECIENTES A " + tallerPsicologia.getIdUdi().getUdi(), "Aviso"));
                    listaParaChequeo = registroAux;
                    for (AdolescenteInfractorUDI a : listaAdolescentesUzdi) {
                        System.out.println("adolescente: " + a);

                    }
                }
            }
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
            
            RegistroFotografico registroFotograficoAux= controladorRegistroFotografico.guardarRegistroFotografico(registroFotografico);
            registroFotografico=registroFotograficoAux;
        }
        catch (IOException e) {
            
        }
    }
    
}
