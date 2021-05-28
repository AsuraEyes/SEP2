package client.viewmodel.report_member;


import client.model.member.MemberModel;
import client.model.message.MessageModel;
import client.model.rental.RentalModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Report;

import java.io.IOException;

public class ReportMemberViewModel
{
  private RentalModel rentalModel;
  private MemberModel memberModel;
  private MessageModel messageModel;

  private final StringProperty usernameLabel;
  private final StringProperty commentaryTextArea;

  public ReportMemberViewModel(RentalModel rentalModel, MemberModel memberModel, MessageModel messageModel)
  {
    this.rentalModel = rentalModel;
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

  public String getMemberUsername() {
    usernameLabel.setValue(memberModel.getMemberUsername());
    return memberModel.getMemberUsername();
  }

  public void getReport()
  {
    Report report = messageModel.getReport(memberModel.getLoggedInUsername(),
        memberModel.getMemberUsername());
    if(report != null)
    {
      commentaryTextArea.setValue(report.getCommentary());
    }
  }

  public void updateReport()
  {
    int memberFromId = memberModel.getMemberByUsername(memberModel.getLoggedInUsername())
        .getId();
    int memberToId = memberModel.getMemberByUsername(memberModel.getMemberUsername())
        .getId();
    Report report = new Report(commentaryTextArea.getValue(), memberFromId, memberToId);
    messageModel.updateReport(report);
  }

  public String addReport()
  {
    return messageModel.addReport(commentaryTextArea.getValue(), memberModel.getLoggedInUsername(),
            getMemberUsername());
  }

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
