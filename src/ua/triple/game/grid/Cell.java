package ua.triple.game.grid;


import java.awt.*;

import ua.triple.game.configs.Config;
import ua.triple.game.configs.Tiles;
import ua.triple.game.configs.Utils;
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
		int tile_x = element.getType().getTile_x(),
			tile_y = element.getType().getTile_y(),
			cell_size = Config.cellSize,
			tile_size = Config.tileSize;
		Utils.print("{"+x+","+y+"}{"+tile_x+","+tile_y+"}("+(x * cell_size)+","+(y * cell_size)+")("+(x * cell_size + cell_size)+","+(y * cell_size + cell_size)+")["+(tile_x * tile_size)+","+(tile_y * tile_size)+"]["+(tile_x * tile_size + tile_size)+","+(tile_y * tile_size + tile_size)+"]");
		
        g.drawImage(Tiles.getTilesAssets(), 
        			x * cell_size, 
        			y * cell_size, 
        			x * cell_size + cell_size, 
        			y * cell_size + cell_size,
        			tile_x * tile_size, 
        			tile_y * tile_size, 
        			tile_x * tile_size + tile_size, 
        			tile_y * tile_size + tile_size,
        			null);
    }

    public void setElement(Element el) {
        element = el;
    }
}
