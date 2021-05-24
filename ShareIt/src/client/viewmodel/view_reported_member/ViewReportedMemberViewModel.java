package client.viewmodel.view_reported_member;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewReportedMemberViewModel {

    private final StringProperty reportedNameLabel;
    private final StringProperty reporterNameLabel;
    private final StringProperty commentaryLabel;

    public ViewReportedMemberViewModel(){
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
}
