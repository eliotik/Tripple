package com.triple.game.elements.subspecies;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
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

	public void changeDislocation(Cell currentCell) {
		int[] coords = currentCell.getCoordinates();
		int currentX = coords[0];
		int currentY = coords[1];
//		System.out.println("<--------------->");
//		System.out.println(coords[0]+","+coords[1]);
		Cell cell_b = Game.grid.getCell(currentX, currentY - 1);
		Cell cell_d = Game.grid.getCell(currentX + 1, currentY);
		Cell cell_f = Game.grid.getCell(currentX, currentY + 1);
		Cell cell_h = Game.grid.getCell(currentX - 1, currentY);
//		if (cell_b != null) System.out.println("b: "+cell_b.getCoordinates()[0]+","+cell_b.getCoordinates()[1]);
//		if (cell_d != null) System.out.println("d: "+cell_d.getCoordinates()[0]+","+cell_d.getCoordinates()[1]);
//		if (cell_f != null) System.out.println("f: "+cell_f.getCoordinates()[0]+","+cell_f.getCoordinates()[1]);
//		if (cell_h != null) System.out.println("h: "+cell_h.getCoordinates()[0]+","+cell_h.getCoordinates()[1]);
		ArrayList<String> directions = new ArrayList<String>();
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
		if (directions.isEmpty()) {
			if ( moveNeighborsBears(currentCell, cell_b, cell_d, cell_f, cell_h) ) {
				changeDislocation(currentCell);
				return;
//				System.out.println("[Trying to move again]");
			} else {
				currentCell.setElement( ElementsFactory.getElement( ElementTypesCollection.getTypeById("grave_base") ) );
				currentCell.checkJoinables();
//				System.out.println("GRAVE");
//				System.out.println(">---------------<");
			}
		} else {
			HashMap<String, Cell> cells = new HashMap<String, Cell>();
			cells.put("b", cell_b);
			cells.put("d", cell_d);
			cells.put("f", cell_f);
			cells.put("h", cell_h);
			Random generator = new Random();
			String direction = directions.get( generator.nextInt(directions.size()) );
			Cell newCell = cells.get(direction);
			Element bear = this;
			currentCell.setElement(null);
			newCell.setElement(bear);
			newCell.setHotOfBear(true);
//			System.out.println(newCell.getCoordinates()[0]+","+newCell.getCoordinates()[1]);
//			System.out.println(">---------------<");
		}
	}

	private Cell getDirectionCell(Cell currentCell, Cell excludeCell) {
		int[] coords = currentCell.getCoordinates();
		int currentX = coords[0];
		int currentY = coords[1];
		Cell cell_b = Game.grid.getCell(currentX, currentY - 1);
		Cell cell_d = Game.grid.getCell(currentX + 1, currentY);
		Cell cell_f = Game.grid.getCell(currentX, currentY + 1);
		Cell cell_h = Game.grid.getCell(currentX - 1, currentY);
		
		ArrayList<String> directions = new ArrayList<String>();
		if (cell_b != null && cell_b.getElement() == null && !cell_b.equals(excludeCell)) {
			directions.add("b");
		}
		if (cell_d != null && cell_d.getElement() == null && !cell_d.equals(excludeCell)) {
			directions.add("d");
		}
		if (cell_f != null && cell_f.getElement() == null && !cell_f.equals(excludeCell)) {
			directions.add("f");
		}
		if (cell_h != null && cell_h.getElement() == null && !cell_h.equals(excludeCell)) {
			directions.add("h");
		}
		if (directions.isEmpty()) {
			return null;
		} else {
			HashMap<String, Cell> cells = new HashMap<String, Cell>();
			cells.put("b", cell_b);
			cells.put("d", cell_d);
			cells.put("f", cell_f);
			cells.put("h", cell_h);
			Random generator = new Random();
			String direction = directions.get( generator.nextInt(directions.size()) );
			return cells.get(direction);
		}		
	}
	
	private boolean moveNeighborsBears(Cell currentCell, Cell cell_b,
			Cell cell_d, Cell cell_f, Cell cell_h) {
		
		if (cell_b != null && cell_b.getElement() != null && 
			cell_b.getElement().getType().getSubspecies().equalsIgnoreCase("bear") ) {
			Cell newCell = getDirectionCell(cell_b, currentCell);
			if (newCell != null) {
				Element bear = cell_b.getElement();
				cell_b.setElement(null);
				newCell.setElement(bear);
				newCell.setHotOfBear(true);
				return true;
			}
		}

		if (cell_d != null && cell_d.getElement() != null && 
			cell_d.getElement().getType().getSubspecies().equalsIgnoreCase("bear") ) {
			Cell newCell = getDirectionCell(cell_d, currentCell);
			if (newCell != null) {
				Element bear = cell_d.getElement();
				cell_d.setElement(null);
				newCell.setElement(bear);
				newCell.setHotOfBear(true);
				return true;
			}
		}		
		
		if (cell_f != null && cell_f.getElement() != null && 
			cell_f.getElement().getType().getSubspecies().equalsIgnoreCase("bear") ) {
			Cell newCell = getDirectionCell(cell_f, currentCell);
			if (newCell != null) {
				Element bear = cell_f.getElement();
				cell_f.setElement(null);
				newCell.setElement(bear);
				newCell.setHotOfBear(true);
				return true;
			}
		}
		
		if (cell_h != null && cell_h.getElement() != null && 
			cell_h.getElement().getType().getSubspecies().equalsIgnoreCase("bear") ) {
			Cell newCell = getDirectionCell(cell_h, currentCell);
			if (newCell != null) {
				Element bear = cell_h.getElement();
				cell_h.setElement(null);
				newCell.setElement(bear);
				newCell.setHotOfBear(true);
				return true;
			}
		}		
		
		return false;
	}	
}
