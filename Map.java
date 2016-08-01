package group_assignment;


import java.io.Serializable;
import java.util.Random;

/**
 * @author Carlos
 *
 */

public class Map implements Serializable {

	char[][] grid;
	char[][] displayGrid;
	@SuppressWarnings("unused")
	private int ninjaSet;
	private PowerUp p;
	// will determine the spawning position of the ninjas
	Random rnd;
	// will keep track of the player's position(row and col)
	private int row;
	private int col;
	private int r;
	private int c;
	private boolean caseFound;
	private boolean isNinja;
	private Ninja[] ninjas = new Ninja[6];
	private String debug;
	Location PL;
	private int bRow;
	private int bCol;
	private String debugMode;
	private int invincCounter;

	public Map(String debugMode) {
		rnd = new Random();
		grid = new char[9][9];
		p = new PowerUp();
		caseFound = false;
		this.debugMode = debugMode;

		if (debugMode.equals("d")) {
			displayGrid = new char[9][9];
			ninjaSet = 6;
			setRooms(debugMode);
			spawnNinjas();
			spawnPlayer();
			p.spawnPowerUps(grid);
			briefcaseSpawn();
			displayGrid(debugMode);
		} else if (debugMode.equals("n")) {
			// intially sets the number of ninjas in the map to 6
			ninjaSet = 6;
			setRooms(debugMode);
			spawnNinjas();
			spawnPlayer();
			p.spawnPowerUps(grid);
			briefcaseSpawn();
			displayGrid(debugMode);
		}
	}

	/**
	 * The method will set the rooms to a specific location everytime the game
	 * is initialized. Only executes once throughout the program.
	 */
	public void setRooms(String debugMode) {
		if (debugMode.equals("d")) {
			for (int i = 0; i < grid.length; ++i) {
				for (int j = 0; j < grid[i].length; ++j) {
					if ((i == 1 || i == 4 || i == 7)
							&& (j == 1 || j == 4 || j == 7)) {
						grid[i][j] = 'R';
					} else {
						grid[i][j] = ' ';
					}
				}
			}
		} else if (debugMode.equals("n")) {
			for (int i = 0; i < grid.length; ++i) {
				for (int j = 0; j < grid[i].length; ++j) {
					if ((i == 1 || i == 4 || i == 7)
							&& (j == 1 || j == 4 || j == 7)) {
						grid[i][j] = 'R';
					} else {
						grid[i][j] = ' ';
					}
				}
			}
		}
	}

	/**
	 * Spawns a player represented by the character 'P' and places it in the
	 * same location each time.
	 * 
	 */
	public void spawnPlayer() { 
		row = 8;
		col = 0;
		grid[row][col] = 'P';
	}
	
	public void killPlayer() {
		grid[row][col] = ' ';
		row = 8;
		col = 0;
		grid[row][col] = 'P';
	}

	// /**
	// * Spawns 6 ninjas every time the game is started. It places them randomly
	// * on the grid without overwriting existing entities.
	// */
	// public void spawnNinjas() {
	// do {
	// r = rnd.nextInt(9);
	// c = rnd.nextInt(9);
	// /*
	// * makes sure that ninjas will spawn at least 3 spaces away from spy
	// * and not inside a room
	// */
	// if (grid[r][c] == ' ' && grid[r][c] != 'R' &&
	// grid[r][c] != 'C' && r < 6 || c > 2) {
	// grid[r][c] = 'N';
	// --ninjaSet;
	// }
	// } while (ninjaSet > 0);
	// }

	public void briefcaseSpawn() {
		int guy=rnd.nextInt(9);
		switch(guy){
		case 0:
			bRow=7;
			bCol=1;
			
			break;
		case 1:
			bRow=7;
			bCol=4;
			
			break;
		case 2:
			bRow=7;
			bCol=7;
			
			break;
		case 3:
			bRow=4;
			bCol=1;
			
			break;
		case 4:
			bRow=4;
			bCol=4;
			
			break;
		case 5:
			bRow=4;
			bCol=7;
			
			break;
		case 6:
			bRow=1;
			bCol=1;
			
			break;
		case 7:
			bRow=1;
			bCol=4;
			
			break;
		case 8:
			bRow=1;
			bCol=7;
			
			break;
		}
 
	}

