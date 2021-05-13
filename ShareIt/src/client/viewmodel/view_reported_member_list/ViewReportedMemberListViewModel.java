package client.viewmodel.view_reported_member_list;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewReportedMemberListViewModel {
    private final StringProperty searchField;
    private final StringProperty reportedNameLabel;
    private final StringProperty reporterNameLabel;

    public ViewReportedMemberListViewModel(){
        searchField = new SimpleStringProperty("Search");
        reportedNameLabel = new SimpleStringProperty();
        reporterNameLabel = new SimpleStringProperty();
    }

    public StringProperty getSearchField(){
        return searchField;
    }

    public StringProperty getReportedNameLabel(){
        return reportedNameLabel;
    }

    public StringProperty getReporterNameLabel(){
        return reporterNameLabel;
    }
}
