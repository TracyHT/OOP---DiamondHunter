/**
 * Name: Group 08
   Member:
   1: Ngô Lê Thiên Ân ITITDK21030
   2: Nguyễn Đình Thắng ITITIU21309
   3: Huỳnh Thanh Thủy ITITIU21325
   4: Cao Hoàng Khôi Nguyên ITITDK21048
   Purpose:
		// The GameStateManager does exactly what its
		// name says. It contains a list of GameStates.
		// It decides which GameState to update() and
		// draw() and handles switching between different
		// GameStates.
 */
package com.neet.DiamondHunter.Manager;

import java.awt.Graphics2D;

import com.neet.DiamondHunter.GameState.CharacterChoosing;
import com.neet.DiamondHunter.GameState.GameOverState;
import com.neet.DiamondHunter.GameState.GameState;
import com.neet.DiamondHunter.GameState.GameWinState;
import com.neet.DiamondHunter.GameState.Guideline;
import com.neet.DiamondHunter.GameState.IntroState;
import com.neet.DiamondHunter.GameState.MenuState;
import com.neet.DiamondHunter.GameState.PauseState;
import com.neet.DiamondHunter.GameState.Round2;
import com.neet.DiamondHunter.GameState.Round1;
import com.neet.DiamondHunter.GameState.Round3;


public class GameStateManager {
	
	private boolean paused;
	private PauseState pauseState;
	
	private GameState[] gameStates;
	private int currentState;
	private int previousState;
	
	public static final int NUM_STATES = 9;
	public static final int INTRO = 0;
	public static final int MENU = 1;
	public static final int GAMEOVER = 2;
	public static final int ROUND1 = 3;
	public static final int ROUND2 = 4;
	public static final int ROUND3 = 5;
	public static final int CHARACTER = 6;
	public static final int GUIDELINE = 7;
	public static final int GAMEWIN = 8;
	
	
	public GameStateManager() {
		
		JukeBox.init();
		
		paused = false;
		pauseState = new PauseState(this);
		
		gameStates = new GameState[NUM_STATES];
		setState(INTRO);
		
	}
	public int getState(){
		return currentState;
	}
	
	public void setState(int i) {
		previousState = currentState;
		unloadState(previousState);
		currentState = i;
		if(i == INTRO) {
			gameStates[i] = new IntroState(this);
			gameStates[i].init();
		}
		else if(i == MENU) {
			gameStates[i] = new MenuState(this);
			gameStates[i].init();
		}
		else if(i == ROUND1) {
			gameStates[i] = new Round1(this);
			gameStates[i].init();
		}
		else if(i == ROUND2) {
			gameStates[i] = new Round2(this);
			gameStates[i].init();
		}
		else if (i == ROUND3) {
			gameStates[i] = new Round3(this);
			gameStates[i].init();
		}
		else if(i == GAMEOVER) {
			gameStates[i] = new GameOverState(this);
			gameStates[i].init();
		}
		else if(i == CHARACTER){
			gameStates[i] = new CharacterChoosing(this);
			gameStates[i].init();
		}
		else if(i == GUIDELINE){
			gameStates[i] = new Guideline(this);
			gameStates[i].init();
		}
		else if(i == GAMEWIN){
			gameStates[i] = new GameWinState(this);
			gameStates[i].init();
		}
	}
	
	public void unloadState(int i) {
		gameStates[i] = null;
	}
	
	public void setPaused(boolean b) {
		paused = b;
	}
	
	public void update() {
		if(paused) {
			pauseState.update();
		}
		else if(gameStates[currentState] != null) {
			gameStates[currentState].update();
		}
	}
	
	public void draw(Graphics2D g) {
		if(paused) {
			pauseState.draw(g);
		}
		else if(gameStates[currentState] != null) {
			gameStates[currentState].draw(g);
		}
	}
	
}
