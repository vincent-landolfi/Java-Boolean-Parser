package booleanoo;

import java.util.Map;

public class XorExpression extends BinaryExpression {
  /**
 * Constructor for the XorExpression object.
 * 
 * @param left
 *            A boolean that the left side evaluates to.
 * @param right
 *            A boolean that the right side evaluates to.
 */
  public XorExpression(BooleanExpression left, BooleanExpression right) {
    // use the super class' constructor
    super(new Xor(), left, right);
  }

  @Override
  /**
 * Simplify the given XorExpression.
 * 
 * @param context
 *            The given context to refer to.
 */
  public BooleanExpression simplify(Map<String, Boolean> context) {
    // preset the return expression to null
    BooleanExpression expr = null;
    // assume the left and right are evaluatable
    Boolean leftIsEvaluatable = true;
    Boolean rightIsEvaluatable = true;
    // if the left side is not evaluatable
    if (this.leftIsTrue(context) == null) {
      // set the var accordingly
      leftIsEvaluatable = false;
    }
    // if the right side is not evaluatable
    if (this.rightIsTrue(context) == null) {
      // set the var accordingly
      rightIsEvaluatable = false;
    }
    // if the left side and the right side are identical
    if (this.getLeft().toString().equals(this.getRight().toString())) {
      // set the return expression to false
      expr = new BooleanValue(false);
    // if the left side is true
    } else if (leftIsEvaluatable && this.leftIsTrue(context)) {
      // set the return expression to the negation of the right side
      expr = (new Negation(this.getRight().simplify(context))).simplify(context);
    // if the right side is true
    } else if (rightIsEvaluatable && this.rightIsTrue(context)) {
      // set the return expression to the negation of the left side
      expr = (new Negation(this.getLeft().simplify(context))).simplify(context);
    // is the right side is false
    } else if (rightIsEvaluatable && !this.rightIsTrue(context)) {
      // set the return expression to the left side
      expr = this.getLeft().simplify(context);
    // if the left side is false
    } else if (leftIsEvaluatable && !this.leftIsTrue(context)) {
      // set the return expression to the right side
      expr = this.getRight().simplify(context);
    // if none of the expressions are met
    } else {
      // check if there is anything simplifiable
      if (this.somethingSimplifiable(context)) {
        // simplify the left and right sides
        expr = new XorExpression(this.getLeft().simplify(context),
          this.getRight().simplify(context)).simplify(context);
      }
    }
    // check how to simplify the expression further and apply it
    return expr;
  }

  /**
 * Return a string representation of the xor expression.
 */
  public String toString() {
    // left xor right surrounded by brackets
    return "(" + this.getLeft().toString() + " " + Constants.XOR 
        + " " + this.getRight().toString() + ")";
  }
  
}
