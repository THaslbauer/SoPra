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
	public void factionConstructorTest(){
		
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
		 
		faction.setTactics(instructions);
		
		assertTrue("Instruction array must be correct",faction.getTactics().equals(instructions));
		 
		 
	}
	
	
	@Test
	public void addShipTest(){
		
		String faction_name = "test";
		int id = 1;
		
		Faction faction = new Faction(faction_name,1);
		
		faction.addShip();
		
		assertTrue(faction.getScore() == 1);
	}
	
	@Test
	public void removeShipTest(){
		
		String faction_name = "test";
		int id = 1;
		
		Faction faction = new Faction(faction_name,1);
		
		faction.addShip();
		faction.addShip();
		
		faction.removeShip();
		
		assertTrue(faction.getScore() == 1);
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
		
		faction.decreaseScore(-2);
		
		assertTrue("score should be 2",faction.getScore() == 2);
	}
	
	@Test
	public void decreaseScore2Test(){
		
		String faction_name = "test";
		
		Faction faction = new Faction(faction_name,1);
		
		faction.increaseScore(4);
		
		try{
			faction.decreaseScore(-1);
			faction.decreaseScore(-3);
		}
		
		catch(IllegalArgumentException e){
			
			return;
		}
		
		fail("score cannot be decreased by one or three");
		
	}
	
	@Test
	public void decreaseScore3test(){
		
		String faction_name = "test";
		
		Faction faction = new Faction(faction_name,1);
		
		faction.increaseScore(1);
		
		try{
			
		faction.decreaseScore(-2);
		
		}
		
		catch(IllegalArgumentException e){
			 return;
		}
		
		fail("Score cannot be decreased by two if there is only one point left");
	}
	
	
	
}
