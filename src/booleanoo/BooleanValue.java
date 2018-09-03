package booleanoo;

import java.util.Map;

public class BooleanValue implements BooleanExpression {
  // the boolean value
  private Boolean value;

  /**
 * Constructor to make the new BooleanValue object.
 * 
 * @param value Boolean value associate with the object.
 */
  public BooleanValue(Boolean value) {
    // set the value to the given var
    this.value = value;
  }

  @Override
 /**
 * Evaluate the BooleanValue to a Boolean.
 */
 public Boolean evaluate(Map<String, Boolean> context) {
    // the value is just itself
    return this.value;
  }

  @Override
 /**
 * Simplifies the given BooleanValue.
 */
 public BooleanExpression simplify(Map<String, Boolean> context) {
    return this; // return itself
  }

  /**
 * Return a string form of the boolean value.
 */
  public String toString() {
    // just return the string of the boolean
    return value.toString();
  }

  @Override
 /**
 * Booleans are always simplifiable, so we return true.
 */
 public Boolean somethingSimplifiable(Map<String, Boolean> context) {
    // always true
    return true;
  }

  @Override
  /**
  * Returns true if this is equal to the given object.
  */
  public boolean equals(Object other) {
    return other != null
      && other.getClass().equals(BooleanValue.class) 
      && (((BooleanValue)other).value == this.value);
  }

}
