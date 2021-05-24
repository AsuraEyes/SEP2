package client.views.view_reported_member;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.view_reported_member.ViewReportedMemberViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
        viewReportedMemberViewModel.getReportedPerson();
        viewReportedMemberViewModel.getReporterPerson();
        viewReportedMemberViewModel.getComment();
    }

    public void goBackToReportedMembers(ActionEvent actionEvent) throws
        IOException
    {
        viewHandler.setView(viewHandler.menu(), viewHandler.viewReportedMemberList());
    }
}
