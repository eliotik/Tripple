package com.triple.game.player;

import java.awt.Graphics;

import com.triple.game.Game;
import com.triple.game.configs.Config;
import com.triple.game.configs.Tiles;
import com.triple.game.elements.Element;
import com.triple.game.grid.Grid;

public class PlayerHand {

	private Element element;
	
	private int stepAnimation = 0;
	private int directionAnimation = 0;//0 -down; 1 - up
	private int updateStepTick = 0;
	private int stepTick = 60;
	
	public void render(Graphics g) {
		
		int tile_x = element.getType().getTile_x(),
				tile_y = element.getType().getTile_y(),
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
        			x_width + stepAnimation, 
        			y_width + stepAnimation, 
        			x_width + cell_size, 
        			y_width + cell_size,
        			tile_x_width + tile_x_border_pixelized + tile_border_width,
        			tile_y_width + tile_y_border_pixelized + tile_border_width_pixelized,
        			tile_x_width + tile_x_border_pixelized + tile_size + tile_border_width,
        			tile_y_width + tile_y_border_pixelized + tile_size + tile_border_width,
        			null);
		updateTickerAnimation();
		
	}

	private void updateTickerAnimation() {
		updateStepTick++;
		if (directionAnimation == 0)
		{
			if (stepAnimation <= -2) {
				directionAnimation = 1;
				stepAnimation++;
				updateStepTick = 0;
			}
			
			if (updateStepTick >= stepTick) {
				updateStepTick = 0;
				stepAnimation--;
			}
		} else {
			if (stepAnimation >= 2) {
				directionAnimation = 0;
				stepAnimation--;
				updateStepTick = 0;
			}
			if (updateStepTick >= stepTick) {
				updateStepTick = 0;
				stepAnimation++;
			}
		}
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public Element getElement() {
		return element;
	}

}
