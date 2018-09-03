package Test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import booleanoo.BooleanValue;
import booleanoo.Conjunction;
import booleanoo.Disjunction;
import booleanoo.IffExpression;
import booleanoo.Implication;
import booleanoo.Negation;
import booleanoo.UnassignedVariableException;
import booleanoo.Variable;

public class ImplicationTest {
	HashMap context;
	@Before
	public void setUp() throws Exception {
		context = new HashMap<String,Boolean>();
		context.put("c", true);
		context.put("d", false);
	}

	@Test
	public void test() {
		Implication disj = new Implication(new Variable("c"),new Variable("c"));
		try {
			assertTrue(disj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test1() {
		Implication disj = new Implication(new Variable("d"),new Variable("c"));
		try {
			assertTrue(disj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2() {
		Implication disj = new Implication(new Variable("d"),new Variable("d"));
		try {
			assertTrue(disj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3() {
		Implication disj = new Implication(new Variable("c"),new Variable("d"));
		try {
			assertFalse(disj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test4() {
		Implication disj = new Implication(new Variable("c"),new Variable("a"));
		assertTrue(disj.simplify(context).toString().equals("a"));
	}
	
	@Test
	public void test5() {
		Implication disj = new Implication(new Variable("d"),new Variable("a"));
		assertTrue(disj.simplify(context).toString().equals("true"));
	}
	
	@Test
	public void test6() {
		Implication disj = new Implication(new Variable("a"),new Variable("d"));
		assertTrue(disj.simplify(context).toString().equals("(not a)"));
	}
	
	@Test
	public void test7() {
		Implication disj = new Implication(new Variable("a"),new Variable("a"));
		assertTrue(disj.simplify(context).toString().equals("true"));
	}
	
	@Test
	public void test8() {
		Implication disj = new Implication(new Variable("a"),new Variable("c"));
		assertTrue(disj.simplify(context).toString().equals("true"));
	}
	
	@Test
	public void test9() {
		context.put("y", false);
		Implication disj = new Implication(new Conjunction(new Variable("z"),new BooleanValue(false)),new Conjunction(new Variable("x"),new Variable("z")));
		assertTrue(disj.simplify(context).toString().equals("true"));
	}
	
	@Test
	public void test10() {
		context.put("y", false);
		Implication disj = new Implication(new Conjunction(new Variable("z"),new BooleanValue(true)),new Conjunction(new Variable("z"),new Variable("z")));
		assertTrue(disj.simplify(context).toString().equals("true"));
	}
	
	@Test
	public void test11() {
		context.put("y", true);
		Implication disj = new Implication(new Conjunction(new Variable("y"),new BooleanValue(true)),new Conjunction(new Variable("r"),new Variable("f")));
		assertTrue(disj.simplify(context).toString().equals("(r and f)"));
	}
	
	@Test
	public void test12() {
		context.put("y", true);
		Implication disj = new Implication(new Conjunction(new Variable("p"),new BooleanValue(false)),new Conjunction(new Variable("y"),new Variable("y")));
		assertTrue(disj.simplify(context).toString().equals("true"));
	}
	
	@Test
	public void test13() {
		context.put("y", false);
		Implication disj = new Implication(new Conjunction(new Variable("o"),new BooleanValue(true)),new Conjunction(new Variable("y"),new Variable("l")));
		assertTrue(disj.simplify(context).toString().equals("(not o)"));
	}
	
	@Test
	public void test14() {
		context.put("y", false);
		Implication disj = new Implication(new Conjunction(new Variable("o"),new Conjunction(new Variable("f"),new Variable("t"))),new Conjunction(new Variable("y"),new Variable("l")));
		assertTrue(disj.simplify(context).equals(new Negation((new Conjunction(new Variable("o"),new Conjunction(new Variable("f"),new Variable("t")))))));
	}
	
	@Test
	public void test15() {
		context.put("y", false);
		Implication disj = new Implication(new Conjunction(new Variable("o"),new Conjunction(new Variable("f"),new BooleanValue(false))),new Conjunction(new Variable("y"),new Variable("l")));
		assertTrue(disj.simplify(context).toString().equals("true"));
	}
	
	@Test
	public void test16() {
		context.put("y", false);
		context.put("o", true);
		Implication disj = new Implication(new Conjunction(new Variable("o"),new BooleanValue(true)),new Conjunction(new Variable("p"),new Variable("p")));
		assertTrue(disj.simplify(context).equals(new Variable("p")));
	}


}
