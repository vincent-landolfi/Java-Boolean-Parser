package booleanoo;

import java.util.Map;

public class Negation extends UnaryExpression {

  /**
 * Constructor for the Negation object.
 * 
 * @param op
 *            The evaluation of the operand,
 */
  public Negation(BooleanExpression op) {
    // use super class' instructor
    super(new Not(), op);
  }

  @Override
  /**
 * Simplify the Negation expression.
 * 
 * @param context
 *            The given context to refer to.
 */
  public BooleanExpression simplify(Map<String, Boolean> context) {
    // instantiate return value
    BooleanExpression expr = null;
    // check if we have a doubly nested not
    if (this.getOperand().getClass().equals(Negation.class)) {
      // set the expression to the operand and simplify it 
      expr = (((Negation) this.getOperand()).getOperand()).simplify(context);
    // if we have not boolean
    } else if (this.getOperand().getClass().equals(BooleanValue.class)) {
      // just return the opposite of the boolean
      expr = new BooleanValue(!this.getOperand().toString().equals("true"));
    // if its not doubly nested but there's something to simplify
    } else if (this.somethingSimplifiable(context)) {
      // make a new negation simplifying the inside
      expr = new Negation(this.getOperand().simplify(context)).simplify(context);
    // if there is nothing to simplify, just return the original
    } else {
      // original
      expr = this;
    }
    // return the simplified expression
    return expr;
  }

  /**
 * Return a string representation of the negation.
 */
  public String toString() {
    // not expression surrounded by brackets
    return "(" + Constants.NOT + " " + this.getOperand().toString() + ")";
  }
  
}
