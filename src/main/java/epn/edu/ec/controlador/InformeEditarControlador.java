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
import epn.edu.ec.utilidades.RecursosEspeciales;
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
import javax.servlet.http.Part;
import org.omnifaces.util.Utils;
import org.primefaces.event.FlowEvent;

@Named(value = "informeEditarControlador")
@ViewScoped
public class InformeEditarControlador implements Serializable{

    private List<Part> imagenes;
    private List<RegistroFotografico> registroFotografico;
    private List<RegistroFotografico> registroFotograficoAEliminar;
    
    List<AsistenciaAdolescente> listaParaChequeo;
    List<ItemTaller> listaItemsTaller;
    
    Informe informeEditar;
    Taller taller;
    RegistroFotograficoServicio servicioRegistroFotografico;
    
    int cantidadAsistentes=0;
    private boolean skip;
    
    RegistroAsistenciaServicio servicioRegistro;
    AsistenciaAdolescentesServicio servicioAsistencia;
    TallerServicio servicioTaller;
    InformeServicio servicioInforme;
    private boolean informeGuardado;
    
    private RecursosEspeciales recursosEspeciales;

    @PostConstruct
    public void init(){
        
        recursosEspeciales= new RecursosEspeciales();
        imagenes= new ArrayList<>();
        registroFotografico= new ArrayList<>();
        registroFotograficoAEliminar= new ArrayList<>();
        taller= new Taller();
        informeEditar= new Informe();
        listaParaChequeo= new ArrayList<>();
        listaItemsTaller= new ArrayList<>();
        
        servicioRegistro= new RegistroAsistenciaServicio();
        servicioTaller= new TallerServicio();
        servicioInforme= new InformeServicio();
        servicioRegistroFotografico= new RegistroFotograficoServicio();
        servicioAsistencia= new AsistenciaAdolescentesServicio();
        
        Informe informeAux = (Informe) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("informe_psicologia_editar");
        
        if (informeAux != null) {
            
            informeEditar=informeAux;
            
            taller=informeEditar.getIdTaller();     
            
            obtenerRegistroAsistencia();
            
            List<ItemTaller> itemsAux= servicioTaller.obtenerItemsPorTalleres(taller.getIdTaller());
            
            if(itemsAux != null){
                
                listaItemsTaller=itemsAux;
            }
            
            List<RegistroFotografico> registroFotograficoAux=servicioRegistroFotografico.listaRegistroFotografico(informeEditar.getIdInforme());
            
            if(registroFotograficoAux!=null){
                registroFotografico=registroFotograficoAux;
            }
        
        }
    }
        

    public Informe getInformeEditar() {
        return informeEditar;
    }

    public void setInformeEditar(Informe informeEditar) {
        this.informeEditar = informeEditar;
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

    public boolean isInformeGuardado() {
        return informeGuardado;
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

    private void obtenerRegistroAsistencia() {

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
                        InputStream imagen= recursosEspeciales.cambioResolucionImagen(imagenes.get(i).getInputStream());
                        byte[] array= Utils.toByteArray(imagen);
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
                registroFotograficoAEliminar.add(r);
                break;
            }
        }
    }
    
    
    private int guardarRegistroAsistencia(){
        
        if(listaParaChequeo != null){
            
            int cantidadRegistro=0;
            for(AsistenciaAdolescente asistencia : listaParaChequeo){
            
                AsistenciaAdolescente asistenciAux= servicioAsistencia.guardarRegistroAsistenciaAdolescente(asistencia);

                if(asistenciAux!=null){
                    cantidadRegistro++;
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
            
            informeEditar.setHoraInicio(taller.getHoraInicio());
            informeEditar.setNumeroAdolescentes(cantidadAsistentes);
            informeEditar.setIdTaller(taller);
            informeEditar.setFecha(taller.getFecha());
            Informe informeAux= servicioInforme.guardarInforme(this.informeEditar);

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
        
        if(registroFotograficoAEliminar.size()>0){
            
            for(RegistroFotografico registro : registroFotograficoAEliminar){
                
                if(registro.getIdRegistroFotografico() != null){
                    servicioRegistroFotografico.eliminarRegistroFotografico(registro.getIdRegistroFotografico());
                }
                
            }
        }
        
        if(registroFotografico.size()>0){
            
            for(RegistroFotografico registro : registroFotografico){
                
                if(registro.getIdRegistroFotografico() == null){
                    registro.setIdInforme(informe);
                    servicioRegistroFotografico.guardarRegistroFotografico(registro);
                }
            }
        }
        
    }
    
    public void guardarInformeResultados() {

        try {

            if (cantidadAsistentes > 0) {

                if (registroFotografico.size() > 0) {

                    int asistencia = guardarRegistroAsistencia();
                    if (asistencia > 0) {

                        Informe informeAux = guardarInforme();

                        if (informeAux != null) {

                            guardarRegistroFotografico(informeAux);

                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL INFORME", "Aviso"));
                            informeGuardado = true;
                        } else {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL INFORME", "Error"));
                            informeGuardado = false;
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO DE ASISTENCIA", "Error"));
                        informeGuardado = false;
                    }

                } else {
                    informeGuardado = false;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "DEBE DE INGRESAR AL MENOS UNA IMAGEN", "Aviso"));
                }

            } else {
                informeGuardado = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "DEBE DE SELECCIONAR LA ASISTENCIA DE LOS ADOLESCENTES INFRACTORES", "Aviso"));

            }

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL INFORME", "Aviso"));
            informeGuardado = false;
        }
    }
    
}
