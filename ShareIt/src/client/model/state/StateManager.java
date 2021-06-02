package client.model.state;

import java.io.Serializable;

/**
 * A class that manages states that users can be in while using application
 */
public class StateManager implements Serializable
{
  private static StateManager instance;
  private LoginState currentState;

  private StateManager()
  {
    currentState = new VisitorState();
  }

  /**
   * Creates stateManager when it needs to be accessed
   *
   * @return returns new StateManager
   */
  public static synchronized StateManager getInstance()
  {
    if (instance == null)
    {
      instance = new StateManager();
    }
    return instance;
  }

  /**
   * Gets usertype.
   *
   * @return the usertype
   */
  public String getUsertype()
  {
    return currentState.getUsertype();
  }

  /**
   * Gets username.
   *
   * @return the username
   */
  public String getUsername()
  {
    return currentState.getUsername();
  }

  /**
   * Sets login state.
   *
   * @param state the state
   */
  public void setLoginState(LoginState state)
  {
    currentState = state;
  }
}
