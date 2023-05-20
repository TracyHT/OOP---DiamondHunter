package com.neet.DiamondHunter.GameState;

import java.awt.Color;
import java.awt.Graphics2D;

import com.neet.DiamondHunter.Entity.Player;
import com.neet.DiamondHunter.Manager.Content;
import com.neet.DiamondHunter.Manager.GameStateManager;
import com.neet.DiamondHunter.Manager.Keys;
import com.neet.DiamondHunter.Manager.JukeBox;

public class CharacterChoosing extends GameState {
    private boolean choosingCharacter = true;

    

    public CharacterChoosing(GameStateManager gsm) {
        super(gsm);
    }
    
    //@Override
    public void init() {}

    @Override
    public void update() {
        handleInput();
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.ORANGE);
       
        g.drawImage(Content.CharacterFrame[0][0],null, 0, 0);
        g.drawImage(Content.PLAYER1[0][0], 33, 70, null);
        g.drawImage(Content.PLAYER2[0][0], 105, 50, null);

        Content.drawString(g, "CHOOSE", 34, 10);
        Content.drawString(g, "YOUR", 86, 10);
        Content.drawString(g, "CHARACTER",42, 20);
        Content.drawString(g, "Press T",94, 135);
        Content.drawString(g, "Press O",14, 135);
    }

    
    @Override
    public void handleInput() {
        if (choosingCharacter) {
            if (Keys.isPressed(Keys.O)) {
                gsm.setPaused(false);
                JukeBox.resumeLoop("music1");
                Player.buttonPressed(1); // Choose player 1
                choosingCharacter = false; // Exit character selection
                gsm.setState(GameStateManager.MENU);
            } else if (Keys.isPressed(Keys.T)) {
                gsm.setPaused(false);
                JukeBox.resumeLoop("music1");
                Player.buttonPressed(2); // Choose player 2
                choosingCharacter = false; // Exit character selection
                gsm.setState(GameStateManager.MENU);
            }
        }
    }
}
    


