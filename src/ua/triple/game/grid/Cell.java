package ua.triple.game.grid;


import java.awt.*;

import ua.triple.game.Game;
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
		int tile_x = element.getType().getTile_x(),
			tile_y = element.getType().getTile_y(),
			cell_size = Config.cellSize,
			tile_size = Config.tileSize,
			tile_border_width = Config.tileBorderWidth,
			tile_border_width_pixelized = tile_border_width * Game.pixelSize,
			x_width = x * cell_size,
			y_width = y * cell_size,
			tile_x_width = tile_x * tile_size,
			tile_y_width = tile_y * tile_size,
			tile_x_border_pixelized = tile_x * tile_border_width_pixelized,
			tile_y_border_pixelized = tile_y * tile_border_width_pixelized;
		//Utils.print("{"+x+","+y+"}{"+tile_x+","+tile_y+"}("+(x * cell_size)+","+(y * cell_size)+")("+(x * cell_size + cell_size)+","+(y * cell_size + cell_size)+")["+(tile_x * tile_size)+","+(tile_y * tile_size)+"]["+(tile_x * tile_size + tile_size)+","+(tile_y * tile_size + tile_size)+"]");
//		System.out.println("------------------");
//		System.out.println("("+x+","+y+")-("+tile_x+","+tile_y+")");
//		System.out.println("("+(x * cell_size)+","+(y * cell_size)+")-("+(x * cell_size + cell_size)+","+(y * cell_size + cell_size)+")");
//		System.out.println("("+(tile_x * tile_size + tile_border_width)+","+(tile_y * tile_size + tile_border_width)+")-("+(tile_x * tile_size + tile_size - tile_border_width)+","+(tile_y * tile_size + tile_size - tile_border_width)+")");
//		System.out.println("("+(tile_x * tile_size)+","+(tile_y * tile_size)+")-("+(tile_x * tile_size + tile_size)+","+(tile_y * tile_size + tile_size)+")");
        g.drawImage(Tiles.getTilesAssets(), 
        			x_width, 
        			y_width, 
        			x_width + width, 
        			y_width + height,
        			tile_x_width + tile_x_border_pixelized + tile_border_width,
        			tile_y_width + tile_y_border_pixelized + tile_border_width_pixelized,
        			tile_x_width + tile_x_border_pixelized + tile_size + tile_border_width,
        			tile_y_width + tile_y_border_pixelized + tile_size + tile_border_width,
        			null);
    }

    public void setElement(Element el) {
        element = el;
    }
}
