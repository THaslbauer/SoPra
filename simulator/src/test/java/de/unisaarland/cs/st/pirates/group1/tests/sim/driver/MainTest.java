package de.unisaarland.cs.st.pirates.group1.tests.sim.driver;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.main.Main;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.TestGui;

public class MainTest
{
	// this map is stored in the file map.txt. It is only shown for
	// debug purposes
	public String map = "5\n"
					  + "8\n"
					  + ".a.a."
					  + "..a.."
					  + "....."
					  + "##9##"
					  + "##9##"
					  + "....."
					  + "..b.."
					  + ".b.b.";
	
	// this tactic is stored in the file tacticA.txt. It is only shown
	// for debug purposes
	public String tacticA = "turn left\n"
						  + "move else 0\n"
						  + "pickup 4 else 0\n"
						  + "turn right\n"
						  + "turn right\n"
						  + "turn right\n"
						  + "move else 0\n"
						  + "drop\n"
						  + "turn right\n"
						  + "turn right\n"
						  + "turn right\n"
						  + "goto 0\n";
	
	@Before
	public void setUp()
	{
		System.setProperty("turns", "18");
		System.setProperty("seed", "100003");
	}
	
	@Test
	public void mainTest()
	{
		File a = new File(map);
		
		String[] arg = new String[3];
		arg[0]       = "./src/test/java/de/unisaarland/cs/st/pirates/group1/tests/testUtil/map.txt";
		arg[1]       = "./src/test/java/de/unisaarland/cs/st/pirates/group1/tests/testUtil/tacticA.txt";
		arg[2]       = "./src/test/java/de/unisaarland/cs/st/pirates/group1/tests/testUtil/tacticA.txt";
		
		Main.main(arg);
	}
	
}
