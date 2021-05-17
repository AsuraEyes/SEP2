package client.model.database.category;

import shared.transferobjects.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO
{
  private static CategoryDAOImpl instance;
  private String password;

  private CategoryDAOImpl()throws SQLException{
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized CategoryDAOImpl getInstance() throws SQLException{
    if(instance == null){
      instance = new CategoryDAOImpl();
    }
    return instance;
  }

  public void setPassword(String password){
    this.password = password;
  }

  private Connection getConnection() throws SQLException {
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", password);
  }

  @Override public List<Category> readCategory()
      throws SQLException
  {
    try(Connection connection = getConnection()){
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM share_it.category");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Category> arrayListToReturn = new ArrayList<>();
      while(resultSet.next()){
        arrayListToReturn.add(new Category(resultSet.getString("name")));
      }
      //return array list
      return arrayListToReturn;
    }
  }

}
