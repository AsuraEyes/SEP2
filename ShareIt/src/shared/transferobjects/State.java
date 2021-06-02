package shared.transferobjects;

import java.io.Serializable;

/**
 * A class that handles States information.
 */
public class State implements Serializable
{
  private String name;
  /**
   * Constructor initializing field.
   *
   * @param name The name of a state that rental can be in.
   */
  public State(String name)
  {
    this.name = name;
  }

  /**
   * Gets state name.
   *
   * @return returns String type of state name.
   */
  public String getName()
  {
    return name;
  }

  /**
   * Sets state name.
   *
   * @param name String type of state name.
   */
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * Gets name of a state as a String
   * @return returns name of the State
   */
  @Override public String toString()
  {
    return name;
  }
}