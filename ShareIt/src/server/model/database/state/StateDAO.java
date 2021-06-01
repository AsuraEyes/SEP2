package server.model.database.state;

import shared.transferobjects.State;

import java.sql.SQLException;
import java.util.List;
/**
 * The interface of State Data Access Object.
 */
public interface StateDAO
{
  /**
   * Reads all cities from database
   * @return returns all state names in a arraylist
   * @throws SQLException
   */
  List<State> readState() throws SQLException;
}
