package com.triple.game.elements.subspecies;

import java.awt.Graphics;

import com.triple.game.configs.Config;
import com.triple.game.elements.Element;
import com.triple.game.elements.ElementType;
import com.triple.sprites.Tiles;

public class ElementBear extends Element {

	private boolean doMove = false;
	private boolean isMoving = false;
	private int currentX;
	private int currentY;
	private int moveToX;
	private int moveToY;
	private double pixelsMoveToX;
	private double pixelsMoveToY;
	private double currentMoveX;  
	private double currentMoveY;  
	
	public ElementBear() {
		super();
	}
	
	public ElementBear(ElementType type) {
		super(type);
	}

	public void setMoveDirection(int x, int y, int moveToX, int moveToY) {
		if (isMoving) return;
		doMove = true;
		currentX = x;
		currentY = y;
		currentMoveX = moveToX * Config.cellSize;
		currentMoveY = moveToY * Config.cellSize;		
		this.moveToX = moveToX;
		this.moveToY = moveToY;
		pixelsMoveToX = moveToX * Config.cellSize + Config.cellSize/2;
		pixelsMoveToY = moveToY * Config.cellSize + Config.cellSize/2;		
	}
	
	public void render(Graphics g, int x, int y, int width, int height, boolean animate, boolean showBorder, boolean drawBackground) {
		
		int	cell_size = width,
			tile_size = Config.tileSize,
			tile_border_width = Config.tileBorderWidth,
			x_width = x * cell_size,
			y_width = y * cell_size;
		
		if (drawBackground && type.isShowBackground())
			renderBackground(g, x, y, width, height);
		
		if (showBorder) {
			renderBorder(g, x, y, width, height, animate);
		}
		
		int tile_x = type.getTile_x(),
			tile_y = type.getTile_y();
		
		g.drawImage(Tiles.getTilesAsset(), 
        			x_width + (stepAnimation*-1), 
					y_width + (stepAnimation*-1), 
					x_width + cell_size - (stepAnimation*-1), 
					y_width + cell_size + (stepAnimation*-1),
					tile_x * tile_size + tile_x * tile_border_width + tile_border_width,
					tile_y * tile_size + tile_y * tile_border_width + tile_border_width,
					tile_x * tile_size + tile_size + tile_x * tile_border_width + tile_border_width,
					tile_y * tile_size + tile_size + tile_y * tile_border_width + tile_border_width,
        			null);
		
		updateTickerAnimation(6, 3);
		
	}
	
	protected void updateTickerAnimation(int stepTickAmount, int stepAmount) {
		updateStepTick++;		
		if (directionAnimation == 0)
		{
			if (stepAnimation <= -stepAmount) {
				directionAnimation = 1;
				stepAnimation++;
				updateStepTick = 0;
			} else {
				if (updateStepTick > stepTickAmount) {
					updateStepTick = 0;
					stepAnimation--;
				}
			}
			
		} else {
			if (stepAnimation >= stepAmount) {
				directionAnimation = 0;
				stepAnimation--;
				updateStepTick = 0;
			} else {
				if (updateStepTick > stepTickAmount) {
					updateStepTick = 0;
					stepAnimation++;
				}
			}
		}
	}	
}
