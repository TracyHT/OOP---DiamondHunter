/**
 * Name: Group 08
   Member:
   1: Ngô Lê Thiên Ân ITITDK21030
   2: Nguyễn Đình Thắng ITITIU21309
   3: Huỳnh Thanh Thủy ITITIU21325
   4: Cao Hoàng Khôi Nguyên ITITDK21048
   Purpose:
		// Possibly redundant subclass of Entity.
		// There are 3 types of items: Axe, key and weapon.
		// Upon collection, informs the Player
		// that the Player does indeed have the item.
 */
package com.neet.DiamondHunter.Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.neet.DiamondHunter.Manager.Content;
import com.neet.DiamondHunter.TileMap.TileMap;

public class Item extends Entity{
	
	private BufferedImage sprite;
	private int type;
	public static final int KEY = 0;
	public static final int WEAPON =2;
	public static final int AXE = 1;
	
	public Item(TileMap tm) {
		super(tm);
		type = -1;
		width = height = 16;
		cwidth = cheight = 12;
	}
	
	public void setType(int i) {
		type = i;
		if (type == WEAPON){
			sprite = Content.ITEMS[1][0];
		}
		if(type == KEY) {
			sprite = Content.ITEMS[1][2];
		}
		else if(type == AXE) {
			sprite = Content.ITEMS[1][1];
		}
	}
	
	public void collected(Player p) {
		if(type == WEAPON) {
			p.gotWeapon();
		}
		if(type == AXE) {
			p.gotAxe();
		}
		if (type == KEY) {
			p.gotKey();
		}
	}
	
	public void draw(Graphics2D g) {
		setMapPosition();
		g.drawImage(sprite, x + xmap - width / 2, y + ymap - height / 2, null);
	}
	
}
