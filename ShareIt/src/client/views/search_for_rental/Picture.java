package client.views.search_for_rental;

import javafx.scene.image.Image;

public class Picture
{
  private String name;
  private String description;
  private Image image;

  public Picture(String name, String description, String url)
  {
    this.name = name;
    this.description = description;
    image = new Image(url,200,200,false, false);
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public Image getImage()
  {
    return image;
  }

  public void setImage(Image image)
  {
    this.image = image;
  }

  @Override public String toString()
  {
    return name + "\n\n" + description;
  }
}
