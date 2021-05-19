package client.viewmodel.rate_feedback;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.sql.SQLException;

public class RateFeedbackViewModel
{
  private ShareItModel model;
  private StringProperty commentaryTextArea;
  private String username1;
  private String username2;

  public RateFeedbackViewModel(ShareItModel model) throws SQLException
  {
    this.model = model;
    commentaryTextArea = new SimpleStringProperty();
  }

  public StringProperty getCommentaryTextArea(){
    return commentaryTextArea;
  }

  public String onSubmitButtonPressed(double starValue) throws IOException
  {

    return model.addFeedback(starValue,commentaryTextArea.getValue(),username1,username2);
  }


}
