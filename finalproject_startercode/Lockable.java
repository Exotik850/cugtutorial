public interface Lockable {

	/** Constants are allowed in interfaces and they make your program easier to read */
	public final static boolean LOCKED = true;
	public final static boolean UNLOCKED = false;
	
	/** Returns whether this object is locked */
	public boolean isLocked();

	/** Locks this object */
	public void doLock();

	/** Unlocks this object */
    public void doUnlock();
}
