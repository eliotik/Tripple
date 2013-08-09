package com.triple.game.grid;


import com.triple.game.Game;
import com.triple.game.elements.Element;
import com.triple.game.elements.ElementType;
import com.triple.game.elements.ElementTypesCollection;

import java.awt.*;
import java.util.*;
import java.util.List;

import static com.triple.sprites.Tiles.getTilesAsset;

public class Cell extends Rectangle{

    private static final long serialVersionUID = 1L;

	private int x;
    private int y;
    private int collapseX;
    private int collapseY;
    private Element element;
    private Element temporaryElement;
    private boolean showBorder = false;
    private boolean isCollapse = false;
    private List<Cell> collapsedCellList = new ArrayList<Cell>();

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

        if (collapsedCellList != null)
        {
            for (Cell collapseCell : collapsedCellList)
            {
                if (collapseCell.isCollapse == true)
                {
//                    System.out.println("collapseX = " + collapseCell.collapseX);
//                    System.out.println("collapseY = " + collapseCell.collapseY);
//                    System.out.println("x = " + collapseCell.x);
//                    System.out.println("y = " + collapseCell.y);
//                    System.out.println("name = " + collapseCell.toString());
//                    int x = getChangedCoordinate(collapseCell.x , collapseCell.width),
//                        y = getChangedCoordinate(collapseCell.y , collapseCell.height),
//                        sx = getChangedCoordinate(collapseCell.collapseX , collapseCell.height),
//                        sy = getChangedCoordinate(collapseCell.collapseY , collapseCell.height);
//                    renderPartial(g, x, y, collapseCell.width, collapseCell.height, sx, sy);
                    int dx = getChangedCoordinate( Math.abs(collapseCell.collapseX - collapseCell.x), collapseCell.width ),
                        dy = getChangedCoordinate( Math.abs(collapseCell.collapseY - collapseCell.y), collapseCell.height );
                    if (dx > 10 || dx > 10)
                    {
                        renderPartial(g, collapseCell.collapseX, collapseCell.collapseY,
                                         collapseCell.width, collapseCell.height, collapseCell.x, collapseCell.y );
                    } else {
                        collapseCell.setElement(null);
                    }

                }
            }
        }

    }

    public void renderPartial(Graphics g, int x, int y, int width, int height, int sx, int sy) {
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        System.out.println("sx = " + sx);
        System.out.println("sy = " + sy);

        g.drawImage(getTilesAsset(),
                getChangedCoordinate(x, width) + 1,
                getChangedCoordinate(y, width) + 1,
                getChangedCoordinate(x, width) + width + 1,
                getChangedCoordinate(y, width) + height + 1,
                sx,
                sx,
                sx + width,
                sx + height,
                null);

    }

    private int getChangedCoordinate(int position, int multiplier) {

        int coordinate = position * multiplier;

        return coordinate;
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

				//Game.getPlayerPanel().getPlayer().getScore().addScore(neighbors.get(i).getElement().getType().getScore());

                neighbors.get(i).collapseX = this.x;
                neighbors.get(i).collapseY = this.y;
                neighbors.get(i).isCollapse = true;
                collapsedCellList.add(neighbors.get(i));
                //neighbors.get(i).setElement(null);

			}
            //flop = false;

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

}
