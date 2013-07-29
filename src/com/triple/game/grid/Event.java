package com.triple.game.grid;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.triple.game.Game;
import com.triple.game.configs.Config;
import com.triple.game.elements.Element;
import com.triple.game.elements.ElementTypesCollection;

public class Event implements MouseListener, MouseMotionListener {
    public Cell[][] cells = new Cell[Grid.cellsAmount][Grid.cellsAmount];

    private Cell focusedCell;
    
    public void mouseClicked(MouseEvent e) {
        //System.out.println("Test mouseClicked");
    }

    public void mousePressed(MouseEvent e) {
    	int x = e.getX()/Game.pixelSize/Config.cellSize,
			y = e.getY()/Game.pixelSize/Config.cellSize;
    	if (x < Grid.cellsAmount && y < Grid.cellsAmount) {
	        Cell cell = Game.grid.getCell(x, y);
	        if (cell.getElement() == null) {
	        	cell.setElement(Game.playerPanel.getPlayer().getHand().getElement());
	        	Game.playerPanel.getPlayer().getHand().setElement(ElementTypesCollection.getRandom());
	        } else {
//	        	System.out.println(cell.getElement().getType().getId());
//	        	System.out.println(cell.getTemporaryElement() == null);
//	        	System.out.println((String)cell.getElement().getType().getId() == (String)"inventory");
//	        	String cellId = cell.getElement().getType().getId();
//	        	String str = "inventory";
//	        	System.out.println("["+cellId+"]");
//	        	System.out.println(str.toString() == cellId.toString());
	        	if (cell.getElement().getType().getContainer())
	        	{
	        		if (cell.getTemporaryElement() == null)
	        		{
	//	        		System.out.println("[EQUAL]");
		        		cell.setTemporaryElement(Game.playerPanel.getPlayer().getHand().getElement());
		        		Game.playerPanel.getPlayer().getHand().setElement(ElementTypesCollection.getRandom());
	        		} else {
	        			Element hand = Game.playerPanel.getPlayer().getHand().getElement();
	        			Game.playerPanel.getPlayer().getHand().setElement(cell.getTemporaryElement());
	        			cell.setTemporaryElement(hand);
	        		}
	        	}
	        }
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
    public void mouseMoved(MouseEvent e) {
    	int x = e.getX()/Game.pixelSize/Config.cellSize,
			y = e.getY()/Game.pixelSize/Config.cellSize;
    	if (x < Grid.cellsAmount && y < Grid.cellsAmount) {
    		if (focusedCell != null) {
    			focusedCell.setShowBorder(false);
    		}
    		Cell cell = Game.grid.getCell(x, y);
    		cell.setShowBorder(true);
    		focusedCell = cell;
    	}
    }

	public void mouseDragged(MouseEvent e) {
		
	}

}