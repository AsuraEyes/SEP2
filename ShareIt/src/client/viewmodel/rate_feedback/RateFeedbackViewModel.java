package client.viewmodel.rate_feedback;

import client.model.ShareItModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Rating;

import java.io.IOException;

/**
 * A class that holds and manages data from the RateFeedback view.
 */
public class RateFeedbackViewModel
{
  private final ShareItModel model;
  private final StringProperty commentaryTextArea;
  private final SimpleStringProperty usernameLabel;
  private final DoubleProperty ratingProperty;

  /**
   * Instantiates a new RateFeedbackViewModel.
   *
   * @param model The model that this ViewModel uses
   */
  public RateFeedbackViewModel(ShareItModel model)
  {
    this.model = model;
    ratingProperty = new SimpleDoubleProperty();
    commentaryTextArea = new SimpleStringProperty();
    usernameLabel = new SimpleStringProperty();
  }

  /**
   * Gets Member's commentary.
   *
   * @return returns commentary input
   */
  public StringProperty getCommentaryTextArea(){
    return commentaryTextArea;
  }

  /**
   * Gets Member's username.
   *
   * @return returns the usernameLabel
   */
  public StringProperty getUsernameLabel()
  {
    return usernameLabel;
  }

  /**
   * Gets how high Member did rate
   *
   * @return returns rating(from 1.0 to 5.0)
   */
  public DoubleProperty getRatingProperty(){ return ratingProperty;}

  /**
   * Gets rated memberUsername.
   *
   * @return returns username of Member that is rated
   */
  public String getMemberUsername()
  {
    usernameLabel.setValue(model.getMemberUsername());
    getRating();
    return model.getMemberUsername();
  }

  /**
   * Gets rating of rated member.
   */
  public void getRating()
  {
    Rating rating = model.getRating(model.getLoggedInUsername(),
        model.getMemberUsername());
    if(rating != null)
    {
      ratingProperty.setValue(rating.getRating());
      commentaryTextArea.setValue(rating.getCommentary());
    }
  }

  /**
   * Updates feedback based on if member already rated this member.
   */
  public void updateFeedback(){
    int memberFromId = model.getMemberByUsername(model.getLoggedInUsername())
        .getId();
    int memberToId = model.getMemberByUsername(model.getMemberUsername()).getId();
    Rating rating = new Rating(ratingProperty.getValue(),
        commentaryTextArea.getValue(),memberFromId,memberToId);
    model.updateRating(rating);
  }

  /**
   * Adds new rating feedback.
   *
   * @return returns new Rating object
   * @throws IOException
   */
  public String addFeedback() throws IOException
  {
    return model.addFeedback(ratingProperty.getValue(),commentaryTextArea.getValue(),
        model.getLoggedInUsername(), getMemberUsername());
  }

  /**
   * After Submit button have been pressed this method sends data to the model.
   *
   * @return returns string based on if feedback has been created or updated
   * @throws IOException
   */
  public String onSubmitButtonPressed() throws IOException
  {
    if(!addFeedback().equals("Added"))
    {
      updateFeedback();
      return "updated";
    }
    return "created";
  }
}
