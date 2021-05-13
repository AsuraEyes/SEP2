package client.views.view_rating_full;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.view_rating_full.ViewRatingFullViewModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ViewRatingFullController {
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

    private ViewRatingFullViewModel viewRatingFullViewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
        this.viewHandler = viewHandler;
        viewRatingFullViewModel = viewModelFactory.getViewRatingFullViewModel();
        searchField.textProperty().bindBidirectional(viewRatingFullViewModel.getSearchField());
        userNameLabel.textProperty().bind(viewRatingFullViewModel.getUserNameLabel());
        locationLabel.textProperty().bind(viewRatingFullViewModel.getLocationLabel());
        ratingLabel.textProperty().bind(viewRatingFullViewModel.getRatingLabel());
        addressLabel.textProperty().bind(viewRatingFullViewModel.getAddressLabel());
        contactLabel.textProperty().bind(viewRatingFullViewModel.getContactLabel());
        otherInfoLabel.textProperty().bind(viewRatingFullViewModel.getOtherInfoLabel());
        commenterNameLabel.textProperty().bind(viewRatingFullViewModel.getCommenterNameLabel());
        commenterRateLabel.textProperty().bind(viewRatingFullViewModel.getCommenterRateLabel());
        commentLabel.textProperty().bind(viewRatingFullViewModel.getCommentLabel());
    }

    public void searchButton(ActionEvent actionEvent) {

    }

    public void goBackToUserPage(ActionEvent actionEvent) {

    }
}
