package de.unisaarland.cs.st.pirates.group1.tests.sim.gamestuff;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;

/**
 * This class tests if class faction works
 * @author Kerstin
 *
 */
public class FactionTest {

	@Before
	public void init(){
		
	}
	
	@Test
	public void factionConstructor1Test(){
		
		String faction_name = "test";
		int id = 1;
		Faction faction = new Faction(faction_name, id);
		
		assertTrue("faction's name must be test", faction.getName().equals(faction_name));
		assertTrue("the score must be 0", faction.getScore() == 0);
		assertTrue("the number of ships must be 0", faction.getShipCount() == 0);
		assertTrue("faction has no tactics yet", faction.getTactics() == null);
		assertTrue("the id of the faction must be 1", faction.getFactionID() ==1);
		
		
	}
	
	@Test
	public void factionConstructor2Test(){
		
		try{
		String faction_name = null;
		
		Faction faction = new Faction(faction_name,2);
		}
		
		catch(IllegalArgumentException e){
			return;
		}
		
		fail("a faction's name should not be null");
	}
	
	@Test
	public void getterAndSetterTest(){
		
		String faction_name = "test";
		int id = 1;
		
		Faction faction = new Faction(faction_name,1);
		
		//set score on legal value
		faction.setScore(20);		
		assertTrue("score should be 20",faction.getScore() == 20);
		
		//set ship count on legal value
		faction.setShipCount(12);
		assertTrue("ship count should be 12", faction.getShipCount() == 12);
		
		// test if tactics are added
		Instruction ins = new ShipTest.TestInstruction();
		Instruction [] instructions = new Instruction[2];
		
		instructions[0] = ins;
		instructions[1] = ins;
		
		faction.setTactics(instructions);
		
		assertTrue("Instruction array must be correct",faction.getTactics().equals(instructions));
		 
		 
	}
	
	
	@Test
	public void addShipTest(){
		
		String faction_name = "test";
		int id = 1;
		
		Faction faction = new Faction(faction_name,1);
		
		faction.addShip();
		
		assertTrue(faction.getShipCount() == 1);
	}
	
	@Test
	public void removeShipTest(){
		
		String faction_name = "test";
		int id = 1;
		
		Faction faction = new Faction(faction_name,1);
		
		faction.addShip();
		faction.addShip();
		
		faction.removeShip();
		
		assertTrue(faction.getShipCount() == 1);
	}
	
	@Test
	public void removeShipTest1(){
		
		String faction_name = "test";
		int id = 1;
		
		Faction faction = new Faction(faction_name, 1);
		
		try{
			faction.removeShip();
		}
		catch(UnsupportedOperationException e){
			return;
		}
		
		fail("there is no ship which can be removed");
	}
	
	@Test
	public void increaseScore1Test(){
		
		String faction_name = "test";
		
		Faction faction = new Faction(faction_name,1);
		
		faction.increaseScore(1);
		
		assertTrue("score should be 1",faction.getScore() == 1);
		
	}
	
	@Test
	public void increaseScore2Test(){
		
		String faction_name = "test";
		
		Faction faction = new Faction(faction_name,1);
		
		try{
			faction.increaseScore(-1);
		}
		
		catch(IllegalArgumentException e){
			return;
		}
		
		fail("score is now -1");
	}
	
	@Test
	public void increaseScore3Test(){
		
		String faction_name = "test";
		
		Faction faction = new Faction(faction_name,1);
		
		try{
			faction.increaseScore(5);
		}
		
		catch(IllegalArgumentException e){
			return;
		}
		
		fail("score is now 5");		
		
	}
	
	@Test
	public void decreaseScore1Test(){
		
		String faction_name = "test";
		
		Faction faction = new Faction(faction_name,1);
		
		faction.increaseScore(4);
		
		faction.decreaseScore();
		
		assertTrue("score should be 2",faction.getScore() == 2);
	}
	
	
	@Test
	public void decreaseScore3test(){
		
		String faction_name = "test";
		
		Faction faction = new Faction(faction_name,1);
		
		faction.increaseScore(1);
		
		try{
			
		faction.decreaseScore();
		
		}
		
		catch(UnsupportedOperationException e){
			 return;
		}
		
		fail("Score cannot be decreased by two if there is only one point left");
	}
	
	

	@Test
	public void getInstruction1Test(){
	
		String faction_name = "test";
		
		Faction faction = new Faction(faction_name,1);
		
		Instruction ins = new ShipTest.TestInstruction();
		Instruction [] instructions = new Instruction[2];
		instructions[0] = ins;
		instructions[1] = ins;
		 
		faction.setTactics(instructions);
		
		Instruction instruction = faction.getInstruction(1);
		
		assertTrue("the right instruction should be returned",instruction.equals(ins));
	}
	
	@Test
	public void getInstruction2Test(){
		
		String faction_name = "test";
		
		Faction faction = new Faction(faction_name,1);
		
		Instruction ins = new ShipTest.TestInstruction();
		Instruction [] instructions = new Instruction[2];
		instructions[0] = ins;
		instructions[1] = ins;
		
		try{
			faction.getInstruction(2);
		}
		
		catch(IndexOutOfBoundsException e){
			return;
		}
		
		fail("index out of bounds exception should be thrown (wrong PC)");
	}
	
	@Test
	public void getInstruction3Test(){
		
		String faction_name = "test";
		
		Faction faction = new Faction(faction_name,1);
		
		Instruction ins = new ShipTest.TestInstruction();
		Instruction [] instructions = new Instruction[2];
		
		try{
			faction.getInstruction(-1);
		}
		
		catch(IndexOutOfBoundsException e){
			return;
		}
		
		fail("index out of bounds exception should be thrown (wrong PC)");
	}
	
	@Test
	public void getInstruction4Test(){
		
		String faction_name = "test";
		
		Faction faction = new Faction(faction_name,1);
		
		try{
			faction.getInstruction(1);
		}
		
		catch(UnsupportedOperationException e){
			return;
		}
		
		fail("there is no tactic to be chose");
	}
	
	@Test
	public void equalsTest(){
		
		String faction_name = "test";
		
		Faction faction = new Faction(faction_name,1);
		
		
		//another faction for testing purposes
		String faction_name2 ="test1";
		Faction faction2 = new Faction(faction_name2,1);
		
		//another faction for testing purposes
		String faction_name3 = "test";
		Faction faction3 = new Faction(faction_name3, 3);
		
		//another faction for testing purposes
		String faction_name4 = "test";
		Faction faction4 = new Faction(faction_name4,1);
		
		assertTrue("a faction should equal itself",faction.equals(faction));
		assertFalse("a faction should not equal null", faction.equals(null));
		assertFalse("a faction should not equal true",faction.equals(true));
		assertFalse("a faction should not equal another faction with different name", faction.equals(faction2));
		assertFalse("a faction should not equal another faction with different id", faction.equals(faction3));
		assertTrue("a faction equals a second faction with same data", faction.equals(faction4));
	}
	
	
}
