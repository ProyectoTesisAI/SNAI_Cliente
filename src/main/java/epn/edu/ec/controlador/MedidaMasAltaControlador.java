/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author User
 */
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
        adolescenteInfractorUDI= (AdolescenteInfractorUDI)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adolescente_infractor_udi");

        if(adolescenteInfractorUDI!=null){
            List<MedidaSocioeducativa> listaMedidasSocioeducativas= servicio.listaMedidaSocioeducativosPorAdolescentesUzdi(adolescenteInfractorUDI);
            
            if(listaMedidasSocioeducativas!=null){
                medidaMasAlta=servicio.obtenerMedidaMasAlta(adolescenteInfractorUDI);
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
