package server.model.database.city;

import shared.transferobjects.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CityDAOImpl implements CityDAO
{
  private static CityDAOImpl instance;
  private String password;
  /**
   * Class that implements methods from its interface and provides access to a database(City in this case)
   *
   */
  private CityDAOImpl()throws SQLException{
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized CityDAOImpl getInstance() throws SQLException{
    if(instance == null){
      instance = new CityDAOImpl();
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

  /**
   * Reads all cities from database by connecting to the database and get all table contents
   * @return returns all city names in a arraylist
   * @throws SQLException
   */
  @Override public List<City> readCity() throws SQLException
  {
    try(Connection connection = getConnection()){
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM share_it.city");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<City> arrayListToReturn = new ArrayList<>();
      while(resultSet.next()){
        arrayListToReturn.add(new City(resultSet.getString("name")));
      }
      //return array list
      return arrayListToReturn;
    }
  }}
