package client.viewmodel.view_reported_member;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Rating;
import shared.transferobjects.Report;

public class ViewReportedMemberViewModel {

    private final StringProperty reportedNameLabel;
    private final StringProperty reporterNameLabel;
    private final StringProperty commentaryLabel;

    private ShareItModel model;

    public ViewReportedMemberViewModel(ShareItModel model){
        this.model = model;
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
        reporterNameLabel.setValue(model.getReporterPerson());
        return model.getReporterPerson();
    }
    public String getReportedPerson(){
        reportedNameLabel.setValue(model.getReportedPerson());
        return model.getReportedPerson();
    }
    public void getComment()
    {
        Report report = model.getReport(getReporterPerson(),getReportedPerson());
        if(report != null)
        {
            commentaryLabel.setValue(report.getCommentary());
        }
    }

    public void setReportedNameLabel()
    {
        model.setReportedUsername(reportedNameLabel.getValue());
    }

    public void setReporterNameLabel()
    {
        model.setReporterUsername(reporterNameLabel.getValue());
    }
}
