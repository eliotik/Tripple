package com.triple.game.player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.triple.game.Game;
import com.triple.game.configs.Config;
import com.triple.game.grid.Grid;

public class PlayerPanel {

	public static Dimension size = new Dimension(140, 320);
	public static Dimension pixel = new Dimension(size.width/Game.pixelSize, size.height/Game.pixelSize);
	private Player player;
	
    public PlayerPanel(Player player) {
    	this.player = player;
    }
    
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}

	public void render(Graphics g) {

		if(player.getName() != "" && g instanceof Graphics2D)
		{
			Graphics2D g2 = (Graphics2D)g;
			//g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			//g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	        g2.setFont(new Font("Arial", Font.PLAIN, 24));			
	        g2.setColor(Color.BLACK);
	        g2.drawString(player.getName(), Grid.cellsAmount * Config.cellSize + 10, 60);
	        
		}
    	
		if (player.getHand() != null) {
			player.getHand().render(g);
		}
        
		if (player.getScore() != null) {
			player.getScore().render(g);
		}
	}
	
    public void tick() {

    }


}
