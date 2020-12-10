public class CommandPut extends Command {
    @Override
    public String[] getCommandWords() {
        return new String[]{"put", "set", "place"};
    }

    @Override
    public void doCommand(String cmd, String[] params, World world) {
        if(params.length != 3 && !params[1].equals("in")){
            World.print("I don't understand.\n\n");
            return;
        }

        String itemName = params[0];
        String containerName = params[2];
        Room r = world.getPlayer().getCurrentRoom();
        Item item = world.getPlayer().hasItem(itemName) ? world.getPlayer().getItem(itemName) : r.getItem(itemName);
        Container container = (Container)(world.getPlayer().hasItem(containerName) ? world.getPlayer().getItem(containerName) : r.getItem(containerName));        

        if (!world.getPlayer().hasItem(itemName) && !r.hasItem(itemName)) {
            World.print("You can't see any " + itemName + " here! \n\n");
            return;
        }
    
        if (!world.getPlayer().hasItem(containerName) && !r.hasItem(containerName)) {
            World.print("You can't see any " + containerName + " here! \n\n");
            return;
        }

        if (!(container instanceof Container)) {
            World.print("The " + containerName + " can't hold things. \n\n");
            return;
        }

        if (itemName.equals(containerName)) {
            World.print("You can't put the " + containerName + " into itself! \n\n");
            return;
        }

        if (world.getPlayer().hasItem(item)) {
            container.doPut(item, world.getPlayer());
        } else {
            container.doPut(item, r);
        }

    }

    @Override
    public String getHelpDescription() {
        return "put [item] into [container]";
    }
}