/**
 * The Testing class for Instructions
 * 
 * @version 1.1
 * @author christopher
 */

package de.unisaarland.cs.st.pirates.group1.tests.sim.logic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.logger.AddCell;
import de.unisaarland.cs.st.pirates.group1.logger.Create;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Buoy;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.EntityFactory;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Sea;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Treasure;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap6T;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Cell;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Key;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.ElseInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.DropInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.GotoInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.MarkInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.SenseInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.TurnInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.UnmarkInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.util.CellType;
import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Heading;
import de.unisaarland.cs.st.pirates.group1.sim.util.Register;
import de.unisaarland.cs.st.pirates.group1.sim.util.ShipType;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.ExpectLogger;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.TestGui;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.TestGuiDropInstr;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.TestGuiNotify;

public class InstructionTest {
	
	private Worldmap worldMap;
	private Faction faction;
	private Ship ship;
	private Ship testShip2;
	private Tile baseTile;
	private Tile waterTile1;
	private Tile waterTile2;
	private Tile islandTile2;
	private Tile islandTile1;
	private Position position1;
	private int x;
	private ExpectLogger expectLogger;
	private EntityFactory entityFactory;
	private Worldmap worldMap2;
	private Tile waterTileMap2;
	private Ship ship2;
	
	@Before
	public void init(){
		x = 0;
		expectLogger = new ExpectLogger();
		entityFactory = new EntityFactory();
		
		//A worldMap with exactly one seatile
		worldMap = new Worldmap6T(2,2,expectLogger,entityFactory);
		worldMap2 = new Worldmap6T(2,2,expectLogger,entityFactory);
		
		//A TestFaction, Position and Tile
		faction = new Faction("a",0);
		position1 = new Position(0,0);
		Position position2 = new Position(1,0);
		Position position3 = new Position(0,1);
		Position position4 = new Position(1,1);
		
		waterTile1 = worldMap.createSeaTile(position1);
		waterTile2 = worldMap.createSeaTile(position2);
		islandTile1 = worldMap.createIslandTile(position3, true);
		islandTile2 = worldMap.createIslandTile(position4, false);
		
		waterTileMap2 = worldMap2.createSeaTile(position1);
		worldMap2.createSeaTile(position2);
		Tile tr = worldMap2.createIslandTile(position3, true);
		Tile ts = worldMap2.createIslandTile(position4, false);
		worldMap2.createTreasure(9, tr);
		worldMap2.createTreasure(9, ts);
		
		
		expectLogger.expect(new AddCell(Cell.WATER, null, 0, 0));
		expectLogger.expect(new AddCell(Cell.WATER, null, 1, 0));
		expectLogger.expect(new AddCell(Cell.SUPPLY, null, 0, 1));
		expectLogger.expect(new AddCell(Cell.ISLAND, null, 1, 1));
		
		
		//A Test ship of the TestFaction with ID 1, if ship is attaching itself
		ship = new Ship(faction,1,waterTile1);
		ship2 = new Ship(faction,1,waterTileMap2);
		
		//waterTile1.attach(ship);
		
	}
/*
 * Christopher Tests START
 */
	
	//GotoInstruction Tests
	
	/**
	 * goTo address getter check
	 */
	@Test
	public void goToAdressGetterTest(){
		GotoInstruction goToInstruction = new GotoInstruction(expectLogger,-11);
		
		goToInstruction.execute(ship);
		assertTrue(goToInstruction.getAddress() == -11);
	}
	
	/**
	 * testing if the pc after the goto is increased of the right amount
	 * and also checks if a null logger is handled correctly
	 */
	@Test
	public void goToCorrectJumpTest(){
		Instruction goToInstruction = new GotoInstruction(expectLogger,1);
		
		int pc = ship.getPC();
		try{
		goToInstruction.execute(ship);}
		catch (NullPointerException e){
			fail("NullPointerException, because logger is null");		
		}
		assertTrue(ship.getPC() == pc + 1);
	}
	
	
	
	/**
	 * testing if the jump to 0 is valid
	 */
	
	@Test
	public void goToCorrectZeroJumpTest(){
		Instruction goToInstruction = new GotoInstruction(expectLogger,0);
		
		//int pc = ship.getPC();
		try{
		goToInstruction.execute(ship);}
		catch (NullPointerException e){
			fail("NullPointerException because logger is null");		
		}
		assertTrue( 0 == ship.getPC());
	}
	
	/**
	 * checks if the logger/gui opens the notify method after receiving this
	 */
	
