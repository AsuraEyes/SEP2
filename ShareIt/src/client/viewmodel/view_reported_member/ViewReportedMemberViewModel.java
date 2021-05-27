package client.viewmodel.view_reported_member;


import client.model.member.MemberModel;
import client.model.message.MessageModel;
import client.model.rental.RentalModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Report;

public class ViewReportedMemberViewModel {
    private RentalModel rentalModel;
    private MemberModel memberModel;
    private MessageModel messageModel;

    private final StringProperty reportedNameLabel;
    private final StringProperty reporterNameLabel;
    private final StringProperty commentaryLabel;

    public ViewReportedMemberViewModel(RentalModel rentalModel, MemberModel memberModel, MessageModel messageModel){
        this.rentalModel = rentalModel;
        this.memberModel = memberModel;
        this.messageModel = messageModel;

        reportedNameLabel = new SimpleStringProperty();
        reporterNameLabel = new SimpleStringProperty();
        commentaryLabel = new SimpleStringProperty();
    }

    public StringProperty getReportedNameLabel(){
        return reportedNameLabel;
    }

    public StringProperty getReporterNameLabel(){
        return reporterNameLabel;
    }

    public StringProperty getCommentaryLabel(){
        return commentaryLabel;
    }

    public String getReporterPerson(){
        reporterNameLabel.setValue(memberModel.getReporterPerson());
        return memberModel.getReporterPerson();
    }

    public String getReportedPerson(){
        reportedNameLabel.setValue(memberModel.getReportedPerson());
        return memberModel.getReportedPerson();
    }

    public void getComment(){
        Report report = messageModel.getReport(getReporterPerson(),getReportedPerson());
        if(report != null) {
            commentaryLabel.setValue(report.getCommentary());
        }
    }

    public void setReportedNameLabel() {
        memberModel.setReportedUsername(reportedNameLabel.getValue());
    }

    public void setReporterNameLabel()
    {
        memberModel.setReporterUsername(reporterNameLabel.getValue());
    }
}
