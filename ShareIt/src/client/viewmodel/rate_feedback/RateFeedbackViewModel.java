package client.viewmodel.rate_feedback;

import client.model.member.MemberModel;
import client.model.message.MessageModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Rating;
/**
 * A class that holds and manages data from the RateFeedback view.
 */
public class RateFeedbackViewModel
{
  private StringProperty commentaryTextArea;
  private SimpleStringProperty usernameLabel;
  private DoubleProperty ratingProperty;
  private MemberModel memberModel;
  private MessageModel messageModel;
  /**
   * Instantiates StringProperties used for binding with the fields in the controller
   *
   * @param memberModel The model that this ViewModel uses
   * @param messageModel The model that this ViewModel uses
   */
  public RateFeedbackViewModel(MemberModel memberModel,
      MessageModel messageModel)
  {
    this.memberModel = memberModel;
    this.messageModel = messageModel;

    ratingProperty = new SimpleDoubleProperty();
    commentaryTextArea = new SimpleStringProperty();
    usernameLabel = new SimpleStringProperty();
  }

  public StringProperty getCommentaryTextArea()
  {
    return commentaryTextArea;
  }

  public StringProperty getUsernameLabel()
  {
    return usernameLabel;
  }

  public DoubleProperty getRatingProperty()
  {
    return ratingProperty;
  }
  /**
   * Sends the username to the controller.
   * calls the setRating() method
   *
   * @return returns username of Member that is rated
   */
  public String getMemberUsername()
  {
    usernameLabel.setValue(memberModel.getMemberUsername());
    setRating();
    return memberModel.getMemberUsername();
  }
  /**
   * Retrieves the rating, and sets it in the controller
   */
  public void setRating()
  {
    Rating rating = messageModel.getRating(memberModel.getLoggedInUsername(),
        memberModel.getMemberUsername());
    if (rating != null)
    {
      ratingProperty.setValue(rating.getRating());
      commentaryTextArea.setValue(rating.getCommentary());
    }
  }
  /**
   * Updates feedback based on if member already rated this member.
   */
  public void updateFeedback()
  {
    int memberFromId = memberModel
        .getMemberByUsername(memberModel.getLoggedInUsername()).getId();
    int memberToId = memberModel
        .getMemberByUsername(memberModel.getMemberUsername()).getId();
    Rating rating = new Rating(ratingProperty.getValue(),
        commentaryTextArea.getValue(), memberFromId, memberToId);
    messageModel.updateRating(rating);
  }
  /**
   * Adds new rating feedback.
   *
   * @return returns new Rating object
   */
  public String addFeedback()
  {
    return messageModel
        .addFeedback(ratingProperty.getValue(), commentaryTextArea.getValue(),
            memberModel.getLoggedInUsername(), getMemberUsername());
  }
  /**
   * After Submit button have been pressed this method sends data to the model.
   *
   * @return returns string based on if feedback has been created or updated
   */
  public String onSubmitButtonPressed()
  {
    Rating rating = messageModel.getRating(memberModel.getLoggedInUsername(),
        memberModel.getMemberUsername());
    if (rating != null)
    {
      if (!rating.getCommentary().equals(commentaryTextArea.getValue())
          || rating.getRating() != ratingProperty.getValue())
      {
        updateFeedback();
        return "updated";
      }
    }
    else
      addFeedback();
    return "created";
  }
}
