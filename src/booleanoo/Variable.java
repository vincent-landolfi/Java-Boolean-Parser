package booleanoo;

import java.util.Map;

public class Variable implements BooleanExpression {
  // String to represent the variable
  private String id;

  /**
 * Constructor for Variable object.
 * 
 * @param id
 *            A string to represent the variable.
 */
  public Variable(String id) {
    // set the given id to the object's id
    this.id = id;
  }

  @Override
  /**
 * Evaluate the given Variable to a boolean.
 * 
 * @param context
 *            The given context to refer to.
 */
  public Boolean evaluate(Map<String, Boolean> context) throws UnassignedVariableException {
    // if the variable is in the context
    if (context.containsKey(id)) {
      // get the boolean associated with the id string
      return context.get(id);
    // if the variable isnt in the context
    } else {
      // throw the unassigned variable error
      throw new UnassignedVariableException("Variable not found");
    }
  }

  @Override
  /**
 * Simplify the given variable.
 * 
 * @param context
 *            The given context to refer to.
 */
  public BooleanExpression simplify(Map<String, Boolean> context) {
    // preset the return expression to null
    BooleanExpression expr = null;
    // if the variable is in the context
    if (context.containsKey(id)) {
      // set the return expression to the boolean value associated with it
      // in the context
      expr = new BooleanValue(context.get(id));
    // if it is not in the context
    } else {
      // just set the variable to itself
      expr = this;
    }
    // return the simplified variable
    return expr;
  }

  /**
 * Return the id of the variable.
 */
  public String toString() {
    return id;
  }

  @Override
  /**
 * Lets us know if there is something simplifiable in the variable.
 * 
 * @param expr
 *            The given expression to check for simplifications.
 * @param context
 *            The given context to refer to.
 */
  public Boolean somethingSimplifiable(Map<String, Boolean> context) {
    // return true if the variable is not in the context
    return context.get(id) != null;
  }
  
  /**
   * Returns true if two Variables are equal.
   */
  public boolean equals(Object other) {
    // check if their strings are equal
    return other != null 
        && other.getClass().equals(Variable.class) && ((Variable)other).id.equals(this.id);
  }

}
