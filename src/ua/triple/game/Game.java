package ua.triple.game;

import java.awt.*;

import ua.triple.game.configs.Tiles;
import ua.triple.game.configs.Utils;
import ua.triple.game.elements.ElementTypesCollection;
import ua.triple.game.grid.Grid;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static int pixelSize = 2;

    public static Dimension size = new Dimension(460, 320);
    public static Dimension pixel = new Dimension(size.width/pixelSize, size.height/pixelSize);

    public static String name = "Triple Town";
    public static boolean isRunning = false;

    public static Grid grid;

    private Image screen;
    private PlayerPanel playerPanel;

    public Game(int height, int width) {
        Dimension d = new Dimension(height, width);
        setPreferredSize(d);
    }
    
    public Game() {
        setPreferredSize(size);
    }

    public void start() {
        isRunning = true;
        Tiles.loadTiles();
        ElementTypesCollection.loadElements();
        grid = new Grid();
        playerPanel = new PlayerPanel(new Player("Player", ElementTypesCollection.getRandom()));

        new Thread(this).start();
    }

    public void stop() {

    }

    public void run() {
        screen = createVolatileImage(pixel.width, pixel.height);
        
        long lastTime = System.nanoTime();
        final double numTicks = 60.0;
        double n = 1000000000 / numTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        
        while (isRunning) {
        	long now = System.nanoTime();
        	delta += (now - lastTime) / n;
        	lastTime = now;
        	if (delta >= 1) {
        		tick();
        		++updates;
        		--delta;
        	}
            
            render();
            
            ++frames;
            if (System.currentTimeMillis() - timer > 1000) {
            	timer += 1000;
            	Utils.print(updates + " ticks, fps: " + frames);
            }
            updates = 0;
            frames = 0;
            
            try { Thread.sleep(5); } catch (Exception e) {}
        }
            
    }

    public void render() {
        Graphics g = screen.getGraphics();

        g.setColor(new Color(100,100,100));
        g.fillRect(0, 0, pixel.width, pixel.height);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        
        grid.render(g);
        
        
        g = getGraphics();
        
        playerPanel.render(g);
        
        g.drawImage(screen, 0, 0, size.width, size.height, 0, 0, pixel.width, pixel.height, null);

        g.dispose();
    }

    public void tick() {

    }
}
