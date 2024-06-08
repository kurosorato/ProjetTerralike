package main;

import java.awt.Graphics;
import java.io.IOException;

public class Game implements Runnable {

	private GameInterface gameInterface;
	private GameManager gameManager;
	private Thread gameThread;
	private final int FPS = 60;
	private final int UPS = 60;
	

	public Game() throws IOException {
		this.gameInterface = new GameInterface(this);
		this.gameManager = new GameManager(this);
        new GameWindow(this.gameInterface);
		gameInterface.requestFocus();
		startLoop();
	}

	private void startLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	//met a jour les methodes appeler
	public void update() {
		this.gameManager.update();
	}

	public GameInterface getGameInterface(){
		return gameInterface;
	}

	//permet de dessiner sur la fenetre
	public void dessine(Graphics g){
		this.gameManager.redessine(g);
	}
	
	public GameManager getGameManager() {
		return gameManager;
	}


	// La boucle principale du jeu
	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS;
		double timePerUpdate = 1000000000.0 / UPS;

		long previousTime = System.nanoTime();

		//int frames = 0;
		//int updates = 0;
		double deltaUpdate = 0;
		double deltaFrame = 0;
		//long lastCheck = System.currentTimeMillis();


		while (true) {
			long currentTime = System.nanoTime();

			//pour gerer le cas ou l'update depasse sur la prochaine, le delta ramene a 1 les frames
			//ex: si 1 update par sec, et que l'ordi prend 1.1 sec pour update la prochaine update se fera 0.9 sec plus tard
			deltaUpdate += (currentTime - previousTime) / timePerUpdate;
			deltaFrame += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			//appel la methode pour faire des mise a jour (position etc...)
			if (deltaUpdate >= 1) {
				update();
				//updates++;
				deltaUpdate--;
			}

			//redessine ce qu'il a a l'ecran
			if (deltaFrame >= 1) {
				gameInterface.repaint();
				//frames++;
				deltaFrame--;
			}

			// if (System.currentTimeMillis() - lastCheck >= 1000) {
			// 	lastCheck = System.currentTimeMillis();
			// 	System.out.println("FPS: " + frames + " | Updates: " + updates);
			// 	frames = 0;
			// 	updates = 0;
			// }
		}
	}


}