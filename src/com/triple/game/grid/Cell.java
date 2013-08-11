package com.triple.game.grid;


import com.triple.game.Game;
import com.triple.game.configs.Config;
import com.triple.game.elements.Element;
import com.triple.game.elements.ElementType;
import com.triple.game.elements.ElementTypesCollection;

import java.awt.*;
import java.util.*;

public class Cell extends Rectangle{

    private static final long serialVersionUID = 1L;

    private int x;
    private int y;
    private int collapseNewX;
    private int collapseNewY;
    private int stepCollapsionX;
    private int stepCollapsionY;
    private int collapseX;
    private int collapseY;
    private int collapseStep;
    private int collapseStepDelta;
    private int collapsionPath;
    private boolean doCollapse = false;
    
    private Element element;
    private Element temporaryElement;
    private boolean showBorder = false;

    public Cell(Rectangle size, int x, int y) {
        setBounds(size);
        this.x = x;
        this.y = y;
    }

    public int[] getCoordinates() {
        int[] coords = {x, y};
        return coords;
    }

    public Element getElement() {
        return element;
    }

    public void render(Graphics g) {

        if (element != null)
        {
        	if (doCollapse == true) {
        		if (collapseStep < collapsionPath - Config.cellSize/2) {
        			int directionX = (collapseNewX < collapseX*Config.cellSize) ? ((collapseNewX == collapseX*Config.cellSize) ? 0 : 1) : ((collapseNewX == collapseX*Config.cellSize) ? 0 : -1),
    					directionY = (collapseNewY < collapseY*Config.cellSize) ? ((collapseNewY == collapseY*Config.cellSize) ? 0 : 1) : ((collapseNewY == collapseY*Config.cellSize) ? 0 : -1);
					if (collapseStepDelta >= Config.collapseStepDelta) {
						collapseNewX += directionX * Config.collapseStepSize;
						collapseNewY += directionY * Config.collapseStepSize;
						collapseStep += collapsionPath - getVector(collapseNewX, collapseX*Config.cellSize + Config.cellSize/2, collapseNewY, collapseY*Config.cellSize + Config.cellSize/2);
						stepCollapsionX = directionX  * collapseStep;
						stepCollapsionY = directionY  * collapseStep;
						collapseStepDelta = 0;
					} else {
						++collapseStepDelta;
					}
        			element.renderCollapsing(g, x, y, width, height, stepCollapsionX, stepCollapsionY);
        		} else {
        			collapseStep = 0;
        			collapseNewX = x;
        			collapseNewY = y;
        			stepCollapsionX = 0;
        			stepCollapsionY = 0;
        			collapseStepDelta = 0;
	        		element = null;
	        		doCollapse = false;
        		}
        		
        	} else {
        		element.render(g, x, y, width, height, false, showBorder, true);
        	}
        } else if (showBorder) {
            Element.renderBorder(g, x, y, width, height, false);
        }

        if (temporaryElement != null)
            temporaryElement.renderContainer(g, 0, 0, width-6, height-6, 1, -1, false, 0);
    }

    private int getVector(double x1, double x2, double y1, double y2) {
    	return (int) Math.sqrt(Math.pow(x2 - x1 , 2) + Math.pow(y2 - y1, 2) );
    }
    
    public void setElement(Element el) {
        element = el;
    }

    public void setTemporaryElement(Element el) {
        temporaryElement = el;
    }

    public Element getTemporaryElement() {
        return temporaryElement;
    }

    public boolean isShowBorder() {
        return showBorder;
    }

    public void setShowBorder(boolean showBorder) {
        this.showBorder = showBorder;
    }

