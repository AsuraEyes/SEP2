package client.views.rate_feedback;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.rate_feedback.RateFeedbackViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.util.Optional;

/**
 * A class that manages an interface and handle interactions in RateFeedback view
 */
public class RateFeedbackController
{
  @FXML private Rating ratingStars;
  @FXML private TextArea commentaryTextArea;
  @FXML private Label usernameLabel;
  private RateFeedbackViewModel rateFeedbackViewModel;
  private ViewHandler viewHandler;

  /**
   * Init.
   *
   * @param viewHandler      the view handler
   * @param viewModelFactory the view model factory
   * @throws IOException the io exception
   */
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws  IOException
  {
    this.viewHandler = viewHandler;
    rateFeedbackViewModel = viewModelFactory.getRateFeedbackViewModel();
    commentaryTextArea.textProperty().bindBidirectional(rateFeedbackViewModel.getCommentaryTextArea());
    usernameLabel.textProperty().bind(rateFeedbackViewModel.getUsernameLabel());
    ratingStars.ratingProperty().bindBidirectional(rateFeedbackViewModel.getRatingProperty());
    commentaryTextArea.clear();
    ratingStars.setRating(0);
    rateFeedbackViewModel.getMemberUsername();

  }

  /**
   * Changes view when button was pressed and data validated.
   *
   * @throws IOException
   */
  public void submitButton() throws IOException {
      Stage stage = (Stage) viewHandler.getStage().getScene().getWindow();
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "");
      alert.initOwner(stage);
      alert.getDialogPane().setContentText("The feedback was " + rateFeedbackViewModel.onSubmitButtonPressed() + " successfully!");

      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == ButtonType.OK) {
        viewHandler.setView(viewHandler.menu(), viewHandler.viewMemberProfile());
      }
  }

  /**
   * Changes view when button was pressed.
   *
   * @throws IOException
   */
  public void goBackToUsersPageButton() throws IOException {
    viewHandler.setView(viewHandler.menu(), viewHandler.viewMemberProfile());
  }
}
