package booleanoo;

public interface BooleanOperator {
  /**
 * @return a string form of the boolean operator.
 */
  public String toString();
  
  /**
   * Returns true if this is equal to the given object.
   * @return true if this is equal to the given object.
   */
  public boolean equals(Object other);
}
