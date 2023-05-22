// Congratulations for finishing the game.
// Gives you a rank based on how long it took
// you to beat the game.

// Under two minutes = Speed Demon
// Under three minutes = Adventurer
// Under four minutes = Beginner
// Four minutes or greater = Bumbling Idiot

package com.neet.DiamondHunter.GameState;

import java.awt.Color;
import java.awt.Graphics2D;

import com.neet.DiamondHunter.Main.GamePanel;
import com.neet.DiamondHunter.Manager.Content;
import com.neet.DiamondHunter.Manager.Data;
import com.neet.DiamondHunter.Manager.GameStateManager;
import com.neet.DiamondHunter.Manager.JukeBox;
import com.neet.DiamondHunter.Manager.Keys;

public class GameOverState extends GameState {

	private Color color;

	private int rank;
	private long ticks;
	int a = 2;
	
	public GameOverState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		color = new Color(164, 198, 222);
		ticks = Data.getTime();
		if (ticks < 3600)
			rank = 1;
		else if (ticks < 5400)
			rank = 2;
		else if (ticks < 7200)
			rank = 3;
		else
			rank = 4;
	}

	public void update() {
	}

	public void draw(Graphics2D g) {

		g.setColor(color);
		g.drawImage(Content.GAMEOVERLOSE[0][0], 0, 0, null);
		
		int minutes = (int) (ticks / 1800);
		int seconds = (int) ((ticks / 30) % 60);
		if(minutes < 10) {
			if(seconds < 10) Content.drawString(g, "0" + minutes + ":0" + seconds, 60+a, 48);
			else Content.drawString(g, "0" + minutes + ":" + seconds, 60+a, 48);
		}
		else {
			if(seconds < 10) Content.drawString(g, minutes + ":0" + seconds, 60+a, 48);
			else Content.drawString(g, minutes + ":" + seconds, 60+a, 48);
		}
		
		Content.drawString(g, "rank", 64+a, 66);
		if(rank == 1) Content.drawString(g, "speed demon", 36+a, 78);
		else if(rank == 2) Content.drawString(g, "adventurer", 40+a, 78);
		else if(rank == 3) Content.drawString(g, "beginner", 38+a, 78);
		else if(rank == 4) Content.drawString(g, "bumbling idiot", 24+a, 78);
		
		Content.drawString(g, "press any key", 28+a, 110);
		
	}

	public void handleInput() {
		if (Keys.isPressed(Keys.ENTER)) {
			gsm.setState(GameStateManager.MENU);
			JukeBox.play("collect");
		}
	}

}