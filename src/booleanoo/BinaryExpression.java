package booleanoo;

import java.util.Map;

/** Boolean Expression object.
 * @author Vince
 *
 */
public abstract class BinaryExpression implements BooleanExpression {
  // operator, left and right expressions
  private BinaryOperator operator;
  private BooleanExpression left;
  private BooleanExpression right;

  /**
 * Sets the variables for the BinaryExpression object.
 * 
 * @param operator
 *            The binary operator (and, or, iff, implies, xor)
 * @param left
 *            The boolean expression on the left side of the operator.
 * @param right
 *            The boolean expression on the right side of the operator.
 */
  public BinaryExpression(BinaryOperator operator, BooleanExpression left, 
      BooleanExpression right) {
    // set all of our variables in the constructor
    this.operator = operator;
    this.left = left;
    this.right = right;
  }

  /**
 * Evaluates the given BooleanExpression by applying the operator the the
 * evaluations of the left and right sides of the equations.
 * 
 * @param context
 *            The given context to refer to.
 * @return The evaluation of the given expression.
 */
  public Boolean evaluate(Map<String, Boolean> context) throws UnassignedVariableException {
    // apply the operator to the evaluations of the
    // right and left sides of the expression
    return ((BinaryOperator) this.getOperator()).apply(this.getLeft().evaluate(context),
      this.getRight().evaluate(context));
  }

  /**
 * Tells user if BinaryExpression is identical to another.
 */
  public boolean equals(Object other) {
    return other != null
        && other.getClass().getSuperclass().equals(BinaryExpression.class)
        && this.getOperator().equals(((BinaryExpression)other).getOperator())
        && this.getLeft().equals(((BinaryExpression)other).getLeft())
        && this.getRight().equals(((BinaryExpression)other).getRight());
  }

  /**
 * Returns a string representation of the BinaryExpression object.
 * 
 * @return A value that will be overridden.
 */
  public String toString() {
    // arbitrary value
    return "Expression";
  }

  /**
 * Returns the left side of the BooleanExpression.
 * 
 * @return the left side of the BooleanExpression.
 */
  protected final BooleanExpression getLeft() {
    // return left side
    return this.left;
  }

  /**
 * Returns the right side of the BooleanExpression.
 * 
 * @return the right side of the BooleanExpression.
 */
  protected final BooleanExpression getRight() {
    // return right side
    return this.right;
  }

  /**
 * Returns the operator of the BooleanExpression.
 * 
 * @return Operator of the BooleanExpression.
 */
  protected final BooleanOperator getOperator() {
    // return operator
    return this.operator;
  }

  /**
 * Checks if the left side of the BooleanExpression evaluates to true.
 * 
 * @param context
 *            The given context to refer to.
 * @return retVal. True if the left side of the expression evaluates to
 *         true.
 */
  public Boolean leftIsTrue(Map<String, Boolean> context) {
    // preset the return value
    Boolean retVal = null;
    // since evaluate can raise error, need to surround with try catch
    try {
      // evaluate the left side of the expression set it to the retVal
      retVal = this.getLeft().evaluate(context);
      // if we get the error
    } catch (UnassignedVariableException error) {
      // we're just going to leave it as null
    }
    return retVal;
  }

  /**
 * Tells us if the right side of the expression evaluates to true.
 * 
 * @param context
 *            The given context to refer to.
 * @return true if the right side of the expression evaluates to true.
 */
  public Boolean rightIsTrue(Map<String, Boolean> context) {
    // preset the return value
    Boolean retVal = null;
    // since evaluate can raise error, need to surround with try catch
    try {
      // evaluate the right side of the expression set it to the retVal
      retVal = this.getRight().evaluate(context);
      // if we get the error
    } catch (UnassignedVariableException error) {
      // we're just going to leave it as null
    }
    return retVal;
  }

  /**
 * Checks if there is something to simplify in the given expression.
 * 
 * @param context
 *            Given context to refer to.
 * @return true if there is something to simplify.
 */
  public Boolean somethingSimplifiable(Map<String, Boolean> context) {
    // recurse on left and right side, which will eventually evaluate to
    // true or
    // false once a variable or boolean value is reached.
    return this.getLeft().somethingSimplifiable(context)
      || this.getRight().somethingSimplifiable(context)
      || this.getLeft().equals(this.getRight());
  }

}
