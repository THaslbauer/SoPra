package de.unisaarland.cs.st.pirates.group1.tests.testLogger;

import de.unisaarland.cs.st.pirates.logger.LogWriter;

public class AddCell extends LogOperation {
	public LogWriter.Cell type;
	public Integer affiliation;
	public int x, y;
	
	public AddCell(LogWriter.Cell type, Integer affiliation, int x, int y) {
		this.type = type;
		this.affiliation = affiliation;
		this.x = x;
		this.y = y;
	}
	
	public boolean equals (Object o) {
		AddCell other;
		try {
			other = (AddCell) o;
		} catch(ClassCastException e) {
			return false;
		}
		return type == other.type && affiliation == other.affiliation && x == other.x && y == other.y;		
	}
	// Ω LOOL
}
