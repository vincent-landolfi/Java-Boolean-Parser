package booleanoo;

public interface BinaryOperator extends BooleanOperator {
  /**
 * Applies the operand to the left and right booleans.
 * 
 * @param left
 *            Boolean the left sides evaluates to.
 * @param right
 *            Boolean the right side evaluates to.
 * @return value of operand applied to left and right booleans.
 */
  public Boolean apply(Boolean left, Boolean right);
}
