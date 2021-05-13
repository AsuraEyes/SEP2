package client.views.view_rating;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.view_rating.ViewRatingViewModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ViewRatingController {
    private TextField searchField;
    private Label userNameLabel;
    private Label locationLabel;
    private Label ratingLabel;
    private Label addressLabel;
    private Label contactLabel;
    private Label otherInfoLabel;

    private Label commenterNameLabel;
    private Label commenterRateLabel;
    private Label commentLabel;

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
        otherInfoLabel.textProperty().bind(viewRatingViewModel.getOtherInfoLabel());
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
