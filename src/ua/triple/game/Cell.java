package ua.triple.game;


import java.awt.*;

public class Cell extends Rectangle{

    public int[] id = {-1, -1};

    private static final long serialVersionUID = 1L;

    public Element getElement() {
        return element;
    }

    private Element element;

    public Cell(Rectangle size, int[] id) {
        setBounds(size);
        this.id = id;
    }

    public void render(Graphics g) {
        g.drawImage(ElementType.tiles, x, y, x + width, y + height, id[0] * ElementType.cellSize, id[1] * ElementType.cellSize, id[0] * ElementType.cellSize + ElementType.cellSize, id[1] * ElementType.cellSize + ElementType.cellSize, null);
    }

    public void setElement(Element el) {
        element = el;
    }
}
