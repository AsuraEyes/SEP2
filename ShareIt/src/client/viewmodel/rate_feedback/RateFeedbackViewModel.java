package client.viewmodel.rate_feedback;


import client.model.member.MemberModel;
import client.model.message.MessageModel;
import client.model.rental.RentalModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Rating;
import shared.transferobjects.Rental;

import java.io.IOException;

public class RateFeedbackViewModel
{
  private RentalModel rentalModel;
  private MemberModel memberModel;
  private MessageModel messageModel;

  private final StringProperty commentaryTextArea;
  private final SimpleStringProperty usernameLabel;
  private final DoubleProperty ratingProperty;


  public RateFeedbackViewModel(RentalModel rentalModel, MemberModel memberModel, MessageModel messageModel)
  {
    this.rentalModel = rentalModel;
    this.memberModel = memberModel;
    this.messageModel = messageModel;

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
    usernameLabel.setValue(memberModel.getMemberUsername());
    setRating();
    return memberModel.getMemberUsername();
  }

  public void setRating()
  {
    Rating rating = messageModel.getRating(memberModel.getLoggedInUsername(),
        memberModel.getMemberUsername());
    if(rating != null)
    {
      ratingProperty.setValue(rating.getRating());
      commentaryTextArea.setValue(rating.getCommentary());
    }
  }
  public void updateFeedback(){
    int memberFromId = memberModel.getMemberByUsername(memberModel.getLoggedInUsername())
        .getId();
    int memberToId = memberModel.getMemberByUsername(memberModel.getMemberUsername()).getId();
    Rating rating = new Rating(ratingProperty.getValue(),
        commentaryTextArea.getValue(),memberFromId,memberToId);
    messageModel.updateRating(rating);
  }
  public String addFeedback()
  {
    return messageModel.addFeedback(ratingProperty.getValue(),commentaryTextArea.getValue(),
        memberModel.getLoggedInUsername(), getMemberUsername());
  }
  public String onSubmitButtonPressed()
  {
    Rating rating = messageModel.getRating(memberModel.getLoggedInUsername(),
        memberModel.getMemberUsername());
    if(rating != null)
    {
      if(!rating.getCommentary().equals(commentaryTextArea.getValue()) || rating.getRating() != ratingProperty.getValue())
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
