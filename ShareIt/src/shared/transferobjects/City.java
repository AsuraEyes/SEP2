package shared.transferobjects;

import java.io.Serializable;

public class City implements Serializable
{
  private String name;

  public City(String name)
  {
    this.name = name;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String toString()
  {
    return name;
  }

}
