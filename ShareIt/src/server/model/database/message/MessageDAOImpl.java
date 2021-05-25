package server.model.database.message;

import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;
import server.model.database.member.MemberDAOImpl;
import shared.transferobjects.Member;
import shared.transferobjects.Message;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MessageDAOImpl implements MessageDAO
{
  private static MessageDAOImpl instance;
  private String password;

  private MessageDAOImpl()throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized MessageDAOImpl getInstance() throws SQLException {
    if(instance == null){
      instance = new MessageDAOImpl();
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
      //.prepareStatement("SELECT time, username, text FROM share_it.message, share_it.member WHERE member_from = member.id AND member_to = ? ORDER BY time desc");
      System.out.println("Logged in user id: "+loggedUserId);
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM share_it.message WHERE member_to = ? AND time IN (SELECT MAX(time) FROM share_it.message WHERE member_to = ? GROUP BY member_from) ORDER BY time DESC ;");
      statement.setInt(1, loggedUserId);
      statement.setInt(2, loggedUserId);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Message> arrayListToReturn = new ArrayList<>();

      while (resultSet.next())
      {
        String text = resultSet.getString("text");
        Date d2 = resultSet.getTimestamp("time");
        int fromUserId = resultSet.getInt("member_from");
        //String username = resultSet.getString("username");
        Member member = MemberDAOImpl.getInstance().getMemberById(fromUserId);


        //arrayListToReturn.add(new Message(fromUserId, loggedUserId, text, d2));
        arrayListToReturn.add(new Message(d2, member.getUsername(), text));
      }
/*
      while (resultSet.next())
      {
        String text = resultSet.getString("text");
        Date d1 = new Date(resultSet.getDate("time").getTime());
        Date d2 = resultSet.getTimestamp("time");
        java.sql.Date date = resultSet.getDate("time");
        date.toInstant();
        int fromUserId = resultSet.getInt("member_from");

        arrayListToReturn.add(new Message(fromUserId, loggedUserId, text, d2));
      }
    */
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
      Date currentTime = Calendar.getInstance().getTime();
      Timestamp ts = new Timestamp(currentTime.getTime());

      statement.setTimestamp(2, ts);
      statement.setInt(3, message.getMemberFrom());
      statement.setInt(4, message.getMemberTo());
      statement.executeUpdate();

      Message returnMessage = new Message(ts, MemberDAOImpl.getInstance().getMemberById(
          message.getMemberFrom()).getUsername(), message.getText());
      returnMessage.setMemberFrom(message.getMemberFrom());
      returnMessage.setMemberTo(message.getMemberTo());
      System.out.println("In the DAO: "+returnMessage);
      System.out.println("IN the DAO the first one: "+message);
      return returnMessage;

      /*ResultSet generatedKeys = statement.getGeneratedKeys();
      if (generatedKeys.next())
      {
        return new Message(message.getMemberFrom(), message.getMemberTo(), message.getText(), ts);
      }
      else
      {
        throw new SQLException("No keys generated");
      }*/


       /*statement = connection
          .prepareStatement("SELECT time, username, text FROM share_it.message, share_it.member WHERE member_to = member.id AND member_to = ? AND  member_from = ? AND time = ?");
      statement.setInt(1, message.getMemberFrom());
      statement.setInt(2, message.getMemberTo());
      statement.setTimestamp(3, ts);

      ResultSet resultSet = statement.executeQuery();
      if(resultSet.next())
      {
        String text = resultSet.getString("text");
        Date d2 = resultSet.getTimestamp("time");
        String username = resultSet.getString("username");
        System.out.println(text + d2 + username);
        Message returnMessage = new Message(d2,username, text);
        returnMessage.setMemberFrom(message.getMemberFrom());
        returnMessage.setMemberTo(message.getMemberTo());
        return returnMessage;
      }
      else
        System.out.println("Problem!");*/

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
      /*PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM share_it.message WHERE member_to = ? AND member_from = ?");*/
      PreparedStatement statement = connection
          .prepareStatement("SELECT time, text, username FROM share_it.message, share_it.member WHERE ((member_from = ? AND member_to = ?)OR(member_from = ? AND member_to = ?))AND (member.id = message.member_from) ORDER BY time;");
      statement.setInt(1, loggedUserId);
      statement.setInt(2, fromUserid);
      statement.setInt(3, fromUserid);
      statement.setInt(4, loggedUserId);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Message> arrayListToReturn = new ArrayList<>();

      while (resultSet.next())
      {
        String text = resultSet.getString("text");
       // Date date = resultSet.getDate("time");
        //Date d1 = new Date(resultSet.getDate("time").getTime());
        Date d2 = resultSet.getTimestamp("time");
       // int fromUserId = resultSet.getInt("member_from");
        String username = resultSet.getString("username");


        //arrayListToReturn.add(new Message(fromUserId, loggedUserId, text, d2));
        arrayListToReturn.add(new Message(d2, username, text));
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

