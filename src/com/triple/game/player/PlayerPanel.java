package com.triple.game.player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import com.triple.game.Game;
import com.triple.game.configs.Config;
import com.triple.game.grid.Grid;

public class PlayerPanel {

	public static Dimension size = new Dimension(140, 320);
	public static Dimension pixel = new Dimension(size.width/Game.pixelSize, size.height/Game.pixelSize);
	private Players players = new Players();
	
    public PlayerPanel(Player player) {
    	players.add(player);
    }
    
	public Player getPlayer(int id) {
		return players.get(id);
	}
	
	public Player getPlayer(String name) {
		return players.get(name);
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}

	public void render(Graphics g) {

		if (players.count() > 0) {
			for ( int i = 0, l = players.count(); i < l; ++i ) {
				Player player = players.get(i);
				if (player != null) {
					if(player.getName() != "" && g instanceof Graphics2D)
					{
						Graphics2D g2 = (Graphics2D)g;
						g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						//g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				        g2.setFont(new Font("Arial", Font.PLAIN, 24));			
				        g2.setColor(Color.BLACK);
				        g2.drawString(player.getName(), Grid.cellsAmount * Config.cellSize + 10, 60 * (i + 1));
				        
					}
			    	
					if (player.getHand() != null) {
						player.getHand().render(g, i);
					}
			        
					if (player.getScore() != null) {
						player.getScore().render(g, i + 1);
					}
				} else {
					break;
				}
			}
		}
		
	}
	
    public void tick() {

    }


}
