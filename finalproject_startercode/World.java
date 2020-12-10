 

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public abstract class World {

	/* Constants */
	/** Line word wrap length for console output */
	public static final int LINE_WRAP_LENGTH = 70;
	
	/** Reads user input */
	public static final Scanner IN = new Scanner(System.in);

	/* private World instance variables */	
	/** The main player of the game, which is you */
	private Player player;

	/** Keeps track of the Rooms in this World using <Key, Value>
	 *  pairs of <RoomName, RoomObject>
	 */
	private HashMap<String, Room> rooms;
	
	/** Whether or not the game is over */
	private boolean gameOver;

	/**
	 * Create the game and initialize its internal map.
	 */
	public World() {
		rooms = new HashMap<>();
		player = null;
		gameOver = false;
	}
	
	/**
	 * resets the game.
	 */
	public void resetGame() {
		rooms = new HashMap<>();
		player = null;
		gameOver = false;
		initializeGame();
	}
	
	/**
	 * Adds a room to the map of rooms
	 * @param room the room to add
	 * @throws IllegalArgumentException if a room with that name already exists or is null.
	 */
	public void addRoom(Room room) {
		if (room == null) {
			throw new IllegalArgumentException("Cannot add a null room");
		}
		if (rooms.containsKey(room.getName())) {
			throw new IllegalArgumentException("A room with that name already exists! Remove the old room first.");
		}
		rooms.put(room.getName(), room);
	}
	
	/**
	 * Removes the room with the given name from the map of rooms
	 * @param name the name of the room to remove
	 */
	public void removeRoom(String name) {
		rooms.remove(name);
	}

	/**
	 * Returns the room with the given name
	 * @param name the name of the room
	 * @return the room with the given name
	 */
	public Room getRoom(String name) {
		return rooms.get(name);
	}
	
	/**
	 * Returns a set of all the room names.
	 * @return a set of all the room names.
	 */
	public Set<String> getRooms() {
		return rooms.keySet();
	}

	/**
	 * Returns the player.
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Sets the player
	 * @param player the new player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	/**
	 * Returns true if the game is over or false otherwise
	 * @return true if the game is over or false otherwise
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * Set the value of game over
	 * @param gameOver the new value of game over
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	/**
	 * Main game loop. Keeps getting and processing the next command until
	 * the game is over.
	 */
	public void play() {
		initializeGame();
		printWelcome();
		// Enter the main command loop. Here we repeatedly read commands and
		// execute them until the game is over.
		do {
			System.out.print("> "); // print prompt
			String command = IN.nextLine();
			processCommand(command);
			onCommandFinished();
		} while (!isGameOver());
	}
	
	/**
	 * Given a command, process (that is: execute) the command.
	 */
	private void processCommand(String command) {
		if (Command.getCommandWord(command) != null) {
			if (!Command.hasValidCommandWord(command)) {
				print(invalidCommandMsg(command));
			}
			else {
				Command.doCommand(command, this);
			}
		} else {
			print(emptyCommandMsg());
		}
	}
	
	/**
	 * Returns the message to print when a command is all white space
	 * @return the message to print when a command is all white space
	 */
	public String emptyCommandMsg() {
		return "Please type in a command\n\n";
	}
	
	/**
	 * Returns the message to print telling the user the given command was invalid
	 * @param command the invalid command
	 * @return the message to print telling the user the command was invalid
	 */
	public String invalidCommandMsg(String command) {
		return "I don't know the command '" + Command.getCommandWord(command) + "'\n\n";
	}

	/**
	 * Helper method to print any String in a line-wrapped format.
	 * Prints the input String line-wrapped to a column width of LINE_WRAP_LENGTH,
	 * which is a constant defined at the top of this class.
	 * 
	 * Pseudocode and strategy:
	 * There are so many special cases, I could not have written this without planning it out.
	 * I decided to leave the comments here so you can see the strategy.
	 *  - Mr. Ferrante
	 * 
	 * while(length of str >= lengthLimit)
	 * 		Find the first occurrence of \n.
	 * 		If it's < lengthLimit then add substring(0, occurrence + 1) to output and reduce str by same amount.
	 * 		Else if there's a space at lengthLimit then add substring(0, lengthLimit) to output and
	 *  		reduce str by same amount
	 * 		Else find last occurrence of space within substring(0, lengthLimit)
	 * 			If no space anywhere then
	 * 				If there's a space at least somewhere in str, then add substring(0, firstSpace) to
	 * 					output and reduce str by same amount
	 * 				Else (no space anywhere)
	 * 					add rest of str to output and reduce by same amount
	 * 			Else (space somewhere within substring)
	 * 				add str.substring(0, index of last space) to output
	 * 				reduce str by same amount
	 * If there's anything left in str, add it.
	 */
	public static void print(String str) {
		String output = "";
		
		while (str.length() >= LINE_WRAP_LENGTH) {
			int lineBreakIndex = str.indexOf("\n");
			if (lineBreakIndex < LINE_WRAP_LENGTH && lineBreakIndex != -1) {
				output += str.substring(0, lineBreakIndex + 1);
				str = str.substring(lineBreakIndex + 1);
			}
			else if (str.charAt(LINE_WRAP_LENGTH) == ' ') {
				output += str.substring(0, LINE_WRAP_LENGTH);
				str = str.substring(LINE_WRAP_LENGTH + 1);
				if (str.length() > 0)
					output += "\n";
			}
			else {
				int lastSpaceIndex = str.substring(0, LINE_WRAP_LENGTH).lastIndexOf(" ");
				if (lastSpaceIndex == -1) {
					int firstSpaceIndex = str.indexOf(" ");
					if (firstSpaceIndex != -1) {
						output += str.substring(0, firstSpaceIndex);
						str = str.substring(firstSpaceIndex + 1);
						if (str.length() > 0)
							output += "\n";
					}
					else {
						output += str;
						str = "";
					}
				}
				else {
					output += str.substring(0, lastSpaceIndex);
					str = str.substring(lastSpaceIndex + 1);
					if (str.length() > 0)
						output += "\n";
				}
			}
		}
		if (str.length() > 0) {
			output += str;
		}
		System.out.print(output);
	}
	
	/* abstract methods */
	
	/**
	 * Print out the opening message for the player.
	 */
	public abstract void printWelcome();
	
	/**
	 * Called after each command has finished executing.
	 * This would be a good time to set game over to true if appropriate
	 * and to print out anything that should be printed before the
	 * command prompt.
	 */
	public abstract void onCommandFinished();
	
	/**
	 * Called when first creating the world and when restarting the game.
	 * Override this to initialize anything that needs to be initialized
	 * before each game.
	 * Examples:
	 * 1. Ask user any questions about game options
	 * 2. Create Rooms and link them together
	 * 3. Create player and place them in a room
	 */
	public abstract void initializeGame();
}