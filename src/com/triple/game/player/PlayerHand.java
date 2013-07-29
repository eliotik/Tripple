package com.triple.game.player;

import java.awt.Graphics;

import com.triple.game.configs.Config;
import com.triple.game.elements.Element;
import com.triple.game.grid.Grid;

public class PlayerHand {

	private Element element;
	
	public void render(Graphics g) {
		element.render(g, (Grid.cellsAmount + 1), 1, Config.cellSize, Config.cellSize, true);
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public Element getElement() {
		return element;
	}

}
