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

public class NotTest {
	HashMap context;
	@Before
	public void setUp() throws Exception {
		context = new HashMap<String,Boolean>();
		context.put("c", true);
		context.put("d", false);
	}

	@Test
	public void test() {
		Negation disj = new Negation(new Variable("d"));
		try {
			assertTrue(disj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test1() {
		Negation disj = new Negation(new Variable("c"));
		try {
			assertFalse(disj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2() {
		Negation disj = new Negation(new Negation(new Variable("a")));
		assertTrue(disj.simplify(context).toString().equals("a"));
	}
	
	@Test
	public void test3() {
		Negation disj = new Negation(new Negation(new Conjunction(new Variable("x"),new Variable("y"))));
		assertTrue(disj.simplify(context).toString().equals("(x and y)"));
	}
	
	@Test
	public void test4() {
		Negation disj = new Negation(new Negation(new Conjunction(new Variable("x"),new Conjunction(new BooleanValue(true),new Variable("x")))));
		assertTrue(disj.simplify(context).toString().equals("x"));
	}
	
	@Test
	public void test5() {
		Negation disj = new Negation(new Conjunction(new Variable("x"),new Variable("x")));
		assertTrue(disj.simplify(context).toString().equals("(not x)"));
	}
	
	@Test
	public void test6() {
		context.put("y", false);
		Negation disj = new Negation(new Negation(new IffExpression(new Conjunction(new Conjunction(new Variable("s"),new Variable("r")),new Variable("z")),new Conjunction(new Variable("z"),new BooleanValue(false)))));
		assertTrue(disj.simplify(context).equals(new Negation(new Conjunction(new Conjunction(new Variable("s"),new Variable("r")),new Variable("z")))));
	}
	
	@Test
	public void test7() {
		context.put("y", false);
		Negation disj = new Negation(new IffExpression(new Conjunction(new Variable("z"),new BooleanValue(false)),new Conjunction(new Variable("x"),new Variable("z"))));
		assertTrue(disj.simplify(context).toString().equals("(x and z)"));
	}
	
	@Test
	public void test8() {
		Negation neg = new Negation(new Variable("c"));
		assertTrue(neg.simplify(context).equals(new BooleanValue(false)));
	}

}
