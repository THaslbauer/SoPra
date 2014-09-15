package de.unisaarland.cs.st.pirates.group1.tests.sim.logger;

import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Key;

public class TestGuiNotify extends TestGui {


	@Override
	public LogWriter notify(Entity entity, int id, Key key, int value)
			throws NullPointerException, IllegalArgumentException,
			IllegalStateException {
		value = -1;
		return null;
	}
}
