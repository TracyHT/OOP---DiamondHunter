// The main menu GameState.

package com.neet.DiamondHunter.GameState;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.neet.DiamondHunter.Manager.Content;
import com.neet.DiamondHunter.Manager.GameStateManager;
import com.neet.DiamondHunter.Manager.JukeBox;
import com.neet.DiamondHunter.Manager.Keys;

public class MenuState extends GameState {
	
	private BufferedImage bg;
	private BufferedImage diamond;
	
	private int currentOption = 0;
	private String[] options = {
		"START",
		"QUIT",
		"CHARACTER",
		"GUIDELINE",
	};
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		bg = Content.MENUBG[0][0];
		diamond = Content.DIAMOND[0][0];
		JukeBox.load("/SFX/collect.wav", "collect");
		JukeBox.load("/SFX/menuoption.wav", "menuoption");
	}
	
	public void update() {
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(bg, 0, 0, null);
		
		Content.drawString(g, options[0], 62, 90);
		Content.drawString(g, options[1], 66, 100);
		Content.drawString(g, options[2], 44, 110);
		Content.drawString(g, options[3], 44, 120);
		
		if(currentOption == 0) g.drawImage(diamond, 25, 86, null);
		else if(currentOption == 1) g.drawImage(diamond, 25, 96, null);
		else if (currentOption == 2) g.drawImage(diamond, 25, 106, null);
		else if (currentOption == 3) g.drawImage(diamond, 25, 116, null);
		
	}
	
	public void handleInput() {
		if((Keys.isPressed(Keys.DOWN) || Keys.isPressed(Keys.S)) && currentOption < options.length - 1) {
			JukeBox.play("menuoption");
			currentOption++;
		}
		else if((Keys.isPressed(Keys.DOWN) || Keys.isPressed(Keys.S)) && currentOption > 0){
			JukeBox.play("menuoption");
			currentOption--;
		}
		if((Keys.isPressed(Keys.UP) || Keys.isPressed(Keys.W)) && currentOption > 0) {
			JukeBox.play("menuoption");
			currentOption--;
		}
		else if((Keys.isPressed(Keys.UP) || Keys.isPressed(Keys.W)) && currentOption < options.length - 1){
			JukeBox.play("menuoption");
			currentOption++;
		}
		if(Keys.isPressed(Keys.ENTER)) {
			JukeBox.play("collect");
			selectOption();
		}
	}
	
	private void selectOption() {
        if (currentOption == 0) {
            // "Start" option selected
            gsm.setState(GameStateManager.ROUND1);
        } else if (currentOption == 1) {
            // "Options" option selected
            System.exit(0);
        } else if (currentOption == 2) {
            // "Character" option selected
            gsm.setState(GameStateManager.CHARACTER);
        } else if (currentOption == 3) {
            // "Guideline" option selected
            //gsm.setState(GameStateManager.GUIDELINE);
        }
    }

	
}
