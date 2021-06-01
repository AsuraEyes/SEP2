package client.model.message;

import shared.transferobjects.Message;
import shared.transferobjects.Rating;
import shared.transferobjects.Report;
import shared.transferobjects.Warning;
import shared.util.Subject;

import java.util.ArrayList;

public interface MessageModel extends Subject
{
  String addFeedback(double starValue, String feedback, String username1,
      String username2);

  String addReport(String feedback, String username1, String username2);

  String getSearchText();
  void setSearchText(String text);

  ArrayList<Rating> getAllRatingsOnMember(String memberUsername);
  Rating getRating(String fromUsername, String toUsername);
  Report getReport(String fromUsername, String toUsername);
  void updateRating(Rating rating);
  void updateReport(Report report);
  ArrayList<Message> getAllReceivedMessages();
  void setAllReceivedMessages(String loggedUsername);
  ArrayList<Warning> getAllWarnings();
  ArrayList<Message> getMessagesFromUser(int loggedUserId, int fromUserid);
  ArrayList<Warning> getWarnings(String administrator, int idTo);
  void sendMessage(Message message);
  void setAllReceivedWarnings();
  void sendWarning(Warning warning);

}
