package epn.edu.ec.servicios;

import epn.edu.ec.modelo.AdolescenteInfractor;
import epn.edu.ec.modelo.EjecucionMedidaCAI;
import epn.edu.ec.modelo.Reporte1;
import epn.edu.ec.modelo.Reporte2;
import epn.edu.ec.modelo.Reporte3;
import epn.edu.ec.modelo.Reporte4;
import epn.edu.ec.modelo.Reporte5;
import epn.edu.ec.modelo.Reporte6N;
import epn.edu.ec.modelo.Reporte6S;
import epn.edu.ec.modelo.Reporte7;
import epn.edu.ec.modelo.Reporte8;
import epn.edu.ec.utilidades.URLServicios;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class AdolescenteInfractorServicio {

    private final ConexionServicio<AdolescenteInfractor> conexion;
    private final ConexionServicio<Reporte1> conexionR1;
    private final ConexionServicio<Reporte2> conexionR2;
    private final ConexionServicio<Date> conexionFecha;
    private final ConexionServicio<String> conexionString;
    private final ConexionServicio<EjecucionMedidaCAI> conexionMedida;
    private static final String URL_ADOLESCENTE_INFRACTOR = URLServicios.URL_ADOLESCENTE;

    public AdolescenteInfractorServicio() {
        conexion = new ConexionServicio<>();
        conexionR1 = new ConexionServicio<>();
        conexionR2 = new ConexionServicio<>();
        conexionFecha=new ConexionServicio<>();
        conexionString=new ConexionServicio<>();
        conexionMedida= new ConexionServicio<>();
        
    }

    public AdolescenteInfractor guardarAdolescenteInfractorUDI(AdolescenteInfractor adolescenteInfractor) {
        
        try {
            AdolescenteInfractor adolescenteInfractorAux = null;
            Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR, "POST", true, adolescenteInfractor);
            if (response != null) {
                if (response.getStatus() == 200) {
                    adolescenteInfractorAux = response.readEntity(AdolescenteInfractor.class);
                }
            }
            return adolescenteInfractorAux;
        } catch (Exception e) {
            return null;
        }       
    }

    public List<Reporte1> reporteTipoDelitoUDI(Reporte1 tipoDelito) {
        
        List<Reporte1> reporteAux = null;
        
        Response response = conexionR1.conexion(URL_ADOLESCENTE_INFRACTOR + "/reporteTipoDelitoUDI", "POST", true, tipoDelito);
        if (response != null) {
            if (response.getStatus() == 200) {
                reporteAux = response.readEntity(new GenericType<List<Reporte1>>() {
                });
            } else if (response.getStatus() == 204) {
                reporteAux = null;
            }
        }
        return reporteAux;
    }
    
    public List<Reporte1> reporteTipoDelitoCAI(Reporte1 tipoDelito) {
        List<Reporte1> reporteAux = null;
        
        Response response = conexionR1.conexion(URL_ADOLESCENTE_INFRACTOR + "/reporteTipoDelitoCAI", "POST", true, tipoDelito);
        if (response != null) {
            if (response.getStatus() == 200) {
                reporteAux = response.readEntity(new GenericType<List<Reporte1>>() {
                });
            } else if (response.getStatus() == 204) {
                reporteAux = null;
            }
        }
        return reporteAux;
    }
    
    public List<Reporte2> reporteEdadCAI(Reporte2 edad) {
        List<Reporte2> reporteAux = null;

        Response response = conexionR2.conexion(URL_ADOLESCENTE_INFRACTOR + "/reporteEdadCAI", "POST", true, edad);
        if (response != null) {
            if (response.getStatus() == 200) {
                reporteAux = response.readEntity(new GenericType<List<Reporte2>>() {
                });
            } else if (response.getStatus() == 204) {
                reporteAux = null;
            }
        }
        return reporteAux;
    }
    
    public List<Reporte2> reporteEdadUDI(Reporte2 edad) {
        List<Reporte2> reporteAux = null;
        
        Response response = conexionR2.conexion(URL_ADOLESCENTE_INFRACTOR + "/reporteEdadUDI", "POST", true, edad);
        if (response != null) {
            if (response.getStatus() == 200) {
                reporteAux = response.readEntity(new GenericType<List<Reporte2>>() {
                });
            } else if (response.getStatus() == 204) {
                reporteAux = null;
            }
        }
        return reporteAux;
    }
    
    public List<Reporte2> reporteEdadFechaUDI(Date edad) {
        List<Reporte2> reporteAux = null;
        
        Response response = conexionFecha.conexion(URL_ADOLESCENTE_INFRACTOR + "/reporteEdadFechaUDI", "POST", true, edad);
        if (response != null) {
            if (response.getStatus() == 200) {
                reporteAux = response.readEntity(new GenericType<List<Reporte2>>() {
                });
            } else if (response.getStatus() == 204) {
                reporteAux = null;
            }
        }
        return reporteAux;
    }
    
    public List<Reporte2> reporteEdadFechaCAI(Date edad) {
        List<Reporte2> reporteAux = null;
        
        Response response = conexionFecha.conexion(URL_ADOLESCENTE_INFRACTOR + "/reporteEdadFechaCAI", "POST", true, edad);
        if (response != null) {
            if (response.getStatus() == 200) {
                reporteAux = response.readEntity(new GenericType<List<Reporte2>>() {
                });
            } else if (response.getStatus() == 204) {
                reporteAux = null;
            }
        }
        return reporteAux;
    }
    
    public List<Reporte3> reporteNacionalidadCAI(String nacionalidad) {
        List<Reporte3> reporteAux = null;
        
        Response response = conexionString.conexion(URL_ADOLESCENTE_INFRACTOR + "/reporteNacionalidadCAI", "POST", true, nacionalidad);
        if (response != null) {
            if (response.getStatus() == 200) {
                reporteAux = response.readEntity(new GenericType<List<Reporte3>>() {
                });
            } else if (response.getStatus() == 204) {
                reporteAux = null;
            }
        }
        return reporteAux;
    }
    
    public List<Reporte3> reporteNacionalidadUDI(String nacionalidad) {
        List<Reporte3> reporteAux = null;
        
        Response response = conexionString.conexion(URL_ADOLESCENTE_INFRACTOR + "/reporteNacionalidadUDI", "POST", true, nacionalidad);
        if (response != null) {
            if (response.getStatus() == 200) {
                reporteAux = response.readEntity(new GenericType<List<Reporte3>>() {
                });
            } else if (response.getStatus() == 204) {
                reporteAux = null;
            }
        }
        return reporteAux;
    }
    
    public List<Reporte4> reporteMedidasSocioeducativasUDI(String medidaSocioeducativa) {
        List<Reporte4> reporteAux = null;
        
        Response response = conexionString.conexion(URL_ADOLESCENTE_INFRACTOR + "/reporteMedidaSocioeducativaUDI", "POST", true, medidaSocioeducativa);
        if (response != null) {
            if (response.getStatus() == 200) {
                reporteAux = response.readEntity(new GenericType<List<Reporte4>>() {
                });
            } else if (response.getStatus() == 204) {
                reporteAux = null;
            }
        }
        return reporteAux;
    }
    
    public List<Reporte4> reporteMedidasSocioeducativasCAI(EjecucionMedidaCAI medidaSocioeducativa) {
        List<Reporte4> reporteAux = null;
        
        Response response = conexionMedida.conexion(URL_ADOLESCENTE_INFRACTOR + "/reporteMedidaSocioeducativaCAI", "POST", true, medidaSocioeducativa);
        if (response != null) {
            if (response.getStatus() == 200) {
                reporteAux = response.readEntity(new GenericType<List<Reporte4>>() {
                });
            } else if (response.getStatus() == 204) {
                reporteAux = null;
            }
        }
        return reporteAux;
    }
    
    public List<Reporte5> reporteFechaIngresoCAI(Date fechaIngreso) {
        List<Reporte5> reporteAux = null;
        
        Response response = conexionFecha.conexion(URL_ADOLESCENTE_INFRACTOR + "/reporteFechaIngesoCAI", "POST", true, fechaIngreso);
        if (response != null) {
            if (response.getStatus() == 200) {
                reporteAux = response.readEntity(new GenericType<List<Reporte5>>() {
                });
            } else if (response.getStatus() == 204) {
                reporteAux = null;
            }
        }
        return reporteAux;
    }
    
    public List<Reporte6S> reporteNivelEducacionSiCAI(String nivelEducacion) {
        List<Reporte6S> reporteAux = null;
        
        Response response = conexionString.conexion(URL_ADOLESCENTE_INFRACTOR + "/reporteNivelEducacionSCAI", "POST", true, nivelEducacion);
        if (response != null) {
            if (response.getStatus() == 200) {
                reporteAux = response.readEntity(new GenericType<List<Reporte6S>>() {
                });
            } else if (response.getStatus() == 204) {
                reporteAux = null;
            }
        }
        return reporteAux;
    }
    
    public List<Reporte6N> reporteNivelEducacionNoCAI(String nivelEducacion) {
        List<Reporte6N> reporteAux = null;
        
        Response response = conexionString.conexion(URL_ADOLESCENTE_INFRACTOR + "/reporteNivelEducacionNCAI", "POST", true, nivelEducacion);
        if (response != null) {
            if (response.getStatus() == 200) {
                reporteAux = response.readEntity(new GenericType<List<Reporte6N>>() {
                });
            } else if (response.getStatus() == 204) {
                reporteAux = null;
            }
        }
        return reporteAux;
    }
    
    public List<Reporte6S> reporteNivelEducacionSiUDI(String nivelEducacion) {
        List<Reporte6S> reporteAux = null;
        
        Response response = conexionString.conexion(URL_ADOLESCENTE_INFRACTOR + "/reporteNivelEducacionSUDI", "POST", true, nivelEducacion);
        if (response != null) {
            if (response.getStatus() == 200) {
                reporteAux = response.readEntity(new GenericType<List<Reporte6S>>() {
                });
            } else if (response.getStatus() == 204) {
                reporteAux = null;
            }
        }     
        return reporteAux;
    }
    
    public List<Reporte6N> reporteNivelEducacionNoUDI(String nivelEducacion) {
        List<Reporte6N> reporteAux = null;
        
        Response response = conexionString.conexion(URL_ADOLESCENTE_INFRACTOR + "/reporteNivelEducacionNUDI", "POST", true, nivelEducacion);
        if (response != null) {
            if (response.getStatus() == 200) {
                reporteAux = response.readEntity(new GenericType<List<Reporte6N>>() {
                });
            } else if (response.getStatus() == 204) {
                reporteAux = null;
            }
        }   
        return reporteAux;
    }
    
    public List<Reporte6S> reporteEdadNivelEducacionSiCAI(String edad) {
        List<Reporte6S> reporteAux = null;
        
        Response response = conexionString.conexion(URL_ADOLESCENTE_INFRACTOR + "/reporteEdadNivelEducativoSiCAI", "POST", true, edad);
        if (response != null) {
            if (response.getStatus() == 200) {
                reporteAux = response.readEntity(new GenericType<List<Reporte6S>>() {
                });
            } else if (response.getStatus() == 204) {
                reporteAux = null;
            }
        }   
        return reporteAux;
    }
    
    public List<Reporte6N> reporteEdadNivelEducacionNoCAI(String edad) {
        List<Reporte6N> reporteAux = null;
        
        Response response = conexionString.conexion(URL_ADOLESCENTE_INFRACTOR + "/reporteEdadNivelEducativoNoCAI", "POST", true, edad);
        if (response != null) {
            if (response.getStatus() == 200) {
                reporteAux = response.readEntity(new GenericType<List<Reporte6N>>() {
                });
            } else if (response.getStatus() == 204) {
                reporteAux = null;
            }
        }
        return reporteAux;
    }
    
    public List<Reporte6S> reporteEdadNivelEducacionSiUDI(String edad) {
        List<Reporte6S> reporteAux = null;
        
        Response response = conexionString.conexion(URL_ADOLESCENTE_INFRACTOR + "/reporteEdadNivelEducativoSiUDI", "POST", true, edad);
        if (response != null) {
            if (response.getStatus() == 200) {
                reporteAux = response.readEntity(new GenericType<List<Reporte6S>>() {
                });
            } else if (response.getStatus() == 204) {
                reporteAux = null;
            }
        }
        return reporteAux;
    }
    
    public List<Reporte6N> reporteEdadNivelEducacionNoUDI(String edad) {
        List<Reporte6N> reporteAux = null;
        
        Response response = conexionString.conexion(URL_ADOLESCENTE_INFRACTOR + "/reporteEdadNivelEducativoNoUDI", "POST", true, edad);
        if (response != null) {
            if (response.getStatus() == 200) {
                reporteAux = response.readEntity(new GenericType<List<Reporte6N>>() {
                });
            } else if (response.getStatus() == 204) {
                reporteAux = null;
            }
        }
        return reporteAux;
    }
    
    public List<Reporte7> reporteLugarResidenciaCAI() {
        List<Reporte7> reporteAux = null;
        Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR+"/reporteLugarResidenciaCAI", "GET", true, null);
        if (response != null) {
            if (response.getStatus() == 200) {
                reporteAux = response.readEntity(new GenericType<List<Reporte7>>() {
                });
            } else if (response.getStatus() == 204) {
                reporteAux = null;
            }
        }
        return reporteAux;
    }
    
    public List<Reporte7> reporteLugarResidenciaUDI() {
        List<Reporte7> reporteAux = null;
        
        Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR+"/reporteLugarResidenciaUDI", "GET", true, null);
        if (response != null) {
            if (response.getStatus() == 200) {
                reporteAux = response.readEntity(new GenericType<List<Reporte7>>() {
                });
            } else if (response.getStatus() == 204) {
                reporteAux = null;
            }
        }
        return reporteAux;
    }
    
    public List<Reporte8> reporteInformesCompletos() {
        List<Reporte8> reporteAux = null;
        
        Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR+"/reporteInformesCompletos", "GET", true, null);
        if (response != null) {
            if (response.getStatus() == 200) {
                reporteAux = response.readEntity(new GenericType<List<Reporte8>>() {
                });
            } else if (response.getStatus() == 204) {
                reporteAux = null;
            }
        }
        return reporteAux;
    }
    
    public int eliminarAdolescenteInfractor(Integer id) {

        int statusRespuesta = 0;
        Response response = conexion.conexion(URL_ADOLESCENTE_INFRACTOR+"/"+id.toString(), "DELETE", true, null);
        if (response != null) {
            if (response.getStatus() == 200 || response.getStatus() == 204) {
                statusRespuesta = 200;
            }
        }    
        return statusRespuesta;
    }
}
