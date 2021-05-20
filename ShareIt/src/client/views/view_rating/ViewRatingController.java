package client.views.view_rating;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.view_rating.ViewRatingViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
        viewHandler.setView(viewHandler.menu(), viewHandler.viewMemberProfile());
    }

    public void seeFullRating(ActionEvent actionEvent) {

    }

    public void displayRatings(ArrayList<Rating> ratings){

    }
}
