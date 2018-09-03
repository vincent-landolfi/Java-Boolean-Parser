package booleanoo;

import java.util.Map;

public class Conjunction extends BinaryExpression {

  /**
 * Constructor to build a new Conjunction.
 * 
 * @param left
 *            Left side of the conjunction.
 * @param right
 *            Right side of the conjunction.
 */
  public Conjunction(BooleanExpression left, BooleanExpression right) {
    // use the super class constructor
    super(new And(), left, right);
  }

  @Override
  /**
 * Simplifies the given Conjunction
 * 
 * @param context
 *            The given context to refer to.
 */
  public BooleanExpression simplify(Map<String, Boolean> context) {
    // instantiate return value to null
    BooleanExpression expr = null;
    // assume both sides are evaluatable
    Boolean leftIsEvaluatable = true;
    Boolean rightIsEvaluatable = true;
    // if left isnt evaluatable
    if (this.leftIsTrue(context) == null) {
      // set variable accordingly
      leftIsEvaluatable = false;
    }
    // if right isnt evaluatable
    if (this.rightIsTrue(context) == null) {
      // set variable accordingly
      rightIsEvaluatable = false;
    }
    // if both sides are the same, or the right side evaluates to true
    if (this.leftAndRightAreEqual() || (rightIsEvaluatable && this.rightIsTrue(context))) {
      // set return value to left side simplified
      expr = this.getLeft().simplify(context);
      // if left evaluates to true
    } else if (leftIsEvaluatable && this.leftIsTrue(context)) {
      // set return value to right side simplified
      expr = this.getRight().simplify(context);
      // if either side evaluates to false
    } else if ((leftIsEvaluatable && !this.leftIsTrue(context))
        || (rightIsEvaluatable && !this.rightIsTrue(context))) {
      // set return value to false
      expr = new BooleanValue(false);
    // if no simplification is reached
    } else {
      // check if the expression has something to simplifiable
      if (this.somethingSimplifiable(context)) {
        // simplify the left and right sides
        expr = new Conjunction(this.getLeft().simplify(context),
        this.getRight().simplify(context)).simplify(context);
      // if there is nothing to evaluate
      } else {
        // just return the expression back
        expr = this;
      }
    }
    // return the simplified expression
    return expr;
  }

  /**
 * A string representation of the conjunction.
 */
  public String toString() {
    // left and right surround by brackets
    return "(" + this.getLeft().toString() + " " + Constants.AND + " " 
        + this.getRight().toString() + ")";
  }

  /**
 * Check if left and right side are the same.
 * 
 * @return true if left and right sides are identical.
 */
  public boolean leftAndRightAreEqual() {
    // check if strings are the same
    return (this.getLeft()).equals(this.getRight());
  }

}
