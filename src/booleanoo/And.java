package booleanoo;

/** Binary Operator object, the operator of a binary expression.
 * @author Vince
 *
 */
public class And implements BinaryOperator {

  /**
 * Applies the given operand (And to the two given booleans)
 * 
 * @param left
 *            The evaluation of the left side of the equation.
 * @param right
 *            The evaluation of the right side of the equation.
 * @return The operand applied to the left and right booleans.
 */
  @Override
  public Boolean apply(Boolean left, Boolean right) {
    // apply and to the left and the right
    return left && right;
  }

  /**
 * Returns the operand in string form.
 * 
 * @return The operand in string form.
 */
  public String toString() {
    // return and constant
    return Constants.AND;
  }

  /**
   * Returns true if this is equal to the given object.
   */
  @Override
  public boolean equals(Object other) {
    // check if they're the same class since all instances are the same
    return this.getClass().equals(other.getClass());
  }

}
