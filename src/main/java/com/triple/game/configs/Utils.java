package com.triple.game.configs;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

public class Utils {
	public static void print(String value) {
		System.out.println(value);		
	}
	
	public static void print(int value) {
		System.out.println(value);		
	}
	
	public static void print(char value) {
		System.out.println(value);		
	}
	
	public static void print(boolean value) {
		System.out.println(value);		
	}
	
	public static BufferedImage rotate(BufferedImage image, double angle) {
	    double sin = Math.abs(Math.sin(Math.toRadians(angle))), cos = Math.abs(Math.cos(Math.toRadians(angle)));
	    int w = image.getWidth(), h = image.getHeight();
	    int neww = (int)Math.floor(w*cos+h*sin), newh = (int)Math.floor(h*cos+w*sin);
	    GraphicsConfiguration gc = Utils.getDefaultConfiguration();
	    BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
	    Graphics2D g = result.createGraphics();
	    g.translate((neww-w)/2, (newh-h)/2);
	    g.rotate(Math.toRadians(angle), w/2, h/2);
	    g.drawRenderedImage(image, null);
	    g.dispose();
	    return result;
	    /*
	    public static BufferedImage tilt(BufferedImage image, Rotation rotation) {
			double sin = Math.abs(Math.sin(rotation.angle)), cos = Math.abs(Math.cos(rotation.angle));
			int w = image.getWidth(), h = image.getHeight();
			int neww = (int)Math.floor(w*cos+h*sin), newh = (int)Math.floor(h*cos+w*sin);
			GraphicsConfiguration gc = getDefaultConfiguration();
			BufferedImage rotated = gc.createCompatibleImage(neww, newh);
			Graphics2D g = rotated.createGraphics();
			g.translate((neww-w)/2, (newh-h)/2);
			g.rotate(rotation.angle, w/2, h/2);
			g.drawRenderedImage(image, null);
			g.dispose();
			return rotated;
		} 
	    */
	}
	
    public static GraphicsConfiguration getDefaultConfiguration() {
    	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	GraphicsDevice gd = ge.getDefaultScreenDevice();
    	return gd.getDefaultConfiguration();
    }	
}
