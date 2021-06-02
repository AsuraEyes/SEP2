package client.model.state;

/**
 * The interface of Login state. It is set everytime the user is changed in
 * the application
 */
public interface LoginState
{
  /**
   * Gets usertype.
   *
   * @return the usertype
   */
  String getUsertype();
  /**
   * Gets username.
   *
   * @return the username
   */
  String getUsername();
}
