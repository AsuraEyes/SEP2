package server.model.data_check;

import server.model.database.rental.RentalDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class that checks data before running an instance(Rental data in this case)
 */
public class DataCheckRental
{
  private String name;
  private String description;
  private String price;
  private int priceNb;
    /**
     * Check of the Rental data before passing it into the DAO.
     *
     * @param name               Name that can easily connect with posted rental.
     * @param pictureLink        Path of storing a picture.
     * @param description        Description that describes shortly posted rental.
     * @param price              Price in Danish Kroner.
     * @param otherInformation   Other information put by Member when creating a new rental offer.
     * @param stateName          The name of a state that rental is in.
     * @param selectedCategories Categories that were selected when creating a new rental offer.
     * @return returns String type dependable on the result.
     */
  public String checkRentalData(String name, String pictureLink,
      String description, String price, String otherInformation,
      String stateName, String username, ArrayList<String> selectedCategories)
  {
    this.name = name;
    this.description = description;
    this.price = price;

    if (nameGiven() && descriptionGiven() && priceIsNumber())
    {
      try
      {
        RentalDAOImpl.getInstance()
            .create(name, pictureLink, description, priceNb, otherInformation,
                stateName, username, selectedCategories);
        return "Adding successful";
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      if (!nameGiven())
      {
        return "Name cannot be empty";
      }
      if (!descriptionGiven())
      {
        return "Description cannot be empty";
      }
      if (!priceIsNumber())
      {
        return "Price is a not number";
      }
    }
    return "Ooops, something went wrong!!";
  }
    /**
     * Check of the updated Rental data before passing it into the DAO.
     *
     * @param name               Updated ame that can easily connect with posted rental.
     * @param pictureLink        Updated path of storing a picture.
     * @param description        Updated description that describes shortly posted rental.
     * @param price              Updated price in Danish Kroner.
     * @param otherInformation   Updated other information.
     * @param stateName          Updated name of a state that rental is in.
     * @param rentalId           Id of the rental that is assigned to it automatically when creating a new rental offer.(It cannot be updated)
     * @param selectedCategories Updated categories that were selected.
     * @return returns String type dependable on the result.
     */
  public String updateCheckRentalData(String name, String pictureLink,
      String description, String price, String otherInformation,
      String stateName, int rentalId, ArrayList<String> selectedCategories)
  {
    this.name = name;
    this.description = description;
    this.price = price;

    if (nameGiven() && descriptionGiven() && priceIsNumber())
    {
      try
      {
        RentalDAOImpl.getInstance()
            .update(name, pictureLink, description, priceNb, otherInformation,
                stateName, rentalId, selectedCategories);
        return "Edit successful";
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      if (!nameGiven())
      {
        return "Name cannot be empty";
      }
      if (!descriptionGiven())
      {
        return "Description cannot be empty";
      }
      if (!priceIsNumber())
      {
        return "Price is a not number";
      }
    }
    return "Ooops, something went wrong!!";
  }
    /**
     * Checks is name was given.
     * @return returns true if name is not empty, false if it is empty
     */
  private boolean nameGiven()
  {
    if (name != null)
    {
      if (!(name.trim().equals("") && name.isBlank() && name.isEmpty()))
      {
        return true;
      }
    }
    return false;
  }
    /**
     * Checks is description was given.
     * @return returns true if description is not empty, false if it is empty
     */
  private boolean descriptionGiven()
  {
    if (description != null)
    {
      if (!(description.trim().equals("") && description.isBlank()
          && description.isEmpty()))
      {
        return true;
      }
    }
    return false;
  }
    /**
     * Checks if given price is a number
     * @return returns true if object is returning a integer, false if not
     */
  private boolean priceIsNumber()
  {
    try
    {
      this.priceNb = Integer.parseInt(price);
      return true;
    }
    catch (NumberFormatException e)
    {
      return false;
    }
  }
}
