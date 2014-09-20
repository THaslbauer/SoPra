package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Key;
import de.unisaarland.cs.st.pirates.group1.sim.util.CellType;
/**
 * Represents an instruction to repair a {@link Ship}.
 * @author Nico
 *
 */
public class RepairInstruction extends ElseInstruction {

	/**
	 * Creates the instruction.
	 * @param logger
	 * @param elsePC The PC to set if the repair fails
	 */
	public RepairInstruction(ExtendedLogWriter logger, int elsePC) {
		super(logger, elsePC);
	}

	@Override
	public void execute(Ship ship)
	{
		if(ship == null)
		{
			throw new IllegalArgumentException();
		}
		
		// get the tile on which the ship is standing
		Tile baseTile = ship.getMyTile();
		
		// get the ship's faction
		Faction faction = ship.getFaction();
		
		
		// checks if the ship's current tile is a friendly base
		if(baseTile.navigable(ship) != CellType.HOME)
		{
			// if not set the ship's pc to the elseAddress
			this.elseJump(ship);
			return;
		}
		
		
		// checks if the faction has enought score
		if(faction.getScore() >= 2)
		{
			// if it has, it decreases the score by 2
			faction.decreaseScore();
		}
		else
		{
			// if not set the ship's pc to the elseAddress
			this.elseJump(ship);
			return;
		}
		
		
		// increases the ship's condition and pc
		ship.setCondition(3);
		ship.increasePC();
		
		// and notifies it
		logger.notify(Entity.SHIP, ship.getPC(), Key.CONDITION, 3);
		logger.notify(Entity.SHIP, ship.getPC(), Key.PC, ship.getPC());
		
	}

}
