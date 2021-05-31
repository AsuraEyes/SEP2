package shared.transferobjects;

import java.io.Serializable;
import java.util.ArrayList;

public class Rental implements Serializable
{
  private int id;
  private String name;
  private String pictureLink;
  private String description;
  private int price;
  private String otherInformation;
  private String stateName;
  private int memberId;
  private ArrayList<String> selectedCategories;

  public Rental(int id, String name, String pictureLink, String description,
      int price, String otherInformation, String stateName, int memberId,
      ArrayList<String> selectedCategories)
  {
    this.id = id;
    this.name = name;
    this.pictureLink = pictureLink;
    this.description = description;
    this.price = price;
    this.otherInformation = otherInformation;
    this.stateName = stateName;
    this.memberId = memberId;
    this.selectedCategories = selectedCategories;
  }

  public Rental(int id)
  {
    this.id = id;
  }

  public ArrayList<String> getSelectedCategories()
  {
    return selectedCategories;
  }

  public void setSelectedCategories(ArrayList<String> selectedCategories)
  {
    this.selectedCategories = selectedCategories;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getPictureLink()
  {
    return pictureLink;
  }

  public void setPictureLink(String pictureLink)
  {
    this.pictureLink = pictureLink;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public int getPrice()
  {
    return price;
  }

  public void setPrice(int price)
  {
    this.price = price;
  }

  public String getOtherInformation()
  {
    return otherInformation;
  }

  public void setOtherInformation(String otherInformation)
  {
    this.otherInformation = otherInformation;
  }

  public String getStateName()
  {
    return stateName;
  }

  public void setStateName(String stateName)
  {
    this.stateName = stateName;
  }

  public int getMemberId()
  {
    return memberId;
  }

  @Override public String toString()
  {
    return name + "\n\n" + description;
  }
}
