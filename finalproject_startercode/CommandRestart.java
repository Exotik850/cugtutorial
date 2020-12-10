public class CommandRestart extends Command {

	@Override
	public String[] getCommandWords() {
		return new String[]{"restart"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {
		World.print("\nAre you sure you want to restart (enter y to confirm)? ");
		if (World.IN.nextLine().equalsIgnoreCase("y")) {
			world.resetGame(); // removes all rooms and calls initializeGame()
			world.printWelcome();
		}
	}	
	
	@Override
	public String getHelpDescription() {
		return "";
	}
}
