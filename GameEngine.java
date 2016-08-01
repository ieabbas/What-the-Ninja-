package group_assignment;
/**
 * 
 */


import java.util.*;
import java.io.*;

/**
 * @author roberto_805
 *
 */
public class GameEngine implements Serializable {
//	private Ninja[] turtles; // This variable holds the ninja object
	private Spy Bond; // This variable holds the player object
	
	File file; // left
	private String choice;
	private boolean foundCase; // this variable is false as long as the brief
								// case isnt found.
	private Map grid;
	private UI display;
	public String debugMode;
	private int invincCounter;
	String skunk2;
	Map skunk3;
	Spy skunk4;

	/**
	 * This constructor is used to instantiate some of the objects used in the
	 * game.
	 * 
	 * @param n
	 *            ninja object
	 * @param o
	 *            Spy object
	 * @param p
	 *            Items object
	 */
	public GameEngine() {
		// turtles = new Ninja(n);
		display = new UI();
		choice = display.loadOption();
		if (load(choice) == false) {
			Bond = new Spy();
			
			foundCase = false;
			// turtles = grid.getNinjas();
			debugMode = display.debugMode();
			display.gameDescription();
		}
		// loading goes in else below but not sure how to load
		else {
			display.gameDescription();
			debugMode = skunk2;
			grid = (skunk3);
			Bond = skunk4;
			grid.displayGrid(debugMode);
			reposition();
		}
	}

	/**
	 * This will serve as an introduction to the game. It will determine the
	 * starting position of the briefcase, the position of the player, the
	 * position of the enemies and the position of the items.
	 * 
	 * @throws IOException
	 */
	public void start() {

		grid = new Map(debugMode);

		// should call a method from the UI to display a description
		// will start to ask for user input and move the Spy's position
		// accordingly
		reposition();
	}

