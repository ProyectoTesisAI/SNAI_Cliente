package epn.edu.ec.utilidades;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Validaciones {

    public Boolean cedulaValida(String cedula) {
        int total = 0;
        int tamanoLongitudCedula = 10;
        int[] coeficientes = {2, 1, 2, 1, 2, 1, 2, 1, 2};
        int numeroProvincias = 24;
        int tercerdigito = 6;
        if (cedula.matches("[0-9]*") && cedula.length() == tamanoLongitudCedula) {
            int provincia = Integer.parseInt(cedula.charAt(0) + "" + cedula.charAt(1));
            int digitoTres = Integer.parseInt(cedula.charAt(2) + "");
            if ((provincia > 0 && provincia <= numeroProvincias) && digitoTres < tercerdigito) {
                int digitoVerificadorRecibido = Integer.parseInt(cedula.charAt(9) + "");
                for (int i = 0; i < coeficientes.length; i++) {
                    int valor = Integer.parseInt(coeficientes[i] + "") * Integer.parseInt(cedula.charAt(i) + "");
                    total = valor >= 10 ? total + (valor - 9) : total + valor;
                }
                int digitoVerificadorObtenido = total >= 10 ? (total % 10) != 0 ? 10 - (total % 10) : (total % 10) : total;
                if (digitoVerificadorObtenido == digitoVerificadorRecibido) {
                    return true;
                }
            }
            return false;

        } else {
            return false;
        }
    }

    public Integer obtenerEdad(Date fechaNacimiento) {
        Integer edad = 0;
        if (fechaNacimiento != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss z uuuu").withLocale(Locale.US);
            ZonedDateTime zdt = ZonedDateTime.parse(fechaNacimiento.toString(), dtf);
            LocalDate ld = zdt.toLocalDate();

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fecha = ld.format(fmt);
            LocalDate fechaNac = LocalDate.parse(fecha, fmt);
            LocalDate ahora = LocalDate.now();

            Period periodo = Period.between(fechaNac, ahora);
            edad = periodo.getYears();
            return edad;
        }
        return edad;
    }

    public Boolean verificarFechaNacimiento(Date fechaNacimiento) {
        Boolean verificarFechaMenor = false;
        Boolean verificarEdad = false;
        Integer edad = 0;
        if (fechaNacimiento != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss z uuuu").withLocale(Locale.US);
            ZonedDateTime zdt = ZonedDateTime.parse(fechaNacimiento.toString(), dtf);
            LocalDate ld = zdt.toLocalDate();

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fecha = ld.format(fmt);
            LocalDate fechaNac = LocalDate.parse(fecha, fmt);
            LocalDate ahora = LocalDate.now();
            if (fechaNac.isBefore(ahora)) {
                verificarFechaMenor = true;
                //para saber si la edad es la correcta
                Period periodo = Period.between(fechaNac, ahora);
                edad = periodo.getYears();
                if (edad < 18) {
                    verificarEdad = true;
                } else {
                    verificarEdad = false;
                }
            } else {
                verificarFechaMenor = false;
                verificarEdad = false;
            }

            //establecer la salida
            if (verificarEdad == true && verificarFechaMenor == true) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public Boolean numeroContactoValida(String numero) {
        int total = 0;
        int tamanoMinimo = 9;
        if (numero.matches("[0-9]*") && numero.length() >= tamanoMinimo) {
            return true;
        } else {
            return false;
        }
    }
    
    public Boolean verificadorSoloNumeros(String numero) {
        if (numero.matches("[0-9]*")) {
            return true;
        } else {
            return false;
        }
    }
}
