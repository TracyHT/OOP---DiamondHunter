package com.neet.DiamondHunter.Manager;

public class HealthControl {
	
	public static int health = 3;
	
	public static void setHealth(int h ) { health = h; }
	public static int getHealth() { return health; }
    public static void modifyHealth(int h) { health += h; }
	
}