	@Test
	public void goToLoggerReceivesTest(){
		TestGui testGui = new TestGuiNotify();
		Instruction goToInstruction = new GotoInstruction(testGui,12);
		
		goToInstruction.execute(ship);
		assertTrue(Integer.toString(testGui.value), testGui.value == -1);
	}
	
	//DropInstruction Tests
	/**
	 * checks if notify and create method in gui is called
	 */
	@Test
	public void shipDropNotifyTest(){
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		Instruction dropInstruction = new DropInstruction(testGui);
		
		ship.setLoad(4);
		//checks if create and notify is called
		dropInstruction.execute(ship);
		assertTrue(testGui.value == -1);
		Key[] keys = {Key.VALUE, Key.X_COORD, Key.Y_COORD};
		int[] values = {4, ship.getMyTile().getPosition().x, ship.getMyTile().getPosition().y};
		expectLogger.expect(new Create(Entity.TREASURE, 0, keys, values));
	}
	
	/**
	 * checks if right amount of treasure value is on tile after drop
	 */
	
	@Test
	public void shipDropsLoadTest(){
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		Instruction dropInstruction = new DropInstruction(testGui);
		
		int val;
		try{
			val = ship.getMyTile().getTreasure().getValue();
		} catch (NullPointerException e) {
			val = 0;
		}
		ship.setLoad(4);
		//checks if there is a treasure on the watertile after dropping
		
		dropInstruction.execute(ship);
		assertTrue(val+4 == ship.getMyTile().getTreasure().getValue());
	}
	
	/**
	 * checks if PC is increased after Drop
	 */
	@Test
	public void dropInstructionIncreasePC(){
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		Instruction dropInstruction = new DropInstruction(testGui);
		
		int pc = ship.getPC();
		
		dropInstruction.execute(ship);
		assertTrue(pc+1 == ship.getPC());
	}
	
	// Markinstruction Tests
	
	/**
	 * checks if error raised if buoy id is >5
	 */
	@Test
	public void illegalArgMarkInstructionTest(){
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		try{
		Instruction markInstruction = new MarkInstruction(testGui,6);
		fail("Nothing thrown");
		}
		catch(IllegalArgumentException e){
			
		}
	}
	/**
	 * checks if error raised if buoy id is <0
	 */
	
	@Test
	public void illegalArgNegMarkInstructionTest(){
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		try{
		Instruction markInstruction = new MarkInstruction(testGui,-1);
		fail("Nothing thrown");
		}
		catch(IllegalArgumentException e){
			
		}
	}
	
	/**
	 * checks if ship marks the tile correctly
	 */
	@Test
	public void correctMarkInstructionTest(){
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		Instruction markInstruction = new MarkInstruction(testGui,0);
		
		markInstruction.execute(ship);
		List<Buoy> buoyList = ship.getMyTile().getBuoyMap().get(ship.getFaction());
		
		for(Buoy b :buoyList){
			if(b.getId() == 0){
				x += 1;
			}else{
				continue;
			}
		}
		assertTrue(x == 1);
	}
	
	/**
	 * checks if a tile is marked only once, if its marked already
	 */
	
	@Test
	public void CorrectMarkedTwiceTest(){
		
		//adds a buoy to a list in the Hashmap
		Tile water = worldMap.getTile(position1);
		Buoy buoy = new Buoy(0, null, 0, water);
		List<Buoy> xs = new ArrayList();
		xs.add(buoy);
		water.getBuoyMap().put(faction, xs);
		
		
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		Instruction markInstruction = new MarkInstruction(testGui,0);
		
		markInstruction.execute(ship);
		List<Buoy> buoyList = ship.getMyTile().getBuoyMap().get(ship.getFaction());
		
		//checks if there is only one buoy with ID 0 in the map
		for(Buoy b :buoyList){
			if(b.getId() == 0){
				x += 1;
			}else{
				continue;
			}
		}
		assertTrue(x == 1);
	}
	/**
	 * getter Test MarkInstruction
	 */
	
	@Test
	public void markInstructionGetterTest(){
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		MarkInstruction markInstruction = new MarkInstruction(testGui,4);
		
		assertTrue(markInstruction.getType() == 4);
	}
	
	//Sense Instruction
	
	/**
	 * getter Tests Sense Instruction
	 */
	
	@Test
	public void senseInstructionGetterTest(){
		Direction d = Direction.D3;
		ExpectLogger testGui = new ExpectLogger();
		SenseInstruction senseInstruction = new SenseInstruction(testGui, d );
		
		assertTrue(senseInstruction.getDir() == Direction.D3);
	}
	
