public interface Closeable {

	/** Constants are allowed in interfaces and they make your program easier to read */
	public final static boolean OPEN = true;
	public final static boolean CLOSED = false;

	/** Returns whether this Closeable object is open */
	public boolean isOpen();

	/** Opens this Closeable object */
	public void doOpen();

	/** Closes this Closeable object */
	public void doClose();
}
