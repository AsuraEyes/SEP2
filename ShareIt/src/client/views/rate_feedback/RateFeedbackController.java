package client.views.rate_feedback;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.rate_feedback.RateFeedbackViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

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
   */
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    rateFeedbackViewModel = viewModelFactory.getRateFeedbackViewModel();
    commentaryTextArea.textProperty()
        .bindBidirectional(rateFeedbackViewModel.getCommentaryTextArea());
    usernameLabel.textProperty().bind(rateFeedbackViewModel.getUsernameLabel());
    ratingStars.ratingProperty()
        .bindBidirectional(rateFeedbackViewModel.getRatingProperty());
    commentaryTextArea.clear();
    ratingStars.setRating(0);
    rateFeedbackViewModel.getMemberUsername();

  }

  public void submitButton()
  {
    Stage stage = (Stage) viewHandler.getStage().getScene().getWindow();
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "");
    alert.initOwner(stage);
    alert.getDialogPane().setContentText(
        "The feedback was " + rateFeedbackViewModel.onSubmitButtonPressed()
            + " successfully!");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK)
    {
      viewHandler.setView(viewHandler.menu(), viewHandler.viewMemberProfile());
    }
  }

  public void goBackToUsersPageButton()
  {
    viewHandler.setView(viewHandler.menu(), viewHandler.viewMemberProfile());
  }
}
