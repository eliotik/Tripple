package com.triple.game.configs;

public class Config {
	public static final int cellSize = 80;
	public static final int tileBorderWidth = 1;
	public static final int tileSize = Config.cellSize - Config.tileBorderWidth*2;
	public static final String xmlFilePath = "configs/";
	public static final String elementsFile = "elements.xml";
	public static final int[] bgCenter = {0, 2};
	public static final int[] bgOneCorner = {1, 2};
	public static final int[] focusBorder = {0, 1};
}