    public void checkJoinables() {
        Game.isJoinning = true;

        ArrayList<Cell> neighbors = new ArrayList<Cell>();
        HashMap<String, ArrayList<ElementType>> elementTypes = new HashMap<String, ArrayList<ElementType>>();
        ArrayList<String> elements = new ArrayList<String>();

        findNeigbors(neighbors, this, this);

        if (neighbors.size() >= 2) {
        	
            for (int i = 0, l = neighbors.size(); i < l; ++i) {
            	Cell neighbor = neighbors.get(i);
            	neighbor.collapse(x, y);
            	
                ElementType elType = neighbor.getElement().getType();
                if (elementTypes.get(elType.getType()) != null && elementTypes.get(elType.getType()).size() > 0)
                {
                    elementTypes.get(elType.getType()).add(elType);
                } else {
                    ArrayList<ElementType> elTypes = new ArrayList<ElementType>();
                    elTypes.add(elType);
                    elementTypes.put(elType.getType(), elTypes);
                    elements.add(elType.getType());
                }

            }
            String sufix = (neighbors.size() > 2) ? "_multi": "_base";
            ElementType newType = ElementTypesCollection.getTypeById( element.getType().getJoinResult() + sufix );

            if (elementTypes.get(element.getType().getType()) != null && elementTypes.get(element.getType().getType()).size() > 0)
            {
                elementTypes.get(element.getType().getType()).add(element.getType());
            } else {
                ArrayList<ElementType> elTypes = new ArrayList<ElementType>();
                elTypes.add(element.getType());
                elementTypes.put(element.getType().getType(), elTypes);
                elements.add(element.getType().getType());
            }

            if (elements.size() > 0) {
                for (int i = 0, l = elements.size(); i < l; ++i) {
                    ArrayList<ElementType> tempEls = elementTypes.get(elements.get(i));
                    if (tempEls != null && tempEls.size() > 0) {
                        if (tempEls.size() > 3 && tempEls.get(0) != null && Game.getPlayerPanel() != null) {
                            Game.getPlayerPanel().getPlayer(0).getScore().addMultiplier(tempEls.get(0).getJoinScoreMultiplier());
                        }
                        for(int y = 0, c = tempEls.size(); y < c; ++y) {
                            if (tempEls.get(y) != null && Game.getPlayerPanel() != null)
                                Game.getPlayerPanel().getPlayer(0).getScore().addScore(tempEls.get(y).getScore());
                        }
                    }
                }
            }

            if (!newType.getId().equals("") && Game.getPlayerPanel() != null)
            {
                Game.getPlayerPanel().getPlayer(0).getScore().addScore(element.getType().getScore());
                element.setType( newType );
                checkJoinables();
            }
        }

        Game.isJoinning = false;
    }

    private void collapse(int collapseX, int collapseY) {
    	this.collapseX = collapseX;
    	this.collapseY = collapseY;
    	this.doCollapse = true;
    	collapseStep = 0;
    	collapseNewX = x*Config.cellSize;
    	collapseNewY = y*Config.cellSize;
    	stepCollapsionX = 0;
    	stepCollapsionY = 0;
    	collapseStepDelta = 0;
    	collapsionPath = getVector(x*Config.cellSize, collapseX*Config.cellSize + Config.cellSize/2, y*Config.cellSize, collapseY*Config.cellSize + Config.cellSize/2);
	}

	private void findNeigbors(ArrayList<Cell> outerNeighbors, Cell excludeCell, Cell mainCell) {
        Cell cell;
        for (int i = 0, l = 4; i < l; ++i) {
            switch(i) {
                case 1:
                    cell = Game.grid.getCell(mainCell.getCoordinates()[0]+1, mainCell.getCoordinates()[1]);
                    break;
                case 2:
                    cell = Game.grid.getCell(mainCell.getCoordinates()[0], mainCell.getCoordinates()[1]+1);
                    break;
                case 3:
                    cell = Game.grid.getCell(mainCell.getCoordinates()[0]-1, mainCell.getCoordinates()[1]);
                    break;
                default:
                    cell = Game.grid.getCell(mainCell.getCoordinates()[0], mainCell.getCoordinates()[1]-1);
                    break;
            }
            if ( cell != null && cell != excludeCell && cell.getElement() != null && mainCell.getElement() != null &&
                    cell.getElement().getType().getSubspecies().equals(mainCell.getElement().getType().getSubspecies()) &&
                    !cell.getElement().getType().getJoinResult().equals(cell.getElement().getType().getId()) )
            {
                outerNeighbors.add(cell);
                findNeigbors(outerNeighbors, mainCell, cell);
            }
        }
        cell = null;
    }

}
