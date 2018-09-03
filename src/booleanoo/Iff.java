package booleanoo;

public class Iff implements BinaryOperator {

  /**
 * Apply iff to the left and right booleans.
 */
  public Boolean apply(Boolean left, Boolean right) {
    return (!left && !right) || (left && right);
  }

  /**
 * Return string representation of this object.
 */
  public String toString() {
    // return iff constant
    return Constants.IFF;
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