	/**
	 * Will display the current state of the game and the position of the
	 * player, and other entities.
	 */
	public void displayGrid(String debugMode) {
		boolean printIt = false;
		Location PL = findPLocation();
		int r = PL.getFirst();// row of player
		int c = PL.getSecond();// column of player

		if (debugMode.equals("d")) {
			for (int i = 0; i < grid.length; ++i) {
				for (int j = 0; j < grid[i].length; ++j) {
					if (grid[i][j] == 'C'
							&& p.displayBriefCaseLocation() == true) {
						System.out.print("[C]");
					} else {
						System.out.print("[" + grid[i][j] + "]");
					}
					// System.out.print("[" + grid[i][j] + "]");
				}
				System.out.println();
			}
		} else if (debugMode.equals("n")) {
			for (int row = 0; row < grid.length; ++row) {
				for (int col = 0; col < grid[row].length; ++col) {
					printIt = false;
					if (row == r) {
						int distanceToP = Math.abs(c - col);
						if (distanceToP <= 2) {
							printIt = true;
							if ((c != 8 && grid[r][c + 1] == 'R')|| (c!=8 && grid[r][c+1]=='C')) {
								printIt = false;
							}
							if ((c != 0 && grid[r][c - 1] == 'R')||(c!=0 && grid[r][c-1]=='C')) {
								printIt = false;
							}
						}
					}
					if (col == c) {
						int distanceToP = Math.abs(r - row);
						if (distanceToP <= 2) {
							printIt = true;
						}
						if ((r != 8 && grid[r + 1][c] == 'R') ||(r != 8 && grid[r + 1][c] == 'C')){
							printIt = false;
						}
						if ((r != 0 && grid[r - 1][c] == 'R')||(r != 0 && grid[r - 1][c] == 'C')) {
							printIt = false;
						}
					}
					if (grid[row][col] == 'P' || grid[row][col] == 'R'
							|| printIt == true) {
						System.out.print("[" + grid[row][col] + "]");
					} else if (grid[row][col] == 'C') {
						if (p.displayBriefCaseLocation() == true) {
							System.out.print("[C]");
						} else {
							System.out.print("[R]");
						}
					} else {

						System.out.print("[" + '*' + "]");
					}
				}
				System.out.println();
			}
		}
	}

	public boolean isNinja(Location N) {
		int row = N.getFirst();
		int col = N.getSecond();

		if (grid[row][col] == 'N') {
			return true;
		} else {
			return false;
		}
	}

	public boolean isPowerUp(Location PU) {
		int row = PU.getFirst();
		int col = PU.getSecond();

		if (grid[row][col] == 'I' || grid[row][col] == 'O'
				|| grid[row][col] == 'B') {
			return true;
		} else {
			return false;
		}
	}

	private Location findPLocation() {
		int i = 0;
		int j = 0;
		for (i = 0; i < grid.length; ++i) {

			for (j = 0; j < grid[i].length; ++j) {

				if (grid[i][j] == 'P') {

					return new Location(i, j);
				}
			}

		}
		return new Location(i, j);
	}

	/**
	 * Will move the player in the direction chosen by the user Replaces the
	 * previous occupied location with a blank space.
	 * 
	 * @param direction
	 *            receives an integer from the GameEngine class which retrieves
	 *            it from the UI class using the Scanner class.
	 */
	public void move(String direction) {
		int newrow = row;
		int newcol = col;
		int gridContent;
		int power = 0;
		gridContent = gridContentCheck(direction, col, row);
		if (gridContent == 0 || gridContent == 3 || gridContent == 4
				|| gridContent == 5) {
			switch (direction.charAt(0)) {
			case 'w':
				grid[row][col] = ' ';
				power = p.encounterPowerUp(grid, --newrow, newcol);
				grid[--row][col] = 'P';
				break;
			case 'd':
				grid[row][col] = ' ';
				power = p.encounterPowerUp(grid, newrow, ++newcol);
				grid[row][++col] = 'P';
				break;
			case 's':
				grid[row][col] = ' ';
				power = p.encounterPowerUp(grid, ++newrow, newcol);
				grid[++row][col] = 'P';
				break;
			case 'a':
				grid[row][col] = ' ';
				power = p.encounterPowerUp(grid, newrow, --newcol);
				grid[row][--col] = 'P';
				break;
			}
			
			if(invincCounter > 0){
				
				invincCounter--;
			}
			if(power==3){
				System.out.print("The briefcase is in the room located in:\n");
				System.out.println("Row:"+(bRow+1));
				System.out.println("Column:"+(bCol+1));
				grid[bRow][bCol]='C';
			}
			else if (power == 1){
				p.addBullet();
			}
			else if (power == 2){
				startInvic();
			}
		} else if (gridContent == 1) {
			if (direction == "s") {
				caseFound = true;
			} else if (direction == "d" || direction == "a" || direction == "w") {
				System.out.println("Can't enter room form that direction");
			}
		} else if (gridContent == 2) {
			switch (direction.charAt(0)) {
			case 'w':
				grid[row][col] = 'P';
				System.out.println("Can't enter the room from that direction.");
				break;
			case 'd':
				grid[row][col] = 'P';
				System.out.println("Can't enter the room from that direction.");
				break;
			case 's':
				grid[row][col] = ' ';
				row++;
				System.out.println("Entering room.");
				System.out.println("The room is empty.");
				grid[--row][col] = 'P';
				// this is where the player goes into the room
				break;
			case 'a':
				grid[row][col] = 'P';
				System.out.println("Can't enter the room from that direction.");
				break;

			}

		}
		
	}

