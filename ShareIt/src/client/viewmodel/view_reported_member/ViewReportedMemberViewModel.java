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
   * Instantiates a new ViewReportedMemberViewModel.
   *
   * @param model The model that this ViewModel uses
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
    /**
     * Get reportedNameLabel.
     *
     * @return returns reported member username
     */
  public StringProperty getReportedNameLabel()
  {
    return reportedNameLabel;
  }
    /**
     * Get reporterNameLabel.
     *
     * @return returns reporter member username
     */
  public StringProperty getReporterNameLabel()
  {
    return reporterNameLabel;
  }
    /**
     * Get commentaryLabel.
     *
     * @return returns report commentary
     */
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
    /**
     * Sets reported person.
     */
  public void setReportedNameLabel()
  {
    memberModel.setMemberUsername(reportedNameLabel.getValue().substring(10));
    rentalModel.setAllMemberRentals(reportedNameLabel.getValue().substring(10));
  }
    /**
     * Sets reporter person.
     */
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
