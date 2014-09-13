
/**
 * Interface
 * This is an additional logger, tracking fights and krakens.
 * It implements the given LogWriter.
 * 
 * @version 1.0
 * @author christopher
 */

package de.unisaarland.cs.st.pirates.group1.sim.logger;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Kraken;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;

public interface ExtendedLogWriter extends LogWriter{
	
/**
 * The additional fighting Method, messaging the changes if a fight between two ships appears.
 * @param ship the first of the two ships, which is actually stepping
 * @param otherShip the other ship
 * @return
 * 
 */
public void fight(Ship ship, Ship otherShip);

/**
 * the additinal fightin Method, messaging the changes if a fight between a ship and a kraken appears.
 * @param ship the ship which is actually stepping
 * @param kraken the kraken
 */

public void fight(Ship ship, Kraken kraken);

}
