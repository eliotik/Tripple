package com.triple.game.grid;


import com.triple.game.Game;
import com.triple.game.elements.Element;
import com.triple.game.elements.ElementType;
import com.triple.game.elements.ElementTypesCollection;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Cell extends Rectangle{

	private int x;
    private int y;
    private int stepTick = 2;

    private static final long serialVersionUID = 1L;
    private Element element;
    private Element temporaryElement;
    private Element offCellElement;
    private Cell mainCell;
    private boolean showBorder = false;
    private boolean flop = false;

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
			element.render(g, x, y, width, height, false, showBorder, true);
		} else if (showBorder) {
			Element.renderBorder(g, x, y, width, height, false);
		}
		
		if (temporaryElement != null)
			temporaryElement.renderContainer(g, 0, 0, width-6, height-6, 1, -1, false, 0);

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
				ElementType elType = neighbors.get(i).getElement().getType();
				if (elementTypes.get(elType.getType()) != null && elementTypes.get(elType.getType()).size() > 0)
				{
					elementTypes.get(elType.getType()).add(elType);
				} else {
					ArrayList<ElementType> elTypes = new ArrayList<ElementType>();
					elTypes.add(elType);
					elementTypes.put(elType.getType(), elTypes);
					elements.add(elType.getType());
				}
                //neighbors.get(i).animateFlop(neighbors.get(i), this);
				//Game.getPlayerPanel().getPlayer().getScore().addScore(neighbors.get(i).getElement().getType().getScore());
				neighbors.get(i).setElement(null);
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
							Game.getPlayerPanel().getPlayer().getScore().addMultiplier(tempEls.get(0).getJoinScoreMultiplier());
						}
						for(int y = 0, c = tempEls.size(); y < c; ++y) {
							if (tempEls.get(y) != null && Game.getPlayerPanel() != null)
								Game.getPlayerPanel().getPlayer().getScore().addScore(tempEls.get(y).getScore());
						}
					}
				}
			}
			
			if (!newType.getId().equals(""))
			{
				Game.getPlayerPanel().getPlayer().getScore().addScore(element.getType().getScore());
				element.setType( newType );
				checkJoinables();
			}
		}
		
		Game.isJoinning = false;
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

//    private void animateFlop(Cell offCell, Cell mainCell){
//        System.out.println(offCell.getCoordinates()[0] + " " + offCell.getCoordinates()[1]);
//    }

}
