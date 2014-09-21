package de.unisaarland.cs.st.pirates.group1.tests.testLogger;

import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;

public class Destroy extends LogOperation {
	public Entity entity;
	public int id;
	
	public Destroy(Entity entity, int id) {
		this.entity = entity;
		this.id = id;
	}
	
	public boolean equals(Object o) {
		Destroy other;
		try {
			other = (Destroy) o;
		} catch (ClassCastException e) {
			return false;
		}
		
		return entity == other.entity && id == other.id;
	}
	
	
}
