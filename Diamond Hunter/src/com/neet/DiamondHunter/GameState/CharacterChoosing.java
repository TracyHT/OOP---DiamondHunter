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
    private int charoption = 0;
    private String [] option = {
        "Name",
        "Name"
    };
    

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
        g.drawImage(Content.PLAYER2[0][0], 110, 70, null);

        Content.drawString(g, "CHOOSE", 34, 10);
        Content.drawString(g, "YOUR", 86, 10);
        Content.drawString(g, "CHARACTER",42, 20);
        Content.drawString(g, option[1],97, 108);
        Content.drawString(g, option[0],20, 108);
        Content.drawString(g, "CHOOSE CHAR: ENTER", 10, 130);
        Content.drawString(g, "START: ENTER", 10, 140);
        Content.drawString(g, "BACK TO MENU: ESC",10, 150);
    }

    
    @Override
    public void handleInput() {
        if (Keys.isPressed(Keys.ESCAPE)) {
            gsm.setPaused(false);
            JukeBox.resumeLoop("music1");
            //Player.buttonPressed(1); // Choose player 1
            choosingCharacter = false; // Exit character selection
            gsm.setState(GameStateManager.MENU);
        }
        if (choosingCharacter) {
            if (Keys.isPressed(Keys.A) || Keys.isPressed(Keys.LEFT)) {
                if(charoption == 1){
                    JukeBox.play("menuoption");
                    charoption = 0;
                }
                else if(charoption == 0){
                    JukeBox.play("menuoption");
                    charoption = 1;
                }

                //Player.buttonPressed(1); // Choose player 1
                
            } else if (Keys.isPressed(Keys.D) || Keys.isPressed(Keys.RIGHT)) {
                if(charoption == 1){ 
                    JukeBox.play("menuoption");
                    charoption = 0;
                }
                else if(charoption == 0){
                    JukeBox.play("menuoption");
                    charoption = 1;
                }
                
                //Player.buttonPressed(2); // Choose player 2
            }
        }
        if (Keys.isPressed(Keys.SPACE)){
            JukeBox.play("collect");
            choosingCharacter = false; // Exit character selection
            selectOption();
        }
        if (Keys.isPressed(Keys.ENTER)){
            JukeBox.play("collect");
            gsm.setState(GameStateManager.ROUND2);
        }
    }

    private void selectOption(){
        if (charoption == 0 || charoption == 1){
            gsm.setPaused(false);
            //JukeBox.resumeLoop("music1");
            if (charoption == 0){
            
            }
            else if(charoption == 1){
                
            }
        }
    }
}
    


