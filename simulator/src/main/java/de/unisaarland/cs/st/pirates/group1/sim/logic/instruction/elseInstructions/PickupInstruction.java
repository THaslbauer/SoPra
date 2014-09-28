package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Treasure;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Key;

/**
 * Represents an instruction for a {@link Ship} to pick up a {@link Treasure}.
 * @author thomas
 *
 */
public class PickupInstruction extends ElseInstruction {
	private Direction dir;

	/**
	 * Creates the instruction.
	 * @param logger
	 * @param elsePC
	 * @param dir The direction where the {@link Treasure} to be picked up should be.
	 */
	public PickupInstruction(ExtendedLogWriter logger, int elsePC, Direction dir) {
		super(logger, elsePC);
		if(dir == null)
			throw new IllegalArgumentException("Direction can't be null");
		this.dir = dir;
	}

	public Direction getDir() {
		return dir;
	}

	@Override
	public void execute(Ship ship) {
		int maxLoad = Ship.getMaxload();
		Tile tile = ship.getMyTile();
		Tile neighbour = tile.getNeighbour(ship.getHeading(), dir);
		int possibleLoad = maxLoad - ship.getLoad();
		if(possibleLoad == 0 || neighbour.getTreasure() == null) {
			super.elseJump(ship);
			return;
		}
		int treasure = neighbour.getTreasure().getValue();
		//should never happen:
		if(treasure <= 0) {
			super.elseJump(ship);
			return;
		}
		else {
			if(treasure - possibleLoad >= 0) {
				ship.setLoad(maxLoad);
				logger.notify(Entity.SHIP, ship.getId(), Key.VALUE, maxLoad);
				neighbour.decreaseTreasure(possibleLoad);
			}
			else {
				int newLoad = maxLoad - (possibleLoad - treasure);
				ship.setLoad(newLoad);
				logger.notify(Entity.SHIP, ship.getId(), Key.VALUE, newLoad);
				neighbour.decreaseTreasure(treasure);
			}
			super.cycle(ship);
		}
		
	}

}