	public void startInvic() {
		invincCounter = 5;
	}

	public int getInvincCounter() {
		return invincCounter;
	}
	
	/**
	 * This method check for whether the room the player is moving into has the
	 * briefcase or not. Currently used for fixing the room if the player moves
	 * into it incorrectly. Can be used to check for win condition?
	 * 
	 * @param direction
	 * @return
	 */
	public int gridContentCheck(String direction, int x, int y) {
		int type = 0;
		// int y = row;
		// int x = col;

		if (direction == "w" && y != 0) {
			if(grid[y - 1][x] =='C'){
				type=1;
			}else if (grid[y - 1][x] == 'R') {
				type = 2;
			} else if (grid[y - 1][x] == 'B') {
				type = 3;
			} else if (grid[y - 1][x] == 'O') {
				type = 4;
			} else if (grid[y - 1][x] == 'I') {
				type = 5;
			} else if (grid[y - 1][x] == 'P') {
				type = 6;
			} else if (grid[y - 1][x] == 'N') {
				type = 7;
			} else {
				type = 0;
			}
		} else if (direction == "d" && x != 8) {
			if(grid[y][x + 1] =='C'){
				type=1;
			} else if (grid[y][x + 1] == 'R') {
				type = 2;
			} else if (grid[y][x + 1] == 'B') {
				type = 3;
			} else if (grid[y][x + 1] == 'O') {
				type = 4;
			} else if (grid[y][x + 1] == 'I') {
				type = 5;
			} else if (grid[y][x + 1] == 'P') {
				type = 6;
			} else if (grid[y][x + 1] == 'N') {
				type = 7;
			} else {
				type = 0;
			}
		} else if (direction == "s" && y != 8) {
			if(grid[y + 1][x] =='C'){
				type=1;
			}else if (grid[y + 1][x] == 'R') {
				type = 2;
			} else if (grid[y + 1][x] == 'B') {
				type = 3;
			} else if (grid[y + 1][x] == 'O') {
				type = 4;
			} else if (grid[y + 1][x] == 'I') {
				type = 5;
			} else if (grid[y + 1][x] == 'P') {
				type = 6;
			} else if (grid[y + 1][x] == 'N') {
				type = 7;
			} else {
				type = 0;
			}
		} else if (direction == "a" && x != 0) {
			if(grid[y][x - 1]=='C'){
				type=1;
			}else if (grid[y][x - 1] == 'R') {
				type = 2;
			} else if (grid[y][x - 1] == 'B') {
				type = 3;
			} else if (grid[y][x - 1] == 'O') {
				type = 4;
			} else if (grid[y][x - 1] == 'I') {
				type = 5;
			} else if (grid[y][x - 1] == 'P') {
				type = 6;
			} else if (grid[y][x - 1] == 'N') {
				type = 7;
			} else {
				type = 0;
			}
		}

		return type;
	}

	/**
	 * This method will return how many ninjas there are left on the map
	 */
	public int ninjaCount() {
		return ninjaSet;
	}

	/**
	 * Checks to see whether the player is attempting to enter a room and, if
	 * they are doing so, then checks to see if they are entering the correct
	 * side (top).
	 * 
	 * @param direction
	 */

	/**
	 * Method checks to see if player can enter the room. It will return true if
	 * door is accessed from the north side
	 * 
	 * @return
	 */
	public boolean northSide() {
		return false;
	}

	/**
	 * will check if the player and the ninja are adjacent to each other, and
	 * will return the Boolean depending on the position of ninja relative to
	 * player
	 * 
	 * @return
	 */
	public boolean adjacent() {
		return false;
	}

	/**
	 * Method will determine if an encounter is nearby This method serves as the
	 * flashlight to see 2 squares ahead
	 * 
	 * @return
	 */
	/**
	 * Method will determine if an encounter is nearby This method lets you see
	 * if there is a ninja within the row/col.
	 * 
	 * @return
	 */
	public boolean ninjaLook() {
		return false;
	}

