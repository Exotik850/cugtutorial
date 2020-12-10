public class CommandTake extends Command {

	@Override
	public String[] getCommandWords() {
		return new String[]{"take", "get", "grab", "hold"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {
		if (params.length == 1) {
			String itemName = params[0];
			if (world.getPlayer().getCurrentRoom().hasItem(itemName)) {
				Item item = world.getPlayer().getCurrentRoom().getItem(itemName);
				item.doTake(world.getPlayer().getCurrentRoom());
			} else if (world.getPlayer().hasItem(itemName)) {
				World.print("You already have that!\n\n");
			} else {
				World.print("You can't see any " + itemName + " here.\n\n");
			}
		} 
		else if (params.length == 3) {
			if(!params[1].equals("from")){
				World.print("I don't understand.\n\n");
				return;
			} 

			String containerName = params[2];
			String itemName = params[0];
			Container container = (Container) (world.getPlayer().hasItem(containerName) ? world.getPlayer().getItem(containerName) : world.getPlayer().getCurrentRoom().getItem(containerName));

			if(container == null){
				World.print("You can't see any " + containerName + " here.\n\n");
				return;
			}

			if(!(container instanceof Container)){
				World.print("The " + containerName + " can't hold things!\n\n");
				return;
			}

			if(!(container.hasItem(itemName))){
				World.print("The " + containerName + " doesn't have a " + itemName);
				return;
			}
			Item item = container.getItem(itemName);
			container.doTake(item);
		}
		else {
			World.print("I don't understand.\n\n");
		}
	}

	@Override
	public String getHelpDescription() {
		return "[item] or [item] from [container]";
	}

}
