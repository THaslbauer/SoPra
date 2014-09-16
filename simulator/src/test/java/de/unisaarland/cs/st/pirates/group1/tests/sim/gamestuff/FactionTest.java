package de.unisaarland.cs.st.pirates.group1.tests.sim.gamestuff;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;

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
		
		//TODO: tests if tactics are added
	}
	
}
