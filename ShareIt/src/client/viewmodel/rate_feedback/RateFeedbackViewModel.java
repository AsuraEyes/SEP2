package client.viewmodel.rate_feedback;

import client.model.ShareItModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Rating;

import java.io.IOException;

public class RateFeedbackViewModel
{
  private final ShareItModel model;
  private final StringProperty commentaryTextArea;
  private final SimpleStringProperty usernameLabel;
  private final DoubleProperty ratingProperty;


  public RateFeedbackViewModel(ShareItModel model)
  {
    this.model = model;
    ratingProperty = new SimpleDoubleProperty();
    commentaryTextArea = new SimpleStringProperty();
    usernameLabel = new SimpleStringProperty();
  }


  public StringProperty getCommentaryTextArea(){
    return commentaryTextArea;
  }

  public StringProperty getUsernameLabel()
  {
    return usernameLabel;
  }

  public DoubleProperty getRatingProperty(){ return ratingProperty;}



  public String getMemberUsername()
  {
    usernameLabel.setValue(model.getMemberUsername());
    getRating();
    return model.getMemberUsername();
  }

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
  public void updateFeedback(){
    int memberFromId = model.getMemberByUsername(model.getLoggedInUsername())
        .getId();
    int memberToId = model.getMemberByUsername(model.getMemberUsername()).getId();
    Rating rating = new Rating(ratingProperty.getValue(),
        commentaryTextArea.getValue(),memberFromId,memberToId);
    model.updateRating(rating);
  }
  public String addFeedback() throws IOException
  {
    return model.addFeedback(ratingProperty.getValue(),commentaryTextArea.getValue(),
        model.getLoggedInUsername(), getMemberUsername());
  }
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
