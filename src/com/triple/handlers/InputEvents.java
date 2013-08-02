package com.triple.handlers;

import com.triple.game.Game;
import com.triple.game.configs.Config;
import com.triple.game.elements.Element;
import com.triple.game.elements.ElementTypesCollection;
import com.triple.game.grid.Cell;
import com.triple.game.grid.Grid;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputEvents implements MouseListener, MouseMotionListener {
    public Cell[][] cells = new Cell[Grid.cellsAmount][Grid.cellsAmount];

    private Cell focusedCell;
    
    public void mouseClicked(MouseEvent e) {
        //System.out.println("Test mouseClicked");
    }

    public void mousePressed(MouseEvent e) {
    	if (Game.getGameState() != 1) return;
    	if (Game.isJoinning) return;
    	
    	int x = e.getX()/Game.pixelSize/Config.cellSize,
			y = e.getY()/Game.pixelSize/Config.cellSize;
    	
    	if (x < Grid.cellsAmount && y < Grid.cellsAmount) {
	        Cell cell = Game.grid.getCell(x, y);
	        if (cell.getElement() == null) {
	        	cell.setElement(Game.playerPanel.getPlayer().getHand().getElement());
	        	Game.playerPanel.getPlayer().getHand().setElement(ElementTypesCollection.getRandom());
	        	cell.checkJoinables();
	        } else {
	        	if (cell.getElement().getType().getContainer() && cell.getElement().getType().getId().equals("inventory"))
	        	{
	        		if (cell.getTemporaryElement() == null)
	        		{
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
        //System.out.println("Test mouseEntered");
    }

    public void mouseExited(MouseEvent e) {
        //System.out.println("Test mouseExited");
    }
    
    public void mouseMoved(MouseEvent e) {
    	if (Game.getGameState() != 1) return;
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
