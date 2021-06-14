package shared.transferobjects;

import java.io.Serializable;

/**
 * A class that handles Cities information.
 */
public class City implements Serializable
{
  private String name;
  /**
   * Constructor initializing field.
   *
   * @param name name of the City
   */
  public City(String name)
  {
    this.name = name;
  }
  /**
   * Gets name of the city.
   *
   * @return String type of name of the City
   */
  public String getName()
  {
    return name;
  }
  /**
   * Sets name of the city.
   *
   * @param name String type of name of the City
   */
  public void setName(String name)
  {
    this.name = name;
  }
  /**
   * Gets city name as a String.
   * @return name of the City
   */
  public String toString()
  {
    return name;
  }

}
