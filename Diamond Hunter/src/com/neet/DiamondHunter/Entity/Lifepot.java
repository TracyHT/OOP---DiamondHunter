// Diamond class.
// May contain a list of tileChanges.
// These tileChanges are used to modify
// the tile map upon collection.

package com.neet.DiamondHunter.Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.neet.DiamondHunter.Manager.Content;
import com.neet.DiamondHunter.TileMap.TileMap;

public class Lifepot extends Entity {
	
	BufferedImage[] sprites;
	
	public Lifepot(TileMap tm) {
		
		super(tm);
		
		width = 9;
		height = 11;
		cwidth = 16;
		cheight = 16;
		
		sprites = Content.LIFEPOT[0];
		animation.setFrames(sprites);
		animation.setDelay(10);
		
	}
	
	public void update() {
		animation.update();
	}
	
	public void draw(Graphics2D g) {
		super.draw(g);
	}
	
}
