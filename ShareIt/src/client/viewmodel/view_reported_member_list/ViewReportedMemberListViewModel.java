package client.viewmodel.view_reported_member_list;


import client.model.member.MemberModel;
import client.model.message.MessageModel;
import client.model.rental.RentalModel;
import shared.transferobjects.Member;
import shared.transferobjects.Report;

import java.util.List;

public class ViewReportedMemberListViewModel {
  private RentalModel rentalModel;
  private MemberModel memberModel;
  private MessageModel messageModel;


    public ViewReportedMemberListViewModel(RentalModel rentalModel, MemberModel memberModel, MessageModel messageModel){
      this.rentalModel = rentalModel;
      this.memberModel = memberModel;
      this.messageModel = messageModel;

    }

   public Member getMemberById(int id)
    {
       return memberModel.getMemberById(id);
    }

    public List<Report> getReportList() {
        return messageModel.getReportList();
    }

    public void setUsernames(String reporterNameLabel, String reportedNameLabel) {
      memberModel.setUsernames(reporterNameLabel, reportedNameLabel);
    }
}
