package booleanoo;

import java.util.Map;

public class Implication extends BinaryExpression {

  /**
 * Constructor for the Implication object
 * 
 * @param left
 *            Left side of the implication expression.
 * @param right
 *            Right side of the implication expression.
 */
  public Implication(BooleanExpression left, BooleanExpression right) {
    // use the constructor from the super class
    super(new Implies(), left, right);
  }

  @Override
  /**
 * Simplify the Implication expression.
 * 
 * @param context
 *            The given context to refer to.
 */
  public BooleanExpression simplify(Map<String, Boolean> context) {
    // preset the return value to null
    BooleanExpression expr = null;
    // assume left and right sides are true
    Boolean leftIsEvaluatable = true;
    Boolean rightIsEvaluatable = true;
    // if left side is not evaluatable
    if (this.leftIsTrue(context) == null) {
      // set var accordingly
      leftIsEvaluatable = false;
    }
    // if right side is not evaluatable
    if (this.rightIsTrue(context) == null) {
      // set var accordingly
      rightIsEvaluatable = false;
    }
    // if left side is true
    if (leftIsEvaluatable && this.leftIsTrue(context)) {
      // set return value to right side
      expr = this.getRight().simplify(context);
    // if left is false, right side is true, or left and right sides are
    // identical
    } else if ((leftIsEvaluatable && !this.leftIsTrue(context)) 
        || (rightIsEvaluatable && this.rightIsTrue(context))
        || this.getLeft().toString().equals(this.getRight().toString())) {
      // set return expression to true
      expr = new BooleanValue(true);
    // if right side is false
    } else if (rightIsEvaluatable && !this.rightIsTrue(context)) {
      // set return expression to negation of left side
      expr = (new Negation(this.getLeft().simplify(context))).simplify(context);
    // if no conditions are met
    } else {
      // check if there is anything we can simplify
      if (this.somethingSimplifiable(context)) {
        // simplify the left and right sides
        expr = new Implication(this.getLeft().simplify(context),
          this.getRight().simplify(context)).simplify(context);
      // if theres nothing to simplify
      } else {
        // set the expression to the original
        expr = this;
      }
    }
    // return the simplified expression
    return expr;
  }

  /**
 * String representation of the implication expression.
 */
  public String toString() {
    // left implies right surrounded by brackets
    return "(" + this.getLeft().toString() + " " + Constants.IMPLIES + " " 
        + this.getRight().toString() + ")";
  }
  
}
