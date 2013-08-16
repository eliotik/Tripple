package com.triple.game.configs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.triple.game.grid.Grid;

public class FPS2 {
	private static long lastLoopTime;
	private static final int TARGET_FPS = 60;
	private static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS; 
	private static int fps;
	private static int showFps;
	private static long lastFpsTime;
	private static double delta;
	
	public static void init() {
		lastLoopTime = System.nanoTime();
		fps = 0;
		showFps = 0;
		lastFpsTime = 0;
		delta = 0;
	}
	
	public static void update() {
		long now = System.nanoTime();
		long updateLength = now - lastLoopTime;
		lastLoopTime = now;
		delta = updateLength / ((double)OPTIMAL_TIME);
		
		lastFpsTime += updateLength;
		fps++;
		
		if (lastFpsTime >= 1000000000)
		{
		   lastFpsTime = 0;
		   showFps = fps;
		   fps = 0;
		}		
	}
	
	public static long getTimeout() {
		return (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
	}
	
	public static double getDelta() {
		return delta;
	}
	
	public static void render(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setFont(new Font("Arial", Font.PLAIN, 9));			
        g2.setColor(Color.BLACK);
        g2.drawString("fps="+showFps, (Grid.cellsAmount + 1) * Config.cellSize, (Grid.cellsAmount) * Config.cellSize - 5);
	}	
}
