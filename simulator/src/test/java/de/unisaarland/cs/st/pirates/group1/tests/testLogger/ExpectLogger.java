package de.unisaarland.cs.st.pirates.group1.tests.testLogger;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import static org.junit.Assert.assertTrue;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Kraken;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter;

public class ExpectLogger implements ExtendedLogWriter {
	public LinkedList<LogOperation> ops = new LinkedList<LogOperation>();

	
	/* Most epic method of da history */
	public void expect(LogOperation op) {
		LogOperation otherop = ops.removeFirst();
		assertTrue(op.equals(otherop));
	}

	@Override
	public LogWriter addCell(Cell type, Integer faction, int x, int y)
			throws NullPointerException, ArrayIndexOutOfBoundsException,
			IllegalArgumentException, IllegalStateException {
		
		ops.add(new AddCell(type, faction, x, y));
		return this;
	}

	@Override
	public LogWriter addCustomHeaderData(String data)
			throws NullPointerException, ArrayIndexOutOfBoundsException,
			IllegalStateException {
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
		
		ops.add(new Create(entity, id, keys, values));
		return this;
	}

	@Override
	public LogWriter destroy(Entity entity, int id)
			throws NullPointerException, IllegalArgumentException,
			IllegalStateException {
		ops.add(new Destroy(entity, id));
		return this;
	}

	@Override
	public LogWriter fleetScore(int id, int value)
			throws IllegalArgumentException, ArrayIndexOutOfBoundsException,
			IllegalStateException {
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
		ops.add(new LogStep());
	}

	@Override
	public LogWriter notify(Entity entity, int id, Key key, int value)
			throws NullPointerException, IllegalArgumentException,
			IllegalStateException {
		ops.add(new Notify(entity, id, key, value));
		return this;
	}

	@Override
	public void fight(Ship ship, Ship otherShip) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fight(Ship ship, Kraken kraken) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerChange(Ship ship) {
		// TODO Auto-generated method stub
		
	}

}
