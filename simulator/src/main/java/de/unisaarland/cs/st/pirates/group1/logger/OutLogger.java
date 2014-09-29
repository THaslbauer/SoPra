package de.unisaarland.cs.st.pirates.group1.logger;


import java.io.IOException;
import java.io.OutputStream;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Kraken;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.logger.LogWriter;

public class OutLogger implements ExtendedLogWriter {
	
	public boolean printTactics = false;
	private int [] points = new int[] {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
	
	@Override
	public LogWriter addCell(Cell type, Integer faction, int x, int y)
			throws NullPointerException, ArrayIndexOutOfBoundsException,
			IllegalArgumentException, IllegalStateException {
		
		System.out.println(new AddCell(type, faction, x, y));
		return this;
	}

	@Override
	public LogWriter addCustomHeaderData(String data)
			throws NullPointerException, ArrayIndexOutOfBoundsException,
			IllegalStateException {
		System.out.println("HEADER:\n"+data);
		return null;
	}

	@Override
	public Transaction beginTransaction(Entity entity, int id)
			throws NullPointerException, IllegalArgumentException,
			IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() throws IllegalStateException, IOException {
		System.out.println("Log closed!\n\nScore:\n");
		int a = 0;
		for(int i : points) 
			if(points[a] != -1)
				System.out.println("Faction "+a+++": "+i+" Points");
		
		
		
	}

	@Override
	public LogWriter commitTransaction(Transaction transaction)
			throws NullPointerException, IllegalArgumentException,
			IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogWriter create(Entity entity, int id, Key[] keys, int[] values)
			throws NullPointerException, IllegalArgumentException,
			ArrayIndexOutOfBoundsException, IllegalStateException {
		
		System.out.println(new Create(entity, id, keys, values));
		return this;
	}

	@Override
	public LogWriter destroy(Entity entity, int id)
			throws NullPointerException, IllegalArgumentException,
			IllegalStateException {
		System.out.println(new Destroy(entity, id));
		return this;
	}

	@Override
	public LogWriter fleetScore(int id, int value)
			throws IllegalArgumentException, ArrayIndexOutOfBoundsException,
			IllegalStateException {
		System.out.println("Faction "+id+" scored "+value+" Points!");
		points[id] = value;
		return this;
	}

	@Override
	public void init(OutputStream logStream, String map, String... programs)
			throws NullPointerException, IOException,
			ArrayIndexOutOfBoundsException {
		System.out.println("MAP:\n"+map+"\n\n");
		if(!printTactics)
			return;
		System.out.println("TACTICS:\n");
		for(String s : programs) {
			System.out.println(s+"\n");
		}
		System.out.println("\n");
		
	}

	@Override
	public void logStep() throws IllegalStateException, IOException {
		System.out.println(new LogStep());
	}

	@Override
	public LogWriter notify(Entity entity, int id, Key key, int value)
			throws NullPointerException, IllegalArgumentException,
			IllegalStateException {
		System.out.println(new Notify(entity, id, key, value));
		return this;
	}

	@Override
	public void fight(Ship ship, Ship otherShip) {
		System.out.println(new Fight(ship, otherShip));
	}

	@Override
	public void fight(Ship ship, Kraken kraken) {
		System.out.println(new Fight(ship, kraken));
		
	}

	@Override
	public void registerChange(Ship ship) {
		// TODO Auto-generated method stub
		
	}

}
