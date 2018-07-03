import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BishopMoveTest extends BishopMove {

	@Test
	public void testHowManyMoves() {
		assertEquals(1, howManyMoves(4, 6, 7, 3));
		assertEquals(0, howManyMoves(2,5,2, 5));
		assertEquals(2, howManyMoves(1, 3, 5, 5));
		assertEquals(-1, howManyMoves(4, 6, 7, 4));
	}

}
