package server.model.database.rating;

import server.model.database.member.MemberDAOImpl;
import shared.transferobjects.Rating;
import shared.transferobjects.Rental;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RatingDAOImpl implements RatingDAO
{
  private static RatingDAOImpl instance;
  private String password;

  private RatingDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized RatingDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new RatingDAOImpl();
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

  @Override public Rating create(double starValue, String feedback, String username1, String username2) throws SQLException
  {
    try(Connection connection = getConnection()){
    username1 = "bob";
    username2 = "bmwdriver";

      int memberId1 = MemberDAOImpl.getInstance().readIdByUsername(username1);
      int memberId2 = MemberDAOImpl.getInstance().readIdByUsername(username2);


      System.out.println(starValue);
      PreparedStatement statement = connection.prepareStatement("INSERT INTO share_it.rating(value, commentary,member_from, member_to) VALUES (?, ?, ?, ?);");
      statement.setDouble(1, starValue);
      statement.setString(2, feedback);
      statement.setInt(3,memberId1);
      statement.setInt(4,memberId2);
      statement.executeUpdate();

      return new Rating(starValue,feedback,memberId1,memberId2);

    }

  }

  @Override
  public ArrayList<Rating> getAllRatingsOnMember(String username) throws SQLException {
    try (Connection connection = getConnection()) {

      int id = MemberDAOImpl.getInstance().readIdByUsername(username);
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM share_it.rating WHERE member_to = ?");
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();

      ArrayList<Rating> arrayListToReturn = new ArrayList<>();
      while (resultSet.next()) {
        arrayListToReturn.add(new Rating(resultSet.getDouble("value"), resultSet.getString("commentary"), resultSet.getInt("member_from"), resultSet.getInt("member_to")));
      }
      //return array list
      return arrayListToReturn;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
