package client.viewmodel.report_member;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Report;

import java.io.IOException;

/**
 * A class that holds and manages data from the ReportMember view.
 */
public class ReportMemberViewModel
{
  private ShareItModel model;
  private final StringProperty usernameLabel;
  private final StringProperty commentaryTextArea;

  /**
   * Instantiates a new ReportMemberViewModel.
   *
   * @param model The model that this ViewModel uses
   */
  public ReportMemberViewModel(ShareItModel model)
  {
    this.model = model;
    usernameLabel = new SimpleStringProperty();
    commentaryTextArea = new SimpleStringProperty();

  }

  /**
   * Gets Member's username.
   *
   * @return returns the usernameLabel
   */
  public StringProperty getUsernameLabel()
{
  return usernameLabel;
}

  /**
   * Gets Member's commentary.
   *
   * @return returns commentary input
   */
  public StringProperty getCommentaryTextArea()
  {
    return commentaryTextArea;
  }

  /**
   * Gets reported memberUsername.
   *
   * @return returns username of Member that is reported
   */
  public String getMemberUsername() {
    usernameLabel.setValue(model.getMemberUsername());
    return model.getMemberUsername();
  }

  /**
   * Gets report.
   */
  public void getReport()
  {
    Report report = model.getReport(model.getLoggedInUsername(),
        model.getMemberUsername());
    if(report != null)
    {
      commentaryTextArea.setValue(report.getCommentary());
    }
  }

  /**
   * Updates report based on if member already reported this member.
   */
  public void updateReport()
  {
    int memberFromId = model.getMemberByUsername(model.getLoggedInUsername())
        .getId();
    int memberToId = model.getMemberByUsername(model.getMemberUsername())
        .getId();
    Report report = new Report(commentaryTextArea.getValue(), memberFromId, memberToId);
    model.updateReport(report);
  }

  /**
   * Adds new report.
   *
   * @return returns new Report object
   * @throws IOException
   */
  public String addReport() throws IOException
  {
    return model.addReport(commentaryTextArea.getValue(), model.getLoggedInUsername(),
            getMemberUsername());
  }

  /**
   * After Report button have been pressed this method sends data to the model.
   *
   * @return returns string based on if report has been created or updated
   * @throws IOException
   */
  public String onReportButtonPressed() throws IOException
  {
    if (!addReport().equals("Added"))
    {
      updateReport();
      return "updated";
    }
    return "created";
  }

}
