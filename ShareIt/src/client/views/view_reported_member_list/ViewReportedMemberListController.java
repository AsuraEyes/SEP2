package client.views.view_reported_member_list;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.view_reported_member_list.ViewReportedMemberListViewModel;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import shared.transferobjects.Report;

import java.io.IOException;
import java.util.List;

public class ViewReportedMemberListController {
    @FXML private VBox vBox;

    private ViewReportedMemberListViewModel viewReportedMemberListViewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
        this.viewHandler = viewHandler;
        viewReportedMemberListViewModel = viewModelFactory.getViewReportedMemberListViewModel();
        displayReports(viewReportedMemberListViewModel.getReportList()) ;
    }

    public void displayReports(List<Report> reports)
    {
        if (reports != null && !reports.isEmpty()) {
            for (int i = 0; i < reports.size(); i++) {
                Label reportedNameLabel = new Label("Reported: " + reports.get(i).getUsernameTo());
                reportedNameLabel.getStyleClass().add("reported");

                Label reporterNameLabel = new Label("Reporter: " + reports.get(i).getUsernameFrom());
                reporterNameLabel.getStyleClass().add("reporter");

                VBox littleVBox = new VBox();
                littleVBox.getChildren().addAll(reportedNameLabel,reporterNameLabel);
                littleVBox.setPadding(new Insets(20,160,20,160));
                littleVBox.getStyleClass().add("littleVbox");

                vBox.getChildren().addAll(littleVBox);
                vBox.getStyleClass().add("vbox");
                vBox.getChildren().get(i)
                    .addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                        viewReportedMemberListViewModel.setUsernames(reporterNameLabel.getText(), reportedNameLabel.getText());
                        viewHandler.setView(viewHandler.menu(), viewHandler.viewReportedMember());
                    });
            }
        }
    }

}
