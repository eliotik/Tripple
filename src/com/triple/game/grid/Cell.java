package com.triple.game.grid;


import java.awt.*;

import com.triple.game.elements.*;

public class Cell extends Rectangle{

	private int x;
    private int y;

    private static final long serialVersionUID = 1L;
    private Element element;

    public Cell(Rectangle size, int x, int y) {
    	setBounds(size);
    	this.x = x;
    	this.y = y;
	}    
    
    public Element getElement() {
        return element;
    }

	public void render(Graphics g) {
		if (element == null) return;
		element.render(g, x, y, width, height, false);
    }

    public void setElement(Element el) {
        element = el;
    }
}
