package server.model.database.message;

import shared.transferobjects.Message;

import java.util.ArrayList;

public interface MessageDAO
{
  ArrayList<Message> getAllReceivedMessages(int loggedUserId);
  ArrayList<Message> getMessagesFromUser(int loggedUserId, int fromUserid);
  Message sendMessage(Message message);
}
