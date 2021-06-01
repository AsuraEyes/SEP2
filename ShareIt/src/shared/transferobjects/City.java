package shared.transferobjects;

import java.io.Serializable;

/**
 * A class that creates and handles Cities.
 */
public class City implements Serializable
{
    private String name;

  /**
   * Constructor initializing field.
   *
   * @param name name of the City
   */
  public City(String name){
      this.name = name;
    }

  /**
   * Gets name of the city.
   *
   * @return returns String type of name of the City
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
   * Gets city name as a string.
   * @return returns name of the City
   */
  public String toString(){
        return name;
    }

}
