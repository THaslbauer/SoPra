package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;

public class PickupInstruction extends ElseInstruction {
	Direction dir;

	public PickupInstruction(InfoPoint infoPoint, int elsePC, Direction dir) {
		super(infoPoint, elsePC);
		this.dir = dir;
	}

	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub

	}

}
