package res;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.awt.image.BufferedImage;

public class RessourceManager {

    //level
    public static final String[] BACKGROUNDS = {"background_layer_1.png", "background_layer_2.png", "background_layer_3.png"};
    public static final String LEVELTILES = "blocks.png";
    public static final String SHADERS = "shaders.png";

    //extention fichier
    public static final String PNG = ".png";

    public RessourceManager(){
    }


    //permet de charger une image puis la renvoi sous forme de bufferedImage
    public BufferedImage importImages(String img){
        BufferedImage image = null;
        InputStream x = getClass().getResourceAsStream(img);
        try {
            image = ImageIO.read(x);
        } catch (IOException e) {
            System.out.println("le fichier "+ img +" n'a pas été trouvé !");
        }
        return image;
    }

    
}
