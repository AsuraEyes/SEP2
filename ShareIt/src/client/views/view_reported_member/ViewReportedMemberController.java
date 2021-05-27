package client.views.view_reported_member;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.view_reported_member.ViewReportedMemberViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class ViewReportedMemberController {
    @FXML private Label reportedMemberField;
    @FXML private Label reportedByField;
    @FXML private Label commentField;

    private ViewReportedMemberViewModel viewReportedMemberViewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws IOException{
        this.viewHandler = viewHandler;
        viewReportedMemberViewModel = viewModelFactory.getViewReportedMemberViewModel();
        reportedMemberField.textProperty().bind(viewReportedMemberViewModel.getReportedNameLabel());
        reportedByField.textProperty().bind(viewReportedMemberViewModel.getReporterNameLabel());
        commentField.textProperty().bind(viewReportedMemberViewModel.getCommentaryLabel());
        viewReportedMemberViewModel.loadReport();
    }

    public void goBackToReportedMembers() throws IOException {
        viewReportedMemberViewModel.loadAllReportedMembers();
        viewHandler.setView(viewHandler.menu(), viewHandler.viewReportedMemberList());
    }

    public void reporterMemberClickedOn() throws IOException {
        viewReportedMemberViewModel.setReporterNameLabel();
        viewHandler.setView(viewHandler.menu(),viewHandler.viewMemberProfile());
    }

    public void reportedMemberClickedOn() throws IOException {
        viewReportedMemberViewModel.setReportedNameLabel();
        viewHandler.setView(viewHandler.menu(),viewHandler.viewMemberProfile());
    }
}
