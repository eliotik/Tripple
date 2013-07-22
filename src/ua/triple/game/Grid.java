package ua.triple.game;

import java.awt.*;

public class Grid {

    public Cell[][] cells = new Cell[8][8];

    public Grid() {
        for (int x = 0; x < cells.length; ++x) {
            for (int y = 0; y < cells[0].length; ++y) {
                 cells[x][y] = new Cell(new Rectangle(x * ElementType.cellSize, y * ElementType.cellSize, ElementType.cellSize, ElementType.cellSize), ElementType.id);
            }
        }
        generateGrid();
    }

    public void generateGrid() {
        for (int x = 0; x < cells.length; ++x ) {
            for (int y = 0; y < cells[0].length; ++y) {
                cells[x][y].setElement(new Element());
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