	/**
	 * This method serves as what happens when the ninja encounters the player
	 * within one square.
	 */
	public boolean ninjaEncounter() {
		for (int i = 0; i < ninjas.length; i++) {
			int pos[] = ninjas[i].getPosition();
			if (pos[0] == col && ninjas[i].getAlive() == true) {
				if (pos[1] == row + 1
						|| pos[1] == row - 1) {
					return true;
				}
			} else if (pos[1] == row && ninjas[i].getAlive() == true) {
				if (pos[0] == col + 1
						|| pos[0] == col - 1) {
					return true;
				} 
			}
		}
		return false;
	}

	/**
	 * This method is for checking if the position is next to the spy
	 */
//	public boolean SpyNinjaCheck(int i) {
//		int pos[] = ninjas[i].getPosition();
//		if (pos[0] == col) {
//			if (pos[1] == row + 1
//					|| pos[1] == row - 1) {
//				return true;
//			}
//		} else if (pos[1] == row) {
//			if (pos[0] == col + 1
//					|| pos[0] == col - 1) {
//				return true;
//			} 
//		}
//		return false;
//	}

	/**
	 * The next two methods generate the random direction a ninja will move and
	 * then moves them. 0 is up, 1 is right, 2 is down, 3 is left
	 * 
	 * @return
	 */
	public String ninjaDirection(Ninja ninja) {
		Random direction = new Random();
		String letterDir = null;
		int dir; 
		boolean fail;
		do{
			fail = false;
			dir = direction.nextInt(4);
			switch (dir) {
			case 0:
				letterDir = "w";
				if(ninja.getPosition()[1] == 0){
					fail = true;
				}
				break;
			case 1:
				letterDir = "d";
				if(ninja.getPosition()[0] == 8){
					fail = true;
				}
				break;
			case 2:
				letterDir = "s";
				if(ninja.getPosition()[1] == 8){
					fail = true;
				}
				break;
			case 3:
				letterDir = "a";
				if(ninja.getPosition()[0] == 0){
					fail = true;
				}
			}
		}while (fail == true);
		return letterDir;
	}

