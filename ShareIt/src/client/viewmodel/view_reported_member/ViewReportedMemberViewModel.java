package client.viewmodel.view_reported_member;

import client.model.member.MemberModel;
import client.model.rental.RentalModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Report;

public class ViewReportedMemberViewModel
{
  private final RentalModel rentalModel;
  private final MemberModel memberModel;

  private final StringProperty reportedNameLabel;
  private final StringProperty reporterNameLabel;
  private final StringProperty commentaryLabel;

  public ViewReportedMemberViewModel(RentalModel rentalModel,
      MemberModel memberModel)
  {
    this.rentalModel = rentalModel;
    this.memberModel = memberModel;

    reportedNameLabel = new SimpleStringProperty();
    reporterNameLabel = new SimpleStringProperty();
    commentaryLabel = new SimpleStringProperty();
  }

  public StringProperty getReportedNameLabel()
  {
    return reportedNameLabel;
  }

  public StringProperty getReporterNameLabel()
  {
    return reporterNameLabel;
  }

  public StringProperty getCommentaryLabel()
  {
    return commentaryLabel;
  }

  public void loadReport()
  {
    Report report = memberModel.getSelectedReport();
    reporterNameLabel.setValue("Reporter: " + report.getUsernameFrom());
    reportedNameLabel.setValue("Reported: " + report.getUsernameTo());
    commentaryLabel.setValue(report.getCommentary());
  }

  public void setReportedNameLabel()
  {
    memberModel.setMemberUsername(reportedNameLabel.getValue().substring(10));
    rentalModel.setAllMemberRentals(reportedNameLabel.getValue().substring(10));
  }

  public void setReporterNameLabel()
  {
    memberModel.setMemberUsername(reporterNameLabel.getValue().substring(10));
    rentalModel.setAllMemberRentals(reporterNameLabel.getValue().substring(10));
  }

  public void loadAllReportedMembers()
  {
    memberModel.setReportList();
  }
}
