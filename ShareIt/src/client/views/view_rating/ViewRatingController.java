package client.views.view_rating;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.view_rating.ViewRatingViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ViewRatingController {
    @FXML private TextField searchField;
    @FXML private Label userNameLabel;
    @FXML private Label locationLabel;
    @FXML private Label ratingLabel;
    @FXML private Label addressLabel;
    @FXML private Label contactLabel;
    @FXML private Label otherInformationLabel;

    @FXML private Label commenterNameLabel;
    @FXML private Label commenterRateLabel;
    @FXML private Label commentLabel;

    private ViewRatingViewModel viewRatingViewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
        this.viewHandler = viewHandler;
        viewRatingViewModel = viewModelFactory.getViewRatingViewModel();
        searchField.textProperty().bindBidirectional(viewRatingViewModel.getSearchField());
        userNameLabel.textProperty().bind(viewRatingViewModel.getUserNameLabel());
        locationLabel.textProperty().bind(viewRatingViewModel.getLocationLabel());
        ratingLabel.textProperty().bind(viewRatingViewModel.getRatingLabel());
        addressLabel.textProperty().bind(viewRatingViewModel.getAddressLabel());
        contactLabel.textProperty().bind(viewRatingViewModel.getContactLabel());
        otherInformationLabel.textProperty().bind(viewRatingViewModel.getOtherInfoLabel());
        commenterNameLabel.textProperty().bind(viewRatingViewModel.getCommenterNameLabel());
        commenterRateLabel.textProperty().bind(viewRatingViewModel.getCommenterRateLabel());
        commentLabel.textProperty().bind(viewRatingViewModel.getCommentLabel());
    }

    public void searchButton(ActionEvent actionEvent) {

    }

    public void goBackToUserPage(ActionEvent actionEvent) {

    }

    public void seeFullRating(ActionEvent actionEvent) {

    }

    public void loadMoreRatings(ActionEvent actionEvent) {

    }
}
