// The only subclass the fully utilizes the
// Entity superclass (no other class requires
// movement in a tile based map).
// Contains all the gameplay associated with
// the Player.

package com.neet.DiamondHunter.Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.security.AlgorithmParameterGenerator;

import com.neet.DiamondHunter.Manager.Content;
import com.neet.DiamondHunter.Manager.Data;
import com.neet.DiamondHunter.Manager.HealthControl;
import com.neet.DiamondHunter.Manager.JukeBox;
import com.neet.DiamondHunter.TileMap.TileMap;
import com.neet.DiamondHunter.Entity.Bullet;
import com.neet.DiamondHunter.GameState.CharacterChoosing;

public class Player extends Entity {
	
	// sprites
	private BufferedImage[] downSprites;
	private BufferedImage[] leftSprites;
	private BufferedImage[] rightSprites;
	private BufferedImage[] upSprites;
	private BufferedImage[] downBoatSprites;
	private BufferedImage[] leftBoatSprites;
	private BufferedImage[] rightBoatSprites;
	private BufferedImage[] upBoatSprites;
	
	// animation
	private final int DOWN = 0;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	private final int UP = 3;
	private final int DOWNBOAT = 4;
	private final int LEFTBOAT = 5;
	private final int RIGHTBOAT = 6;
	private final int UPBOAT = 7;
	
	// gameplay
	private int numDiamonds;
	private int totalDiamonds;
	private boolean hasWeapon;
	private boolean hasKey;
	private boolean hasAxe;
	private boolean onWater;
	private long ticks;
	
	public Player(TileMap tm) {
		
		super(tm);
		
		width = 16;
		height = 16;
		cwidth = 12;
		cheight = 12;
		ticks = Data.getTime();
		
		moveSpeed = 2;
		health = HealthControl.getHealth();
		
		
		numDiamonds = 0;
		
		if(CharacterChoosing.character){

		downSprites = Content.PLAYER1[0];
		leftSprites = Content.PLAYER1[1];
		rightSprites = Content.PLAYER1[2];
		upSprites = Content.PLAYER1[3];
		downBoatSprites = Content.PLAYER1[4];
		leftBoatSprites = Content.PLAYER1[5];
		rightBoatSprites = Content.PLAYER1[6];
		upBoatSprites = Content.PLAYER1[7];
		
		animation.setFrames(downSprites);
		animation.setDelay(10);

		}
		else{

		downSprites = Content.PLAYER2[0];
		leftSprites = Content.PLAYER2[1];
		rightSprites = Content.PLAYER2[2];
		upSprites = Content.PLAYER2[3];
		downBoatSprites = Content.PLAYER2[4];
		leftBoatSprites = Content.PLAYER2[5];
		rightBoatSprites = Content.PLAYER2[6];
		upBoatSprites = Content.PLAYER2[7];
		
		animation.setFrames(downSprites);
		animation.setDelay(10);
		}
		
		
	}
	
	private void setAnimation(int i, BufferedImage[] bi, int d) {
		currentAnimation = i;
		animation.setFrames(bi);
		animation.setDelay(d);
	}
	
	public void collectedDiamond() { numDiamonds++; }
	public int numDiamonds() { return numDiamonds; }
	public int getTotalDiamonds() { return totalDiamonds; }
	public void setTotalDiamonds(int i) { totalDiamonds = i; }
	
	public void gotWeapon() { hasWeapon = true; }
	public void gotKey() { hasKey = true; }
	public void gotAxe() { hasAxe = true; }
	public boolean hasWeapon() { return hasWeapon; }
	public boolean hasAxe() { return hasAxe; }
	public boolean hasKey() { return hasKey; }
	
	// Used to update time.
	public long getTicks() { return ticks; }
	
	// Keyboard input. Moves the player.
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
	
	public void changeHealth(int amount) {
		actionCounter++;
		if (actionCounter == 16){
			setHealth(-1);
			HealthControl.modifyHealth(-1);	
			actionCounter = 0;
		}
	}
	// Keyboard input.
	// If Player has axe, dead trees in front
	// of the Player will be chopped down.
	public void setAction() {
		if(hasAxe) {
			if(currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 21) {
				tileMap.setTile(rowTile - 1, colTile, 1);
				JukeBox.play("tilechange");
			}
			if(currentAnimation == DOWN && tileMap.getIndex(rowTile + 1, colTile) == 21) {
				tileMap.setTile(rowTile + 1, colTile, 1);
				JukeBox.play("tilechange");
			}
			if(currentAnimation == LEFT && tileMap.getIndex(rowTile, colTile - 1) == 21) {
				tileMap.setTile(rowTile, colTile - 1, 1);
				JukeBox.play("tilechange");
			}
			if(currentAnimation == RIGHT && tileMap.getIndex(rowTile, colTile + 1) == 21) {
				tileMap.setTile(rowTile, colTile + 1, 1);
				JukeBox.play("tilechange");
			}
		}
	}
	
	public void update() {
		
		ticks++;
		
		// check if on water
		boolean current = onWater;
		if(tileMap.getIndex(ydest / tileSize, xdest / tileSize) == 4) {
			changeHealth(-1);
			onWater = true;
		}
		else {
			onWater = false;
		}
		// if going from land to water
		if(!current && onWater) {
			JukeBox.play("splash");
		}
		
		// set animation
		if(down) {
			if(onWater && currentAnimation != DOWNBOAT) {
				setAnimation(DOWNBOAT, downBoatSprites, 10);
			}
			else if(!onWater && currentAnimation != DOWN) {
				setAnimation(DOWN, downSprites, 10);
			}
		}
		if(left) {
			if(onWater && currentAnimation != LEFTBOAT) {
				setAnimation(LEFTBOAT, leftBoatSprites, 10);
			}
			else if(!onWater && currentAnimation != LEFT) {
				setAnimation(LEFT, leftSprites, 10);
			}
		}
		if(right) {
			if(onWater && currentAnimation != RIGHTBOAT) {
				setAnimation(RIGHTBOAT, rightBoatSprites, 10);
			}
			else if(!onWater && currentAnimation != RIGHT) {
				setAnimation(RIGHT, rightSprites, 10);
			}
		}
		if(up) {
			if(onWater && currentAnimation != UPBOAT) {
				setAnimation(UPBOAT, upBoatSprites, 10);
			}
			else if(!onWater && currentAnimation != UP) {
				setAnimation(UP, upSprites, 10);
			}
		}
		
		// update position
		super.update();
	}
	
	// Draw Player.
	public void draw(Graphics2D g) {
		super.draw(g);
	}
}