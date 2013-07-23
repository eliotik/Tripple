package ua.triple.game;

import java.awt.*;

import ua.triple.game.configs.Tiles;
import ua.triple.game.elements.ElementTypesCollection;
import ua.triple.game.grid.Grid;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private static int pixelSize = 2;

    public static Dimension size = new Dimension(640, 640);
    public static Dimension pixel = new Dimension(size.width/pixelSize, size.height/pixelSize);

    public static String name = "Triple Town";
    public static boolean isRunning = false;

    public static Grid grid;

    private Image screen;

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


        new Thread(this).start();
    }

    public void stop() {

    }

    public void run() {
        screen = createVolatileImage(pixel.width, pixel.height);

        while (isRunning) {
            tick();
            render();
            try { Thread.sleep(5); } catch (Exception e) {}
        }
            
    }

    public void render() {
        Graphics g = screen.getGraphics();

        g.setColor(new Color(100,100,100));
        g.fillRect(0, 0, pixel.width, pixel.height);

        grid.render(g);

        g = getGraphics();
        g.drawImage(screen, 0, 0, size.width, size.height, 0, 0, pixel.width, pixel.height, null);

        g.dispose();
    }

    public void tick() {

    }
}
