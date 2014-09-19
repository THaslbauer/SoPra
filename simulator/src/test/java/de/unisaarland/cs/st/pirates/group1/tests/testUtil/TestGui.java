package de.unisaarland.cs.st.pirates.group1.tests.testUtil;

import java.io.IOException;
import java.io.OutputStream;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Kraken;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter;

public class TestGui implements ExtendedLogWriter
{
	public int value = 0;

	@Override
	public LogWriter addCell(Cell type, Integer faction, int x, int y)
			throws NullPointerException, ArrayIndexOutOfBoundsException,
			IllegalArgumentException, IllegalStateException {
		value += 1;
		return this;
	}

	@Override
	public LogWriter addCustomHeaderData(String data)
			throws NullPointerException, ArrayIndexOutOfBoundsException,
			IllegalStateException {
		value += 1;
		return this;
	}

	@Override
	public Transaction beginTransaction(Entity entity, int id)
			throws NullPointerException, IllegalArgumentException,
			IllegalStateException {
		value += 1;
		return null;
	}

	@Override
	public void close() throws IllegalStateException, IOException {
		value += 1;
	}

	@Override
	public LogWriter commitTransaction(Transaction transaction)
			throws NullPointerException, IllegalArgumentException,
			IllegalStateException {
		value += 1;
		return null;
	}

	@Override
	public LogWriter create(Entity entity, int id, Key[] keys, int[] values)
			throws NullPointerException, IllegalArgumentException,
			ArrayIndexOutOfBoundsException, IllegalStateException {
		value += 1;
		return null;
	}

	@Override
	public LogWriter destroy(Entity entity, int id)
			throws NullPointerException, IllegalArgumentException,
			IllegalStateException {
		value += 1;
		return null;
	}

	@Override
	public LogWriter fleetScore(int id, int value)
			throws IllegalArgumentException, ArrayIndexOutOfBoundsException,
			IllegalStateException {
		this.value += 1;
		return null;
	}

	@Override
	public void init(OutputStream logStream, String map, String... programs)
			throws NullPointerException, IOException,
			ArrayIndexOutOfBoundsException {
		value += 1;
	}

	@Override
	public void logStep() throws IllegalStateException, IOException {
		value += 1;
	}

	@Override
	public LogWriter notify(Entity entity, int id, Key key, int value)
			throws NullPointerException, IllegalArgumentException,
			IllegalStateException {
		this.value += 1;
		return null;
	}

	@Override
	public void fight(Ship ship, Ship otherShip) {
		value += 1;
	}

	@Override
	public void fight(Ship ship, Kraken kraken) {
		value += 1;
	}

	@Override
	public void registerChange(Ship ship) {
		// TODO Auto-generated method stub
		
	}
}
