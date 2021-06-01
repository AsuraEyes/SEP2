package client.views.report_member;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.report_member.ReportMemberViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.Optional;

/**
 * A class that manages an interface and handle interactions in ReportMember view
 */
public class ReportMemberController
{
  @FXML private Label usernameLabel;
  @FXML private TextArea commentaryArea;

  private ReportMemberViewModel reportMemberViewModel;
  private ViewHandler viewHandler;

  /**
   * Init.
   *
   * @param viewHandler      the view handler
   * @param viewModelFactory the view model factory
   * @throws IOException the io exception
   */
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws IOException {
    this.viewHandler = viewHandler;
    reportMemberViewModel = viewModelFactory.getReportMemberViewModel();
    usernameLabel.textProperty().bind(reportMemberViewModel.getUsernameLabel());
    commentaryArea.textProperty().bindBidirectional(reportMemberViewModel.getCommentaryTextArea());
    reportMemberViewModel.getMemberUsername();
  }

  /**
   *  Changes a view when button was pressed and data validated.
   *
   * @throws IOException the io exception
   */
  public void reportButton() throws IOException {
    Stage stage = (Stage) viewHandler.getStage().getScene().getWindow();
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "");
    alert.initOwner(stage);
    alert.getDialogPane().setContentText("The report was " + reportMemberViewModel.onReportButtonPressed() + " successfully!");

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
  public void goBackToUserPageButton() throws IOException {
    viewHandler.setView(viewHandler.menu(), viewHandler.viewMemberProfile());
  }
}
