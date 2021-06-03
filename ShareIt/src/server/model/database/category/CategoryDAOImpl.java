package server.model.database.category;

import shared.transferobjects.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements methods from its interface and provides access to a database(Category in this case)
 *
 */
public class CategoryDAOImpl
{
  private static CategoryDAOImpl instance;
  private String password;

  private CategoryDAOImpl()throws SQLException{
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized CategoryDAOImpl getInstance(){
    if(instance == null){
      try
      {
        instance = new CategoryDAOImpl();
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

  private Connection getConnection() throws SQLException {
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", password);
  }

  /**
   * Reads all categories from database by connecting to the database and get all table contents
   * @return returns all category names in a arraylist
   */
  public List<Category> readCategory()
  {
      try (Connection connection = getConnection())
      {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM share_it.category");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Category> arrayListToReturn = new ArrayList<>();
        while (resultSet.next())
        {
          arrayListToReturn.add(new Category(resultSet.getString("name")));
        }
        return arrayListToReturn;
      }
      catch (SQLException throwables)
      {
        throwables.printStackTrace();
      }
    return null;
  }

}
