
/**
 * InfoPoint that implements the ExtendedLogWriter interface.
 * It tracks the changes important for logging and the GUI.
 * 
 * 
 * @version 1.2
 * @author christopher
 */

package de.unisaarland.cs.st.pirates.group1.sim.logger;

import java.io.IOException;
import java.io.OutputStream;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Kraken;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;

public class InfoPoint implements ExtendedLogWriter{

	private LogWriter refLogger;
	private ExtendedLogWriter GUI;
	
	/**
	 * Default constructor
	 */
	public InfoPoint(){
		
	}
	

	//The getter and Setter
	public LogWriter getRefLogger() {
		return refLogger;
	}

	public void setRefLogger(LogWriter refLogger) {
		this.refLogger = refLogger;
	}

	public ExtendedLogWriter getGUI() {
		return GUI;
	}

	public void setGUI(ExtendedLogWriter gUI) {
		GUI = gUI;
	}



@Override
public LogWriter addCell(Cell type, Integer faction, int x, int y)
		throws NullPointerException, ArrayIndexOutOfBoundsException,
		IllegalArgumentException, IllegalStateException {
	refLogger.addCell(type, faction, x, y);
	GUI.addCell(type, faction, x, y);
	return null;
}

@Override
public LogWriter addCustomHeaderData(String data) throws NullPointerException,
		ArrayIndexOutOfBoundsException, IllegalStateException {
	refLogger.addCustomHeaderData(data);
	GUI.addCustomHeaderData(data);
	return null;
}

@Override
public Transaction beginTransaction(Entity entity, int id)
		throws NullPointerException, IllegalArgumentException,
		IllegalStateException {
	refLogger.beginTransaction(entity, id);
	GUI.beginTransaction(entity, id);
	return null;
}

@Override
public void close() throws IllegalStateException, IOException {
	refLogger.close();
	GUI.close();
}

@Override
public LogWriter commitTransaction(Transaction transaction)
		throws NullPointerException, IllegalArgumentException,
		IllegalStateException {
	refLogger.commitTransaction(transaction);
	GUI.commitTransaction(transaction);
	return null;
}

@Override
public LogWriter create(Entity entity, int id, Key[] keys, int[] values)
		throws NullPointerException, IllegalArgumentException,
		ArrayIndexOutOfBoundsException, IllegalStateException {
	refLogger.create(entity, id, keys, values);
	GUI.create(entity, id, keys, values);
	return null;
}

@Override
public LogWriter destroy(Entity entity, int id) throws NullPointerException,
		IllegalArgumentException, IllegalStateException {
	refLogger.destroy(entity, id);
	GUI.destroy(entity, id);
	return null;
}

@Override
public LogWriter fleetScore(int id, int value) throws IllegalArgumentException,
		ArrayIndexOutOfBoundsException, IllegalStateException {
	refLogger.fleetScore(id, value);
	GUI.fleetScore(id, value);
	return null;
}

@Override
public void init(OutputStream logStream, String map, String... programs)
		throws NullPointerException, IOException,
		ArrayIndexOutOfBoundsException {
	refLogger.init(logStream, map, programs);
	GUI.init(logStream, map, programs);
	
}

@Override
public void logStep() throws IllegalStateException, IOException {
	refLogger.logStep();
	GUI.logStep();
}

@Override
public LogWriter notify(Entity entity, int id, Key key, int value)
		throws NullPointerException, IllegalArgumentException,
		IllegalStateException {
	refLogger.notify(entity, id, key, value);
	GUI.notify(entity, id, key, value);
	return null;
}

@Override
public void fight(Ship ship, Ship otherShip) {
	GUI.fight(ship, otherShip);
}

@Override
public void fight(Ship ship, Kraken kraken) {
	GUI.fight(ship, kraken);
}


@Override
public void registerChange(Ship ship) {
	GUI.registerChange(ship);
}

}
