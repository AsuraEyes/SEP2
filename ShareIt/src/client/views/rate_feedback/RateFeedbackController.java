package client.views.rate_feedback;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.model.state.StateManager;
import client.model.state.VisitorState;
import client.viewmodel.create_account.CreateAccountViewModel;
import client.viewmodel.rate_feedback.RateFeedbackViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class RateFeedbackController
{
  @FXML private Rating ratingStars;
  @FXML private TextArea commentaryTextArea;
  @FXML private Label usernameLabel;
  private RateFeedbackViewModel rateFeedbackViewModel;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws  IOException
  {
    this.viewHandler = viewHandler;
    rateFeedbackViewModel = viewModelFactory.getRateFeedbackViewModel();
    commentaryTextArea.textProperty().bindBidirectional(rateFeedbackViewModel.getCommentaryTextArea());
    usernameLabel.textProperty().bind(rateFeedbackViewModel.getUsernameLabel());
    ratingStars.ratingProperty().bindBidirectional(rateFeedbackViewModel.getRatingProperty());
    System.out.println("here");
    rateFeedbackViewModel.getMemberUsername();
    rateFeedbackViewModel.getRating();


  }

  public void submitButton(ActionEvent actionEvent) throws IOException
  {
      Stage stage = (Stage) viewHandler.getStage().getScene().getWindow();
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "");
      alert.initOwner(stage);
      alert.getDialogPane().setContentText("The feedback was " + rateFeedbackViewModel.onSubmitButtonPressed() + " successfully!");

      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == ButtonType.OK)
      {
        commentaryTextArea.clear();
        ratingStars.setRating(0);
        viewHandler.setView(viewHandler.menu(), viewHandler.welcomePage());
      }

    //rateFeedbackViewModel.onSubmitButtonPressed();

    //viewHandler.setView(viewHandler.menu(), viewHandler.viewMemberProfile());
  }

  public void goBackToUsersPageButton(ActionEvent actionEvent)
      throws IOException
  {
    viewHandler.setView(viewHandler.menu(), viewHandler.viewMemberProfile());
  }
}
