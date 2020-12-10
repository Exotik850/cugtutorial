public class CommandGo extends Command {

	@Override
	public String[] getCommandWords() {
		return new String[]{"go", "move", "travel", "walk"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {
		if (params.length == 0) {
			// if there is no second word, we don't know where to go...
			World.print("Go where?\n");
		} else {
			Player player = world.getPlayer();
			String direction = params[0];

			// Try to leave current room.
			Room nextRoom = player.getCurrentRoom().nextRoom(direction);
			if (nextRoom == null) {
				World.print("You can't go that way!\n\n");
			} else if (nextRoom.isLocked()) {
				World.print("Sorry.  It's locked.\n\n");
			} else {
				player.getCurrentRoom().doLeave();
				player.setCurrentRoom(nextRoom);
				World.print("\n" + player.getCurrentRoom().getDescription());
				player.getCurrentRoom().doEnter();
			}
		}
	}	
	
	@Override
	public String getHelpDescription() {
		return "[direction]";
	}

}
