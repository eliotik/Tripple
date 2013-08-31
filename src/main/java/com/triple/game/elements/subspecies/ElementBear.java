package com.triple.game.elements.subspecies;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.triple.game.Game;
import com.triple.game.configs.Config;
import com.triple.game.elements.Element;
import com.triple.game.elements.ElementType;
import com.triple.game.elements.ElementTypesCollection;
import com.triple.game.elements.ElementsFactory;
import com.triple.game.grid.Cell;
import com.triple.sprites.Tiles;

public class ElementBear extends Element {

//	private boolean doMove = false;
	private boolean isMoving = false;
//	private int currentX;
//	private int currentY;
//	private int moveToX;
//	private int moveToY;
//	private double pixelsMoveToX;
//	private double pixelsMoveToY;
//	private double currentMoveX;  
//	private double currentMoveY;  
	
	public ElementBear() {
		super();
	}
	
	public ElementBear(ElementType type) {
		super(type);
	}

	public void setMoveDirection(int x, int y, int moveToX, int moveToY) {
		if (isMoving) return;
//		doMove = true;
//		currentX = x;
//		currentY = y;
//		currentMoveX = moveToX * Config.cellSize;
//		currentMoveY = moveToY * Config.cellSize;		
//		this.moveToX = moveToX;
//		this.moveToY = moveToY;
//		pixelsMoveToX = moveToX * Config.cellSize + Config.cellSize/2;
//		pixelsMoveToY = moveToY * Config.cellSize + Config.cellSize/2;		
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

	public boolean changeDislocation(Cell currentCell) {
		
		ArrayList<String> directions = new ArrayList<String>();
		HashMap<String, Cell> cells = getEmptyNeighbors(currentCell, directions);
		if (directions.isEmpty()) {
			if (findNeighborBear(currentCell, cells)) {
				this.changeDislocation(currentCell);
			} else {
				currentCell.setElement( ElementsFactory.getElement( ElementTypesCollection.getTypeById("grave_base") ) );
			}
		} else {
			Cell newCell = getNewParentCell(directions, cells);
			currentCell.setElement(null);
			newCell.setElement((Element) this);
			newCell.setHotOfBear(true);
			return true;
		}
		return false;
	}

	private boolean findNeighborBear(Cell currentCell, HashMap<String, Cell> cells) {
		String[] keys = cells.keySet().toArray(new String[0]);
		for (int i = 0, l = keys.length; i < l; ++i) {
			Cell cell = cells.get(keys[i]);
			if (cell != null) {
				if (cell.isHotOfBear()) return true;
				Element el = cell.getElement();
				if (el != null && el.getType().getSubspecies().equalsIgnoreCase("bear")) {
					ElementBear Bear = (ElementBear) el;
					if ( Bear.changeDislocation(cell) ) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private Cell getNewParentCell(ArrayList<String> directions, HashMap<String, Cell> cells) {
		Random generator = new Random();
		String direction = directions.get( generator.nextInt(directions.size()) );
		return cells.get(direction);
	}

	private HashMap<String, Cell> getEmptyNeighbors(Cell currentCell, ArrayList<String> directions) {
		int[] coords = currentCell.getCoordinates();
		int currentX = coords[0];
		int currentY = coords[1];
		
		Cell cell_b = Game.grid.getCell(currentX, currentY - 1);
		Cell cell_d = Game.grid.getCell(currentX + 1, currentY);
		Cell cell_f = Game.grid.getCell(currentX, currentY + 1);
		Cell cell_h = Game.grid.getCell(currentX - 1, currentY);
		
		if (cell_b != null && cell_b.getElement() == null) {
			directions.add("b");
		}
		if (cell_d != null && cell_d.getElement() == null) {
			directions.add("d");
		}
		if (cell_f != null && cell_f.getElement() == null) {
			directions.add("f");
		}
		if (cell_h != null && cell_h.getElement() == null) {
			directions.add("h");
		}
		
		HashMap<String, Cell> cells = new HashMap<String, Cell>();
		cells.put("b", cell_b);
		cells.put("d", cell_d);
		cells.put("f", cell_f);
		cells.put("h", cell_h);		
		
		return cells;
	}

}
