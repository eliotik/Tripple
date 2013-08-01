package com.triple.game;

import com.triple.game.configs.Fps;
import com.triple.game.configs.Tiles;
import com.triple.game.elements.ElementTypesCollection;
import com.triple.game.grid.Event;
import com.triple.game.grid.Grid;
import com.triple.game.player.Player;
import com.triple.game.player.PlayerPanel;

import javax.swing.*;
import java.awt.*;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int pixelSize = 1;

    public static final Dimension size = new Dimension(800, 640);
    public static final Dimension pixel = new Dimension(size.width/pixelSize, size.height/pixelSize);

    public static final String name = "Triple Town";
    public static boolean isRunning = false;
    public static boolean isJoinning = false;

    public static Grid grid;
    public static PlayerPanel playerPanel;
    public static ElementTypesCollection elementTypesCollection;
    
    private Image screen;
    private JFrame frame;

    public static void main(String[] args) {
        new Game().start();
    }    
  
    public Game() {
    	setMinimumSize(size);
    	setMaximumSize(size);
    	setPreferredSize(size);
    	
        frame = new JFrame(name);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        frame.pack();
        
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public synchronized void start() {
        isRunning = true;
        Tiles.loadTiles();
        ElementTypesCollection.loadElements();
        grid = new Grid();
        grid.refreshJoinableCells();
        elementTypesCollection = new ElementTypesCollection();
        playerPanel = new PlayerPanel(new Player("Player", elementTypesCollection.getRandomForHand()));

        new Thread(this).start();
        addMouseListener(new Event());
        addMouseMotionListener(new Event());
    }

    public void stop() {
    	isRunning = false;
    }

    public void run() {
        screen = createVolatileImage(pixel.width, pixel.height);
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

    private void drawScreenBackground(Graphics g) {
		g.drawImage(Tiles.getScreenBackground(), 
					0, 
					0, 
					size.width, 
					size.height,
					null);    	
    }
    
    public void render() {
        Graphics g = screen.getGraphics();

        g.setColor(new Color(100,100,100));
        g.fillRect(0, 0, pixel.width, pixel.height);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        
        drawScreenBackground(g);
        grid.render(g);
        playerPanel.render(g);
        Fps.render(g);
        
        g = getGraphics();
        g.drawImage(screen, 0, 0, size.width, size.height, 0, 0, pixel.width, pixel.height, null);
        g.dispose();
    }

    public void tick() {

    }
}
