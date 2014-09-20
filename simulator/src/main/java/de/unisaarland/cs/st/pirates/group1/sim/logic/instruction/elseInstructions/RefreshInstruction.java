package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.util.CellType;
import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Heading;
import de.unisaarland.cs.st.pirates.group1.sim.util.Register;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Key;

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

	@Override
	public void execute(Ship ship)
	{
		Tile refreshTile = ship.getMyTile().getNeighbour(Heading.H0, dir);
		
		if(refreshTile.navigable(ship) != CellType.ISLAND)
		{
			this.elseJump(ship);
			this.cycle(ship);
			return;
		}
		
		if(!refreshTile.isSupply())
		{
			this.elseJump(ship);
			this.cycle(ship);
			return;
		}
		
		if(ship.getRegister(Register.SHIP_MORAL) == 4)
		{
			this.elseJump(ship);
			this.cycle(ship);
			return;
		}
		
		
		ship.setRegister(Register.SHIP_MORAL, 4);
		ship.increasePC();
		
		this.logger.notify(Entity.SHIP, ship.getId(), Key.MORAL, 4);
		this.logger.notify(Entity.SHIP, ship.getId(), Key.PC, ship.getPC());
		
	}

}
