/**
 * Name: Group 08
   Member:
   1: Ngô Lê Thiên Ân ITITDK21030
   2: Nguyễn Đình Thắng ITITIU21309
   3: Huỳnh Thanh Thủy ITITIU21325
   4: Cao Hoàng Khôi Nguyên ITITDK21048
   Purpose:
		// The main playing GameState no 3.
		// Contains everything you need for gameplay:
		// Player, TileMap, Diamonds, etc.
		// Updates and draws all game objects.
 */

package com.neet.DiamondHunter.GameState;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.neet.DiamondHunter.Entity.Diamond;
import com.neet.DiamondHunter.Entity.Item;
import com.neet.DiamondHunter.Entity.Lifepot;
import com.neet.DiamondHunter.Entity.Player;
import com.neet.DiamondHunter.Entity.Sparkle;
import com.neet.DiamondHunter.Entity.Monster;
import com.neet.DiamondHunter.Entity.Boss;
import com.neet.DiamondHunter.Entity.Bullet;
import com.neet.DiamondHunter.HUD.Hud;
import com.neet.DiamondHunter.Main.GamePanel;
import com.neet.DiamondHunter.Manager.Data;
import com.neet.DiamondHunter.Manager.GameStateManager;
import com.neet.DiamondHunter.Manager.HealthControl;
import com.neet.DiamondHunter.Manager.JukeBox;
import com.neet.DiamondHunter.Manager.Keys;
import com.neet.DiamondHunter.TileMap.TileMap;
import com.neet.DiamondHunter.Entity.Bullet;

public class Round3 extends GameState {
	
	// player
	private Player player;
	
	// tilemap
	private TileMap tileMap;
	
	// diamonds
	private ArrayList<Diamond> diamonds;
	
	// items
	private ArrayList<Item> items;
	
	// sparkles
	private ArrayList<Sparkle> sparkles;

	//bullets
	private ArrayList<Bullet> bullets;

	// monster
	private ArrayList<Monster> monster;

	//boss
	private ArrayList<Boss> boss;

	//lifepot
	private ArrayList<Lifepot> lifepots;

	//counter
	int timeCounter;

	// camera position
	private int xsector;
	private int ysector;
	private int sectorSize; 
	
	// hud
	private Hud hud;
	
	// events
	private boolean blockInput;
	private boolean eventStart;
	private boolean eventFinish;
	private int eventTick;
	
	// transition box
	private ArrayList<Rectangle> boxes;
	
	public Round3(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		
		// create lists
		diamonds = new ArrayList<Diamond>();
		sparkles = new ArrayList<Sparkle>();
		items = new ArrayList<Item>();
		monster = new ArrayList<Monster>();
		boss = new ArrayList<Boss>();
		bullets = new ArrayList<Bullet>();
		lifepots = new ArrayList<Lifepot>();

		// load map
		tileMap = new TileMap(16);
		tileMap.loadTiles("/Tilesets/testtileset.png");
		tileMap.loadMap("/Maps/map3.map");
		
		// create player
		player = new Player(tileMap);
		
		// fill lists
		populateMonster();
		populateDiamonds();
		populateItems();
		populateBoss();
		populateLifepot();
		
		// initialize player
		player.setTilePosition(5, 4);
		player.setTotalDiamonds(diamonds.size());
		
		// set up camera position
		sectorSize = GamePanel.HEIGHT - 32;
		xsector = (player.getx()) / sectorSize;
		ysector = (player.gety()) / sectorSize;
		tileMap.setPositionImmediately(-xsector * sectorSize, -ysector * sectorSize);
		
		// load hud
		hud = new Hud(player, diamonds);
		
		// load music
		JukeBox.load("/Music/bgmusic.mp3", "music1");
		JukeBox.setVolume("music1", -10);
		JukeBox.loop("music1", 1000, 1000, JukeBox.getFrames("music1") - 1000);
		JukeBox.load("/Music/finish.mp3", "finish");
		JukeBox.setVolume("finish", -10);
		
		// load sfx
		JukeBox.load("/SFX/collect.wav", "collect");
		JukeBox.load("/SFX/mapmove.wav", "mapmove");
		JukeBox.load("/SFX/tilechange.wav", "tilechange");
		JukeBox.load("/SFX/splash.wav", "splash");
		
		// start event
		boxes = new ArrayList<Rectangle>();
		eventStart = true;
		eventStart();
			
	}
	private void populateLifepot(){
		Lifepot l;
		l = new Lifepot(tileMap);
		l.setTilePosition(6, 2);
		lifepots.add(l);

		l = new Lifepot(tileMap);
		l.setTilePosition(23, 34);
		lifepots.add(l);

		l = new Lifepot(tileMap);
		l.setTilePosition(29, 37);
		lifepots.add(l);

		l = new Lifepot(tileMap);
		l.setTilePosition(34, 2);
		lifepots.add(l);

		l = new Lifepot(tileMap);
		l.setTilePosition(26, 10);
		lifepots.add(l);
	}
	private void populateMonster(){
		Monster m;
		m = new Monster(tileMap);
		m.setTilePosition(3, 14);
		monster.add(m);

		m = new Monster(tileMap);
		m.setTilePosition(4, 34);
		monster.add(m);

		m = new Monster(tileMap);
		m.setTilePosition(12, 34);
		monster.add(m);

		m = new Monster(tileMap);
		m.setTilePosition(29, 27);
		monster.add(m);

		m = new Monster(tileMap);
		m.setTilePosition(11, 30);
		monster.add(m);

		m = new Monster(tileMap);
		m.setTilePosition(21, 13);
		monster.add(m);
	}

