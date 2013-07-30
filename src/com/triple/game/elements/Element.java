package com.triple.game.elements;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import com.triple.game.Game;
import com.triple.game.configs.Config;
import com.triple.game.configs.Tiles;
import com.triple.game.configs.Utils;
import com.triple.game.grid.Cell;

public class Element {

    private ElementType type;
    
	private int stepAnimation = 0;
	private int directionAnimation = 1;//0 -down; 1 - up
	private int updateStepTick = 0;
	private int stepTick = 20;
    
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
		
		g.drawImage(Tiles.getTilesAssets(), 
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

	private void renderBackground(Graphics g, int x, int y, int width, int height) {
		
		int tile_x = Config.bgCenter[0],
			tile_y = Config.bgCenter[1];
		
		double angle = 0;
		
		Cell cell_a = Game.grid.getCell(x-1, y-1),
			cell_b = Game.grid.getCell(x, y-1),
			cell_c = Game.grid.getCell(x+1, y-1),
			cell_d = Game.grid.getCell(x+1, y),
			cell_e = Game.grid.getCell(x+1, y+1),
			cell_f = Game.grid.getCell(x, y+1),
			cell_g = Game.grid.getCell(x-1, y+1),
			cell_h = Game.grid.getCell(x-1, y);
		
		
		if (cell_b != null || cell_d != null || cell_f != null || cell_g != null)
		{
			if (cell_b.getElement() == null && cell_d.getElement() == null &&
				cell_f.getElement() == null && cell_h.getElement() == null) {
				/*
				---
				| |
				---
				*/
				angle = 0;
				tile_x = Config.bgCenter[0];
				tile_y = Config.bgCenter[1];
			} else if (cell_b.getElement() == null && cell_d.getElement() != null &&
					cell_f.getElement() == null && cell_h.getElement() != null) {
				/*
				-----
				-----
				*/
				angle = 0;
				tile_x = Config.bgLine[0];
				tile_y = Config.bgLine[1];
			} else if (cell_b.getElement() != null && cell_d.getElement() == null &&
					cell_f.getElement() != null && cell_h.getElement() == null) {
				/*
				| |
				*/
				angle = 90;
				tile_x = Config.bgLine[0];
				tile_y = Config.bgLine[1];
			} else if (cell_b.getElement() != null && cell_d.getElement() == null &&
					cell_f.getElement() == null && cell_h.getElement() == null) {
				/*
				| |
				---
				*/
				angle = 0;
				tile_x = Config.bgOneSide[0];
				tile_y = Config.bgOneSide[1];
			} else if (cell_b.getElement() == null && cell_d.getElement() != null &&
					cell_f.getElement() == null && cell_h.getElement() == null) {
				/*
				| |
				---
				*/
				angle = 90;
				tile_x = Config.bgOneSide[0];
				tile_y = Config.bgOneSide[1];
			} else if (cell_b.getElement() == null && cell_d.getElement() == null &&
					cell_f.getElement() != null && cell_h.getElement() == null) {
				/*
				| |
				---
				*/
				angle = 180;
				tile_x = Config.bgOneSide[0];
				tile_y = Config.bgOneSide[1];
			} else if (cell_b.getElement() == null && cell_d.getElement() == null &&
					cell_f.getElement() == null && cell_h.getElement() != null) {
				/*
				| |
				---
				*/
				angle = 270;
				tile_x = Config.bgOneSide[0];
				tile_y = Config.bgOneSide[1];
			} else if (cell_b.getElement() != null && cell_d.getElement() != null &&
					cell_f.getElement() == null && cell_h.getElement() == null) {
				/*
				| |__
				|  
				-----
				*/
				angle = 0;
				tile_x = Config.bgTwoSides[0];
				tile_y = Config.bgTwoSides[1];
			} else if (cell_b.getElement() == null && cell_d.getElement() != null &&
					cell_f.getElement() != null && cell_h.getElement() == null) {
				/*
				| |__
				|  
				-----
				*/
				angle = 90;
				tile_x = Config.bgTwoSides[0];
				tile_y = Config.bgTwoSides[1];
			} else if (cell_b.getElement() == null && cell_d.getElement() == null &&
					cell_f.getElement() != null && cell_h.getElement() != null) {
				/*
				| |__
				|  
				-----
				*/
				angle = 180;
				tile_x = Config.bgTwoSides[0];
				tile_y = Config.bgTwoSides[1];
			} else if (cell_b.getElement() != null && cell_d.getElement() == null &&
					cell_f.getElement() == null && cell_h.getElement() != null) {
				/*
				| |__
				|  
				-----
				*/
				angle = 270;
				tile_x = Config.bgTwoSides[0];
				tile_y = Config.bgTwoSides[1];
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
		
		if(angle != 0)
		{
			BufferedImage image = new BufferedImage(Config.tileSize, Config.tileSize, Transparency.TRANSLUCENT);
	        Graphics2D g2d = image.createGraphics();
	        g2d.drawImage(Tiles.getTilesAssets(), 
		    			0, 
						0, 
						cell_size, 
						cell_size,
		    			tile_x_from,
	        			tile_y_from,
	        			tile_x_to,
	        			tile_y_to,
		    			null);
	        //g2d.rotate(Math.toRadians(angle));
	        g2d.dispose();
	        image = Utils.rotate(image, angle);	
//	        java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis()); 
//	        long tsi = ts.getTime();
//	        try {
//				ImageIO.write(image, "png", new File("bin/"+tsi+".png"));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
	        
			g.drawImage(image,//Tiles.getTilesAssets(), 
	        			x_width, 
						y_width, 
						x_width + cell_size, 
						y_width + cell_size,
						0,
	        			0,
	        			Config.tileSize,
	        			Config.tileSize,
	        			null);
			image = null;
		} else {
			g.drawImage(Tiles.getTilesAssets(), 
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
				
		g.drawImage(Tiles.getTilesAssets(), 
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