import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class InvariantSetsTest {

	InvariantSets cls;
	
	@Before
	public void setup()
	{
		cls =  new InvariantSets();
	}
	
	@Test
	public void testEmptySet() {
		assertEquals(1,cls.countSets(new int[0]));
	}
	
	@Test
	public void testSingleton() {
		int[] input = {0};
		assertEquals(2,cls.countSets(input));
	}
	
	@Test
	public void test0() {

		int[] input = { 1, 0, 0, 0 };
		assertEquals(5, cls.countSets(input));
	}

	@Test
	public void test1() {

		int[] input = { 1, 2, 0 };
		assertEquals(2, cls.countSets(input));
	}

	@Test
	public void test2() {

		int[] input = { 0, 0, 1, 2 };
		assertEquals(5, cls.countSets(input));
	}

	@Test
	public void test3() {

		int[] input = { 0, 1, 2, 3, 4, 5 };
		assertEquals(64, cls.countSets(input));
	}

	@Test
	public void test4() {

		int[] input = { 12, 10, 0, 4, 0, 6, 3, 8, 9, 14, 1, 5, 6, 12, 5 };
		assertEquals(90, cls.countSets(input));
	}

	@Test
	public void test5() {

		int[] input = { 1, 1, 1, 1, 0, 6, 2, 1, 3, 2, 2, 2, 5, 6, 5, 3, 2, 4,
				5, 4, 2, 2, 3, 4, 5, 5, 6, 7, 3, 4, 4, 5, 2, 3, 4, 5, 3, 2 };
		assertEquals(1703383111, cls.countSets(input));
	}
	
	
}
