/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.utilidades;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;


/**
 *
 * @author User
 */
public class RecursosEspeciales {
    
    
    public InputStream cambioResolucionImagen(InputStream inputStreamImagen) {

        try {
            
            BufferedImage imagenOriginal = ImageIO.read(inputStreamImagen);
            
            int tipoImagen = ((imagenOriginal.getType() == 0) ? BufferedImage.TYPE_INT_ARGB : imagenOriginal.getType());
            int anchoAux= imagenOriginal.getWidth();
            int altoAux=imagenOriginal.getHeight();
            
            while(anchoAux > 875 || altoAux > 875) {
                
                anchoAux= (int) (anchoAux/1.25F);
                altoAux= (int)(altoAux/1.25F);
                
            }
            
            BufferedImage resizedImage = new BufferedImage(anchoAux, altoAux, tipoImagen);

            Graphics2D g2d = resizedImage.createGraphics();
            g2d.drawImage(imagenOriginal, 0, 0, anchoAux, altoAux, null);
            g2d.dispose();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            ImageIO.write(resizedImage, "jpg", byteArrayOutputStream);
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            
        } catch (IOException e) {
            return inputStreamImagen;
        }
    }

}
