package com.triple.sprites;

import java.awt.image.BufferedImage;

import com.triple.game.Game;
import com.triple.game.configs.Config;

public class GlobalTextures {
	
	private SpriteSheetButtons buttons;
	
	public GlobalTextures(Game game) {
		buttons = new SpriteSheetButtons();
	}

	public BufferedImage getTextures(String buttonId, String buttonStatus) {
		switch(buttonId) {
			case "play":
				switch(buttonStatus) {
					case "base": return buttons.getSprite(0, 0, Config.menuButton.width, Config.menuButton.height);
					case "hovered": return buttons.getSprite(81, 0, Config.menuButton.width, Config.menuButton.height);
					case "clicked": return buttons.getSprite(162, 0, Config.menuButton.width, Config.menuButton.height);
				}
			break;
		}
		return buttons.getSprite(0, 0, 1, 1);
	}
}
