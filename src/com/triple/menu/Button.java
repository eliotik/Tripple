package com.triple.menu;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.triple.game.configs.Config;
import com.triple.sprites.GlobalTextures;

public class Button extends GameObject {
	
	protected String name;
	private boolean clicked = false;
	private boolean hovered = false;
	protected BufferedImage statusBase;
	protected BufferedImage statusClicked;
	protected BufferedImage statusHovered;	
	
	public Button(String name, double x, double y, GlobalTextures textures) {
		super(x, y, textures);
		this.name = name;		
		statusBase = textures.getTextures(name, "base");
		statusClicked = textures.getTextures(name, "clicked");
		statusHovered = textures.getTextures(name, "hovered");		
	}
	
	public void render(Graphics g) {
		if (!clicked) {
			if (!hovered) {
				g.drawImage(statusBase, (int) x, (int) y, null);
			} else {
				g.drawImage(statusHovered, (int) x, (int) y, null);
			}
		} else {
			g.drawImage(statusClicked, (int) x, (int) y, null);
		}
	}
	
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
	
	public void setHovered(boolean hovered) {
		this.hovered = hovered;
	}

	public Rectangle getButtonBounds() {
		return new Rectangle((int) x, (int) y, Config.menuButton.width, Config.menuButton.height);
	}
}
