// The pause GameState can only be activated
// by calling GameStateManager#setPaused(true).

package com.neet.DiamondHunter.GameState;

import java.awt.Graphics2D;

import com.neet.DiamondHunter.Manager.Content;
import com.neet.DiamondHunter.Manager.GameStateManager;
import com.neet.DiamondHunter.Manager.JukeBox;
import com.neet.DiamondHunter.Manager.Keys;

public class PauseState extends GameState {
	
	public PauseState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {}
	
	public void update() {
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		
		Content.drawString(g, "paused", 40, 30);

		Content.drawString(g, "Mute", 12, 66);
		Content.drawString(g, ": Press x", 52, 66);
		
		Content.drawString(g, "arrow", 12, 76);
		Content.drawString(g, "keys", 16, 84);
		Content.drawString(g, ": move", 52, 80);
		
		Content.drawString(g, "space", 12, 96);
		Content.drawString(g, ": action", 52, 96);
		
		Content.drawString(g, "F1:", 36, 112);
		Content.drawString(g, "return", 68, 108);
		Content.drawString(g, "to menu", 68, 116);
		
	}
	public void handleInput() {
		//mute
			if (Keys.isPressed(Keys.X)) {
				if(PlayState.mute == true){
					PlayState.mute = false;
				}
				else{
					PlayState.mute = true;
				}
			}

		if(Keys.isPressed(Keys.ESCAPE)) {
			if(PlayState.mute == true){
				JukeBox.resumeLoop("music1");
			}
			else{
				JukeBox.stop("music1");
			}
			gsm.setPaused(false);
		}

		if(Keys.isPressed(Keys.F1)) {
			gsm.setPaused(false);
			gsm.setState(GameStateManager.MENU);
		}
	}
	
}
