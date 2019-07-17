package epn.edu.ec.controlador;

import epn.edu.ec.modelo.Personal;
import epn.edu.ec.servicios.PersonalServicio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "personalControlador")
@SessionScoped
public class PersonalControlador implements Serializable {

    private Personal personal;
    private PersonalServicio controlador;

    @PostConstruct
    public void init() {
        personal = new Personal();
        controlador = new PersonalServicio();
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public PersonalServicio getControlador() {
        return controlador;
    }

    public void setControlador(PersonalServicio controlador) {
        this.controlador = controlador;
    }

    public String login(Personal personal) {
        System.out.println("Personal que envio: "+personal);
        return controlador.validarPersonal(personal);
    }
}
