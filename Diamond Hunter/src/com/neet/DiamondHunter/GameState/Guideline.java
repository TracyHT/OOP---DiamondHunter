package com.neet.DiamondHunter.GameState;

import java.awt.Graphics2D;

import com.neet.DiamondHunter.Manager.Content;
import com.neet.DiamondHunter.Manager.GameStateManager;
import com.neet.DiamondHunter.Manager.JukeBox;
import com.neet.DiamondHunter.Manager.Keys;

public class Guideline extends GameState {

    public Guideline(GameStateManager gsm) {
        super(gsm);
    }

    public void init() {
    }

    public void update() {
        handleInput();
    }

    public void draw(Graphics2D g) {
        g.drawImage(Content.GUIDELINE[0][0], 0, 0, null);
        g.drawImage(Content.GUIDELINETITLE[0][0], 10, 20, null);
		
		Content.drawString(g, "move:", 24, 76);
		Content.drawString(g, "a w s d", 72, 72);
        Content.drawString(g, "or arrow", 66, 82);
		
		Content.drawString(g, "space", 14, 96);
		Content.drawString(g, ": action", 56, 96);
		
		Content.drawString(g, "esc:", 32, 112);
		Content.drawString(g, "return", 72, 108);
		Content.drawString(g, "to menu", 70, 116);

        Content.drawString(g, "enter:", 16, 132);
        Content.drawString(g, "choose", 78, 128);
        Content.drawString(g, "character", 68, 136);
		
    }
    

    public void handleInput() {
            if(Keys.isPressed(Keys.ESCAPE)) {
                JukeBox.play("collect");
                gsm.setPaused(false);
                gsm.setState(GameStateManager.MENU);
            }
            else if(Keys.isPressed(Keys.ENTER)){
                JukeBox.play("collect");
                gsm.setPaused(false);
                gsm.setState(GameStateManager.CHARACTER);
            }
        }
        //in order to press Enter you have to press Space to choose the character
        
    }

