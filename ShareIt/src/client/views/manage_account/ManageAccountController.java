package client.views.manage_account;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.manage_account.ManageAccountViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;

public class ManageAccountController
{
  @FXML private TextField searchField;
  @FXML private Label usernameLabel;
  @FXML private Label locationLabel;
  @FXML private Label ratingLabel;
  @FXML private Label addressLabel;
  @FXML private Label contactLabel;
  @FXML private Label otherInformationLabel;
  //rest for rental box
  @FXML private VBox RentalVBox;
  @FXML private Label nameOfRentalLabel;
  @FXML private Label cityLabel;
  @FXML private Label priceLabel;
  @FXML private ImageView ImageView;

  private ViewHandler viewHandler;
  ManageAccountViewModel manageAccountViewModel;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws IOException {
    this.viewHandler = viewHandler;
    manageAccountViewModel = viewModelFactory.getManageAccountViewModel();
  }
  public void searchButton(ActionEvent actionEvent) {
  }

  public void editOrDeleteInformationButton(ActionEvent actionEvent) throws IOException, SQLException {
    viewHandler.setView(viewHandler.menu(), viewHandler.editOrDeleteAccount());
  }
}
