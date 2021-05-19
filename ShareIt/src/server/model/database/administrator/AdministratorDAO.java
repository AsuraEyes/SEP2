package server.model.database.administrator;

import shared.transferobjects.City;

import java.sql.SQLException;
import java.util.List;

public interface AdministratorDAO
{
  boolean uniqueUsername(String username) throws SQLException;

  String checkLogInCredentials(String username, String password);
}
