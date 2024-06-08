package utility;

public class HelpMethods {

    public HelpMethods(level.Level level) {
    }

    public static boolean CanMoveHere(float x, float y, float largeur, float hauteur ,int[][] lvlData, int offsetX , int offsetY) {
        if (!IsSolid(x, y, lvlData, offsetX , offsetY)) {
            if (!IsSolid(x+largeur, y+hauteur, lvlData, offsetX , offsetY)) {
                if (!IsSolid(x+largeur, y, lvlData, offsetX , offsetY)) {
                    if (!IsSolid(x, y+hauteur, lvlData, offsetX , offsetY)) {
                        if (!IsSolid(x+largeur, y+hauteur/2, lvlData, offsetX , offsetY)) {
                            if (!IsSolid(x, y+hauteur/2, lvlData, offsetX , offsetY)) {
                                return true;
                            }
                        }
                        
                    }
                }
            }
        }
        return false;
    }

    private static boolean IsSolid(float x, float y, int[][] lvlData, int offsetX , int offsetY) {
        if (x < 0 || x >= Constantes.FRAME_WIDTH) {
            return true;
        }
        if (y < 0 || y >= Constantes.FRAME_HEIGHT) {
            return true;
        }
        int xIndex = ((int)x-offsetX + Constantes.TILES_SIZE * Constantes.GAME_SIZE) / (Constantes.TILES_SIZE * Constantes.GAME_SIZE) ;
        int yIndex = ((int)y-offsetY + Constantes.TILES_SIZE * Constantes.GAME_SIZE) / (Constantes.TILES_SIZE * Constantes.GAME_SIZE) ;
        int value = lvlData[yIndex][xIndex];
        if (value > 0) {  // id des blocks
            return true;
        }
        return false;
            
    }

}
