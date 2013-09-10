package com.triple.game.grid;

import com.triple.game.configs.Config;
import com.triple.game.elements.Element;
import com.triple.game.elements.ElementTypesCollection;
import com.triple.game.elements.ElementsFactory;
import com.triple.game.elements.subspecies.ElementBear;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Grid {

	public static int cellsAmount = Config.cellsAmount;
    public Cell[][] cells = new Cell[Grid.cellsAmount][Grid.cellsAmount];
    private boolean bearMoved = false;

    public Grid() {
        for (int x = 0; x < cells.length; ++x) {
            for (int y = 0; y < cells[0].length; ++y) {
                cells[x][y] = new Cell(new Rectangle(x * Config.cellSizeX, y * Config.cellSizeY, Config.cellSizeX, Config.cellSizeY), x, y);
          }
        }
        generateGrid();
    }

    public void generateGrid() {
    	Random generator = new Random();
    	int prefilledCellsAmount = generator.nextInt(  (int) Math.floor((cellsAmount * cellsAmount) / 3) ) - 1;//-1 - place for inventory
    	int countElements = prefilledCellsAmount;
        int cellsTotal = cellsAmount * cellsAmount - 1;//-1 - place for inventory
    	List<Element> list = new ArrayList<Element>();
        String elementTypeCount = "0";
        int counter;
        HashMap<String, String> chanceContainer = new HashMap<String, String>();
    	while(prefilledCellsAmount > 0)
    	{
    		Element newElement =  ElementsFactory.getElement(ElementTypesCollection.getRandomByType("base"));
            elementTypeCount = chanceContainer.get( newElement.getType().getName() );
            if (elementTypeCount == null){
                elementTypeCount = "0";
            }
            counter = Integer.parseInt( elementTypeCount );
//            if (counter < countElements * Double.parseDouble(newElement.getType().getChance())){
            if (counter < countElements * newElement.getType().getChance()) {
                counter++;
                elementTypeCount = Integer.toString( counter );

                chanceContainer.put( newElement.getType().getName(), elementTypeCount );
                list.add(newElement);
                --prefilledCellsAmount;
            }
            else{
                break;
            }
        }

        cells[0][0].setElement( ElementsFactory.getElement(ElementTypesCollection.getTypeById("system", "inventory")) );
    	
    	for (int i = 0; i < cellsTotal; ++i) {
    		int randomX = generator.nextInt( cellsAmount );
        	int randomY = generator.nextInt( cellsAmount );
        	
        	int leftItems = list.size();
        	if (randomX == 0 && randomY == 0)
        	{
        		randomX = generator.nextInt( cellsAmount );
        	}
        	if (cells[randomX][randomY].getElement() == null && leftItems > 0)
        		cells[randomX][randomY].setElement( list.remove( leftItems - 1 ) );
    	}
    }

    public void refreshJoinableCells() {
    	for (int x = 0, l = cells.length; x < l; ++x) {
			for (int y = 0, c = cells[x].length; y < c; ++y) {
				cells[x][y].checkJoinables();
			}
		}    	
    }
    
    public void tick() {

    }

    public Cell getCell(int x, int y){
    	int x_s = cells.length;
    	if (x >= x_s || x < 0) return null;//return new Cell(new Rectangle(x * Config.cellSize, y * Config.cellSize, Config.cellSize, Config.cellSize), x, y);
    	int y_s = cells[x].length;
    	if (y >= y_s || y < 0) return null;//return new Cell(new Rectangle(x * Config.cellSize, y * Config.cellSize, Config.cellSize, Config.cellSize), x, y);
    	
        return cells[x][y];
    }

    public void render(Graphics g) {
        for (int x = 0; x < cells.length; ++x ) {
            for (int y = 0; y < cells[0].length; ++y) {
                cells[x][y].render(g);
                cells[x][y].setHotOfBear(false);
            }
        }
    }

	public void moveBears() {
		bearMoved = false;
		for (int x = 0; x < cells.length; ++x) {
            for (int y = 0; y < cells[0].length; ++y) {
            	Cell cell = cells[x][y];
                if (cell.getElement() != null && cell.getElement().getType().getSubspecies().equalsIgnoreCase("bear")) {
                	ElementBear Bear = (ElementBear) cell.getElement();
                	if (cell.isHotOfBear() || Bear.isMoved()) {
                		cell.setHotOfBear(false);
                	} else {
                		Bear.changeDislocation(cell);
//                		if (Bear.changeDislocation(cell)) {
//                			moveBears();
//                			return;
//                		}
                	}
                }
          }
        }
		if (bearMoved) {
			moveBears();
		}
		for (int x = 0; x < cells.length; ++x) {
            for (int y = 0; y < cells[0].length; ++y) {
            	Cell cell = cells[x][y];
                if (cell.getElement() != null && cell.getElement().getType().getSubspecies().equalsIgnoreCase("bear")) {
                	ElementBear Bear = (ElementBear) cell.getElement();
                	if (Bear.isMoved() || Bear.isSleeped()){
                		Bear.setMoved(false);
                		Bear.setSleeped(false);
                		continue;
                	}
                	if (Bear.neighborsNotMoved(cell)) {
                		cell.setElement( ElementsFactory.getElement( ElementTypesCollection.getTypeById("grave_base") ) );
                	}
                }
            }
		}
		refreshJoinableCells();
	}

	public boolean isBearMoved() {
		return bearMoved;
	}

	public void setBearMoved(boolean bearMoved) {
		this.bearMoved = bearMoved;
	}

    public HashMap<String, Element> getElements(){
        HashMap<String, Element> elements = new HashMap<String, Element>();
        for (int x = 0, l = cells.length; x < l; ++x) {
            for (int y = 0, c = cells[x].length; y < c; ++y) {
                if (cells[x][y].getElement() != null) {
                    elements.put("[ " + x + ", " + y + " ]", cells[x][y].getElement());
                }
            }
        }
        return elements;
    }

}
