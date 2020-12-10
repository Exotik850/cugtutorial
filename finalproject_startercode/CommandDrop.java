public class CommandDrop extends Command {

	@Override
	public String[] getCommandWords() {
		return new String[]{"drop"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {
		if (params.length >= 1) {
			String secondWord = params[0];
			if (world.getPlayer().hasItem(secondWord)) {
				Item item = world.getPlayer().getItem(secondWord);
				item.doDrop();
			} else {
				World.print("You don't have a " + secondWord + ".\n\n");
			}
		} else {
			World.print("What do you want to drop?\n\n");
		}
	}
	
	@Override
	public String getHelpDescription() {
		return "[item]";
	}

}
