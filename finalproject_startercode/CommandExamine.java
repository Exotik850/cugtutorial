public class CommandExamine extends Command {

	@Override
	public String[] getCommandWords() {
		return new String[]{"examine", "x", "inspect"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {
		if (params.length >= 1) {
			String secondWord = params[0];
			boolean playerHasItem = world.getPlayer().hasItem(secondWord);
			boolean roomHasItem =  world.getPlayer().getCurrentRoom().hasItem(secondWord); 
			if (playerHasItem) {
				Item item = world.getPlayer().getItem(secondWord);
				item.doExamine();
			} else if (roomHasItem) {
				Item item = world.getPlayer().getCurrentRoom().getItem(secondWord);
				item.doExamine();
			} else {
				World.print("You can't see any " + secondWord + "(s) here!\n\n");
			}
		} else {
			World.print("What do you want to examine?\n\n");
		}
	}	
	
	@Override
	public String getHelpDescription() {
		return "[item]";
	}

}
