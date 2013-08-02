package com.triple.menu;

import com.triple.game.Game;
import com.triple.sprites.GlobalTextures;
import com.triple.sprites.Tiles;

import java.awt.*;
import java.io.IOException;

public class Menu {

	private GlobalTextures textures;
	private Button playButton;
	
	public Menu(Game game) {
		try {
			Tiles.loadMainScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
		textures = new GlobalTextures(game);
		playButton = new Button("play", 350, 280, textures);
	}

    private void drawTitleScreenBackground(Graphics g) {
    	g.drawImage(Tiles.getMainTitleBackground(), 
    			0, 
    			0, 
    			null);    	
    }	
	
	public void render(Graphics g) {
		drawTitleScreenBackground(g);
		playButton.render(g);
	}

	public Button getButton(String buttonName) {
//		switch(buttonName) {
//			case equals("play"): return playButton;
//		}
        if (buttonName.equals("play")){
            return playButton;
        }
		return new Button("BAD", 0, 0, textures);
	}
}
