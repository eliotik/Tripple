package com.triple.sprites;

import com.triple.game.Game;
import com.triple.game.configs.Config;

import java.awt.image.BufferedImage;

public class GlobalTextures {
	
	private SpriteSheetButtons buttons;
	
	public GlobalTextures(Game game) {
		buttons = new SpriteSheetButtons();
	}

	public BufferedImage getTextures(String buttonId, String buttonStatus) {

		switch(buttonId) {
			case "play":  
				if (buttonStatus.equals("base")) {
		            return buttons.getSprite(0, 0, Config.menuButton.width, Config.menuButton.height);
		        } else if (buttonStatus.equals("hovered")) {
		            return buttons.getSprite(81, 0, Config.menuButton.width, Config.menuButton.height);
		        } else if (buttonStatus.equals("clicked")) {
		            return buttons.getSprite(162, 0, Config.menuButton.width, Config.menuButton.height);
		        }
			case "multi":
				if (buttonStatus.equals("base")) {
	                return buttons.getSprite(0, 22, Config.menuButton.width, Config.menuButton.height);
	            } else if (buttonStatus.equals("hovered")) {
	                return buttons.getSprite(81, 22, Config.menuButton.width, Config.menuButton.height);
	            } else if (buttonStatus.equals("clicked")) {
	                return buttons.getSprite(162, 22, Config.menuButton.width, Config.menuButton.height);
	            }
			case "mpstart":
				if (buttonStatus.equals("base")) {
					return buttons.getSprite(0, 44, Config.menuButton.width, Config.menuButton.height);
				} else if (buttonStatus.equals("hovered")) {
					return buttons.getSprite(81, 44, Config.menuButton.width, Config.menuButton.height);
				} else if (buttonStatus.equals("clicked")) {
					return buttons.getSprite(162, 44, Config.menuButton.width, Config.menuButton.height);
				}
			case "mpjoin":
				if (buttonStatus.equals("base")) {
					return buttons.getSprite(0, 66, Config.menuButton.width, Config.menuButton.height);
				} else if (buttonStatus.equals("hovered")) {
					return buttons.getSprite(81, 66, Config.menuButton.width, Config.menuButton.height);
				} else if (buttonStatus.equals("clicked")) {
					return buttons.getSprite(162, 66, Config.menuButton.width, Config.menuButton.height);
				}
			case "mpback":
				if (buttonStatus.equals("base")) {
					return buttons.getSprite(0, 88, Config.menuButton.width, Config.menuButton.height);
				} else if (buttonStatus.equals("hovered")) {
					return buttons.getSprite(81, 88, Config.menuButton.width, Config.menuButton.height);
				} else if (buttonStatus.equals("clicked")) {
					return buttons.getSprite(162, 88, Config.menuButton.width, Config.menuButton.height);
				}
			default: return buttons.getSprite(0, 0, 1, 1);
		}
	}
}
