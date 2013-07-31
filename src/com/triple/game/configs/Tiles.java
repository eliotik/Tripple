package com.triple.game.configs;

//import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Tiles {

    private static BufferedImage tilesAsset;
    private static BufferedImage screenBackground;
	
    public static void loadTiles()
    {
        try {
        	setScreenBackground(ImageIO.read(new File("assets/screen_background.png")));
        	setTilesAsset(ImageIO.read(new File("assets/tiles2.png")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public static Image getTilesAssets() {
//    	return getTilesAsset();
//    }

	public static BufferedImage getScreenBackground() {
		return screenBackground;
	}

	public static void setScreenBackground(BufferedImage screenBackground) {
		Tiles.screenBackground = screenBackground;
	}

	public static BufferedImage getTilesAsset() {
		return tilesAsset;
	}

	public static void setTilesAsset(BufferedImage tilesAsset) {
		Tiles.tilesAsset = tilesAsset;
	}
}
