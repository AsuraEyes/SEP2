package client.views.view_reported_member_list;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.view_reported_member_list.ViewReportedMemberListViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ViewReportedMemberListController {private TextField searchField;
    @FXML private Label reportedNameLabel;
    @FXML private Label reporterNameLabel;

    private ViewReportedMemberListViewModel viewReportedMemberListViewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
        this.viewHandler = viewHandler;
        viewReportedMemberListViewModel = viewModelFactory.getViewReportedMemberListViewModel();
        searchField.textProperty().bindBidirectional(viewReportedMemberListViewModel.getSearchField());
        reportedNameLabel.textProperty().bind(viewReportedMemberListViewModel.getReportedNameLabel());
        reporterNameLabel.textProperty().bind(viewReportedMemberListViewModel.getReporterNameLabel());
    }


}
