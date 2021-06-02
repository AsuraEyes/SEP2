package client.model.state;

import java.io.Serializable;

/**
 * A class representing Visitor state.
 */
public class VisitorState implements LoginState, Serializable
{
  private final String USERTYPE = "Visitor";

  @Override public String getUsertype()
  {
    return USERTYPE;
  }

  @Override public String getUsername()
  {
    return null;
  }
}
