/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.utilidades;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author User
 */
@Named(value = "enlacesPrograma")
@Dependent
public class EnlacesPrograma {

    public String URL_BASE="/SistemaSNAI_Cliente";
    public String PATH_TALLER_PSCICOLOGIA_CREAR="/paginas/psicologia/taller_psicologia.com";
    public String PATH_PANEL_PSICOLOGIA = "/paginas/psicologia/panel_taller_psicologia.com";
    public String PATH_PANEL_INFORME_PSICOLOGIA = "/paginas/psicologia/panel_informe_psicologia.com";
    public String PATH_PANEL_UDI = "/paginas/udi/udi.com";
    public String PATH_PANEL_CAI = "/paginas/cai/cai.com";
    public String PATH_ADOLESCENTE_UDI_CREAR = "/paginas/udi/adolescentes_udi/ai_udi_crear.com";
    public String PATH_ADOLESCENTE_CAI_CREAR = "/paginas/cai/adolescentes_cai/ai_cai_crear.com";
    public String PATH_PANEL_CREAR_UDI = "/paginas/udi/matriz/panel_crear_udi.com";
    public String PATH_PANEL_CREAR_CAI = "/paginas/cai/matriz/panel_crear_cai.com";
    public String PATH_ERROR="/error/error.com";
    public String getURL_BASE() {
        return URL_BASE;
    }
    
    public String getPATH_TALLER_PSCICOLOGIA_CREAR() {
        return PATH_TALLER_PSCICOLOGIA_CREAR;
    }

    public String getPATH_PANEL_PSICOLOGIA() {
        return PATH_PANEL_PSICOLOGIA;
    }

    public String getPATH_PANEL_UDI() {
        return PATH_PANEL_UDI;
    }

    public String getPATH_PANEL_CAI() {
        return PATH_PANEL_CAI;
    }

    public String getPATH_ADOLESCENTE_UDI_CREAR() {
        return PATH_ADOLESCENTE_UDI_CREAR;
    }

    public String getPATH_ADOLESCENTE_CAI_CREAR() {
        return PATH_ADOLESCENTE_CAI_CREAR;
    }

    public String getPATH_PANEL_INFORME_PSICOLOGIA() {
        return PATH_PANEL_INFORME_PSICOLOGIA;
    }

    public String getPATH_PANEL_CREAR_UDI() {
        return PATH_PANEL_CREAR_UDI;
    }

    public String getPATH_PANEL_CREAR_CAI() {
        return PATH_PANEL_CREAR_CAI;
    }

    public String getPATH_ERROR() {
        return PATH_ERROR;
    }
    
    
}
