package ua.triple.game.grid;


import java.awt.*;

import ua.triple.game.configs.Config;
import ua.triple.game.configs.Tiles;
import ua.triple.game.elements.*;

public class Cell extends Rectangle{

	private int x;
    private int y;

    private static final long serialVersionUID = 1L;

    public Element getElement() {
        return element;
    }

    private Element element;
    
    public Cell(Rectangle size, int x, int y) {
    	setBounds(size);
    	this.x = x;
    	this.y = y;
	}

	public void render(Graphics g) {
        g.drawImage(Tiles.getTilesAssets(), x, y, x + width, y + height, x * Config.cellSize, y * Config.cellSize, x * Config.cellSize + Config.cellSize, y * Config.cellSize + Config.cellSize, null);
    }

    public void setElement(Element el) {
        element = el;
    }
}
