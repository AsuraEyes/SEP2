package client.viewmodel.view_reported_member_list;

import client.model.member.MemberModel;
import shared.transferobjects.Report;

import java.util.List;

public class ViewReportedMemberListViewModel
{
  /**
   * A class that holds and manages data from the ViewReportedMemberList view.
   */
  private MemberModel memberModel;
  /**
   * Instantiates a new ViewReportedMemberListViewModel.
   *
   * @param model The model that this ViewModel uses
   */
  public ViewReportedMemberListViewModel(MemberModel memberModel)
  {

    this.memberModel = memberModel;

  }
  /**
   * Gets report list.
   *
   * @return returns list all reports
   */
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
