package ua.triple.game;

import java.awt.*;

public class Game extends Canvas {
    public static Dimension size = new Dimension(800, 600);

    public Game(int height, int width) {
        Dimension d = new Dimension(height, width);
        setSize(d);
        setPreferredSize(d);
    }
    public Game() {
        setSize(size);
        setPreferredSize(size);
    }

}
