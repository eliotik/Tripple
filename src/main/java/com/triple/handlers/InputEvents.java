package com.triple.handlers;

import com.triple.game.Game;
import com.triple.game.configs.Config;
import com.triple.game.elements.Element;
import com.triple.game.elements.ElementTypesCollection;
import com.triple.game.elements.ElementsFactory;
import com.triple.game.elements.subspecies.ElementBear;
import com.triple.game.grid.Cell;
import com.triple.game.grid.Grid;
import com.triple.game.player.Player;
import com.triple.menu.Button;
import com.triple.menu.Menu;
import com.triple.network.DataSerialise;
import com.triple.network.NetworkCollection;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

public class InputEvents implements MouseListener, MouseMotionListener {
    public Cell[][] cells = new Cell[Grid.cellsAmount][Grid.cellsAmount];

    private Cell focusedCell;
    private DataSerialise dataSerialise = new DataSerialise();
	private Game game;
	private Button hoveredButton;
    
	public InputEvents(Game game) {
		this.game = game;
	}    
    
	public void mouseClicked(MouseEvent e) {
		if (Game.getGameState() != 0 && Game.getGameState() != 2) return;
		Menu menu = game.getMenu();
    	int mouse = e.getButton();
    	Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);
    	switch(mouse) {
	    	case MouseEvent.BUTTON1:
	    		if (r.intersects(menu.getButton("play").getButtonBounds()) &&  Game.getGameState() == 0) {
	    			//menu.getButton("play").setClicked(true);
	    			Game.setGameState(1);
	    		} else if (r.intersects(menu.getButton("multi").getButtonBounds()) &&  Game.getGameState() == 0) {
	    			//menu.getButton("multi").setClicked(true);
	    			Game.setGameState(2);
	    		} else if (r.intersects(menu.getButton("mpstart").getButtonBounds()) &&  Game.getGameState() == 2) {
	    			//menu.getButton("mpstart").setClicked(true);
	    			game.startServer();
	    			game.startClient();
	    			Game.setGameState(4);
	    		} else if (r.intersects(menu.getButton("mpjoin").getButtonBounds()) &&  Game.getGameState() == 2) {
	    			//menu.getButton("mpjoin").setClicked(true);
	    			game.startClient();
	    			Game.setGameState(4);
	    		} else if (r.intersects(menu.getButton("mpback").getButtonBounds()) &&  Game.getGameState() == 2) {
	    			Game.setGameState(0);
	    		}
    		break;
    	}
	}
	
	private void setHoveredButton(Button button) {
		hoveredButton = button;
		hoveredButton.setHovered(true);
	}
	
	private void checkHoveredButton(Rectangle r, Menu menu) {
		if (r.intersects(menu.getButton("play").getButtonBounds()) &&  Game.getGameState() == 0) {
			setHoveredButton(menu.getButton("play"));
		} else if (r.intersects(menu.getButton("multi").getButtonBounds()) &&  Game.getGameState() == 0) {
			setHoveredButton(menu.getButton("multi"));
		} else if (r.intersects(menu.getButton("mpstart").getButtonBounds()) &&  Game.getGameState() == 2) {
			setHoveredButton(menu.getButton("mpstart"));
		} else if (r.intersects(menu.getButton("mpjoin").getButtonBounds()) &&  Game.getGameState() == 2) {
			setHoveredButton(menu.getButton("mpjoin"));
		} else if (r.intersects(menu.getButton("mpback").getButtonBounds()) &&  Game.getGameState() == 2) {
			setHoveredButton(menu.getButton("mpback"));
		}
	}
	
    public void mousePressed(MouseEvent e) {
    	if (Game.getGameState() != 1 && Game.getGameState() != 4) return;
    	if (Game.isJoinning) return;
    	if (Game.isStarted == false) Game.isStarted = true;
    	
    	int x = e.getX()/Game.pixelSize/Config.cellSizeX,
			y = e.getY()/Game.pixelSize/Config.cellSizeY;
    	
    	if (x < Grid.cellsAmount && y < Grid.cellsAmount) {
	        Cell cell = Game.grid.getCell(x, y);
	        if (cell != null) {
	        	Player player = Game.getPlayerPanel().getPlayer(0);
		        if (cell.getElement() == null &&
	        		!player.getHand().getElement().getType().getSubspecies().equals("robot")) {
		        	
		        	cell.setElement(player.getHand().getElement());
		        	player.getHand().setElement(ElementTypesCollection.getRandomForHand());
		        	if (cell.getElement().getType().getJoinable()) {
		        		cell.checkJoinables();
		        	}
		        	if (cell.getElement().getType().getSubspecies().equalsIgnoreCase("Bear")) {
		        		ElementBear Bear = (ElementBear) cell.getElement();
                		Bear.changeDislocation(cell);
		        	}
		        	Game.grid.moveBears();
		        } else {
		        	if (cell.getElement() != null) {
		        		if ( cell.getElement().getType().getContainer() && cell.getElement().getType().getId().equals("inventory"))
		        		{
			        		if (cell.getTemporaryElement() == null)
			        		{
		                        cell.setTemporaryElement(player.getHand().getElement());
		                        player.getHand().setElement(ElementTypesCollection.getRandomForHand());
			        		} else {
			        			Element playerElement = player.getHand().getElement();
			        			player.getHand().setElement(cell.getTemporaryElement());
			        			cell.setTemporaryElement(playerElement);
			        		}
		        		} else if (player.getHand().getElement().getType().getSubspecies().equals("robot")){
		        			player.getScore().addScore(cell.getElement().getType().getPenalty());
		        			if (cell.getElement().getType().getSubspecies().equalsIgnoreCase("Bear")) {
		        				cell.setElement(ElementsFactory.getElement(ElementTypesCollection.getTypeById("grave_base")));
		        				cell.checkJoinables();
		        			} else {
		        				cell.setElement(null);
		        			}
		        			Game.grid.moveBears();		        			
		        			player.getHand().setElement(ElementTypesCollection.getRandomForHand());
		        		}
		        	}
		        }
	        }
    	}

        if (Game.getGameState() == 2) {
//            byte[] playerData = dataSerialise.getSerialisePlayer(Game.getPlayerPanel().getPlayer(0));
//            byte[] gridData = dataSerialise.getSerialiseGrid(Game.grid);
//            Game.getClient().sendData(playerData);
//            Game.getClient().sendData(gridData);
        }

    	if (Game.getGameState() == 4) {
            HashMap<String, HashMap<String, Object>> sendData = new HashMap<String, HashMap<String, Object>>();
//            HashMap<String, Element> elements = Game.grid.getElements();
            System.out.println(Game.grid.getElements().toString());
            NetworkCollection networkCollection = new NetworkCollection(Game.getPlayerPanel().getPlayer(0), Game.grid.getElements());

            byte[] data = dataSerialise.getSerialisedNetworkCollection(networkCollection);

            Game.getClient().sendData(data);

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
    	if (Game.getGameState() == 1 || Game.getGameState() == 4) {
    	
	    	int x = e.getX()/Game.pixelSize/Config.cellSizeX,
				y = e.getY()/Game.pixelSize/Config.cellSizeY;
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
    	} else if (Game.getGameState() == 0 || Game.getGameState() == 2) {
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
