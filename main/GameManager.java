package main;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.MouseEvent;

import res.RessourceManager;
import state.GameStats;
import state.MenuPrincipal;
import state.Playing;



public class GameManager {
    private Game game;
    private RessourceManager ressourceManager = new RessourceManager();
    private Playing playing;
    private MenuPrincipal menuPrincipal;

    public GameManager(Game game) throws IOException{
        this.game = game;
        this.playing = new Playing(this);
        this.menuPrincipal = new MenuPrincipal(this);

        BufferedImage cursor = ressourceManager.importImages("Ui/CursorDefault.png");
        changerCurseur(cursor);

        
    }

    public void focus(){
        game.getGameInterface().requestFocusInWindow();
    }

    public Playing getPlaying(){
        return playing;
    }

    public MenuPrincipal getMenuPrincipal(){
        return menuPrincipal;
    }

    public Game getGame(){
        return this.game;
    }

    public RessourceManager getRessourceManager(){
        return this.ressourceManager;
    }


    ////////pour les touches ////////////////////

    public void GameMouseMouved(int x, int y){
		switch (GameStats.state) {
            case MENU -> menuPrincipal.MouseMouved(x,y);
            case PLAYING -> playing.MouseMouved(x,y);
        }
    }

    public void GameMousePressed(MouseEvent e){
		switch (GameStats.state) {
            case MENU -> menuPrincipal.mousePressed(e);
            case PLAYING -> playing.mousePressed(e);
        }
    }

    public void GameKeybordPress(KeyEvent e){
        switch (GameStats.state) {
            case MENU -> menuPrincipal.keyRelease(e);
            case PLAYING -> playing.keyPressed(e);
        }
    }

    public void GameKeyRelease(KeyEvent e){
        switch (GameStats.state) {
            case MENU -> menuPrincipal.keyRelease(e);
            case PLAYING -> playing.keyRelease(e);
        }
    }

    ///////////////////////////////////////////////////



    ////cette methode est appeler dans la boucle pour mettre a jouer les positions, animation, etc....
    public void update(){
        focus();
		switch (GameStats.state) {
            case MENU -> menuPrincipal.update();
            case PLAYING -> playing.update();
        }

    }
    
    //cette methode est appeler dans la boucle pour redessiner ce qu'il y a l'ecran
    public void redessine(Graphics g){
		switch (GameStats.state) {
            case MENU -> menuPrincipal.redessine(g);
            case PLAYING -> playing.redessine(g);
        }
    }

    public void changerCurseur(BufferedImage cursor){
        Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(0, 0), "Custom Cursor");
        game.getGameInterface().setCursor(customCursor);
        
    }
    
}
