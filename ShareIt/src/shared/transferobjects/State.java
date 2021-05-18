package shared.transferobjects;

import java.io.Serializable;

public class State implements Serializable
{
  private String name;

  public State(String name){
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