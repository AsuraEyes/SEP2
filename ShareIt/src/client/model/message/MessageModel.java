package client.model.message;

import shared.transferobjects.Message;
import shared.transferobjects.Rating;
import shared.transferobjects.Report;
import shared.transferobjects.Warning;
import shared.util.Subject;

import java.util.ArrayList;

/**
 * The interface of Message model.
 */
public interface MessageModel extends Subject
{
  /**
   * Gets data of a new feedback from ViewModel and sends it to the Client.
   *
   * @param starValue the star value
   * @param feedback  the feedback
   * @param username1 username of Member filing the feedback
   * @param username2 username of Member being rated
   * @return returns data from Client
   */
  String addFeedback(double starValue, String feedback, String username1,
      String username2);

  /**
   *  Gets data of a new report from ViewModel and sends it to the Client.
   *
   * @param feedback  the feedback
   * @param username1 username of Member filing the report
   * @param username2 username of Member being reported
   * @return returns data from Client
   */
  String addReport(String feedback, String username1, String username2);

  /**
   * Gets search text.
   *
   * @return returns a phrase that was used for searching
   */
  String getSearchText();
  /**
   * Sets search text.
   *
   * @param text a phrase that was used for searching
   */
  void setSearchText(String text);

  /**
   * Gets all ratings on member from a Client.
   *
   * @param memberUsername the member username
   * @return returns all ratings on member
   */
  ArrayList<Rating> getAllRatingsOnMember(String memberUsername);
  /**
   * Gets rating with data from ViewModel and sending it to a Client.
   *
   * @param fromUsername the from username
   * @param toUsername   the to username
   * @return returns rating that matched the data
   */
  Rating getRating(String fromUsername, String toUsername);
  /**
   * Gets report with data from ViewModel and sending it to a Client.
   *
   * @param fromUsername the from username
   * @param toUsername   the to username
   * @return returns report that matched the data
   */
  Report getReport(String fromUsername, String toUsername);
  /**
   * Updates rating with given data from ViewModel.
   *
   * @param rating updated rating
   */
  void updateRating(Rating rating);
  /**
   * Update report with given data from ViewModel.
   *
   * @param report updated report
   */
  void updateReport(Report report);
  /**
   * Gets all received messages.
   *
   * @return returns all received messages
   */
  ArrayList<Message> getAllReceivedMessages();
  /**
   * Sets all received messages using data from Client.
   *
   * @param loggedUsername Member that messages were sent to
   */
  void setAllReceivedMessages(String loggedUsername);
  /**
   * Gets all warnings.
   *
   * @return returns all warnings
   */
  ArrayList<Warning> getAllWarnings();
  /**
   * Gets messages from user with given data from ViewModel and sending it to the Client.
   *
   * @param loggedUserId the logged user id
   * @param fromUserid   the from userid
   * @return returns messages from user
   */
  ArrayList<Message> getMessagesFromUser(int loggedUserId, int fromUserid);
  /**
   * Gets warnings from the Client.
   *
   * @param administrator the administrator
   * @param idTo          the id to
   * @return returns warnings that matched the data
   */
  ArrayList<Warning> getWarnings(String administrator, int idTo);
  /**
   * Sends a message.
   *
   * @param message the message
   */
  void sendMessage(Message message);
  /**
   * Sets all received warnings from the Client.
   */
  void setAllReceivedWarnings();
  /**
   * Send warning with data from ViewModel to the Client.
   *
   * @param warning the warning
   */
  void sendWarning(Warning warning);

}
