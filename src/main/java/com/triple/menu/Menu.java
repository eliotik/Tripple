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
	
	public Menu(Game game) {
		try {
			Tiles.loadMainScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
		textures = new GlobalTextures(game);
		playButton = new Button("play", 220, 180, textures);
		multiPlayButton = new Button("multi", 220, 210, textures);
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
        if (buttonName.equals("play")) {
            return playButton;
        }else if (buttonName.equals("multi")) {
        	return multiPlayButton;
        }
		return new Button("BAD", 0, 0, textures);
	}
}
