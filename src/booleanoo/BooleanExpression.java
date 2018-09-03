package booleanoo;

import java.util.Map;

public interface BooleanExpression {
  /**
 * Evaluates the given expression.
 * 
 * @param context
 *            The given context to refer to.
 * @return The Boolean value which the expression evaluates to.
 * @throws UnassignedVariableException
 *             Error occurs if there is an unassigned variable in the
 *             expression
 */
  public Boolean evaluate(Map<String, Boolean> context) throws UnassignedVariableException;

  /**
 * Simplifies the expression.
 * 
 * @param context
 *            The given context to refer to.
 * @return simplified expression.
 */
  public BooleanExpression simplify(Map<String, Boolean> context);

  /**
 * Compares this BooleanExpression to another and checks if they are equal.
 * @param other object to compare with
 * @return true if the object is equal to this
 */
  public boolean equals(Object other);

  /**
 * @return String form of the given expression.
 */
  public String toString();

  /**
 * Tells us if there is something to simplify in the expression.
 * 
 * @param context
 *            The given context to refer to.
 * @return true if there is something to simplify.
 */
  public Boolean somethingSimplifiable(Map<String, Boolean> context);
  
}
