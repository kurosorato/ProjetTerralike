package level;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import res.RessourceManager;
import state.Playing;
import utility.Constantes;


public class Level {

    private RessourceManager ressourceManager;
    private LevelManager levelManager;

    private BufferedImage[] backgrounds = new BufferedImage[Constantes.NB_BACKGROUNDS];
    private int backgroundX = 0;
    private int backgroundY = 0;

    //private BufferedImage[] shaders;

    public Tile[] levelTilesTab;
    protected int level_height;
    protected int level_width;

    // l'offset du niveau
    public int offsetX = 0;
    public int offsetY = 0;

    // ce qui est affichier a l'ecran
    public int[][] levelData;

    //private ArrayList<BufferedImage> level = new ArrayList<>();



    public Level(Playing playing) {
        this.ressourceManager = playing.getGameManager().getRessourceManager();
        this.levelManager = new LevelManager(this.ressourceManager, this);

        //permet de calculer le nombre de tuile sur l'ecan + 4 (+4 pour genere le terrain hors de l'ecran )
        this.level_height =  (Constantes.FRAME_HEIGHT/Constantes.TILES_SIZE /Constantes.GAME_SIZE)+4;
        this.level_width = (Constantes.FRAME_WIDTH/Constantes.TILES_SIZE /Constantes.GAME_SIZE)+4;

        //permet de cree la matrice du niveau
        this.levelData = new int[level_height][level_width];

        this.backgrounds = levelManager.loadBackgrounds();// charge les images du background
        this.levelTilesTab = this.levelManager.loadTiles(); // charge les tiles dans un tableau (de la classe tile)

        levelManager.loadLevelWidth(offsetX);
        //chargerShaders();
    }



    //met a jour le offset x et y selon le deplacement du joueur
    public void updateLevel(int vitesseY, int vitesseX){
        this.offsetX = this.offsetX + vitesseX;
        this.offsetY = this.offsetY + vitesseY;

        // si l'offset depasse la taille d'une case on le remet a 0 et on charge la nouvelle partie du niveau qui étais hors ecran
        if (offsetX >= Constantes.TILES_SIZE*Constantes.GAME_SIZE || offsetX <= -(Constantes.TILES_SIZE*Constantes.GAME_SIZE) ){
            levelManager.loadLevelWidth(offsetX);
            offsetX = 0;
        }
        if (offsetY >= Constantes.TILES_SIZE*Constantes.GAME_SIZE || offsetY <= -(Constantes.TILES_SIZE*Constantes.GAME_SIZE)){
            levelManager.loadLevelHeight(offsetY);
            offsetY = 0;
        }
    }

    public LevelManager getLevelManager() {
        return this.levelManager;
    }



    // affiche le background
    public void drawBackground(Graphics g){
        for (int i = 0; i < backgrounds.length; i++) {
            g.drawImage(backgrounds[i], backgroundX, backgroundY,(int) Constantes.FRAME_WIDTH, (int)Constantes.FRAME_HEIGHT, null);
        }
    }

    //affiche le niveau
    public void drawLevel(Graphics g){
        int x,y;
        for (int i = 0; i < levelData.length; i++) {
            for (int j = 0; j < levelData[0].length; j++) {
                if ( levelData[i][j] > 0){

                    //permet de deplacer la matrice selon le mouvement du joueur
                    x = j*Constantes.TILES_SIZE*Constantes.GAME_SIZE + this.offsetX - Constantes.TILES_SIZE*Constantes.GAME_SIZE;
                    y = i*Constantes.TILES_SIZE*Constantes.GAME_SIZE + this.offsetY - Constantes.TILES_SIZE*Constantes.GAME_SIZE;

                    //dessine le niveau
                    g.drawImage(levelTilesTab[levelData[i][j]].tile_Image, x, y, Constantes.TILES_SIZE*Constantes.GAME_SIZE, Constantes.TILES_SIZE*Constantes.GAME_SIZE, null);

                    // affiche la hitbox de chaque case
                    //g.drawRect(x, y, Constantes.TILES_SIZE*Constantes.GAME_SIZE, Constantes.TILES_SIZE*Constantes.GAME_SIZE);
                }
            }
        }
    }

}
