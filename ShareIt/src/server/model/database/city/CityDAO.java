package server.model.database.city;

import shared.transferobjects.City;
import java.sql.SQLException;
import java.util.List;
/**
 * The interface of City Data Access Object.
 */
public interface CityDAO
{
  /**
   * Reads all cities from database
    * @return returns all city names in a arraylist
   * @throws SQLException
   */
  List<City> readCity() throws SQLException;
}
