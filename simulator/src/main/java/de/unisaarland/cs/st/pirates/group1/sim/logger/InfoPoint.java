
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
import java.util.List;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Kraken;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.logger.LogWriter;

public class InfoPoint implements ExtendedLogWriter{

	private LogWriter refLogger;
	private List<ExtendedLogWriter> GUIs;
	
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

	public List<ExtendedLogWriter> getGUI() {
		return GUIs;
	}

	public void setGUI(List<ExtendedLogWriter> gUI) {
		GUIs = gUI;
	}



@Override
public LogWriter addCell(Cell type, Integer faction, int x, int y)
		throws NullPointerException, ArrayIndexOutOfBoundsException,
		IllegalArgumentException, IllegalStateException {
	refLogger.addCell(type, faction, x, y);
	for(ExtendedLogWriter g :  GUIs)
	g.addCell(type, faction, x, y);
	return null;
}

@Override
public LogWriter addCustomHeaderData(String data) throws NullPointerException,
		ArrayIndexOutOfBoundsException, IllegalStateException {
	refLogger.addCustomHeaderData(data);
	for(ExtendedLogWriter g :  GUIs)
	g.addCustomHeaderData(data);
	return null;
}

@Override
public Transaction beginTransaction(Entity entity, int id)
		throws NullPointerException, IllegalArgumentException,
		IllegalStateException {
	refLogger.beginTransaction(entity, id);
	for(ExtendedLogWriter g :  GUIs)
	g.beginTransaction(entity, id);
	return null;
}

@Override
public void close() throws IllegalStateException, IOException {
	refLogger.close();
	for(ExtendedLogWriter g :  GUIs)
	g.close();
}

@Override
public LogWriter commitTransaction(Transaction transaction)
		throws NullPointerException, IllegalArgumentException,
		IllegalStateException {
	refLogger.commitTransaction(transaction);
	for(ExtendedLogWriter g :  GUIs)
	g.commitTransaction(transaction);
	return null;
}

@Override
public LogWriter create(Entity entity, int id, Key[] keys, int[] values)
		throws NullPointerException, IllegalArgumentException,
		ArrayIndexOutOfBoundsException, IllegalStateException {
	refLogger.create(entity, id, keys, values);
	for(ExtendedLogWriter g :  GUIs)
	g.create(entity, id, keys, values);
	return null;
}

@Override
public LogWriter destroy(Entity entity, int id) throws NullPointerException,
		IllegalArgumentException, IllegalStateException {
	refLogger.destroy(entity, id);
	for(ExtendedLogWriter g :  GUIs)
	g.destroy(entity, id);
	return null;
}

@Override
public LogWriter fleetScore(int id, int value) throws IllegalArgumentException,
		ArrayIndexOutOfBoundsException, IllegalStateException {
	refLogger.fleetScore(id, value);
	for(ExtendedLogWriter g :  GUIs)
	g.fleetScore(id, value);
	return null;
}

@Override
public void init(OutputStream logStream, String map, String... programs)
		throws NullPointerException, IOException,
		ArrayIndexOutOfBoundsException {
	refLogger.init(logStream, map, programs);
	for(ExtendedLogWriter g :  GUIs)
	g.init(logStream, map, programs);
	
}

@Override
public void logStep() throws IllegalStateException, IOException {
	refLogger.logStep();
	for(ExtendedLogWriter g :  GUIs)
	g.logStep();
}

@Override
public LogWriter notify(Entity entity, int id, Key key, int value)
		throws NullPointerException, IllegalArgumentException,
		IllegalStateException {
	refLogger.notify(entity, id, key, value);
	for(ExtendedLogWriter g :  GUIs)
	g.notify(entity, id, key, value);
	return null;
}

@Override
public void fight(Ship ship, Ship otherShip) {
	for(ExtendedLogWriter g :  GUIs)
	g.fight(ship, otherShip);
}

@Override
public void fight(Ship ship, Kraken kraken) {
	for(ExtendedLogWriter g :  GUIs)
	g.fight(ship, kraken);
}


@Override
public void registerChange(Ship ship) {
	for(ExtendedLogWriter g :  GUIs)
	g.registerChange(ship);
}

}
