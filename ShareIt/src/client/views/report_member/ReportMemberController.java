package client.views.report_member;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.report_member.ReportMemberViewModel;
import client.viewmodel.seatch_for_rental.SearchForRentalViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ReportMemberController
{
  @FXML private Label usernameLabel;
  @FXML private TextArea commentaryArea;

  private ReportMemberViewModel reportMemberViewModel;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws IOException
  {
    this.viewHandler = viewHandler;
    reportMemberViewModel = viewModelFactory.getReportMemberViewModel();
    usernameLabel.textProperty().bind(reportMemberViewModel.getUsernameLabel());
    commentaryArea.textProperty().bindBidirectional(reportMemberViewModel.getCommentaryArea());
  }

  public void reportButton(ActionEvent actionEvent)
  {
  }



  public void goBackToUserPageButton(ActionEvent actionEvent)
  {
  }
}
