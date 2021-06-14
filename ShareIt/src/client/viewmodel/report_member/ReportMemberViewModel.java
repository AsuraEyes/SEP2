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
   * Instantiates StringProperties used for binding with the fields in the controller
   *
   * @param memberModel The model that this ViewModel uses
   * @param messageModel The model that this ViewModel uses
   */
  public ReportMemberViewModel(MemberModel memberModel,
      MessageModel messageModel)
  {
    this.memberModel = memberModel;
    this.messageModel = messageModel;

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
    usernameLabel.setValue(memberModel.getMemberUsername());
    return memberModel.getMemberUsername();
  }

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

  public String addReport()
  {
    return messageModel.addReport(commentaryTextArea.getValue(),
        memberModel.getLoggedInUsername(), getMemberUsername());
  }
  /**
   * After Report button have been pressed this method sends data to the model.
   *
   * @return string based on if report has been created or updated
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
