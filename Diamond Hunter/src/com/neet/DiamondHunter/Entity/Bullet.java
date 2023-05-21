// Simple class that plays animation
// once and is removed.

package com.neet.DiamondHunter.Entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.lang.Math;

import com.neet.DiamondHunter.Manager.Content;
import com.neet.DiamondHunter.TileMap.TileMap;
import com.neet.DiamondHunter.Entity.Monster;

public class Bullet extends Entity {
	
	private boolean remove;
    private double distanceMin = 1000;
    private int indexMin = -1;
    private int type = 0;
    private Entity target;
	
	public Bullet(TileMap tm) {
		super(tm);
		animation.setFrames(Content.BULLET[0]);
		animation.setDelay(6);
		width = 13;
        height = 14;
        cwidth = 13;
		cheight = 14;
        moveSpeed = 3;
    }

    public void moveTo(){
        if (target.getx() < this.getx()) setLeft();
        else setRight();
    }

    public void setTarget(ArrayList<Monster> mList, ArrayList<Boss> bList, int oldx, int oldy) {
        //find the nearest Monster to shoot
        if((mList.size() == 0) && (bList.size() == 0)){
            target.setPosition(oldx + 30, oldy);
            return;
        }
		for(int i = 0; i < mList.size(); i++){
            Monster m = mList.get(i);
            double distance = (m.getx()-oldx) * (m.getx()-oldx) + (m.gety()-oldy)* (m.gety() - oldy);
            distance = Math.sqrt(distance);
            if(distance < distanceMin){
                distanceMin = distance;
                indexMin = i;
            }
        }
        if(indexMin != -1){
            for(int i = 0; i < bList.size(); i++){
                Boss b = bList.get(i);
                double distance = b.getx()*b.getx() + b.gety()*b.gety();
                distance = Math.sqrt(distance);
                if(distance < distanceMin){
                    distanceMin = distance;
                    indexMin = i;
                    type = 1;
                }
            }
            if (type == 0){
                target = mList.get(indexMin);
            }
            else if (type == 1){
                target = bList.get(indexMin);
            }
        }       
	}
	
	public boolean shouldRemove() {
        if (intersects(target)){
            return true;
        }
        if(animation.hasPlayedOnce()) remove = true;
        return remove;
	}
	
	public void update() {
		animation.update();
        moveTo();
        super.update();
	}
	
	public void draw(Graphics2D g) {
		super.draw(g);
	}
	
}
