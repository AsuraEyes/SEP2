package client.viewmodel.view_reported_member_list;

import client.model.ShareItModel;
import shared.transferobjects.Member;
import shared.transferobjects.Report;

import java.util.List;

/**
 * A class that holds and manages data from the ViewReportedMemberList view.
 */
public class ViewReportedMemberListViewModel {
    private ShareItModel model;

  /**
   * Instantiates a new ViewReportedMemberListViewModel.
   *
   * @param model The model that this ViewModel uses
   */
  public ViewReportedMemberListViewModel(ShareItModel model){
        this.model = model;
    }

  /**
   * Gets member by id.
   *
   * @param id Member's ID
   * @return returns Member with matchin id
   */
  public Member getMemberById(int id)
    {
       return model.getMemberById(id);
    }

  /**
   * Gets report list.
   *
   * @return returns list all reports
   */
  public List<Report> getReportList() {
        return model.getReportList();
    }

  /**
   * Sets usernames.
   *
   * @param reporterNameLabel Reporter member username
   * @param reportedNameLabel Reported member username
   */
  public void setUsernames(String reporterNameLabel, String reportedNameLabel) {
      model.setUsernames(reporterNameLabel, reportedNameLabel);
    }
}
