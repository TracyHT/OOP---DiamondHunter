/**
 * Name: Group 08
   Member:
   1: Ngô Lê Thiên Ân ITITDK21030
   2: Nguyễn Đình Thắng ITITIU21309
   3: Huỳnh Thanh Thủy ITITIU21325
   4: Cao Hoàng Khôi Nguyên ITITDK21048
   Purpose:
		// Boss class
		// Control boss behavior
 */

package com.neet.DiamondHunter.Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.neet.DiamondHunter.Manager.Content;
import com.neet.DiamondHunter.TileMap.TileMap;

public class Boss extends Entity {
	
	BufferedImage[] sprites;
	public Boss(TileMap tm) {
		
		super(tm);
		moveSpeed = 3;
		health = 15;
		
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
	public void setAction() {
		actionCounter++;

		if (actionCounter == 30){
			Random random = new Random();
			int i = random.nextInt(4);

			if (i == 0) setUp();
			else if (i == 1) setDown();
			else if (i == 2) setLeft();
			else if (i == 3) setRight();
	
			actionCounter = 0;
		}
	}
	public boolean shouldRemove() {
		if (getHealth() <= 0) return true;
		 return false;
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
