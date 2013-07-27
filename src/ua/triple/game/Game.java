package ua.triple.game;

import java.awt.*;

import ua.triple.game.configs.Config;
import ua.triple.game.configs.Tiles;
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

    private int screenUpdates;
    private int screenFrames;
    private long screenTimer;
    private int screenShowUpdates;
    private int screenShowFrames;    
    
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
        screenTimer = System.currentTimeMillis();
        
        while (isRunning) {
        	long now = System.nanoTime();
        	delta += (now - lastTime) / n;
        	lastTime = now;
        	if (delta >= 1) {
        		tick();
        		++screenUpdates;
        		--delta;
        	}
            
            render();
            
            try { Thread.sleep(5); } catch (Exception e) {}
        }
            
    }

    public void render() {
        Graphics g = screen.getGraphics();

        g.setColor(new Color(100,100,100));
        g.fillRect(0, 0, pixel.width, pixel.height);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        
        grid.render(g);
        playerPanel.render(g);

        ++screenFrames;
        Graphics2D g2 = (Graphics2D)g;
        g2.setFont(new Font("Arial", Font.PLAIN, 9));			
        g2.setColor(Color.BLACK);
        g2.drawString("fps="+screenShowUpdates+":"+screenShowFrames, (Grid.cellsAmount + 1) * Config.cellSize, (Grid.cellsAmount) * Config.cellSize - 5);

        if (System.currentTimeMillis() - screenTimer > 1000) {
        	screenTimer += 1000;
        	screenShowUpdates = screenUpdates;
        	screenShowFrames = screenFrames;
        	screenUpdates = 0;
        	screenFrames = 0;
        }        
        
        g = getGraphics();
        g.drawImage(screen, 0, 0, size.width, size.height, 0, 0, pixel.width, pixel.height, null);
        g.dispose();
    }

    public void tick() {

    }
}
