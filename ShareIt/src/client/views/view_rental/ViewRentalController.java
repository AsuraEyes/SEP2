package client.views.view_rental;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.model.state.StateManager;
import client.viewmodel.view_rental.ViewRentalViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class ViewRentalController {
  @FXML private Label nameOfRentalLabel;
  @FXML private Label descriptionLabel;
  @FXML private Label stateLabel;
  @FXML private Label priceLabel;
  @FXML private Label otherInformationLabel;
  @FXML private Label categoriesLabel;
  @FXML private ImageView imageView;
  @FXML private Button goBackButton;
  @FXML private Label usernameLabel;
  @FXML private Label locationLabel;
  @FXML private Label ratingLabel;


  private ViewHandler viewHandler;
  private ViewRentalViewModel viewRentalViewModel;


  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws IOException {
    viewRentalViewModel = viewModelFactory.getViewRentalViewModel();
    this.viewHandler = viewHandler;

    Bindings.bindBidirectional(this.imageView.imageProperty(), viewRentalViewModel.imagePropertyProperty());
    Bindings.bindBidirectional(this.imageView.idProperty(), viewRentalViewModel.getImageIdMemberId());

    nameOfRentalLabel.textProperty().bind(viewRentalViewModel.nameOfRentalProperty());
    descriptionLabel.textProperty().bind(viewRentalViewModel.descriptionOfRentalProperty());
    stateLabel.textProperty().bind(viewRentalViewModel.stateOfRentalProperty());
    priceLabel.textProperty().bind(viewRentalViewModel.priceOfRentalProperty());
    otherInformationLabel.textProperty().bind(viewRentalViewModel.otherInformationOfRentalProperty());
    categoriesLabel.textProperty().bind(viewRentalViewModel.categoryOfRentalProperty());
    usernameLabel.textProperty().bind(viewRentalViewModel.usernameOfRentalProperty());
    locationLabel.textProperty().bind(viewRentalViewModel.locationOfRentalProperty());
    ratingLabel.textProperty().bind(viewRentalViewModel.ratingOfUserOfRentalProperty());

    if(viewRentalViewModel.getUserType().equals("Administrator")){
      goBackButton.setText("Go back to member page");
    }
  }


  public void goBackToSearchResultsButton() throws IOException {
    if(viewRentalViewModel.getUserType().equals("Administrator")){
      viewHandler.setView(viewHandler.menu(), viewHandler.viewMemberProfile());
    }
    else{
      viewHandler.setView(viewHandler.menu(), viewHandler.searchForRental());
    }

  }

  public void seeMoreButton() throws IOException
  {
    viewRentalViewModel.setMemberUsername();
    viewRentalViewModel.setMemberRentals();
    if(viewRentalViewModel.usernameOfRentalProperty().getValue().equals(viewRentalViewModel.getLoggedInUsername())){
      viewHandler.setView(viewHandler.menu(), viewHandler.manageAccount());
    }
    else{
      viewHandler.setView(viewHandler.menu(), viewHandler.viewMemberProfile());
    }
  }
}
