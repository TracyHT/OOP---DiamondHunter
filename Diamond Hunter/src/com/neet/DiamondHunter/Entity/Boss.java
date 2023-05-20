// Diamond class.
// May contain a list of tileChanges.
// These tileChanges are used to modify
// the tile map upon collection.

package com.neet.DiamondHunter.Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.neet.DiamondHunter.Manager.Content;
//import com.neet.DiamondHunter.Manager.Data;
import com.neet.DiamondHunter.TileMap.TileMap;
//import java.util.Random;

public class Boss extends Entity {
	
	BufferedImage[] sprites;
	private int type;
	public static final int SMALL = 0;
	public static final int BOSS = 1;
	
	public Boss(TileMap tm) {
		
		super(tm);
		moveSpeed = 5;
		
        width = 40;
        height = 40;
        cwidth = 36;
        cheight = 36;
        sprites = Content.BOSS[0];
        animation.setFrames(sprites);
        animation.setDelay(4);
		
	}
	public void setDown() {
		super.setDown();
	}
	public void setLeft() {
		super.setLeft();
	}
	public void setRight() {
		super.setRight();
	}
	public void setUp() {
		super.setUp();
	}

	public void move(int direction){
		super.setPosition(super.getx() + direction, super.gety());
	}
	public int setAction() {
		actionCounter++;

		if (actionCounter == 120){
			Random random = new Random();
			int i = random.nextInt(2);

			if (i == 0){
				setLeft();
				return(-moveSpeed);
			}
			else if (i == 1){
				setRight();
				return (moveSpeed);
			}
			actionCounter = 0;
		}
		return 0;
	}
	
	public void update() {
		animation.update();
		setAction();
		super.update();
	}
	
	public void draw(Graphics2D g) {
		super.draw(g);
	}
	
}
