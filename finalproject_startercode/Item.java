/**
 *  Abstract class to handle everything an Item can do.
 *  Note: Since how you use an Item differs based on the Item, this class is declared abstract
 *  includes abstract method doUse().  Concrete subclasses must override the doUse() method and
 *  decide how to use that particular Item.
 */
public abstract class Item {

	/** Constants to make reading code easier **/
	public static final boolean TAKEABLE = true;
	public static final boolean NOT_TAKEABLE = false;

	/** Item attributes **/
	private String itemName;
	private boolean isTakeable;
	private int itemWeight;
	private String itemDescription;
	private World world;

	/** Constructs an Item using all attributes */
	public Item(World world, String name, int weight, boolean takeable, String description) {
		this.world = world;
		itemName = name;
		itemWeight = weight;
		isTakeable = takeable;
		itemDescription = description;
	}
	
	/** Constructs a TAKEABLE Item */
	public Item(World world, String name, int weight, String description) {
		this(world, name, weight, true, description);
	}

	/* Getters & Setters */
	public World getWorld() {
		return world;
	}
	
	public String getName() {
		return itemName;
	}

	public int getWeight() {
		return itemWeight;
	}

	public void setWeight(int newWeight) {
		itemWeight = newWeight;
	}

	public boolean isTakeable() {
		return isTakeable;
	}

	public void setTakeable(boolean takeable) {
		isTakeable = takeable;
	}

	public String getDescription() {
		return itemDescription;
	}

	public void setDescription(String newDescription) {
		itemDescription = newDescription;
	}

	/* Process common commands */

	public void doTake(Container container) {
		if (isTakeable) {
			getWorld().getPlayer().addItem(this);
			container.removeItem(this);
			World.print("Taken.\n\n");
		}
		else {
			World.print("You can't take that.\n\n");
		}
	}

	public void doDrop() {
		getWorld().getPlayer().removeItem(this);
		getWorld().getPlayer().getCurrentRoom().addItem(this);		
		World.print("Dropped.\n\n");
	}

	public void doExamine() {
		World.print(getDescription() + "\n\n");		
	}

	/** This method must be overridden by subclasses */
	public abstract void doUse();
}