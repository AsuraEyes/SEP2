package client.views.view_reported_member;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.view_reported_member.ViewReportedMemberViewModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ViewReportedMemberController {
    private TextField searchField;
    private Label reportedNameLabel;
    private Label reporterNameLabel;
    private Label commentaryLabel;

    private ViewReportedMemberViewModel viewReportedMemberViewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
        this.viewHandler = viewHandler;
        viewReportedMemberViewModel = viewModelFactory.getViewReportedMemberViewModel();
        searchField.textProperty().bindBidirectional(viewReportedMemberViewModel.getSearchField());
        reportedNameLabel.textProperty().bind(viewReportedMemberViewModel.getReportedNameLabel());
        reporterNameLabel.textProperty().bind(viewReportedMemberViewModel.getReporterNameLabel());
        commentaryLabel.textProperty().bind(viewReportedMemberViewModel.getCommentaryLabel());
    }

    public void searchButton(ActionEvent actionEvent) {

    }
}
