/**
 * Name: Group 08
   Member:
   1: Ngô Lê Thiên Ân ITITDK21030
   2: Nguyễn Đình Thắng ITITIU21309
   3: Huỳnh Thanh Thủy ITITIU21325
   4: Cao Hoàng Khôi Nguyên ITITDK21048
   Purpose:
		// user can choose the appearance for the character
 */
package com.neet.DiamondHunter.GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.concurrent.TimeUnit;

import com.neet.DiamondHunter.Entity.Player;
import com.neet.DiamondHunter.Manager.Content;
import com.neet.DiamondHunter.Manager.GameStateManager;
import com.neet.DiamondHunter.Manager.Keys;
import com.neet.DiamondHunter.Manager.JukeBox;

public class CharacterChoosing extends GameState {
    private boolean choosingCharacter = true;
    private boolean pressspace = false;

    public static boolean character = true;

    private int charoption = 0;
    private int square = 17;
    
    private String [] option = {
        "upin",
        "ipin"
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
        g.drawImage(Content.PLAYER1[0][0], 30, 70, null);
        g.drawImage(Content.PLAYER2[0][0], 110, 70, null);
        g.drawImage(Content.CHOOSECHAR[0][0], 10, 6, null);
        
        Content.drawString(g, option[1],97, 108);
        Content.drawString(g, option[0],20, 108);
        Content.drawString(g, "CHOOSE CHAR : SPACE", 4, 130);
        Content.drawString(g, "START       : ENTER", 4, 140);
        Content.drawString(g, "BACK TO MENU: ESC",4, 150);

        g.setColor(Color.WHITE);
        g.drawRect(square, 48, 48, 55);

        if(pressspace){ 
        g.setColor(Color.YELLOW);
        g.drawRect(square, 48, 48, 55);
        }
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
                if(charoption > 0){
                    JukeBox.play("menuoption");
                    charoption--;
                    square = 17;
                }
                else if(charoption == 0){
                    JukeBox.play("menuoption");
                    charoption = option.length - 1;
                    square = 93;
                }

                //Player.buttonPressed(1); // Choose player 1
                
            } else if (Keys.isPressed(Keys.D) || Keys.isPressed(Keys.RIGHT)) {
                if(charoption == option.length - 1){ 
                    JukeBox.play("menuoption");
                    charoption = 0;
                    square = 17;
                }
                else if(charoption < option.length - 1){
                    JukeBox.play("menuoption");
                    charoption++;
                    square = 93;
                }
                
                //Player.buttonPressed(2); // Choose player 2
            }
        }
        if (Keys.isPressed(Keys.SPACE)){
            JukeBox.play("collect");
            choosingCharacter = false; // Exit character selection
            selectOption();
            pressspace = true;
            
        }
        if (Keys.isPressed(Keys.ENTER) && pressspace){
            JukeBox.play("collect");
            gsm.setState(GameStateManager.ROUND1);
        }
    }

    private void selectOption(){
        if (charoption == 0 || charoption == 1){
            gsm.setPaused(false);
            if (charoption == 0){
                character = true;
            }
            else if(charoption == 1){
                character = false;
            }
        }
    }
}
    