	/**
	 * This method is called every time the spy gets stabbed to position the
	 * player at the initial spot.
	 * 
	 * @throws IOException
	 */
	public void reposition() {
		do {
			boolean chooseError = false;
			do {
				int turnChoice;
				chooseError = false;
				turnChoice = display.turnOption();
				if (turnChoice == 1) {
					move();
					grid.ninjaMove();
					grid.displayGrid(debugMode);
					boolean die = grid.ninjaEncounter();
					invincCounter = grid.getInvincCounter();
					if(die == true && invincCounter == 0){
						grid.killPlayer();
						Bond.fatality();
						display.die();
						grid.displayGrid(debugMode);
					}
				} else if (turnChoice == 2) {
					look();
					grid.ninjaMove();
					grid.displayGrid(debugMode);
					boolean die = grid.ninjaEncounter();
					if(die == true && invincCounter == 0){
						grid.killPlayer();
						Bond.fatality();
						display.die();
						grid.displayGrid(debugMode);
					}
					if(invincCounter > 0){
						invincCounter--;
					}
				} else if (turnChoice == 3) {
					shoot();
					grid.ninjaMove();
					
					boolean die = grid.ninjaEncounter();
					if(die == true && invincCounter == 0){
						grid.killPlayer();
						Bond.fatality();
						display.die();
						grid.displayGrid(debugMode);
					}
					if(invincCounter > 0){
						invincCounter--;
					}
				} else if (turnChoice == 4) {
					try {
						save(this.grid, this.Bond, this.debugMode);
						display.saveConfirmation();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else if(turnChoice == 5){
					if(display.saveOption().equalsIgnoreCase("y")){
						try {
							save(this.grid, this.Bond, this.debugMode);
							display.saveConfirmation();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					Main.endGame();
				} else {
					display.optionError();
					chooseError = true;
				}
				
			} while (chooseError == true);
			
//			if (grid.ninjaEncounter(turtles)) {
//				display.nextToNinja();
//				Bond.getStabbed();
//			}
			
			display.theStats(Bond, grid, grid.getPowerUp());
			checkWin(grid.getGrid());
			checkLose();
			if(invincCounter > 0){
				display.showInvinc(invincCounter);
			}
		} while (grid.getFound() == false);
		
	}

	public void checkLose() {
		if(Bond.getLives() < 1){
			display.lose();
			Main.endGame();
		}
		
	}

	public void look() {
		switch (display.lookDirection()) {
		case 1: {
			if (grid.look(1) == true) {
				display.ninjaAhead();
			} else {
				display.noNinja();
			}
			break;
		}
		case 2: {
			if (grid.look(2) == true) {
				display.ninjaAhead();
			} else {
				display.noNinja();
			}
			break;
		}
		case 3: {
			if (grid.look(3) == true) {
				display.ninjaAhead();
			} else {
				display.noNinja();
			}
			break;
		}
		case 4: {
			if (grid.look(4) == true) {
				display.ninjaAhead();
			} else {
				display.noNinja();
			}
			break;
		}
		default: {
			display.optionError();
			look(); // call the method again in this engine class
			break;
		}
		}
	}

	public void shoot() {
		int direction;
		int ninjasKilled = 0;
		direction = display.shootDirection();
		if (grid.hasBullet() == 1) {
			ninjasKilled = grid.shoot(direction);
			display.ninjasKilled(ninjasKilled);

		} else if (grid.hasBullet() == 0) {
			display.noBulletsLeft();
		}
	}

	public void move() {
		String option;
		boolean fail = false;
		boolean intFail;
		do {
			fail = false;
			option = display.moveChoice();// gets either w,a,s,d and sets it to
											// option
			if (grid.gridContentCheck(option, grid.getGrid()[0],
					grid.getGrid()[1]) == 0) {// col=getGrid()[0] and
												// row=getGrid()[1]
				try {
					switch (option.charAt(0)) {
					case 'w':
						grid.move("w");// moves player position
						break;
					case 'd':
						grid.move("d");// moves player position
						break;
					case 's':
						grid.move("s");// moves player position

						break;
					case 'a':
						grid.move("a");// moves player position
						break;
					default:
						display.invalid();
					}
					// System.out.println("No error");
				} catch (ArrayIndexOutOfBoundsException err) {
					display.outOfBoundsError();
					fail = true;
					/** Unneeded for the left option **/
					// grid.fixPlayer(option);
				}
			} else if (grid.gridContentCheck(option, grid.getGrid()[0],
					grid.getGrid()[1]) == 7) {
				display.nextToNinja();

			} else {
				display.roomError();
				fail = true;
			}
			if (option.equalsIgnoreCase("w") || option.equalsIgnoreCase("a")
					|| option.equalsIgnoreCase("s")
					|| option.equalsIgnoreCase("d")) {
				intFail = false;
			} else {
				intFail = true;
			}
		} while (fail == true || intFail == true);

	}

	/**
	 * This method will be called once the brief case has been found. It will
	 * basically serve as a method that displays a message letting the player
	 * know that he has found successfully completed the mission and it will
	 * cause the game to end.
	 */
	public void checkWin(int[] positionArray) {
		int pRow = positionArray[0];
		int pCol = positionArray[1];
		if (pRow == grid.briefR() && pCol == grid.briefC()) {
			display.winMessage();
			Main.endGame();
		}
	}

	/**
	 * This method will be called every turn. It determines if the ninja is in
	 * an adjacent square. If the ninja is in an adjacent square, the player
	 * will be stabbed, the live and true will be returned by the the
	 * method(which will determine if the player should be positioned at the
	 * initial spot as long as the lives left are not 0). If not, then the ninja
	 * will move in a random direction.
	 */
	public void checkForSpy() {

	}

	/**
	 * This method will be called at every player turn to get the player's
	 * action.
	 */
	public void spyCommand() {

	}

	/**
	 * This method will be in charge of saving the progress.
	 * 
	 * @throws IOException
	 */
	public void save(Map ww, Spy www, String wwww) throws IOException {
		file = new File("TeamGameFile.dat");
		file.createNewFile();
		FileOutputStream outstream = new FileOutputStream("TeamGameFile.dat");
		ObjectOutputStream objectOutputFile = new ObjectOutputStream(outstream);
		objectOutputFile.writeUTF(wwww);
		objectOutputFile.writeObject(ww);
		objectOutputFile.writeObject(www);
		objectOutputFile.close();
		grid.displayGrid(debugMode);
	}

	/**
	 * This method still needs work
	 * 
	 * @param yucca
	 * @return
	 */
	public boolean load(String yucca) {
		boolean loadTruth = false;
		if (yucca.equalsIgnoreCase("y")) {
			try {
				FileInputStream instream = new FileInputStream(
						"TeamGameFile.dat");
				ObjectInputStream objectOutputFile = new ObjectInputStream(
						instream);
				skunk2 = objectOutputFile.readUTF();
				skunk3 = (Map) objectOutputFile.readObject();
				skunk4 = (Spy) objectOutputFile.readObject();

				objectOutputFile.close();

			} catch (FileNotFoundException e) {
				display.noSaveGame();
				display.systemExit();
				Main.endGame();
			} catch (IOException f) {
				f.getMessage();
			} catch (ClassNotFoundException g) {
				g.getMessage();
			}
			loadTruth = true;
		}
		return loadTruth;
	}

	/**
	 * This method allows the main class to get the state of the case(whether or
	 * not it was found). This will stop the loop once it is true.
	 * 
	 * @return The brief case status
	 */
	public boolean getCaseStatus() {
		return foundCase;
	}

	public void startInvinc(){
		grid.getInvincCounter();
	}
}