package com.triple.game.configs;

import java.awt.Dimension;
import java.awt.Rectangle;

public class Config {
	public static final int cellSize = 80;
	public static final int tileBorderWidth = 1;
	public static final int tileSize = Config.cellSize - Config.tileBorderWidth*2;
	public static final String xmlFilePath = "configs/";
	public static final String elementsFile = "elements.xml";
	public static final int collapseStepSize = 1;

	public static final int[] focusBorder = {0, 1};
	public static final int[] bgCenter = {0, 0};
	public static final int[] bgCorner_e = {2, 3};
	public static final int[] bgSide_f = {1, 3};
	public static final int[] bgSide_d = {2, 2};
	public static final int[] bgSide_d_round = {7, 1};
	public static final int[] bgTwoSides = {1, 2};
	public static final int[] bgLine = {2, 2};
	public static final int[] bgFullRound = {1, 2};
	
	public static final Dimension screenSize = new Dimension(800, 640);
	public static final String gameName = "Triple town";
	public static final int pixelSize = 1;
	public static final String screenBackgroundFile = "assets/screen_background.png";
	public static final String tilesFile = "assets/tiles2.png";
	public static final String mainTitleFile = "assets/main_title.png";
	public static final String buttonsAssetsFile = "assets/buttons.png";
	public static final String backgroundsAssetsFile = "assets/tiles_backgrounds.png";
	public static final Rectangle menuButton = new Rectangle(0, 0, 82, 23);
}
