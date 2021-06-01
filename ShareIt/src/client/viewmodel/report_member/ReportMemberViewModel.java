package client.viewmodel.report_member;

import client.model.member.MemberModel;
import client.model.message.MessageModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Report;
/**
 * A class that holds and manages data from the ReportMember view.
 */
public class ReportMemberViewModel
{
  private StringProperty usernameLabel;
  private StringProperty commentaryTextArea;
  private MemberModel memberModel;
  private MessageModel messageModel;
  /**
   * Instantiates a new ReportMemberViewModel.
   *
   * @param model The model that this ViewModel uses
   */
  public ReportMemberViewModel(MemberModel memberModel,
      MessageModel messageModel)
  {
    this.memberModel = memberModel;
    this.messageModel = messageModel;

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
  public String getMemberUsername()
  {
    usernameLabel.setValue(memberModel.getMemberUsername());
    return memberModel.getMemberUsername();
  }

  /**
   * Gets report.
   */
  public void getReport()
  {
    Report report = messageModel.getReport(memberModel.getLoggedInUsername(),
        memberModel.getMemberUsername());
    if (report != null)
    {
      commentaryTextArea.setValue(report.getCommentary());
    }
  }

  /**
   * Updates report based on if member already reported this member.
   */
  public void updateReport()
  {
    int memberFromId = memberModel
        .getMemberByUsername(memberModel.getLoggedInUsername()).getId();
    int memberToId = memberModel
        .getMemberByUsername(memberModel.getMemberUsername()).getId();
    Report report = new Report(commentaryTextArea.getValue(), memberFromId,
        memberToId);
    messageModel.updateReport(report);
  }
  /**
   * Adds new report.
   *
   * @return returns new Report object
   */
  public String addReport()
  {
    return messageModel.addReport(commentaryTextArea.getValue(),
        memberModel.getLoggedInUsername(), getMemberUsername());
  }
  /**
   * After Report button have been pressed this method sends data to the model.
   *
   * @return returns string based on if report has been created or updated
   */
  public String onReportButtonPressed()
  {
    if (!addReport().equals("Added"))
    {
      updateReport();
      return "updated";
    }
    return "created";
  }

}
