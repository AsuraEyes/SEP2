package server.model.database.chat;

import shared.transferobjects.Message;

import java.util.ArrayList;

public interface ChatDAO
{
  ArrayList<Message> getAllReceivedMessages(int loggedUserId);
  ArrayList<Message> getMessagesFromUser(int loggedUserId, int fromUserid);
  Message sendMessage(Message message);
}
