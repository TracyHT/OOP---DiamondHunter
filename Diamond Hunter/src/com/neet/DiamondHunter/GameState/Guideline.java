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
    }
    

    public void handleInput() {
            if(Keys.isPressed(Keys.ESCAPE)) {
                gsm.setPaused(false);
                JukeBox.resumeLoop("music1");
                gsm.setState(GameStateManager.MENU);
            }
        }
        
    }

