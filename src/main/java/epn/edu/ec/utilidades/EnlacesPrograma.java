package epn.edu.ec.utilidades;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named(value = "enlacesPrograma")
@Dependent
public class EnlacesPrograma {

    public String URL_BASE = "/SistemaSNAI_Cliente";

    public String PATH_LOGIN="/login.com";
    
    public String PATH_TALLER_CREAR = "/paginas/user/taller/taller.com";
    public String PATH_INFORME_CREAR = "/paginas/user/informe/informe.com";

    public String PATH_ADOLESCENTE_UDI_CREAR = "/paginas/uzdi/adolescentes_udi/ai_udi_crear.com";
    public String PATH_ADOLESCENTE_CAI_CREAR = "/paginas/cai/adolescentes_cai/ai_cai_crear.com";
    
    public String PATH_PANEL_INFORME = "/paginas/user/informe/panel_informe.com";
    public String PATH_INFORME_VER = "/paginas/user/informe/informe_ver.com";
    
    public String PATH_PANEL_TALLER = "/paginas/user/taller/panel_taller.com";
    public String PATH_TALLER_VER = "/paginas/user/taller/taller_ver.com";

    public String PATH_PANEL_INFORME_ADMINISTRADOR = "/paginas/admin/informe/panel_informe.com";
    public String PATH_INFORME_EDITAR = "/paginas/admin/informe/informe_editar.com";
    
    public String PATH_PANEL_TALLER_ADMINISTRADOR = "/paginas/admin/taller/panel_taller.com";
    public String PATH_TALLER_EDITAR = "/paginas/admin/taller/taller_editar.com";

    public String PATH_PANEL_UDI_USER = "/paginas/uzdi/user/udi.com";
    public String PATH_PANEL_UDI_ADMINISTRADOR = "/paginas/uzdi/admin/udi.com";
    
    public String PATH_PANEL_CAI_USER = "/paginas/cai/user/cai.com";
    public String PATH_PANEL_CAI_ADMIN = "/paginas/cai/admin/cai.com";
    
    public String PATH_PANEL_CREAR_UDI_LIDER_UZDI = "/paginas/uzdi/user/liderUzdi/panel_crear_udi.com";
    public String PATH_PANEL_CREAR_UDI_PSICOLOGO = "/paginas/uzdi/user/psicologo/panel_crear_udi.com";
    public String PATH_PANEL_CREAR_UDI_TRABAJADOR_SOCIAL = "/paginas/uzdi/user/trabajadorSocial/panel_crear_udi.com";
    public String PATH_PANEL_CREAR_UDI_ADMINISTRADOR = "/paginas/uzdi/admin/panel_crear_udi.com";
    

    public String PATH_PANEL_CREAR_CAI_COORDINADOR = "/paginas/cai/user/crear/coordinadorCai/panel_crear_cai.com";
    public String PATH_PANEL_CREAR_CAI_PSICOLOGO = "/paginas/cai/user/crear/psicologo/panel_crear_cai.com";
    public String PATH_PANEL_CREAR_CAI_TRABAJADOR_SOCIAL = "/paginas/cai/user/crear/trabajadorSocial/panel_crear_cai.com";
    public String PATH_PANEL_CREAR_CAI_INSPECTOR_EDUCADOR = "/paginas/cai/user/crear/inspectorEducador/panel_crear_cai.com";
    public String PATH_PANEL_CREAR_CAI_JURIDICO = "/paginas/cai/user/crear/juridico/panel_crear_cai.com";

