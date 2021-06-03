package client.views.view_reported_member_list;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.view_reported_member_list.ViewReportedMemberListViewModel;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import shared.transferobjects.Report;

import java.util.List;
/**
 * A class that manages an interface and handle interactions in ViewReportedMembersList view
 */
public class ViewReportedMemberListController
{
  @FXML private VBox vBox;

  private ViewReportedMemberListViewModel viewReportedMemberListViewModel;
  private ViewHandler viewHandler;
  /**
   * Init.
   * Loads the list of all reports and display the username of the reported member
   * and the member that got reported
   * @param viewHandler      the view handler
   * @param viewModelFactory the view model factory
   */
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    viewReportedMemberListViewModel = viewModelFactory
        .getViewReportedMemberListViewModel();
    displayReports(viewReportedMemberListViewModel.getReportList());
  }
  /**
   * Loads all the member's reports and places them individually in a
   * separate Vbox. The VBox's are created for each message and display with the
   * help of Label the required content.
   * @param reports All the reports that members have filed
   */
  public void displayReports(List<Report> reports)
  {
    if (reports != null && !reports.isEmpty())
    {
      for (int i = 0; i < reports.size(); i++)
      {
        String reportedUsername = reports.get(i).getUsernameTo();
        Label reportedNameLabel = new Label("Reported: " + reportedUsername);
        reportedNameLabel.getStyleClass().add("reported");
        String reporterUsername = reports.get(i).getUsernameFrom();
        Label reporterNameLabel = new Label("Reporter: " + reporterUsername);
        reporterNameLabel.getStyleClass().add("reporter");

        VBox littleVBox = new VBox();
        littleVBox.getChildren().addAll(reportedNameLabel, reporterNameLabel);
        littleVBox.setPadding(new Insets(20, 160, 20, 160));
        littleVBox.getStyleClass().add("littleVbox");

        vBox.getChildren().addAll(littleVBox);
        vBox.getStyleClass().add("vbox");
        vBox.getChildren().get(i)
            .addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
              viewReportedMemberListViewModel
                  .setUsernames(reporterUsername, reportedUsername);
              viewHandler.setView(viewHandler.menu(),
                  viewHandler.viewReportedMember());
            });
      }
    }
  }

}
