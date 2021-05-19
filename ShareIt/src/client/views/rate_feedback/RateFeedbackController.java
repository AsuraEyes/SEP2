package client.views.rate_feedback;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.create_account.CreateAccountViewModel;
import client.viewmodel.rate_feedback.RateFeedbackViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.sql.SQLException;

public class RateFeedbackController
{
  @FXML private Rating ratingStars;
  @FXML private TextArea commentaryTextArea;
  @FXML private Label usernameLabel;
  private RateFeedbackViewModel rateFeedbackViewModel;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws SQLException, IOException
  {
    this.viewHandler = viewHandler;
    rateFeedbackViewModel = viewModelFactory.getRateFeedbackViewModel();
    commentaryTextArea.textProperty().bindBidirectional(rateFeedbackViewModel.getCommentaryTextArea());
  }

  public void submitButton(ActionEvent actionEvent) throws IOException
  {
    String message = rateFeedbackViewModel.onSubmitButtonPressed(ratingStars.getRating());

  }

  public void goBackToUsersPageButton(ActionEvent actionEvent)
  {
  }


}
