package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Key;
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
		if(buoys == null){
			buoys = new LinkedList<>();
			buoyMap.put(shipFact, buoys);
		}
		else {
			for(int i = 0; i < buoys.size(); i++) {
				if(buoys.get(i).getType() == type) {
					exists = true;
				}
			}
		}
		if(!exists)
			buoys.add(tile.getWorldmap().createBuoy(type, shipFact, tile));
		logger.notify(Entity.SHIP, ship.getId(), Key.PC, ship.increasePC());
		super.cycle(ship);
	}
}
