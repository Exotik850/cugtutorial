public class CommandLook extends Command {

	@Override
	public String[] getCommandWords() {
		return new String[]{"look", "l"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {
		
		// "look" command
		if (params.length == 0) {
			World.print("\n" + world.getPlayer().getCurrentRoom().getDescription());

		// "look at [Item]" command
		} else if (params.length == 2 && params[0].equals("at")) {
			Command.doCommand("examine " + params[1], world);
			
		// error
		} else {
			World.print("I don't understand.\n\n");
		}
	}	

	@Override
	public String getHelpDescription() {
		return "";
	}

}