	/**
	 * test if notify is called correctly
	 */
	@Test
	public void senseInstructionNotifyTest(){
		Direction d = Direction.D3;
		TestGui testGui = new TestGuiNotify();
		SenseInstruction senseInstruction = new SenseInstruction(testGui,d);
		
		senseInstruction.execute(ship);
		
		//checks if checkRegisters is called
		assertTrue(testGui.value == -3);
		
	}
	
	/**
	 * tests if other enemy ship is sense correctly
	 */
	@Test
	public void CorrectEnemyShipSenseTest(){
		//set a ship on another SeaTile of the TestMap
		Faction enemyFaction = new Faction("b",1);
		Ship enemyShip = new Ship(enemyFaction,2,waterTile2);
		
		
		Direction d = Direction.D0;
		TestGui testGui = new TestGuiNotify();
		SenseInstruction senseInstruction = new SenseInstruction(testGui,d);
		
		senseInstruction.execute(ship);
		Register enemyRegister = Register.SENSE_SHIPTYPE;
		assertTrue(ship.getRegister(enemyRegister) == ShipType.ENEMY.ordinal());
	}
	/**
	 * tests if ship of own faction is sensed correctly
	 */
	@Test
	public void CorrectOwnShipSenseTest(){
		//set a ship on another SeaTile of the TestMap
		Ship otherShip = new Ship(faction,2,waterTile2);
		
		Direction d = Direction.D0;
		TestGui testGui = new TestGuiNotify();
		SenseInstruction senseInstruction = new SenseInstruction(testGui,d);
		
		senseInstruction.execute(ship);
		Register reg = Register.SENSE_SHIPTYPE;
		assertTrue(ship.getRegister(reg) == ShipType.FRIEND.ordinal());
	}
	
	/**
	 * Tests if Island with treasure is sensed correctly
	 * 
	 */
	@Test
	public void treasureIslandSenseTest(){
		Direction d = Direction.D1;
		TestGui testGui = new TestGuiNotify();
		SenseInstruction senseInstruction = new SenseInstruction(testGui,d);
		
		senseInstruction.execute(ship2);
		
		Register reg = Register.SENSE_CELLTYPE;
		assertTrue(ship2.getRegister(reg) == CellType.ISLAND.ordinal());
		
		Register sup = Register.SENSE_SUPPLY;
		assertTrue(ship2.getRegister(sup) == 1);
		
		Register no = Register.SENSE_TREASURE;
		assertTrue(ship2.getRegister(no) == 1);
	}
	
	/**
	 * Tests if Island is sensed correctly
	 */
	@Test
	public void SupplyIslandSenseTest(){
		Direction d = Direction.D1;
		TestGui testGui = new TestGuiNotify();
		SenseInstruction senseInstruction = new SenseInstruction(testGui,d);
		
		senseInstruction.execute(ship);
		
		Register reg = Register.SENSE_CELLTYPE;
		assertTrue(ship.getRegister(reg) == CellType.ISLAND.ordinal());
		
		Register sup = Register.SENSE_SUPPLY;
		assertTrue(ship.getRegister(sup) == 1);
		
		Register no = Register.SENSE_TREASURE;
		assertTrue(ship.getRegister(no) == 0);
	}
	
	/**
	 * Sense Test own Buoy
	 */
	@Test
	public void OwnBuoySenseTest(){
		Direction d = Direction.D0;
		TestGui testGui = new TestGuiNotify();
		SenseInstruction senseInstruction = new SenseInstruction(testGui, d);
		Buoy b = worldMap.createBuoy(5, faction, waterTile2);
		
		senseInstruction.execute(ship);
		
		assertTrue(ship.getRegister(Register.SENSE_ENEMYMARKER) == 0);
		assertTrue(ship.getRegister(Register.SENSE_MARKER5) == 1);
		assertTrue(ship.getRegister(Register.SENSE_MARKER0) == 0);
		assertTrue(ship.getRegister(Register.SENSE_MARKER1) == 0);
		assertTrue(ship.getRegister(Register.SENSE_MARKER2) == 0);
		assertTrue(ship.getRegister(Register.SENSE_MARKER3) == 0);
		assertTrue(ship.getRegister(Register.SENSE_MARKER4) == 0);
		
	}
	
