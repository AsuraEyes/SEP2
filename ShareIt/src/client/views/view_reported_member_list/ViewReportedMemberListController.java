package client.views.view_reported_member_list;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.view_reported_member_list.ViewReportedMemberListViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import server.model.database.member.MemberDAOImpl;
import shared.transferobjects.Member;
import shared.transferobjects.Report;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ViewReportedMemberListController {
    @FXML private VBox vBox;

    private ViewReportedMemberListViewModel viewReportedMemberListViewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
        throws IOException
    {
        this.viewHandler = viewHandler;
        viewReportedMemberListViewModel = viewModelFactory.getViewReportedMemberListViewModel();
        displayReports(viewReportedMemberListViewModel.getReportList()) ;
    }

    public void displayReports(List<Report> reports)
    {
        if (reports != null && !reports.isEmpty())
        {
            for (int i = 0; i < reports.size(); i++)
            {
                VBox reportBox = new VBox();
                Label reportedNameLabel = new Label(viewReportedMemberListViewModel.getMemberById(reports.get(i).getMemberTo()).getUsername()+"\n");
                reportedNameLabel.setFont(Font.font ("Californian FB", 24));
                reportedNameLabel.setTextFill(Color.WHITE);
                Label reporterNameLabel = new Label(viewReportedMemberListViewModel.getMemberById(reports.get(i).getMemberFrom()).getUsername());
                reporterNameLabel.setFont(Font.font ("Californian FB", 18));
                reporterNameLabel.setTextFill(Color.WHITE);
                TextFlow textFlow = new TextFlow();
                textFlow.getChildren().addAll(reportedNameLabel,reporterNameLabel);
                reportBox.getChildren().addAll(textFlow);
                vBox.setSpacing(10);
                vBox.setPadding(new Insets(20,160,20,160));
                vBox.setStyle("-fx-border-color: #FF665A; -fx-border-width: 3; -fx-background-color:#7D6B7D;");
                vBox.getChildren().addAll(reportedNameLabel, reporterNameLabel);

                vBox.getChildren().get(i)
                    .addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                        try
                        {
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
