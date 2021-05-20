package server.model.database.rating;

import server.model.database.member.MemberDAOImpl;
import shared.transferobjects.Rating;
import java.sql.*;



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

      int memberId1 = MemberDAOImpl.getInstance().readIdByUsername(username1);
      int memberId2 = MemberDAOImpl.getInstance().readIdByUsername(username2);

      System.out.println("member from: "+username1+" member to: "+username2);

      System.out.println(starValue);
      PreparedStatement statement = connection.prepareStatement("INSERT INTO share_it.rating(value, commentary,member_from, member_to) VALUES (?, ?, ?, ?);");
      statement.setDouble(1, starValue);
      statement.setString(2, feedback);
      statement.setInt(3,memberId1);
      statement.setInt(4,memberId2);

      System.out.println(statement);
      statement.executeUpdate();

      return new Rating(starValue,feedback,memberId1,memberId2);

    }

  }

}
