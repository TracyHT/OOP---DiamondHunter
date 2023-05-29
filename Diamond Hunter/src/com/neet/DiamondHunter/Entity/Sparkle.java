/**
 * Name: Group 08
   Member:
   1: Ngô Lê Thiên Ân ITITDK21030
   2: Nguyễn Đình Thắng ITITIU21309
   3: Huỳnh Thanh Thủy ITITIU21325
   4: Cao Hoàng Khôi Nguyên ITITDK21048
   Purpose:
		//sparkle class
		// Simple class that plays animation
		// once and is removed.
 */
package com.neet.DiamondHunter.Entity;

import java.awt.Graphics2D;

import com.neet.DiamondHunter.Manager.Content;
import com.neet.DiamondHunter.TileMap.TileMap;

public class Sparkle extends Entity {
	
	private boolean remove;
	
	public Sparkle(TileMap tm) {
		super(tm);
		animation.setFrames(Content.SPARKLE[0]);
		animation.setDelay(5);
		width = height = 16;
	}
	
	public boolean shouldRemove() {
		return remove;
	}
	
	public void update() {
		animation.update();
		if(animation.hasPlayedOnce()) remove = true;
	}
	
	public void draw(Graphics2D g) {
		super.draw(g);
	}
	
}
