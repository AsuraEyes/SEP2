package server.model.database.city;

import shared.transferobjects.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CityDAOImpl implements CityDAO
{
  private static CityDAOImpl instance;
  private String password;

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

  @Override public List<City> readCity() throws SQLException
  {
    try(Connection connection = getConnection()){
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM share_it.city");
      System.out.println("at least get here");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<City> arrayListToReturn = new ArrayList<>();
      while(resultSet.next()){
        System.out.println("get here");
        arrayListToReturn.add(new City(resultSet.getString("name")));
      }
      //return array list
      System.out.println(arrayListToReturn);
      return arrayListToReturn;
    }
  }}
