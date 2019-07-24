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
    
    public String PATH_TALLER_CREAR="/paginas/user/taller/taller.com";
    
    public String PATH_ADOLESCENTE_UDI_CREAR = "/paginas/udi/adolescentes_udi/ai_udi_crear.com";
    public String PATH_ADOLESCENTE_CAI_CREAR = "/paginas/cai/adolescentes_cai/ai_cai_crear.com";
    
    public String PATH_PANEL_INFORME = "/paginas/user/informe/panel_informe.com";
    public String PATH_PANEL_TALLER = "/paginas/user/taller/panel_taller.com";
    public String PATH_PANEL_INFORME_ADMINISTRADOR = "/paginas/admin/informe/panel_informe.com";
    public String PATH_PANEL_TALLER_ADMINISTRADOR = "/paginas/admin/taller/panel_taller.com";
    
    public String PATH_PANEL_UDI = "/paginas/udi/udi.com";
    public String PATH_PANEL_CAI = "/paginas/cai/cai.com";
    
    public String PATH_PANEL_CREAR_UDI = "/paginas/udi/matriz/panel_crear_udi.com";
    public String PATH_PANEL_CREAR_CAI = "/paginas/cai/matriz/panel_crear_cai.com";
    
    public String PATH_ERROR="/error/error.com";
    
    public String getURL_BASE() {
        return URL_BASE;
    }
    
    public String getPATH_TALLER_CREAR() {
        return PATH_TALLER_CREAR;
    }

    public String getPATH_PANEL_TALLER() {
        return PATH_PANEL_TALLER;
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

    public String getPATH_PANEL_INFORME() {
        return PATH_PANEL_INFORME;
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

    public String getPATH_PANEL_INFORME_ADMINISTRADOR() {
        return PATH_PANEL_INFORME_ADMINISTRADOR;
    }

    public String getPATH_PANEL_TALLER_ADMINISTRADOR() {
        return PATH_PANEL_TALLER_ADMINISTRADOR;
    }
    
    
}
