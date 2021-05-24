package server.model.database.chat;

import server.model.database.member.MemberDAOImpl;
import shared.transferobjects.Member;
import shared.transferobjects.Message;
import shared.transferobjects.Rental;

import java.io.File;
import java.nio.file.Files;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;

public class ChatDAOImpl implements ChatDAO
{
  private static ChatDAOImpl instance;
  private String password;

  private ChatDAOImpl()throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized ChatDAOImpl getInstance() throws SQLException {
    if(instance == null){
      instance = new ChatDAOImpl();
    }
    return instance;
  }

  public void setPassword(String password){
    this.password = password;
  }

  private Connection getConnection() throws SQLException {
    System.out.println("password: " + password);
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", password);
  }
  @Override public ArrayList<Message> getAllReceivedMessages(int loggedUserId)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM share_it.message WHERE member_to = ?");
      statement.setInt(1, loggedUserId);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Message> arrayListToReturn = new ArrayList<>();

      while (resultSet.next())
      {
        String text = resultSet.getString("text");
        Date date = resultSet.getDate("time");
        int fromUserId = resultSet.getInt("member_from");

        arrayListToReturn.add(new Message(fromUserId, loggedUserId, text, date));
      }
      resultSet.close();
      statement.close();
      return arrayListToReturn;
      }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override public Message sendMessage(Message message)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO share_it.message(text, time, member_from, member_to) VALUES (?, ?, ?, ?);",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, message.getText());
      Timestamp ts = Timestamp.from(Instant.now());

      statement.setTimestamp(2, ts);
      statement.setInt(3, message.getMemberFrom());
      statement.setInt(4, message.getMemberTo());
      statement.executeUpdate();

      ResultSet generatedKeys = statement.getGeneratedKeys();
      if (generatedKeys.next())
      {
        return new Message(message.getMemberFrom(), message.getMemberTo(), message.getText(), ts);
      }
      else
      {
        throw new SQLException("No keys generated");
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

    @Override public ArrayList<Message> getMessagesFromUser(int loggedUserId, int fromUserid)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM share_it.message WHERE member_to = ? AND member_from = ?");
      statement.setInt(1, loggedUserId);
      statement.setInt(2, fromUserid);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Message> arrayListToReturn = new ArrayList<>();

      while (resultSet.next())
      {
        String text = resultSet.getString("text");
        Date date = resultSet.getDate("time");
        int fromUserId = resultSet.getInt("member_from");

        arrayListToReturn.add(new Message(fromUserId, loggedUserId, text, date));
      }
      resultSet.close();
      statement.close();
      return arrayListToReturn;
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }
}

