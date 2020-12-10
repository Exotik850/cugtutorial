 /**
 * A Player can contain objects and knows the Room it's currently in.
 */
public class Player extends Container {

	/** Room this Player is in at the moment */
	private Room currentRoom;

	private int health;

	private Boolean hasBrushedTeeth;

	private Boolean isWearingClothes;
	
	/** Creates a player that starts in the given Room with no items */
	public Player(Room startRoom, World world, String name, String description) {
		super(world, name, description);
		currentRoom = startRoom;
		health = 0;
		hasBrushedTeeth = false;
		isWearingClothes = false;
	}

	/* Getters and setters */

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(Room newRoom) {
		currentRoom = newRoom;
	}

	public int getHealth(){
		return health;
	}

	public void setHealth(int health){
		this.health = health;
	}

	public Boolean getHasBrushedTeeth(){
		return hasBrushedTeeth;
	}

	public void setHasBrushedTeeth(Boolean hasBrushedTeeth){
		this.hasBrushedTeeth = hasBrushedTeeth;
	}

	public Boolean getIsWearingClothes(){
		return isWearingClothes;
	}

	public void setIsWearingClothes(Boolean isWearingClothes){
		this.isWearingClothes = isWearingClothes;
	}
}