package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.util.CellType;
import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Heading;
import de.unisaarland.cs.st.pirates.group1.sim.util.Register;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Key;

/**
 * Represents an instruction to refresh the morale of a {@link Ship}.
 * @author Nico
 
 */
public class RefreshInstruction extends ElseInstruction
{
	private Direction dir;

	/**
	 * Creates the instruction.
	 * @param logger
	 * @param elsePC The PC that is set when the instruction fails
	 * @param dir The {@link Direction} in which to look for the refresh
	 */
	public RefreshInstruction(ExtendedLogWriter logger, int elsePC, Direction dir) {
		super(logger, elsePC);
		this.dir = dir;
	}

	public Direction getDir() {
		return dir;
	}
	
	/**
	 * This method maximizes the ship's morale and resets the boredom if it succeeds. It
	 * fails if the ship tile's neighbour tile in direction dir is not an island, the
	 * island is a non supply island or the ship's morale is already maximized.
	 * 
	 */
	@Override
	public void execute(Ship ship)
	{
		
		if(ship == null)
		{
			throw new IllegalArgumentException();
		}
		
		Tile refreshTile = ship.getMyTile().getNeighbour(Heading.H0, dir);
		int maxMorale    = Ship.getMaxmorale();
		
		
		if(refreshTile.navigable(ship) != CellType.ISLAND)
		{
			this.elseJump(ship);
			return;
		}
		
		if(!refreshTile.isSupply())
		{
			this.elseJump(ship);
			return;
		}
		
		if(ship.getRegister(Register.SHIP_MORAL) == maxMorale)
		{
			this.elseJump(ship);
			return;
		}
		
		
		ship.setRegister(Register.SHIP_MORAL, maxMorale);
		logger.notify(Entity.SHIP, ship.getId(), Key.PC, ship.increasePC());
		ship.resetBoredom();
		
		
		this.logger.notify(Entity.SHIP, ship.getId(), Key.MORAL, maxMorale);
		
	}

}
