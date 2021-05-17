package server.model.database.city;

import shared.transferobjects.City;
import java.sql.SQLException;
import java.util.List;

public interface CityDAO
{
  List<City> readCity() throws SQLException;

}
