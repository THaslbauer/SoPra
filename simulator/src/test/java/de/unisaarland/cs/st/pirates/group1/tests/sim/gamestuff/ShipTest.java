package de.unisaarland.cs.st.pirates.group1.tests.sim.gamestuff;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Sea;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Heading;

public class ShipTest {
	
	//A test class for testing the step method 
	public static class TestInstruction extends Instruction{
		
		private int value = 0;
		
		public TestInstruction(){
			super(null);
		}

		@Override
		public void execute(Ship ship) {
			value +=1 ;
			
		}
		
		public int getValue(){
			return this.value;
		}
				
		
	}
	
	private Ship ship1;
	private int id1;
	private Tile tile1;
	private Faction faction1;
	private TestInstruction instruction1;
	
	
	@BeforeClass
	public void init(){
		
		//Everything that is needed for a ship
		faction1 = new Faction("a");
		id1 = 1;
		tile1 = new Sea(null, new Position(1,1));
		
		//Some test tactics code for the step method
		instruction1= new TestInstruction();
		Instruction [] iarr = new Instruction[2];
		iarr[0] = instruction1;
		iarr[1] = instruction1;
		faction1.setTactics(iarr);
	
		
		
	}
	
	@Test
	public void shipConstructorTest(){
		
		ship1 = new Ship(faction1, id1, tile1);
		
		assertTrue(ship1.getCondition() == 3);
		assertTrue(ship1.getHeading() == Heading.HO);
		assertTrue(ship1.getMorale() == 4);
		assertTrue(ship1.getLoad() == 0);
		assertTrue(ship1.getBoredom() == 0);
		assertTrue(ship1.getRestTime() == 0);
		assertTrue(ship1.getPC() == 0);
	}
	
	@Test
	public void shipStepTest(){
		
		ship1 = new Ship(faction1, id1, tile1);
		
		int test = instruction1.getValue();
		
		ship1.step();
		
		assertTrue(ship1.getPC() == 1);
		assertTrue(instruction1.getValue() == 1);
	}

}
