package booleanoo;

public class Implies implements BinaryOperator {

  @Override
  /**
 * Apply implies to the left and right booleans
 */
  public Boolean apply(Boolean left, Boolean right) {
    // if left is false or both are true
    return (!left) || (left && right);
  }

  /**
 * String representation of Implies.
 */
  public String toString() {
    // return constant implies
    return Constants.IMPLIES;
  }
  

  @Override
  /**
   * Returns true if this is equal to the given object.
   */
  public boolean equals(Object other) {
    // check if they're the same class since all instances are the same
    return this.getClass().equals(other.getClass());
  }

}
