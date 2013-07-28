package ua.triple.game.grid;

import ua.triple.game.configs.Config;
import ua.triple.game.elements.Element;
import ua.triple.game.elements.ElementTypesCollection;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {

	public static int cellsAmount = 8;
    public Cell[][] cells = new Cell[Grid.cellsAmount][Grid.cellsAmount];

    public Grid() {
        for (int x = 0; x < cells.length; ++x) {
            for (int y = 0; y < cells[0].length; ++y) {
                cells[x][y] = new Cell(new Rectangle(x * Config.cellSize, y * Config.cellSize, Config.cellSize, Config.cellSize), x, y);
          }
        }
        generateGrid();
    }

    public void generateGrid() {
    	Random generator = new Random();
    	int prefilledCellsAmount = generator.nextInt(  (int) Math.floor((cellsAmount * cellsAmount) / 3) ) - 1;//-1 - place for inventory
    	int cellsTotal = cellsAmount * cellsAmount - 1;//-1 - place for inventory
    	List<Element> list = new ArrayList<Element>();
    	while(prefilledCellsAmount > 0)
    	{
    		list.add(new Element(ElementTypesCollection.getRandomByType("base")));
    		--prefilledCellsAmount;
    	}
    	
    	cells[0][0].setElement( new Element(ElementTypesCollection.getTypeById("system", "inventory")) );
    	
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
    		
    	
    	/*
        for (int x = 0; x < cells.length; ++x ) {
            for (int y = 0; y < cells[0].length; ++y) {
            	if ( x == 0 && y == 0 ) {
            		cells[x][y].setElement( new Element(ElementTypesCollection.getTypeById("system", "inventory")) );
            	} else {
            		int leftItems = list.size();
            		if( generator.nextInt( cellsTotal ) < (int)Math.floor(cellsTotal/(generator.nextInt(cellsAmount)+1) ) && leftItems > 0 ) {
            			cells[x][y].setElement( list.remove( leftItems - 1 ) );
            		}
            	}
            }
        }*/
    }

    public void tick() {

    }

    public void render(Graphics g) {
        for (int x = 0; x < cells.length; ++x ) {
            for (int y = 0; y < cells[0].length; ++y) {
                cells[x][y].render(g);
            }
        }
    }
}
