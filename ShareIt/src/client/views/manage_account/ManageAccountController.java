package client.views.manage_account;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

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


  public void searchButton(ActionEvent actionEvent)
  {
  }

  public void changeInformationButton(ActionEvent actionEvent)
  {
  }

  public void goBackToViewedRentalButton(ActionEvent actionEvent)
  {
  }
}
