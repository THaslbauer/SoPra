package gui.game.objects;

public class Buoy extends Passive {
	public final int faction;
	public final int type;
	
	public Buoy(int x, int y, int f, int t) {
		super(x,y);
		faction = f;
		type = t;
	}
	
}
