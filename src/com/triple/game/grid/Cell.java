package com.triple.game.grid;


import java.awt.*;

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
			element.render(g, x, y, width, height, false, showBorder);
		} else if (showBorder) {
			Element.renderBorder(g, x, y, width, height, false);
		}
		
		if (temporaryElement != null)
			temporaryElement.renderContainer(g, 0, 0, width-6, height-6, 3, 2, false, 0);
		
			
		
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

}
