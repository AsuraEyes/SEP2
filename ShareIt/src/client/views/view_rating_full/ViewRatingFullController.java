package client.views.view_rating_full;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.view_rating_full.ViewRatingFullViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ViewRatingFullController {
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
        otherInformationLabel.textProperty().bind(viewRatingFullViewModel.getOtherInfoLabel());
        commenterNameLabel.textProperty().bind(viewRatingFullViewModel.getCommenterNameLabel());
        commenterRateLabel.textProperty().bind(viewRatingFullViewModel.getCommenterRateLabel());
        commentLabel.textProperty().bind(viewRatingFullViewModel.getCommentLabel());
    }

    public void searchButton(ActionEvent actionEvent) {

    }

    public void goBackToUserPage(ActionEvent actionEvent) {

    }

  public void goBackToUserRatings(ActionEvent actionEvent)
  {
  }
}
