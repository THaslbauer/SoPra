
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
	// TODO Auto-generated method stub
	return null;
}

@Override
public LogWriter addCustomHeaderData(String data) throws NullPointerException,
		ArrayIndexOutOfBoundsException, IllegalStateException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Transaction beginTransaction(Entity entity, int id)
		throws NullPointerException, IllegalArgumentException,
		IllegalStateException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void close() throws IllegalStateException, IOException {
	// TODO Auto-generated method stub
	
}

@Override
public LogWriter commitTransaction(Transaction transaction)
		throws NullPointerException, IllegalArgumentException,
		IllegalStateException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public LogWriter create(Entity entity, int id, Key[] keys, int[] values)
		throws NullPointerException, IllegalArgumentException,
		ArrayIndexOutOfBoundsException, IllegalStateException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public LogWriter destroy(Entity entity, int id) throws NullPointerException,
		IllegalArgumentException, IllegalStateException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public LogWriter fleetScore(int id, int value) throws IllegalArgumentException,
		ArrayIndexOutOfBoundsException, IllegalStateException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void init(OutputStream logStream, String map, String... programs)
		throws NullPointerException, IOException,
		ArrayIndexOutOfBoundsException {
	// TODO Auto-generated method stub
	
}

@Override
public void logStep() throws IllegalStateException, IOException {
	// TODO Auto-generated method stub
	
}

@Override
public LogWriter notify(Entity entity, int id, Key key, int value)
		throws NullPointerException, IllegalArgumentException,
		IllegalStateException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void fight(Ship ship, Ship otherShip) {
	// TODO Auto-generated method stub
	
}

@Override
public void fight(Ship ship, Kraken kraken) {
	// TODO Auto-generated method stub
	
}

}
