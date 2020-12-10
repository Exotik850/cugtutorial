/**
 * A UselessItem is meant for two purposes:
 * 
 * (1) You can use UselessItems for things the player (or any Contaier) can hold
 *     but have no special actions or properties other than a description.
 *     
 * (2) You can copy UseLessItem to a new class and use it to make a useful item :)
 */
public class UselessItem extends Item {

	public UselessItem(World world, String name, int weight, boolean takeable, String description) {
		super(world, name, weight, takeable, description);
	}

	@Override
	public void doUse() {
		World.print("You attempt to use the " + getName() + " but nothing happens.\n\n");
	}

}
