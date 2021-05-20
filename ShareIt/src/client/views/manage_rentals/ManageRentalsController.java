package client.views.manage_rentals;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.manage_rentals.ManageRentalsViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class ManageRentalsController {
  @FXML private TextField searchField;

  @FXML private Label nameOfRentalLabel;
  @FXML private Label descriptionLabel;
  @FXML private Label stateLabel;
  @FXML private Label priceLabel;
  @FXML private Label otherInformationLabel;
  @FXML private Label categoryLabel;

  private ViewHandler viewHandler;
  private ManageRentalsViewModel manageRentalsViewModel;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws IOException {
    this.viewHandler = viewHandler;
    manageRentalsViewModel = viewModelFactory.getManageRentalsViewModel();
    nameOfRentalLabel.textProperty().bind(manageRentalsViewModel.nameOfRentalProperty());
    descriptionLabel.textProperty().bind(manageRentalsViewModel.descriptionOfRentalProperty());
    stateLabel.textProperty().bind(manageRentalsViewModel.stateOfRentalProperty());
    priceLabel.textProperty().bind(manageRentalsViewModel.priceOfRentalProperty());
    otherInformationLabel.textProperty().bind(manageRentalsViewModel.otherInformationOfRentalProperty());
    categoryLabel.textProperty().bind(manageRentalsViewModel.categoryOfRentalProperty());
  }

  public void searchButton(ActionEvent actionEvent)
  {
  }

  public void changeButton(ActionEvent actionEvent) throws SQLException, IOException {
    viewHandler.setView(viewHandler.menu(), viewHandler.editRental());
  }

  public void deleteButton(ActionEvent actionEvent) throws SQLException, IOException {
//    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "");
//    alert.setTitle("Delete rental");
//    alert.setHeaderText("Are you sure?");
//    alert.getDialogPane().setContentText("Are you sure you want to permanent delete this rental?");
//
//    Optional<ButtonType> result = alert.showAndWait();
//    if (result.get() == ButtonType.YES) {
//      Rental rental = ;
//      RentalDAOImpl.getInstance().delete(rental);
//
//      Stage stage = (Stage) viewHandler.getStage().getScene().getWindow();
//      alert = new Alert(Alert.AlertType.INFORMATION, "");
//      alert.setTitle("Confirmation");
//      alert.setHeaderText("New rental successfully created");
//      alert.initOwner(stage);
//      alert.getDialogPane().setContentText("Click ok to get to welcome page.");
//
//      Optional<ButtonType> result2 = alert.showAndWait();
//      if (result2.get() == ButtonType.OK)
//      {
//        viewHandler.setView(viewHandler.menu(), viewHandler.viewMemberProfile());
//      }
//    }
  }

  public void goBackToProfileOverviewButton(ActionEvent actionEvent) throws IOException {
    viewHandler.setView(viewHandler.menu(), viewHandler.manageAccount());
  }
}
