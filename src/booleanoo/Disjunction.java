package booleanoo;

import java.util.Map;

public class Disjunction extends BinaryExpression {

  /**
 * Constructor for Disjunction.
 * 
 * @param left
 *            Left side of the disjunction.
 * @param right
 *            Right side of the disjunction.
 */
  public Disjunction(BooleanExpression left, BooleanExpression right) {
    // use super class to instantiate new Disjunction
    super(new Or(), left, right);
  }

  @Override
  /**
 * String representation of the disjunction.
 */
  public String toString() {
    // left or right surrounded by brackets
    return "(" + this.getLeft().toString() + " " + Constants.OR + " " 
        + this.getRight().toString() + ")";
  }

  @Override
  /**
 * Simplifies the given disjunction.
 * 
 * @param context
 *            The given context to refer to.
 */
  public BooleanExpression simplify(Map<String, Boolean> context) {
    // preset return expression
    BooleanExpression expr = null;
    // assume left and right sides are evaluatable
    Boolean leftIsEvaluatable = true;
    Boolean rightIsEvaluatable = true;
    // if left side doesnt evaluate
    if (this.leftIsTrue(context) == null) {
      // set var accordingly
      leftIsEvaluatable = false;
    }
    // if right side doesnt evaluate
    if (this.rightIsTrue(context) == null) {
      // set var accordingly
      rightIsEvaluatable = false;
    }
    // if either sides is true
    if ((rightIsEvaluatable && this.rightIsTrue(context)) 
        || (leftIsEvaluatable && this.leftIsTrue(context))) {
      // set return value to true
      expr = new BooleanValue(true);
    // if right is false, or both sides are identical
    } else if ((rightIsEvaluatable && !this.rightIsTrue(context))
        || this.getLeft().toString().equals(this.getRight().toString())) {
      // set return value to the left side of the expression simplified
      expr = this.getLeft().simplify(context);
    // if left side is false
    } else if ((leftIsEvaluatable && !this.leftIsTrue(context))) {
      // set return value to right side simplified
      expr = this.getRight().simplify(context);
    // if none of the above conditions are met
    } else {
      // if there is something simplifiable in the expression
      if (this.somethingSimplifiable(context)) {
        // simplify the left and right sides
        expr = new Disjunction(this.getLeft().simplify(context),
          this.getRight().simplify(context)).simplify(context);
      // if nothing is simplifiable
      } else {
        // just return the expression
        expr = this;
      }
    }
    // return simplified expression
    return expr;
  }
  
}
