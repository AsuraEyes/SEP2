package server.model.database.report;

import server.model.database.member.MemberDAOImpl;
import shared.transferobjects.Member;
import shared.transferobjects.Report;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Class that implements methods from its interface and provides access to a database(Report in this case)
 *
 */
public class ReportDAOImpl implements ReportDAO
{
  private static ReportDAOImpl instance;
  private String password;

  private ReportDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized ReportDAOImpl getInstance()
  {
    if (instance == null)
    {
      try
      {
        instance = new ReportDAOImpl();
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
   * Creates new report feedback of Member by connecting to a database then inserting data provided by user to the database
   * @param feedback  optionally a written feedback that rating user can leave
   * @param username1 username of user that is sending report
   * @param username2 username of user that is reported
   * @return returns new object of Report with data which was provided by user while filling a report
   * @throws SQLException
   */
  @Override public Report create(String feedback, String username1,
      String username2) throws SQLException
  {
    try (Connection connection = getConnection())
    {

      int memberId1 = MemberDAOImpl.getInstance().readIdByUsername(username1);
      int memberId2 = MemberDAOImpl.getInstance().readIdByUsername(username2);

      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO share_it.report(commentary,member_from, member_to) VALUES (?, ?, ?);");
      statement.setString(1, feedback);
      statement.setInt(2, memberId1);
      statement.setInt(3, memberId2);

      statement.executeUpdate();

      return new Report(feedback, memberId1, memberId2);
    }
  }

  /**
   * Gets user's Report based on from which to which user was it by connecting to a database then inserting data provided by user to the database
   * @param fromUsername User that was reporting
   * @param toUsername   User that got reported
   * @return returns Report object that has usernames matching
   * @throws SQLException
   */
  @Override public Report getReport(String fromUsername, String toUsername)
  {
    try (Connection connection = getConnection())
    {
      int fromId = MemberDAOImpl.getInstance().readIdByUsername(fromUsername);
      int toId = MemberDAOImpl.getInstance().readIdByUsername(toUsername);
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM share_it.report WHERE member_from = ? AND member_to = ?  ");
      statement.setInt(1, fromId);
      statement.setInt(2, toId);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next())
      {
        return new Report(resultSet.getString("commentary"), fromId, toId);
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
   * Updates report feedback whenever user decides to change it by connecting to the database and update rating table based on given data
   * @param report new value of report
   */
  @Override public void updateReport(Report report)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE share_it.report SET commentary = ? WHERE member_from = ? AND member_to = ?");
      statement.setString(1, report.getCommentary());
      statement.setInt(2, report.getMemberFrom());
      statement.setInt(3, report.getMemberTo());
      statement.executeQuery();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
  /**
   * Reads all rentals from database by connecting to the database and get all table contents
   * @return returns a list of all reports that are stored in the database
   */
  public List<Report> readReports()
  {

    try (Connection connection = getConnection())
    {
      List<Member> members = MemberDAOImpl.getInstance()
          .readMembersIdsAndUsernames();
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM share_it.report");
      ResultSet resultSet = statement.executeQuery();
      List<Report> listOfReports = new ArrayList<>();
      while (resultSet.next())
      {
        listOfReports.add(new Report(resultSet.getString("commentary"),
            resultSet.getInt("member_from"), resultSet.getInt("member_to")));
      }
      for (int i = 0; i < members.size(); i++)
      {
        for (int j = 0; j < listOfReports.size(); j++)
        {
          if (listOfReports.get(j).getMemberFrom() == members.get(i).getId())
          {
            listOfReports.get(j).setUsernameFrom(members.get(i).getUsername());
          }
          else if (listOfReports.get(j).getMemberTo() == members.get(i).getId())
          {
            listOfReports.get(j).setUsernameTo(members.get(i).getUsername());
          }
        }

      }
      return listOfReports;
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }
}
