package ua.triple.game.configs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import ua.triple.game.grid.Grid;

public class Fps {
    private static int screenUpdates;
    private static int screenFrames;
    private static long screenTimer;
    private static int screenShowUpdates;
    private static int screenShowFrames;
    private static double delta;
    private static long lastTime;
    private static final double numTicks = 60.0;;
    private static double n;
    
    public static void init() {
        lastTime = System.nanoTime();
        n = 1000000000 / numTicks;    	
        screenTimer = System.currentTimeMillis();		
	}
    
    public static boolean doTick() {
    	long now = System.nanoTime();
    	delta += (now - lastTime) / n;
    	lastTime = now;
    	
    	if (delta < 1) return false;
    	
		++screenUpdates;
		--delta;
		return true;
    }

	public static void render(Graphics g) {
		
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
		
	}
}
