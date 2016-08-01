package group_assignment;


import java.io.Serializable;
import java.util.Random;

public class PowerUp implements Serializable {

	Random rnd = new Random();

	private int bullet = 1;
	private int invincTurns = 0;
	private boolean visible;
	private boolean invincible = false;
	private boolean showBriefCase = false;

	public void addBullet() { // method that increments bullet count
		bullet = 1;
	}

	/**
	 * This method returns if the player has a bullet or not.
	 */
	public int hasBullet() {
		return bullet;
	}

	public void shoot() {
		bullet = 0;
	}

	/**
	 * This method will return that the player is invincible (more than 0 turns)
	 * for the Spy's fatality(), which is dependent upon the Ninja's
	 * stabPlayer() to be false, meaning the spy can be stabbed.
	 * 
	 * @return whether the player is invincible
	 */

	public boolean invincibility() { // call this method to see if you are
										// invincible from stabbing
		if (invincible == true) { // return true if you are invincible from
									// stabbing.

			if (invincTurns <= 5) {

				return true;

			} else {

				return false;

			}
		} else { // otherwise you are no longer invincible
			return false;
		}
	}

	public void invincibilityIncreaseTurns() { // call this method after each
												// move after picking up 'I'
		if (invincible == true) {
			invincTurns++;
		}
		if (invincTurns >= 5) {
			invincible = false;
			invincTurns = 0;
			System.out.println("\nNo longer invincible");
		}
	}

	/**
	 * This method increases how many turns have been used with invincibility
	 */

	public int getInvincibilityStatus() {
		return invincTurns;
	}

	public void spawnPowerUps(char[][] grid) {
		int r, c;
		boolean done = false;

		do {
			r = rnd.nextInt(9);
			c = rnd.nextInt(9);
			if (grid[r][c] == ' ') { // B represents additional bullet power up
				grid[r][c] = 'B';
				done = true;
			}
		} while (done == false);

		done = false;

		do {
			r = rnd.nextInt(9);
			c = rnd.nextInt(9);
			if (grid[r][c] == ' ') { // I represents invincibility power up
				grid[r][c] = 'I';
				done = true;
			}
		} while (done == false);

		done = false;

		do {
			r = rnd.nextInt(9);
			c = rnd.nextInt(9);
			if (grid[r][c] == ' ') { // O represents radar power up
				grid[r][c] = 'O';
				done = true;
			}
		} while (done == false);
	}

	// return 1 if it encounters additional bullet power up
	// 2 if invincibility
	// 3 if radar
	// 0 if neither of the three.
	public int encounterPowerUp(char[][] grid, int r, int c) {

		if (grid[r][c] == 'B') {
			if (hasBullet() == 1) {
				System.out.println("You still have your initial bullet!");
			}
			// UI.showmessage(You still have your initial bullet! Power up does
			// nothing);
			return 1;
		} else if (grid[r][c] == 'I') {
			// invincible = true;
			return 2;
		} else if (grid[r][c] == 'O') {
			System.out.println("\nEncountered Radar");
			showBriefCase = true;
			return 3;
		} else {
			return 0;
		}
	}

	public boolean displayBriefCaseLocation() {
		return showBriefCase;
	}

	public void radar() {
	}

	// create a method that determines location of briefcase
	// either letter or method location
	public Location briefcaseLocation(char[][] grid) {
		int r = 0;
		int c = 0;
		for (r = 0; r < 9; r++) {
			for (c = 0; c < 9; c++) {
				if (grid[r][c] == 'R') // && how to determine which room the
										// briefcase is in
					return new Location(r, c);
			}
		}
		return new Location(r, c);
	}
}