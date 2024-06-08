package main;

import javax.swing.JPanel;
import utility.Constantes;
import java.awt.*;



public class GameInterface extends JPanel {
    private Game game;
    
    public GameInterface(Game game){
        this.game = game;
        setWindowDimention();
        addKeyListener(new KeyInputs(this)); // permet de gere les touches du clavier
        Mouse mouse = new Mouse(this);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        requestFocusInWindow();
        // RessourceManager ressourceManager = new RessourceManager();

        // BufferedImage cursor = ressourceManager.importImages("Ui/CursorDefault.png");
        // Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(0, 0), "Custom Cursor");
        // setCursor(customCursor);

    }

    public Game getGame() {
        return game;
    }

    // pour la dimention de la fenetre
    private void setWindowDimention() {
        Dimension dim = new Dimension(Constantes.FRAME_WIDTH, Constantes.FRAME_HEIGHT);
        setPreferredSize(dim);
    }


    //pour dessiner sur l'ecran
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.game.dessine(g);
    }

}