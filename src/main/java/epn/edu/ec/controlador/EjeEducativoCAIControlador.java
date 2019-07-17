package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorCAI;
import epn.edu.ec.modelo.EjeEducativoCAI;
import epn.edu.ec.servicios.EjeEducativoCAIServicio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "ejeEducativoCAIControlador")
@ViewScoped
public class EjeEducativoCAIControlador implements Serializable{

    private AdolescenteInfractorCAI adolescenteInfractorCAI;
    private EjeEducativoCAI ejeEducativoCAI;
    private EjeEducativoCAIServicio servicio;
    private boolean guardado;
    private boolean estudia;
    
     @PostConstruct
    public void init(){
        servicio= new EjeEducativoCAIServicio();
        
        ejeEducativoCAI=new EjeEducativoCAI();
        guardado=false;
        
        adolescenteInfractorCAI= new AdolescenteInfractorCAI();
        adolescenteInfractorCAI= (AdolescenteInfractorCAI)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_cai");
        
        if(isEstudia()){
            estudia=true;
        }
        else{
            estudia=false;
        }
        
        
        if(adolescenteInfractorCAI != null){
            EjeEducativoCAI ejeEducativoCAIAux= servicio.obtenerEjeEducativoCAI(adolescenteInfractorCAI.getIdAdolescenteCai());
            if(ejeEducativoCAIAux!=null){
                ejeEducativoCAI=ejeEducativoCAIAux;
                guardado=true;
                estudia=ejeEducativoCAI.getEstudia();
                System.out.println("estudia init: "+estudia);
            }            
        }
        
    }

    public AdolescenteInfractorCAI getAdolescenteInfractorCAI() {
        return adolescenteInfractorCAI;
    }

    public void setAdolescenteInfractorCAI(AdolescenteInfractorCAI adolescenteInfractorCAI) {
        this.adolescenteInfractorCAI = adolescenteInfractorCAI;
    }

    public EjeEducativoCAI getEjeEducativoCAI() {
        return ejeEducativoCAI;
    }

    public void setEjeEducativoCAI(EjeEducativoCAI ejeEducativoCAI) {
        this.ejeEducativoCAI = ejeEducativoCAI;
    }

    public EjeEducativoCAIServicio getServicio() {
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
            System.out.println("si estudia");
            ejeEducativoCAI.setEstudia(true);
        }
        else if(estudia==false){
            System.out.println("no estudia");
            ejeEducativoCAI.setEstudia(false);
        }
        
    }

    
        /*********************Métodos para invocar a los diferentes servicios web******************/
    
    public String guardarEjeEducativoCAI(){
        this.ejeEducativoCAI.setIdEjeEducativo(adolescenteInfractorCAI);
        EjeEducativoCAI ejeEducativoCAIAux = servicio.guardarEjeEducativoCAI(this.ejeEducativoCAI);
        if(ejeEducativoCAIAux!=null){
            return "/paginas/cai/cai.com?faces-redirect=true";     
        }
        else{
            return null;
        }
    }

    
}
