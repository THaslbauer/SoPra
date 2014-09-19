package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Key;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Heading;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;

/**
 * Makes a ship turn.
 * @author thomas
 *
 */
public class TurnInstruction extends Instruction {
	private boolean left;
	
	/**
	 * Create the Instruction
	 * @param logger
	 * @param left Controls if you have to turn left or right (left == true)
	 */
	public TurnInstruction(ExtendedLogWriter logger, boolean left){
		super(logger);
		this.left = left;
	}

	public boolean isLeft() {
		return left;
	}

	public void execute(Ship ship){
		Heading head = null;
		if(left){
			switch(ship.getHeading()){
			case H0:
				head = Heading.H5;
				break;
			case H1:
				head = Heading.H0;
				break;
			case H2:
				head = Heading.H1;
				break;
			case H3:
				head = Heading.H2;
				break;
			case H4:
				head = Heading.H3;
				break;
			case H5:
				head = Heading.H4;
				break;
			default:
				throw new UnsupportedOperationException("Heading should be H0 to H5");
			}
		}
		else{
			switch(ship.getHeading()){
			case H0:
				head = Heading.H1;
				break;
			case H1:
				head = Heading.H2;
				break;
			case H2:
				head = Heading.H3;
				break;
			case H3:
				head = Heading.H4;
				break;
			case H4:
				head = Heading.H5;
				break;
			case H5:
				head = Heading.H0;
				break;
			default:
				throw new UnsupportedOperationException("Heading should be H0 to H5");
			}
		}
		ship.setHeading(head);
		logger.notify(Entity.SHIP, ship.getId(), Key.DIRECTION, head.ordinal());
		logger.notify(Entity.SHIP, ship.getId(), Key.PC, ship.increasePC());
		super.cycle(ship);
	}
	
}
