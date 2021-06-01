package client.views.view_reported_member;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.view_reported_member.ViewReportedMemberViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * A class that manages an interface and handle interactions in ViewReportedMember view
 */
public class ViewReportedMemberController {
    @FXML private Label reportedMemberField;
    @FXML private Label reportedByField;
    @FXML private Label commentField;

    private ViewReportedMemberViewModel viewReportedMemberViewModel;
    private ViewHandler viewHandler;

    /**
     * Init.
     *
     * @param viewHandler      the view handler
     * @param viewModelFactory the view model factory
     * @throws IOException the io exception
     */
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

    /**
     * Changes a view when button was pressed.
     *
     * @throws IOException
     */
    public void goBackToReportedMembers() throws IOException {
        viewHandler.setView(viewHandler.menu(), viewHandler.viewReportedMemberList());
    }

    /**
     * Views profile of chosen reporter member.
     *
     * @throws IOException
     */
    public void reporterMemberClickedOn() throws IOException {
        viewReportedMemberViewModel.setReporterNameLabel();
        viewHandler.setView(viewHandler.menu(),viewHandler.viewMemberProfile());
    }

    /**
     * Views profile of chosen reported member.
     *
     * @throws IOException
     */
    public void reportedMemberClickedOn() throws IOException {
        viewReportedMemberViewModel.setReportedNameLabel();
        viewHandler.setView(viewHandler.menu(),viewHandler.viewMemberProfile());
    }
}
