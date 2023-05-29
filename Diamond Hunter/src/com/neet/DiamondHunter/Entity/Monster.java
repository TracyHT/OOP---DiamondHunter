/**
 * Name: Group 08
   Member:
   1: Ngô Lê Thiên Ân ITITDK21030
   2: Nguyễn Đình Thắng ITITIU21309
   3: Huỳnh Thanh Thủy ITITIU21325
   4: Cao Hoàng Khôi Nguyên ITITDK21048
   Purpose:
		// Monster class
		// Control monster behavior
		// Define when the monster should be removed
 */

package com.neet.DiamondHunter.Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.ArrayList;

import com.neet.DiamondHunter.Manager.Content;
//import com.neet.DiamondHunter.Manager.Data;
import com.neet.DiamondHunter.TileMap.TileMap;
//import java.util.Random;

public class Monster extends Entity {
	
	BufferedImage[] sprites;
	public static final int SMALL = 0;
	public static final int BOSS = 1;
	private ArrayList<int[]> tileChanges;
	
	public Monster(TileMap tm) {
		
		super(tm);
		moveSpeed = 3;
		
		width = 16;
		height = 16;
		cwidth = 12;
		cheight = 12;
		health = 1;
		
		sprites = Content.MONSTER[1];
		animation.setFrames(sprites);
		animation.setDelay(4);

		tileChanges = new ArrayList<int[]>();
		
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
	
	public void addChange(int[] i) {
		tileChanges.add(i);
	}
	public ArrayList<int[]> getChanges() {
		return tileChanges;
	}

	public void setAction() {
		actionCounter++;

		if (actionCounter == 16){
			Random random = new Random();
			int i = random.nextInt(2);

			if (i == 0) setLeft();
			else if (i == 1) setRight();
	
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
