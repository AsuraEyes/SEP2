package client.viewmodel.rate_feedback;

import client.model.ShareItModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Member;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.sql.SQLException;

public class RateFeedbackViewModel
{
  private final ShareItModel model;
  private final StringProperty commentaryTextArea;
  private final SimpleStringProperty usernameLabel;
  private String username1;
  private String username2;

  public RateFeedbackViewModel(ShareItModel model) throws SQLException
  {
    this.model = model;
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

  public String onSubmitButtonPressed(double starValue) throws IOException
  {

    return model.addFeedback(starValue,commentaryTextArea.getValue(),username1,getMemberUsername());
  }

  public String getMemberUsername()
  {
    usernameLabel.setValue(model.getMemberUsername());
    System.out.println("Get member username view member VM : " + model.getMemberUsername());
    return model.getMemberUsername();
  }
  public void setMemberUsername(){
    model.setMemberUsername(usernameLabel.getValue());
  }
}
