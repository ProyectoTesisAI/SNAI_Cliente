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

    public String URL_BASE = "/SistemaSNAI_Cliente";

    public String PATH_TALLER_CREAR = "/paginas/user/taller/taller.com";
    public String PATH_INFORME_CREAR = "/paginas/user/informe/informe.com";

    public String PATH_ADOLESCENTE_UDI_CREAR = "/paginas/udi/adolescentes_udi/ai_udi_crear.com";
    public String PATH_ADOLESCENTE_CAI_CREAR = "/paginas/cai/adolescentes_cai/ai_cai_crear.com";
    
    public String PATH_PANEL_INFORME = "/paginas/user/informe/panel_informe.com";
    public String PATH_INFORME_VER = "/paginas/user/informe/informe_ver.com";
    
    public String PATH_PANEL_TALLER = "/paginas/user/taller/panel_taller.com";
    public String PATH_TALLER_VER = "/paginas/user/taller/taller_ver.com";

    public String PATH_PANEL_INFORME_ADMINISTRADOR = "/paginas/admin/informe/panel_informe.com";
    public String PATH_INFORME_EDITAR = "/paginas/admin/informe/informe_editar.com";
    
    public String PATH_PANEL_TALLER_ADMINISTRADOR = "/paginas/admin/taller/panel_taller.com";
    public String PATH_TALLER_EDITAR = "/paginas/admin/taller/taller_editar.com";

    public String PATH_PANEL_UDI_USER = "/paginas/user/uzdi/udi.com";
    public String PATH_PANEL_UDI_ADMINISTRADOR = "/paginas/admin/uzdi/udi.com";
    
    public String PATH_PANEL_CAI_USER = "/paginas/user/cai/cai.com";
    public String PATH_PANEL_CAI_ADMIN = "/paginas/admin/cai/cai.com";
    
    public String PATH_PANEL_CREAR_UDI_LIDER_UZDI = "/paginas/user/uzdi/liderUzdi/panel_crear_udi.com";
    public String PATH_PANEL_CREAR_UDI_PSICOLOGO = "/paginas/user/uzdi/psicologo/panel_crear_udi.com";
    public String PATH_PANEL_CREAR_UDI_TRABAJADOR_SOCIAL = "/paginas/user/uzdi/trabajadorSocial/panel_crear_udi.com";
    public String PATH_PANEL_CREAR_UDI_ADMINISTRADOR = "/paginas/admin/uzdi/panel_crear_udi.com";
    
    public String PATH_PANEL_CREAR_CAI_COORDINADOR = "/paginas/user/cai/coordinadorCai/panel_crear_cai.com";
    public String PATH_PANEL_CREAR_CAI_PSICOLOGO = "/paginas/user/cai/psicologo/panel_crear_cai.com";
    public String PATH_PANEL_CREAR_CAI_TRABAJADOR_SOCIAL = "/paginas/user/cai/trabajadorSocial/panel_crear_cai.com";
    public String PATH_PANEL_CREAR_CAI_INSPECTOR_EDUCADOR = "/paginas/user/cai/inspectorEducador/panel_crear_cai.com";
    public String PATH_PANEL_CREAR_CAI_JURIDICO = "/paginas/user/cai/juridico/panel_crear_cai.com";
    public String PATH_PANEL_CREAR_CAI_ADMINISTRADOR = "/paginas/admin/cai/panel_crear_cai.com";
    
    public String PATH_PANEL_EJECUCION_MEDIDA_CAI_CREAR = "/paginas/user/cai/panel_crear_medida_cai.com";
    public String PATH_PANEL_EJECUCION_MEDIDA_CAI_VER = "/paginas/user/cai/panel_ver_medida_cai.com";
    
    public String PATH_PANEL_INFORMACION_MEDIDA_CAI = "/paginas/user/cai/panel_crear_info_medida_cai.com";
    public String PATH_PANEL_INFORMACION_MEDIDA_CAI_INSPECTOR_EDUCADOR = "/paginas/user/cai/inspectorEducador/panel_crear_info_medida_cai.com";
    
    public String PATH_ERROR = "/error/error.com";

    public String getPATH_ADOLESCENTE_UDI_CREAR() {
        return PATH_ADOLESCENTE_UDI_CREAR;
    }

    public void setPATH_ADOLESCENTE_UDI_CREAR(String PATH_ADOLESCENTE_UDI_CREAR) {
        this.PATH_ADOLESCENTE_UDI_CREAR = PATH_ADOLESCENTE_UDI_CREAR;
    }

    public String getPATH_ADOLESCENTE_CAI_CREAR() {
        return PATH_ADOLESCENTE_CAI_CREAR;
    }

    public void setPATH_ADOLESCENTE_CAI_CREAR(String PATH_ADOLESCENTE_CAI_CREAR) {
        this.PATH_ADOLESCENTE_CAI_CREAR = PATH_ADOLESCENTE_CAI_CREAR;
    }
    
    

}
