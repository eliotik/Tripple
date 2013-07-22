package ua.triple.game;

public class Grid {

    public Cell[][] cells = new Cell[9][9];

    public Grid() {
        for (int x = 0; x < cells.length; ++x) {
            for (int y = 0; y < cells[0].length; ++y) {
                 cells[x][y] = new Cell();
            }
        }

    }

    public void tick() {

    }

    private void render() {

    }
}
