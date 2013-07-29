package com.triple.game.elements;

import java.awt.Graphics;

import com.triple.game.Game;
import com.triple.game.configs.Config;
import com.triple.game.configs.Tiles;

public class Element {

    private ElementType type;
    
	private int stepAnimation = 0;
	private int directionAnimation = 1;//0 -down; 1 - up
	private int updateStepTick = 0;
	private int stepTick = 80;
    
    public Element(ElementType type) {
        this.setType(type);
    }

	public ElementType getType() {
		return type;
	}

	public void setType(ElementType type) {
		this.type = type;
	}

	public void render(Graphics g, int x, int y, int width, int height, boolean animate) {
		int tile_x = type.getTile_x(),
				tile_y = type.getTile_y(),
				cell_size = width,
				tile_size = Config.tileSize,
				tile_border_width = Config.tileBorderWidth,
				tile_border_width_pixelized = tile_border_width * Game.pixelSize,
				x_width = x * cell_size,
				y_width = y * cell_size,
				tile_x_width = tile_x * tile_size,
				tile_y_width = tile_y * tile_size,
				tile_x_border_pixelized = tile_x * tile_border_width_pixelized,
				tile_y_border_pixelized = tile_y * tile_border_width_pixelized;
		
		g.drawImage(Tiles.getTilesAssets(), 
        			(animate) ? (x_width + stepAnimation) : x_width, 
					(animate) ? (y_width + stepAnimation) : y_width, 
					(animate) ? (x_width + cell_size + (stepAnimation*-1)) : (x_width + cell_size), 
					(animate) ? (y_width + cell_size + (stepAnimation*-1)) : (y_width + cell_size),
        			tile_x_width + tile_x_border_pixelized + tile_border_width,
        			tile_y_width + tile_y_border_pixelized + tile_border_width_pixelized,
        			tile_x_width + tile_x_border_pixelized + tile_size + tile_border_width,
        			tile_y_width + tile_y_border_pixelized + tile_size + tile_border_width,
        			null);
		
		if (animate)
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
	

}