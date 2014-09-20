package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import java.util.Random;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Base;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Island;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Kraken;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Key;
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

	@Override
	public void execute(Ship ship) {
		Tile ownTile = ship.getMyTile();
		Tile neighbourTile = ownTile.getNeighbour(ship.getHeading(), Direction.D0);
		switch (neighbourTile.navigable(ship)) {
		case EMPTY:
			this.tryMove(ship);
			break;
		case ENEMYHOME:
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

	private void resolveFight(Ship ship, Ship otherShip) {
		//other ship gets hurt
		int newCond = otherShip.getCondition();
		assert(newCond > 0);
		otherShip.setCondition(--newCond);
		logger.notify(Entity.SHIP, otherShip.getId(), Key.CONDITION, newCond);
		//other ship loses morale if possible
		int morale = otherShip.getMorale();
		if(morale > 0) {
			otherShip.setMorale(--morale);
			logger.notify(Entity.SHIP, otherShip.getId(), Key.MORAL, morale);
		}
		//now other ship loses treasure if possible
		int treasure = otherShip.getLoad();
		int capacity = Ship.getMaxload() - ship.getLoad();
		int newLoad = ship.getLoad();
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
			otherShip.getMyTile().increaseTreasure(treasure);
		}
		//winner takes some treasure
		ship.setLoad(newLoad);
		logger.notify(Entity.SHIP, ship.getId(), Key.VALUE, newLoad);
	}

	private void move(Ship ship) {
		Tile neighbourTile = ship.getMyTile().getNeighbour(ship.getHeading(), Direction.D0);
		ship.setMyTile(neighbourTile);
		logger.notify(Entity.SHIP, ship.getId(), Key.PC, ship.increasePC());

		//calculate restTime
		int restTime = 4;
		//look for loaded ship
		restTime += ship.getLoad() > 0 ? 2 : 0;
		//look for demoralized crew
		restTime += ship.getMorale() == 0 ? 2 : 0;
		ship.setRestTime(restTime);
		logger.notify(Entity.SHIP, ship.getId(), Key.RESTING, restTime);

		//now cycle
		super.cycle(ship);

		//last thing: check for kraken and maybe get destroyed
		if(neighbourTile.getKraken() != null) {
			//found kraken
			logger.fight(ship, neighbourTile.getKraken());
			ship.setCondition(ship.getCondition() -1);
			logger.notify(Entity.SHIP, ship.getId(), Key.CONDITION, ship.getCondition());
			this.cleanup(ship);
		}

	}

	private boolean cleanup(Ship ship){
		if(ship.getCondition() == 0) {
			ship.setMyTile(null);
			logger.destroy(Entity.SHIP, ship.getId());
			ship.getFaction().decreaseScore();
		}
		return false;
		
	}

}
