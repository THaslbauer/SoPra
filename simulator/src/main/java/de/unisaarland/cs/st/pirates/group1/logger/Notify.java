package de.unisaarland.cs.st.pirates.group1.logger;

import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Key;

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
	
	public String toString() {
		return "Notify: "+entity+" with id "+id+" changed "+key+" to "+value;
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