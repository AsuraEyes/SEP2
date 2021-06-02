package client.viewmodel.view_reported_member;

import client.model.member.MemberModel;
import client.model.rental.RentalModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Report;
/**
 * A class that holds and manages data from the ViewReportedMember view.
 */
public class ViewReportedMemberViewModel
{
  private final RentalModel rentalModel;
  private final MemberModel memberModel;

  private final StringProperty reportedNameLabel;
  private final StringProperty reporterNameLabel;
  private final StringProperty commentaryLabel;
  /**
   * Instantiates StringProperties used for binding with the fields in the controller
   *
   * @param rentalModel The model that this ViewModel uses
   * @param memberModel The model that this ViewModel uses
   */
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

  /**
   * Sets the names of the member that reported and the one that got reported
   * together with the commentary, in the controller.
   *
   */
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
