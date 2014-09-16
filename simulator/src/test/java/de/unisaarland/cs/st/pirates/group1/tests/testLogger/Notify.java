package de.unisaarland.cs.st.pirates.group1.tests.testLogger;

import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Key;

public class Notify extends LogOperation {
	public Entity entity;
	public int id;
	public Key key;
	public int value;
	
	
	public Notify(Entity entity, int id, Key key, int value) {
		this.entity = entity;
		this.id = id;
		this.key = key;
		this.value = value;
	}
	
	public boolean equals (Object o) {
		Notify other;
		try {
			other = (Notify) o;
		} catch(ClassCastException e) {
			return false;
		}
		return entity == other.entity && id == other.id && key == other.key && value == other.value;		
	}
}
