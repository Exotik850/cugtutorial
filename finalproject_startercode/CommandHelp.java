public class CommandHelp extends Command {

	@Override
	public String[] getCommandWords() {
		return new String[]{"help"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {
		if (params.length > 0) {
			Command handler = Command.getCommandHandlerForWord(params[0]);
			if (handler == null || handler.getHelpDescription() == null) {
				World.print("I have nothing to say about " + params[0]);
			} else {
				World.print(Command.getHelpDescription(handler) + "\n\n");
			}
		} else {
			World.print("This is a text adventure game.  Your job is to explore your surroundings, " +
				        "collect and use items, and discover how to win the game.\n\n");
			World.print("Standard Commands:\n\n");
			for(Command handler : getAllCommandHandlers()) {
				if (handler.getHelpDescription() != null) {
					World.print(Command.getHelpDescription(handler) + "\n");
				}
			}
			World.print("Could there also be secret commands? Experiment to find out...\n");
			World.print("\n");			
		}
	}

	@Override
	public String getHelpDescription() {
		return "[command (optional)]";
	}
}
