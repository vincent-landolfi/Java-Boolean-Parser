package booleanoo;

import java.util.Map;

public abstract class UnaryExpression implements BooleanExpression {
  // operator and operand
  private UnaryOperator operator;
  private BooleanExpression operand;

  /**
 * Constructor for UnaryExpression object.
 * 
 * @param operator
 *            The given operator for the expression
 * @param operand
 *            The given operand to apply to the operator to.
 */
  public UnaryExpression(UnaryOperator operator, BooleanExpression operand) {
    // set the class variables
    this.operator = operator;
    this.operand = operand;
  }

  /**
 * Evaluate the given UnaryExpression
 * 
 * @param context
 *            The given context to refer to.
 */
  public Boolean evaluate(Map<String, Boolean> context) throws UnassignedVariableException {
    // apply to UnaryOperator to the evaluation of the operand
    return ((UnaryOperator) this.getOperator()).apply(this.getOperand().evaluate(context));
  }

  /**
 * Returns true if the given object is an equal expression to this.
 */
  public boolean equals(Object other) {
    return other != null
        && other.getClass().getSuperclass().equals(UnaryExpression.class)
        && this.getOperator().equals(((UnaryExpression)other).getOperator())
        && this.getOperand().equals(((UnaryExpression)other).getOperand());
  }

  /**
 * Return the string representation of UnaryExpression.
 */
  public String toString() {
    // return blank string to be overridden
    return "";
  }

  /**
 * Returns the operand of the UnaryExpression object.
 * 
 * @return the operand of the UnaryExpression object
 */
  protected final BooleanExpression getOperand() {
    // return the operand held at the object
    return this.operand;
  }

  /**
 * Returns the operator of the UnaryExpression object.
 * 
 * @return the operator of the UnaryExpression object
 */
  protected final BooleanOperator getOperator() {
    // return the operator held at the object
    return this.operator;
  }
  
  /**
 * Check if there is something simplifiable in the UnaryExpression.
 */
  public Boolean somethingSimplifiable(Map<String, Boolean> context) {
    // check if there is something simplifiable in the operand
    return this.operand.somethingSimplifiable(context)
        || this.operand.getClass().equals(Negation.class);
  }
}
