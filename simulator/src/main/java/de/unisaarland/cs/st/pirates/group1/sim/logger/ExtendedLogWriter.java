
/**
 * Interface
 * This is an additional logger, tracking fights and krakens.
 * It implements the given LogWriter.
 * 
 * @version 1.1
 * @author christopher
 */

package de.unisaarland.cs.st.pirates.group1.sim.logger;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Kraken;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.logger.LogWriter;

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
 * the additional fighting Method, messaging the changes if a fight between a ship and a kraken appears.
 * @param ship the ship which is actually stepping
 * @param kraken the kraken that is chilling down the sea
 */

public void fight(Ship ship, Kraken kraken);


/**
 * notifies the register changes of a sense instruction, which is not implemented in the standard LogWriter Interface
 * @param ship the ship whos registers have to be updated
 */
public void registerChange(Ship ship);

}
