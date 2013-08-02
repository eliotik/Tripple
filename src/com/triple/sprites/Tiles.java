package com.triple.sprites;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.triple.game.configs.Config;

public class Tiles {

    private static BufferedImage tilesAsset;
    private static BufferedImage screenBackground;
    private static BufferedImage mainTitleBackground;
    private static BufferedImage buttonsAsset;
	
    public static void loadTiles() throws IOException {
    	setScreenBackground(ImageIO.read(new File(Config.screenBackgroundFile)));
    	setTilesAsset(ImageIO.read(new File(Config.tilesFile)));
    }

    public static void loadMainScreen() throws IOException {
    	setMainTitleBackground(ImageIO.read(new File(Config.mainTitleFile)));
    	setButtonsAsset(ImageIO.read(new File(Config.buttonsAssetsFile)));
    }
    
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

	public static BufferedImage getMainTitleBackground() {
		return mainTitleBackground;
	}

	public static void setMainTitleBackground(BufferedImage mainTitleBackground) {
		Tiles.mainTitleBackground = mainTitleBackground;
	}

	public static BufferedImage getButtonsAsset() {
		return buttonsAsset;
	}

	public static void setButtonsAsset(BufferedImage buttonsAsset) {
		Tiles.buttonsAsset = buttonsAsset;
	}
}
