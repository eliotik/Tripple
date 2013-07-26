package ua.triple.game;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import ua.triple.game.configs.Config;
import ua.triple.game.configs.Tiles;
import ua.triple.game.elements.Element;
import ua.triple.game.grid.Grid;

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
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			Font font = new Font("Serif", Font.PLAIN, 16);
	        g2.setFont(font);			
			g2.drawString("Player: " + player.getName(), (Grid.cellsAmount + 1) * Config.cellSize, 10); 
		}
    	
		if (player.getHand() != null) {
			Element hand = player.getHand();
			int tile_x = hand.getType().getTile_x(),
					tile_y = hand.getType().getTile_y(),
					cell_size = Config.cellSize,
					tile_size = Config.tileSize,
					tile_border_width = Config.tileBorderWidth,
					tile_border_width_pixelized = tile_border_width * Game.pixelSize,
					x_width = (Grid.cellsAmount + 1) * cell_size,
					y_width = 1 * cell_size,
					tile_x_width = tile_x * tile_size,
					tile_y_width = tile_y * tile_size,
					tile_x_border_pixelized = tile_x * tile_border_width_pixelized,
					tile_y_border_pixelized = tile_y * tile_border_width_pixelized;
				
			g.drawImage(Tiles.getTilesAssets(), 
	        			x_width, 
	        			y_width, 
	        			x_width + cell_size, 
	        			y_width + cell_size,
	        			tile_x_width + tile_x_border_pixelized + tile_border_width,
	        			tile_y_width + tile_y_border_pixelized + tile_border_width_pixelized,
	        			tile_x_width + tile_x_border_pixelized + tile_size + tile_border_width,
	        			tile_y_width + tile_y_border_pixelized + tile_size + tile_border_width,
	        			null);	        
    	
		}
        
	}
	
    public void tick() {

    }


}
