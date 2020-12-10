public class CommandInventory extends Command {

	@Override
	public String[] getCommandWords() {
		return new String[]{"inventory", "i"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {
		World.print("You are carrying: " + world.getPlayer().getItemString() + ".\n\n");
	}	
	
	@Override
	public String getHelpDescription() {
		return "";
	}
}
