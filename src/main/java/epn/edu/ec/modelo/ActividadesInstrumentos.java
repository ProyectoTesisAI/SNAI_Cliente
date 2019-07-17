package epn.edu.ec.modelo;

import java.io.Serializable;

public class ActividadesInstrumentos implements Serializable {

    private AdolescenteInfractorUDI idAdolescenteInfractorUDI;
    private Boolean planEjecucionMedida;
    private Boolean planGlobalFamilia;
    private Boolean planVida;
    private Boolean planIndividualAplicaconMedida;
    

    public ActividadesInstrumentos() {
    }

    public AdolescenteInfractorUDI getIdAdolescenteInfractorUDI() {
        return idAdolescenteInfractorUDI;
    }

    public void setIdAdolescenteInfractorUDI(AdolescenteInfractorUDI idAdolescenteInfractorUDI) {
        this.idAdolescenteInfractorUDI = idAdolescenteInfractorUDI;
    }

    

    public Boolean getPlanGlobalFamilia() {
        return planGlobalFamilia;
    }

    public void setPlanGlobalFamilia(Boolean planGlobalFamilia) {
        this.planGlobalFamilia = planGlobalFamilia;
    }

    public Boolean getPlanVida() {
        return planVida;
    }

    public void setPlanVida(Boolean planVida) {
        this.planVida = planVida;
    }

    public Boolean getPlanEjecucionMedida() {
        return planEjecucionMedida;
    }

    public void setPlanEjecucionMedida(Boolean planEjecucionMedida) {
        this.planEjecucionMedida = planEjecucionMedida;
    }

    public Boolean getPlanIndividualAplicaconMedida() {
        return planIndividualAplicaconMedida;
    }

    public void setPlanIndividualAplicaconMedida(Boolean planIndividualAplicaconMedida) {
        this.planIndividualAplicaconMedida = planIndividualAplicaconMedida;
    }
    
}
