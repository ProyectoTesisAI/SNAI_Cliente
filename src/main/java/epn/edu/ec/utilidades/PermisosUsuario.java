/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.utilidades;

import epn.edu.ec.modelo.Usuario;
import javax.faces.context.FacesContext;

/**
 *
 * @author User
 */
public class PermisosUsuario {

    private final EnlacesPrograma enlaces;

    public PermisosUsuario() {
        enlaces = new EnlacesPrograma();
    }

    public String RolUsuario() {

        Usuario usuarioLoginAux = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");

        if (usuarioLoginAux != null) {
            String rolUsuario = usuarioLoginAux.getIdRolUsuarioCentro().getIdRol().getRol();

            if (rolUsuario != null) {
                return rolUsuario;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public String redireccionGestionInformacionUzdi() {

        String rolUsuario = RolUsuario();

        if (rolUsuario != null) {

            if (Constantes.ROL_ADMINISTRADOR.equals(rolUsuario)) {
                return enlaces.PATH_PANEL_CREAR_UDI_ADMINISTRADOR + "?faces-redirect=true";
            } else {

                if (rolUsuario.equals(Constantes.ROL_LIDER_UZDI) || rolUsuario.equals(Constantes.ROL_SUBDIRECTOR) || rolUsuario.equals(Constantes.ROL_DIRECTOR_UZDI)) {
                    return enlaces.PATH_PANEL_CREAR_UDI_LIDER_UZDI + "?faces-redirect=true";
                } else if (rolUsuario.equals(Constantes.ROL_PSICOLOGO_UZDI)) {
                    return enlaces.PATH_PANEL_CREAR_UDI_PSICOLOGO + "?faces-redirect=true";
                } else if (rolUsuario.equals(Constantes.ROL_TRABAJADOR_SOCIAL_UZDI)) {
                    return enlaces.PATH_PANEL_CREAR_UDI_TRABAJADOR_SOCIAL + "?faces-redirect=true";
                } else if (rolUsuario.equals(Constantes.ROL_JURIDICO_UZDI)) {
                    return enlaces.PATH_PANEL_CREAR_UDI_JURIDICO + "?faces-redirect=true";
                } else {
                    return null;
                }
            }
        } else {
            return enlaces.PATH_ERROR + "?faces-redirect=true";
        }
    }

    public String redireccionGestionEdicionInformacionUzdi() {

        String rolUsuario = RolUsuario();

        if (rolUsuario != null) {

            if ("ADMINISTRADOR".equals(rolUsuario)) {
                return enlaces.PATH_PANEL_EDITAR_UDI_ADMINISTRADOR + "?faces-redirect=true";
            } else {
                return null;
            }
        } else {
            return enlaces.PATH_ERROR + "?faces-redirect=true";
        }
    }

    public String redireccionGestionInformacionCai() {

        String rolUsuario = RolUsuario();

        if (rolUsuario != null) {

            if (Constantes.ROL_ADMINISTRADOR.equals(rolUsuario)) {
                return enlaces.PATH_PANEL_CREAR_CAI_ADMINISTRADOR + "?faces-redirect=true";
            } else {

                if (rolUsuario.equals(Constantes.ROL_COORDINADOR_CAI) || rolUsuario.equals(Constantes.ROL_SUBDIRECTOR) || rolUsuario.equals(Constantes.ROL_DIRECTOR_CAI)) {
                    return enlaces.PATH_PANEL_CREAR_CAI_COORDINADOR + "?faces-redirect=true";
                } else if (rolUsuario.equals(Constantes.ROL_INSPECTOR_EDUCADOR)) {
                    return enlaces.PATH_PANEL_CREAR_CAI_INSPECTOR_EDUCADOR + "?faces-redirect=true";
                } else if (rolUsuario.equals(Constantes.ROL_PSICOLOGO_CAI)) {
                    return enlaces.PATH_PANEL_CREAR_CAI_PSICOLOGO + "?faces-redirect=true";
                } else if (rolUsuario.equals(Constantes.ROL_JURIDICO_CAI)) {
                    return enlaces.PATH_PANEL_CREAR_CAI_JURIDICO + "?faces-redirect=true";
                } else if (rolUsuario.equals(Constantes.ROL_TRABAJADOR_SOCIAL_CAI)) {
                    return enlaces.PATH_PANEL_CREAR_CAI_TRABAJADOR_SOCIAL + "?faces-redirect=true";
                } else {
                    return null;
                }
            }
        } else {
            return enlaces.PATH_ERROR + "?faces-redirect=true";
        }
    }

    public String redireccionEditarInformacionCai() {

        String rolUsuario = RolUsuario();

        if (rolUsuario != null) {

            if (Constantes.ROL_ADMINISTRADOR.equals(rolUsuario)) {
                return enlaces.PATH_PANEL_EDITAR_CAI_ADMINISTRADOR + "?faces-redirect=true";
            } else {
                return null;
            }
        } else {
            return enlaces.PATH_ERROR + "?faces-redirect=true";
        }
    }

    public String redireccionEjecucionMedida() {

        String rolUsuario = RolUsuario();

        if (rolUsuario != null) {

            if (Constantes.ROL_ADMINISTRADOR.equals(rolUsuario)) {
                return enlaces.PATH_PANEL_EJECUCION_MEDIDA_CAI_CREAR_ADMININSTRADOR + "?faces-redirect=true";
            } else {

                if (rolUsuario.equals(Constantes.ROL_COORDINADOR_CAI) || rolUsuario.equals(Constantes.ROL_SUBDIRECTOR) || rolUsuario.equals(Constantes.ROL_DIRECTOR_CAI)) {
                    return enlaces.PATH_PANEL_EJECUCION_MEDIDA_CAI_CREAR_USUARIO + "?faces-redirect=true";
                } else if (rolUsuario.equals(Constantes.ROL_INSPECTOR_EDUCADOR)) {
                    return enlaces.PATH_PANEL_EJECUCION_MEDIDA_CAI_VER_USUARIO + "?faces-redirect=true";
                } else if (rolUsuario.equals(Constantes.ROL_PSICOLOGO_CAI)) {
                    return enlaces.PATH_PANEL_EJECUCION_MEDIDA_CAI_VER_USUARIO + "?faces-redirect=true";
                } else if (rolUsuario.equals(Constantes.ROL_JURIDICO_CAI)) {
                    return enlaces.PATH_PANEL_EJECUCION_MEDIDA_CAI_CREAR_USUARIO + "?faces-redirect=true";
                } else if (rolUsuario.equals(Constantes.ROL_TRABAJADOR_SOCIAL_CAI)) {
                    return enlaces.PATH_PANEL_EJECUCION_MEDIDA_CAI_VER_USUARIO + "?faces-redirect=true";
                } else {
                    return null;
                }
            }
        } else {
            return enlaces.PATH_ERROR + "?faces-redirect=true";
        }
    }

    public String redireccionEditarEjecucionMedida() {

        String rolUsuario = RolUsuario();

        if (rolUsuario != null) {

            if (Constantes.ROL_ADMINISTRADOR.equals(rolUsuario)) {
                return enlaces.PATH_PANEL_EJECUCION_MEDIDA_CAI_EDITAR_ADMINISTRADOR + "?faces-redirect=true";
            } else {
                return null;
            }
        } else {
            return enlaces.PATH_ERROR + "?faces-redirect=true";
        }
    }

    public String redireccionInformacionAdicionalMedida() {

        String rolUsuario = RolUsuario();

        if (rolUsuario != null) {

            if (Constantes.ROL_ADMINISTRADOR.equals(rolUsuario)) {
                return enlaces.PATH_PANEL_INFORMACION_MEDIDA_CAI_ADMINISTRADOR + "?faces-redirect=true";
            } else {

                if (rolUsuario.equals(Constantes.ROL_COORDINADOR_CAI) || rolUsuario.equals(Constantes.ROL_SUBDIRECTOR) || rolUsuario.equals(Constantes.ROL_DIRECTOR_CAI)) {
                    return enlaces.PATH_PANEL_INFORMACION_MEDIDA_CAI_USUARIO + "?faces-redirect=true";
                } else if (rolUsuario.equals(Constantes.ROL_INSPECTOR_EDUCADOR)) {
                    return enlaces.PATH_PANEL_INFORMACION_MEDIDA_CAI_INSPECTOR_EDUCADOR + "?faces-redirect=true";
                } else if (rolUsuario.equals(Constantes.ROL_PSICOLOGO_CAI)) {
                    return enlaces.PATH_PANEL_INFORMACION_MEDIDA_CAI_USUARIO + "?faces-redirect=true";
                } else if (rolUsuario.equals(Constantes.ROL_JURIDICO_CAI)) {
                    return enlaces.PATH_PANEL_INFORMACION_MEDIDA_CAI_USUARIO + "?faces-redirect=true";
                } else if (rolUsuario.equals(Constantes.ROL_TRABAJADOR_SOCIAL_CAI)) {
                    return enlaces.PATH_PANEL_INFORMACION_MEDIDA_CAI_USUARIO + "?faces-redirect=true";
                } else {
                    return null;
                }
            }
        } else {
            return enlaces.PATH_ERROR + "?faces-redirect=true";
        }
    }

    public String redireccionEditarInformacionAdicionalMedida() {

        String rolUsuario = RolUsuario();

        if (rolUsuario != null) {

            if (Constantes.ROL_ADMINISTRADOR.equals(rolUsuario)) {
                return enlaces.PATH_PANEL_INFORMACION_MEDIDA_CAI_EDITAR_ADMINISTRADOR + "?faces-redirect=true";
            } else {
                return null;
            }
        } else {
            return enlaces.PATH_ERROR + "?faces-redirect=true";
        }
    }
}
