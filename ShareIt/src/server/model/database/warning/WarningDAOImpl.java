package server.model.database.warning;

import shared.transferobjects.Warning;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
/**
 * Class that implements methods from its interface and provides access to a database(Warning in this case)
 *
 */
public class WarningDAOImpl
{
  private static WarningDAOImpl instance;
  private String password;

  private WarningDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized WarningDAOImpl getInstance()
  {
    if (instance == null)
    {
      try
      {
        instance = new WarningDAOImpl();
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
     * Gets all the Warnings connected to the administrator that gave it and member that received it by connecting to the database and get all table contents that are matched with given data
     * @param administrator Administrator that filled the warning
     * @param memberTo Member that is geting warned
     * @return a list of all warnings that are matched with given data
     */
  public ArrayList<Warning> getWarnings(String administrator,
      int memberTo)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT time, text FROM share_it.warning WHERE administrator_from = ? AND member_to = ? ORDER BY time");
      statement.setString(1, administrator);
      statement.setInt(2, memberTo);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Warning> arrayListToReturn = new ArrayList<>();

      while (resultSet.next())
      {
        String text = resultSet.getString("text");
        Date date = resultSet.getTimestamp("time");

        arrayListToReturn.add(new Warning(administrator, memberTo, text, date));
      }
      return arrayListToReturn;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }
    /**
     * Creates new warning by connecting to a database then inserting data provided by administrator to the database
     * @param warning Warning object that will be sent
     * @return new object of Warning with data which was provided by Administrator while creating new warning
     */
  public Warning sendWarning(Warning warning)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO share_it.warning(text, time, administrator_from, member_to) VALUES (?, ?, ?, ?);",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, warning.getText());
      Timestamp ts = Timestamp.from(Instant.now());
      statement.setTimestamp(2, ts);
      statement.setString(3, warning.getAdministratorFrom());
      statement.setInt(4, warning.getMemberTo());
      statement.executeUpdate();

      ResultSet generatedKeys = statement.getGeneratedKeys();
      if (generatedKeys.next())
      {
        return new Warning(warning.getAdministratorFrom(),
            warning.getMemberTo(), warning.getText(), ts);
      }
      else
      {
        throw new SQLException("No keys generated");
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }
}
