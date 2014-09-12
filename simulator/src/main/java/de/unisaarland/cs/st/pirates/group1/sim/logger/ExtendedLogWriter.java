
/**
 * Interface
 * This is an additional logger, tracking fights and krakens.
 * It implements the given LogWriter.
 * 
 * @version 1.0
 * @author christopher
 */

package de.unisaarland.cs.st.pirates.group1.sim.logger;

public interface ExtendedLogWriter extends LogWriter{
	
/**
 * The additional fighting Method, messaging the changes if a fight appears.
 * @param
 * @return
 * 
 */
public void fight();

}
