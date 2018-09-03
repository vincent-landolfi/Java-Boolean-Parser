package booleanoo;

public class Xor implements BinaryOperator {

  @Override
  /**
 * Apply xor to the given left and right booleans.
 * 
 * @param left
 *            A boolean that the left side evaluates to.
 * @param right
 *            A boolean that the right side evaluates to.
 */
  public Boolean apply(Boolean left, Boolean right) {
    // left xor right
    return left ^ right;
  }

  /**
 * Return a string representation of xor.
 */
  public String toString() {
    // return xor constant
    return Constants.XOR;
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
