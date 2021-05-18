package server.model.data_check;

import server.model.database.rental.RentalDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataCheckSearch
{
  private String search;

  public String checkSearch(String search)
  {
    this.search = search;
    if(searchGiven()){
      try
      {
        RentalDAOImpl.getInstance().readBySearch(search);
        return "";
      }
      catch (SQLException e){
        //
      }
    }
    else {
      if(!searchGiven()){
        return "";
      }
    }
    return "Ooops, something went wrong!!";
  }

  public String checkSearchWithLocationFilter(String search, String city)
  {
    this.search = search;
    if(searchGiven()){
      try
      {
        RentalDAOImpl.getInstance().readBySearchAndLocation(search, city);
        return "";
      }
      catch (SQLException e){
        //
      }
    }
    else {
      if(!searchGiven()){
        return "no";
      }
    }
    return "Check database password";
  }
  public String checkSearchWithFilter(String search,String city,ArrayList<String> selectedCategories )
  {
    this.search = search;
    if(searchGiven())
    {
      try
      {
        RentalDAOImpl.getInstance().readBySearchAndFilter(search,city,selectedCategories );
        return "";
      }
      catch (SQLException e)
      {
        //
      }
    }
      else {
        if(!searchGiven()){
          return "no";
        }
      }
      return "Check database password";
    }

  private boolean searchGiven(){
    if (search != null){
      if (!(search.trim().equals("") && search.isBlank() && search.isEmpty())){
        return true;
      }
    }
    return false;
  }


}
