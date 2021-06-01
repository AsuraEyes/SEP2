package client.views.view_rating;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.view_rating.ViewRatingViewModel;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import shared.transferobjects.Rating;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A class that manages an interface and handle interactions in ViewRating view
 */
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

    /**
     * Init.
     *
     * @param viewHandler      the view handler
     * @param viewModelFactory the view model factory
     * @throws IOException the io exception
     */
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

    /**
     * Changes view when button was pressed and user type checked.
     *
     * @throws IOException
     */
    public void goBackToUserPage() throws IOException {
        if(userNameLabel.textProperty().getValue().equals(viewRatingViewModel.getUserType())){
            viewHandler.setView(viewHandler.menu(), viewHandler.manageAccount());
        }
        else{
            viewHandler.setView(viewHandler.menu(), viewHandler.viewMemberProfile());
        }

    }

    /**
     * Displays ratings.
     *
     * @param ratings All member's ratings
     */
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
