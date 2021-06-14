package shared.transferobjects;

import java.io.Serializable;

/**
 * A class that handles Categories information.
 */
public class Category implements Serializable
{
  private String name;

  /**
   * Constructor initializing field
   *
   * @param name name of the Category
   */
  public Category(String name)
  {
    this.name = name;
  }

  /**
   * Gets name of the category.
   *
   * @return Sting type of name of the Category
   */
  public String getName()
  {
    return name;
  }

  /**
   * Sets name of the category.
   *
   * @param name String type of name of the Category
   */
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * Gets category name as a String.
   * @return name of the Category
   */
  public String toString()
  {
    return name;
  }
}
