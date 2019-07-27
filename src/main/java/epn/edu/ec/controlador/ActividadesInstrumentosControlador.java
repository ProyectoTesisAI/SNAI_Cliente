package epn.edu.ec.controlador;

import epn.edu.ec.modelo.ActividadesInstrumentos;
import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.servicios.ActividadesInstrumentosServicio;
import epn.edu.ec.utilidades.EnlacesPrograma;
import epn.edu.ec.utilidades.PermisosUsuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "actividadesInstrumentosControlador")
@ViewScoped
public class ActividadesInstrumentosControlador implements Serializable{

    private AdolescenteInfractorUDI adolescenteInfractorUDI;
    private ActividadesInstrumentos actividadesInstrumentos;
    private ActividadesInstrumentosServicio servicio;
    private boolean guardado;
    private EnlacesPrograma enlaces;
    private PermisosUsuario permisosUsuario;
    
    @PostConstruct
    public void init(){
        
        permisosUsuario= new PermisosUsuario();
        enlaces= new EnlacesPrograma();
        servicio= new ActividadesInstrumentosServicio();
        
        actividadesInstrumentos= new ActividadesInstrumentos();
        guardado=false;
        
        adolescenteInfractorUDI= new AdolescenteInfractorUDI();
        AdolescenteInfractorUDI adolescenteInfractorUDIAux= (AdolescenteInfractorUDI)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_udi");
        
        if(adolescenteInfractorUDIAux != null){
            
            adolescenteInfractorUDI=adolescenteInfractorUDIAux;
            
            ActividadesInstrumentos actividadesInstrumentosAux= servicio.obtenerActividadesInstrumentos(adolescenteInfractorUDI.getIdAdolescenteInfractor().getIdAdolescenteInfractor());
            if(actividadesInstrumentosAux!=null){
                actividadesInstrumentos=actividadesInstrumentosAux;
                guardado=true;
            }            
        }
        
    }

    public AdolescenteInfractorUDI getAdolescenteInfractorUDI() {
        return adolescenteInfractorUDI;
    }

    public void setAdolescenteInfractorUDI(AdolescenteInfractorUDI adolescenteInfractorUDI) {
        this.adolescenteInfractorUDI = adolescenteInfractorUDI;
    }

    public ActividadesInstrumentos getActividadesInstrumentos() {
        return actividadesInstrumentos;
    }

    public void setActividadesInstrumentos(ActividadesInstrumentos actividadesInstrumentos) {
        this.actividadesInstrumentos = actividadesInstrumentos;
    }

    
    public ActividadesInstrumentosServicio getServicio() {
        return servicio;
    }

    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }
    
    /*********************Métodos para invocar a los diferentes servicios web******************/
    
    public String guardarActividadesInstrumentos(){
        
        this.actividadesInstrumentos.setIdAdolescenteInfractorUDI(adolescenteInfractorUDI);

        ActividadesInstrumentos actividadesInstrumentosAux = servicio.guardarActividadesInstrumentos(actividadesInstrumentos);
        if(actividadesInstrumentosAux!=null){
            
            String rolUsuario=permisosUsuario.RolUsuario();
        
            if(rolUsuario!=null){
                
                if(rolUsuario.equals("ADMINISTRADOR")){
                    return enlaces.PATH_PANEL_UDI_ADMINISTRADOR+"?faces-redirect=true";
                }
                else{
                    return enlaces.PATH_PANEL_UDI_USER+"?faces-redirect=true";
                }
            }
            else{
                return null;
            }              
        }
        else{
            return null;
        }
    }

}
