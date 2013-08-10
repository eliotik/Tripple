package com.triple.game.elements;

import com.triple.game.Game;
import com.triple.game.configs.Config;
import com.triple.game.grid.Cell;
import com.triple.sprites.Tiles;
import java.awt.*;

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

	public void render(Graphics g, int x, int y, int width, int height, boolean animate, boolean showBorder, boolean drawBackground) {
		
		int	cell_size = width,
			tile_size = Config.tileSize,
			tile_border_width = Config.tileBorderWidth,
			x_width = x * cell_size,
			y_width = y * cell_size;
		
		if (drawBackground)
			renderBackground(g, x, y, width, height);
		
		if (showBorder) {
			renderBorder(g, x, y, width, height, animate);
		}
		
		int tile_x = type.getTile_x(),
			tile_y = type.getTile_y();
		
		g.drawImage(Tiles.getTilesAsset(), 
        			(animate) ? (x_width + stepAnimation) : x_width, 
					(animate) ? (y_width + stepAnimation) : y_width, 
					(animate) ? (x_width + cell_size + (stepAnimation*-1)) : (x_width + cell_size), 
					(animate) ? (y_width + cell_size + (stepAnimation*-1)) : (y_width + cell_size),
					tile_x * tile_size + tile_x * tile_border_width + tile_border_width,
					tile_y * tile_size + tile_y * tile_border_width + tile_border_width,
					tile_x * tile_size + tile_size + tile_x * tile_border_width + tile_border_width,
					tile_y * tile_size + tile_size + tile_y * tile_border_width + tile_border_width,
        			null);
		
		if (animate)
			updateTickerAnimation(stepTick);
		
	}

	public void renderCollapsing(Graphics g, int x, int y, int width, int height, int stepCollapsionX, int stepCollapsionY) {
		
		int	cell_size = width,
			tile_size = Config.tileSize,
			tile_border_width = Config.tileBorderWidth,
			x_width = x * cell_size + stepCollapsionX,
			y_width = y * cell_size + stepCollapsionY;
		
		int tile_x = type.getTile_x(),
			tile_y = type.getTile_y();
//		if(x==1 && y==0) System.out.println("x="+x+", y="+y+", x_width="+x_width+", y_width="+y_width);
		g.drawImage(Tiles.getTilesAsset(), 
        			x_width, 
					y_width, 
					x_width + cell_size, 
					y_width + cell_size,
					tile_x * tile_size + tile_x * tile_border_width + tile_border_width,
					tile_y * tile_size + tile_y * tile_border_width + tile_border_width,
					tile_x * tile_size + tile_size + tile_x * tile_border_width + tile_border_width,
					tile_y * tile_size + tile_size + tile_y * tile_border_width + tile_border_width,
        			null);
	}	
	
	private void renderBackground(Graphics g, int x, int y, int width, int height) {
		
		int tile_x = Config.bgCenter[0],
			tile_y = Config.bgCenter[1];
		
//		double angle = 0;
//		
		Cell cell_a = Game.grid.getCell(x-1, y-1),
			cell_b = Game.grid.getCell(x, y-1),
			cell_c = Game.grid.getCell(x+1, y-1),
			cell_d = Game.grid.getCell(x+1, y),
			cell_e = Game.grid.getCell(x+1, y+1),
			cell_f = Game.grid.getCell(x, y+1),
			cell_g = Game.grid.getCell(x-1, y+1),
			cell_h = Game.grid.getCell(x-1, y);
		
		
		
		
		if (cell_a != null && cell_b != null && cell_c != null && cell_d != null && 
			cell_e != null && cell_f != null && cell_g != null && cell_h != null)
		{
			if (cell_a.getElement() != null && cell_b.getElement() != null && cell_c.getElement() != null && cell_d.getElement() != null && 
				cell_e.getElement() != null && cell_f.getElement() != null && cell_g.getElement() != null && cell_h.getElement() != null) {
				tile_x = Config.bgFullRound[0];
				tile_y = Config.bgFullRound[1];
			}
		} else if (cell_a == null && cell_b == null && cell_c == null && cell_d != null && 
			cell_e != null && cell_f != null && cell_g == null && cell_h == null) {
			if (cell_d.getElement() != null || cell_e.getElement() != null || cell_f.getElement() != null) {
				
				if (cell_d.getElement() != null && cell_e.getElement() == null && cell_f.getElement() == null) {
					tile_x = Config.bgSide_f[0];
					tile_y = Config.bgSide_f[1];					
				} else if (cell_d.getElement() != null && cell_e.getElement() != null && cell_f.getElement() == null) {
					tile_x = Config.bgSide_f[0];
					tile_y = Config.bgSide_f[1];					
				} else if (cell_d.getElement() != null && cell_e.getElement() != null && cell_f.getElement() != null) {
					tile_x = Config.bgFullRound[0];
					tile_y = Config.bgFullRound[1];
				} else if (cell_d.getElement() == null && cell_e.getElement() != null && cell_f.getElement() != null) {
					tile_x = Config.bgSide_d[0];
					tile_y = Config.bgSide_d[1];					
				} else if (cell_d.getElement() == null && cell_e.getElement() == null && cell_f.getElement() != null) {
					tile_x = Config.bgSide_d[0];
					tile_y = Config.bgSide_d[1];					
				} else if (cell_d.getElement() != null && cell_e.getElement() == null && cell_f.getElement() != null) {
					tile_x = Config.bgFullRound[0];
					tile_y = Config.bgFullRound[1];					
				} else if (cell_d.getElement() == null && cell_e.getElement() != null && cell_f.getElement() == null) {
					tile_x = Config.bgCorner_e[0];
					tile_y = Config.bgCorner_e[1];					
				}
				
			} else {
				tile_x = Config.bgCorner_e[0];
				tile_y = Config.bgCorner_e[1];
			}
		} else if (cell_a == null && cell_b != null && cell_c != null && cell_d != null && 
			cell_e != null && cell_f != null && cell_g == null && cell_h == null) {
			if (cell_b.getElement() != null || cell_c.getElement() == null || cell_d.getElement() == null ||
				cell_e.getElement() == null || cell_f.getElement() == null) {
				
				if (cell_b.getElement() != null && cell_c.getElement() == null && cell_d.getElement() == null &&
						cell_e.getElement() == null && cell_f.getElement() == null) {
					
				}
				
			} else {
				tile_x = Config.bgSide_d_round[0];
				tile_y = Config.bgSide_d_round[1];
			}
		}

			
		
		int cell_size = width,
			tile_size = Config.tileSize,
			tile_border_width = Config.tileBorderWidth,
			tile_border_width_pixelized = tile_border_width * Game.pixelSize,
			tile_x_width = tile_x * Config.tileSize,
			tile_y_width = tile_y * Config.tileSize,
			tile_x_border_pixelized = tile_x * tile_border_width_pixelized,
			tile_y_border_pixelized = tile_y * tile_border_width_pixelized,
			x_width = x * cell_size,
			y_width = y * cell_size,
			tile_x_from = tile_x_width + tile_x_border_pixelized + tile_border_width,
			tile_y_from = tile_y_width + tile_y_border_pixelized + tile_border_width_pixelized,
			tile_x_to = tile_x_width + tile_x_border_pixelized + tile_size + tile_border_width,
			tile_y_to = tile_y_width + tile_y_border_pixelized + tile_size + tile_border_width;
		

		g.drawImage(Tiles.getBackgroundsAssetsFile(), 
    			x_width, 
				y_width, 
				x_width + cell_size, 
				y_width + cell_size,
				tile_x_from,
    			tile_y_from,
    			tile_x_to,
    			tile_y_to,
    			null);
	}

	
	public void renderContainer(Graphics g, int x, int y, int width, int height, int offsetX, int offsetY, boolean animate, int tickModifier) {
		int tile_x = type.getTile_x(),
				tile_y = type.getTile_y(),
				cell_size = width,
				tile_size = Config.tileSize,
				tile_border_width = Config.tileBorderWidth,
				tile_border_width_pixelized = tile_border_width * Game.pixelSize,
				x_width = x * cell_size + offsetX,
				y_width = y * cell_size + offsetY,
				tile_x_width = tile_x * tile_size,
				tile_y_width = tile_y * tile_size,
				tile_x_border_pixelized = tile_x * tile_border_width_pixelized,
				tile_y_border_pixelized = tile_y * tile_border_width_pixelized;
		
		g.drawImage(Tiles.getTilesAsset(), 
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
			updateTickerAnimation((tickModifier > 0) ? tickModifier : stepTick);
		
	}
	
	public static void renderBorder(Graphics g, int x, int y, int width, int height, boolean animate) {
		int	cell_size = width,
			tile_size = Config.tileSize,
			tile_border_width = Config.tileBorderWidth,
			tile_border_width_pixelized = tile_border_width * Game.pixelSize,
			x_width = x * cell_size,
			y_width = y * cell_size,
			border_tile_x = Config.focusBorder[0],
			border_tile_y = Config.focusBorder[1],
			border_tile_x_width = border_tile_x * tile_size,
			border_tile_y_width = border_tile_y * tile_size,
			border_tile_x_border_pixelized = border_tile_x * tile_border_width_pixelized,
			border_tile_y_border_pixelized = border_tile_y * tile_border_width_pixelized;
				
		g.drawImage(Tiles.getTilesAsset(), 
    			x_width, 
				y_width, 
				x_width + cell_size, 
				y_width + cell_size,
				border_tile_x_width + border_tile_x_border_pixelized + tile_border_width,
				border_tile_y_width + border_tile_y_border_pixelized + tile_border_width,
				border_tile_x_width + border_tile_x_border_pixelized + tile_size + tile_border_width,
				border_tile_y_width + border_tile_y_border_pixelized + tile_size + tile_border_width,
    			null);
	}
	
	private void updateTickerAnimation(int stepTickAmount) {
		updateStepTick++;
		if (directionAnimation == 0)
		{
			if (stepAnimation <= -2) {
				directionAnimation = 1;
				stepAnimation++;
				updateStepTick = 0;
			}
			
			if (updateStepTick >= stepTickAmount) {
				updateStepTick = 0;
				stepAnimation--;
			}
		} else {
			if (stepAnimation >= 2) {
				directionAnimation = 0;
				stepAnimation--;
				updateStepTick = 0;
			}
			if (updateStepTick >= stepTickAmount) {
				updateStepTick = 0;
				stepAnimation++;
			}
		}
	}	
	

}
