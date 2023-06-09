/**
 * Name: Group 08
   Member:
   1: Ngô Lê Thiên Ân ITITDK21030
   2: Nguyễn Đình Thắng ITITIU21309
   3: Huỳnh Thanh Thủy ITITIU21325
   4: Cao Hoàng Khôi Nguyên ITITDK21048
   Purpose:
		// Loads and splits all sprites on start up.
		// The sprites can easily be accessed as they
		// are public and static.
 */
package com.neet.DiamondHunter.Manager;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Content {
	
	public static BufferedImage[][] MENUBG = load("/HUD/menuscreen.png", 160, 176);
	public static BufferedImage[][] BAR = load("/HUD/bar.png", 160, 16);
	public static BufferedImage[][] GUIDELINE = load("/HUD/guideline.png", 160, 176);
	public static BufferedImage[][] GUIDELINETITLE = load("/HUD/guidelinetitle.png", 137, 17);
	public static BufferedImage[][] CHOOSECHAR = load("/HUD/choosechar.png", 135, 27);
	public static BufferedImage[][] CharacterFrame = load("/HUD/Character.png", 160, 176);

	public static BufferedImage[][] PLAYER1 = load("/Sprites/playersprites.png", 16, 16);
	public static BufferedImage[][] PLAYER2 = load("/Sprites/playersprites2.png", 16, 16);
	public static BufferedImage[][] MONSTER = load("/Sprites/Monster.png", 16, 16);
	public static BufferedImage[][] BOSS = load("/Sprites/Boss.png", 40, 40);
	public static BufferedImage[][] DIAMOND = load("/Sprites/diamond.gif", 16, 16);
	public static BufferedImage[][] SPARKLE = load("/Sprites/sparkle.gif", 16, 16);
	public static BufferedImage[][] BULLET = load("/Sprites/bullet.png", 13, 14);
	public static BufferedImage[][] ITEMS = load("/Sprites/items.png", 16, 16);
	public static BufferedImage[][] HEARTS = load("/Sprites/Heart.png", 16, 16);
	public static BufferedImage[][] LIFEPOT = load("/Sprites/Lifepot.png", 9, 11);

	public static BufferedImage[][] GAMEOVERWIN = load("/HUD/gameoverwin.png", 160, 176);
	public static BufferedImage[][] GAMEOVERLOSE = load("/HUD/gameoverlose.png", 160, 176);
	
	
	public static BufferedImage[][] font = load("/HUD/font.gif", 8, 8);

	public static BufferedImage[][] load(String s, int w, int h) {
		BufferedImage[][] ret;
		try {
			BufferedImage spritesheet = ImageIO.read(Content.class.getResourceAsStream(s));
			int width = spritesheet.getWidth() / w;
			int height = spritesheet.getHeight() / h;
			ret = new BufferedImage[height][width];
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					ret[i][j] = spritesheet.getSubimage(j * w, i * h, w, h);
				}
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading graphics.");
			System.exit(0);
		}
		return null;
	}

	public static void drawString(Graphics2D g, String s, int x, int y) {
		s = s.toUpperCase();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == 47)
				c = 36; // slash
			if (c == 58)
				c = 37; // colon
			if (c == 32)
				c = 38; // space
			if (c >= 65 && c <= 90)
				c -= 65; // letters
			if (c >= 48 && c <= 57)
				c -= 22; // numbers
			int row = c / font[0].length;
			int col = c % font[0].length;
			g.drawImage(font[row][col], x + 8 * i, y, null);
		}
	}

}
