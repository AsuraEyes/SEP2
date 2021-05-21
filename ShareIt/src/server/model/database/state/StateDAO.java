package server.model.database.state;

import shared.transferobjects.State;

import java.sql.SQLException;
import java.util.List;

public interface StateDAO
{
  /**
   * Reads all cities from database
   * @return returns all state names in a arraylist
   * @throws SQLException
   */
  List<State> readState() throws SQLException;
}
