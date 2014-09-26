package de.unisaarland.cs.st.pirates.group1.logger;

import java.util.LinkedList;

import de.unisaarland.cs.st.pirates.logger.LogWriter.*;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;

public class Create extends LogOperation {
	public Entity entity;
	public int id;
	public LinkedList<Entry> entries = new LinkedList<Entry>();
	
	public class Entry {
		public Key key;
		public int value;
		
		public Entry(Key k, int v) {
			key = k;
			value = v;
		}
		
		public String toString() {
			return "{"+key+","+value+"}";
		}
	}
	
	public Create(Entity entity, int id, Key[] keys, int[] values) {
		this.entity = entity;
		this.id = id;
		for(int i = 0; i < keys.length; i++) {
			entries.add(new Entry(keys[i], values[i]));
		}
	}
	
	public String toString() {
		return "Create "+entity+" with id "+id+" and properties "+entries;
	}
	
	public boolean equals(Object o) {
		Create other;
		try {
			other = (Create) o;
		} catch (ClassCastException e) {
			return false;
		}
		
		if(entity != other.entity)
			return false;
		if(id != other.id)
			return false;
		outer: for(Entry e : entries) {
			for(Entry f : other.entries) {
				if(e.key == f.key && e.value == f.value) {
					break outer;
				}
			}
			return false;
		}
		return true;	
	}
	
}
