/**
 * Represents the given Logger.
 * 
 * @version 1.0
 * @author christopher
 */

package sim.logger;

public interface LogWriter {
	
/**
 * The inner class Cell, an enumerator representing the Cells for logging purposes.
 */
	
public static class Cell{
	
}

/**
 * The inner class Entity, an enumerator representing the Entity for logging purposes.
 */

public static class Entity{
	
}

/**
 * The inner class Key, an enumerator representing the key to identify to which object the value is mapped.
 */

public static class Key{
	
}

/**
 * The interface Transaction.
 * Syntactic sugar for several notifies. Allows us grouping several changes in a step.
 */

public static interface Transaction{
	
/**
 * The method set, that adds a Key-Value-Pair to a Transaction
 * @param key The key, @see LogWriter.Key
 * @param value The changed value
 * 
 * @return the Transaction
 * 
 * @throws NullPointerException If Key is not set
 * @throws IllegalArgumentException If key is not valid
 */
	
public void set(LogWriter.Key key, int value)
		throws NullPointerException,IllegalArgumentException;
}
/**
 * This method documents a Tile for logging purposes.
 * After the Logger is initialized (@see init(OutputStream, String, String...)) all tiles in the map need to be added to the Log.
 * The call only is valid after the init Method and before the first Call of logStep()
 * 
 * @param type The typ of the tile
 * @param faction The faction, in case the tile is a base. If the Tile is not a base, the value is null.
 * @param x x-Koord of the tile
 * @param y y-Koord of the tile
 * 
 * @return The Logger
 * 
 * @throws NullPointerException if the type of the tile is not set
 * @throws ArrayIndexOutOfBoundsException if the id of the faction is greater then the given factionCount from the init
 * @throws IllegalArgumentException if x or y are negativ
 * @throws IllegalStateException if the method is not called between init() and the first logStep()
 */
public LogWriter addCell(LogWriter.Cell type, Integer faction, int x, int y)
		throws NullPointerException, ArrayIndexOutOfBoundsException,
		IllegalArgumentException,IllegalStateException;

}
