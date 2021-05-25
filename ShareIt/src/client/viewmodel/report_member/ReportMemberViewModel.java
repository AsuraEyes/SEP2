package client.viewmodel.report_member;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Rating;
import shared.transferobjects.Report;

import java.io.IOException;

public class ReportMemberViewModel
{
  private ShareItModel model;
  private final StringProperty usernameLabel;
  private final StringProperty commentaryTextArea;

  public ReportMemberViewModel(ShareItModel model)
  {
    this.model = model;
    usernameLabel = new SimpleStringProperty();
    commentaryTextArea = new SimpleStringProperty();

  }
  public StringProperty getUsernameLabel()
{
  return usernameLabel;
}
  public StringProperty getCommentaryTextArea()
  {
    return commentaryTextArea;
  }

  public String getMemberUsername()
  {
    usernameLabel.setValue(model.getMemberUsername());
    System.out.println("this issmthmber VM : " + model.getMemberUsername());
    return model.getMemberUsername();
  }

  public void getReport()
  {
    Report report = model.getReport(model.getLoggedInUsername(),
        model.getMemberUsername());
    if(report != null)
    {
      commentaryTextArea.setValue(report.getCommentary());
    }
  }

  public void updateReport()
  {
    int memberFromId = model.getMemberByUsername(model.getLoggedInUsername())
        .getId();
    int memberToId = model.getMemberByUsername(model.getMemberUsername())
        .getId();
    Report report = new Report(commentaryTextArea.getValue(), memberFromId, memberToId);
    model.updateReport(report);
  }

  public String addReport() throws IOException
  {
    return model.addReport(commentaryTextArea.getValue(), model.getLoggedInUsername(),
            getMemberUsername());
  }

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
