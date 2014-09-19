/**
 * Represents the given Logger.
 * 
 * @version 1.0
 * @author christopher
 */

package de.unisaarland.cs.st.pirates.group1.sim.logger;

import java.io.IOException;
import java.io.OutputStream;

public interface LogWriter {
	
/**
 * The inner class Cell, an enumerator representing the Cells for logging purposes.
 * There can be three different types: ISLAND, SUPPLY, WATER
 */
	
public static enum Cell{
	ISLAND,
	SUPPLY,
	WATER;
	
}

/**
 * The inner class Entity, an enumerator representing the Entity for logging purposes.
 */

public static enum Entity{
	BUOY,
	SHIP,
	KRAKEN,
	TREASURE;
	
}

/**
 * The inner class Key, an enumerator representing the key to identify to which object the value is mapped.
 */

public static enum Key{
	DIRECTION,
	FLEET,
	MORAL,
	PC,
	RESTING,
	VALUE,
	X_COORD,
	Y_COORD;
	
}

/**
 * The interface Transaction.
 * Syntactic sugar for several notifies. Allows us grouping several changes in a step.
 */

public static interface Transaction{
	
/**
 * The method set, that adds a Key-Value-Pair to a Transaction
 * @param key The key, {@link LogWriter.Key}
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
 * After the Logger is initialized {@link init(OutputStream, String, String...)} all tiles in the map need to be added to the Log.
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

/**
 * This method allows adding a String to the Header of the Log.
 * 
 * @param data the String, that will be added to the header
 * 
 * @return The Logger
 * 
 * @throws NullPointerException If data is not set
 * @throws ArrayIndexOutOfBoundsException If the data length is greater then 1.000.000
 * @throws IllegalStateException if the method is not called between init() and the first logStep()
 */

public LogWriter addCustomHeaderData(String data)
		throws NullPointerException, ArrayIndexOutOfBoundsException, IllegalStateException;


/**
 * Begins a new transaction. {@link LogWriter.Transaction}
 * 
 * @param entity der Objekttyp {@link LogWriter.Entity}
 * @param id the identity of the Objekt. This represents together with the Objecttype the key of the Objekct in the Log.
 * 
 * @return a new Transaction with the specified key (guaranteed not null)
 * 
 * @throws NullPointerException If entity is not set.
 * @throws IllegalArgumentException If id is negativ.
 * @throws IllegalStateException if the method is not called between init() and the first logStep().
 */
public LogWriter.Transaction beginTransaction(LogWriter.Entity entity, int id)
		throws NullPointerException, IllegalArgumentException, IllegalStateException;

/**
 * This method closes the LogWriter.
 * 
 * @throws IllegalStateException the method should be called before the first logStep().
 * @throws IOException If the Writing on the first OutputStream fails.
 */
public void close()
		throws IllegalStateException,
		IOException;

/**
 * commits the transaction to the logger.
 * 
 * @param transaction The transaction to commit
 * @return
 * @throws NullPointerException If transaction is not set.
 * @throws IllegalArgumentException If the transaction dont have any datas.
 * @throws IllegalStateException if the method is called before the first logStep().
 * @see LogWriter.Transaction, beginTransaction(Entity, int)
 */
public LogWriter commitTransaction(LogWriter.Transaction transaction)
		throws NullPointerException,
		IllegalArgumentException,
		IllegalStateException;

/**
 * creates a new Object
 * 
 * @param entity The objecttype of the Object to create {@link LogWriter.Entity}
 * @param id The identity of the object. Identifies an object together with the type of the object clearly.
 * @param keys The key. See: {@link LogWriter.Key}
 * @param values The values to set, in the right order specified by the keys.
 * 
 * @return the Logger
 * 
 * @throws NullPointerException if entity, keys or values are not set.
 * @throws IllegalArgumentException if id is negative, or if an key is invalid.
 * @throws ArrayIndexOutOfBoundsException if the length of the keys field is not equal to the length auf values
 * @throws IllegalStateException if the method is called before {@link init(OutputStream, String, String...)}
 */

public LogWriter create(LogWriter.Entity entity, int id, LogWriter.Key[] keys, int[] values)
		throws NullPointerException,
		IllegalArgumentException,
		ArrayIndexOutOfBoundsException,
		IllegalStateException;

/**
 * Destroys an object in the game.
 * 
 * @param entity The Objecttype of the Object to destroy. {@link LogWriter.Entity}
 * @param id The Identity of the Object to destroy
 * 
 * @return
 * 
 * @throws NullPointerException If entity is not set.
 * @throws IllegalArgumentException If id is negative.
 * @throws IllegalStateException If the method is called before the first {@link logStep()}
 */

public LogWriter destroy(LogWriter.Entity entity, int id)
		throws NullPointerException,
		IllegalArgumentException,
		IllegalStateException;

/**
 * Signalizes the changes of the score in a fleet.
 * 
 * @param id The identity of the fleet, fleet id start at 0, beginning with a on the map.
 * @param value The actual score of the fleet. This is the absolute score, not the delta!
 * 
 * @return The Logger
 * 
 * @throws IllegalArgumentException If the id or value are negative
 * @throws IllegalStateException 
 */
public LogWriter fleetScore(int id, int value)
		throws IllegalArgumentException,
		ArrayIndexOutOfBoundsException,
		IllegalStateException;

/**
 * Initializes the LogWriter.
 * 
 * @param logStream The OutputStream for the logWriter.
 * @param map A String-representation of the Worldmap.
 * @param programs The tactic programms in String-Representation.
 * 
 * @return void
 * 
 * @throws NullPointerException If an argument is not set.
 * @throws IOException If the logStream is not writible.
 * @throws ArrayIndexOutOfBoundsException If the porgrams field is empty
 */

public void init(OutputStream logStream, String map, String... programs)
		throws NullPointerException,
		IOException,
		ArrayIndexOutOfBoundsException;

/**
 * Signalizes the end of a round to the logger
 * @param
 * 
 * @return void
 * 
 * @throws IllegalStateException If {@link init(OutputStream, String, String...)} was not called before.
 * @throws IOException If writing to the {@link OutputStream} fails
 */
public void logStep()
		throws IllegalStateException,
		IOException;

/**
 * Notifies the Logger that an object has been changed. Only one change per call.
 * 
 * @param entity The type of the object, that has been changed. See: {@link LogWriter.Entity}
 * @param id The identity of the object.
 * @param key The key to the value, where the changes occur. See: {@link LogWriter.Key}
 * @param value The value, that is mapped to the key. Describes the changes.
 * 
 * @return void
 * 
 * @throws NullPointerException if entity or key is not set
 * @throws IllegalArgumentException If id is negative or key and object does not match
 * @throws IllegalStateException If the initialization is not finished. Means the initial call of {@link logStep()} was not executed.
 */
public LogWriter notify(LogWriter.Entity entity, int id, LogWriter.Key key, int value)
		throws NullPointerException,
		IllegalArgumentException,
		IllegalStateException;






}
