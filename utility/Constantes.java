package utility;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Constantes {

    public static final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int FRAME_WIDTH = (int)dim.getWidth();
	public static final int FRAME_HEIGHT = (int)dim.getHeight();

    public static final int NB_BACKGROUNDS = 3;
    public static final int TILES_SIZE = 24;
    public static final int GAME_SIZE = 2;
    
    public static final int LEVEL_DATA_WIDTH = 1000;
    public static final int LEVEL_DATA_HEIGHT = 200;

    public static final int TILES_TEXTURES_NUMBER = 13;

}
