package booleanoo;

public class Not implements UnaryOperator {

  @Override
  /**
 * Apply not to the operand
 */
  public Boolean apply(Boolean operand) {
    // negate the operand
    return !operand;
  }

  /**
 * Return string representation of not.
 */
  public String toString() {
    // return not constant
    return Constants.NOT;
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
