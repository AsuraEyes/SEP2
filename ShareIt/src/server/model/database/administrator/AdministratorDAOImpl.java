package server.model.database.administrator;

import java.sql.*;
/**
 * Class that implements methods from its interface and provides access to a database(Administrator in this case)
 *
 */

public class AdministratorDAOImpl implements AdministratorDAO
{
  private static AdministratorDAOImpl instance;
  private String password;

  private AdministratorDAOImpl()throws SQLException{
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized AdministratorDAOImpl getInstance(){
    if(instance == null){
      try
      {
        instance = new AdministratorDAOImpl();
      }
      catch (SQLException throwables)
      {
        throwables.printStackTrace();
      }
    }
    return instance;
  }

  public void setPassword(String password){
    this.password = password;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=share_it", "postgres", password);
  }

  /**
   * Checks if the username is unique(not in database)(specifically for administrator) by connecting to the database and try to match given username with existing ones
   * @param username username that has to be checked for being unique
   * @return returns username if the data matches from given username and password
   * @throws SQLException
   */
  @Override
  public boolean uniqueUsername(String username) throws SQLException {
    try(Connection connection = getConnection()){
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM share_it.administrator WHERE username = ?");
      statement.setString(1, username);
      ResultSet resultSet = statement.executeQuery();
      int numberOfResults = 0;
      while (resultSet.next()){
        numberOfResults++;
      }
      if(numberOfResults == 0){
        return true;
      }
      return false;
    }
  }

  /**
   * Checks to whom given username and password belongs to(specifically for administrator) by connecting to the database and matching given data with existing data
   * @param username username that has to be checked
   * @param password password that has to be checked
   * @return returns username if the data matches from given username and password
   */
  @Override
  public String checkLogInCredentials(String username, String password) {
    try(Connection connection = getConnection()){
      PreparedStatement statement = connection.prepareStatement("SELECT username FROM share_it.administrator WHERE username = ? AND password = ?");
      statement.setString(1, username);
      statement.setString(2, password);

      ResultSet resultSet = statement.executeQuery();

      if(resultSet.next()){
        return resultSet.getString("username");
      }
      else{
        return null;
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