	private void populateBoss(){
		Boss b;
		b = new Boss(tileMap);
		b.setTilePosition(28, 32);
		boss.add(b);
	} 
	
	private void populateDiamonds() {
		//no diamond in this round
		
	}
	private void generateBullet(){
		if(monster.size() == 0 && boss.size() == 0)return;
		Bullet bu = new Bullet(tileMap);
 		bu.setPosition(player.getx(), player.gety());
		bu.setTarget(monster,boss,player.getx(), player.gety());
		bullets.add(bu);
	}
	
	private void populateItems() {
		
		Item item;

		item = new Item(tileMap);
		item.setType(Item.AXE);
		item.setTilePosition(10, 27);
		items.add(item);
		
		
		item = new Item(tileMap);
		item.setType(Item.WEAPON);
		item.setTilePosition(16, 13);
		items.add(item);
		
	}
	
	public void update() {
		
		// check keys
		handleInput();
		
		// check events
		if(eventStart) eventStart();
		if(eventFinish) eventFinish();
		
		if(boss.get(0).shouldRemove()) {
			eventFinish = blockInput = true;
		}

		if(player.getHealth() == 0){
			eventFinish= blockInput = true;
		}
		
		// update camera
		int oldxs = xsector;
		int oldys = ysector;
		xsector = (player.getx()) / sectorSize;
		ysector = (player.gety()) / sectorSize;
		tileMap.setPosition(-xsector * sectorSize, -ysector * sectorSize);
		tileMap.update();
		
		if(oldxs != xsector || oldys != ysector) {
			JukeBox.play("mapmove");
		}
		
		if(tileMap.isMoving()) return;
		
		// update player
		player.update();

		//update boss
		Boss b = boss.get(0);
		for(int j = 0; j < bullets.size(); j++)
					if(b.intersects(bullets.get(j))) b.setHealth(-1);
		b.update();
		if(player.intersects(b)) {
			// decrease health
			player.changeHealth(-1);
			// play health decreased sound
			JukeBox.play("collect");				
		}
		
		//update health
		for(int i = 0; i < monster.size(); i++) {
			Monster m = monster.get(i);
			m.update();
			if(player.intersects(m)) {
				// decrease health
				player.changeHealth(-1);
				// play health decreased sound
				JukeBox.play("collect");				
			}
		}

		//update lifepot
		for(int i = 0; i < lifepots.size(); i++) {
			
			Lifepot l = lifepots.get(i);
			l.update();
			
			// player collects diamond
			if(player.intersects(l) && (player.getHealth() < 3)) {
				// remove from list
				lifepots.remove(i);
				i--;

				// increment amount of collected diamonds
				player.setHealth(1);
				HealthControl.modifyHealth(1);
				
				// play collect sound
				JukeBox.play("collect");
				
				// add new sparkle
				Sparkle s = new Sparkle(tileMap);
				s.setPosition(l.getx(), l.gety());
				sparkles.add(s);
			}
		}

		// update diamonds
		for(int i = 0; i < diamonds.size(); i++) {
			
			Diamond d = diamonds.get(i);
			d.update();
			
			// player collects diamond
			if(player.intersects(d)) {
				
				// remove from list
				diamonds.remove(i);
				i--;
				
				// increment amount of collected diamonds
				player.collectedDiamond();
				
				// play collect sound
				JukeBox.play("collect");
				
				// add new sparkle
				Sparkle s = new Sparkle(tileMap);
				s.setPosition(d.getx(), d.gety());
				sparkles.add(s);
				
				// make any changes to tile map
				ArrayList<int[]> ali = d.getChanges();
				for(int[] j : ali) {
					tileMap.setTile(j[0], j[1], j[2]);
				}
				if(ali.size() != 0) {
					JukeBox.play("tilechange");
				}
				
			}
		}
		
		// update sparkles
		for(int i = 0; i < sparkles.size(); i++) {
			Sparkle s = sparkles.get(i);
			s.update();
			if(s.shouldRemove()) {
				sparkles.remove(i);
				i--;
			}
		}
		
		// update items
		for(int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			if(player.intersects(item)) {
				items.remove(i);
				i--;
				item.collected(player);
				JukeBox.play("collect");
				Sparkle s = new Sparkle(tileMap);
				s.setPosition(item.getx(), item.gety());
				sparkles.add(s);
			}
		}

		//update monster
		for(int i = 0; i < monster.size(); i++) {
			Monster m = monster.get(i);
			m.update();
			for(int j = 0; j < bullets.size(); j++)
					if(m.intersects(bullets.get(j))) m.setHealth(-1);
			if (m.getHealth() <= 0){
				monster.remove(i);
				i--;
			}
		}	

		//update bullets
		for(int i = 0; i < bullets.size(); i++) {
			Bullet bu = bullets.get(i);
			bu.update();
			if(bu.shouldRemove()) {
				bullets.remove(i);
				i--;
			}
		}
	}
	
