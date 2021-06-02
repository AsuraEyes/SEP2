package server.model.database.rental_category;

import java.sql.*;
import java.util.ArrayList;
/**
 * Class that implements methods from its interface and provides access to a database(Rental category in this case)
 *
 */
public class RentalCategoryDAOImpl
{
  private static RentalCategoryDAOImpl instance;
  private String password;

  private RentalCategoryDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized RentalCategoryDAOImpl getInstance()
  {
    if (instance == null)
    {
      try
      {
        instance = new RentalCategoryDAOImpl();
      }
      catch (SQLException throwables)
      {
        throwables.printStackTrace();
      }
    }
    return instance;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager
        .getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
            password);
  }
    /**
     * Gets selected categories on rental by connecting to the database and matching given rentalId with existing data
     * @param rentalId Rental's ID
     * @return returns a list of all selected categories that were chosen during creation of new Rental offer
     * @throws SQLException
     */
  public ArrayList<String> getSelectedCategoriesOnRental(int rentalId)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM share_it.rental_category WHERE rental_id = ?");
      statement.setInt(1, rentalId);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<String> listOfCategories = new ArrayList<>();
      while (resultSet.next())
      {
        listOfCategories.add(resultSet.getString("category_name"));
      }
      return listOfCategories;
    }
  }
}
