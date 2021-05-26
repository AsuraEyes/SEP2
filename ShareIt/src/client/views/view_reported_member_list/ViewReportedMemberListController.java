package client.views.view_reported_member_list;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.view_reported_member_list.ViewReportedMemberListViewModel;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import shared.transferobjects.Report;

import java.io.IOException;
import java.util.List;

public class ViewReportedMemberListController {
    @FXML private VBox vBox;

    private ViewReportedMemberListViewModel viewReportedMemberListViewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws IOException {
        this.viewHandler = viewHandler;
        viewReportedMemberListViewModel = viewModelFactory.getViewReportedMemberListViewModel();
        displayReports(viewReportedMemberListViewModel.getReportList()) ;
    }

    public void displayReports(List<Report> reports)
    {
        if (reports != null && !reports.isEmpty()) {
            for (int i = 0; i < reports.size(); i++) {
                Label reportedNameLabel = new Label(viewReportedMemberListViewModel.getMemberById(reports.get(i).getMemberTo()).getUsername());
                reportedNameLabel.setFont(Font.font ("Californian FB", 24));
                reportedNameLabel.setTextFill(Color.WHITE);
                Label reporterNameLabel = new Label(viewReportedMemberListViewModel.getMemberById(reports.get(i).getMemberFrom()).getUsername());
                reporterNameLabel.setFont(Font.font ("Californian FB", 16));
                reporterNameLabel.setTextFill(Color.WHITE);
                VBox littleVBox = new VBox();
                littleVBox.getChildren().addAll(reportedNameLabel,reporterNameLabel);

                vBox.setSpacing(35);
                littleVBox.setPadding(new Insets(30,160,30,160));
                littleVBox.setStyle("-fx-background-color:#7D6B7D");

                vBox.getChildren().addAll(littleVBox);

                vBox.getChildren().get(i)
                    .addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                        try {
                            viewReportedMemberListViewModel.setUsernames(reporterNameLabel.getText(), reportedNameLabel.getText());
                            viewHandler.setView(viewHandler.menu(), viewHandler.viewReportedMember());
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    });
            }
        }
    }

}
