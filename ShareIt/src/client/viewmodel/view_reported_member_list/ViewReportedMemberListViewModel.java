package client.viewmodel.view_reported_member_list;

import client.model.member.MemberModel;
import shared.transferobjects.Report;

import java.util.List;
/**
 * A class that holds and manages data from the ViewReportedMemberList view.
 */
public class ViewReportedMemberListViewModel
{

  private final MemberModel memberModel;
  /**
   * Instantiates StringProperties used for binding with the fields in the controller
   *
   * @param memberModel The model that this ViewModel uses
   */
  public ViewReportedMemberListViewModel(MemberModel memberModel)
  {

    this.memberModel = memberModel;

  }

  public List<Report> getReportList()
  {
    return memberModel.getReportList();
  }
  /**
   * Sets usernames.
   *
   * @param reporterNameLabel Reporter member username
   * @param reportedNameLabel Reported member username
   */
  public void setUsernames(String reporterNameLabel, String reportedNameLabel)
  {
    memberModel.setSelectedReport(reporterNameLabel, reportedNameLabel);
  }
}
