package client.viewmodel.rate_feedback;

import client.model.ShareItModel;
import client.model.state.StateManager;
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


  public RateFeedbackViewModel(ShareItModel model)
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

    return model.addFeedback(starValue,commentaryTextArea.getValue(),
        StateManager.getInstance().getUsername(),getMemberUsername());
  }

  public String getMemberUsername()
  {
    usernameLabel.setValue(model.getMemberUsername());
    System.out.println("this issmthmber VM : " + model.getMemberUsername());
    return model.getMemberUsername();
  }
  public void setMemberUsername(){
    model.setMemberUsername(usernameLabel.getValue());
  }
}
