package level;

import java.awt.image.BufferedImage;
import java.io.*;
import res.RessourceManager;
import utility.Constantes;


public class LevelManager {

    private RessourceManager ressourceManager;
    private Level level;

    public int chunkPosX;
    public int chunkPosY;

    public int[][] level_data;
    //public int[][] shaders_chunk;


    public LevelManager(RessourceManager ressourceManager, Level level){
        this.ressourceManager = ressourceManager;
        this.level =level;
        initChunks();

    }

    
    // renvoi un tableau contenant des tile (de la classe tile) avec un id differents pour chaque tile et son image
    public Tile[] loadTiles(){
        Tile[] res = new Tile[Constantes.TILES_TEXTURES_NUMBER];
        BufferedImage levelTiles = ressourceManager.importImages(RessourceManager.LEVELTILES);
        int x = -1;
        for (int i = 0; i < Constantes.TILES_TEXTURES_NUMBER; i++) {
                x++;
                res[x] = new Tile();
                res[x].id = x;
                //System.out.println(i*Constantes.TILES_SIZE);
                res[x].tile_Image = levelTiles.getSubimage(i*Constantes.TILES_SIZE, 0, Constantes.TILES_SIZE, Constantes.TILES_SIZE);
            }
        return res;
    }

    //renvoi un tableau contenant les images de backgrounds sous forme de BufferedImage
    public BufferedImage[] loadBackgrounds(){
        BufferedImage[] backgrounds = new BufferedImage[Constantes.NB_BACKGROUNDS];
        for (int i = 0; i < Constantes.NB_BACKGROUNDS; i++) {
            backgrounds[i] = ressourceManager.importImages(RessourceManager.BACKGROUNDS[i]);
        }
        return backgrounds;
    }

    public Level getLevelActuel() {
        return level;
    }




    
    //ajout d'une bordure dans la map
    public void bedrock(){
        for (int i = 0; i < Constantes.LEVEL_DATA_WIDTH; i++) {
            for (int j = 0; j < Constantes.LEVEL_DATA_HEIGHT; j++) {
                if(j > Constantes.LEVEL_DATA_HEIGHT-15 || j < 15){
                    level_data[i][j]=12;
                }
                if(i > Constantes.LEVEL_DATA_WIDTH-30 || i<30){
                    level_data[i][j]=12;
                }
            }
        }
    }


    //initialise le level_data
    public void initChunks(){
        level_data = new int[Constantes.LEVEL_DATA_WIDTH][Constantes.LEVEL_DATA_HEIGHT];
        //shaders_chunk = new int[Constantes.LEVEL_DATA_WIDTH][Constantes.LEVEL_DATA_HEIGHT];
        chunkPosY = 50;
        chunkPosX = Constantes.LEVEL_DATA_WIDTH /2 - level.level_width /2;

        //code spécial
        ProcessBuilder pb = new ProcessBuilder();
        pb.command("./a.exe");
        try {
            
        Process process = pb.start();

        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;


        while ((line = br.readLine()) != null) {
            String[] x_y = line.split(", ");
            level_data[Integer.parseInt(x_y[0])][130 - Integer.parseInt(x_y[1])] = 5; 
            level_data[Integer.parseInt(x_y[0])][130 - Integer.parseInt(x_y[1]) + 1 ] = 9;
            level_data[Integer.parseInt(x_y[0])][130 - Integer.parseInt(x_y[1]) + 2 ] = 9;
            level_data[Integer.parseInt(x_y[0])][130 - Integer.parseInt(x_y[1]) + 3 ] = 9;
            //System.out.println(Integer.parseInt(x_y[0]));
            //System.out.println(Integer.parseInt(x_y[1]));
            for (int i = 130 - Integer.parseInt(x_y[1]) + 4; i < 130; i++) {
                level_data[Integer.parseInt(x_y[0])][i] = 10;
            }            



        }

        } catch (Exception e) {
            System.err.println(e);
        }

        System.err.println("niveau charger");
        bedrock();

    }
    





    ///// Charge le niveau qui était hors écran dans la matrice du niveau/////

    public void loadLevelWidth(int val) {
        if (val > 0) {
            // Bloc de code pour décaler les données du niveau vers la gauche
            for (int i = 0; i < level.level_height; i++) {
                for (int j = level.level_width - 1; j >= 0; j--) {
                    level.levelData[i][j] = level_data[chunkPosX + j - 1][chunkPosY + i];

                }
            }

            chunkPosX--;
        } else {
            // Bloc de code pour décaler les données du niveau vers la droite
            for (int i = 0; i < level.level_height; i++) {
                for (int j = 0; j < level.level_width; j++) {
                    level.levelData[i][j] = level_data[chunkPosX + j + 1][chunkPosY + i];

                }
            }
            System.err.println(chunkPosX);
            chunkPosX++; // Revenir en arrière du décalage précédent
        }
    }
    
    public void loadLevelHeight(int val){
        if (val > 0) {
            // Bloc de code pour décaler les données du niveau vers le haut
            for (int j = 0; j < level.level_width; j++) {
                for (int i = level.level_height - 1; i >= 0; i--) {
                        level.levelData[i][j] = level_data[chunkPosX + j][chunkPosY + i-1];
                }
            }
            chunkPosY--;

            // Bloc de code pour décaler les données du niveau vers le bas
        } else {
            for (int j = 0; j < level.level_width; j++) {
                for (int i = 0; i < level.level_height; i++) {

                        level.levelData[i][j] = level_data[chunkPosX + j][chunkPosY + i+1];
                }
            }
            chunkPosY++;
        }
    }

    //////////////////////////////////////////////////////////////////////////
}
