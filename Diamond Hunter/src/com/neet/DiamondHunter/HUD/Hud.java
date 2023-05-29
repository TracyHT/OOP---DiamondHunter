/**
 * Name: Group 08
   Member:
   1: Ngô Lê Thiên Ân ITITDK21030
   2: Nguyễn Đình Thắng ITITIU21309
   3: Huỳnh Thanh Thủy ITITIU21325
   4: Cao Hoàng Khôi Nguyên ITITDK21048
   Purpose:
		// Contains a reference to the Player.
		// Draws all relevant information at the
		// bottom of the screen.
 */

package com.neet.DiamondHunter.HUD;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import com.neet.DiamondHunter.Entity.Diamond;
import com.neet.DiamondHunter.Entity.Player;
import com.neet.DiamondHunter.Main.GamePanel;
import com.neet.DiamondHunter.Manager.Content;
import com.neet.DiamondHunter.Manager.HealthControl;

public class Hud {
	
	private int yoffset;
	
	private BufferedImage bar;
	private BufferedImage diamond;
	private BufferedImage weapon;
	private BufferedImage key;
	private BufferedImage axe;
	
	private Player player;
	
	private int numDiamonds;
	
	private Font font;
	private Color textColor; 
	
	public Hud(Player p, ArrayList<Diamond> d) {
		
		player = p;
		numDiamonds = d.size();
		yoffset = GamePanel.HEIGHT;
		
		bar = Content.BAR[0][0];
		diamond = Content.DIAMOND[0][0];
		key = Content.ITEMS[0][2];
		axe = Content.ITEMS[0][1];
		weapon = Content.ITEMS[0][0];
		
		font = new Font("Arial", Font.PLAIN, 10);
		textColor = new Color(47, 64, 126);
		
	}
	
	public void draw(Graphics2D g) {
		
		// draw hud
		g.drawImage(bar, 0, yoffset, null);
	
		// draw diamond bar
		g.setColor(textColor);
		g.fillRect(8, yoffset + 6, (int)(28.0 * player.numDiamonds() / numDiamonds), 4);
	
		// draw diamond amount
		g.setColor(textColor);
		g.setFont(font);
		String s = player.numDiamonds() + "/" + numDiamonds;
		Content.drawString(g, s, 40, yoffset + 3);
		if(player.numDiamonds() >= 10) g.drawImage(diamond, 80, yoffset, null);
		else g.drawImage(diamond, 72, yoffset, null);
	
		// draw items
		if(player.hasKey()) g.drawImage(key, 100, yoffset, null);
		if(player.hasWeapon()) g.drawImage(weapon, 100, yoffset, null);
		if(player.hasAxe()) g.drawImage(axe, 112, yoffset, null);
	
		// draw time
		int minutes = (int) (player.getTicks() / 1800);
		int seconds = (int) ((player.getTicks() / 30) % 60);
		if(minutes < 10) {
			if(seconds < 10) Content.drawString(g, "0" + minutes + ":0" + seconds, 114, 3);
			else Content.drawString(g, "0" + minutes + ":" + seconds, 114, 3);
		}
		else {
			if(seconds < 10) Content.drawString(g, minutes + ":0" + seconds, 85, 3);
			else Content.drawString(g, minutes + ":" + seconds, 85, 3);
		}
		
		//draw healthbar
		int xoffset = 10;
        int yoffset = 10;
        for (int i = 0; i < HealthControl.getHealth(); i++) {
            g.drawImage(Content.HEARTS[0][0], xoffset + i * 20, yoffset, null);
        }
	}
}
