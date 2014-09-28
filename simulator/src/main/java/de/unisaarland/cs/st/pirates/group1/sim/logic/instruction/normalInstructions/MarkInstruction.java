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
 * An Instruction that sets a {@link Buoy} on a tile
 * @author thomas
 *
 */
public class MarkInstruction extends Instruction {
	
	private int type;

	/**
	 * Creates the Instruction
	 * @param logger
	 * @param type The type of the {@link Buoy} to set
	 */
	public MarkInstruction(ExtendedLogWriter logger, int type) {
		super(logger);
		if(type < 0 || type > 5)
			throw new IllegalArgumentException("Buoy type can't be less than 0 or greater than 5, was "+type);
		this.type = type;
	}

	public int getType() {
		return type;
	}

	@Override
	public void execute(Ship ship) {
		Tile tile = ship.getMyTile();
		Map<Faction, List<Buoy>> buoyMap = tile.getBuoyMap();
		Faction shipFact = ship.getFaction();
		List<Buoy> buoys = buoyMap.get(shipFact);
		boolean exists = false;
		if(buoys != null){
			for(int i = 0; i < buoys.size(); i++) {
				if(buoys.get(i).getType() == type) {
					exists = true;
				}
			}
		}
		if(!exists)
			tile.getWorldmap().createBuoy(type, shipFact, tile);
		super.cycle(ship);
	}
}
