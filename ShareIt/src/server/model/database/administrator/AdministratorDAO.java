package server.model.database.administrator;

import shared.transferobjects.City;

import java.sql.SQLException;
import java.util.List;

public interface AdministratorDAO
{
  /**
   * Checks if the username is unique(not in database)
   * @param username username that has to be checked for being unique
   * @return returns true if it is unique or false if it is already in database
   * @throws SQLException
   */
  boolean uniqueUsername(String username) throws SQLException;
  /**
   * Checks to whom given username and password belongs to(specifically for administrator)
   * @param username username that has to be checked
   * @param password password that has to be checked
   * @return returns username if the data matches from given username and password
   * @throws SQLException
   */
  String checkLogInCredentials(String username, String password);
}
