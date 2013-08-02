package com.triple.menu;

import com.triple.sprites.GlobalTextures;

public class GameObject {
	protected double x, y;
	protected GlobalTextures textures;
	public GameObject(double x, double y, GlobalTextures textures) {
		this.x = x;
		this.y = y;
		this.textures = textures;
	}
}
