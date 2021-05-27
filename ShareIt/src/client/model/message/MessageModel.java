package client.model.message;

import shared.transferobjects.*;
import shared.util.Subject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface MessageModel extends Subject
{
  String addFeedback(double starValue, String feedback, String username1, String username2)throws
      IOException;

  String addReport(String feedback, String username1, String username2) throws IOException;


  String getSearchText();
  void setSearchText(String text);

  ArrayList<Rating> getAllRatingsOnMember(String memberUsername);
  Rating getRating(String fromUsername, String toUsername);
  Report getReport(String fromUsername, String toUsername);
  void updateRating(Rating rating);
  void updateReport(Report report);
  ArrayList<Message> getAllReceivedMessages();
  ArrayList<Warning> getAllWarnings();

  ArrayList<Message> getMessagesFromUser(int loggedUserId, int fromUserid);
  ArrayList<Warning> getWarnings(String administrator, int idTo);
  void sendMessage(Message message);
  void setAllReceivedMessages(String loggedUsername);
  void setAllReceivedWarnings();
  void sendWarning(Warning warning);

}
