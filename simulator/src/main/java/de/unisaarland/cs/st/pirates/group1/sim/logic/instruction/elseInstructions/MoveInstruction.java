package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Base;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Island;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Kraken;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Key;
import de.unisaarland.cs.st.pirates.group1.sim.util.CellType;
import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;

/**
 * Represents an instruction to move a {@link Ship}. Also handles all things that can go wrong:
 * Ship beaching on an {@link Island}
 * Ship fighting with another ship
 * Ship getting attacked by {@link Kraken}
 * Ship trying to move onto an enemy {@link Base}
 * @author thomas
 *
 */
public class MoveInstruction extends ElseInstruction {

	/**
	 * Creates the instruction.
	 * @param logger
	 * @param elsePC
	 */
	public MoveInstruction(ExtendedLogWriter logger, int elsePC) {
		super(logger, elsePC);
	}

	
	/**
	 * Main part of the Instruction, calls helper method according to Tile type
	 */
	@Override
	public void execute(Ship ship) {
		Tile ownTile = ship.getMyTile();
		Tile neighbourTile = ownTile.getNeighbour(ship.getHeading(), Direction.D0);
		switch (neighbourTile.navigable(ship)) {
		case EMPTY:
			this.tryMove(ship);
			break;
		case ENEMYHOME:
			this.elseJump(ship);
			break;
		case HOME:
			this.tryMove(ship);
			break;
		case ISLAND:
			this.beach(ship);
			break;
		default:
			throw new UnsupportedOperationException("There should be no other value of the enum");
		}
	}
	
	/**
	 * handles hitting an Island
	 * @param ship
	 */
	private void beach(Ship ship) {
		//lose health
		ship.setCondition(ship.getCondition() -1);
		logger.notify(Entity.SHIP, ship.getId(), Key.CONDITION, ship.getCondition());
		//lose morale if possible
		int morale = ship.getMorale();
		if(morale > 0){
			ship.setMorale(--morale);
			logger.notify(Entity.SHIP, ship.getId(), Key.MORAL, morale);
		}
		//lose treasure if possible
		if(ship.getLoad() > 0) {
			ship.setLoad(ship.getLoad()-1);
			logger.notify(Entity.SHIP, ship.getId(), Key.VALUE, ship.getLoad());
			ship.getMyTile().increaseTreasure(1);
		}
		//look if we are dead and if not do elsejump
		if(!cleanup(ship))
			super.elseJump(ship);
	}

	/**
	 * Tries to move a ship onto a water tile and initiates a fight if there is an enemy on the other tile
	 * @param ship
	 */
	private void tryMove(Ship ship) {
		Tile ownTile = ship.getMyTile();
		Tile neighbourTile = ownTile.getNeighbour(ship.getHeading(), Direction.D0);
		Ship otherShip = neighbourTile.getShip();
		//look if there is no ship on neighbourTile
		if(otherShip == null) {
			this.move(ship);
		}
		else {
			//there was a ship, now see if it belongs to our faction
			if(otherShip.getFaction() == ship.getFaction()) {
				//cancel move if it belongs to our faction
				elseJump(ship);
				return;
			}
			else {
				//enemy ship! let's fight!
				logger.fight(ship, otherShip);
				boolean aggressorWins = false;
				//find out who wins
				//first morale
				if(ship.getMorale() > otherShip.getMorale()) {
					//our ship wins!
					resolveFight(ship, otherShip);
					aggressorWins = true;
				}
				else if(ship.getMorale() < otherShip.getMorale()) {
					//enemy ship wins!
					resolveFight(otherShip, ship);
				}
				else {
					// now condition
					if(ship.getCondition() > otherShip.getCondition()) {
						//our ship wins!
						resolveFight(ship, otherShip);
						aggressorWins = true;
					}
					else if(ship.getCondition() < otherShip.getCondition()) {
						//enemy ship wins!
						resolveFight(otherShip, ship);
					}
					else {
						//now coin flip (get it from the Worldmap)
						if(ship.getMyTile().getWorldmap().random.nextInt(2) == 1) {
							//our ship wins!
							resolveFight(ship, otherShip);
							aggressorWins = true;
						}
						else {
							//enemy ship wins!
							resolveFight(otherShip, ship);
						}
					}
				}
				//now check if aggressor won and finish the move accordingly (cancel movement or move if defender is dead)
				if(aggressorWins) {
					//cleanup other ship to see if we can move
					if(this.cleanup(otherShip)) {
						//move the ship
						this.move(ship);
						return;
					}
					else {
						//we couldn't move as the other ship didn't die
						super.elseJump(ship);
					}
				}
				else {
					if(!this.cleanup(ship)) {
						//cleanup our ship and do the elsejump if we didn't die
						super.elseJump(ship);
						return;
					}
				}
			}
		}
	}

