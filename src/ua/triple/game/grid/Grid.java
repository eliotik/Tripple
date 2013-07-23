package ua.triple.game.grid;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ua.triple.game.configs.Config;
import ua.triple.game.configs.Utils;
import ua.triple.game.elements.Element;
import ua.triple.game.elements.ElementTypesCollection;

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
    	int prefilledCellsAmount = generator.nextInt(  (int) Math.floor((cellsAmount * cellsAmount) / 3) );
    	List<Element> list = new ArrayList<Element>();
    	while(prefilledCellsAmount > 0)
    	{
    		list.add(new Element(ElementTypesCollection.getRandomByType("base")));
    		--prefilledCellsAmount;
    	}
    	
    	Utils.print(list.size());
    	
        for (int x = 0; x < cells.length; ++x ) {
            for (int y = 0; y < cells[0].length; ++y) {
            	if(generator.nextInt(3) == 1 && list.size() > 0)
            	{
            		cells[x][y].setElement( list.remove( list.size() - 1 ) );
            	}
            }
        }
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
