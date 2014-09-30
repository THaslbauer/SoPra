package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions;

import java.util.List;
import java.util.Map;

import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Key;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Buoy;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;

/**
 * An Instruction that removes a {@link Buoy} from a Tile
 * @author thomas
 *
 */
public class UnmarkInstruction extends Instruction {
	
	private int type;

	/**
	 * creates the Instruction
	 * @param logger the Logger to connect to
	 * @param type Buoytype to unmark
	 */
	public UnmarkInstruction(ExtendedLogWriter logger, int type) {
		super(logger);
		this.type = type;
	}
	
	public int getType() {
		return type;
	}

	/**
	 *  Deletes a Buoy of the given type from a Tile if it is there.
	 *  Invariants: No Buoys in map <-> Buoy List not there (null in Buoy map)
	 */
	@Override
	public void execute(Ship ship){
		Tile tile = ship.getMyTile();
		//get Buoys to look at, so we can see if the buoy is on the tile
		Map<Faction, List<Buoy>> buoyMap = tile.getBuoyMap();
		Faction shipFact = ship.getFaction();
		List<Buoy> buoys = buoyMap.get(shipFact);
		if(buoys == null)
			return;
		for(int i = 0; i < buoys.size(); i++) {
			if(buoys.get(i).getType() == type) {
				//buoy is on tile, remove and destroy
				Buoy buoy = buoys.get(i);
				buoys.remove(i);
				logger.destroy(Entity.BUOY, buoy.getId());
			}
		}
		//no buoys left? now remove the list from the map
		if(buoys.isEmpty())
			buoyMap.remove(shipFact);
		//cycle and PC increase, never forget that!
		logger.notify(Entity.SHIP, ship.getId(), Key.PC, ship.increasePC());
		super.cycle(ship);
	}

}
