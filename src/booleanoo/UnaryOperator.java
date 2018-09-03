package booleanoo;

public interface UnaryOperator extends BooleanOperator {
  /**
 * Apply the UnaryOperator to the given operand.
 * 
 * @param operand
 *            A boolean that the operand expression evaluates to.
 * @return The value of the evaluation of the UnaryExpression.
 */
  public Boolean apply(Boolean operand);
}
