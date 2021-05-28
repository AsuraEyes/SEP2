package server.model.database.city;

import shared.transferobjects.City;
import java.sql.SQLException;
import java.util.List;

public interface CityDAO
{
  /**
   * Reads all cities from database
    * @return returns all city names in a arraylist
   */
  List<City> readCity();
}
