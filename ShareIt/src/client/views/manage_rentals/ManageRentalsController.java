package client.views.manage_rentals;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.manage_rentals.ManageRentalsViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Optional;
/**
 * A class that manages an interface and handle interactions in ManageRentals view
 */
public class ManageRentalsController
{

  @FXML private Label nameOfRentalLabel;
  @FXML private Label descriptionLabel;
  @FXML private Label stateLabel;
  @FXML private Label priceLabel;
  @FXML private Label otherInformationLabel;
  @FXML private Label categoryLabel;
  @FXML private ImageView imageView;

  private ViewHandler viewHandler;
  private ManageRentalsViewModel manageRentalsViewModel;
  /**
   * Init.
   *
   * @param viewHandler      the view handler
   * @param viewModelFactory the view model factory
   */
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    manageRentalsViewModel = viewModelFactory.getManageRentalsViewModel();

    Bindings.bindBidirectional(this.imageView.imageProperty(),
        manageRentalsViewModel.imagePropertyProperty());

    nameOfRentalLabel.textProperty()
        .bind(manageRentalsViewModel.nameOfRentalProperty());
    descriptionLabel.textProperty()
        .bind(manageRentalsViewModel.descriptionOfRentalProperty());
    stateLabel.textProperty()
        .bind(manageRentalsViewModel.stateOfRentalProperty());
    priceLabel.textProperty()
        .bind(manageRentalsViewModel.priceOfRentalProperty());
    otherInformationLabel.textProperty()
        .bind(manageRentalsViewModel.otherInformationOfRentalProperty());
    categoryLabel.textProperty()
        .bind(manageRentalsViewModel.categoryOfRentalProperty());
  }

  public void editButton()
  {
    viewHandler.setView(viewHandler.menu(), viewHandler.editRental());
    manageRentalsViewModel.getSelectedRental();
  }
  /**
   * Deletes a rental after validation.
   */
  public void deleteButton()
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "");
    alert.setTitle("Delete rental");
    alert.setHeaderText("Are you sure?");
    alert.getDialogPane().setContentText(
        "Are you sure you want to permanent delete this rental?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK)
    {
      boolean success = manageRentalsViewModel.deleteRental();
      if (success)
      {
        Stage stage = (Stage) viewHandler.getStage().getScene().getWindow();
        alert = new Alert(Alert.AlertType.INFORMATION, "");
        alert.setTitle("Confirmation");
        alert.setHeaderText("Rental successfully deleted");
        alert.initOwner(stage);
        alert.getDialogPane()
            .setContentText("Click ok to get to your profile.");

        Optional<ButtonType> result2 = alert.showAndWait();
        if (result2.get() == ButtonType.OK)
        {
          manageRentalsViewModel.setAllMemberRentals();
          viewHandler.setView(viewHandler.menu(), viewHandler.manageAccount());
        }
      }
    }
  }

  public void goBackToProfileOverviewButton()
  {
    manageRentalsViewModel.setMemberUsername();
    viewHandler.setView(viewHandler.menu(), viewHandler.manageAccount());
  }
}
