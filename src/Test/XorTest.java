package Test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import booleanoo.BooleanValue;
import booleanoo.Conjunction;
import booleanoo.IffExpression;
import booleanoo.Implication;
import booleanoo.UnassignedVariableException;
import booleanoo.Variable;
import booleanoo.XorExpression;

public class XorTest {
	HashMap context;
	@Before
	public void setUp() throws Exception {
		context = new HashMap<String,Boolean>();
		context.put("c", true);
		context.put("d", false);
	}

	@Test
	public void test() {
		XorExpression disj = new XorExpression(new Variable("c"),new Variable("d"));
		try {
			assertTrue(disj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test1() {
		XorExpression disj = new XorExpression(new Variable("d"),new Variable("c"));
		try {
			assertTrue(disj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2() {
		XorExpression disj = new XorExpression(new Variable("d"),new Variable("d"));
		try {
			assertFalse(disj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3() {
		XorExpression disj = new XorExpression(new Variable("c"),new Variable("c"));
		try {
			assertFalse(disj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test4() {
		XorExpression disj = new XorExpression(new Variable("a"),new Variable("c"));
		assertTrue(disj.simplify(context).toString().equals("(not a)"));
	}
	
	@Test
	public void test5() {
		XorExpression disj = new XorExpression(new Variable("a"),new Variable("d"));
		assertTrue(disj.simplify(context).toString().equals("a"));
	}
	
	@Test
	public void test6() {
		XorExpression disj = new XorExpression(new Variable("c"),new Variable("a"));
		assertTrue(disj.simplify(context).toString().equals("(not a)"));
	}
	
	@Test
	public void test7() {
		XorExpression disj = new XorExpression(new Variable("d"),new Variable("a"));
		assertTrue(disj.simplify(context).toString().equals("a"));
	}
	
	@Test
	public void test8() {
		XorExpression disj = new XorExpression(new Variable("a"),new Variable("a"));
		assertTrue(disj.simplify(context).toString().equals("false"));
	}
	
	@Test
	public void test9() {
		XorExpression xor = new XorExpression(new Conjunction(new Variable("s"),new Variable("s")),new Conjunction(new Variable("s"),new Variable("s")));
		assertTrue(xor.simplify(context).toString().equals("false"));
	}
	
	@Test
	public void test10() {
		XorExpression xor = new XorExpression(new Conjunction(new Variable("s"),new Variable("s")),new Conjunction(new BooleanValue(true),new BooleanValue(true)));
		assertTrue(xor.simplify(context).toString().equals("(not s)"));
	}
	
	@Test
	public void test11() {
		XorExpression xor = new XorExpression(new Conjunction(new BooleanValue(true),new BooleanValue(true)),new Conjunction(new Variable("s"),new Variable("s")));
		assertTrue(xor.simplify(context).toString().equals("(not s)"));
	}
	
	@Test
	public void test12() {
		XorExpression xor = new XorExpression(new Conjunction(new BooleanValue(true),new BooleanValue(true)),new Conjunction(new Variable("s"),new Variable("s")));
		assertTrue(xor.simplify(context).toString().equals("(not s)"));
	}
	
	@Test
	public void test13() {
		XorExpression xor = new XorExpression(new Conjunction(new BooleanValue(false),new BooleanValue(true)),new Conjunction(new Variable("s"),new Variable("e")));
		assertTrue(xor.simplify(context).equals(new Conjunction(new Variable("s"),new Variable("e"))));
	}
	
	@Test
	public void test14() {
		XorExpression xor = new XorExpression(new Conjunction(new BooleanValue(false),new BooleanValue(true)),new Conjunction(new Variable("e"),new Variable("e")));
		assertTrue(xor.simplify(context).toString().equals("e"));
	}
	
	@Test
	public void test15() {
		XorExpression xor = new XorExpression(new Conjunction(new Variable("e"),new Variable("e")),new Conjunction(new BooleanValue(false),new BooleanValue(true)));
		assertTrue(xor.simplify(context).toString().equals("e"));
	}
	
	@Test
	public void test16() {
		XorExpression xor = new XorExpression(new Conjunction(new Variable("e"),new Variable("s")),new Conjunction(new BooleanValue(false),new BooleanValue(true)));
		assertTrue(xor.simplify(context).toString().equals("(e and s)"));
	}
	
	@Test
	public void test17() {
		context.put("o", true);
		XorExpression xor = new XorExpression(new Implication(new Conjunction(new Variable("o"),new BooleanValue(true)),new Conjunction(new Variable("p"),new Variable("p"))),new Conjunction(new BooleanValue(false),new BooleanValue(true)));
		assertTrue(xor.simplify(context).toString().equals("p"));
	}
	
	@Test
	public void test18() {
		context.put("o", true);
		XorExpression xor = new XorExpression(new Conjunction(new BooleanValue(true),new BooleanValue(true)),new Implication(new Conjunction(new Variable("o"),new BooleanValue(true)),new Conjunction(new Variable("p"),new Variable("p"))));
		assertTrue(xor.simplify(context).toString().equals("(not p)"));
	}
	
	@Test
	public void test19() {
		context.put("o", true);
		XorExpression xor = new XorExpression(new Implication(new Conjunction(new Variable("o"),new BooleanValue(true)),new Conjunction(new Variable("p"),new Variable("p"))),new Implication(new Conjunction(new Variable("o"),new BooleanValue(true)),new Conjunction(new Variable("p"),new Variable("p"))));
		assertTrue(xor.simplify(context).toString().equals("false"));
	}
}
