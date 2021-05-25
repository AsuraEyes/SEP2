package client.viewmodel.view_reported_member_list;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import shared.transferobjects.Member;
import shared.transferobjects.Report;

import java.util.List;

public class ViewReportedMemberListViewModel {

    private ShareItModel model;



    public ViewReportedMemberListViewModel(ShareItModel model){
        this.model = model;

    }

   public Member getMemberById(int id)
    {
       return model.getMemberById(id);
    }

    public List<Report> getReportList() {
        return model.getReportList();
    }

    public void setUsernames(String reporterNameLabel, String reportedNameLabel)
    {
      model.setUsernames(reporterNameLabel, reportedNameLabel);
    }
}
