package com.neet.DiamondHunter.Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import com.neet.DiamondHunter.Manager.Content;

public class Health {

    private int health = 3;
    private BufferedImage[] hearts;

    public Health() {
        try {
            hearts = Content.HEARTS[0]; 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void draw(Graphics2D g) {
        int xoffset = 10;
        int yoffset = 10;
        for (int i = 0; i < health; i++) {
            g.drawImage(hearts[0], xoffset + i * 20, yoffset, null);
        }
    }

    public void updateHealth(Graphics2D g) {
        // instantiate the Player class with null argument

        //if (player.getOnWater()) {
            //health -= 1;

             //make sure health is not less than 0
            if (health < 0) {
                health = 0;
            }

            // update the health bar
            int xoffset = 10;
            int yoffset = 10;
            for (int i = 0; i < health; i++) {
                g.drawImage(hearts[0], xoffset + i * 20, yoffset, null);
            }
        }
    }
//



