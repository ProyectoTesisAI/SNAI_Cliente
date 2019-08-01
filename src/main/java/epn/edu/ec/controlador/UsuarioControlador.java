package epn.edu.ec.controlador;

import epn.edu.ec.modelo.CAI;
import epn.edu.ec.modelo.Rol;
import epn.edu.ec.modelo.RolCentroUsuario;
import epn.edu.ec.modelo.UDI;
import epn.edu.ec.modelo.Usuario;
import epn.edu.ec.servicios.CaiServicio;
import epn.edu.ec.servicios.RolCentroUsuarioServicio;
import epn.edu.ec.servicios.UdiServicio;
import epn.edu.ec.servicios.UsuarioServicio;
import epn.edu.ec.utilidades.EnlacesPrograma;
import epn.edu.ec.utilidades.Validaciones;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "usuarioControlador")
@ViewScoped
public class UsuarioControlador implements Serializable {

    private Usuario usuario;
    private UsuarioServicio servicioUsuario;

    private RolCentroUsuario rolCentroUsuario;
    private RolCentroUsuarioServicio servicioRCU;

    private String mensajeCedula;
    private String mensajeTelefono;
    private Validaciones validacion;

    private EnlacesPrograma enlaces;

    private String tipoRol;
    private boolean estadoAdmin;
    private boolean esUZDI;

    private List<UDI> listaUdi;
    private List<CAI> listaCai;
    private CaiServicio servicioCai;
    private UdiServicio servicioUdi;

    @PostConstruct
    public void init() {
        servicioUsuario = new UsuarioServicio();
        usuario = new Usuario();
        servicioRCU = new RolCentroUsuarioServicio();
        rolCentroUsuario = new RolCentroUsuario();
        Rol rol = new Rol();
        rol.setRol("ADMINISTRADOR");
        rolCentroUsuario.setIdRol(rol);
        enlaces = new EnlacesPrograma();
        validacion = new Validaciones();
        mensajeCedula = null;
        mensajeTelefono = null;
        estadoAdmin = true;
        servicioCai = new CaiServicio();
        servicioUdi = new UdiServicio();
        listaUdi = new ArrayList<>();
        listaCai = new ArrayList<>();
        listaUdi = servicioUdi.listaUdi(); //muestro la lista de UDIs rescatadas de la base de datos
        listaCai = servicioCai.listaCai(); //muestro la lista de CAIs rescatadas de la base de datos
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public RolCentroUsuario getRolCentroUsuario() {
        return rolCentroUsuario;
    }

    public void setRolCentroUsuario(RolCentroUsuario rolCentroUsuario) {
        this.rolCentroUsuario = rolCentroUsuario;
    }

    public String getMensajeCedula() {
        return mensajeCedula;
    }

    public void setMensajeCedula(String mensajeCedula) {
        this.mensajeCedula = mensajeCedula;
    }

    public String getMensajeTelefono() {
        return mensajeTelefono;
    }

    public void setMensajeTelefono(String mensajeTelefono) {
        this.mensajeTelefono = mensajeTelefono;
    }

    public String getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
        this.rolCentroUsuario.getIdRol().setRol(tipoRol);
        if ("ADMINISTRADOR".equals(rolCentroUsuario.getIdRol().getRol()) || "SUBDIRECTOR".equals(rolCentroUsuario.getIdRol().getRol()) || "COORDINADOR/LIDER UZDI".equals(rolCentroUsuario.getIdRol().getRol()) || "COORDINADOR CAI".equals(rolCentroUsuario.getIdRol().getRol())) {
            estadoAdmin = true;
        } else {
            estadoAdmin = false;
        }
    }

    public boolean isEstadoAdmin() {
        if ("ADMINISTRADOR".equals(rolCentroUsuario.getIdRol().getRol()) || "SUBDIRECTOR".equals(rolCentroUsuario.getIdRol().getRol()) || "COORDINADOR/LIDER UZDI".equals(rolCentroUsuario.getIdRol().getRol()) || "COORDINADOR CAI".equals(rolCentroUsuario.getIdRol().getRol())) {
            estadoAdmin = true;
        } else {
            estadoAdmin = false;
        }
        return estadoAdmin;
    }

    public void setEstadoAdmin(boolean estadoAdmin) {
        this.estadoAdmin = estadoAdmin;
    }

