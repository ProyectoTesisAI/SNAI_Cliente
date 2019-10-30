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
import epn.edu.ec.utilidades.Constantes;
import epn.edu.ec.utilidades.EnlacesPrograma;
import epn.edu.ec.utilidades.PasswordGenerator;
import epn.edu.ec.utilidades.PermisosUsuario;
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
import org.apache.commons.codec.digest.DigestUtils;

@Named(value = "usuarioControlador")
@ViewScoped
public class UsuarioControlador implements Serializable {

    private Usuario usuario;
    private List<Usuario> listaUsuariosActivos;
    private List<Usuario> listaUsuariosDesactivos;
    private UsuarioServicio servicioUsuario;

    private RolCentroUsuario rolCentroUsuario;
    private RolCentroUsuarioServicio servicioRCU;

    private String mensajeCedula;
    private String mensajeTelefono;
    private Validaciones validacion;

    private EnlacesPrograma enlaces;
    private PermisosUsuario permisos;

    private String tipoRol;
    private boolean estadoAdmin;
    private boolean esUZDI;
    private boolean guardado;

    private List<UDI> listaUdi;
    private List<CAI> listaCai;
    private CaiServicio servicioCai;
    private UdiServicio servicioUdi;
    
    PasswordGenerator generador;
    String passAutogenerado;
    String usuarioCreado;

    @PostConstruct
    public void init() {
        permisos= new PermisosUsuario();
        servicioUsuario = new UsuarioServicio();
        usuario = new Usuario();
        servicioRCU = new RolCentroUsuarioServicio();
        rolCentroUsuario = new RolCentroUsuario();
        Rol rol = new Rol();
        rol.setRol(Constantes.ROL_ADMINISTRADOR);
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
        listaUsuariosActivos = new ArrayList<>();
        listaUsuariosDesactivos = new ArrayList<>();
        listaUsuariosActivos = servicioUsuario.listaUsuariosActivos();
        listaUsuariosDesactivos = servicioUsuario.listaUsuariosDesactivados();
        generador = new PasswordGenerator(10);
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
        
        if (Constantes.ROL_ADMINISTRADOR.equals(rolCentroUsuario.getIdRol().getRol()) || Constantes.ROL_SUBDIRECTOR.equals(rolCentroUsuario.getIdRol().getRol()) || Constantes.ROL_LIDER_UZDI.equals(rolCentroUsuario.getIdRol().getRol()) || Constantes.ROL_DIRECTOR_CAI.equals(rolCentroUsuario.getIdRol().getRol()) || Constantes.ROL_DIRECTOR_UZDI.equals(rolCentroUsuario.getIdRol().getRol()) || Constantes.ROL_COORDINADOR_CAI.equals(rolCentroUsuario.getIdRol().getRol())) {
            estadoAdmin = true;
        } else {
            estadoAdmin = false;
        }
    }