	/**
	 * Resolves a fight: Hurts the losing ship, demoralizes it and makes it lose treasure. Doesn't clean up though.
	 * (this method is a messie)
	 * @param winner
	 * @param loser
	 */
	private void resolveFight(Ship winner, Ship loser) {
		//other ship gets hurt
		int newCond = loser.getCondition();
		assert(newCond > 0);
		loser.setCondition(--newCond);
		logger.notify(Entity.SHIP, loser.getId(), Key.CONDITION, newCond);
		//other ship loses morale if possible
		int morale = loser.getMorale();
		if(morale > 0) {
			loser.setMorale(--morale);
			logger.notify(Entity.SHIP, loser.getId(), Key.MORAL, morale);
		}
		//now other ship loses treasure if possible
		int treasure = loser.getLoad();
		int capacity = Ship.getMaxload() - winner.getLoad();
		int newLoad = winner.getLoad();
		//no treasure, we are done
		if(treasure == 0)
			return;
		//there is some treasure, now look if all fits into the winner's cargo bay
		if(treasure <= capacity)
			newLoad += treasure;
		else {
			//winner couldn't take it all
			newLoad = Ship.getMaxload();
			treasure -= capacity;
			//some treasure drops on the loser ship's tile
			loser.getMyTile().increaseTreasure(treasure);
		}
		//winner takes some treasure
		winner.setLoad(newLoad);
		logger.notify(Entity.SHIP, winner.getId(), Key.VALUE, newLoad);
	}

	/**
	 * moves a ship onto a tile that can be moved onto (needs to be checked beforehand) and looks for krakens (also checks for base and increases morale accordingly)
	 * @param ship
	 */
	private void move(Ship ship) {
		Tile neighbourTile = ship.getMyTile().getNeighbour(ship.getHeading(), Direction.D0);
		ship.setMyTile(neighbourTile);
		
		//we have arrived, now look to see if we're home
		CellType tileType = neighbourTile.navigable(ship);
		assert(tileType == CellType.EMPTY || tileType == CellType.HOME);
		if(tileType == CellType.HOME) {
			ship.setMorale(Ship.getMaxmorale());
			logger.notify(Entity.SHIP, ship.getId(), Key.MORAL, Ship.getMaxmorale());
		}

		//calculate restTime
		int restTime = 4;
		//look for loaded ship
		restTime += ship.getLoad() > 0 ? 2 : 0;
		//look for demoralized crew
		restTime += ship.getMorale() == 0 ? 2 : 0;
		ship.setRestTime(restTime);
		logger.notify(Entity.SHIP, ship.getId(), Key.X_COORD, neighbourTile.getPosition().x);
		
		logger.notify(Entity.SHIP, ship.getId(), Key.Y_COORD, neighbourTile.getPosition().y);

		logger.notify(Entity.SHIP, ship.getId(), Key.RESTING, restTime);
		
		logger.notify(Entity.SHIP, ship.getId(), Key.PC, ship.increasePC());
		
		//now cycle if we didn't arrive on home base
		if(tileType != CellType.HOME) {
			super.cycle(ship);
		}
		else {
			//reset boredom
			ship.resetBoredom();
		}

		//last thing: check for kraken and maybe get destroyed
		if(neighbourTile.getKraken() != null) {
			//found kraken
			logger.fight(ship, neighbourTile.getKraken());
			ship.setCondition(ship.getCondition() -1);
			logger.notify(Entity.SHIP, ship.getId(), Key.CONDITION, ship.getCondition());
			this.cleanup(ship);
		}

	}

	/**
	 * Checks condition and kills a ship if it has condition 0
	 * @param ship
	 * @return If the ship was destroyed
	 */
	private boolean cleanup(Ship ship){
		if(ship.getCondition() == 0) {
			ship.setMyTile(null);
			logger.destroy(Entity.SHIP, ship.getId());
			ship.getFaction().removeShip();
		}
		return false;
		
	}

}
