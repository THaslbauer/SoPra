package de.unisaarland.cs.st.pirates.group1.tests.testUtil;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.logger.LogWriter;

public class TestLoggerWithMapChecks extends DumbExtendedLogWriter {
	//saves a boolean that tells if a method has changed since last view
	//0 : addCell
	//1 : create
	//2 : destroy
	boolean[] hasChanged = new boolean[3];
	//fields for addCell
	Cell cell = null;
	Integer faction = -1;
	int cellX = -1;
	int cellY = -1;
	//fields for create
	Entity createdEntity = null;
	int createdID = 0;
	Key[] createdKeys = null;
	int[] createdVals = null;
	//fields for destroy
	Entity destroyedEntity = null;
	int destroyedID = 0;

	@Override
	public LogWriter addCell(Cell type, Integer faction, int x, int y)
			throws NullPointerException, ArrayIndexOutOfBoundsException,
			IllegalArgumentException, IllegalStateException {
		hasChanged[0] = true;
		this.cell = type;
		this.faction = faction;
		this.cellX = x;
		this.cellY = y;
		return super.addCell(type, faction, x, y);
	}

	@Override
	public LogWriter create(Entity entity, int id, Key[] keys, int[] values)
			throws NullPointerException, IllegalArgumentException,
			ArrayIndexOutOfBoundsException, IllegalStateException {
		hasChanged[1] = true;
		this.createdEntity = entity;
		this.createdID = id;
		this.createdKeys = keys;
		this.createdVals = values;
		return super.create(entity, id, keys, values);
	}

	@Override
	public LogWriter destroy(Entity entity, int id)
			throws NullPointerException, IllegalArgumentException,
			IllegalStateException {
		hasChanged[2] = true;
		this.destroyedEntity = entity;
		this.destroyedID = id;
		return super.destroy(entity, id);
	}
	
	public boolean addCellChanged(){
		boolean changed = hasChanged[0];
		hasChanged[0] = false;
		return changed;
	}

	public boolean createChanged(){
		boolean changed = hasChanged[1];
		hasChanged[1] = false;
		return changed;
	}

	public boolean destroyChanged(){
		boolean changed = hasChanged[2];
		hasChanged[2] = false;
		return changed;
	}

	public Cell getCell() {
		return cell;
	}

	public Integer getFaction() {
		return faction;
	}

	public int getCellX() {
		return cellX;
	}

	public int getCellY() {
		return cellY;
	}

	public Entity getCreatedEntity() {
		return createdEntity;
	}

	public int getCreatedID() {
		return createdID;
	}

	public Key[] getCreatedKeys() {
		return createdKeys;
	}

	public int[] getCreatedVals() {
		return createdVals;
	}

	public Entity getDestroyedEntity() {
		return destroyedEntity;
	}

	public int getDestroyedID() {
		return destroyedID;
	}

}
