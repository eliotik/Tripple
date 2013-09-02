package com.triple.game.player;

import java.awt.Graphics;

import com.triple.game.configs.Config;
import com.triple.game.elements.Element;
import com.triple.game.grid.Grid;

public class PlayerHand {

	private Element element;
	
	public void render(Graphics g, int y) {
		element.render(g, Grid.cellsAmount+6, y+1, Config.cellSizeX/2+2, Config.cellSizeY, true, false);
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public Element getElement() {
		return element;
	}

}