    public boolean isEsUZDI() {
        if ("EQUIPO TECNICO PSICOLOGO UZDI".equals(rolCentroUsuario.getIdRol().getRol()) || "EQUIPO TECNICO JURIDICO UZDI".equals(rolCentroUsuario.getIdRol().getRol())) {
            esUZDI = true;
            listaUdi = servicioUdi.listaUdi();

        } else if ("EQUIPO TECNICO PSICOLOGO CAI".equals(rolCentroUsuario.getIdRol().getRol()) || "EQUIPO TECNICO JURIDICO CAI".equals(rolCentroUsuario.getIdRol().getRol()) || "INSPECTOR EDUCADOR".equals(rolCentroUsuario.getIdRol().getRol())) {
            esUZDI = false;
            listaCai = servicioCai.listaCai();
        }
        return esUZDI;
    }

    public List<UDI> getListaUdi() {
        return listaUdi;
    }

    public List<CAI> getListaCai() {
        return listaCai;
    }

    //Métodos para invocar a los diferentes servicios web************
    public String guardarUsuario() {
        if (this.usuario != null && this.rolCentroUsuario != null) {
            RolCentroUsuario rcuAux = new RolCentroUsuario();
            if ("ADMINISTRADOR".equals(this.rolCentroUsuario.getIdRol().getRol()) || "SUBDIRECTOR".equals(this.rolCentroUsuario.getIdRol().getRol()) || "COORDINADOR/LIDER UZDI".equals(this.rolCentroUsuario.getIdRol().getRol()) || "COORDINADOR CAI".equals(this.rolCentroUsuario.getIdRol().getRol())) {
                rcuAux = servicioRCU.obtenerRolAdministrativo(rolCentroUsuario);
            } else if ("EQUIPO TECNICO PSICOLOGO UZDI".equals(this.rolCentroUsuario.getIdRol().getRol()) || "EQUIPO TECNICO JURIDICO UZDI".equals(this.rolCentroUsuario.getIdRol().getRol())) {
                rcuAux = servicioRCU.obtenerRolSoloUDI(rolCentroUsuario);

            } else if ("EQUIPO TECNICO PSICOLOGO CAI".equals(this.rolCentroUsuario.getIdRol().getRol()) || "EQUIPO TECNICO JURIDICO CAI".equals(this.rolCentroUsuario.getIdRol().getRol()) || "INSPECTOR EDUCADOR".equals(this.rolCentroUsuario.getIdRol().getRol())) {
                rcuAux = servicioRCU.obtenerRolSoloCAI(rolCentroUsuario);
            }
            if (rcuAux != null) {
                this.usuario.setIdRolUsuarioCentro(rcuAux);
                this.usuario.setActivo(true);
                Usuario usuarioAux = servicioUsuario.guardarUsuario(this.usuario);
                if (usuarioAux != null) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioNuevo", usuarioAux);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado correctamente el Usuario ", "Aviso"));
                    return enlaces.PATH_PANEL_ASGINAR_ROL_CENTRO_A_USUARIO + "?faces-redirect=true";
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ha ocurrido un error, no se guardó el Usuario", "Error"));
                    return null;
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ha ocurrido un error, no se guardó el Usuario", "Error"));
                return null;
            }
        } else {
            System.out.println("Se tiene un usuario en null");
            return enlaces.PATH_ERROR + "?faces-redirect=true";
        }
    }

    public void limpiarMensajeCedula(AjaxBehaviorEvent evento) {
        String cedula = usuario.getCedula();
        if (validacion.cedulaValida(cedula)) {
            mensajeCedula = "";
        } else {
            mensajeCedula = "cédula incorrecta";
        }
    }

    public void validarCedula(AjaxBehaviorEvent evento) {

        String cedula = usuario.getCedula();
        if (validacion.cedulaValida(cedula)) {
            mensajeCedula = "cédula correcta";
        } else {
            mensajeCedula = "cédula incorrecta";
        }
    }

    public void limpiarMensajeNumeroContacto(AjaxBehaviorEvent evento) {
        String numero = usuario.getTelefono();
        if (validacion.numeroContactoValida(numero)) {
            mensajeTelefono = "";
        } else {
            mensajeTelefono = "Número incorrecto";
        }
    }

    public void validarNumeroContacto(AjaxBehaviorEvent evento) {
        String numero = usuario.getTelefono();
        if (validacion.numeroContactoValida(numero)) {
            mensajeTelefono = "Número correcto";
        } else {
            mensajeTelefono = "Número incorrecto";
        }
    }
}
