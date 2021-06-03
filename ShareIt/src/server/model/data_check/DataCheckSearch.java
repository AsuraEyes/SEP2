package server.model.data_check;

import server.model.database.rental.RentalDAOImpl;
import shared.transferobjects.Rental;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that checks data from ServerModelManager before running an instance(Search data in this case)
 */
public class DataCheckSearch
{
  private String search;

  /**
   * Checks all the rentals with given value
   * @param search keyword
   * @return returns list of rentals where value matches with their name or description
   */
  public List<Rental> checkSearch(String search)
  {
    this.search = search;
    if (searchGiven())
    {
      try
      {
        return RentalDAOImpl.getInstance().readBySearch(search);
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      if (!searchGiven())
      {
        return RentalDAOImpl.getInstance().readRentals();
      }
    }
    return null;
  }
  /**
   * Check of the Search with Filter data before passing it into the DAO.
   *
   * @param search             Search input
   * @param city               Selected city
   * @param selectedCategories Selected categories
   * @return returns null if none of the conditions were met
   */
  public List<Rental> checkSearchWithFilter(String search, String city,
      ArrayList<String> selectedCategories)
  {
    this.search = search;
    try
    {
      return RentalDAOImpl.getInstance()
          .readBySearchAndFilter(search, city, selectedCategories);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  /**
   * Checks if search input was given.
   * @return returns true if search input is not empty, false if it is empty
   */
  private boolean searchGiven()
  {
    if (search != null)
    {
        return !(search.trim().equals("") && search.isBlank() && search.isEmpty());
    }
    return false;
  }

}
