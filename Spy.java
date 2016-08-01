package group_assignment;
/**
 * CS 141: Introduction to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;guez
 *
 * Group Project
 *
 * <>
 *
 * Ismail Abbas
 * 
 */


import java.io.Serializable;
 
/**
 * @author ieabbas
 *
 */
public class Spy implements Serializable {
	/**
	 * This object represents any of the ninjas that may attack the spy (the
	 * player).
	 */
	private Ninja theShredder;
 
	/**
	 * This field represents a UI to be used to show if the player has a bullet
	 * or not
	 */
	private UI display;
	
	/**
	 * This variable holds how many lives the spy has left.
	 */
	private int livesLeft;
 
	/**
	 * This variable represents if the spy has a bullet they can use
	 */
	private int bulletCount;
 
	/**
	 * This is the default constructor for the {@link Spy}, starting them out
	 * with 3 lives, and a single bullet
	 */
 
	public Spy() {
		livesLeft = 3;
		bulletCount = 1;
		
	}
		
	/**
	 * This method represents the action of shooting, dependent upon if 
	 * hasbullet() returns true to signify that the player has a bullet 
	 * to use.
	 * @param hasBullet
	 */
	public void shoot(boolean hasBullet) {
		if (hasBullet) {
		} else {
			display.noBulletsLeft();
		}
	}
 
	/**
	 * This method will return how many lives are remaining after being stabbed
	 * by a ninja.
	 * 
	 *
	 */
	public void fatality() {	
			livesLeft--;
	}
 
	/**
	 * This method returns how many lives the player has left
	 */
	public int getLives() {
		return livesLeft;
	}
	
	/**
	 * This method will check if the spy has ammo
	 */
	public boolean hasAmmo() {
		if (bulletCount != 0) {
			return true;
		}
		return false;
	}
 
	/**
	 * This method will apply the additional bullet. NOT CHECK FOR IF THE PLAYER
	 * HAS GOTTEN AN ADDITIONAL BULLET
	 */
	public int addBullet() {
		if (bulletCount != 1) {
			bulletCount = 1;
			return bulletCount;
		} else {
			return bulletCount;
		}
	}
 
	/**
	 * This method will tell you if the player has died, returning true if the
	 * player has no lives, and false otherwise. Will be called in the
	 * GameEngine to check for if the game is over
	 */
	public boolean gameOver() {
		if (getLives() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getBullet() {
		return bulletCount;
	}
}