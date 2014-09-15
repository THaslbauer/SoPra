package de.unisaarland.cs.st.pirates.group1.tests.sim.logger;

import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Key;

public class TestGuiDropInstr extends TestGui {

	public int val;
	
	@Override
	public LogWriter notify(Entity entity, int id, Key key, int value)
			throws NullPointerException, IllegalArgumentException,
			IllegalStateException {
		value = -1;
		return null;
	}
	
	@Override
	public LogWriter create(Entity entity, int id, Key[] keys, int[] values)
			throws NullPointerException, IllegalArgumentException,
			ArrayIndexOutOfBoundsException, IllegalStateException {
		val = 42;
		return null;
	}
}
