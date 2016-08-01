package group_assignment;
/**
 * 
 */



import java.util.Scanner;

/**
 * @author ieabbas
 *
 */
import java.util.Scanner;

/**
 * @author ieabbas
 *
 */
public class UI {

	/**
	 * This is the Scanner the player will interact with.
	 */
	private Scanner kb = new Scanner(System.in);

	/**
	 * This field holds whether the game is to be run in debug mode or normal
	 * mode.
	 */
	private String debugOrNot;

	/**
	 * This field holds which direction the spy is moving in.
	 */
	private String moveSelection;

	private int lookDir;
	/**
	 * This method in theory should display all the things important to the game
	 * that the player needs to know at the end of each turn.
	 * 
	 * THIS CLASS NEEDS A LITTLE MORE STILL - ISMAIL
	 */
	public void theStats(Spy bond, Map grid, PowerUp p) {
		if(p != null){
			if (bond.getLives() != 0) {
	
			System.out.print("\nThe spy has " + bond.getLives()
					+ " lives remaining.");
		} else {
			System.out
					.print("\nThe spy has no lives remaining! Use your last well!");
		}
		if (p.hasBullet() == 1) {
			System.out.print("\nThe spy has " + p.hasBullet()
					+ " bullet remaining.");
		} else {
			System.out.print("\nThe spy has no bullets remaining!");
		}
		if (grid.ninjaCount() != 0) {
			System.out.println("\nThere are " + grid.ninjaCount()
					+ " ninjas remaining!");
		} else {
			System.out.println("No ninjas remain! Happy briefcase hunting!");
		}
		}
	}

	/**
	 * This method determines in which direction the spy moves. As per PC game
	 * conventions, W is forward, A is left, D is right, and S is backwards.
	 * 
	 * @return {@link #moveSelection}
	 */
	public String moveChoice() {
		System.out.println("\n\nUse the W, A, S, or D to move the player.");
		moveSelection = kb.nextLine();
		return moveSelection;
	}

	/**
	 * This method will display an error message if the user does not enter
	 * valid input
	 */
	public void invalid() {
		System.out.println("Please enter a valid input");
	}

	/**
	 * This method returns whether the player wants to run the game in debug
	 * mode or not. "d" will begin the program in debug, while "n" will start
	 * the game normally.
	 * 
	 * @return {@link #debugOrNot}
	 */
	public String debugMode() {
		System.out.print("\nDebug mode or normal game? \n(type \"d\" or \"n\""
				+ "and press enter.)\n");
		debugOrNot = kb.nextLine();
		return debugOrNot;
	}

	/**
	 * This method will display the winning message once the briefcase is found.
	 */
	public void winMessage() {
		System.out.print("\n\nCongratulations! You've won the game! :D");
		System.exit(0);
	}

	/**
	 * This method tells the player that there's been an error
	 */
	public void outOfBoundsError() {
		System.out.println("\nYou moved out of bounds, choose again.");
	}

	public void roomError() {
		System.out.print("\nError");
	}

	public void youFoundNada() {
		System.out.print("\nSorry! The room was empty!");
	}

	public void gameDescription() {
		System.out
				.println("Objective: Retrieve the brief case from one of the rooms.");
		System.out
				.print("Be careful, there are ninjas patrolling around the rooms. \n");
	}

	public void nextToNinja() {
		System.out.print("\nA ninja got the jump on you! Being stabbed\n +"
				+ "to death is a brutal way to go man...");
	}

	public void noEnter() {
		System.out.println("\nCan't enter from that direction.");
	}

	public String saveOption() {
		System.out.print("\nWould you like to save your progress(y or n)?");
		String saveInput = kb.nextLine();
		return saveInput;

	}

	public String loadOption() {
		System.out.print("Would you like to load previous game(y or n)?\n");
		String saveInput2 = kb.nextLine();
		return saveInput2;
	}

	public void noBulletsLeft() {
		System.out.println("\nYou have no bullets remaining!");
	}

	public int turnOption() {
		System.out.println("\nWhat would you like to do this turn?\n"
				+ " 1. Move\n 2. Look\n 3. Shoot\n 4. Save\n 5. Exit");
		int choice = kb.nextInt();
		kb.nextLine();
		return choice;
	}

	public void optionError() {
		System.out.println("\nThat is not a valid option, choose again.");

	}

	public int shootDirection() {
		int choice = 0;
		do{
			System.out.println("Which direction would you like to shoot?\n" +
					" 1. Up\n 2. Right \n 3. Down\n 4. Left");
			choice = kb.nextInt();
			if (choice == 0 || choice > 4){
				optionError();
			}
		} while (choice == 0 || choice > 4);
			return choice;
	}

	public void ninjasKilled(int ninjasKilled) {
		if (ninjasKilled > 0){
			System.out.println("Concratulations! You killed a ninja!");
		}
		else{
			System.out.println("Sorry! You missed and killed no ninjas.");
		}
	}
	
	public void saveConfirmation(){
		System.out.println("The game has been saved.");
	}
	
	public int lookDirection(){
		System.out.println("Which direction do you want to look? Enter a number from 1-4." +
				"\n1. Up \n2. Right \n3. Down \n4. Left");
		lookDir = kb.nextInt();
		return lookDir;
	}
	public void ninjaAhead(){
		System.out.println("There is a ninja ahead!");
	}
	public void noNinja(){
		System.out.println("NO ninja ahead!");
	}

	public void missed() {
		System.out.println("You missed!");
		
	}

	public void wallShot() {
		System.out.println("You hit a wall!");
		
	}

	public void die() {
		System.out.println("You've been stabbed! You lost a life.");
		
	}

	public void showInvinc(int invincCounter) {
		System.out.println("You have " + invincCounter + " turns of invincibility remaining.");
		
	}

	public void lose() {
		System.out.println("You have lost. So sad.");
	}
	
	public void noSaveGame() {
		System.out.print("\nNo saved game found...WHY ARE YOU TRYING TO LOAD A GAME WITH NO SAVE ._.");
	}
	
	public void systemExit() {
		System.out.print("\n...the game has ended.");
	}

	
}