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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entity == null) ? 0 : entity.hashCode());
		result = prime * result + id;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notify other = (Notify) obj;
		if (entity != other.entity)
			return false;
		if (id != other.id)
			return false;
		if (key != other.key)
			return false;
		if (value != other.value)
			return false;
		return true;
	}
	
	
	
}
