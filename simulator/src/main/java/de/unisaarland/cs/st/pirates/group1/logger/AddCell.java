package de.unisaarland.cs.st.pirates.group1.logger;

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
	
	public String toString() {
		return "Add "+type+(affiliation == null ? " " : " from "+affiliation+" ")+"at ("+x+","+y+")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((affiliation == null) ? 0 : affiliation.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + x;
		result = prime * result + y;
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
		AddCell other = (AddCell) obj;
		if (affiliation == null) {
			if (other.affiliation != null)
				return false;
		} else if (!affiliation.equals(other.affiliation))
			return false;
		if (type != other.type)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	
	
}
