package com.triple.handlers;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.triple.game.Game;
import com.triple.menu.Button;
import com.triple.menu.Menu;

public class MouseInput extends MouseAdapter implements MouseMotionListener {
	Game game;
	Button hoveredButton;
	
	public MouseInput(Game game) {
		this.game = game;
	}
	
	public void mouseClicked(MouseEvent e) {
		Menu menu = game.getMenu();
    	int mouse = e.getButton();
    	Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);
    	switch(mouse) {
    	case MouseEvent.BUTTON1:
    		if (r.intersects(menu.getButton("play").getButtonBounds()))
    			menu.getButton("play").setClicked(true);
    			Game.setGameState(1);
    		break;
    	}
	}
	
	private void setHoveredButton(Button button) {
		hoveredButton = button;
		hoveredButton.setHovered(true);
	}
	
	private void checkHoveredButton(Rectangle r, Menu menu) {
		if (r.intersects(menu.getButton("play").getButtonBounds()))
		{
			setHoveredButton(menu.getButton("play"));
		}
	}
	
    public void mouseMoved(MouseEvent e) {
    	Menu menu = game.getMenu();
    	Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);
    	if (hoveredButton != null) {
    		if (!r.intersects(hoveredButton.getButtonBounds())) {
    			hoveredButton.setHovered(false);
    			checkHoveredButton(r, menu);
    		} else {
    			hoveredButton.setHovered(true);
    		}
    	} else {
    		checkHoveredButton(r, menu);
    	}
		
    		
    }

	public void mouseDragged(MouseEvent e) {
		
	}	
	
}
