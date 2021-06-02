package server.model.database.rating;

import server.model.database.member.MemberDAOImpl;
import shared.transferobjects.Rating;

import java.sql.*;
import java.util.ArrayList;

/**
 * Class that implements methods from its interface and provides access to a database(Rating in this case)
 */
public class RatingDAOImpl
{
  private static RatingDAOImpl instance;
  private String password;

  private RatingDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized RatingDAOImpl getInstance()
  {
    if (instance == null)
    {
      try
      {
        instance = new RatingDAOImpl();
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
   * Creates new rating feedback of Member by connecting to the database then by using instance to get id by using username from member database then insert all given data into rating table
   *
   * @param starValue value of rating (from 1.0 to 5.0)
   * @param feedback  optionally a written feedback that rating user can leave
   * @param username1 user that is rating
   * @param username2 user that is rated
   * @return returns new object of Rating
   * @throws SQLException
   */
  public Rating create(double starValue, String feedback,
      String username1, String username2)
  {
    try (Connection connection = getConnection())
    {

      int memberId1 = MemberDAOImpl.getInstance().readIdByUsername(username1);
      int memberId2 = MemberDAOImpl.getInstance().readIdByUsername(username2);

      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO share_it.rating(value, commentary,member_from, member_to) VALUES (?, ?, ?, ?);");
      statement.setDouble(1, starValue);
      statement.setString(2, feedback);
      statement.setInt(3, memberId1);
      statement.setInt(4, memberId2);

      statement.executeUpdate();

      return new Rating(starValue, feedback, memberId1, memberId2);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  /**
   * Get all ratings that member has from the database by connecting to the database then by using instance to get id by using username from member database and then match member's id with data member_to id from the database
   *
   * @param username username of the user that method will get all ratings for
   * @return returns an array of all user's ratings
   */
  public ArrayList<Rating> getAllRatingsOnMember(String username)
  {
    try (Connection connection = getConnection())
    {
      int id = MemberDAOImpl.getInstance().readIdByUsername(username);
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM share_it.rating WHERE member_to = ?");
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();

      ArrayList<Rating> arrayListToReturn = new ArrayList<>();
      while (resultSet.next())
      {
        arrayListToReturn.add(new Rating(resultSet.getDouble("value"),
            resultSet.getString("commentary"), resultSet.getInt("member_from"),
            resultSet.getInt("member_to")));
      }
      //return array list
      return arrayListToReturn;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Gets user's rating based on from which to which user was it by connecting to the database then by using instance to get id by using username from member database then match all given data with existing database data
   *
   * @param fromUsername User that feedback rating was from
   * @param toUsername   User that got rated
   * @return returns rating object that has usernames matching
   */
  public Rating getRating(String fromUsername, String toUsername)
  {
    try (Connection connection = getConnection())
    {
      int fromId = MemberDAOImpl.getInstance().readIdByUsername(fromUsername);
      int toId = MemberDAOImpl.getInstance().readIdByUsername(toUsername);
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM share_it.rating WHERE member_from = ? AND member_to = ?  ");
      statement.setInt(1, fromId);
      statement.setInt(2, toId);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next())
      {
        return new Rating(resultSet.getDouble("value"),
            resultSet.getString("commentary"), fromId, toId);
      }
      return null;
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  /**
   * Updates rating feedback whenever user decides to change it by connecting to the database and update rating table based on given data
   *
   * @param rating new value of rating
   */
  public void updateRating(Rating rating)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE share_it.rating SET value = ?, commentary = ? WHERE member_from = ? AND member_to = ?");
      statement.setDouble(1, rating.getRating());
      statement.setString(2, rating.getCommentary());
      statement.setInt(3, rating.getMemberFrom());
      statement.setInt(4, rating.getMemberTo());
      statement.executeUpdate();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

}
