/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.controlador;

import epn.edu.ec.modelo.AdolescenteInfractorUDI;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author User
 */
@Named(value = "reporteControlador")
@ViewScoped
public class ReporteControlador implements Serializable{

    /**
     * Creates a new instance of ReporteControlador
     */
    public ReporteControlador() {
    }
    
    public void exportarPDF(){
        //retorna el path del archivo-->Retorna: "file:D:/User/Documents/NetBeansProjects/SistemaReeducacionAI/SistemaReeducacionAI/src/main/java/epn/edu/ec/reportes/RegistroAsistencia.jasper"
        String ruta=getClass().getClassLoader().getResource("/epn/edu/ec/reportes/RegistroAsistencia.jasper").toString();
        //elimino los 6 primeros caracteres, es decir elimino: "file:/", para obtener solo la ruta del archivo
        ruta= ruta.substring(6); 

        Map<String,Object> parametros= new HashMap<String,Object>();
	parametros.put("txtUDI","REGISTRO DE ASISTENCIA "+ "UZDI");
			       
        try{
            AdolescenteInfractorUDI a= new AdolescenteInfractorUDI();
            a.setNombres("Oscar");
            a.setApellidos("España");
            a.setCedula("10037307");
            
            AdolescenteInfractorUDI b= new AdolescenteInfractorUDI();
            b.setNombres("Javier");
            b.setApellidos("Reyes");
            b.setCedula("1781912034");
            
            List<AdolescenteInfractorUDI> lista= new ArrayList<>();
            lista.add(a);
            lista.add(b);
            
            File jasper = new File(ruta);       
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros, new JRBeanCollectionDataSource(lista));
            
            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();
            if (response instanceof HttpServletResponse) {
                  HttpServletResponse hsr = (HttpServletResponse) response;
                  hsr.setContentType("application/pdf");
                  hsr.addHeader("Content-disposition","attachment; filename=jsfReporte.pdf");
                  try {
                        ServletOutputStream stream = hsr.getOutputStream();
                        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                        stream.flush();
                  } catch (IOException ex) {
                        System.out.println("Error:  " + ex.getMessage());
                  }
                  context.responseComplete();
            }
            
            
            /*JasperExportManager.exportReportToPdfFile(jasperPrint, "D:\\User\\Desktop\\Registro Asistencia "+tallerPsicologia.getIdUdi().getUdi()+".pdf"); // 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SE HA GENERADO CORRECTAMENTE EL REGISTRO DE ASISTENCIA ","Aviso" ));*/
            
        }catch(Exception e){
            System.out.println("Error:  " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "NO SE HA GENERADO EL REGISTRO DE ASISTENCIA","ERROR" ));
        }
    }
    
}
