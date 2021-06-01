package server.model.database.message;

import shared.transferobjects.Message;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The interface of Message Data Access Object.
 */
public interface MessageDAO
{
  /**
   * Gets all received messages for logged in member.
   *
   * @param loggedUserId The user that is currently logged in
   * @return returns all the messages connected with this particular member
   */
  ArrayList<Message> getAllReceivedMessages(int loggedUserId);
  /**
   * Gets all the messages connected to the member that sent it and loggedUser that received it
   *
   * @param loggedUserId the logged user id
   * @param fromUserid   the from userid
   * @return returns a list of all messages that are matched with given data
   */
  ArrayList<Message> getMessagesFromUser(int loggedUserId, int fromUserid);
  /**
   * Creates new message by putting data provided by member
   * @param message message object that will be sent
   * @return returns new object of Message with data which was provided by Member while creating new Message
   */
  Message sendMessage(Message message);
}
