package com.triple.handlers;

import com.triple.game.Game;
import com.triple.game.configs.Config;
import com.triple.game.grid.Cell;
import com.triple.game.grid.Grid;
import com.triple.menu.Button;
import com.triple.menu.Menu;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputEvents implements MouseListener, MouseMotionListener {
    public Cell[][] cells = new Cell[Grid.cellsAmount][Grid.cellsAmount];

    private Cell focusedCell;

	private Game game;
	private Button hoveredButton;
    
	public InputEvents(Game game) {
		this.game = game;
	}    
    
	public void mouseClicked(MouseEvent e) {
		if (Game.getGameState() != 0) return;
		Menu menu = game.getMenu();
    	int mouse = e.getButton();
    	Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);
    	switch(mouse) {
    	case MouseEvent.BUTTON1:
    		if (r.intersects(menu.getButton("play").getButtonBounds())) {
    			menu.getButton("play").setClicked(true);
    			Game.setGameState(1);
    		} else if (r.intersects(menu.getButton("multi").getButtonBounds())) {
    			menu.getButton("multi").setClicked(true);
    			Game.setGameState(2);
    		}
    		break;
    	}
	}
	
	private void setHoveredButton(Button button) {
		hoveredButton = button;
		hoveredButton.setHovered(true);
	}
	
	private void checkHoveredButton(Rectangle r, Menu menu) {
		if (r.intersects(menu.getButton("play").getButtonBounds())) {
			setHoveredButton(menu.getButton("play"));
		} else if (r.intersects(menu.getButton("multi").getButtonBounds())) {
			setHoveredButton(menu.getButton("multi"));
		}
	}
	
    public void mousePressed(MouseEvent e) {
    	if (Game.getGameState() != 1 && Game.getGameState() != 2) return;
    	if (Game.isJoinning) return;
    	
    	int x = e.getX()/Game.pixelSize/Config.cellSize,
			y = e.getY()/Game.pixelSize/Config.cellSize;
    	
    	if (x < Grid.cellsAmount && y < Grid.cellsAmount) {
	        Cell cell = Game.grid.getCell(x, y);
	        if (cell != null) {
		        if (cell.getElement() == null) {
		        	cell.setElement(Game.getPlayerPanel().getPlayer().getHand().getElement());
		        	Game.getPlayerPanel().getPlayer().getHand().setElement(Game.elementTypesCollection.getRandomForHand());
		        	cell.checkJoinables();
		        } else {
		        	if (cell.getElement().getType().getContainer() && cell.getElement().getType().getId().equals("inventory"))
		        	{
		        		if (cell.getTemporaryElement() == null)
		        		{
	                        cell.setTemporaryElement(Game.getPlayerPanel().getPlayer().getHand().getElement());
			        		Game.getPlayerPanel().getPlayer().getHand().setElement(Game.elementTypesCollection.getRandomForHand());
		        		} else {
		        			Game.getPlayerPanel().getPlayer().getHand().setElement(cell.getTemporaryElement());
		        			cell.setTemporaryElement(Game.elementTypesCollection.getRandomForHand());
		        		}
		        	}
	
	                if (Game.getPlayerPanel().getPlayer().getHand().getElement().getType().getId().equals("robot_base")){
	                    cell.setElement(null);
	                    Game.getPlayerPanel().getPlayer().getHand().setElement(Game.elementTypesCollection.getRandomForHand());
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
    	if (Game.getGameState() == 1 || Game.getGameState() == 2) {
    	
	    	int x = e.getX()/Game.pixelSize/Config.cellSize,
				y = e.getY()/Game.pixelSize/Config.cellSize;
	    	if (x < Grid.cellsAmount && y < Grid.cellsAmount) {
	    		if (focusedCell != null) {
	    			focusedCell.setShowBorder(false);
	    		}
	    		Cell cell = Game.grid.getCell(x, y);
	    		if (cell != null) {
		    		cell.setShowBorder(true);
		    		focusedCell = cell;
	    		}
	    	}
    	} else if (Game.getGameState() == 0) {
        	Menu menu = game.getMenu();
        	Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);
        	if (hoveredButton != null) {
        		if (!r.intersects(hoveredButton.getButtonBounds())) {
        			hoveredButton.setHovered(false);
        			checkHoveredButton(r, menu);
        		} else {
        			hoveredButton.setHovered(true);
        		}
        	} else {
        		checkHoveredButton(r, menu);
        	}
    	}
    }

	public void mouseDragged(MouseEvent e) {
		
	}

}
