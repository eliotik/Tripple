package ua.triple.game.grid;

import java.awt.*;

import ua.triple.game.configs.Config;
import ua.triple.game.elements.Element;
import ua.triple.game.elements.ElementTypesCollection;

public class Grid {

    public Cell[][] cells = new Cell[8][8];

    public Grid() {
        for (int x = 0; x < cells.length; ++x) {
            for (int y = 0; y < cells[0].length; ++y) {
                 cells[x][y] = new Cell(new Rectangle(x * Config.cellSize, y * Config.cellSize, Config.cellSize, Config.cellSize), x, y);
          }
        }
        generateGrid();
    }

    public void generateGrid() {
        for (int x = 0; x < cells.length; ++x ) {
            for (int y = 0; y < cells[0].length; ++y) {
                cells[x][y].setElement(new Element(ElementTypesCollection.getRandomByType("base")));
            }
        }
    }

    public void tick() {

    }

    public void render(Graphics g) {
        for (int x = 0; x < cells.length; ++x ) {
            for (int y = 0; y < cells[0].length; ++y) {
                cells[x][y].render(g);
            }
        }
    }
}
