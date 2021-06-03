package client.views.report_member;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.report_member.ReportMemberViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

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
   */
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    reportMemberViewModel = viewModelFactory.getReportMemberViewModel();
    usernameLabel.textProperty().bind(reportMemberViewModel.getUsernameLabel());
    commentaryArea.textProperty()
        .bindBidirectional(reportMemberViewModel.getCommentaryTextArea());
    reportMemberViewModel.getMemberUsername();
  }

  public void reportButton()
  {
    Stage stage = (Stage) viewHandler.getStage().getScene().getWindow();
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "");
    alert.initOwner(stage);
    alert.getDialogPane().setContentText(
        "The report was " + reportMemberViewModel.onReportButtonPressed()
            + " successfully!");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK)
    {
      viewHandler.setView(viewHandler.menu(), viewHandler.viewMemberProfile());
    }
  }

  public void goBackToUserPageButton()
  {
    viewHandler.setView(viewHandler.menu(), viewHandler.viewMemberProfile());
  }
}
