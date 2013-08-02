package com.triple.sprites;

import java.awt.image.BufferedImage;


public class SpriteSheetButtons {

	public BufferedImage getSprite(int x, int y, int width, int height) {
		BufferedImage sprites = Tiles.getButtonsAsset();
		return sprites.getSubimage(x, y, width, height);
	}
	
	
}
