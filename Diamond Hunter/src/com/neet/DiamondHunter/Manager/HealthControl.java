/**
 * Name: Group 08
   Member:
   1: Ngô Lê Thiên Ân ITITDK21030
   2: Nguyễn Đình Thắng ITITIU21309
   3: Huỳnh Thanh Thủy ITITIU21325
   4: Cao Hoàng Khôi Nguyên ITITDK21048
   Purpose:
		// Control the health change state of main character
 */
package com.neet.DiamondHunter.Manager;

public class HealthControl {
	
	public static int health = 3;
	
	public static void setHealth(int h ) { health = h; }
	public static int getHealth() { return health; }
    public static void modifyHealth(int h) { health += h; }
	
}