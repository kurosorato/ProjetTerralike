package main;


import javax.swing.*;


public class GameWindow extends JFrame {
    private GameInterface gameInterface;
    
    

    //permet de cree la fenetre 
    public GameWindow(GameInterface gameInterface){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        this.gameInterface = gameInterface;
        add(this.gameInterface);
        pack();
        //setResizable(false);
    }


}
