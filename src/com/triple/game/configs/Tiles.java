package com.triple.game.configs;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Tiles {

    private static BufferedImage tilesAsset;
	
    public static void loadTiles()
    {
        try {
        	tilesAsset = ImageIO.read(new File("assets/tiles.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

	public static Image getTilesAssets() {
		return tilesAsset;
	}    
    
}
