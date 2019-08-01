package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import epn.edu.ec.modelo.MedidaSocioeducativa;
import epn.edu.ec.servicios.MedidaSocioeducativaServicio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "medidaMasAltaControlador")
@ViewScoped
public class MedidaMasAltaControlador implements Serializable{

    private AdolescenteInfractorUDI adolescenteInfractorUDI;
   
    private MedidaSocioeducativa medidaMasAlta;
    private MedidaSocioeducativaServicio servicio;
    private boolean guardado;
    
    @PostConstruct
    public void init(){
        
        servicio= new MedidaSocioeducativaServicio();
        guardado=false;
        
        medidaMasAlta= new MedidaSocioeducativa();
               
        adolescenteInfractorUDI= new AdolescenteInfractorUDI();
        
        AdolescenteInfractorUDI adolescenteInfractorUDIAux=(AdolescenteInfractorUDI)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_udi");

        if(adolescenteInfractorUDIAux!=null){
            
            adolescenteInfractorUDI= adolescenteInfractorUDIAux;
            List<MedidaSocioeducativa> listaMedidasSocioeducativas= servicio.listaMedidasSocioeducativasPorAdolescente(adolescenteInfractorUDI.getIdAdolescenteInfractor().getIdAdolescenteInfractor());
            
            if(listaMedidasSocioeducativas!=null){
                medidaMasAlta=servicio.obtenerMedidaMasAlta(adolescenteInfractorUDI.getIdAdolescenteInfractor().getIdAdolescenteInfractor());
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

    public MedidaSocioeducativa getMedidaMasAlta() {
        return medidaMasAlta;
    }

    public void setMedidaMasAlta(MedidaSocioeducativa medidaMasAlta) {
        this.medidaMasAlta = medidaMasAlta;
    }

    public MedidaSocioeducativaServicio getServicio() {
        return servicio;
    }

    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }
    
}
