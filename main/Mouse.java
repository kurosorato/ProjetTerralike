package main;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

public class Mouse implements MouseInputListener {
    private GameInterface gameinterface;

    public Mouse(GameInterface gameinterface){
        this.gameinterface = gameinterface;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        gameinterface.getGame().getGameManager().GameMousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        gameinterface.getGame().getGameManager().GameMouseMouved(e.getX(),e.getY());
        //System.err.println("X="+ e.getX() + " Y="+ e.getY());
    }

}
