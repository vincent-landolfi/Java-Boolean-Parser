package Test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import booleanoo.BooleanValue;
import booleanoo.Conjunction;
import booleanoo.Disjunction;
import booleanoo.IffExpression;
import booleanoo.Negation;
import booleanoo.UnassignedVariableException;
import booleanoo.Variable;

public class IffTest {
	HashMap context;

	@Before
	public void setUp() throws Exception {
		context = new HashMap<String,Boolean>();
		context.put("c", true);
		context.put("d", false);
	}

	@Test
	public void test() {
		IffExpression disj = new IffExpression(new Variable("c"),new Variable("d"));
		try {
			assertFalse(disj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test1() {
		IffExpression disj = new IffExpression(new Variable("c"),new Variable("c"));
		try {
			assertTrue(disj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2() {
		IffExpression disj = new IffExpression(new Variable("d"),new Variable("c"));
		try {
			assertFalse(disj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3() {
		IffExpression disj = new IffExpression(new Variable("d"),new Variable("d"));
		try {
			assertTrue(disj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test4() {
		IffExpression disj = new IffExpression(new Variable("c"),new Variable("a"));
		assertTrue(disj.simplify(context).toString().equals("a"));
	}
	
	@Test
	public void test5() {
		IffExpression disj = new IffExpression(new Variable("a"),new Variable("c"));
		assertTrue(disj.simplify(context).toString().equals("a"));
	}
	
	@Test
	public void test6() {
		IffExpression disj = new IffExpression(new Variable("d"),new Variable("a"));
		assertTrue(disj.simplify(context).toString().equals("(not a)"));
	}
	
	@Test
	public void test7() {
		IffExpression disj = new IffExpression(new Variable("a"),new Variable("d"));
		assertTrue(disj.simplify(context).toString().equals("(not a)"));
	}
	
	@Test
	public void test8() {
		IffExpression disj = new IffExpression(new Variable("a"),new Variable("a"));
		assertTrue(disj.simplify(context).toString().equals("true"));
	}
	
	@Test
	public void test9() {
		IffExpression disj = new IffExpression(new Conjunction(new Variable("x"),new BooleanValue(true)),new Variable("x"));
		assertTrue(disj.simplify(context).toString().equals("true"));
	}
	
	@Test
	public void test10() {
		context.put("y", false);
		IffExpression disj = new IffExpression(new Conjunction(new Variable("x"),new BooleanValue(false)),new Variable("y"));
		assertTrue(disj.simplify(context).equals(new BooleanValue(true)));
	}
	
	@Test
	public void test11() {
		context.put("y", false);
		IffExpression disj = new IffExpression(new Variable("x"),new Conjunction(new Variable("z"),new BooleanValue(false)));
		assertTrue(disj.simplify(context).equals(new Negation(new Variable("x"))));
	}
	
	@Test
	public void test12() {
		context.put("y", false);
		IffExpression disj = new IffExpression(new Conjunction(new Variable("z"),new BooleanValue(false)),new Variable("x"));
		assertTrue(disj.simplify(context).toString().equals("(not x)"));
	}
	
	@Test
	public void test13() {
		context.put("x", true);
		IffExpression disj = new IffExpression(new Conjunction(new Variable("z"),new Variable("y")),new Variable("x"));
		assertTrue(disj.simplify(context).toString().equals("(z and y)"));
	}
	
	@Test
	public void test14() {
		context.put("y", false);
		IffExpression disj = new IffExpression(new Conjunction(new Variable("z"),new BooleanValue(false)),new Conjunction(new Variable("x"),new Variable("z")));
		assertTrue(disj.simplify(context).toString().equals("(not (x and z))"));
	}
	
	@Test
	public void test15() {
		context.put("y", false);
		IffExpression disj = new IffExpression(new Conjunction(new Variable("z"),new BooleanValue(true)),new Conjunction(new Variable("z"),new Variable("z")));
		assertTrue(disj.simplify(context).toString().equals("true"));
	}
	
	@Test
	public void test16() {
		context.put("y", false);
		IffExpression disj = new IffExpression(new Conjunction(new Variable("x"),new Variable("z")),new Conjunction(new Variable("z"),new BooleanValue(false)));
		assertTrue(disj.simplify(context).equals(new Negation(new Conjunction(new Variable("x"),new Variable("z")))));
	}
	
	@Test
	public void test17() {
		context.put("y", false);
		IffExpression disj = new IffExpression(new Conjunction(new Conjunction(new Variable("s"),new Variable("r")),new Variable("z")),new Conjunction(new Variable("z"),new BooleanValue(false)));
		assertTrue(disj.simplify(context).toString().equals("(not ((s and r) and z))"));
	}
	
	

}
