package de.unisaarland.cs.st.pirates.group1.tests.testLogger;

public class LogStep extends LogOperation {

	@Override
	public int hashCode(){
		return 31;
	}
	
	@Override
	public boolean equals(Object o){
		return o instanceof LogStep;
	}
}
