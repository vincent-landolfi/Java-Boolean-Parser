package Test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import booleanoo.Conjunction;
import booleanoo.Disjunction;
import booleanoo.Negation;
import booleanoo.UnassignedVariableException;
import booleanoo.Variable;
import booleanoo.BooleanValue;

public class DisjunctionTest {
	HashMap context;
	@Before
	public void setUp() throws Exception {
		context = new HashMap<String,Boolean>();
		context.put("c", true);
		context.put("d", false);
		context.put("e", false);
	}

	@Test
	public void test() {
		Disjunction disj = new Disjunction(new BooleanValue(true),new BooleanValue(false));
		try {
			assertTrue(disj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test1() {
		Disjunction disj = new Disjunction(new BooleanValue(false),new BooleanValue(true));
		try {
			assertTrue(disj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2() {
		Disjunction disj = new Disjunction(new BooleanValue(false),new BooleanValue(false));
		try {
			assertFalse(disj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3() {
		Disjunction disj = new Disjunction(new BooleanValue(true),new BooleanValue(true));
		try {
			assertTrue(disj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test4() {
		Disjunction disj = new Disjunction(new Variable("a"),new Variable("a"));
		assertTrue(disj.simplify(context).toString().equals("a"));
	}
	
	@Test
	public void test5() {
		Disjunction disj = new Disjunction(new Variable("c"),new Variable("a"));
		assertTrue(disj.simplify(context).toString().equals("true"));
	}
	
	@Test
	public void test10() {
		Disjunction disj = new Disjunction(new Variable("a"),new Variable("c"));
		assertTrue(disj.simplify(context).toString().equals("true"));
	}
	
	@Test
	public void test6() {
		Disjunction disj = new Disjunction(new Variable("a"),new BooleanValue(true));
		assertTrue(disj.simplify(context).toString().equals("true"));
	}
	
	@Test
	public void test7() {
		Disjunction disj = new Disjunction(new Variable("c"),new BooleanValue(false));
		assertTrue(disj.simplify(context).toString().equals("true"));
	}
	
	@Test
	public void test8() {
		Disjunction disj = new Disjunction(new Variable("d"),new Variable("a"));
		assertTrue(disj.simplify(context).toString().equals("a"));
	}
	
	@Test
	public void test9() {
		Disjunction disj = new Disjunction(new Variable("a"),new Variable("d"));
		assertTrue(disj.simplify(context).toString().equals("a"));
	}
	
	@Test
	public void test11() {
		Disjunction disj = new Disjunction(new Conjunction(new Variable("c"),new Negation(new Variable("e"))),new Disjunction(new Conjunction(new Variable("c"),new Variable("d")),new Conjunction(new Negation(new Variable("e")),new Variable("d"))));
		try {
			assertTrue(disj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test12() {
		context.put("z", false);
		Disjunction disj = new Disjunction(new Conjunction(new Variable("x"),new Variable("y")),new Negation(new Variable("z")));
		assertTrue(disj.simplify(context).toString().equals("true"));
	}
	
	@Test
	public void test13() {
		context.put("z", true);
		Conjunction disj = new Conjunction(new Disjunction(new Conjunction(new Variable("x"),new Variable("y")),new Negation(new Variable("z"))),new Conjunction(new Variable("x"),new Variable("y")));
		assertTrue(disj.simplify(context).toString().equals("(x and y)"));
	}
	
	@Test
	public void test14() {
		context.put("z", false);
		Conjunction disj = new Conjunction(new Negation(new Variable("z")),new Conjunction(new Variable("x"),new Variable("y")));
		assertTrue(disj.simplify(context).toString().equals("(x and y)"));
	}
	
	@Test
	public void test15() {
		context.put("z", false);
		Conjunction disj = new Conjunction(new Conjunction(new Variable("x"),new Variable("y")),new Disjunction(new Conjunction(new Variable("x"),new Variable("y")),new Negation(new Variable("z"))));
		assertTrue(disj.simplify(context).toString().equals("(x and y)"));
	}
	
	@Test
	public void test16() {
		context.put("z", true);
		Conjunction disj = new Conjunction(new Conjunction(new Conjunction(new Variable("x"),new Variable("y")),new Variable ("w")),new Variable("z"));
		assertTrue(disj.simplify(context).toString().equals("((x and y) and w)"));
	}
	
	@Test
	public void test17() {
		context.put("z", true);
		Conjunction disj = new Conjunction(new Conjunction(new Variable("r"),new Variable("f")),new Conjunction(new Conjunction(new Variable("x"),new Variable("y")),new Variable("z")));
		assertTrue(disj.simplify(context).toString().equals("((r and f) and (x and y))"));
	}
	
	@Test
	public void test18() {
		context.put("z", true);
		Conjunction disj = new Conjunction(new Conjunction(new BooleanValue(true),new Variable("g")),new Variable("g"));
		assertTrue(disj.simplify(context).toString().equals("g"));
	}
	
	@Test
	public void test19() {
		context.put("z", true);
		Conjunction disj = new Conjunction(new Conjunction(new BooleanValue(false),new Variable("g")),new BooleanValue(false));
		assertTrue(disj.simplify(context).toString().equals("false"));
	}
	
	@Test
	public void test20() {
		context.put("x", true);
		context.put("y", false);
		context.put("z", true);
		Disjunction disj = new Disjunction(new Conjunction(new Variable("x"),new Negation(new Variable("w"))),new Disjunction(new Conjunction(new Variable("x"),new Variable("y")),new Conjunction(new Negation(new Variable("w")),new Variable("y"))));
		assertTrue(disj.simplify(context).toString().equals("(not w)"));
	}
	
	@Test
	public void test21() {
		context.put("y", true);
		Conjunction conj = new Conjunction(new Negation(new Variable("w")),new Conjunction(new Variable("z"),new Variable("y")));
		assertTrue(conj.simplify(context).toString().equals("((not w) and z)"));
	}
	
	@Test
	public void test22() {
		context.put("y", true);
		context.put("x", false);
		Disjunction conj = new Disjunction(new Conjunction(new Variable("w"),new Negation(new Variable("x"))),new Disjunction(new Conjunction(new Variable("w"),new Variable("y")),new Conjunction(new Negation(new Variable("x")),new Variable("y"))));
		assertTrue(conj.simplify(context).toString().equals("true"));
	}
	
	@Test
	public void test23() {
		Disjunction disj = new Disjunction(new BooleanValue(false),new Conjunction(new Variable("a"),new Variable("a")));
		assertTrue(disj.simplify(context).toString().equals("a"));
	}

}
