import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Container extends Item {

	/** Keeps track of the Items in this Container. */
	HashMap<String, Item> items;

	public Container(World world, String name, String description) {
		super(world, name, 0, Item.NOT_TAKEABLE, description);
		items = new HashMap<String, Item>();
	}

	public Container(World world, String name, int weight, boolean takeable, String description) {
		super(world, name, weight, takeable, description);
		items = new HashMap<String, Item>();
	}

	/**
	 * Determines whether an item can be added to this Container.
	 * By default, any item can be added.
	 * @param item Item to be checked
	 * @return Whether or not the given Item can be added to this Container
	 */
	public boolean canAddItem(Item item) {
		return true;
	}

	/**
	 * Adds an Item to this container's item list.
	 * @param item Item to be added to this Container
	 */
	public void addItem(Item item) {
		items.put(item.getName(), item);
	}

	/**
	 * Determines whether an item can be removed from this container.
	 * By default, any item can be removed.
	 * @param item Item to be checked
	 * @return Whether or not the given Item can be removed from this Container
	 */
	public boolean canRemoveItem(Item item) {
		return true;
	}	

	/**
	 * Removes an item from this container
	 * @param itemName Name of the item to be removed from this Container
	 * @return The Item removed or null if the Item was not found in this Container
	 */
	public Item removeItem(String itemName) {
		return items.remove(itemName);
	}

	/**
	 * Removes an item from this container
	 * @param item Item to be removed from this Container
	 * @return The Item removed or null if the Item was not found in this Container
	 */
	public Item removeItem(Item item) {
		return items.remove(item.getName());
	}

	/**
	 * Returns an Item from this container given its name String
	 * @param itemName Name of the Item to get
	 * @return Item whose name is given in the parameter
	 */
	public Item getItem(String itemName) {
		return items.get(itemName);
	}

	/**
	 * Determines whether this container has a given Item
	 * @param item Item to be checked
	 * @return Whether or not this Container contains the given Item
	 */
	public boolean hasItem(Item item) {
		return items.containsKey(item.getName());
	}

	/**
	 * Determines whether this container has a given Item
	 * @param itemName String name of the Item to be checked.
	 * @return Whether or not this Container contains the given Item
	 */
	public boolean hasItem(String itemName) {
		return items.containsKey(itemName);
	}

	/**
	 * Returns the number of Items in this container
	 * @return number of Items in this container.
	 */
	public int numItems() {
		return items.size();
	}

	/**
	 * Returns an ArrayList of Items in this Container
	 * @return ArrayList of Items in this Container
	 */
	public ArrayList<Item> getItems() {
		ArrayList<Item> list = new ArrayList<Item>();
		Iterator<Item> iter = items.values().iterator();
		while (iter.hasNext()) {
			list.add(iter.next());
		}
		return list;
	}

	/**
	 * Return a String describing the Container's non-scenery items
	 * @return a String describing the Container's non-scenery items
	 */
	public String getItemString() {
		Collection<Item> mapValues = items.values();
		Iterator<Item> roomItemIterator = mapValues.iterator();

		// Make a list of non-scenery items
		List<Item> roomItems = new ArrayList<Item>();

		while (roomItemIterator.hasNext()) {
			Item item = roomItemIterator.next();
			if (!(item instanceof Scenery)) {
				roomItems.add(item);
			}
		}

		String items = "";

		// Generates a String of non-scenery items in this room.
		for (int i = 0; i < roomItems.size(); i++) {
			// If more than 2 item, print "and" before last item
			if (i != 0 && i == roomItems.size() - 1)
				items += " and";
			// If more than 2 items, separate items by a comma
			else if (roomItems.size() > 1 && i > 0 && i < roomItems.size() - 1)
				items += ",";
			Item item = roomItems.get(i);
			if (i != 0)
				items += " ";
			// Plural nouns don't begin with "a"
			if (item.getName().endsWith("s"))
				items += item.getName();
			// Singular nouns begin with "a"
			else
				items += "a " + item.getName();
		}

		if (items.equals(""))
			return "nothing";
		else
			return items;
	}

	/**
	 * Moves a given Item from a source Container to this Container
	 * @param item Item to be put into this Container
	 * @param cource Container from which to get and remove the Item
	 * @param Item which has been removed from source and put into this Container.
	 */
	public Item doPut(Item item, Container source) {
		addItem(item);
		source.removeItem(item);		
		World.print("Done!\n\n"); // meaning you put [item] into [container]
		return item;
	}

	/**
	 * Moves a given Item from this Container to the Player's inventory
	 * @param item Item to be taken
	 * @return Item which has been moved into the Player's inventory
	 */
	public Item doTake(Item item) {
		getWorld().getPlayer().addItem(item);
		this.removeItem(item);		
		World.print("Done!\n\n"); // meaning you put [item] into [container]
		return item;
	}	

	@Override
	public void doUse() {
		World.print("What do you want to do with the " + getName() + "?\n\n");
	}

	@Override
	public void doExamine() {		
		World.print(getDescription() + " Inside the " + getName() + " you see " + getItemString() + ".\n\n");		
	}
}