package client.views.view_rental;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.view_rental.ViewRentalViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
/**
 * A class that manages an interface and handle interactions in ViewRental view
 */
public class ViewRentalController
{
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
  /**
   * Init.
   * Changes the view depending on the user type
   * @param viewHandler      the view handler
   * @param viewModelFactory the view model factory
   */
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    viewRentalViewModel = viewModelFactory.getViewRentalViewModel();
    this.viewHandler = viewHandler;

    Bindings.bindBidirectional(this.imageView.imageProperty(),
        viewRentalViewModel.imagePropertyProperty());

    nameOfRentalLabel.textProperty()
        .bind(viewRentalViewModel.nameOfRentalProperty());
    descriptionLabel.textProperty()
        .bind(viewRentalViewModel.descriptionOfRentalProperty());
    stateLabel.textProperty().bind(viewRentalViewModel.stateOfRentalProperty());
    priceLabel.textProperty().bind(viewRentalViewModel.priceOfRentalProperty());
    otherInformationLabel.textProperty()
        .bind(viewRentalViewModel.otherInformationOfRentalProperty());
    categoriesLabel.textProperty()
        .bind(viewRentalViewModel.categoryOfRentalProperty());
    usernameLabel.textProperty()
        .bind(viewRentalViewModel.usernameOfRentalProperty());
    locationLabel.textProperty()
        .bind(viewRentalViewModel.locationOfRentalProperty());
    ratingLabel.textProperty()
        .bind(viewRentalViewModel.ratingOfUserOfRentalProperty());

    if (viewRentalViewModel.getUserType().equals("Administrator"))
    {
      goBackButton.setText("Go back to member page");
    }
  }
  /**
   * Changes a view when button was pressed and user type checked.
   */
  public void goBackToSearchResultsButton()
  {
    if (viewRentalViewModel.getUserType().equals("Administrator"))
    {
      viewHandler.setView(viewHandler.menu(), viewHandler.viewMemberProfile());
    }
    else
    {
      viewHandler.setView(viewHandler.menu(), viewHandler.searchForRental());
    }

  }
  /**
   * Changes a view when button was pressed depending on the user type.
   */
  public void seeMoreButton()
  {
    viewRentalViewModel.setMemberUsername();
    viewRentalViewModel.setMemberRentals();
    if (viewRentalViewModel.usernameOfRentalProperty().getValue().substring(10)
        .equals(viewRentalViewModel.getLoggedInUsername()))
    {
      viewHandler.setView(viewHandler.menu(), viewHandler.manageAccount());
    }
    else
    {
      viewHandler.setView(viewHandler.menu(), viewHandler.viewMemberProfile());
    }
  }
}
