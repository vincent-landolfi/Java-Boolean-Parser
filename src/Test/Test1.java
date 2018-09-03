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

public class Test1 {

	HashMap context;
	@Before
	public void setUp() throws Exception {
		context = new HashMap <String,Boolean>();
		context.put("c", true);
		context.put("a", true);
		context.put("b", false);
	}

	@Test
	public void test() {
		Conjunction conj = new Conjunction(new BooleanValue(true),new BooleanValue(false));
		try {
			assertFalse(conj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test1() {
		Conjunction conj = new Conjunction(new BooleanValue(false),new BooleanValue(true));
		try {
			assertFalse(conj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2() {
		Conjunction conj = new Conjunction(new BooleanValue(true),new BooleanValue(true));
		try {
			assertTrue(conj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3() {
		Conjunction conj = new Conjunction(new BooleanValue(false),new BooleanValue(false));
		try {
			assertFalse(conj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test4() {
		Conjunction conj = new Conjunction(new Variable("e"),new Variable("e"));
		assertTrue(conj.simplify(context).toString().equals("e"));
	}
	
	@Test
	public void test5() {
		Conjunction conj = new Conjunction(new BooleanValue(true),new Variable("d"));
		assertTrue(conj.simplify(context).toString().equals("d"));
	}
	
	@Test
	public void test6() {
		Conjunction conj = new Conjunction(new Variable("e"),new Variable("d"));
		assertTrue(conj.simplify(context).toString().equals("(e and d)"));
	}

	@Test
	public void test7() {
		Conjunction conj = new Conjunction(new Variable("a"),new BooleanValue(false));
		assertTrue(conj.simplify(context).toString().equals("false"));
	}
	
	@Test
	public void test8() {
		Conjunction conj = new Conjunction(new BooleanValue(false),new Variable("a"));
		assertTrue(conj.simplify(context).toString().equals("false"));
	}
	
	@Test
	public void test9() {
		Conjunction conj = new Conjunction(new BooleanValue(false),new Variable("a"));
		assertTrue(conj.simplify(context).toString().equals("false"));
	}
	
	@Test
	public void test10() {
		Conjunction conj = new Conjunction(new Variable("a"),new Disjunction(new Variable("b"),new Variable("c")));
		try {
			assertTrue(conj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test11() {
		Conjunction conj = new Conjunction(new Disjunction(new Variable("b"),new Variable("c")),new Variable("a"));
		try {
			assertTrue(conj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test12() {
		Conjunction conj = new Conjunction(new Negation(new Variable("b")), new Variable("a"));
		try {
			assertTrue(conj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test13() {
		Conjunction conj = new Conjunction(new Negation(new Variable("b")), new Conjunction(new Variable("b"),new Disjunction(new Variable("c"),new Conjunction(new Variable("a"),new Disjunction(new Variable("c"),new Variable("b"))))));
		try {
			assertFalse(conj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test14() {
		Variable conj = new Variable("a");
		try {
			assertTrue(conj.evaluate(context));
		} catch (UnassignedVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test15() {
		Conjunction conj = new Conjunction(new Variable("a"),new Disjunction(new Variable("b"),new Variable("c")));
		assertTrue(conj.simplify(context).toString().equals("true"));
	}
	
	@Test
	public void test16() {
		context.put("d", true);
		context.put("e", false);
		Conjunction conj = new Conjunction(new Variable("d"),new Disjunction(new Variable("e"),new Conjunction(new Variable("f"),new Variable("d"))));
		assertTrue(conj.simplify(context).toString().equals("f"));
	}
	
	@Test
	public void test17() {
		Conjunction conj = new Conjunction(new Conjunction(new Variable("r"),new Variable("r")),new Conjunction(new Variable("t"),new Variable("t")));
		assertTrue(conj.simplify(context).toString().equals("(r and t)"));
	}
	
	

}
