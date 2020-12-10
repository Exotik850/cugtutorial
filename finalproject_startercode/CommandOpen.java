public class CommandOpen extends Command {

	@Override
	public String[] getCommandWords() {
		return new String[]{"open"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {

		if (params.length >= 1) {
			String secondWord = params[0];
			boolean playerHasItem = world.getPlayer().hasItem(secondWord);
			boolean roomHasItem =  world.getPlayer().getCurrentRoom().hasItem(secondWord); 
			if (playerHasItem || roomHasItem) {
				// We know the player or room has the item so grab it from whichever has the item
				Item item = playerHasItem ? world.getPlayer().getItem(secondWord) : world.getPlayer().getCurrentRoom().getItem(secondWord);
				if (item instanceof Closeable) {
					Closeable obj = (Closeable)item; // Casting is safe because we already checked that it's Openable
					obj.doOpen();
				} else {
					World.print("You can't open that!\n\n");
				}
			} else {
				World.print("There is no " + secondWord + " here!\n\n");
			}
		} else {
			World.print("What do you want to open?\n\n");
		}
	}
	
	@Override
	public String getHelpDescription() {
		return "[item]";
	}
}
