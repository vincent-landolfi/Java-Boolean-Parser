package booleanoo;

import java.util.Map;

public class IffExpression extends BinaryExpression {

  /**
 * Constructor for IffExpression
 * 
 * @param left
 *            Left side of the iff expression.
 * @param right
 *            Right side of the iff expression.
 */
  public IffExpression(BooleanExpression left, BooleanExpression right) {
    // use the super class' constructor
    super(new Iff(), left, right);
  }

  @Override
  /**
 * Simplify the given iff expression.
 * 
 * @param context.
 *            The given context to refer to.
 */
  public BooleanExpression simplify(Map<String, Boolean> context) {
    // preset the return value to null
    BooleanExpression expr = null;
    // assume left and right sides are evaluatable
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
    // if left and right sides are identical
    if (this.getLeft().toString().equals(this.getRight().toString())) {
      // set return expression to true
      expr = new BooleanValue(true);
    // if left side is true
    } else if (leftIsEvaluatable && this.leftIsTrue(context)) {
      // set return expression to the right side
      expr = this.getRight().simplify(context);
    // if right side is true
    } else if (rightIsEvaluatable && this.rightIsTrue(context)) {
      // set return expression to left side
      expr = this.getLeft().simplify(context);
    // if right side value is false
    } else if (rightIsEvaluatable && !this.rightIsTrue(context)) {
      // set return expression to negation of left side and simplify
      expr = (new Negation(this.getLeft().simplify(context))).simplify(context);
    // if left side is false
    } else if (leftIsEvaluatable && !this.leftIsTrue(context)) {
      // set return expression to negation of right side and simplify
      expr = (new Negation(this.getRight().simplify(context))).simplify(context);
    // if none of the conditions are met
    } else {
      // if there is something simplifiable in the expression
      if (this.somethingSimplifiable(context)) {
        // simplify the left and right sides
        expr = new IffExpression(this.getLeft().simplify(context),
          this.getRight().simplify(context)).simplify(context);
      // if there's nothing to evaluate
      } else {
        // just return the expression back
        expr = this;
      }
    }
    // return the simplified expression
    return expr;
  }

  /**
 * Return a string representation of the iff expression.
 */
  public String toString() {
    // left iff right surrounded by brackets
    return "(" + this.getLeft().toString() + " " + Constants.IFF 
        + " " + this.getRight().toString() + ")";
  }
  
}
