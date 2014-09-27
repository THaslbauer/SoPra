package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Key;
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

	/**
	 * This method maximizes the ship's condition. It fails if the
	 * ship's tile is not the ship's base or the faction has not
	 * enought score to repair the ship.
	 * 
	 */
	@Override
	public void execute(Ship ship)
	{
		if(ship == null)
		{
			throw new IllegalArgumentException();
		}
		
		Tile baseTile    = ship.getMyTile();
		Faction faction  = ship.getFaction();
		int maxCondition = Ship.getMaxCondition(); 
		
		System.out.println(baseTile.navigable(ship));
		
		if(baseTile.navigable(ship) != CellType.HOME)
		{
			this.elseJump(ship);
			return;
		}
				
		if(faction.getScore() < 2)
		{
			this.elseJump(ship);
			return;
		}
		
		
		faction.decreaseScore();
		logger.fleetScore(faction.getFactionID(), faction.getScore());
		ship.setCondition(maxCondition);
		ship.increasePC();
		this.cycle(ship);
		

		this.logger.notify(Entity.SHIP, ship.getPC(), Key.CONDITION, maxCondition);
		this.logger.notify(Entity.SHIP, ship.getPC(), Key.PC, ship.getPC());
		
	}

}
