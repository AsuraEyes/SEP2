package client.views.view_rating;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.view_rating.ViewRatingViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.controlsfx.control.InfoOverlay;
import shared.transferobjects.Rating;

import java.io.IOException;
import java.util.ArrayList;

public class ViewRatingController {
    @FXML private Label userNameLabel;
    @FXML private Label locationLabel;
    @FXML private Label ratingLabel;
    @FXML private Label addressLabel;
    @FXML private Label contactLabel;
    @FXML private Label otherInformationLabel;
    @FXML private VBox vBox;

    private ViewRatingViewModel viewRatingViewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws IOException {
        this.viewHandler = viewHandler;
        viewRatingViewModel = viewModelFactory.getViewRatingViewModel();
        userNameLabel.textProperty().bind(viewRatingViewModel.getUsername());
        locationLabel.textProperty().bind(viewRatingViewModel.getLocation());
        ratingLabel.textProperty().bind(viewRatingViewModel.getRating());
        addressLabel.textProperty().bind(viewRatingViewModel.getAddress());
        contactLabel.textProperty().bind(viewRatingViewModel.getContact());
        otherInformationLabel.textProperty().bind(viewRatingViewModel.getOtherInformation());

        displayRatings(viewRatingViewModel.getAllRatingsOnMember(viewRatingViewModel.getMemberUsername()));
    }

    public void searchButton(ActionEvent actionEvent) {

    }

    public void goBackToUserPage(ActionEvent actionEvent) throws IOException {
        if(userNameLabel.textProperty().getValue().equals(viewRatingViewModel.getUserType())){
            viewHandler.setView(viewHandler.menu(), viewHandler.manageAccount());
        }
        else{
            viewHandler.setView(viewHandler.menu(), viewHandler.viewMemberProfile());
        }

    }

    public void seeFullRating(ActionEvent actionEvent) {

    }

    public void displayRatings(ArrayList<Rating> ratings){
        if (ratings != null && !ratings.isEmpty())
        {
            for (int i = 0; i < ratings.size(); i++)
            {
                VBox ratingBox = new VBox();
                Label ratingLabel = new Label("Rating: " + ratings.get(i).getRating());
                ratingLabel.setFont(Font.font ("Californian FB", 24));
                ratingLabel.setTextFill(Color.WHITE);
                Text commentary = new Text("Commentary: \n" + ratings.get(i).getCommentary());
                commentary.setFill(Color.WHITE);
                TextFlow textFlow = new TextFlow();
                textFlow.getChildren().addAll(commentary);
                ratingBox.getChildren().addAll(ratingLabel,textFlow);
                ratingBox.setSpacing(10);
                ratingBox.setPadding(new Insets(20,160,20,160));
                ratingBox.setStyle("-fx-border-color: #FF8C64; -fx-border-width: 3; -fx-background-color: #FF8C64;");
                vBox.getChildren().add(ratingBox);
            }
        }
    }
}
