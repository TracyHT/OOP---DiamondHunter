package com.neet.DiamondHunter.GameState;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.neet.DiamondHunter.Manager.Content;
import com.neet.DiamondHunter.Manager.GameStateManager;

public class CharacterChoosing extends GameState {

    private BufferedImage player1Sprite = Content.PLAYER1[0][0];
    private BufferedImage player2Sprite = Content.PLAYER2[0][0];
    private boolean choosingCharacter = false;
    private int selectedCharacter = 0;
    private BufferedImage diamond;
    private int currentOption = 0;
	/*private String[] options = {
		"Select Character"
	}*/
    

    public CharacterChoosing(GameStateManager gsm) {
        super(gsm);
    }
    
    //@Override
    public void init() {
        
        /*player1Sprite = Content.PLAYER1[0][0];
        player2Sprite = Content.PLAYER2[0][0];
        diamond = Content.DIAMOND[0][0];
        choosingCharacter = true;
        selectedCharacter = 1;*/
    }

    @Override
    public void update() {
        handleInput();
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawRect(0, 0, 160, 176);
        Content.drawString(g, "CHOOSE YOUR CHARACTER", 62, 90);
        
        if (choosingCharacter) {
            g.drawImage(Content.PLAYER1[0][0], 120, 90, null);
            g.drawImage(Content.PLAYER2[0][0], 160, 90, null);
            //g.drawImage(Sprites.diamond, 200, 90, null);
        } else {
            if (selectedCharacter == 0)
                g.drawImage(player1Sprite, 120, 90, null);
            else if (selectedCharacter == 1)
                g.drawImage(player2Sprite, 120, 90, null);
        }
        //Content.drawString(g, options[0], 62, 90);
		
		//if(currentOption == 0) g.drawImage(diamond, 25, 86, null);
		//else if(currentOption == 1) g.drawImage(diamond, 25, 96, null);
    }

    @Override
    public void handleInput() {
        if (choosingCharacter && selectedCharacter == 0){
            chooseCharacter(1);
        } else if (choosingCharacter && selectedCharacter == 1){
            chooseCharacter(2);
        }
        
    }

    public void chooseCharacter(int character) {
        if (character == 1) {
            selectedCharacter = 0; // player 1
            player1Sprite = Content.PLAYER1[0][0];
            player2Sprite = Content.PLAYER2[0][0];
        } else if (character == 2) {
            selectedCharacter = 1; // player 2
            player1Sprite = Content.PLAYER1[0][0];
            player2Sprite = Content.PLAYER2[0][0];
        }
        choosingCharacter = false;
    }
}

