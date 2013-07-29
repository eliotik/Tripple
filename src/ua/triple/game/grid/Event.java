package ua.triple.game.grid;

import ua.triple.game.Game;
import ua.triple.game.configs.Config;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Event implements MouseListener {
    private Grid grid;
    public Cell[][] cells = new Cell[Grid.cellsAmount][Grid.cellsAmount];


    public Event(Grid grid){
        this.grid = grid;
    }
    public void mouseClicked(MouseEvent e) {
        //System.out.println("Test mouseClicked");
    }

    public void mousePressed(MouseEvent e) {
    	int x = e.getX()/Game.pixelSize/Config.cellSize,
			y = e.getY()/Game.pixelSize/Config.cellSize;
    	if (x < Grid.cellsAmount && y < Grid.cellsAmount)
    	{
	    	System.out.println( e.getX()/Game.pixelSize/Config.cellSize + " " + e.getY()/Game.pixelSize/Config.cellSize );
	        Cell cell = grid.getCell(x, y);
	        System.out.println(cell.getElement().toString());
	        System.out.println("Test mousePressed");
    	}
    }

    public void mouseReleased(MouseEvent e) {
        //System.out.println("Test mouseReleased");
    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("Test mouseEntered");
    }

    public void mouseExited(MouseEvent e) {
        System.out.println("Test mouseExited");
    }

}
