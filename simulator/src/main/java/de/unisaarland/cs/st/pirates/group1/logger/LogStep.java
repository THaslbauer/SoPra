package de.unisaarland.cs.st.pirates.group1.logger;

/**
 * An Object which represents the logStep() method call in the ExtendedLogWriter interface
 * @author thomas
 *
 */
public class LogStep extends LogOperation {

	/**
	 * All LogSteps are equal.
	 */
	@Override
	public int hashCode(){
		return 31;
	}
	
	public String toString() {
		return "STEP";
	}
	
	/**
	 * All LogSteps are equal.
	 */
	@Override
	public boolean equals(Object o){
		return o instanceof LogStep;
	}
}
