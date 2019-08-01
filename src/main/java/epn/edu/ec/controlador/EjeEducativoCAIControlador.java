package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.modelo.EjeEducativo;
import epn.edu.ec.servicios.EjeEducativoServicio;
import epn.edu.ec.utilidades.EnlacesPrograma;
import epn.edu.ec.utilidades.PermisosUsuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "ejeEducativoControladorCAI")
@ViewScoped
public class EjeEducativoCAIControlador implements Serializable{

    private AdolescenteInfractorCAI adolescenteInfractorCAI;
    private EjeEducativo ejeEducativo;
    
    private EjeEducativoServicio servicio;
    
    private boolean guardado;
    private boolean estudia;
    private EnlacesPrograma enlaces;
    private PermisosUsuario permisosUsuario;
    
     @PostConstruct
    public void init(){
        
        permisosUsuario= new PermisosUsuario();
        enlaces= new EnlacesPrograma();
        servicio= new EjeEducativoServicio();
        
        ejeEducativo=new EjeEducativo();
        guardado=false;
        
        if(isEstudia()){
            estudia=true;
        }
        else{
            estudia=false;
        }
        
        adolescenteInfractorCAI= new AdolescenteInfractorCAI();
        AdolescenteInfractorCAI adolescenteInfractorCAIAux= (AdolescenteInfractorCAI)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_cai");
        
        if(adolescenteInfractorCAIAux != null){
            
            adolescenteInfractorCAI=adolescenteInfractorCAIAux;
            
            EjeEducativo ejeEducativoCAIAux= servicio.obtenerEjeEducativo(adolescenteInfractorCAI.getIdAdolescenteInfractor().getIdAdolescenteInfractor());
            if(ejeEducativoCAIAux!=null){
                ejeEducativo=ejeEducativoCAIAux;
                guardado=true;
                estudia=ejeEducativo.getEstudia();
            }            
        }
        
    }

    public AdolescenteInfractorCAI getAdolescenteInfractorCAI() {
        return adolescenteInfractorCAI;
    }

    public void setAdolescenteInfractorCAI(AdolescenteInfractorCAI adolescenteInfractorUDI) {
        this.adolescenteInfractorCAI = adolescenteInfractorUDI;
    }

    public EjeEducativo getEjeEducativo() {
        return ejeEducativo;
    }

    public void setEjeEducativo(EjeEducativo ejeEducativo) {
        this.ejeEducativo = ejeEducativo;
    }

    public EjeEducativoServicio getServicio() {
        return servicio;
    }
    
    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }

    public boolean isEstudia() {
        return estudia;
    }

    public void setEstudia(boolean estudia) {
        this.estudia = estudia;
        if(estudia==true){
            ejeEducativo.setEstudia(true);
        }
        else if(estudia==false){
            ejeEducativo.setEstudia(false);
        }
        
    }

    
        /*********************Métodos para invocar a los diferentes servicios web******************/
    
    public void guardarEjeEducativoCAI(){
        
        this.ejeEducativo.setIdAdolescenteInfractor(adolescenteInfractorCAI.getIdAdolescenteInfractor());
        
        EjeEducativo ejeEducativoCAIAux = servicio.guardarEjeEducativo(ejeEducativo);
        if (ejeEducativoCAIAux != null) {
            guardado=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GUARDADO CORRECTAMENTE EL REGISTRO EJE EDUCATIVO", "Información"));
            
        } else {
            guardado=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO EJE EDUCATIVO", "Error"));
        }
    }

    
}
