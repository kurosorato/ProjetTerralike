package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputs implements KeyListener {
  private GameInterface gameinterface;





  public KeyInputs(GameInterface gameinterface){
    this.gameinterface = gameinterface;
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override 
  public void keyPressed(KeyEvent e) {
    gameinterface.getGame().getGameManager().GameKeybordPress(e);
  }

  @Override
  public void keyReleased(KeyEvent e) {
    gameinterface.getGame().getGameManager().GameKeyRelease(e);
  }
}
