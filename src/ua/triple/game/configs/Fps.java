package ua.triple.game.configs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import ua.triple.game.grid.Grid;

public class Fps {
    private static int frames;
    private static long screenTimer;
    private static int screenShowUpdates;
    private static int screenShowFrames;
    private static double delta;
    private static long lastTime;
    private static final double numTicks = 60.0;;
    private static double n;
    private static int ticks;
    
    public static void init() {
        lastTime = System.nanoTime();
        n = 1000000000 / numTicks;    	
        screenTimer = System.currentTimeMillis();
        ticks = 0;
        frames = 0;
	}
    
    public static void doTick() {
    	long now = System.nanoTime();
    	setDelta(getDelta() + (now - lastTime) / n);
    	lastTime = now;
    	
//    	if (getDelta() < 1) return false;
//    	
//		++ticks;
//		setDelta(getDelta() - 1);
//		return true;
    }

	public static void render(Graphics g) {
		
        ++frames;
        Graphics2D g2 = (Graphics2D)g;
        g2.setFont(new Font("Arial", Font.PLAIN, 9));			
        g2.setColor(Color.BLACK);
        g2.drawString("fps="+screenShowUpdates+":"+screenShowFrames, (Grid.cellsAmount + 1) * Config.cellSize, (Grid.cellsAmount) * Config.cellSize - 5);
		
	}

	public static double getDelta() {
		return delta;
	}

	public static void setDelta(double delta) {
		Fps.delta = delta;
	}

	public static void doTickUpdate() {
		ticks++;
		delta -= 1;
		
	}

	public static void increaseFrames() {
		frames++;
	}

	public static void doUpdate() {
        if (System.currentTimeMillis() - screenTimer > 1000) {
        	screenTimer += 1000;
        	screenShowUpdates = ticks;
        	screenShowFrames = frames;
        	ticks = 0;
        	frames = 0;
        }
	}
}
