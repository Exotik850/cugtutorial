/**
 * Scenery objects are NON-TAKEABLE items meant to be added to rooms to match things
 * mentioned in a room description.  For example, if the current room is "CITY PARK"
 * and the room description says
 * 
 * "In the middle of the park stands a lonely tree.  Off in the distance you see
 *  icecaps on snowy mountain."
 *  
 * then you should probably make a Scenery object for the Tree and a Scenery object
 * for the mountains so that the viewer can "examine" them but not interact with
 * them or pick them up.
 */
public class Scenery extends Item {

	/**
	 * A Scenery object is an NON-TAKEABLE Item.  See description above.
	 */
	public Scenery(World world, String name, int weight, String description) {
		super(world, name, weight, Item.NOT_TAKEABLE, description);
	}

	@Override
	public void doUse() {
		World.print("The " + getName() + " just sits there.  Nothing happens.\n\n");
	}
}
