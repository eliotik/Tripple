package ua.triple.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ElementType {
    public static BufferedImage tiles;
    public static int[] id = {-1,-1};
    public static int cellSize = 80;

    public ElementType() {

    }

    public static void loadTiles()
    {
        try {
            ElementType.tiles = ImageIO.read(new File("assets/tiles.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