	/**
	 * Sense Test own Buoy
	 */
	@Test
	public void OwnBuoySenseTestvier(){
		Direction d = Direction.D0;
		TestGui testGui = new TestGuiNotify();
		SenseInstruction senseInstruction = new SenseInstruction(testGui, d);
		Buoy b = worldMap.createBuoy(4, faction, waterTile2);
		Buoy c = worldMap.createBuoy(3, faction, waterTile2);
		Buoy e = worldMap.createBuoy(2, faction, waterTile2);
		Buoy f = worldMap.createBuoy(1, faction, waterTile2);
		
		
		senseInstruction.execute(ship);
		
		assertTrue(ship.getRegister(Register.SENSE_ENEMYMARKER) == 0);
		assertTrue(ship.getRegister(Register.SENSE_MARKER5) == 0);
		assertTrue(ship.getRegister(Register.SENSE_MARKER0) == 0);
		assertTrue(ship.getRegister(Register.SENSE_MARKER1) == 1);
		assertTrue(ship.getRegister(Register.SENSE_MARKER2) == 1);
		assertTrue(ship.getRegister(Register.SENSE_MARKER3) == 1);
		assertTrue(ship.getRegister(Register.SENSE_MARKER4) == 1);
		
	}
	/**
	 * Sense Test enemy Buoy
	 */
	
	@Test
	public void EnemyBuoySenseTest(){
		Direction d = Direction.D0;
		TestGui testGui = new TestGuiNotify();
		SenseInstruction senseInstruction = new SenseInstruction(testGui, d);
		Faction enemyFaction = new Faction("b",1);
		Buoy b = worldMap.createBuoy(5, enemyFaction, waterTile2);
		
		senseInstruction.execute(ship);
		
		assertTrue(ship.getRegister(Register.SENSE_ENEMYMARKER) == 1);
		assertTrue(ship.getRegister(Register.SENSE_MARKER5) == 0);
		assertTrue(ship.getRegister(Register.SENSE_MARKER0) == 0);
		assertTrue(ship.getRegister(Register.SENSE_MARKER1) == 0);
		assertTrue(ship.getRegister(Register.SENSE_MARKER2) == 0);
		assertTrue(ship.getRegister(Register.SENSE_MARKER3) == 0);
		assertTrue(ship.getRegister(Register.SENSE_MARKER4) == 0);
		
	}
	
	//TurnInstruction
	/**
	 * checks if its a valid turn to the right
	 */
	@Test
	public void RightTurnTest(){
		
		TestGui testGui = new TestGuiNotify();
		TurnInstruction turnInstruction = new TurnInstruction(testGui,false);
		
		turnInstruction.execute(ship);
		assertTrue(ship.getHeading().equals(Heading.H1));
		assertFalse(turnInstruction.isLeft());
	}
	/**
	 * checks if its a valid turn to the left
	 */
	//TODO WAITTIME after turning?
	@Test
	public void LeftTurnTest(){
		
		TestGui testGui = new TestGuiNotify();
		TurnInstruction turnInstruction = new TurnInstruction(testGui,true);
		
		turnInstruction.execute(ship);
		assertTrue(ship.getHeading().equals(Heading.H5));
		assertTrue(turnInstruction.isLeft());
		
	}
	
	/**
	 * checks isleft
	 */
	@Test
	public void LeftTurnInstructionTest(){
		
		TestGui testGui = new TestGuiNotify();
		TurnInstruction turnInstruction = new TurnInstruction(testGui,true);
		
		turnInstruction.execute(ship);
		//assertTrue(ship.getHeading().equals(Heading.H5));
		assertTrue(turnInstruction.isLeft());
		
	}
	
	//UnMarkInstruction
	/**
	 * tests if an existing buoy is correctly deleted
	 */
	@Test
	public void UnmarkInstructionTest(){
		//add a buoy with id 1 to the tile
		Tile water = worldMap.getTile(position1);
		Buoy buoy = new Buoy(0, null, 0, water);
		List<Buoy> xs = new ArrayList();
		xs.add(buoy);
		water.getBuoyMap().put(faction, xs);
		
		
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		Instruction unmarkInstruction = new UnmarkInstruction(testGui,0);
		
		unmarkInstruction.execute(ship);
		List<Buoy> buoyList = ship.getMyTile().getBuoyMap().get(ship.getFaction());
		if(buoyList != null){
			for(Buoy b :buoyList){
				if(b.getId() == 0){
					fail("Buoy with id 1 is still in the list");
				}else{
					continue;
				}
			}
		}
	}
	
	
	/**
	 * TODO WHAT HAPPENS IF BUOY IS DELETED WHICH IS NOT ON THE TILE
	 */
	
	/**
	 * getter test
	 */
	@Test
	public void UnmarkInstructionGetterTest(){
		
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		UnmarkInstruction unmarkInstruction = new UnmarkInstruction(testGui,5);
		
		assertTrue(unmarkInstruction.getType() == 5);
	}

	
/*
 * Christopher Tests END
 */

}