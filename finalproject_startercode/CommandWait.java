public class CommandWait extends Command {

	@Override
	public String[] getCommandWords() {
		return new String[]{"wait", "rest"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {
		World.print("Time passes...\n");
	}	

	@Override
	public String getHelpDescription() {
		return "";
	}
}
