package client.views.view_member_profile;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.view_member_profile.ViewMemberProfileViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ViewMemberProfileController
{
  @FXML private TextField searchField;
  @FXML private Label usernameLabel;
  @FXML private Label locationLabel;
  @FXML private Label ratingLabel;
  @FXML private Label addressLabel;
  @FXML private Label contactLabel;
  @FXML private Label otherInformationLabel;
  
  public VBox rentalVBox;
  public Label nameOfRentalLabel;
  public Label cityLabel;
  public Label priceLabel;
  public ImageView ImageView;

  private ViewMemberProfileViewModel viewMemberProfileViewModel;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
    this.viewHandler = viewHandler;
    viewMemberProfileViewModel = viewModelFactory.getViewMemberProfileViewModel();
    searchField.textProperty().bindBidirectional(viewMemberProfileViewModel.getSearchField());
    usernameLabel.textProperty().bind(viewMemberProfileViewModel.getUsernameLabel());
    locationLabel.textProperty().bind(viewMemberProfileViewModel.getLocationLabel());
    ratingLabel.textProperty().bind(viewMemberProfileViewModel.getRatingLabel());
    addressLabel.textProperty().bind(viewMemberProfileViewModel.getAddressLabel());
    contactLabel.textProperty().bind(viewMemberProfileViewModel.getContactLabel());
    otherInformationLabel.textProperty().bind(viewMemberProfileViewModel.getOtherInformationLabel());

  }

  public void searchButton(ActionEvent actionEvent)
  {

  }

  public void reportButton(ActionEvent actionEvent)
  {
  }

  public void chatButton(ActionEvent actionEvent)
  {
  }

  public void rateButton(ActionEvent actionEvent)
  {
  }
  
  public void deleteButton(ActionEvent actionEvent)
  {
  }
  
  public void goBackToViewedRentalButton(ActionEvent actionEvent)
  {
  }

  public void loadMoreRentalButton(ActionEvent actionEvent)
  {
  }

  public void viewRatingButton(ActionEvent actionEvent)
  {
  }

  public void rentalVBoxClicked(MouseEvent mouseEvent)
  {
  }
}
