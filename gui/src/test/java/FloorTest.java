import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;



public class FloorTest {

	@Test
	public void testFloor() {
		// Math.floor() is tested
		assertEquals(Math.floor(2.0/3.0), 0.0);
		assertFalse(Math.floor(2.0/3.0 * 1000) == 0);
	}

}
