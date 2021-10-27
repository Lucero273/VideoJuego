package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class HojaSprites {
    private final int ancho;
    private final int alto;
    public final int[] pixeles; //declaramos el arreglo de pixeles que tendrá la imagen
    
    //Coleccion de hojas de Sprites
    public static HojaSprites madera = new HojaSprites("/texturas/hojasprites.png", 256, 256);
    
    //fin de la coleccion
    
    public HojaSprites(final String ruta, final int ancho, final int alto){
        this.ancho = ancho;
        this.alto = alto;
        
        //inicializamos el arreglo
        pixeles = new int[ancho * alto];
        
       
        BufferedImage imagen;
        try {
            imagen = ImageIO.read(HojaSprites.class.getResource(ruta));
            
            imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);

            
        } catch (IOException ex) {
            Logger.getLogger(HojaSprites.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int obtenAncho(){
        return ancho;
}
    
}
