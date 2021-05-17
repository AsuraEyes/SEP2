package client.model.database.city;

import shared.transferobjects.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CityDAOImpl implements CityDAO
{
  private static client.model.database.city.CityDAOImpl instance;
  private String password;

  private CityDAOImpl()throws SQLException{
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized client.model.database.city.CityDAOImpl getInstance() throws SQLException{
    if(instance == null){
      instance = new client.model.database.city.CityDAOImpl();
    }
    return instance;
  }

  public void setPassword(String password){
    this.password = password;
  }

  private Connection getConnection() throws SQLException {
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", password);
  }

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