	public void ninjaMove() {
		boolean fail = false;
		boolean cf = false;
		String direction;
		int content;
		do {
			for (int i = 0; i < ninjas.length; i++) {
				do {
					cf = false;
					direction = ninjaDirection(ninjas[i]);
					content = gridContentCheck(direction,
							ninjas[i].getPosition()[0],
							ninjas[i].getPosition()[1]);
					if (content != 0) {
						cf = true;
					}
				} while (cf == true);
				if(ninjas[i].getAlive() == true){
				try {
					// direction = ninjaDirection();
					// content = gridContentCheck(direction);
					fail = false;
					int[] pos = ninjas[i].getPosition();
					if (content == 0) {
						switch (direction.charAt(0)) {
						case 'w':
							grid[pos[1]][pos[0]] = ' ';
							grid[--pos[1]][pos[0]] = 'N';
							break;
						case 'd':
							grid[pos[1]][pos[0]] = ' ';
							grid[pos[1]][++pos[0]] = 'N';
							break;
						case 's':
							grid[pos[1]][pos[0]] = ' ';
							grid[++pos[1]][pos[0]] = 'N';
							break;
						case 'a':
							grid[pos[1]][pos[0]] = ' ';
							grid[pos[1]][--pos[0]] = 'N';
							break;
						}
						fail = false;
						ninjas[i].setPosition(pos);
					} else {
						fail = true;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					fail = true;
					System.out.println("Bad Ninja");
				}	
				}
			}
		} while (fail == true);
	}

	/**
	 * This method spawns each ninja and assigns their positions into the Ninja
	 * class
	 */
	public void spawnNinjas() {

		for (int i = 0; i < ninjas.length; i++) {
			ninjas[i] = new Ninja();
			ninjas[i].setPosition(setNinjaPosition());
		}
	}

	/**
	 * This method generates the ninja's positions.
	 * 
	 * @return
	 */
	private int[] setNinjaPosition() {
		int[] position = { 0, 0 };
		do {
			r = rnd.nextInt(9);
			c = rnd.nextInt(9);
			/*
			 * makes sure that ninjas will spawn at least 3 spaces away from spy
			 * and not inside a room
			 */
			if (grid[r][c] == ' ' && (r < 6 || c > 2)) {
				grid[r][c] = 'N';
			}
		} while (grid[r][c] != 'N');
		position[1] = r;
		position[0] = c;
		return position;
	}

	public boolean getFound() {
		return caseFound;
	}

	public int[] getGrid() {
		int[] grid = { col, row };
		return grid;
	}
	
	/**
	 * This method is used to get the array of ninjas, so the 
	 * game engine can use it in the reposition method.
	 */
	public Ninja[] getNinjas() {
		return ninjas;
	}
	
	public int shoot(int direction) {
		int deadCounter = 0;
		int[] pos;
		int minRow = 8;
		int ninjaIndex = 0;
		int maxRow = 0;
		int minCol = 8;
		int maxCol = 0;
		p.shoot();
		boolean ninjaShoot = false;
		switch(direction) {
		case 1:		//up
			for(int i = 0; i < ninjas.length; i++){
				pos = ninjas[i].getPosition();
				if(pos[0] == col && pos[1] < row){
					if (pos[1] > maxRow){
						maxRow = pos[1];
						ninjaIndex = i;
						ninjaShoot = true;
						deadCounter++;
					}
					//ninjas[i].getShot();
					//grid[pos[1]][pos[0]] = ' ';
					//deadCounter++;
				}
			}
			
			ninjas[ninjaIndex].getShot();
			grid[maxRow][col] = ' ';
			
			
			System.out.println();
			displayGrid(debugMode);
			System.out.println();
			
		break;
			
		case 2: 	//right
			for(int i = 0; i < ninjas.length; i++){
				pos = ninjas[i].getPosition();
				if(pos[0] > col && pos[1] == row){
					if (pos[0] < minCol){
						minCol = pos[0];
						ninjaIndex = i;
						ninjaShoot = true;
						deadCounter++;
					}
					//ninjas[i].getShot();
					//grid[pos[1]][pos[0]] = ' ';
					//deadCounter++;
				}
			}
			ninjas[ninjaIndex].getShot();
			grid[row][minCol] = ' ';
			
			System.out.println();
			displayGrid(debugMode);
			System.out.println();
			
			
		break;
		case 3:		//down
			for(int i = 0; i < ninjas.length; i++){
				pos = ninjas[i].getPosition();
				if(pos[0] == col && pos[1] > row){
					if (pos[1] < minRow){
						minRow = pos[1];
						ninjaIndex = i;
						ninjaShoot = true;
						deadCounter++;
					}
					//ninjas[i].getShot();
					//grid[pos[1]][pos[0]] = ' ';
					//deadCounter++;
				}
			}
			ninjas[ninjaIndex].getShot();
			grid[minRow][col] = ' ';
			
			System.out.println();
			displayGrid(debugMode);
			System.out.println();
		
			
		break;
		case 4:		//left
			for(int i = 0; i < ninjas.length; i++){
				pos = ninjas[i].getPosition();
				if(pos[0] < col && pos[1] == row){
						if (pos[0] > maxCol){
							maxCol = pos[0];
							ninjaIndex = i;
							ninjaShoot = true;
							deadCounter++;
							;
						}
					//ninjas[i].getShot();
					//grid[pos[1]][pos[0]] = ' ';
					//deadCounter++;
				}
			}
			ninjas[ninjaIndex].getShot();
			grid[row][maxCol] = ' ';
			
			System.out.println();
			displayGrid(debugMode);
			System.out.println();
			
			
			break;
	}
	ninjaSet = ninjaSet - deadCounter;
	return deadCounter;	
}	
	
	
	public int briefC(){
		return bCol;
	}
	public int briefR(){
		return bRow;
	}
	
	public boolean look(int direction){
		PL = findPLocation();
		row = PL.getFirst();
		col = PL.getSecond();
		isNinja = false;
 
		switch(direction){
		//look up--- same column 
		case 1:{
			for(int i = 0; i < row; i++){
				if(grid[i][col] == 'N'){
					grid[i][col] = 'N';
					isNinja = true;
				}
			}
			break;
		}
		case 2:{
			//looking right
			for(int j = 8; j > col; j--){
				if(grid[row][j] == 'N'){
					grid[row][j] = 'N';	
					isNinja = true;
				}
			}
			break;
		}
		case 3:{
			//looking down
			for(int i = 8; i > row; i--){
				if(grid[i][col] == 'N'){
					isNinja = true;
					grid[i][col] = 'N';			
				}
			}
			break;
		}
		case 4:{
			//looking left
			for(int j = 0; j < col; j++){
				if(grid[row][j] == 'N'){
					grid[row][j] = 'N';
					isNinja = true;
				}
			}
			break;
		}
		}
		return isNinja;
	}
	
	public int hasBullet(){
		int bullet = p.hasBullet();
		return bullet;
	}

	public PowerUp getPowerUp() {
		
		return p;
	}
}