public class CommandUse extends Command {

	@Override
	public String[] getCommandWords() {
		return new String[]{"use"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {

		if (params.length >= 1) {
			String secondWord = params[0];
			boolean playerHasItem = world.getPlayer().hasItem(secondWord);
			boolean roomHasItem =  world.getPlayer().getCurrentRoom().hasItem(secondWord); 
			if (playerHasItem) {
				Item item = world.getPlayer().getItem(secondWord);
				item.doUse();
			} else if (roomHasItem) {
				Item item = world.getPlayer().getCurrentRoom().getItem(secondWord);
				item.doUse();
			} else {
				World.print("You don't have a " + secondWord + ".\n\n");
			}
		} else {
			World.print("What do you want to use?\n\n");
		}
	}
	
	@Override
	public String getHelpDescription() {
		return "[item]";
	}
}
