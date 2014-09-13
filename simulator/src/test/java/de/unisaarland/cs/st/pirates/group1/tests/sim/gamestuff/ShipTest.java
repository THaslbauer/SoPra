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
import de.unisaarland.cs.st.pirates.group1.sim.util.Register;

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
	
	private static Ship ship1;
	private static int id1;
	private static Tile tile1;
	private static Faction faction1;
	private static TestInstruction instruction1;
	
	
	@BeforeClass
	public static void init(){
		
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
		
		
		assertTrue(ship1.getRegister(Register.SENSE_CELLTYPE) == -1);
		assertTrue(ship1.getRegister(Register.SENSE_ENEMYMARKER) == 0);
		assertTrue(ship1.getRegister(Register.SENSE_MARKER0) == 0);
		assertTrue(ship1.getRegister(Register.SENSE_MARKER1) == 0);
		assertTrue(ship1.getRegister(Register.SENSE_MARKER2) == 0);
		assertTrue(ship1.getRegister(Register.SENSE_MARKER3) == 0);
		assertTrue(ship1.getRegister(Register.SENSE_MARKER4) == 0);
		assertTrue(ship1.getRegister(Register.SENSE_MARKER5) == 0);
		assertTrue(ship1.getRegister(Register.SENSE_SHIPCONDITION) == -1);
		assertTrue(ship1.getRegister(Register.SENSE_SHIPDIRECTION) == -1);
		assertTrue(ship1.getRegister(Register.SENSE_SHIPLOADED) == 0);
		assertTrue(ship1.getRegister(Register.SENSE_SHIPTYPE) == 0);
		assertTrue(ship1.getRegister(Register.SENSE_SUPPLY) == 0);
		assertTrue(ship1.getRegister(Register.SENSE_TREASURE) == 0);
	}
	
	@Test
	public void increasePCTest(){
		
		ship1 = new Ship(faction1, id1, tile1);
		
		ship1.increasePC();
		
		assertTrue(ship1.getPC() == 1);
	}
	
	@Test
	public void shipStepTest(){
		
		ship1 = new Ship(faction1, id1, tile1);
		
		int test = instruction1.getValue();
		
		ship1.step();
		
		assertTrue(ship1.getPC() == 1);
		assertTrue(instruction1.getValue() == 1);
	}
	
	@Test
	public void shipClearRegisterTest(){
		
		ship1 = new Ship(faction1, id1, tile1);
		
		ship1.setRegister(Register.SENSE_CELLTYPE, 3);
		ship1.setRegister(Register.SENSE_ENEMYMARKER, 1);
		ship1.setRegister(Register.SENSE_MARKER0, 1);
		ship1.setRegister(Register.SENSE_MARKER1, 1);
		ship1.setRegister(Register.SENSE_MARKER2, 1);
		ship1.setRegister(Register.SENSE_MARKER3, 1);
		ship1.setRegister(Register.SENSE_MARKER4, 1);
		ship1.setRegister(Register.SENSE_MARKER5, 1);
		ship1.setRegister(Register.SENSE_SHIPCONDITION, 1);
		ship1.setRegister(Register.SENSE_SHIPDIRECTION, 2);
		ship1.setRegister(Register.SENSE_SHIPLOADED, 1);
		ship1.setRegister(Register.SENSE_SHIPTYPE, 1);
		ship1.setRegister(Register.SENSE_SUPPLY, 1);
		ship1.setRegister(Register.SENSE_TREASURE, 1);
		
		assertTrue(ship1.getRegister(Register.SENSE_CELLTYPE) == 3);
		assertTrue(ship1.getRegister(Register.SENSE_ENEMYMARKER) == 1);
		assertTrue(ship1.getRegister(Register.SENSE_MARKER0) == 1);
		assertTrue(ship1.getRegister(Register.SENSE_MARKER1) == 1);
		assertTrue(ship1.getRegister(Register.SENSE_MARKER2) == 1);
		assertTrue(ship1.getRegister(Register.SENSE_MARKER3) == 1);
		assertTrue(ship1.getRegister(Register.SENSE_MARKER4) == 1);
		assertTrue(ship1.getRegister(Register.SENSE_MARKER5) == 1);
		assertTrue(ship1.getRegister(Register.SENSE_SHIPCONDITION) == 1);
		assertTrue(ship1.getRegister(Register.SENSE_SHIPDIRECTION) == 2);
		assertTrue(ship1.getRegister(Register.SENSE_SHIPLOADED) ==1);
		assertTrue(ship1.getRegister(Register.SENSE_SHIPTYPE) == 1);
		assertTrue(ship1.getRegister(Register.SENSE_SUPPLY) == 1);
		assertTrue(ship1.getRegister(Register.SENSE_TREASURE) == 1);
		
		ship1.clearRegisters();
		
		assertTrue(ship1.getRegister(Register.SENSE_CELLTYPE) == -1);
		assertTrue(ship1.getRegister(Register.SENSE_ENEMYMARKER) == 0);
		assertTrue(ship1.getRegister(Register.SENSE_MARKER0) == 0);
		assertTrue(ship1.getRegister(Register.SENSE_MARKER1) == 0);
		assertTrue(ship1.getRegister(Register.SENSE_MARKER2) == 0);
		assertTrue(ship1.getRegister(Register.SENSE_MARKER3) == 0);
		assertTrue(ship1.getRegister(Register.SENSE_MARKER4) == 0);
		assertTrue(ship1.getRegister(Register.SENSE_MARKER5) == 0);
		assertTrue(ship1.getRegister(Register.SENSE_SHIPCONDITION) == -1);
		assertTrue(ship1.getRegister(Register.SENSE_SHIPDIRECTION) == -1);
		assertTrue(ship1.getRegister(Register.SENSE_SHIPLOADED) == 0);
		assertTrue(ship1.getRegister(Register.SENSE_SHIPTYPE) == 0);
		assertTrue(ship1.getRegister(Register.SENSE_SUPPLY) == 0);
		assertTrue(ship1.getRegister(Register.SENSE_TREASURE) == 0);
		
		
	}

	
	@Test
	public void shipResetAndIncreaseBoredomTest(){
		
		ship1 = new Ship(faction1, id1, tile1);
		
		//Tests if increaseBoredom works
		ship1.increaseBoredom();
		ship1.increaseBoredom();
		ship1.increaseBoredom();
		ship1.increaseBoredom();
		ship1.increaseBoredom();
		
		assertTrue(ship1.getBoredom() == 5);
		
		//Tests if resetBoredom works
		ship1.resetBoredom();
		
		assertTrue(ship1.getBoredom() == 0);
	}
	
	//TODO: find out what controls the following invariants
	
	@Test
	public void setMoraleTest(){
		
		ship1 = new Ship(faction1, id1, tile1);
		
		
		try{
			ship1.setMorale(5);
			ship1.setMorale(-1);
		}
		//TODO:Find out which exception is thrown!!!
		
		catch(Exception e){
			return;
		}
		
		fail("Wrong value in field morale");
		
	}
	
	@Test
	public void setConditionTest(){
		
		ship1 = new Ship(faction1, id1, tile1);
		
		
		try{
			ship1.setCondition(4);
			ship1.setCondition(0);
		}
		
		//TODO: find out which exception is thrown
		catch(Exception e){
			return;
		}
		
		fail("Wrong value in field condition");
	}
	
	
	
}
