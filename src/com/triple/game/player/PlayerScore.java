package com.triple.game.player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.triple.game.configs.Config;
import com.triple.game.grid.Grid;

public class PlayerScore {
	private int score;
	private double multiplier;
	
	public PlayerScore() {
		setScore(0);
		setMultiplier(0);
	}
	
	public void reset() {
		score = 0;
		multiplier = 0;
	}
	
	public void addScore(int score) {
		if (multiplier == 0) {
			this.score += score;
		} else {
			this.score += (int) Math.ceil(score * multiplier);
		}
	}

	public void addMultiplier(double multiplier) {
		this.multiplier += multiplier;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public double getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(double multiplier) {
		this.multiplier = multiplier;
	}
	
	public void render(Graphics g) {
		if(g instanceof Graphics2D)
		{
			Graphics2D g2 = (Graphics2D)g;
	        g2.setFont(new Font("Arial", Font.PLAIN, 18));			
	        g2.setColor(Color.BLACK);
	        g2.drawString("Score: "+score, Grid.cellsAmount * Config.cellSize + 10, 100);
	        g2.setFont(new Font("Arial", Font.PLAIN, 14));
	        g2.drawString("1 x "+multiplier, Grid.cellsAmount * Config.cellSize + 10, 125);
	        
		}		
	}
	
}
