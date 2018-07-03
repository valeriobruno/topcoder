

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class BracketExpressionsTest extends BracketExpressions {

	BracketExpressions expr;
	
	@Before
	public void setup()
	{
		expr = new BracketExpressions();
	}
	@Test
	public void testEmpty() {
		Assert.assertEquals(BracketExpressions.POSSIBLE, expr.ifPossible(""));
	}
	
	@Test
	public void testSingleOpen() {
		Assert.assertEquals(BracketExpressions.IMPOSSIBLE, expr.ifPossible("("));
	}
	
	@Test
	public void testOpenClose() {
		Assert.assertEquals(BracketExpressions.POSSIBLE, expr.ifPossible("()"));
	}
	
	@Test
	public void testOpenXClose() {
		Assert.assertEquals(BracketExpressions.POSSIBLE, expr.ifPossible("X)"));
	}
	
	@Test
	public void testOpenXCloseX() {
		Assert.assertEquals(BracketExpressions.POSSIBLE, expr.ifPossible("XX"));
	}
	@Test
	public void testOpenCloseX() {
		Assert.assertEquals(BracketExpressions.POSSIBLE, expr.ifPossible("(X"));
	}

	@Test
	public void testSquaredCurly() {
		Assert.assertEquals(BracketExpressions.POSSIBLE, expr.ifPossible("[]{}"));
	}
	
	
	@Test
	public void test0() {
		String input = "([]{})";

		assertEquals(POSSIBLE, expr.ifPossible(input));
	}

	@Test
	public void test1() {
		String input = "(())[]";

		assertEquals(POSSIBLE, expr.ifPossible(input));
	}

	@Test
	public void test2() {
		String input = "({])";

		assertEquals(IMPOSSIBLE, expr.ifPossible(input));
	}

	@Test
	public void test3() {
		String input = "[]X";

		assertEquals(IMPOSSIBLE, expr.ifPossible(input));
	}

	@Test
	public void test4() {
		String input = "([]X()[()]XX}[])X{{}}]";

		assertEquals(POSSIBLE, expr.ifPossible(input));
	}

	@Test
	public void test5() {
		String input = "X{}[]()]X{X{}]X]";

		assertEquals(POSSIBLE, expr.ifPossible(input));
	}
}
