package shared.transferobjects;

import java.io.Serializable;

public class Category implements Serializable
{
  private String name;

  public Category(String name){
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
}
