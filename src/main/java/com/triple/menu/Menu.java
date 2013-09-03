package com.triple.menu;

import com.triple.game.Game;
import com.triple.sprites.GlobalTextures;
import com.triple.sprites.Tiles;

import java.awt.*;
import java.io.IOException;

public class Menu {

	private GlobalTextures textures;
	private Button playButton;
	private Button multiPlayButton;
	private Button mpStartButton;
	private Button mpJoinButton;
	private Button mpBackButton;
	
	public Menu(Game game) {
		try {
			Tiles.loadMainScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
		textures = new GlobalTextures(game);
		playButton = new Button("play", 220, 180, textures);
		multiPlayButton = new Button("multi", 220, 210, textures);
		mpStartButton = new Button("mpstart", 170, 180, textures);
		mpJoinButton = new Button("mpjoin", 280, 180, textures);
		mpBackButton = new Button("mpback", 220, 220, textures);
	}

    private void drawTitleScreenBackground(Graphics g) {
    	g.drawImage(Tiles.getMainTitleBackground(), 
    			-140, 
    			-100, 
    			null);    	
    }	
	
	public void render(Graphics g) {
		drawTitleScreenBackground(g);
		playButton.render(g);
		multiPlayButton.render(g);
	}

	public Button getButton(String buttonName) {
		switch(buttonName) {
			case "play": return playButton;
			case "multi": return multiPlayButton;
			case "mpstart": return mpStartButton;
			case "mpjoin": return mpJoinButton;
			case "mpback": return mpBackButton;
			default: return null;
		}
	}

	public void mprender(Graphics g) {
		drawTitleScreenBackground(g);
		mpStartButton.render(g);
		mpJoinButton.render(g);
		mpBackButton.render(g);
	}
}
