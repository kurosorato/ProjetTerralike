package state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

import level.Level;
import entity.Perso;
import main.GameManager;
import utility.Constantes;


public class Playing {

    private Perso player;
    private Level level;
    private GameManager gameManager;


    public Playing(GameManager gameManager) throws IOException {
        this.gameManager = gameManager;
        this.level = new Level(this);
        this.player = new Perso((int) Constantes.FRAME_WIDTH/2, (int) Constantes.FRAME_HEIGHT/2,null, 10, 10, 224,266,this);
        //this.pnj = new PNJ(500, 500, null, 500, 0, 55, 48, this);

    }


    public Perso getPlayer() {
        return this.player;
    }

    public Level getLevel(){
        return this.level;
    }

    public GameManager getGameManager(){
        return this.gameManager;
    }




    ///////pour les touches + souris ////////

    public void keyPressed(KeyEvent e){
        player.keysPressed(e);
    }

    public void keyRelease(KeyEvent e){
        player.keysReleased(e);
    }

    public void MouseMouved(int x, int y){
        getPlayer().updateMousePosition(x,y);
    }

    public void mousePressed(MouseEvent e){
        if (e.getButton() == MouseEvent.BUTTON1) {
            // Clic gauche
            getPlayer().destroyBlock();
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            // Clic droit
            getPlayer().addBlock();
        }
    }

    //////////////////////////////////////////





    //cette methode est appeler dans la boucle pour mettre a jouer les positions, animation, etc....
    public void update(){
        player.deplacement();
        player.updateAnimationtick();
        //pnj.animation();
        //pnj.deplacement();
        //utility.animation();

    }
    
    //cette methode est appeler dans la boucle pour redessiner ce qu'il y a l'ecran
    public void redessine(Graphics g){
        level.drawBackground(g);
        level.drawLevel(g);
        player.paintComponent(g);
        //pnj.paintComponent(g);
        //utility.afficheD(g);
    }



}
