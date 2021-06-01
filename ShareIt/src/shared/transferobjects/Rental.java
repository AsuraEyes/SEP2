package shared.transferobjects;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * A class that creates and handles Rentals.
 */
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
    /**
     * Constructor initializing fields.
     *
     * @param id                 Id of the rental that is assigned to it automatically when creating a new rental offer.
     * @param name               Name that can easily connect with posted rental.
     * @param pictureLink        Path of storing a picture.
     * @param description        Description that describes shortly posted rental.
     * @param price              Price in Danish Kroner.
     * @param otherInformation   Other information put by Member when creating a new rental offer.
     * @param stateName          The name of a state that rental is in.
     * @param memberId           The Member's ID that rent belongs to.
     * @param selectedCategories Categories that were selected when creating a new rental offer.
     */
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
    /**
     * Gets selected categories.
     *
     * @return returns an ArrayList of type String of categories that were selected.
     */
  public ArrayList<String> getSelectedCategories()
  {
    return selectedCategories;
  }
    /**
     * Sets selected categories.
     *
     * @param selectedCategories an ArrayList of type String of categories that were selected.
     */
  public void setSelectedCategories(ArrayList<String> selectedCategories)
  {
    this.selectedCategories = selectedCategories;
  }
    /**
     * Gets ID of the Rental.
     *
     * @return returns int type of Rental's ID.
     */
  public int getId()
  {
    return id;
  }
    /**
     * Sets ID of the Rental.
     *
     * @param id int type of Rental's ID.
     */
  public void setId(int id)
  {
    this.id = id;
  }
    /**
     * Gets name of the Rental.
     *
     * @return returns String type of Rental's name.
     */
  public String getName()
  {
    return name;
  }
    /**
     * Sets name of the Rental.
     *
     * @param name String type of Rental's name.
     */
  public void setName(String name)
  {
    this.name = name;
  }
    /**
     * Gets picture storage path.
     *
     * @return returns String type of picture storage path.
     */
  public String getPictureLink()
  {
    return pictureLink;
  }
    /**
     * Sets picture storage path.
     *
     * @param pictureLink String type of picture storage path.
     */
  public void setPictureLink(String pictureLink)
  {
    this.pictureLink = pictureLink;
  }
    /**
     * Gets description of the Rental.
     *
     * @return returns String type of Rental's description.
     */
  public String getDescription()
  {
    return description;
  }
    /**
     * Sets description of the Rental.
     *
     * @param description String type of Rental's description.
     */
  public void setDescription(String description)
  {
    this.description = description;
  }
    /**
     * Gets price of the Rental.
     *
     * @return return int type of Rental's price.
     */
  public int getPrice()
  {
    return price;
  }
    /**
     * Sets price of the Rental.
     *
     * @param price int type of Rental's price.
     */
  public void setPrice(int price)
  {
    this.price = price;
  }
    /**
     * Gets other information of the Rental.
     *
     * @return returns String type of Rental's other information.
     */
  public String getOtherInformation()
  {
    return otherInformation;
  }
    /**
     * Sets other information of the Rental.
     *
     * @param otherInformation String type of Rental's other information.
     */
  public void setOtherInformation(String otherInformation)
  {
    this.otherInformation = otherInformation;
  }
    /**
     * Gets the name of a state that the Rental is in.
     *
     * @return returns String type of state name.
     */
  public String getStateName()
  {
    return stateName;
  }
    /**
     * Sets the name of a state that the Rental is in.
     *
     * @param stateName String type of state name.
     */
  public void setStateName(String stateName)
  {
    this.stateName = stateName;
  }
    /**
     * Gets ID of a member that posted a rental.
     *
     * @return returns int type of members ID.
     */
  public int getMemberId()
  {
    return memberId;
  }
    /**
     * Gets rental with its name and description as a String.
     * @return returns Rental's name and description.
     */
  @Override public String toString()
  {
    return name + "\n\n" + description;
  }
}
