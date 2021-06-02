package client.model.state;

import java.io.Serializable;

/**
 * A class representing Administrator state.
 */
public class AdministratorState implements LoginState, Serializable
{
  private final String USERTYPE = "Administrator";
  private final String username;

  /**
   * Instantiates a new Administrator state.
   *
   * @param username the username
   */
  public AdministratorState(String username)
  {
    this.username = username;
  }

  @Override public String getUsertype()
  {
    return USERTYPE;
  }

  @Override public String getUsername()
  {
    return username;
  }
}