    public String PATH_PANEL_EDITAR_UDI_LIDER_UZDI = "/paginas/uzdi/user/liderUzdi/panel_editar_udi.com";
    public String PATH_PANEL_EDITAR_UDI_PSICOLOGO = "/paginas/uzdi/user/psicologo/panel_editar_udi.com";
    public String PATH_PANEL_EDITAR_UDI_TRABAJADOR_SOCIAL = "/paginas/uzdi/user/trabajadorSocial/panel_editar_udi.com";
    public String PATH_PANEL_EDITAR_UDI_ADMINISTRADOR = "/paginas/uzdi/admin/panel_editar_udi.com";
    
    
    public String PATH_PANEL_CREAR_CAI_ADMINISTRADOR = "/paginas/cai/admin/crear/panel_crear_cai.com";
    public String PATH_PANEL_EDITAR_CAI_ADMINISTRADOR = "/paginas/cai/admin/editar/panel_crear_cai.com";
    
    
    
    public String PATH_PANEL_EJECUCION_MEDIDA_CAI_CREAR_ADMININSTRADOR = "/paginas/cai/admin/crear/panel_crear_medida_cai.com";
    public String PATH_PANEL_EJECUCION_MEDIDA_CAI_CREAR_USUARIO= "/paginas/cai/user/crear/panel_crear_medida_cai.com";
    public String PATH_PANEL_EJECUCION_MEDIDA_CAI_VER_USUARIO = "/paginas/cai/user/crear/panel_ver_medida_cai.com";
    
    
    public String PATH_PANEL_INFORMACION_MEDIDA_CAI_ADMINISTRADOR = "/paginas/cai/admin/crear/panel_crear_info_medida_cai.com";
    public String PATH_PANEL_INFORMACION_MEDIDA_CAI_USUARIO = "/paginas/cai/user/crear/panel_crear_info_medida_cai.com";
    public String PATH_PANEL_INFORMACION_MEDIDA_CAI_INSPECTOR_EDUCADOR = "/paginas/cai/user/crear/inspectorEducador/panel_crear_info_medida_cai.com";
    
    public String PATH_PANEL_EJECUCION_MEDIDA_CAI_EDITAR_ADMININSTRADOR = "/paginas/cai/admin/editar/panel_crear_medida_cai.com";
    public String PATH_PANEL_INFORMACION_MEDIDA_CAI_EDITAR_ADMINISTRADOR = "/paginas/cai/admin/editar/panel_crear_info_medida_cai.com";
    
    
    public String PATH_ERROR = "/error/error.com";
    
    public String PATH_PANEL_REPORTE="/paginas/admin/reportes/reporte.com";
    public String PATH_PANEL_REPORTE_1 = "/paginas/admin/reportes/panel_generar_reporte_1.com";
    public String PATH_PANEL_REPORTE_2 = "/paginas/admin/reportes/panel_generar_reporte_2.com";
    public String PATH_PANEL_REPORTE_3 = "/paginas/admin/reportes/panel_generar_reporte_3.com";
    public String PATH_PANEL_REPORTE_4 = "/paginas/admin/reportes/panel_generar_reporte_4.com";
    public String PATH_PANEL_REPORTE_5 = "/paginas/admin/reportes/panel_generar_reporte_5.com";
    public String PATH_PANEL_REPORTE_6 = "/paginas/admin/reportes/panel_generar_reporte_6.com";
    public String PATH_PANEL_REPORTE_7 = "/paginas/admin/reportes/panel_generar_reporte_7.com";
    public String PATH_PANEL_REPORTE_8 = "/paginas/admin/reportes/panel_generar_reporte_8.com";
    public String PATH_PANEL_REPORTE_9 = "/paginas/admin/reportes/panel_generar_reporte_9.com";
    
    public String PATH_PANEL_USUARIO_NUEVO = "/paginas/admin/panel_administracion_usuario.com";

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

    public String getPATH_PANEL_REPORTE() {
        return PATH_PANEL_REPORTE;
    }

    public void setPATH_PANEL_REPORTE(String PATH_PANEL_REPORTE) {
        this.PATH_PANEL_REPORTE = PATH_PANEL_REPORTE;
    }

    public String getPATH_PANEL_USUARIO_NUEVO() {
        return PATH_PANEL_USUARIO_NUEVO;
    }

}
