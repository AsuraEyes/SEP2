package client.viewmodel.view_reported_member_list;

import client.model.member.MemberModel;
import shared.transferobjects.Report;

import java.util.List;

public class ViewReportedMemberListViewModel
{

  private MemberModel memberModel;

  public ViewReportedMemberListViewModel(MemberModel memberModel)
  {

    this.memberModel = memberModel;

  }

  public List<Report> getReportList()
  {
    return memberModel.getReportList();
  }

  public void setUsernames(String reporterNameLabel, String reportedNameLabel)
  {
    memberModel.setSelectedReport(reporterNameLabel, reportedNameLabel);
  }
}
