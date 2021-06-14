package client.model.message;

import shared.transferobjects.Message;
import shared.transferobjects.Rating;
import shared.transferobjects.Report;
import shared.transferobjects.Warning;
import shared.util.Subject;

import java.util.ArrayList;

/**
 * The interface of Message model.
 * Middle point between Client and ViewModels handling messages data
 */
public interface MessageModel extends Subject
{
  /**
   * Gets data of a new feedback from ViewModel and sends it to the database.
   *
   * @param starValue the star value
   * @param feedback  the feedback
   * @param username1 username of Member filing the feedback
   * @param username2 username of Member being rated
   * @return data from Client
   */
  String addFeedback(double starValue, String feedback, String username1,
      String username2);

  /**
   *  Gets data of a new report from ViewModel and sends it to the database.
   *
   * @param feedback  the feedback
   * @param username1 username of Member filing the report
   * @param username2 username of Member being reported
   * @return data from Client
   */
  String addReport(String feedback, String username1, String username2);

  /**
   * Gets search text .
   *
   * @return a phrase that was used for searching on WelcomePage view.
   */
  String getSearchText();
  /**
   * Sets search text.
   *
   * @param text a phrase that was used for searching on WelcomePage view.
   */
  void setSearchText(String text);

  /**
   * Gets all ratings on member from database.
   *
   * @param memberUsername the member username
   * @return all ratings on member
   */
  ArrayList<Rating> getAllRatingsOnMember(String memberUsername);
  /**
   * Gets rating with data from database.
   *
   * @param fromUsername the from username
   * @param toUsername   the to username
   * @return rating that matched the data
   */
  Rating getRating(String fromUsername, String toUsername);
  /**
   * Gets report with data from database.
   *
   * @param fromUsername the from username
   * @param toUsername   the to username
   * @return report that matched the data
   */
  Report getReport(String fromUsername, String toUsername);
  /**
   * Updates rating in the database, with the given data from ViewModel.
   *
   * @param rating updated rating
   */
  void updateRating(Rating rating);
  /**
   * Update report in the database, with the given data from ViewModel.
   *
   * @param report updated report
   */
  void updateReport(Report report);
  /**
   * Gets all received messages from MessageModelManager for the logged user.
   *
   * @return all received messages
   */
  ArrayList<Message> getAllReceivedMessages();
  /**
   * Sets all received messages in the MessageModelManager, using data from database.
   *
   * @param loggedUsername Member that messages were sent to
   */
  void setAllReceivedMessages(String loggedUsername);
  /**
   * Gets all warnings, for all the members, from MessageModelManager.
   *
   * @return all warnings
   */
  ArrayList<Warning> getAllWarnings();
  /**
   * Gets messages from specified user from the database.
   *
   * @param loggedUserId the logged user id
   * @param fromUserid   the from userid
   * @return messages from user
   */
  ArrayList<Message> getMessagesFromUser(int loggedUserId, int fromUserid);
  /**
   * Gets warnings from the database for the logged user.
   *
   * @param administrator the administrator
   * @param idTo          the id of the logged user.
   * @return warnings that matched the data
   */
  ArrayList<Warning> getWarnings(String administrator, int idTo);
  /**
   * Sends a message to user and stores it in the database.
   *
   * @param message the message
   */
  void sendMessage(Message message);
  /**
   * Sets all received warnings for the user, in MessageModelManager, from the database.
   */
  void setAllReceivedWarnings();
  /**
   * Send warning and stores it in the database.
   *
   * @param warning the warning
   */
  void sendWarning(Warning warning);

}
