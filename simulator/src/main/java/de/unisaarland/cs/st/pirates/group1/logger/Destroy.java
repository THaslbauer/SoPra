package de.unisaarland.cs.st.pirates.group1.logger;

import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;

public class Destroy extends LogOperation {
	public Entity entity;
	public int id;
	
	public Destroy(Entity entity, int id) {
		this.entity = entity;
		this.id = id;
	}
	
	public String toString() {
		return "Destroy "+entity+" with id "+id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entity == null) ? 0 : entity.hashCode());
		result = prime * result + id;
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
		Destroy other = (Destroy) obj;
		if (entity != other.entity)
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
