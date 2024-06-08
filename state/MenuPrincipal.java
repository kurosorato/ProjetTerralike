package state;

import javax.swing.*;

import main.GameManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import utility.Constantes;

public class MenuPrincipal {
    
    private JButton playButton;
    private JButton quitButton;
    private BufferedImage layer1;
    private BufferedImage layer2;
    private BufferedImage layer3;


    public MenuPrincipal(GameManager gamemanager) {


        // Charger les images de fond
        try {
            layer1 = ImageIO.read(new File("res/background1.png"));
            layer2 = ImageIO.read(new File("res/background3.png"));
            layer3 = ImageIO.read(new File("res/background4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Redimensionner les images
        layer1 = resizeImage(layer1, Constantes.FRAME_WIDTH, Constantes.FRAME_HEIGHT);
        layer2 = resizeImage(layer2, Constantes.FRAME_WIDTH, Constantes.FRAME_HEIGHT);
        layer3 = resizeImage(layer3, Constantes.FRAME_WIDTH, Constantes.FRAME_HEIGHT);

        // Créer les boutons
        createButtons();

        // Créer et configurer le panneau de boutons

 

        gamemanager.getGame().getGameInterface().setLayout(new BorderLayout());
        gamemanager.getGame().getGameInterface().setPreferredSize(new Dimension(Constantes.FRAME_WIDTH, Constantes.FRAME_HEIGHT));

        // Créer un panneau pour contenir les boutons avec BoxLayout
        gamemanager.getGame().getGameInterface().setLayout(new BoxLayout(gamemanager.getGame().getGameInterface(), BoxLayout.Y_AXIS));
        gamemanager.getGame().getGameInterface().setOpaque(false); // Rendre le panneau transparent
        gamemanager.getGame().getGameInterface().add(Box.createVerticalGlue());
        gamemanager.getGame().getGameInterface().add(playButton);
        gamemanager.getGame().getGameInterface().add(Box.createVerticalStrut(20)); // Espace entre les boutons
        gamemanager.getGame().getGameInterface().add(quitButton);
        gamemanager.getGame().getGameInterface().add(Box.createVerticalGlue());


    }

    private void createButtons() {
        // Créez le bouton JOUER
        playButton = new JButton("  JOUER  ");
        playButton.setFont(new Font("Arial", Font.BOLD, 35));
        playButton.setForeground(Color.WHITE);
        playButton.setBackground(Color.BLUE);
        playButton.setFocusPainted(false);
        playButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        playButton.setPreferredSize(new Dimension(200, 80));
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        playButton.setHorizontalAlignment(SwingConstants.CENTER);
        playButton.setVerticalAlignment(SwingConstants.CENTER);
        playButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                startGame(); // Démarrer le jeu lorsque le bouton est cliqué
            }
        });

        // Créez le bouton QUITTER
        quitButton = new JButton(" QUITTER ");
        quitButton.setFont(new Font("Arial", Font.BOLD, 35));
        quitButton.setForeground(Color.WHITE);
        quitButton.setBackground(Color.RED);
        quitButton.setFocusPainted(false);
        quitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        quitButton.setPreferredSize(new Dimension(180, 75));
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        quitButton.setHorizontalAlignment(SwingConstants.CENTER);
        quitButton.setVerticalAlignment(SwingConstants.CENTER);
        quitButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    // Méthodes obligatoires (peuvent rester vides)
    public void keyPressed(KeyEvent e) { System.err.println("test"); }
    public void keyRelease(KeyEvent e) { }
    public void MouseMouved(int x, int y) { }
    public void mousePressed(MouseEvent e){}
    public void update() { }

    // Démarrer le jeu lorsque l'on clique sur le bouton JOUER
    private void startGame() {
        GameStats.state = GameStats.PLAYING;
        // Boutons JOUER et QUITTER invisibles
        playButton.setVisible(false);
        quitButton.setVisible(false);
    }

    // Redimensionner l'image
    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g2d.dispose();
        return resizedImage;
    }

    // Méthode pour dessiner les éléments
    public void redessine(Graphics g) {
        g.drawImage(layer1, 0, 0, null);
        g.drawImage(layer2, 0, 0, null);
        g.drawImage(layer3, 0, 0, null);

        // Emplacement du texte TERRALIKE
        Font font = new Font("Arial", Font.BOLD, 120);
        FontMetrics metrics = g.getFontMetrics(font);
        int textWidth = metrics.stringWidth("TERRALIKE");
        int x = (Constantes.FRAME_WIDTH - textWidth) / 2;
        int y = Constantes.FRAME_HEIGHT / 4;
        g.setFont(font);
        
        // Contour pour le texte TERRALIKE
        g.setColor(Color.BLACK);
        g.drawString("TERRALIKE", x - 2, y - 2);
        g.drawString("TERRALIKE", x + 2, y - 2);
        g.drawString("TERRALIKE", x - 2, y + 2);
        g.drawString("TERRALIKE", x + 2, y + 2);
        
        // Texte en blanc au-dessus
        g.setColor(Color.WHITE);
        g.drawString("TERRALIKE", x, y);

        // Texte en bas
        Font bottomFont = new Font("Arial", Font.BOLD, 20);
        FontMetrics bottomMetrics = g.getFontMetrics(bottomFont);
        int bottomTextWidth = bottomMetrics.stringWidth("");
        int bottomX = (Constantes.FRAME_WIDTH - bottomTextWidth) / 2;
        int bottomY = Constantes.FRAME_HEIGHT - 150;
        g.setFont(bottomFont);
        g.setColor(Color.WHITE);
        g.drawString("NICOLAS - MARC - TEDDY - ENZO", bottomX, bottomY);

        //GameStats.state = GameStats.PLAYING;
    }


}
