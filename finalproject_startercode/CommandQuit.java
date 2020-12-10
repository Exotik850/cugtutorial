public class CommandQuit extends Command {

	@Override
	public String[] getCommandWords() {
		return new String[] {"quit"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {
		World.print("\nAre you sure you want to quit (enter y to confirm)? ");
		if (World.IN.nextLine().equalsIgnoreCase("y")) {
			World.print("\nThank you for playing!  Good bye!\n\n");
			System.exit(0);
		}
	}

	@Override
	public String getHelpDescription() {
		return "";
	}

}
