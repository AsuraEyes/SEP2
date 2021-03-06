package client.views.view_reported_member;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.view_reported_member.ViewReportedMemberViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
/**
 * A class that manages an interface and handle interactions in ViewReportedMember view
 */
public class ViewReportedMemberController
{
  @FXML private Label reportedMemberField;
  @FXML private Label reportedByField;
  @FXML private Label commentField;

  private ViewReportedMemberViewModel viewReportedMemberViewModel;
  private ViewHandler viewHandler;

  /**
   * Init.
   * Loads all reported members
   * @param viewHandler      the view handler
   * @param viewModelFactory the view model factory
   */

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    viewReportedMemberViewModel = viewModelFactory
        .getViewReportedMemberViewModel();
    reportedMemberField.textProperty()
        .bind(viewReportedMemberViewModel.getReportedNameLabel());
    reportedByField.textProperty()
        .bind(viewReportedMemberViewModel.getReporterNameLabel());
    commentField.textProperty()
        .bind(viewReportedMemberViewModel.getCommentaryLabel());
    viewReportedMemberViewModel.loadReport();
  }

  public void goBackToReportedMembers()
  {
    viewReportedMemberViewModel.loadAllReportedMembers();
    viewHandler
        .setView(viewHandler.menu(), viewHandler.viewReportedMemberList());
  }

  public void reporterMemberClickedOn()
  {
    viewReportedMemberViewModel.setReporterNameLabel();
    viewHandler.setView(viewHandler.menu(), viewHandler.viewMemberProfile());
  }

  public void reportedMemberClickedOn()
  {
    viewReportedMemberViewModel.setReportedNameLabel();
    viewHandler.setView(viewHandler.menu(), viewHandler.viewMemberProfile());
  }
}
