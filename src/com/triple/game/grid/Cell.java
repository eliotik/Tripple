package com.triple.game.grid;


import java.awt.*;
import java.util.ArrayList;
import com.triple.game.Game;
import com.triple.game.elements.*;

public class Cell extends Rectangle{

	private int x;
    private int y;

    private static final long serialVersionUID = 1L;
    private Element element;
    private Element temporaryElement;
    private boolean showBorder = false;

    public Cell(Rectangle size, int x, int y) {
    	setBounds(size);
    	this.x = x;
    	this.y = y;
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
		
//		HashMap<String, Cell> hm = new HashMap<String, Cell>();

		
//		Cell cell_a = Game.grid.getCell(x-1, y-1),
//			cell_b = Game.grid.getCell(x, y-1),
//			cell_c = Game.grid.getCell(x+1, y-1),
//			
//			cell_d = Game.grid.getCell(x+1, y),
//			cell_e = Game.grid.getCell(x+1, y+1),
//			cell_f = Game.grid.getCell(x, y+1),
//			
//			cell_g = Game.grid.getCell(x-1, y+1),
//			cell_h = Game.grid.getCell(x-1, y);
//		
//		if (cell_a != null && cell_a.getElement() != null && cell_a.getElement().getType().getId() == element.getType().getId())
//			hm.put(element.getType().getId(), cell_a);
//		if (cell_b != null && cell_b.getElement() != null && cell_b.getElement().getType().getId() == element.getType().getId())
//			hm.put(element.getType().getId(), cell_b);
//		if (cell_c != null && cell_c.getElement() != null && cell_c.getElement().getType().getId() == element.getType().getId())
//			hm.put(element.getType().getId(), cell_c);
//		if (cell_d != null && cell_d.getElement() != null && cell_d.getElement().getType().getId() == element.getType().getId())
//			hm.put(element.getType().getId(), cell_d);
//		if (cell_e != null && cell_e.getElement() != null && cell_e.getElement().getType().getId() == element.getType().getId())
//			hm.put(element.getType().getId(), cell_e);
//		if (cell_f != null && cell_f.getElement() != null && cell_f.getElement().getType().getId() == element.getType().getId())
//			hm.put(element.getType().getId(), cell_f);
//		if (cell_g != null && cell_g.getElement() != null && cell_g.getElement().getType().getId() == element.getType().getId())
//			hm.put(element.getType().getId(), cell_g);
//		if (cell_h != null && cell_h.getElement() != null && cell_h.getElement().getType().getId() == element.getType().getId())
//			hm.put(element.getType().getId(), cell_h);
		Cell cell_a, cell_b; 
		for (int i = 0, l = 4; i < l; ++i) {
			switch(i) {
				case 1:
					cell_a = Game.grid.getCell(x+1, y-1);
					cell_b = Game.grid.getCell(x+1, y);
				break;
				case 2:
					cell_a = Game.grid.getCell(x+1, y+1);
					cell_b = Game.grid.getCell(x, y+1);
				break;
				case 3:
					cell_a = Game.grid.getCell(x-1, y+1);
					cell_b = Game.grid.getCell(x-1, y);
				break;
				default:
					cell_a = Game.grid.getCell(x-1, y-1);
					cell_b = Game.grid.getCell(x, y-1);
				break;
			}
			if (cell_a != null && cell_a.getElement() != null && cell_a.getElement().getType().getSubspecies().equals(element.getType().getSubspecies()))
				neighbors.add(cell_a);
			if (cell_b != null && cell_b.getElement() != null && cell_b.getElement().getType().getSubspecies().equals(element.getType().getSubspecies()))
				neighbors.add(cell_b);			
		}
		cell_a = cell_b = null;
		
		System.out.println(element.getType().getId()+", neighbors: "+neighbors.size());
		Game.isJoinning = false;
	}

}