	public void draw(Graphics2D g) {
		
		// draw tilemap
		tileMap.draw(g);
		
		// draw player
		player.draw(g);
		
		// draw diamonds
		for(Diamond d : diamonds) {
			d.draw(g);
		}

		//draw bullets
		for(Bullet bu : bullets) {
			bu.draw(g);
		}
		
		// draw sparkles
		for(Sparkle s : sparkles) {
			s.draw(g);
		}
		
		// draw items
		for(Item i : items) {
			i.draw(g);
			i.setLeft();
		}

		//draw Monster
		for(Monster i : monster){
			i.draw(g);
		}

		//draw boss
		for(Boss i: boss){
			i.draw(g);
		}

		//draw lifepot
		for(Lifepot l: lifepots){
			l.draw(g);
		}
		
		// draw transition boxes
		g.setColor(java.awt.Color.BLACK);
		for(int i = 0; i < boxes.size(); i++) {
			g.fill(boxes.get(i));
		}

		// draw hud
		hud.draw(g);
		
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.ESCAPE)) {
			JukeBox.stop("music1");
			gsm.setPaused(true);
		}
		if (Keys.isPressed(Keys.SPACE)) {
			if(player.hasWeapon()) generateBullet();
		}
		if(blockInput) return;
		if(Keys.isDown(Keys.LEFT) || Keys.isDown(Keys.A)) player.setLeft();
		if(Keys.isDown(Keys.RIGHT) || Keys.isDown(Keys.D)) player.setRight();
		if(Keys.isDown(Keys.UP) || Keys.isDown(Keys.W)) player.setUp();
		if(Keys.isDown(Keys.DOWN) || Keys.isDown(Keys.S)) player.setDown();
		if(Keys.isPressed(Keys.SPACE)) player.setAction();
	}
	
	//===============================================
	
	private void eventStart() {
		eventTick++;
		if (HealthControl.getHealth() == 0){
			Data.setTime(player.getTicks());
			gsm.setState(gsm.GAMEOVER);
		}
		if(eventTick == 1) {
			boxes.clear();
			for(int i = 0; i < 12; i++) {
				boxes.add(new Rectangle(0, i * 16, GamePanel.WIDTH, 16));
			}
		}
		if(eventTick > 1 && eventTick < 32) {
			for(int i = 0; i < boxes.size(); i++) {
				Rectangle r = boxes.get(i);
				if(i % 2 == 0) {
					r.x -= 4;
				}
				else {
					r.x += 4;
				}
			}
		}
		if(eventTick == 33) {
			boxes.clear();
			eventStart = false;
			eventTick = 0;
		}
	}
	
	private void eventFinish() {
		eventTick++;
		if (HealthControl.getHealth() == 0){
			Data.setTime(player.getTicks());
			gsm.setState(gsm.GAMEOVER);
		}
		if(eventTick == 1) {
			boxes.clear();
			for(int i = 0; i < 12; i++) {
				if(i % 2 == 0) boxes.add(new Rectangle(-128, i * 16, GamePanel.WIDTH, 16));
				else boxes.add(new Rectangle(128, i * 16, GamePanel.WIDTH, 16));
			}
			JukeBox.stop("music1");
			JukeBox.play("finish");
		}
		if(eventTick > 1) {
			for(int i = 0; i < boxes.size(); i++) {
				Rectangle r = boxes.get(i);
				if(i % 2 == 0) {
					if(r.x < 0) r.x += 4;
				}
				else {
					if(r.x > 0) r.x -= 4;
				}
			}
		}
		if(boss.get(0).getHealth() <= 0) {
			if(!JukeBox.isPlaying("finish")) {
				Data.setTime(player.getTicks());
				gsm.setState(GameStateManager.GAMEWIN);
			}
		}
	}
	
}
