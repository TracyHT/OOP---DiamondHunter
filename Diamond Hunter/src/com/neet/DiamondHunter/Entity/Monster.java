// Diamond class.
// May contain a list of tileChanges.
// These tileChanges are used to modify
// the tile map upon collection.

package com.neet.DiamondHunter.Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import com.neet.DiamondHunter.Manager.Content;
import com.neet.DiamondHunter.TileMap.TileMap;

public class Monster extends Entity {
	
	BufferedImage[] sprites;
	private int type;
	public static final int SMALL = 0;
	public static final int BOSS = 1;
	
	public Monster(TileMap tm) {
		
		super(tm);
		
	}
	public void setType(int i) {
		type = i;
		if(type == SMALL) {
			width = 16;
			height = 16;
			cwidth = 12;
			cheight = 12;
			sprites = Content.MONSTER[1];
			animation.setFrames(sprites);
			animation.setDelay(4);
		}
		else if(type == BOSS) {
			width = 40;
			height = 40;
			cwidth = 36;
			cheight = 36;
			sprites = Content.BOSS[0];
			animation.setFrames(sprites);
			animation.setDelay(4);
		}
	}
	
	public void update() {
		animation.update();
	}
	
	public void draw(Graphics2D g) {
		super.draw(g);
	}
	
}
