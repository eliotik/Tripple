package ua.triple.game;

import java.awt.*;

public class Game extends Canvas implements Runnable {
    public static Dimension size = new Dimension(800, 600);
    public static String name = "Triple Town";
    public static boolean isRunning = false;

    public static Grid grid;

    public Game(int height, int width) {
        Dimension d = new Dimension(height, width);
        setPreferredSize(d);
    }
    public Game() {
        setPreferredSize(size);
    }

    public void start() {
        isRunning = true;
        grid = new Grid();


        new Thread(this).start();
    }

    public void stop() {

    }

    @Override
    public void run() {
        while (isRunning) {
            tick();
            render();
            try { Thread.sleep(5); } catch (Exception e) {}
        }
            
    }

    public void render() {

    }

    public void tick() {

    }
}
