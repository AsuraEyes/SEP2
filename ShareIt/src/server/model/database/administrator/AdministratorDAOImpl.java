package server.model.database.administrator;

import shared.transferobjects.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AdministratorDAOImpl implements AdministratorDAO
{
  private static AdministratorDAOImpl instance;
  private String password;

  private AdministratorDAOImpl()throws SQLException{
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized AdministratorDAOImpl getInstance() throws SQLException{
    if(instance == null){
      instance = new AdministratorDAOImpl();
    }
    return instance;
  }

  public void setPassword(String password){
    this.password = password;
  }

  private Connection getConnection() throws SQLException {
    System.out.println(password);
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=share_it", "postgres", password);
  }

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

  @Override
  public String checkLogInCredentials(String username, String password) {
    try(Connection connection = getConnection()){
      System.out.println("username: " + username + ", password: " + password);
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
