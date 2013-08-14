package com.triple.game.elements;

import java.awt.Graphics;

public interface IElement {
	
	public ElementType getType();

	public void setType(ElementType type);

	public void render(Graphics g, int x, int y, int width, int height, boolean animate, boolean drawBackground);

	public void render(Graphics g, int x, int y, int width, int height, boolean animate, boolean showBorder, boolean drawBackground);
	
	//public void renderBorder(Graphics g, int x, int y, int width, int height, boolean animate);	
	
	public void renderCollapsing(Graphics g, int x, int y, int width, int height, int stepCollapsionX, int stepCollapsionY);
	
	public void renderContainer(Graphics g, int x, int y, int width, int height, int offsetX, int offsetY, boolean animate, int tickModifier);
	
}
