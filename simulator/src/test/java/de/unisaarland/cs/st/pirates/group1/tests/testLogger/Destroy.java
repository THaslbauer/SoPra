package de.unisaarland.cs.st.pirates.group1.tests.testLogger;

import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter;

public class Destroy {
	public LogWriter.Cell type;
	public Integer affiliation;
	
	Destroy(LogWriter.Cell type, Integer affiliation) {
		this.type = type;
		this.affiliation = affiliation;
	}
	
	public boolean equals (Object o) {
		AddCell other;
		try {
			other = (AddCell) o;
		} catch(ClassCastException e) {
			return false;
		}
		return type == other.type && affiliation == other.affiliation;		
	}
}
