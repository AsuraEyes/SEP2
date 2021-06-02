package client.model.state;

import java.io.Serializable;

/**
 * A class representing Member state.
 */
public class MemberState implements LoginState, Serializable
{
  private final String USERTYPE = "Member";
  private final String username;

  public MemberState(String username)
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
