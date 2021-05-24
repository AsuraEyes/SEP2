package client.viewmodel.view_reported_member_list;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewReportedMemberListViewModel {

    private final StringProperty reportedNameLabel;
    private final StringProperty reporterNameLabel;

    public ViewReportedMemberListViewModel(){

        reportedNameLabel = new SimpleStringProperty();
        reporterNameLabel = new SimpleStringProperty();
    }

    public StringProperty getReportedNameLabel(){
        return reportedNameLabel;
    }

    public StringProperty getReporterNameLabel(){
        return reporterNameLabel;
    }
}
