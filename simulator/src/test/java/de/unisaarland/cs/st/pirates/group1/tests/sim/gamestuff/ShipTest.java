package de.unisaarland.cs.st.pirates.group1.tests.sim.gamestuff;

import static org.junit.Assert.*;

import org.junit.Before;
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

/**
 * A test class for ships
 * @author Kerstin
 *
 */
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
	
	
	@Before
	public void init(){
		
		//Everything that is needed for a ship
		faction1 = new Faction("a",1);
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
		assertTrue(ship1.getHeading() == Heading.H0);
		assertTrue(ship1.getMorale() == 4);
		assertTrue(ship1.getLoad() == 0);
		assertTrue(ship1.getBoredom() == 0);
		assertTrue(ship1.getRestTime() == 0);
		assertTrue(ship1.getPC() == 0);
		assertTrue(ship1.getFaction() == faction1);
		assertTrue(ship1.getId() == id1);
		assertTrue(ship1.getMyTile().equals(tile1));
		assertTrue(ship1.getMyTile().getShip().equals(ship1));
		
		
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
		assertTrue(ship1.getRegister(Register.SENSE_SHIPLOADED) == -1);
		assertTrue(ship1.getRegister(Register.SENSE_SHIPTYPE) == -1);
		assertTrue(ship1.getRegister(Register.SENSE_SUPPLY) == 0);
		assertTrue(ship1.getRegister(Register.SENSE_TREASURE) == 0);
	}
	
	@Test
	public void increasePCTest(){
		
		ship1 = new Ship(faction1, id1, tile1);
		
		ship1.increasePC();
		
		assertTrue("PC should be increased",ship1.getPC() == 1);
	}
	
	@Test
	public void shipStepTest(){
		
		ship1 = new Ship(faction1, id1, tile1);
		
		int test = instruction1.getValue();
		
		assertTrue(test == 0);
		
		ship1.step();
		
		assertTrue("instruction must be executed",instruction1.getValue() == 1);
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
		
		ship1.clearSenseRegisters();
		
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
		assertTrue(ship1.getRegister(Register.SENSE_SHIPLOADED) == -1);
		assertTrue(ship1.getRegister(Register.SENSE_SHIPTYPE) == -1);
		assertTrue(ship1.getRegister(Register.SENSE_SUPPLY) == 0);
		assertTrue(ship1.getRegister(Register.SENSE_TREASURE) == 0);
		
		
	}

	@Test
	public void shipIncreaseBoredomTest(){
		
		ship1 = new Ship(faction1, id1, tile1);
		
		ship1.increaseBoredom();
		
		assertTrue("boredom should be increased by one",ship1.getBoredom() == 1);
	}
	
//	@Test
//	public void shipResetAndIncreaseBoredomTest(){
//		
//		ship1 = new Ship(faction1, id1, tile1);
//		
//		//Tests if increaseBoredom works
//		ship1.increaseBoredom();
//		ship1.increaseBoredom();
//		ship1.increaseBoredom();
//		ship1.increaseBoredom();
//		ship1.increaseBoredom();
//		
//		assertTrue(ship1.getBoredom() == 5);
//		
//		//Tests if resetBoredom works
//		ship1.resetBoredom();
//		
//		assertTrue("boredom should be resetted (0)",ship1.getBoredom() == 0);
//	}
//	
	
	@Test
	public void setMorale1Test(){
		
		ship1 = new Ship(faction1, id1, tile1);
		
		
		try{
			ship1.setMorale(5);
		}
		
		catch(IllegalArgumentException e){
			return;
		}
		catch(Exception e){
			fail("Wrong exception thrown");
		}
		fail("Wrong value in field morale");
		
	}
	
	@Test
	public void setMorale2Test(){
		
		ship1 = new Ship(faction1, id1, tile1);
		
		
		try{
			ship1.setMorale(-1);
		}
		
		catch(IllegalArgumentException e){
			return;
		}
		
		catch(Exception e){
			fail("Wrong exception thrown");
		}
		
		fail("Wrong value in field morale");
	}
	
	@Test
	public void setCondition1Test(){
		
		ship1 = new Ship(faction1, id1, tile1);
		
		
		try{
			ship1.setCondition(4);
		}
		
		catch(IllegalArgumentException e){
			return;
		}
		
		catch(Exception e){
			fail("Wrong exception thrown");
		}
		
		fail("Wrong value in field condition");
	}
	
	
	
	@Test
	public void setCondition3Test(){
		
		ship1 = new Ship(faction1, id1, tile1);
		
		ship1.setCondition(2);
		
		assertTrue("", ship1.getCondition() == 2);
		
	}
	
	//TODO: find out what program does, when trying to attach a second ship to tile
	
	
	@Test
	public void setload1Test(){
		
		ship1 = new Ship(faction1, id1, tile1);
		
		try{
			ship1.setLoad(5);
		}
		
		catch(IllegalArgumentException e){
			return;
		}
		
		catch(Exception e){
			fail("Wrong exception thrown");
		}
		
		fail("Wrong value in field load");
	}
	
	@Test
	public void setload2Test(){
		
		ship1 = new Ship(faction1, id1, tile1);
		
		try{
			ship1.setLoad(-1);
		}
		
		catch(IllegalArgumentException e){
			return;
		}
		
		catch(Exception e){
			fail("Wrong exception thrown");
		}
		
		fail("Wrong value in field load");
	}
	
	
	@Test
	public void maxTest(){
		
		assertTrue(Ship.getMaxmorale() == 4);
		assertTrue(Ship.getMaxload() == 4);
		assertTrue(Ship.getMaxboredom() == 40);
		assertTrue(Ship.getMaxcondition() == 3);
	}
	
	@Test
	public void setRestTimeTest(){
		
		ship1 = new Ship(faction1, id1, tile1);
		
		try{
			ship1.setRestTime(9);
		}
		
		catch(IllegalArgumentException e){
			return;
		}

		catch(Exception e){
			fail("Wrong exception thrown");
		}
		
		fail("Resttime cannot be greater than 8");
	}
	
	@Test
	public void setRestTime2Test(){
		
		ship1 = new Ship(faction1, id1, tile1);
		
		try{
			ship1.setRestTime(-1);
		}
		
		catch(IllegalArgumentException e){
			return;
		}
		
		catch(Exception e){
			fail("Wrong exception thrown");
		}
		
		fail("Resttime cannot be less than 0");
	}
	
	@Test
	public void boredom3Test(){
		//TODO This should be done by cycle.
//		ship1 = new Ship(faction1, id1, tile1);
//		
//		for(int i = 0; i<39; i++){
//			ship1.increaseBoredom();
//		}
//		
//		ship1.increaseBoredom();
//		
//		assertTrue("boredom should be resetted after forty cycles",ship1.getBoredom() == 0);
//		assertTrue("morale must be lowered after forty cycles",ship1.getMorale() == 3);
	}
	
	@Test
	public void shipmoved(){
		//TODO: when implemented writing this test
	}
	
	@Test
	public void addSecondShip(){
		
		ship1 = new Ship(faction1, id1, tile1);
		
		try{
			Ship ship2 = new Ship(faction1 , 2, tile1);
		}
		
		//TODO: find out which exception is thrown
		catch(Exception e){
			return;
		}
		
		fail("Second ship is not attached to tile");
	}
	
}
