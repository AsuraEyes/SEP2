package client.views.rate_feedback;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.Rating;

public class RateFeedbackController
{
  @FXML private Rating ratingStars;
  @FXML private TextArea commentaryTextArea;
  @FXML private Label usernameLabel;
  @FXML private TextField searchField;

  public void searchButton(ActionEvent actionEvent)
  {
  }

  public void submitButton(ActionEvent actionEvent)
  {
  }

  public void goBackToUsersPageButton(ActionEvent actionEvent)
  {
  }
}