    public boolean isEstadoAdmin() {
        if (Constantes.ROL_ADMINISTRADOR.equals(rolCentroUsuario.getIdRol().getRol()) || Constantes.ROL_SUBDIRECTOR.equals(rolCentroUsuario.getIdRol().getRol()) || Constantes.ROL_LIDER_UZDI.equals(rolCentroUsuario.getIdRol().getRol()) || Constantes.ROL_DIRECTOR_CAI.equals(rolCentroUsuario.getIdRol().getRol()) || Constantes.ROL_DIRECTOR_UZDI.equals(rolCentroUsuario.getIdRol().getRol()) || Constantes.ROL_COORDINADOR_CAI.equals(rolCentroUsuario.getIdRol().getRol())) {
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
        if (Constantes.ROL_PSICOLOGO_UZDI.equals(rolCentroUsuario.getIdRol().getRol()) || Constantes.ROL_JURIDICO_UZDI.equals(rolCentroUsuario.getIdRol().getRol()) || Constantes.ROL_TRABAJADOR_SOCIAL_UZDI.equals(rolCentroUsuario.getIdRol().getRol())) {
            esUZDI = true;
            listaUdi = servicioUdi.listaUdi();

        } else if (Constantes.ROL_PSICOLOGO_CAI.equals(rolCentroUsuario.getIdRol().getRol()) || Constantes.ROL_JURIDICO_CAI.equals(rolCentroUsuario.getIdRol().getRol()) || Constantes.ROL_INSPECTOR_EDUCADOR.equals(rolCentroUsuario.getIdRol().getRol()) || Constantes.ROL_TRABAJADOR_SOCIAL_CAI.equals(rolCentroUsuario.getIdRol().getRol())) {
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

    public List<Usuario> getListaUsuariosActivos() {
        return listaUsuariosActivos;
    }

    public List<Usuario> getListaUsuariosDesactivos() {
        return listaUsuariosDesactivos;
    }

    public String getPassAutogenerado() {
        return passAutogenerado;
    }

    public void setPassAutogenerado(String passAutogenerado) {
        this.passAutogenerado = passAutogenerado;
    }
    

    public String getUsuarioCreado() {
        return usuarioCreado;
    }

    public boolean isGuardado() {
        return guardado;
    }

    
    
    //Metodo para cifrar
    private String cifrarContraseña() {
        String password = usuario.getContraseña();
        password = DigestUtils.sha256Hex(password);
        return password;
    }


    public void generarPassword(AjaxBehaviorEvent evento){ 
        if(passAutogenerado == null){
            passAutogenerado=generador.generarPassword();
        }
        
    }
    
    //Métodos para invocar a los diferentes servicios web************
    public void guardarUsuario() {
    
        if (this.usuario != null && this.rolCentroUsuario != null) {
            
            RolCentroUsuario rcuAux = new RolCentroUsuario();
            
            if (Constantes.ROL_ADMINISTRADOR.equals(this.rolCentroUsuario.getIdRol().getRol()) 
                || Constantes.ROL_SUBDIRECTOR.equals(this.rolCentroUsuario.getIdRol().getRol()) 
                || Constantes.ROL_LIDER_UZDI.equals(this.rolCentroUsuario.getIdRol().getRol()) 
                || Constantes.ROL_DIRECTOR_UZDI.equals(this.rolCentroUsuario.getIdRol().getRol()) 
                || Constantes.ROL_DIRECTOR_CAI.equals(this.rolCentroUsuario.getIdRol().getRol()) 
                || Constantes.ROL_COORDINADOR_CAI.equals(this.rolCentroUsuario.getIdRol().getRol())) {
            
                rcuAux = servicioRCU.obtenerRolAdministrativo(rolCentroUsuario);
            
            } else if (Constantes.ROL_PSICOLOGO_UZDI.equals(this.rolCentroUsuario.getIdRol().getRol()) 
                       || Constantes.ROL_JURIDICO_UZDI.equals(this.rolCentroUsuario.getIdRol().getRol()) 
                       || Constantes.ROL_TRABAJADOR_SOCIAL_UZDI.equals(rolCentroUsuario.getIdRol().getRol())) {
            
                rcuAux = servicioRCU.obtenerRolSoloUDI(rolCentroUsuario);

            } else if (Constantes.ROL_PSICOLOGO_CAI.equals(this.rolCentroUsuario.getIdRol().getRol()) 
                       || Constantes.ROL_JURIDICO_CAI.equals(this.rolCentroUsuario.getIdRol().getRol()) 
                       || Constantes.ROL_INSPECTOR_EDUCADOR.equals(this.rolCentroUsuario.getIdRol().getRol()) 
                       || Constantes.ROL_TRABAJADOR_SOCIAL_CAI.equals(rolCentroUsuario.getIdRol().getRol())) {
                rcuAux = servicioRCU.obtenerRolSoloCAI(rolCentroUsuario);
            }
            if (rcuAux != null) {
                
                this.usuario.setContraseña(passAutogenerado);
                String passCifrado = cifrarContraseña();
                this.usuario.setContraseña(passCifrado);
                this.usuario.setIdRolUsuarioCentro(rcuAux);
                this.usuario.setActivo(true);
                Usuario usuarioAux = servicioUsuario.guardarUsuario(this.usuario);
                if (usuarioAux != null) {
                    guardado=true;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_INFO, "Se ha guardado correctamente el Usuario ", "Aviso"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Ha ocurrido un error, no se guardó el Usuario", "Error"));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Ha ocurrido un error, no se guardó el Usuario", "Error"));
            }
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "No ha ingresado todos los datos, verifique y vuelta a intentarlo", 
                "Error"));
        }
    }

    public String redireccionPanelAdministracion() throws InterruptedException {
        if(guardado==true){
            Thread.sleep(1250);
            String rol = permisos.RolUsuario();
            if (rol != null) {
                if (rol.equals(Constantes.ROL_ADMINISTRADOR)) {
                    return enlaces.PATH_PANEL_USUARIO_NUEVO+"?faces-redirect=true";
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
        else{
            return null;
        }
        
    }
    
    public String DesactivarUsuario(Usuario usuario) {
        if (usuario != null && usuario.getActivo() == true) {
            usuario.setActivo(false);
            Usuario usuarioAux = servicioUsuario.desactivarUsuario(usuario);
            if (usuarioAux != null) {
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Se ha guardado correctamente el Usuario ", "Aviso"));
                
                return enlaces.PATH_PANEL_USUARIO_NUEVO + "?faces-redirect=true";
            
            } else {
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Ha ocurrido un error, no se guardó el Usuario", "Error"));
                return null;
            }
        } else {
            return enlaces.PATH_ERROR + "?faces-redirect=true";
        }
    }

    public String ActivarUsuario(Usuario usuario) {
        if (usuario != null && usuario.getActivo() == false) {
            usuario.setActivo(true);
            Usuario usuarioAux = servicioUsuario.activarUsuario(usuario);
            if (usuarioAux != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioDesactivado", usuario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado correctamente el Usuario ", "Aviso"));
                return enlaces.PATH_PANEL_USUARIO_NUEVO + "?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ha ocurrido un error, no se guardó el Usuario", "Error"));
                return null;
            }
        } else {
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
