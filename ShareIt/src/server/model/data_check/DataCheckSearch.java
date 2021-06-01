package server.model.data_check;

import server.model.database.rental.RentalDAOImpl;
import shared.transferobjects.Rental;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that checks data before running an instance(Search data in this case)
 */
public class DataCheckSearch
{
  private String search;

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

  private boolean searchGiven()
  {
    if (search != null)
    {
      if (!(search.trim().equals("") && search.isBlank() && search.isEmpty()))
      {
        return true;
      }
    }
    return false;
  }

}
