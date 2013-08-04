package com.triple.game;

import com.triple.game.configs.Config;
import com.triple.game.configs.Fps;
import com.triple.game.elements.ElementTypesCollection;
import com.triple.game.grid.Grid;
import com.triple.game.player.Player;
import com.triple.game.player.PlayerPanel;
import com.triple.handlers.InputEvents;
import com.triple.menu.Menu;
import com.triple.sprites.Tiles;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int pixelSize = Config.pixelSize;

    public static final Dimension size = Config.screenSize;
    public static final Dimension pixel = new Dimension(size.width/pixelSize, size.height/pixelSize);

    public static final String name = Config.gameName;
    public static boolean isRunning = false;
    public static boolean isJoinning = false;
    private static int gameState;

    public static Grid grid;
    private static PlayerPanel playerPanel;
    public static ElementTypesCollection elementTypesCollection;
    
    private Image screen;
	private Thread thread;
	private Menu menu;
	
    public static void main(String[] args) {
        //new Game().run();
    	
    	JFrame frame = new JFrame(name);
        Game game = new Game();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(game, BorderLayout.CENTER);
        frame.pack();
        
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        game.start();
    }    
  
    public Game() {
    	setMinimumSize(size);
    	setMaximumSize(size);
    	setPreferredSize(size);
    }

    public void init() {
    	menu = new Menu(this);
        try {
			Tiles.loadTiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
        ElementTypesCollection.loadElements();
        elementTypesCollection = new ElementTypesCollection();
        grid = new Grid();
        grid.refreshJoinableCells();
        
		addMouseListener(new InputEvents(this));
		addMouseMotionListener(new InputEvents(this));    	
    }
    
    public synchronized void start() {
    	if (isRunning) return;
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
    	if (!isRunning) return;
    	isRunning = false;
    	
    	try {
    		thread.join();
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    	System.exit(1);
    }

    public void run() {
    	init();
        boolean doRender = false;
        
        Fps.init();
        
        while (isRunning) {
        	Fps.doTick();
        	
        	while(Fps.getDelta() >= 1) {
        		tick();
        		Fps.doTickUpdate();
        		doRender = true;
        	}
        	
        	try { Thread.sleep(2); } catch (Exception e) { e.printStackTrace(); }
        	
        	if (doRender) {
	        	Fps.increaseFrames();
	            render();
        	}
        	
            Fps.doUpdate();
        }
            
    }

    private void drawGameScreenBackground(Graphics g) {
		g.drawImage(Tiles.getScreenBackground(), 
					0, 
					0, 
					size.width, 
					size.height,
					null);    	
    }
    
    public void render() {
    	BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
    		createBufferStrategy(2);
    		return;
    	}
    	Graphics g = bs.getDrawGraphics();
    	
    	switch (getGameState()) {
			case 0:
				getMenu().render(g);	
			break;
			case 1:
				if (getPlayerPanel() == null)
					setPlayerPanel(new PlayerPanel(new Player("Player", ElementTypesCollection.getRandom())));
				if (screen == null)
					screen = createVolatileImage(pixel.width, pixel.height);

				Graphics sg = screen.getGraphics();
				sg.setColor(new Color(100,100,100));
				sg.fillRect(0, 0, pixel.width, pixel.height);
				sg.setFont(new Font("Arial", Font.PLAIN, 24));
		        
		        drawGameScreenBackground(sg);
		        grid.render(sg);
		        getPlayerPanel().render(sg);
		        Fps.render(sg);
		        
		        g.drawImage(screen, 0, 0, size.width, size.height, 0, 0, pixel.width, pixel.height, null);
			break;
		}
    	
    	
    	g.dispose();
    	bs.show();    	
    	
    }

    public void tick() {

    }
        
	public Menu getMenu() {
		return menu;
	}

	public static int getGameState() {
		return Game.gameState;
	}

	public static void setGameState(int gameState) {
		Game.gameState = gameState;
	}

	public static PlayerPanel getPlayerPanel() {
		return playerPanel;
	}

	public static void setPlayerPanel(PlayerPanel playerPanel) {
		Game.playerPanel = playerPanel;
	}

}
