package booleanoo;

public class Or implements BinaryOperator {

  @Override
  /**
 * Apply or to the left and right booleans
 */
  public Boolean apply(Boolean left, Boolean right) {
    // apply or to the left and right booleans
    return left || right;
  }

  /**
 * Return a string representation of or.
 */
  public String toString() {
    // return constant or
    return Constants.OR;
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
