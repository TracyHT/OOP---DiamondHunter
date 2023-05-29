/**
 * Name: Group 08
   Member:
   1: Ngô Lê Thiên Ân ITITDK21030
   2: Nguyễn Đình Thắng ITITIU21309
   3: Huỳnh Thanh Thủy ITITIU21325
   4: Cao Hoàng Khôi Nguyên ITITDK21048
   Purpose:
		// Possibly redundant subclass of Entity.
		// An item to increase player's health.
 */

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
