package booleanoo;

public class UnassignedVariableException extends Exception {

  private static final long serialVersionUID = 1L;

  /**
 * Exception that runs when an unassigned variable is found.
 * 
 * @param message
 *            A message to be displayed when the error is thrown
 */
  public UnassignedVariableException(String message) {
    // use the super class to show the error message
    super(message);
  }
}
